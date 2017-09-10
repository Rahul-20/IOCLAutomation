package com.rainiersoft.iocl.dao.impl;

import com.rainiersoft.iocl.dao.IOCLSupportedUserRoleDAO;
import com.rainiersoft.iocl.entity.IoclSupportedUserrole;
import java.util.List;
import javax.inject.Singleton;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


@Repository
@Singleton
public class IOCLSupportedUserRoleDAOImpl extends GenericDAOImpl<IoclSupportedUserrole, Long> implements IOCLSupportedUserRoleDAO
{
	private static final Logger LOG = LoggerFactory.getLogger(IOCLSupportedUserRoleDAOImpl.class);

	public IOCLSupportedUserRoleDAOImpl() {}

	public IoclSupportedUserrole findRoleIdByRoleName(String roleName) { Session session = getCurrentSession();
	Query query = session.getNamedQuery("findUserRoleIdByUserRole");
	query.setParameter("roleName", roleName);
	IoclSupportedUserrole ioclSupportedUserrole = (IoclSupportedUserrole)findObject(query);
	return ioclSupportedUserrole;
	}

	public List<IoclSupportedUserrole> findAllRoleNames()
	{
		return findAll(IoclSupportedUserrole.class);
	}
}
