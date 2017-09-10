
package com.rainiersoft.iocl.dao.impl;

import java.util.List;

import javax.inject.Singleton;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.rainiersoft.iocl.dao.IOCLSupportedContractorStatusDAO;
import com.rainiersoft.iocl.entity.IoclSupportedContractorstatus;

@Repository
@Singleton
public class IOCLSupportedContractorStatusDAOImpl extends GenericDAOImpl<IoclSupportedContractorstatus, Long> implements IOCLSupportedContractorStatusDAO
{
	public IOCLSupportedContractorStatusDAOImpl() {}

	public List<IoclSupportedContractorstatus> findAllSupportedContractorStatus()
	{
		List<IoclSupportedContractorstatus> listOfStatus = findAll(IoclSupportedContractorstatus.class);
		return listOfStatus;
	}

	public IoclSupportedContractorstatus findStatusIdByContractorStatus(String bayStatus)
	{
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findStatusIdByContractorStatus");
		query.setParameter("contractorStatus", bayStatus);
		IoclSupportedContractorstatus IoclSupportedContractorstatus = (IoclSupportedContractorstatus)findObject(query);
		return IoclSupportedContractorstatus;
	}
}


