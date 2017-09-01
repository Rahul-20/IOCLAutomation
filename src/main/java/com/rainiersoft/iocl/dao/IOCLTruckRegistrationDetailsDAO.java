package com.rainiersoft.iocl.dao;

import com.rainiersoft.iocl.entity.IoclTruckregistrationDetail;

public interface IOCLTruckRegistrationDetailsDAO extends GenericDAO<IoclTruckregistrationDetail, Long>
{
	public IoclTruckregistrationDetail findTruckByTruckNo(String truckNo);

	public void insertTruckregistrationDetail(String truckNo, String driverName, String driverLicNo, String cutomer, String mobileNumber);
}
