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

	public void insertBayDetails(String bayName, int bayNum, String bayType, IoclSupportedBaystatus ioclSupportedBaystatus)
	{
		Session session = getCurrentSession();
		System.out.println("Transaction::::::" + session.getTransaction().isActive() + ":::::::" + session.getFlushMode());
		IoclBayDetail ioclBayDetail = new IoclBayDetail();
		ioclBayDetail.setBayName(bayName);
		ioclBayDetail.setBayNum(bayNum);
		LOG.info("ioclSupportedBaystatus:::::::::" + ioclSupportedBaystatus);
		ioclBayDetail.setIoclSupportedBaystatus(ioclSupportedBaystatus);

		List<IoclBayType> listIoclBayType = new ArrayList<IoclBayType>();
		IoclBayType ioclBayType = new IoclBayType();
		ioclBayType.setBayType(bayType);
		ioclBayType.setIoclBayDetail(ioclBayDetail);
		listIoclBayType.add(ioclBayType);

		ioclBayDetail.setIoclBayTypes(listIoclBayType);

		System.out.println("IOCLLLLLLL:::::::" + ioclBayDetail);

		session.save(ioclBayDetail);
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


	public boolean deleteBay(int bayNum)
	{
		return deleteById(IoclBayDetail.class, Integer.valueOf(bayNum));
	}
}
