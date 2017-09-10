package com.rainiersoft.iocl.dao.impl;

import com.rainiersoft.iocl.dao.IOCLUserroleMappingDAO;
import com.rainiersoft.iocl.entity.IoclUserroleMapping;
import java.util.List;
import javax.inject.Singleton;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;



@Repository
@Singleton
public class IOCLUserroleMappingDAOImpl extends GenericDAOImpl<IoclUserroleMapping, Long> implements IOCLUserroleMappingDAO
{
	public IOCLUserroleMappingDAOImpl() {}

	private static final Logger LOG = LoggerFactory.getLogger(IOCLUserroleMappingDAOImpl.class);

	public List<IoclUserroleMapping> findRolesByUserID(String userId) {
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findRolesByUserID");
		query.setParameter("UserId", userId);
		List<IoclUserroleMapping> ioclUserroleMapping = findObjectCollection(query);
		return ioclUserroleMapping;
	}
}
