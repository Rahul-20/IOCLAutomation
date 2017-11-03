package com.rainiersoft.iocl.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Singleton;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.rainiersoft.iocl.dao.IOCLBayDetailsDAO;
import com.rainiersoft.iocl.entity.IoclBayDetail;
import com.rainiersoft.iocl.entity.IoclBayType;
import com.rainiersoft.iocl.entity.IoclSupportedBaystatus;
import com.rainiersoft.iocl.entity.IoclSupportedBaytype;

@Repository
@Singleton
public class IOCLBayDetailsDAOImpl extends GenericDAOImpl<IoclBayDetail, Long> implements IOCLBayDetailsDAO
{
	private static final Logger LOG = LoggerFactory.getLogger(IOCLBayDetailsDAOImpl.class);

	public IOCLBayDetailsDAOImpl() {}

	public List<IoclBayDetail> findAllAvailableBaysInApplication() {
		return findAll(IoclBayDetail.class);
	}

	public Long insertBayDetails(String bayName, int bayNum, int bayTypeId, IoclSupportedBaystatus ioclSupportedBaystatus,int userID,Date bayCreatedDateObj)
	{
		Session session = getCurrentSession();
		IoclBayDetail ioclBayDetail = new IoclBayDetail();
		ioclBayDetail.setBayName(bayName);
		ioclBayDetail.setBayNum(bayNum);
		ioclBayDetail.setBayCreatedBy(userID);
		ioclBayDetail.setBayCreatedOn(bayCreatedDateObj);
		ioclBayDetail.setIoclSupportedBaystatus(ioclSupportedBaystatus);
		List<IoclBayType> listIoclBayType = new ArrayList<IoclBayType>();
		IoclBayType ioclBayType = new IoclBayType();
		ioclBayType.setBayTypeId(bayTypeId);
		ioclBayType.setIoclBayDetail(ioclBayDetail);
		listIoclBayType.add(ioclBayType);
		ioclBayDetail.setIoclBayTypes(listIoclBayType);
		Integer bayId=(Integer) session.save(ioclBayDetail);
		return bayId.longValue();
	}


	public IoclBayDetail findBayByBayNum(int bayNum)
	{
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findBayByBayNum");
		query.setParameter("bayNum", Integer.valueOf(bayNum));
		IoclBayDetail ioclBayDetail = (IoclBayDetail)findObject(query);
		return ioclBayDetail;
	}


	public boolean deleteBay(int bayId)
	{
		return deleteById(IoclBayDetail.class, Integer.valueOf(bayId));
	}

	@Override
	public void updateBayDetails(String bayName, int bayNum, int bayTypeId,IoclSupportedBaystatus ioclSupportedBaystatus,IoclBayDetail ioclBayDetail,IoclSupportedBaytype ioclSupportedBaytype,int userID,Date bayUpdatedDateObj) 
	{
		Session session = getCurrentSession();
		ioclBayDetail.setBayName(bayName);
		ioclBayDetail.setBayNum(bayNum);
		ioclBayDetail.setBayUpdatedBy(userID);
		ioclBayDetail.setBayUpdatedOn(bayUpdatedDateObj);
		ioclBayDetail.setIoclSupportedBaystatus(ioclSupportedBaystatus);
		ioclBayDetail.getIoclBayTypes().get(0).setBayTypeId(bayTypeId);
		ioclBayDetail.getIoclBayTypes().get(0).setIoclBayDetail(ioclBayDetail);
		session.update(ioclBayDetail);
	}

	@Override
	public IoclBayDetail findBayByBayId(int bayId) 
	{
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findBayByBayId");
		System.out.println("INNNNNNNN::::"+bayId);
		query.setParameter("bayId",bayId);
		IoclBayDetail ioclBayDetail = (IoclBayDetail)findObject(query);
		System.out.println("ioclBayDetails::::::"+ioclBayDetail);
		return ioclBayDetail;
	}

	@Override
	public IoclBayDetail findBayByBayName(String bayName) 
	{
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findBayByBayName");
		query.setParameter("bayName",bayName);
		IoclBayDetail ioclBayDetail = (IoclBayDetail)findObject(query);
		return ioclBayDetail;
	}
}
