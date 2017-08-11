package com.rainiersoft.iocl.dao.impl;

import java.util.List;

import javax.inject.Singleton;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.rainiersoft.iocl.dao.IOCLUserDetailsDAO;
import com.rainiersoft.iocl.dao.IOCLUserroleMappingDAO;
import com.rainiersoft.iocl.entity.IoclUserDetail;
import com.rainiersoft.iocl.entity.IoclUserroleMapping;
import com.rainiersoft.iocl.services.UserManagementServices;

@Repository
@Singleton
public class IOCLUserroleMappingDAOImpl extends GenericDAOImpl<IoclUserroleMapping, Long> implements IOCLUserroleMappingDAO
{
	private static final Logger LOG = LoggerFactory.getLogger(IOCLUserroleMappingDAOImpl.class);
	@Override
	public List<IoclUserroleMapping> findRolesByUserID(String userId) 
	{
		Session session=getCurrentSession();
		Query query=session.getNamedQuery("findRolesByUserID");
		LOG.debug("query:"+query);
		query.setParameter("UserId",userId);
		LOG.debug("userID:"+userId);
		List<IoclUserroleMapping> ioclUserroleMapping= findObjectCollection(query);
		return ioclUserroleMapping;
	}
}
