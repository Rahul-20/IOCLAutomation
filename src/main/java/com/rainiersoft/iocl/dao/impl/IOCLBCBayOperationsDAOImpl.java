package com.rainiersoft.iocl.dao.impl;

import com.rainiersoft.iocl.dao.IOCLBCBayOperationsDAO;
import com.rainiersoft.iocl.entity.IoclBcBayoperation;
import java.util.Date;
import java.util.List;
import javax.inject.Singleton;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;



@Repository
@Singleton
public class IOCLBCBayOperationsDAOImpl extends GenericDAOImpl<IoclBcBayoperation, Long> implements IOCLBCBayOperationsDAO
{
	private static final Logger LOG = LoggerFactory.getLogger(IOCLBCBayOperationsDAOImpl.class);

	public IOCLBCBayOperationsDAOImpl() {}

	public List<IoclBcBayoperation> findBayUpdatesByBC(int bayNo, Date currDate, Date pastDate) {
		LOG.info("Bay Nums::::" + bayNo);
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findBayUpdatesByBC");
		LOG.info("query:" + query);
		query.setParameter("bayNum", Integer.valueOf(bayNo));
		query.setParameter("currDate", currDate);
		query.setParameter("pastDate", pastDate);
		List<IoclBcBayoperation> ioclBcBayoperation = findObjectCollection(query);
		return ioclBcBayoperation;
	}


	public List<IoclBcBayoperation> findBayUpdatesByFanPin(String fanPin)
	{
		LOG.info("fanPin::::" + fanPin);
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findBayUpdatesByFanPin");
		LOG.info("query:" + query);
		query.setParameter("fanPin", fanPin);
		List<IoclBcBayoperation> ioclBcBayoperation = findObjectCollection(query);
		return ioclBcBayoperation;
	}
}
