package com.rainiersoft.iocl.dao;

import java.util.Date;
import java.util.List;

import com.rainiersoft.iocl.entity.IoclSupportedUserrole;
import com.rainiersoft.iocl.entity.IoclSupportedUserstatus;
import com.rainiersoft.iocl.entity.IoclUserDetail;

public interface IOCLUserDetailsDAO extends GenericDAO<IoclUserDetail, Long>
{
	public Long insertUserDetails(String userName, String userPassword, String userFirstName, String userLastName, String userDOB, String userAadharNum, IoclSupportedUserrole userType, String userMobileNum, IoclSupportedUserstatus userStatus, Date createdTimeStamp, Date expiryTimeStamp,int userID);

	public IoclUserDetail findUserByUserName(String userName);

	public void updateUserDetails(String userName, String userPassword, String userMobileNum, IoclSupportedUserstatus userStatus, Date updatedTimeStamp, IoclUserDetail ioclUserDetail,String userFirstName,String userLastName,String userDOB,String userAadharNum,IoclSupportedUserrole userType,int userID);

	public List<IoclUserDetail> findUsers();
	
	public boolean deleteUser(int userId);
	
	public IoclUserDetail findUserByUserId(int userId);
}
