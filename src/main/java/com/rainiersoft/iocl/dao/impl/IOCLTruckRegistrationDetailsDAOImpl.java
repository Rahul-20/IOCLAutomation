package com.rainiersoft.iocl.dao.impl;

import com.rainiersoft.iocl.dao.IOCLTruckRegistrationDetailsDAO;
import com.rainiersoft.iocl.entity.IoclTruckregistrationDetail;
import javax.inject.Singleton;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
@Singleton
public class IOCLTruckRegistrationDetailsDAOImpl extends GenericDAOImpl<IoclTruckregistrationDetail, Long> implements IOCLTruckRegistrationDetailsDAO
{
	public IOCLTruckRegistrationDetailsDAOImpl() {}

	public IoclTruckregistrationDetail findTruckByTruckNo(String truckNo)
	{
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findTruckByTruckNo");
		query.setParameter("truckNo", truckNo);
		IoclTruckregistrationDetail ioclTruckregistrationDetail = (IoclTruckregistrationDetail)findObject(query);
		return ioclTruckregistrationDetail;
	}

	public void insertTruckregistrationDetail(String truckNo, String driverName, String driverLicNo, String cutomer, String mobileNumber)
	{
		IoclTruckregistrationDetail ioclTruckregistrationDetail = new IoclTruckregistrationDetail();
		ioclTruckregistrationDetail.setCustomer(cutomer);
		ioclTruckregistrationDetail.setDriverLicNo(driverLicNo);
		ioclTruckregistrationDetail.setDriverName(driverName);
		ioclTruckregistrationDetail.setMobileNumber(mobileNumber);
		ioclTruckregistrationDetail.setTruckNo(truckNo);
		saveOrUpdate(ioclTruckregistrationDetail);
	}

	@Override
	public IoclTruckregistrationDetail findTruckByTruckId(int truckId) 
	{
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findTruckByTruckId");
		query.setParameter("truckId", truckId);
		IoclTruckregistrationDetail ioclTruckregistrationDetail = (IoclTruckregistrationDetail)findObject(query);
		return ioclTruckregistrationDetail;
	}
}
