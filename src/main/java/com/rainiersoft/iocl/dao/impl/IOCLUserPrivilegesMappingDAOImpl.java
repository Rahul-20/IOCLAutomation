package com.rainiersoft.iocl.dao.impl;

import java.util.List;
import javax.inject.Singleton;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.rainiersoft.iocl.dao.IOCLUserPrivilegesMappingDAO;
import com.rainiersoft.iocl.entity.IoclUserPrivilegesMapping;

@Repository
@Singleton
public class IOCLUserPrivilegesMappingDAOImpl extends GenericDAOImpl<IoclUserPrivilegesMapping, Long> implements IOCLUserPrivilegesMappingDAO
{
	@Override
	public List<IoclUserPrivilegesMapping> findPrivilegesByRole(String userRole) 
	{
		Session session=getCurrentSession();
		Query query=session.getNamedQuery("findPrivilegeNamesByRole");
		query.setParameter("userRole",userRole);
		System.out.println("quer:::::"+query);
		List<IoclUserPrivilegesMapping> ioclUserPrivilegesMapping= findObjectCollection(query);
		System.out.println(":::::::::"+ioclUserPrivilegesMapping.size());
		return ioclUserPrivilegesMapping;
	}
}
