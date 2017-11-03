package com.rainiersoft.iocl.dao.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Singleton;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.rainiersoft.iocl.dao.IOCLLocationDetailsDAO;
import com.rainiersoft.iocl.entity.IoclLocationDetail;
import com.rainiersoft.iocl.entity.IoclStatesDetail;
import com.rainiersoft.iocl.entity.IoclSupportedLocationstatus;


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
		IoclLocationDetail ioclLocationDetail = (IoclLocationDetail)findObject(query);
		return ioclLocationDetail;
	}


	public List<IoclLocationDetail> findAllLocationCodes()
	{
		List<IoclLocationDetail> listOfIoclLocationDetail = findAll(IoclLocationDetail.class);
		return listOfIoclLocationDetail;
	}

	@Override
	public IoclLocationDetail findLocationByLocationName(String locationName) 
	{
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findLocationByLocationName");
		query.setParameter("locationName",locationName);
		IoclLocationDetail ioclLocationDetail = (IoclLocationDetail)findObject(query);
		return ioclLocationDetail;
	}

	@Override
	public Long insertLocationDetails(String locationName, String locationCode, IoclSupportedLocationstatus ioclSupportedLocationstatus,String locationAddress,String city,String pinCode,IoclStatesDetail ioclStatesDetail,int userID,Date locationCreateddateobj) 
	{
		Session session=getCurrentSession();
		IoclLocationDetail ioclLocationDetail=new IoclLocationDetail();
		ioclLocationDetail.setLocationAddress(locationAddress);
		ioclLocationDetail.setLocationCode(locationCode);
		ioclLocationDetail.setLocationName(locationName);
		ioclLocationDetail.setIoclSupportedLocationstatus(ioclSupportedLocationstatus);
		ioclLocationDetail.setCity(city);
		ioclLocationDetail.setIoclStatesDetail(ioclStatesDetail);
		ioclLocationDetail.setPinCode(pinCode);
		ioclLocationDetail.setLocationCreatedBy(userID);
		ioclLocationDetail.setLocationCreatedOn(locationCreateddateobj);
		Integer locationId=(Integer) session.save(ioclLocationDetail);
		return Long.valueOf(locationId);
	}


	@Override
	public boolean deleteLocation(int locationId) {
		return deleteById(IoclLocationDetail.class, locationId);
	}

	@Override
	public void updateLocationDetails(String locationName, String locationCode,IoclSupportedLocationstatus locationStatus, String locationAddress, int locationId,IoclLocationDetail ioclLocationDetail,String city,String pinCode,IoclStatesDetail ioclStatesDetail,int userID,Date locationUpdateddateobj) 
	{
		Session session=getCurrentSession();
		ioclLocationDetail.setLocationAddress(locationAddress);
		ioclLocationDetail.setLocationCode(locationCode);
		ioclLocationDetail.setLocationName(locationName);
		ioclLocationDetail.setIoclSupportedLocationstatus(locationStatus);
		ioclLocationDetail.setCity(city);
		ioclLocationDetail.setIoclStatesDetail(ioclStatesDetail);
		ioclLocationDetail.setPinCode(pinCode);
		ioclLocationDetail.setLocationUpdatedBy(userID);
		ioclLocationDetail.setLocationUpdatedOn(locationUpdateddateobj);
		session.update(ioclLocationDetail);
	}

	@Override
	public IoclLocationDetail findLocationByLocationId(int locationId) {
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findLocationByLocationId");
		query.setParameter("locationId",locationId);
		IoclLocationDetail ioclLocationDetail = (IoclLocationDetail)findObject(query);
		return ioclLocationDetail;
	}
}