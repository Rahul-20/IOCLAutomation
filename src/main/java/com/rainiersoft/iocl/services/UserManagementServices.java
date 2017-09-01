package com.rainiersoft.iocl.services;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rainiersoft.iocl.dao.IOCLSupportedUserRoleDAO;
import com.rainiersoft.iocl.dao.IOCLSupportedUserStatusDAO;
import com.rainiersoft.iocl.dao.IOCLUserDetailsDAO;
import com.rainiersoft.iocl.dao.IOCLUserPrivilegesMappingDAO;
import com.rainiersoft.iocl.dao.IOCLUserroleMappingDAO;
import com.rainiersoft.iocl.entity.IoclSupportedUserrole;
import com.rainiersoft.iocl.entity.IoclSupportedUserstatus;
import com.rainiersoft.iocl.entity.IoclUserDetail;
import com.rainiersoft.iocl.entity.IoclUserPrivilegesMapping;
import com.rainiersoft.iocl.entity.IoclUserroleMapping;
import com.rainiersoft.iocl.exception.IOCLWSException;
import com.rainiersoft.iocl.util.ErrorMessageConstants;
import com.rainiersoft.response.dto.CreationAndUpdationResponseBean;
import com.rainiersoft.response.dto.UserDetailsResponseBean;
import com.rainiersoft.response.dto.UserValidationResponse;

@Service
@Singleton
public class UserManagementServices 
{
	private static final Logger LOG = LoggerFactory.getLogger(UserManagementServices.class);

	@Autowired
	IOCLUserDetailsDAO ioclUserDetailsDAO;

	@Autowired
	IOCLUserroleMappingDAO iOCLUserroleMappingDAO;

	@Autowired
	IOCLUserPrivilegesMappingDAO iOCLUserPrivilegesMappingDAO;

	@Autowired
	IOCLSupportedUserRoleDAO iOCLSupportedUserRoleDAO;

	@Autowired
	IOCLSupportedUserStatusDAO iOCLSupportedUserStatusDAO;

	@Value("${UserPasswordExpiryConfigVal}")
	int userPasswordExpiryConfig;


	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=IOCLWSException.class)
	public Response createNewUser(String userName,String userPassword,String userFirstName,String userLastName,String userDOB,String userAadharNum,List<String> userType,String userMobileNum,String userStatus) throws IOCLWSException
	{
		CreationAndUpdationResponseBean creationResponseBean=new CreationAndUpdationResponseBean();
		try
		{
			//While creating new user check if user is already exist in database or not.
			IoclUserDetail ioclUserDetail=ioclUserDetailsDAO.findUserByUserName(userName);
			if(ioclUserDetail!=null)
			{
				throw new IOCLWSException(Response.Status.CONFLICT.getStatusCode(),ErrorMessageConstants.User_Exist_Msg);
			}

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date currentDateobj = new Date();

			//String createdTimeStamp=df.format(dateobj).toString();

			LOG.info("UserPasswordExpiryConfigVal::::::"+userPasswordExpiryConfig);
			Calendar cal = new GregorianCalendar();
			cal.setTime(currentDateobj);
			cal.add(Calendar.YEAR, userPasswordExpiryConfig);
			LOG.info("This is Hours Added Date:"+cal.getTime());
			Date expiryTimeStamp=cal.getTime();


			//Get the UserType and UserStatus Objects

			IoclSupportedUserrole ioclSupportedUserrole=iOCLSupportedUserRoleDAO.findRoleIdByRoleName(userType.get(0));
			IoclSupportedUserstatus ioclSupportedUserstatus=iOCLSupportedUserStatusDAO.findUserStatusIdByUserStatus(userStatus);


			Long userId=ioclUserDetailsDAO.insertUserDetails(userName,userPassword,userFirstName,userLastName,userDOB,userAadharNum,ioclSupportedUserrole,userMobileNum,ioclSupportedUserstatus,currentDateobj,expiryTimeStamp);
			LOG.info("UserID:::::::"+userId);
			creationResponseBean.setUserID(userId);
			creationResponseBean.setAadhaar(userAadharNum);
			creationResponseBean.setDOB(userDOB);
			creationResponseBean.setFirstName(userFirstName);
			creationResponseBean.setLastName(userLastName);
			creationResponseBean.setMobileNo(userMobileNum);
			creationResponseBean.setStatus(userStatus);
			creationResponseBean.setUserName(userName);
			creationResponseBean.setUserType(userType.get(0));
			creationResponseBean.setMessage("User SuccessFully Created : "+ userName);
			creationResponseBean.setSuccessFlag(true);
			return  Response.status(Response.Status.OK).entity(creationResponseBean).build();
		}
		catch(IOCLWSException ioclwsException)
		{
			LOG.info("Create User Service Method IOCL Exception Block:::::::"+ioclwsException);
			throw ioclwsException;
		}
		catch(Exception exception)
		{
			LOG.info("Create User Service Method Exception Block:::::::"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=IOCLWSException.class)
	public Response validateUser(String userName,String userPassword,String userType) throws IOCLWSException, NoSuchAlgorithmException, UnsupportedEncodingException
	{
		try
		{
			UserValidationResponse userValidationResponse=new UserValidationResponse();
			IoclUserDetail ioclUserDetail=ioclUserDetailsDAO.findUserByUserName(userName);
			if(ioclUserDetail==null)
			{
				throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.User_NotFound_Msg);
			}
			else
			{
				List<String> lUserTypes=new ArrayList<String>();
				List<String> lUserPrivileges=new ArrayList<String>();
				for(IoclUserroleMapping ioclUserroleMapping:ioclUserDetail.getIoclUserroleMappings())
				{
					List<IoclUserPrivilegesMapping> lioclUserPrivilegesMapping=iOCLUserPrivilegesMappingDAO.findPrivilegesByRole(ioclUserroleMapping.getIoclSupportedUserrole().getRoleId());
					for(IoclUserPrivilegesMapping ioclUserPrivilegesMapping:lioclUserPrivilegesMapping)
					{
						LOG.info("ioclUserPrivilegesMapping.getPrivilegeNames():::"+ioclUserPrivilegesMapping.getPrivilegeNames());
						lUserPrivileges.add(ioclUserPrivilegesMapping.getPrivilegeNames());		
					}
					lUserTypes.add(ioclUserroleMapping.getIoclSupportedUserrole().getRoleName());
				}
				LOG.info(":::::::::::"+lUserTypes+":::::"+lUserTypes.size()+"userType::::"+userType+":::::::"+lUserPrivileges.size());
				if(userType!=null && userType.length()<0 && !(lUserTypes.contains(userType)))
				{
					throw new IOCLWSException(ErrorMessageConstants.UserType_MissMatch_Code,ErrorMessageConstants.UserType_MissMatch_Msg);
				}

				MessageDigest crypt = MessageDigest.getInstance("SHA-1");
				crypt.reset();
				crypt.update(userPassword.getBytes("UTF-8"));
				userPassword= new BigInteger(1, crypt.digest()).toString(16);
				if(!(ioclUserDetail.getUserPassword().equals(userPassword)))
				{
					throw new IOCLWSException(ErrorMessageConstants.UserPwd_MissMatch_Code,ErrorMessageConstants.UserPwd_MissMatch_Msg);
				}

				DateFormat daf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date dateobj = new Date();

				Calendar currentDate = Calendar.getInstance();
				Calendar expiryDate = Calendar.getInstance();
				currentDate.setTime(dateobj);
				expiryDate.setTime(ioclUserDetail.getPwdExpiryDate());

				if(!(currentDate.before(expiryDate)))
				{
					throw new IOCLWSException(ErrorMessageConstants.UserPwd_Expiry_Code,ErrorMessageConstants.UserPwd_Expiry_Msg);
				}

				if(ioclUserDetail.getIoclSupportedUserstatus().getUserStatus().equalsIgnoreCase("Locked") || ioclUserDetail.getIoclSupportedUserstatus().getUserStatus().equalsIgnoreCase("In Active"))
				{
					throw new IOCLWSException(ErrorMessageConstants.User_Locked_Code,ErrorMessageConstants.User_Locked_Msg);
				}

				userValidationResponse.setSuccessfulFlag(true);
				userValidationResponse.setSuccessfulMsg("SuccessFully Logged In");
				userValidationResponse.setUserPrivilages(lUserPrivileges);
			}
			return  Response.status(Response.Status.OK).entity(userValidationResponse).build();
		}
		catch(IOCLWSException ioclwsException)
		{
			LOG.info("Validate User Service Method IOCL Exception Block:::::::"+ioclwsException);
			throw ioclwsException;
		}
		catch(Exception exception)
		{
			LOG.info("Validate User Service Method Exception Block:::::::"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public Response updateUser(String userName,String userPassword,String userMobileNum,String userStatus) throws IOCLWSException,Exception
	{
		try
		{
			IoclUserDetail ioclUserDetail=ioclUserDetailsDAO.findUserByUserName(userName);

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date updatedTimeStamp = new Date();
			//String updatedTimeStamp=df.format(dateobj).toString();
			CreationAndUpdationResponseBean updationResponseBean=new CreationAndUpdationResponseBean();

			IoclSupportedUserstatus ioclSupportedUserstatus=iOCLSupportedUserStatusDAO.findUserStatusIdByUserStatus(userStatus);
			ioclUserDetailsDAO.updateUserDetails(userName, userPassword, userMobileNum, ioclSupportedUserstatus,updatedTimeStamp,ioclUserDetail);
			updationResponseBean.setUserName(userName);
			updationResponseBean.setMobileNo(userMobileNum);
			updationResponseBean.setMessage("User Updated SuccessFully : "+ userName);
			updationResponseBean.setSuccessFlag(true);
			return  Response.status(Response.Status.OK).entity(updationResponseBean).build();
		}
		catch(Exception exception)
		{
			LOG.info("Update User Service Method Exception Block:::::::"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class) 
	public Response getAvailableUsers() throws IOCLWSException,Exception
	{
		try
		{
			List<IoclUserDetail> ioclUserDetail=ioclUserDetailsDAO.findUsers();
			UserDetailsResponseBean userDetailsResponseBean;
			List<UserDetailsResponseBean> listUserDetailsResponseBean=new ArrayList<UserDetailsResponseBean>();
			if(ioclUserDetail.size()>0)
			{
				for(IoclUserDetail user:ioclUserDetail)
				{
					userDetailsResponseBean=new UserDetailsResponseBean();
					userDetailsResponseBean.setUserName(user.getUserName());
					userDetailsResponseBean.setUserMobileNum(user.getUserMobileNum());
					userDetailsResponseBean.setUserAadharNum(user.getUserAadharNum());
					userDetailsResponseBean.setUserDOB(user.getUserDOB().toString());
					userDetailsResponseBean.setUserFirstName(user.getUserFirstName());
					userDetailsResponseBean.setUserLastName(user.getUserLastName());
					//userDetailsResponseBean.setUserPassword(user.getUserPassword());
					userDetailsResponseBean.setUserID(user.getUserId());
					userDetailsResponseBean.setUserStatus(user.getIoclSupportedUserstatus().getUserStatus());
					List<IoclUserroleMapping> lIoclUserroleMappings=user.getIoclUserroleMappings();
					List<String> userTypes=new ArrayList<String>();
					for(IoclUserroleMapping ioclUserroleMappings:lIoclUserroleMappings)
					{
						userTypes.add(ioclUserroleMappings.getIoclSupportedUserrole().getRoleName());
					}
					userDetailsResponseBean.setUserType(userTypes);
					listUserDetailsResponseBean.add(userDetailsResponseBean);
				}
			}
			return  Response.status(Response.Status.OK).entity(listUserDetailsResponseBean).build();
		}catch(Exception exception)
		{
			LOG.info("GetAvailableUsers Service Method Exception Block:::::::"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,readOnly=false,rollbackFor=IOCLWSException.class)
	public Response deleteUser(int userId) throws  IOCLWSException, Exception
	{
		try
		{
			boolean deleteFalg=ioclUserDetailsDAO.deleteUser(userId);

			if(deleteFalg)
			{
				return  Response.status(Response.Status.OK).entity("Deleted Success Fully").build();	
			}
			else
			{
				return  Response.status(Response.Status.OK).entity("Failed To Delete").build();
			}
		}
		catch(Exception exception)
		{
			LOG.info("DeleteUser Service Method Exception Block:::::::"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}
}