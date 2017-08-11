package com.rainiersoft.iocl.dao;

import java.util.List;

import com.rainiersoft.iocl.entity.IoclUserDetail;

public interface IOCLUserDetailsDAO extends GenericDAO<IoclUserDetail,Long>
{
	public void insertUserDetails(String userName,String userPassword,String userFirstName,String userLastName,String userDOB,String userAadharNum,List<String> userType,String userMobileNum,String userStatus,String createdTimeStamp,String expiryTimeStamp);
	public IoclUserDetail findUserByUserName(String userName);
	public void updateUserDetails(String userName,String userPassword,String userMobileNum,String userStatus,String updatedTimeStamp,IoclUserDetail ioclUserDetail);
	public List<IoclUserDetail> findUsers();
}
