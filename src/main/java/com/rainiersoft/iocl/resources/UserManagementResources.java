package com.rainiersoft.iocl.resources;

import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.internal.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rainiersoft.iocl.exception.IOCLWSException;
import com.rainiersoft.iocl.services.UserManagementServices;
import com.rainiersoft.iocl.util.ErrorMessageConstants;
import com.rainiersoft.request.dto.UserMangRequestBean;

/**
 * This is the class for User management resources.
 * @author RahulKumarPamidi
 */

@Path("/usermanagement")
@Singleton
@Component
public class UserManagementResources
{
	private static final Logger LOG = LoggerFactory.getLogger(UserManagementResources.class);

	@Autowired
	UserManagementServices userManagementServices;

	public UserManagementResources() {}

	@Path("/uservalidation")
	@PermitAll
	@GET
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response userAuthentication(@Context HttpHeaders headers) throws IOCLWSException
	{
		try
		{
			LOG.info("Entered into userAuthentication resource class method........");

			//Get request headers
			final MultivaluedMap<String, String> reqHeaders = headers.getRequestHeaders();

			//Fetch authorization header
			List<String> authorization = reqHeaders.get("Authorization");

			LOG.info("Authorization Header Value......."+authorization);

			if(authorization == null || authorization.isEmpty())
			{
				throw new IOCLWSException(ErrorMessageConstants.UNAuthorized_Code,ErrorMessageConstants.UNAuthorized_Msg);
			}
			
			final String encodedUserPassword = authorization.get(0).replaceFirst("Basic" + " ", "");
			String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));
			final StringTokenizer userNameAndPwdTokenizer = new StringTokenizer(usernameAndPassword, ":");
			final String userName = userNameAndPwdTokenizer.nextToken();
			final String userPwd = userNameAndPwdTokenizer.nextToken();
			String userType = "";
			return userManagementServices.validateUser(userName, userPwd, userType);
		}
		catch(IOCLWSException ioclwsException)
		{
			LOG.info("Logging the occured exception in the resource class userAuthentication method........"+ioclwsException);
			throw ioclwsException;
		}
	}

	@Path("/usercreation")
	@RolesAllowed({"Admin", "Super Admin","Supervisor"})
	@POST
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response createUser(UserMangRequestBean request) throws IOCLWSException
	{
		try
		{
			LOG.info("Entered into createUser resource class method........");
			String userName = request.getUserName();
			String userPassword = request.getUserPassword();
			String userFirstName = request.getUserFirstName();
			String userLastName = request.getUserLastName();
			String userDOB = request.getUserDOB();
			String userAadharNum = request.getUserAadharNum();
			List<String> userType = request.getUserType();
			String userMobileNum = request.getUserMobileNum();
			String userStatus = request.getUserStatus();
			LOG.info("Request Object For User Creation........"+request);
			return userManagementServices.createNewUser(userName, userPassword, userFirstName, userLastName, userDOB, userAadharNum, userType, userMobileNum, userStatus);
		}
		catch(IOCLWSException ioclwsException)
		{
			LOG.info("Logging the occured exception in the resouce class createUser method........"+ioclwsException);
			throw ioclwsException;
		}
	}

	@Path("/updateuser")
	@RolesAllowed({"Admin", "Super Admin","Supervisor"})
	@PUT
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response updateUser(UserMangRequestBean request) throws IOCLWSException
	{
		try
		{
			LOG.info("Entered into updateUser resource class method........");
			String userName = request.getUserName();
			String userPassword = request.getUserPassword();
			String userMobileNum = request.getUserMobileNum();
			String userStatus = request.getUserStatus();
			String userFirstName = request.getUserFirstName();
			String userLastName = request.getUserLastName();
			String userDOB = request.getUserDOB();
			String userAadharNum = request.getUserAadharNum();
			List<String> userType = request.getUserType();
			boolean editUserNameFlag=request.getEditUserNameFlag();
			int userId=request.getUserId();
			LOG.info("Request Object For Update Creation........"+request);
			return userManagementServices.updateUser(userId,userName, userPassword, userMobileNum, userStatus,editUserNameFlag,userFirstName,userLastName,userDOB,userAadharNum,userType);
		}
		catch(IOCLWSException ioclwsException)
		{
			LOG.info("Logging the occured exception in the resouce class updateUser method........"+ioclwsException);
			throw ioclwsException;
		}
	}

	@Path("/getUsers")
	@RolesAllowed({"Admin", "Super Admin","Supervisor"})
	@GET
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response getUsers(@QueryParam("UserRole") String userRole) throws IOCLWSException
	{
		try
		{
			LOG.info("Entered into getUsers resource class method........");
			return userManagementServices.getAvailableUsers(userRole);
		}
		catch(IOCLWSException ioclwsException)
		{
			LOG.info("Logging the occured exception in the resouce class getUsers method........"+ioclwsException);
			throw ioclwsException;
		}
	}

	@Path("/deleteUser")
	@RolesAllowed({"Admin", "Super Admin","Supervisor"})
	@DELETE
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response deleteUser(@QueryParam("UserID") int userID) throws IOCLWSException
	{
		try
		{
			LOG.info("Entered into deleteUser resource class method........");
			LOG.info("Query parameter for the method deleteUser......"+userID);
			return userManagementServices.deleteUser(userID);
		}
		catch(IOCLWSException ioclwsException)
		{
			LOG.info("Logging the occured exception in the resouce class deleteUser method........"+ioclwsException);
			throw ioclwsException;
		}
	}

	@Path("/supportedUserTypes")
	@RolesAllowed({"Admin", "Super Admin","Supervisor"})
	@GET
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response supportedUserTypes() throws IOCLWSException
	{
		try
		{
			LOG.info("Entered into supportedUserTypes resource class method........");
			return userManagementServices.supportedUserTypes();
		}
		catch(IOCLWSException ioclwsException)
		{
			LOG.info("Logging the occured exception in the resouce class supportedUserTypes method........"+ioclwsException);
			throw ioclwsException;
		}
	}

	@Path("/supportedUserStatus")
	@RolesAllowed({"Admin", "Super Admin","Supervisor"})
	@GET
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response supportedUserStatus() throws IOCLWSException
	{
		try
		{
			LOG.info("Entered into supportedUserStatus resource class method........");
			return userManagementServices.supportedUserStatus();
		}
		catch(IOCLWSException ioclwsException)
		{
			LOG.info("Logging the occured exception in the resouce class supportedUserStatus method........"+ioclwsException);
			throw ioclwsException;
		}
	}

	@Path("/getStaticUserData")
	@RolesAllowed({"Admin", "Super Admin","Supervisor"})
	@GET
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response getData(@QueryParam("UserRole") String userRole) throws IOCLWSException
	{
		try
		{
			LOG.info("Entered into getData resource class method........");
			return userManagementServices.getData(userRole);
		}
		catch(IOCLWSException ioclwsException)
		{
			LOG.info("Logging the occured exception in the resouce class getData method........"+ioclwsException);
			throw ioclwsException;
		}
	}
}