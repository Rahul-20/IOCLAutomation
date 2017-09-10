package com.rainiersoft.iocl.dao.impl;

import com.rainiersoft.iocl.dao.IOCLSupportedUserStatusDAO;
import com.rainiersoft.iocl.entity.IoclSupportedUserstatus;
import java.util.List;
import javax.inject.Singleton;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;



@Repository
@Singleton
public class IOCLSupportedUserStatusDAOImpl extends GenericDAOImpl<IoclSupportedUserstatus, Long> implements IOCLSupportedUserStatusDAO
{
	private static final Logger LOG = LoggerFactory.getLogger(IOCLSupportedUserStatusDAOImpl.class);

	public IOCLSupportedUserStatusDAOImpl() {}

	public IoclSupportedUserstatus findUserStatusIdByUserStatus(String userStatus) {
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findUserStatusIdByUserStatus");
		query.setParameter("userStatus", userStatus);
		IoclSupportedUserstatus ioclSupportedUserstatus = (IoclSupportedUserstatus)findObject(query);
		return ioclSupportedUserstatus;
	}

	public List<IoclSupportedUserstatus> findAllUserStatus()
	{
		return findAll(IoclSupportedUserstatus.class);
	}
}
