package com.rainiersoft.iocl.dao.impl;

import com.rainiersoft.iocl.dao.IOCLSupportedBayStatusDAO;
import com.rainiersoft.iocl.entity.IoclSupportedBaystatus;
import java.util.List;
import javax.inject.Singleton;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
@Singleton
public class IOCLSupportedBayStatusDAOImpl extends GenericDAOImpl<IoclSupportedBaystatus, Long> implements IOCLSupportedBayStatusDAO
{
	public IOCLSupportedBayStatusDAOImpl() {}

	public List<IoclSupportedBaystatus> findAllSupportedBayStatus()
	{
		List<IoclSupportedBaystatus> listOfStatus = findAll(IoclSupportedBaystatus.class);
		return listOfStatus;
	}

	public IoclSupportedBaystatus findStatusIdByStatus(String bayStatus)
	{
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findStatusIdByStatus");
		query.setParameter("bayStatus", bayStatus);
		IoclSupportedBaystatus ioclSupportedBaystatus = (IoclSupportedBaystatus)findObject(query);
		return ioclSupportedBaystatus;
	}
}
