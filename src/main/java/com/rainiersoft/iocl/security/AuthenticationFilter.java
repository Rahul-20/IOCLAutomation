package com.rainiersoft.iocl.security;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rainiersoft.iocl.dao.IOCLUserDetailsDAO;
import com.rainiersoft.iocl.dao.impl.IOCLUserDetailsDAOImpl;
import com.rainiersoft.iocl.entity.IoclUserDetail;
import com.rainiersoft.iocl.entity.IoclUserroleMapping;
import com.rainiersoft.iocl.exception.IOCLWSException;
import com.rainiersoft.iocl.util.CommonUtilites;
import com.rainiersoft.iocl.util.ErrorMessageConstants;
 
@Provider
@Component
public class AuthenticationFilter implements javax.ws.rs.container.ContainerRequestFilter
{
	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationFilter.class);
	
    @Context
    private ResourceInfo resourceInfo;
    
    @Autowired
	IOCLUserDetailsDAO ioclUserDetailsDAO;
     
    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws UnsupportedEncodingException
    {
    	 Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED)
                 .entity("You cannot access this resource").build();
    	 Response ACCESS_FORBIDDEN = Response.status(Response.Status.FORBIDDEN)
                 .entity("Access blocked for all users !!").build();

        Method method = resourceInfo.getResourceMethod();
        LOG.info("Method::::"+method.getName());
        
        if(method.isAnnotationPresent(DenyAll.class))
        {
            requestContext.abortWith(ACCESS_FORBIDDEN);
            return;
        }
        
        if(!method.isAnnotationPresent(PermitAll.class))
        {
            //Get request headers
            final MultivaluedMap<String, String> headers = requestContext.getHeaders();
              
            //Fetch authorization header
            final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);
              
            //If no authorization information present; block access
            if(authorization == null || authorization.isEmpty())
            {
                requestContext.abortWith(ACCESS_DENIED);
                return;
            }
              
            //Get encoded username and password
            final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");
              
            //Decode username and password
            String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));;
  
            //Split username and password tokens
            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();
              
            //Verifying Username and password
            LOG.info(username);
            LOG.info(password);
              
            //Verify user access
            if(method.isAnnotationPresent(RolesAllowed.class))
            {
            	LOG.info("I am inside the Roles allowed");
                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                LOG.debug(":::::::"+rolesAnnotation+"::::::::::"+Arrays.asList(rolesAnnotation.value()));
                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
                if(!(isUserAllowed(username, password, rolesSet)))
	            {
                	LOG.info("Is user allowd");
	                requestContext.abortWith(ACCESS_DENIED);
	                return;
                }
            }
        }
    }
    private boolean isUserAllowed(String username, String password, Set<String> rolesSet) throws UnsupportedEncodingException
    {
        boolean isAllowed = false;
        LOG.info("username::::"+username+"::::password:::::"+password+":::roleset:"+rolesSet);
        //Step 1. Fetch password from database and match with password in argument
        
        // IoclUserDetail ioclUserDetail=ioclUserDetailsDAO.findUserByUserName(username);
        IoclUserDetail ioclUserDetail=checkIfUserExist(username);
        LOG.info("IoclUserDetail:::::::"+ioclUserDetail);
		if(ioclUserDetail==null)
		{
			LOG.info("User Name Not Exist");
			return false;
		}
        
		try
		{
			String ecryptPwd=CommonUtilites.encryption(password);
			LOG.info("ecryptPwd"+ecryptPwd);
			LOG.info("ioclUserDetail.getUserPassword():"+ioclUserDetail.getUserPassword());
			if(!(ioclUserDetail.getUserPassword().equals(ecryptPwd)))
			{
				LOG.info("Passsword not matched");
				return false;
			}
		}
		catch(NoSuchAlgorithmException e)
		{
			LOG.info("Expection:::::"+e);
		}   
        //If both match then get the defined role for user from database.
		List<String> lUserTypes=new ArrayList<String>();
		for(IoclUserroleMapping ioclUserroleMapping:ioclUserDetail.getIoclUserroleMappings())
		{
			LOG.info("::::::::"+ioclUserroleMapping.getUserType());
			lUserTypes.add(ioclUserroleMapping.getUserType());
		}
        
		for(String userRole: lUserTypes)
		{
			LOG.info("User:::"+userRole);
			if(rolesSet.contains(userRole))
	        {
				System.out.println("returning");
	           isAllowed = true;
	           break;
	        }
		}
        return isAllowed;
    }
    
    @Transactional
    public IoclUserDetail checkIfUserExist(String userName)
    {
    	return ioclUserDetailsDAO.findUserByUserName(userName);
    }
}