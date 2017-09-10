package com.rainiersoft.iocl.security;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
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
import com.rainiersoft.iocl.entity.IoclUserDetail;
import com.rainiersoft.iocl.entity.IoclUserroleMapping;
import com.rainiersoft.iocl.util.CommonUtilites;

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

	public void filter(ContainerRequestContext requestContext) throws UnsupportedEncodingException
	{
		/*String lic="80-86-F2-6D-F1-FA";

    	String s[]=getIPAndMAC();

    	if(!(s[0].equals(lic)))
    	{
    		Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Licence Expiredd").build();
    		requestContext.abortWith(ACCESS_DENIED);
    		return;
    	}*/

		Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED).entity("You cannot access this resource").build();
		Response ACCESS_FORBIDDEN = Response.status(Response.Status.FORBIDDEN).entity("Access blocked for all users !!").build();

		if (requestContext.getMethod().equalsIgnoreCase("OPTIONS")) {
			System.out.println("Entered::::::::"+requestContext.getMethod());
			requestContext.abortWith(Response.ok().build());
			return;
		}

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
			LOG.info("::::::::::"+headers.get(AUTHORIZATION_PROPERTY));
			LOG.info("::::::::::"+requestContext.getHeaderString(AUTHENTICATION_SCHEME));
			LOG.info("::::::::::"+requestContext.getMethod());
			LOG.info("::::::::::"+requestContext.getUriInfo());
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

			//Verify user access
			if(method.isAnnotationPresent(RolesAllowed.class))
			{
				RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
				//LOG.debug(":::::::"+rolesAnnotation+"::::::::::"+Arrays.asList(rolesAnnotation.value()));
				//                ArrayList<String> roleslist = (ArrayList<String>) Arrays.asList(rolesAnnotation.value());

				Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));

				if(!(isUserAllowed(username, password, rolesSet)))
				{
					LOG.info("Is user allowd");
					requestContext.abortWith(ACCESS_DENIED);
					return;
				}
			}
		}
		//}
	}

	private boolean isUserAllowed(String username, String password, Set<String> rolesSet) throws UnsupportedEncodingException
	{
		boolean isAllowed = false;
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
			LOG.info("::::::::"+ioclUserroleMapping.getIoclSupportedUserrole().getRoleName());
			lUserTypes.add(ioclUserroleMapping.getIoclSupportedUserrole().getRoleName());
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

	public String[] getIPAndMAC()
	{
		InetAddress ip;
		String[]  arr=new String[2];
		try
		{
			ip = InetAddress.getLocalHost();
			arr[1]=ip.getHostAddress();

			NetworkInterface network = NetworkInterface.getByInetAddress(ip);

			byte[] mac = network.getHardwareAddress();

			System.out.print("Current MAC address : ");

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
			}
			arr[0]=sb.toString();

		} catch (UnknownHostException e) {

			e.printStackTrace();

		} catch (SocketException e){

			e.printStackTrace();

		}
		return arr;
	}
}