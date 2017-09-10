package com.rainiersoft.iocl.dao.impl;

import java.util.List;

import javax.inject.Singleton;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.rainiersoft.iocl.dao.IOCLStatesDetailsDAO;
import com.rainiersoft.iocl.entity.IoclStatesDetail;

@Repository
@Singleton
public class IOCLStatesDetailsDAOImpl extends GenericDAOImpl<IoclStatesDetail, Long> implements IOCLStatesDetailsDAO 
{
	public List<IoclStatesDetail> findAllStates()
	{
		return findAll(IoclStatesDetail.class);
	}

	public IoclStatesDetail findStateIdByStateName(String stateName)
	{
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findStateIdByStateName");
		query.setParameter("stateName", stateName);
		IoclStatesDetail ioclStatesDetail = (IoclStatesDetail)findObject(query);
		return ioclStatesDetail;
	}
}
