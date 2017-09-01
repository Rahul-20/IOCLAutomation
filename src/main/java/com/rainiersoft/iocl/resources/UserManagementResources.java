package com.rainiersoft.iocl.resources;

import com.rainiersoft.iocl.exception.IOCLWSException;
import com.rainiersoft.iocl.services.UserManagementServices;
import com.rainiersoft.request.dto.RequestBean;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;
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
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	public Response userAuthentication(@QueryParam("q") String enodedString, @QueryParam("type") String role) throws IOCLWSException, NoSuchAlgorithmException, UnsupportedEncodingException
	{
		LOG.info("Inside Resources Class Login Method");
		byte[] decodedBytes = DatatypeConverter.parseBase64Binary(enodedString);
		String[] s = new String(decodedBytes).split(":", 2);
		String userName = s[0];
		String userPwd = s[1];
		String userType = role;
		System.out.println("userRole:::::" + userType);
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
}
