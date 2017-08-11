package com.rainiersoft.iocl.services;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rainiersoft.iocl.dao.IOCLUserDetailsDAO;
import com.rainiersoft.iocl.dao.IOCLUserPrivilegesMappingDAO;
import com.rainiersoft.iocl.dao.IOCLUserroleMappingDAO;
import com.rainiersoft.iocl.entity.IoclUserDetail;
import com.rainiersoft.iocl.entity.IoclUserPrivilegesMapping;
import com.rainiersoft.iocl.entity.IoclUserroleMapping;
import com.rainiersoft.iocl.exception.IOCLWSException;
import com.rainiersoft.iocl.model.CreationAndUpdationResponseBean;
import com.rainiersoft.iocl.model.UserDetailsResponseBean;
import com.rainiersoft.iocl.model.UserValidationResponse;
import com.rainiersoft.iocl.util.ErrorMessageConstants;

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

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public Response createNewUser(String userName,String userPassword,String userFirstName,String userLastName,String userDOB,String userAadharNum,List<String> userType,String userMobileNum,String userStatus) throws IOCLWSException
	{

		//While creating new user check if user is already exist in database or not.

		IoclUserDetail ioclUserDetail=ioclUserDetailsDAO.findUserByUserName(userName);
		if(ioclUserDetail!=null)
		{
			throw new IOCLWSException(Response.Status.CONFLICT.getStatusCode(),ErrorMessageConstants.User_Exist_Msg);
		}

		CreationAndUpdationResponseBean creationResponseBean=new CreationAndUpdationResponseBean();
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		String createdTimeStamp=df.format(dateobj).toString();

		Calendar cal = new GregorianCalendar();
		cal.setTime(dateobj);
		cal.add(Calendar.YEAR, 1);
		System.out.println("This is Hours Added Date:"+cal.getTime());
		String expiryTimeStamp=df.format(cal.getTime()).toString();

		ioclUserDetailsDAO.insertUserDetails(userName,userPassword,userFirstName,userLastName,userDOB,userAadharNum,userType,userMobileNum,userStatus,createdTimeStamp,expiryTimeStamp);
		creationResponseBean.setMessage("User SuccessFully Created : "+ userName);
		creationResponseBean.setSuccessFlag(true);
		return  Response.status(Response.Status.OK).entity(creationResponseBean).build();
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public Response validateUser(String userName,String userPassword,String userType) throws IOCLWSException, NoSuchAlgorithmException, UnsupportedEncodingException
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
				List<IoclUserPrivilegesMapping> lioclUserPrivilegesMapping=iOCLUserPrivilegesMappingDAO.findPrivilegesByRole(ioclUserroleMapping.getUserType());
				for(IoclUserPrivilegesMapping ioclUserPrivilegesMapping:lioclUserPrivilegesMapping)
				{
					System.out.println("ioclUserPrivilegesMapping.getPrivilegeNames():::"+ioclUserPrivilegesMapping.getPrivilegeNames());
					lUserPrivileges.add(ioclUserPrivilegesMapping.getPrivilegeNames());		
				}
				lUserTypes.add(ioclUserroleMapping.getUserType());
			}
			LOG.info(":::::::::::"+lUserTypes+":::::"+lUserTypes.size()+"userType::::"+userType+":::::::"+lUserPrivileges.size());
			if(!(lUserTypes.contains(userType)))
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

			DateFormat daf = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
			Date dateobj = new Date();

			Calendar currentDate = Calendar.getInstance();
			Calendar expiryDate = Calendar.getInstance();
			currentDate.setTime(dateobj);
			expiryDate.setTime(ioclUserDetail.getPwdExpiryDate());

			if(!(currentDate.before(expiryDate)))
			{
				throw new IOCLWSException(ErrorMessageConstants.UserPwd_Expiry_Code,ErrorMessageConstants.UserPwd_Expiry_Msg);
			}

			if(ioclUserDetail.getUserStatus().equalsIgnoreCase("Locked") || ioclUserDetail.getUserStatus().equalsIgnoreCase("In Active"))
			{
				throw new IOCLWSException(ErrorMessageConstants.User_Locked_Code,ErrorMessageConstants.User_Locked_Msg);
			}

			userValidationResponse.setSuccessfulFlag(true);
			userValidationResponse.setSuccessfulMsg("SuccessFully Logged In");
			userValidationResponse.setUserPrivilages(lUserPrivileges);
		}
		return  Response.status(Response.Status.OK).entity(userValidationResponse).build();	
	}


	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public Response updateUser(String userName,String userPassword,String userMobileNum,String userStatus) throws IOCLWSException
	{
		IoclUserDetail ioclUserDetail=ioclUserDetailsDAO.findUserByUserName(userName);

		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		String updatedTimeStamp=df.format(dateobj).toString();
		CreationAndUpdationResponseBean updationResponseBean=new CreationAndUpdationResponseBean();
		ioclUserDetailsDAO.updateUserDetails(userName, userPassword, userMobileNum, userStatus,updatedTimeStamp,ioclUserDetail);
		updationResponseBean.setMessage("User Updated SuccessFully : "+ userName);
		updationResponseBean.setSuccessFlag(true);
		return  Response.status(Response.Status.OK).entity(updationResponseBean).build();	
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public Response getAvailableUsers()
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
				userDetailsResponseBean.setUserDOB(user.getUserDOB());
				userDetailsResponseBean.setUserFirstName(user.getUserFirstName());
				userDetailsResponseBean.setUserLastName(user.getUserLastName());
				userDetailsResponseBean.setUserPassword(user.getUserPassword());
				userDetailsResponseBean.setUserStatus(user.getUserStatus());
				List<IoclUserroleMapping> lIoclUserroleMappings=user.getIoclUserroleMappings();
				List<String> userTypes=new ArrayList<String>();
				for(IoclUserroleMapping ioclUserroleMappings:lIoclUserroleMappings)
				{
					userTypes.add(ioclUserroleMappings.getUserType());
				}
				userDetailsResponseBean.setUserType(userTypes);
				listUserDetailsResponseBean.add(userDetailsResponseBean);
			}
		}
		return  Response.status(Response.Status.OK).entity(listUserDetailsResponseBean).build();	
	}
}