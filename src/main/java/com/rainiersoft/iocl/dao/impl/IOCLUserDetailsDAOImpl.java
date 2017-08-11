package com.rainiersoft.iocl.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Singleton;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rainiersoft.iocl.dao.IOCLUserDetailsDAO;
import com.rainiersoft.iocl.entity.IoclUserDetail;
import com.rainiersoft.iocl.entity.IoclUserroleMapping;

@Repository
@Singleton
@SuppressWarnings("deprecation")
public class IOCLUserDetailsDAOImpl extends GenericDAOImpl<IoclUserDetail, Long> implements IOCLUserDetailsDAO 
{
	private static final Logger LOG = LoggerFactory.getLogger(IOCLUserDetailsDAOImpl.class);

	@Override
	public void insertUserDetails(String userName,String userPassword,String userFirstName,String userLastName,String userDOB,String userAadharNum,List<String> userType,String userMobileNum,String userStatus,String createdTimeStamp,String expiryTimeStamp) 
	{
		Session session=getCurrentSession();
		IoclUserDetail ioclUserDetails =new IoclUserDetail();
		ioclUserDetails.setUserName(userName);
		ioclUserDetails.setUserPassword(userPassword);
		ioclUserDetails.setUserFirstName(userFirstName);
		ioclUserDetails.setUserLastName(userLastName);
		ioclUserDetails.setUserDOB(userDOB);
		ioclUserDetails.setUserAadharNum(userAadharNum);
		ioclUserDetails.setUserMobileNum(userMobileNum);
		ioclUserDetails.setUserStatus(userStatus);
		ioclUserDetails.setUserCreatedOn(new Date(createdTimeStamp));
		ioclUserDetails.setPwdExpiryDate(new Date(expiryTimeStamp));

		List<IoclUserroleMapping> listIoclUserroleMappings=new ArrayList<IoclUserroleMapping>();
		for(String userRole: userType)
		{
			LOG.info("UserRole::::"+userRole);
			IoclUserroleMapping ioclUserroleMapping=new IoclUserroleMapping();
			ioclUserroleMapping.setUserType(userRole);
			ioclUserroleMapping.setIoclUserDetail(ioclUserDetails);
			listIoclUserroleMappings.add(ioclUserroleMapping);
		}
		LOG.info("UserRole::::"+listIoclUserroleMappings.size());
		ioclUserDetails.setIoclUserroleMappings(listIoclUserroleMappings);
		session.save(ioclUserDetails);
	}

	@Override
	@Transactional
	public IoclUserDetail findUserByUserName(String userName) 
	{
		LOG.info("UserName::::;;;"+userName);
		Session session=getCurrentSession();
		Query query=session.getNamedQuery("findUserByUserName");
		LOG.info("query:"+query);
		query.setParameter("userName",userName);
		LOG.info("userName:"+userName);
		IoclUserDetail ioclUserDetail= findObject(query);
		return ioclUserDetail;
	}

	@Override
	public void updateUserDetails(String userName, String userPassword, String userMobileNum, String userStatus,String updatedTimeStamp,IoclUserDetail ioclUserDetail) 
	{
		Session session=getCurrentSession();
		ioclUserDetail.setUserPassword(userPassword);
		ioclUserDetail.setUserMobileNum(userMobileNum);
		ioclUserDetail.setUserStatus(userStatus);
		ioclUserDetail.setUserUpdatedOn(new Date(updatedTimeStamp));
		session.update(ioclUserDetail);
	}

	@Override
	public List<IoclUserDetail> findUsers() {
		Session session=getCurrentSession();
		List<IoclUserDetail> listOfUsers=findAll(IoclUserDetail.class);
		return listOfUsers;
	}
}