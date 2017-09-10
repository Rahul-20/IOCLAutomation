package com.rainiersoft.iocl.dao.impl;

import java.util.List;
import javax.inject.Singleton;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.rainiersoft.iocl.dao.IOCLSupportedBayTypesDAO;
import com.rainiersoft.iocl.entity.IoclSupportedBaytype;

@Repository
@Singleton
public class IOCLSupportedBayTypesDAOImpl extends GenericDAOImpl<IoclSupportedBaytype, Long> implements IOCLSupportedBayTypesDAO
{
	public IOCLSupportedBayTypesDAOImpl() {}

	public List<IoclSupportedBaytype> findAllSupportedBayTypes()
	{
		List<IoclSupportedBaytype> listOfTypes = findAll(IoclSupportedBaytype.class);
		return listOfTypes;
	}

	public IoclSupportedBaytype findBayTypeIdByBayType(String bayType) 
	{
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findBayTypeIdByBayType");
		query.setParameter("bayType", bayType);
		IoclSupportedBaytype ioclSupportedBaytype = (IoclSupportedBaytype)findObject(query);
		return ioclSupportedBaytype;
	}
	
	public IoclSupportedBaytype findBayTypeByBayTypeId(int typeId) 
	{
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findBayTypeByBayTypeId");
		query.setParameter("typeId", typeId);
		IoclSupportedBaytype ioclSupportedBaytype = (IoclSupportedBaytype)findObject(query);
		return ioclSupportedBaytype;
	}
}
