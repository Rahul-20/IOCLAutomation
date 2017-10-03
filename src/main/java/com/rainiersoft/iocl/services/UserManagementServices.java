package com.rainiersoft.iocl.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
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
import com.rainiersoft.iocl.util.CommonUtilites;
import com.rainiersoft.iocl.util.ErrorMessageConstants;
import com.rainiersoft.response.dto.CreationAndUpdationResponseBean;
import com.rainiersoft.response.dto.GetUserStaticDataResponseBean;
import com.rainiersoft.response.dto.UserDeletionResponse;
import com.rainiersoft.response.dto.UserDetailsResponseBean;
import com.rainiersoft.response.dto.UserValidationResponse;


/**
 * This is the class for user management services
 * @author RahulKumarPamidi
 */

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

	@Autowired
	IOCLSupportedUserRoleDAO ioclSupportedUserRoleDAO;

	@Resource
	Properties appProps;

	@Value("${UserPasswordExpiryConfigVal}")
	int userPasswordExpiryConfig;


	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=IOCLWSException.class)
	public Response createNewUser(String userName,String userPassword,String userFirstName,String userLastName,String userDOB,String userAadharNum,List<String> userType,String userMobileNum,String userStatus) throws IOCLWSException
	{
		LOG.info("Entered into createNewUser service class method........");
		CreationAndUpdationResponseBean creationResponseBean=new CreationAndUpdationResponseBean();
		try
		{
			IoclUserDetail ioclUserDetail=ioclUserDetailsDAO.findUserByUserName(userName);
			LOG.info("Got the IoclUserDetail object......"+ioclUserDetail);
			if(ioclUserDetail!=null)
			{
				throw new IOCLWSException(Response.Status.CONFLICT.getStatusCode(),ErrorMessageConstants.User_Exist_Msg);
			}

			DateFormat df = new SimpleDateFormat(appProps.getProperty("AppDateFormat"));
			Date currentDateobj = new Date();
			LOG.info("UserPasswordExpiryConfigVal::::::"+userPasswordExpiryConfig);
			Calendar cal = new GregorianCalendar();
			cal.setTime(currentDateobj);
			cal.add(Calendar.DATE,userPasswordExpiryConfig);
			LOG.info("Password expiry date for user:"+cal.getTime());
			Date expiryTimeStamp=cal.getTime();

			IoclSupportedUserrole ioclSupportedUserrole=iOCLSupportedUserRoleDAO.findRoleIdByRoleName(userType.get(0));
			IoclSupportedUserstatus ioclSupportedUserstatus=iOCLSupportedUserStatusDAO.findUserStatusIdByUserStatus(userStatus);
			LOG.info("Got the IoclSupportedUserrole object......"+ioclSupportedUserrole);
			LOG.info("Got the IoclSupportedUserstatus object......"+ioclSupportedUserstatus);
			Long userId=ioclUserDetailsDAO.insertUserDetails(userName,userPassword,userFirstName,userLastName,userDOB,userAadharNum,ioclSupportedUserrole,userMobileNum,ioclSupportedUserstatus,currentDateobj,expiryTimeStamp);
			LOG.info("Created record id in user table:::::::"+userId);
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
			LOG.info("Creation User Response Object::::::"+creationResponseBean);
			return  Response.status(Response.Status.OK).entity(creationResponseBean).build();
		}
		catch(IOCLWSException ioclwsException)
		{
			LOG.info("Logging the occured exception in the service class createNewUser method custom catch block........"+ioclwsException);
			throw ioclwsException;
		}
		catch(Exception exception)
		{
			LOG.info("Logging the occured exception in the service class createNewUser method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=IOCLWSException.class)
	public Response validateUser(String userName,String userPassword,String userType) throws IOCLWSException
	{
		try
		{
			LOG.info("Entered into validateUser service class method........");
			UserValidationResponse userValidationResponse=new UserValidationResponse();
			IoclUserDetail ioclUserDetail=ioclUserDetailsDAO.findUserByUserName(userName);
			LOG.info("Got the IoclUserDetail object......"+ioclUserDetail);
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
					LOG.info("Got the lioclUserPrivilegesMapping object......"+lioclUserPrivilegesMapping);
					for(IoclUserPrivilegesMapping ioclUserPrivilegesMapping:lioclUserPrivilegesMapping)
					{
						lUserPrivileges.add(ioclUserPrivilegesMapping.getPrivilegeNames());		
					}
					lUserTypes.add(ioclUserroleMapping.getIoclSupportedUserrole().getRoleName());
				}
				LOG.info("lUserTypes:userType......"+lUserTypes+":"+userType);
				if(userType!=null && userType.length()<0 && !(lUserTypes.contains(userType)))
				{
					throw new IOCLWSException(ErrorMessageConstants.UserType_MissMatch_Code,ErrorMessageConstants.UserType_MissMatch_Msg);
				}

				userPassword=CommonUtilites.encryption(userPassword);
				if(!(ioclUserDetail.getUserPassword().equals(userPassword)))
				{
					throw new IOCLWSException(ErrorMessageConstants.UserPwd_MissMatch_Code,ErrorMessageConstants.UserPwd_MissMatch_Msg);
				}

				DateFormat daf = new SimpleDateFormat(appProps.getProperty("AppDateFormat"));
				Date dateobj = new Date();

				Calendar currentDate = Calendar.getInstance();
				Calendar expiryDate = Calendar.getInstance();
				currentDate.setTime(dateobj);
				expiryDate.setTime(ioclUserDetail.getPwdExpiryDate());

				if(!(currentDate.before(expiryDate)))
				{
					throw new IOCLWSException(ErrorMessageConstants.UserPwd_Expiry_Code,ErrorMessageConstants.UserPwd_Expiry_Msg);
				}

				if(ioclUserDetail.getIoclSupportedUserstatus().getUserStatus().equalsIgnoreCase(appProps.getProperty("UserLockedStatus")) || ioclUserDetail.getIoclSupportedUserstatus().getUserStatus().equalsIgnoreCase(appProps.getProperty("UserInActiveStatus")))
				{
					throw new IOCLWSException(ErrorMessageConstants.User_Locked_Code,ErrorMessageConstants.User_Locked_Msg);
				}

				userValidationResponse.setSuccessfulFlag(true);
				userValidationResponse.setSuccessfulMsg("SuccessFully Logged In");
				userValidationResponse.setUserPrivilages(lUserPrivileges);
				userValidationResponse.setUserRole(ioclUserDetail.getIoclUserroleMappings().get(0).getIoclSupportedUserrole().getRoleName());
				LOG.info("Validation User Response Object::::::"+userValidationResponse);
			}
			return  Response.status(Response.Status.OK).entity(userValidationResponse).build();
		}
		catch(IOCLWSException ioclwsException)
		{
			LOG.info("Logging the occured exception in the service class validateUser method custom catch block........"+ioclwsException);
			throw ioclwsException;
		}
		catch(Exception exception)
		{
			LOG.info("Logging the occured exception in the service class validateUser method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=IOCLWSException.class)
	public Response updateUser(int userId,String userName,String userPassword,String userMobileNum,String userStatus,boolean editUserNameFlag,String userFirstName,String userLastName,String userDOB,String userAadharNum,List<String> userType) throws IOCLWSException
	{
		try
		{
			LOG.info("Entered into updateUser service class method........");
			DateFormat df = new SimpleDateFormat(appProps.getProperty("AppDateFormat"));
			Date updatedTimeStamp = new Date();
			CreationAndUpdationResponseBean updationResponseBean=new CreationAndUpdationResponseBean();
			if(editUserNameFlag)
			{
				IoclUserDetail ioclUserDetail=ioclUserDetailsDAO.findUserByUserName(userName);
				LOG.info("Inside if got the ioclUserDetail object......"+ioclUserDetail);
				if(ioclUserDetail!=null)
				{
					throw new IOCLWSException(Response.Status.CONFLICT.getStatusCode(),ErrorMessageConstants.UserName_Exist_Msg);
				}
			}
			IoclUserDetail ioclUserDetail=ioclUserDetailsDAO.findUserByUserId(userId);
			LOG.info("Got the ioclUserDetail object......"+ioclUserDetail);
			IoclSupportedUserstatus ioclSupportedUserstatus=iOCLSupportedUserStatusDAO.findUserStatusIdByUserStatus(userStatus);
			LOG.info("Got the ioclSupportedUserstatus object......"+ioclSupportedUserstatus);
			IoclSupportedUserrole ioclSupportedUserrole=iOCLSupportedUserRoleDAO.findRoleIdByRoleName(userType.get(0));
			LOG.info("Got the ioclSupportedUserrole object......"+ioclSupportedUserrole);
			ioclUserDetailsDAO.updateUserDetails(userName, userPassword, userMobileNum, ioclSupportedUserstatus,updatedTimeStamp,ioclUserDetail,userFirstName,userLastName,userDOB,userAadharNum,ioclSupportedUserrole);
			updationResponseBean.setUserName(userName);
			updationResponseBean.setMobileNo(userMobileNum);
			updationResponseBean.setMessage("User Updated SuccessFully : "+ userName);
			updationResponseBean.setStatus(ioclSupportedUserstatus.getUserStatus());
			updationResponseBean.setDOB(ioclUserDetail.getUserDOB().toString());
			updationResponseBean.setAadhaar(ioclUserDetail.getUserAadharNum());
			updationResponseBean.setUserType(ioclUserDetail.getIoclUserroleMappings().get(0).getIoclSupportedUserrole().getRoleName());
			updationResponseBean.setFirstName(ioclUserDetail.getUserFirstName());
			updationResponseBean.setLastName(ioclUserDetail.getUserLastName());
			updationResponseBean.setUserID((long)ioclUserDetail.getUserId());
			updationResponseBean.setSuccessFlag(true);
			LOG.info("Update User Response Object::::::"+updationResponseBean);
			return  Response.status(Response.Status.OK).entity(updationResponseBean).build();
		}
		catch(IOCLWSException ioclwsException)
		{
			LOG.info("Logging the occured exception in the service class updateUser method custom catch block........"+ioclwsException);
			throw ioclwsException;
		}
		catch(Exception exception)
		{
			LOG.info("Logging the occured exception in the service class updateUser method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=IOCLWSException.class) 
	public Response getAvailableUsers(String userRole) throws IOCLWSException
	{
		try
		{
			LOG.info("Entered into getAvailableUsers service class method........");
			List<IoclUserDetail> ioclUserDetail=ioclUserDetailsDAO.findUsers();
			LOG.info("Got the ioclUserDetail object......"+ioclUserDetail);
			UserDetailsResponseBean userDetailsResponseBean;
			List<UserDetailsResponseBean> listUserDetailsResponseBean=new ArrayList<UserDetailsResponseBean>();

			IoclSupportedUserrole ioclSupportedUserrole=iOCLSupportedUserRoleDAO.findRoleIdByRoleName(userRole);
			String childRoles=ioclSupportedUserrole.getChildRoles();
			LOG.info("ChildRoles....."+childRoles);
			ArrayList<String> listOfRoleIDs=null;
			if(null!=childRoles && childRoles.length()>0)
			{
				listOfRoleIDs=new ArrayList<String>(Arrays.asList(childRoles.split(",")));
			}

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

						if(null!=listOfRoleIDs && listOfRoleIDs.size()>0)
						{
							LOG.info("listOfRoleIDs......"+listOfRoleIDs+".....RoleId...."+ioclUserroleMappings.getIoclSupportedUserrole().getRoleId()+"listOfRoleIDs.contains(ioclUserroleMappings.getIoclSupportedUserrole().getRoleId()"+listOfRoleIDs.contains(ioclUserroleMappings.getIoclSupportedUserrole().getRoleId()));
							if(listOfRoleIDs.contains((String.valueOf(ioclUserroleMappings.getIoclSupportedUserrole().getRoleId()))))
							{
								LOG.info("Entered into edit true");
								userDetailsResponseBean.setUserEditFlag(true);
							}
							else
							{
								LOG.info("Entered into edit false");
								userDetailsResponseBean.setUserEditFlag(false);
							}
						}
					}
					userDetailsResponseBean.setUserType(userTypes);
					listUserDetailsResponseBean.add(userDetailsResponseBean);
				}
			}
			LOG.info("getAvailableUsers Response Object::::::"+listUserDetailsResponseBean);
			return  Response.status(Response.Status.OK).entity(listUserDetailsResponseBean).build();
		}catch(Exception exception)
		{
			LOG.info("Logging the occured exception in the service class getAvailableUsers method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,readOnly=false,rollbackFor=IOCLWSException.class)
	public Response deleteUser(int userId) throws  IOCLWSException
	{
		try
		{
			LOG.info("Entered into deleteUser service class method........");
			UserDeletionResponse userDeletionResponse=new UserDeletionResponse();
			boolean deleteFalg=ioclUserDetailsDAO.deleteUser(userId);

			if(deleteFalg)
			{
				userDeletionResponse.setSuccessFlag(true);
				userDeletionResponse.setSuccessMsg("User Deleted Success Fully : "+ userId);
				LOG.info("deleteUser response object......"+userDeletionResponse);
				return  Response.status(Response.Status.OK).entity(userDeletionResponse).build();	
			}
			else
			{
				userDeletionResponse.setSuccessFlag(true);
				userDeletionResponse.setSuccessMsg("Failed to deleted User : "+ userId);
				LOG.info("deleteUser response object......"+userDeletionResponse);
				return  Response.status(Response.Status.OK).entity(userDeletionResponse).build();
			}
		}
		catch(Exception exception)
		{
			LOG.info("Logging the occured exception in the service class deleteUser method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,readOnly=false,rollbackFor=IOCLWSException.class)
	public Response supportedUserTypes() throws  IOCLWSException
	{
		try
		{
			LOG.info("Entered into supportedUserTypes service class method........");
			List<IoclSupportedUserrole> lIoclSupportedUserroles=ioclSupportedUserRoleDAO.findAll(IoclSupportedUserrole.class);
			List<String> userTypes=new ArrayList<String>();
			for(IoclSupportedUserrole ioclSupportedUserrole:lIoclSupportedUserroles)
			{
				userTypes.add(ioclSupportedUserrole.getRoleName());
			}
			LOG.info("supportedUserTypes response object......"+userTypes);
			return  Response.status(Response.Status.OK).entity(userTypes).build();
		}
		catch(Exception exception)
		{
			LOG.info("Logging the occured exception in the service class supportedUserTypes method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,readOnly=false,rollbackFor=IOCLWSException.class)
	public Response supportedUserStatus() throws  IOCLWSException
	{
		try
		{
			LOG.info("Entered into supportedUserStatus service class method........");
			List<IoclSupportedUserstatus> lIoclSupportedUserstatus=iOCLSupportedUserStatusDAO.findAll(IoclSupportedUserstatus.class);
			List<String> userStatus=new ArrayList<String>();
			for(IoclSupportedUserstatus ioclSupportedUserstatus:lIoclSupportedUserstatus)
			{
				userStatus.add(ioclSupportedUserstatus.getUserStatus());
			}
			LOG.info("supportedUserTypes response object......"+userStatus);
			return  Response.status(Response.Status.OK).entity(userStatus).build();
		}
		catch(Exception exception)
		{
			LOG.info("Logging the occured exception in the service class supportedUserStatus method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,readOnly=false,rollbackFor=IOCLWSException.class)
	public Response getData(String userRole) throws  IOCLWSException
	{
		LOG.info("Entered into getData service class method........");
		GetUserStaticDataResponseBean getUserStaticDataResponseBean=new GetUserStaticDataResponseBean();
		try
		{
			Map<String,List<String>> data=new HashMap<String,List<String>>();
			List<IoclSupportedUserstatus> lIoclSupportedUserstatus=iOCLSupportedUserStatusDAO.findAll(IoclSupportedUserstatus.class);
			IoclSupportedUserrole ioclSupportedUseRoleHierarchy=iOCLSupportedUserRoleDAO.findRoleIdByRoleName(userRole);
			ArrayList<String> listOfRoleIDs=null;
			String childRoles=ioclSupportedUseRoleHierarchy.getChildRoles();
			if(null!=childRoles && childRoles.length()>0)
			{
				listOfRoleIDs=new ArrayList<String>(Arrays.asList(childRoles.split(",")));
			}
			List<String> userStatus=new ArrayList<String>();
			List<String> userTypes=new ArrayList<String>();
			for(IoclSupportedUserstatus ioclSupportedUserstatus:lIoclSupportedUserstatus)
			{
				userStatus.add(ioclSupportedUserstatus.getUserStatus());
			}
			List<IoclSupportedUserrole> lIoclSupportedUserroles=ioclSupportedUserRoleDAO.findAll(IoclSupportedUserrole.class);

			for(IoclSupportedUserrole ioclSupportedUserrole:lIoclSupportedUserroles)
			{
				if(listOfRoleIDs.contains((String.valueOf(ioclSupportedUserrole.getRoleId()))))
				{
					userTypes.add(ioclSupportedUserrole.getRoleName());
				}
			}
			data.put("UserStatus",userStatus);
			data.put("UserTypes",userTypes);
			getUserStaticDataResponseBean.setData(data);
			LOG.info("getData response object......"+userStatus);
			return  Response.status(Response.Status.OK).entity(getUserStaticDataResponseBean).build();
		}
		catch(Exception exception)
		{
			LOG.info("Logging the occured exception in the service class getData method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}
}