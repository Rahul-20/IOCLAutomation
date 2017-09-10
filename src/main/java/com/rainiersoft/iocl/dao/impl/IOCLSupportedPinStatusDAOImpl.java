package com.rainiersoft.iocl.dao.impl;

import com.rainiersoft.iocl.dao.IOCLSupportedPinStatusDAO;
import com.rainiersoft.iocl.entity.IoclSupportedPinstatus;
import java.util.List;
import javax.inject.Singleton;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
@Singleton
public class IOCLSupportedPinStatusDAOImpl extends GenericDAOImpl<IoclSupportedPinstatus, Long> implements IOCLSupportedPinStatusDAO
{
	private static final Logger LOG = LoggerFactory.getLogger(IOCLSupportedPinStatusDAOImpl.class);

	public IOCLSupportedPinStatusDAOImpl() {}

	public IoclSupportedPinstatus findPinStatusIdByPinStatus(String pinStatus) {
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findStatusIdByPinStatus");
		query.setParameter("fanPinStatus", pinStatus);
		IoclSupportedPinstatus ioclSupportedPinstatus = (IoclSupportedPinstatus)findObject(query);
		return ioclSupportedPinstatus;
	}
	
	public List<IoclSupportedPinstatus> findAllPinStatus()
	{
		return findAll(IoclSupportedPinstatus.class);
	}
}
