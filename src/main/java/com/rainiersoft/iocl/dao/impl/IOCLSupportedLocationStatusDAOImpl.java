package com.rainiersoft.iocl.dao.impl;

import java.util.List;

import javax.inject.Singleton;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.rainiersoft.iocl.dao.IOCLSupportedLocationStatusDAO;
import com.rainiersoft.iocl.entity.IoclSupportedLocationstatus;

@Repository
@Singleton
public class IOCLSupportedLocationStatusDAOImpl extends GenericDAOImpl<IoclSupportedLocationstatus, Long> implements IOCLSupportedLocationStatusDAO
{
	public IOCLSupportedLocationStatusDAOImpl() {}
	
	public List<IoclSupportedLocationstatus> findAllSupportedLocationStatus()
	{
		List<IoclSupportedLocationstatus> listOfStatus = findAll(IoclSupportedLocationstatus.class);
		return listOfStatus;
	}

	public IoclSupportedLocationstatus findStatusIdByLocationStatus(String locationStatus)
	{
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findStatusIdByLocationStatus");
		query.setParameter("locationStatus", locationStatus);
		IoclSupportedLocationstatus ioclSupportedBaystatus = (IoclSupportedLocationstatus)findObject(query);
		return ioclSupportedBaystatus;
	}
}
