
package com.rainiersoft.iocl.dao.impl;

import java.util.List;

import javax.inject.Singleton;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.rainiersoft.iocl.dao.IOCLSupportedQunatityStatusDAO;
import com.rainiersoft.iocl.entity.IoclSupportedQuantitystatus;

@Repository
@Singleton
public class IOCLSupportedQuantityStatusDAOImpl extends GenericDAOImpl<IoclSupportedQuantitystatus, Long> implements IOCLSupportedQunatityStatusDAO
{
	public IOCLSupportedQuantityStatusDAOImpl() {}

	public List<IoclSupportedQuantitystatus> findAllSupportedQuantityStatus()
	{
		List<IoclSupportedQuantitystatus> listOfStatus = findAll(IoclSupportedQuantitystatus.class);
		return listOfStatus;
	}

	@Override
	public IoclSupportedQuantitystatus findStatusIdByQuantityStatus(String status) {
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findStatusIdByQuantityStatus");
		query.setParameter("quantityStatus", status);
		IoclSupportedQuantitystatus IoclSupportedQuantitystatus = (IoclSupportedQuantitystatus)findObject(query);
		return IoclSupportedQuantitystatus;
	}
}