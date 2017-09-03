package com.rainiersoft.iocl.resources;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

import org.glassfish.jersey.internal.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rainiersoft.iocl.exception.IOCLWSException;
import com.rainiersoft.iocl.services.UserManagementServices;
import com.rainiersoft.request.dto.RequestBean;

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
	public Response userAuthentication(@Context HttpHeaders headers) throws IOCLWSException, NoSuchAlgorithmException, UnsupportedEncodingException
	{
		LOG.info("Inside Resources Class Login Method");
		//Get request headers
		final MultivaluedMap<String, String> reqHeaders = headers.getRequestHeaders();

		//Fetch authorization header
		final List<String> authorization = reqHeaders.get("Authorization");

		//Get encoded username and password
		final String encodedUserPassword = authorization.get(0).replaceFirst("Basic" + " ", "");

		//Decode username and password
		String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));;

		//Split username and password tokens
		final StringTokenizer userNameAndPwdTokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String userName = userNameAndPwdTokenizer.nextToken();
		final String userPwd = userNameAndPwdTokenizer.nextToken();
		String userType = "";

		LOG.info("userRole:::::" + userType);
		return userManagementServices.validateUser(userName, userPwd, userType);
	}

	@Path("/usercreation")
	@RolesAllowed({"Admin", "Super Admin"})
	@POST
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response createUser(RequestBean request) throws IOCLWSException, ParseException
	{
		LOG.info("Inside Resource Class Create User Method");
		String userName = request.getUserName();
		String userPassword = request.getUserPassword();
		String userFirstName = request.getUserFirstName();
		String userLastName = request.getUserLastName();
		String userDOB = request.getUserDOB();
		String userAadharNum = request.getUserAadharNum();
		List<String> userType = request.getUserType();
		LOG.info("::::" + userType.size());
		String userMobileNum = request.getUserMobileNum();
		String userStatus = request.getUserStatus();
		return userManagementServices.createNewUser(userName, userPassword, userFirstName, userLastName, userDOB, userAadharNum, userType, userMobileNum, userStatus);
	}

	@Path("/updateuser")
	@RolesAllowed({"Admin", "Super Admin"})
	@POST
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response updateUser(RequestBean request) throws IOCLWSException, Exception
	{
		LOG.info("Inside Resource Class Update User Method");
		String userName = request.getUserName();
		String userPassword = request.getUserPassword();
		String userMobileNum = request.getUserMobileNum();
		String userStatus = request.getUserStatus();
		return userManagementServices.updateUser(userName, userPassword, userMobileNum, userStatus);
	}

	@Path("/getUsers")
	@RolesAllowed({"Admin", "Super Admin"})
	@GET
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response getUsers() throws IOCLWSException, Exception
	{
		LOG.info("Inside Resource Class GET User Method");
		return userManagementServices.getAvailableUsers();
	}

	@Path("/deleteUser")
	@RolesAllowed({"Admin", "Super Admin"})
	@DELETE
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response deleteUser(@QueryParam("UserID") int userID) throws IOCLWSException,Exception
	{
		return userManagementServices.deleteUser(userID);
	}

	@Path("/supportedUserTypes")
	@RolesAllowed({"Admin", "Super Admin"})
	@DELETE
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response supportedUserTypes() throws IOCLWSException,Exception
	{
		return userManagementServices.supportedUserTypes();
	}

	@Path("/supportedUserStatus")
	@RolesAllowed({"Admin", "Super Admin"})
	@DELETE
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response supportedUserStatus() throws IOCLWSException,Exception
	{
		return userManagementServices.supportedUserStatus();
	}
}
