package com.rainiersoft.iocl.dao.impl;

import com.rainiersoft.iocl.dao.IOCLUserDetailsDAO;
import com.rainiersoft.iocl.entity.IoclSupportedUserrole;
import com.rainiersoft.iocl.entity.IoclSupportedUserstatus;
import com.rainiersoft.iocl.entity.IoclUserDetail;
import com.rainiersoft.iocl.entity.IoclUserroleMapping;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Singleton;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Singleton
public class IOCLUserDetailsDAOImpl  extends GenericDAOImpl<IoclUserDetail, Long> implements IOCLUserDetailsDAO
{
	private static final Logger LOG = LoggerFactory.getLogger(IOCLUserDetailsDAOImpl.class);

	public IOCLUserDetailsDAOImpl() {}

	public Long insertUserDetails(String userName, String userPassword, String userFirstName, String userLastName, String userDOB, String userAadharNum, IoclSupportedUserrole userType, String userMobileNum, IoclSupportedUserstatus userStatus, Date createdTimeStamp, Date expiryTimeStamp) 
	{
		Session session = getCurrentSession();
		IoclUserDetail ioclUserDetails = new IoclUserDetail();
		ioclUserDetails.setUserName(userName);
		ioclUserDetails.setUserPassword(userPassword);
		ioclUserDetails.setUserFirstName(userFirstName);
		ioclUserDetails.setUserLastName(userLastName);
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try 
		{
			ioclUserDetails.setUserDOB(formatter.parse(userDOB));
		}
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		ioclUserDetails.setUserAadharNum(userAadharNum);
		ioclUserDetails.setUserMobileNum(userMobileNum);
		ioclUserDetails.setIoclSupportedUserstatus(userStatus);
		ioclUserDetails.setUserCreatedOn(createdTimeStamp);
		ioclUserDetails.setPwdExpiryDate(expiryTimeStamp);

		List<IoclUserroleMapping> listIoclUserroleMappings = new ArrayList<IoclUserroleMapping>();
		IoclUserroleMapping ioclUserroleMapping = new IoclUserroleMapping();
		ioclUserroleMapping.setIoclSupportedUserrole(userType);
		ioclUserroleMapping.setIoclUserDetail(ioclUserDetails);
		listIoclUserroleMappings.add(ioclUserroleMapping);

		ioclUserDetails.setIoclUserroleMappings(listIoclUserroleMappings);
		Integer userId=(Integer) session.save(ioclUserDetails);
		return userId.longValue();
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=true,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public IoclUserDetail findUserByUserName(String userName)
	{
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findUserByUserName");
		query.setParameter("userName", userName);
		IoclUserDetail ioclUserDetail = (IoclUserDetail)findObject(query);
		return ioclUserDetail;
	}

	public void updateUserDetails(String userName, String userPassword, String userMobileNum, IoclSupportedUserstatus userStatus, Date updatedTimeStamp, IoclUserDetail ioclUserDetail,String userFirstName,String userLastName,String userDOB,String userAadharNum,IoclSupportedUserrole userType)
	{
		Session session = getCurrentSession();
		ioclUserDetail.setUserName(userName);
		ioclUserDetail.setUserFirstName(userFirstName);
		ioclUserDetail.setUserLastName(userLastName);
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try 
		{
			ioclUserDetail.setUserDOB(formatter.parse(userDOB));
		}
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		ioclUserDetail.setUserAadharNum(userAadharNum);
		System.out.println("Password....."+userPassword);
		ioclUserDetail.setUserPassword(userPassword);
		ioclUserDetail.setUserMobileNum(userMobileNum);
		ioclUserDetail.setIoclSupportedUserstatus(userStatus);
		ioclUserDetail.setUserUpdatedOn(updatedTimeStamp);
		ioclUserDetail.getIoclUserroleMappings().get(0).setIoclSupportedUserrole(userType);
		session.update(ioclUserDetail);
	}

	public List<IoclUserDetail> findUsers() {
		List<IoclUserDetail> listOfUsers = findAll(IoclUserDetail.class);
		return listOfUsers;
	}

	public boolean deleteUser(int userId) 
	{
		return deleteById(IoclUserDetail.class, Integer.valueOf(userId));
	}

	@Override
	public IoclUserDetail findUserByUserId(int userId) {
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findUserByUserId");
		query.setParameter("userId", userId);
		IoclUserDetail ioclUserDetail = (IoclUserDetail)findObject(query);
		return ioclUserDetail;
	}
}