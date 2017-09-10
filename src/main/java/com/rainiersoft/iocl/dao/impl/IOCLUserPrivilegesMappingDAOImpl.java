package com.rainiersoft.iocl.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.rainiersoft.iocl.dao.IOCLUserPrivilegesMappingDAO;
import com.rainiersoft.iocl.entity.IoclUserPrivilegesMapping;

@Repository
@javax.inject.Singleton
public class IOCLUserPrivilegesMappingDAOImpl extends GenericDAOImpl<IoclUserPrivilegesMapping, Long> implements IOCLUserPrivilegesMappingDAO
{
	public IOCLUserPrivilegesMappingDAOImpl() {}

	public List<IoclUserPrivilegesMapping> findPrivilegesByRole(int userRoleId)
	{
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findPrivilegeNamesByRole");
		query.setParameter("userRoleId", Integer.valueOf(userRoleId));
		List<IoclUserPrivilegesMapping> ioclUserPrivilegesMapping = findObjectCollection(query);
		return ioclUserPrivilegesMapping;
	}
}
