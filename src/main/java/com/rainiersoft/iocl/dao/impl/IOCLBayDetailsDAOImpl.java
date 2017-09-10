package com.rainiersoft.iocl.dao.impl;

import com.rainiersoft.iocl.dao.IOCLBayDetailsDAO;
import com.rainiersoft.iocl.entity.IoclBayDetail;
import com.rainiersoft.iocl.entity.IoclBayType;
import com.rainiersoft.iocl.entity.IoclSupportedBaystatus;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
@Singleton
public class IOCLBayDetailsDAOImpl extends GenericDAOImpl<IoclBayDetail, Long> implements IOCLBayDetailsDAO
{
	private static final Logger LOG = LoggerFactory.getLogger(IOCLBayDetailsDAOImpl.class);

	public IOCLBayDetailsDAOImpl() {}

	public List<IoclBayDetail> findAllAvailableBaysInApplication() {
		return findAll(IoclBayDetail.class);
	}

	public Long insertBayDetails(String bayName, int bayNum, int bayTypeId, IoclSupportedBaystatus ioclSupportedBaystatus)
	{
		Session session = getCurrentSession();
		IoclBayDetail ioclBayDetail = new IoclBayDetail();
		ioclBayDetail.setBayName(bayName);
		ioclBayDetail.setBayNum(bayNum);
		LOG.info("ioclSupportedBaystatus:::::::::" + ioclSupportedBaystatus);
		ioclBayDetail.setIoclSupportedBaystatus(ioclSupportedBaystatus);

		List<IoclBayType> listIoclBayType = new ArrayList<IoclBayType>();
		IoclBayType ioclBayType = new IoclBayType();
		ioclBayType.setBayTypeId(bayTypeId);
		ioclBayType.setIoclBayDetail(ioclBayDetail);
		listIoclBayType.add(ioclBayType);

		ioclBayDetail.setIoclBayTypes(listIoclBayType);

		LOG.info("IOCLLLLLLL:::::::" + ioclBayDetail);

		Integer bayId=(Integer) session.save(ioclBayDetail);
		return bayId.longValue();
	}


	public IoclBayDetail findBayByBayNum(int bayNum)
	{
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findBayByBayNum");
		query.setParameter("bayNum", Integer.valueOf(bayNum));
		LOG.info("Query:findBayByBayNum " + query);
		IoclBayDetail ioclBayDetail = (IoclBayDetail)findObject(query);
		return ioclBayDetail;
	}


	public boolean deleteBay(int bayId)
	{
		return deleteById(IoclBayDetail.class, Integer.valueOf(bayId));
	}

	@Override
	public void updateBayDetails(String bayName, int bayNum, List<IoclBayType> listIoclBayType,IoclSupportedBaystatus ioclSupportedBaystatus,IoclBayDetail ioclBayDetail) 
	{
		Session session = getCurrentSession();
		ioclBayDetail.setBayName(bayName);
		ioclBayDetail.setBayNum(bayNum);
		ioclBayDetail.setIoclSupportedBaystatus(ioclSupportedBaystatus);
		ioclBayDetail.getIoclBayTypes().get(0).setIoclBayDetail(ioclBayDetail);
		session.update(ioclBayDetail);
	}

	@Override
	public IoclBayDetail findBayByBayId(int bayId) 
	{
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findBayByBayId");
		query.setParameter("bayId",bayId);
		LOG.info("findBayByBayId " + query);
		IoclBayDetail ioclBayDetail = (IoclBayDetail)findObject(query);
		return ioclBayDetail;
	}

	@Override
	public IoclBayDetail findBayByBayName(String bayName) 
	{
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findBayByBayName");
		query.setParameter("bayName",bayName);
		LOG.info("findBayByBayName " + query);
		IoclBayDetail ioclBayDetail = (IoclBayDetail)findObject(query);
		return ioclBayDetail;
	}
}
