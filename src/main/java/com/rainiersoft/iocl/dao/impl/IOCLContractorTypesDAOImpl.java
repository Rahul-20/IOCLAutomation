package com.rainiersoft.iocl.dao.impl;

import java.util.List;

import javax.inject.Singleton;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.rainiersoft.iocl.dao.IOCLContractorTypeDAO;
import com.rainiersoft.iocl.entity.IoclContractortypeDetail;


@Repository
@Singleton
public class IOCLContractorTypesDAOImpl extends GenericDAOImpl<IoclContractortypeDetail, Long> implements IOCLContractorTypeDAO 
{
	public List<IoclContractortypeDetail> findAllContractorTypes()
	{
		return findAll(IoclContractortypeDetail.class);
	}

	public IoclContractortypeDetail findContractorIdByContractorType(String contractorType) {
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findContractorIdByContractorType");
		query.setParameter("contractorType", contractorType);
		IoclContractortypeDetail ioclContractortypeDetail = (IoclContractortypeDetail)findObject(query);
		return ioclContractortypeDetail;
	}
}
