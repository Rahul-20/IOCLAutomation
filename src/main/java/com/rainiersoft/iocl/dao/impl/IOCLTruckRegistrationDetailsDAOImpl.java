package com.rainiersoft.iocl.dao.impl;

import javax.inject.Singleton;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.rainiersoft.iocl.dao.IOCLTruckRegistrationDetailsDAO;
import com.rainiersoft.iocl.entity.IoclTruckregistrationDetail;

@Repository
@Singleton
public class IOCLTruckRegistrationDetailsDAOImpl extends GenericDAOImpl<IoclTruckregistrationDetail, Long> implements IOCLTruckRegistrationDetailsDAO
{

	@Override
	public IoclTruckregistrationDetail findTruckByTruckNo(String truckNo) 
	{
		Session session=getCurrentSession();
		Query query=session.getNamedQuery("findTruckByTruckNo");
		query.setParameter("truckNo",truckNo);
		IoclTruckregistrationDetail ioclTruckregistrationDetail= findObject(query);
		return ioclTruckregistrationDetail;
	}

	@Override
	public void insertTruckregistrationDetail(String truckNo, String driverName, String driverLicNo, String cutomer,
			String mobileNumber, String quantity, String vehicleWgt, String destination, String locationCode) 
	{
		IoclTruckregistrationDetail ioclTruckregistrationDetail=new IoclTruckregistrationDetail();
		ioclTruckregistrationDetail.setCutomer(cutomer);
		ioclTruckregistrationDetail.setDestination(destination);
		ioclTruckregistrationDetail.setDriverLicNo(driverLicNo);
		ioclTruckregistrationDetail.setDriverName(driverName);
		ioclTruckregistrationDetail.setLocationCode(locationCode);
		ioclTruckregistrationDetail.setMobileNumber(mobileNumber);
		ioclTruckregistrationDetail.setQuantity(quantity);
		ioclTruckregistrationDetail.setTruckNo(truckNo);
		ioclTruckregistrationDetail.setVehicleWgt(vehicleWgt);
		saveOrUpdate(ioclTruckregistrationDetail);
	}
	

}