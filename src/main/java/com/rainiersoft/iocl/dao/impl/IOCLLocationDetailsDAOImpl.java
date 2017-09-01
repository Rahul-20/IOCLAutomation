package com.rainiersoft.iocl.dao.impl;

import com.rainiersoft.iocl.dao.IOCLLocationDetailsDAO;
import com.rainiersoft.iocl.entity.IoclLocationDetail;
import java.util.List;
import javax.inject.Singleton;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


@Repository
@Singleton
public class IOCLLocationDetailsDAOImpl extends GenericDAOImpl<IoclLocationDetail, Long> implements IOCLLocationDetailsDAO
{
	private static final Logger LOG = LoggerFactory.getLogger(IOCLLocationDetailsDAOImpl.class);

	public IOCLLocationDetailsDAOImpl() {}

	public IoclLocationDetail findLocationIdByLocationCode(String locationCode) {
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findLocationIdByLocationCode");
		query.setParameter("locationCode", locationCode);
		LOG.info("findLocationIdByLocationCode " + query);
		IoclLocationDetail ioclLocationDetail = (IoclLocationDetail)findObject(query);
		return ioclLocationDetail;
	}


	public List<IoclLocationDetail> findAllLocationCodes()
	{
		List<IoclLocationDetail> listOfIoclLocationDetail = findAll(IoclLocationDetail.class);
		return listOfIoclLocationDetail;
	}
}
