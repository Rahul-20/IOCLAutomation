package com.rainiersoft.iocl.dao;

import com.rainiersoft.iocl.entity.IoclContractorDetail;
import com.rainiersoft.iocl.entity.IoclFanslipDetail;
import com.rainiersoft.iocl.entity.IoclLocationDetail;
import com.rainiersoft.iocl.entity.IoclSupportedPinstatus;
import java.util.Date;
import java.util.List;

public interface IOCLFanslipDetailsDAO extends GenericDAO<IoclFanslipDetail, Long>
{
	public Long insertFanSlipDetails(int bayNo, String fanPin, IoclSupportedPinstatus fanPinStatusId, int truckID, Date createdOn, String quantity, String vehicleWgt, String destination, IoclLocationDetail locationId,Date fanExpirationTime,IoclContractorDetail ioclContractorDetail,int userId);

	public List<IoclFanslipDetail> findAnyBayIsAssignedInPast(int bayNo, Date currDate, Date pastDate);
	
	public List<IoclFanslipDetail> findAllLatestFanSlips(Date currDate, Date pastDate);

	public IoclFanslipDetail findFanPinStatusByFanPin(String fanPin);
	
	public IoclFanslipDetail findFanPinByFanId(int FanId);
	
	public void updateFanPinDetails(IoclFanslipDetail ioclFanslipDetail,IoclSupportedPinstatus ioclSupportedPinstatus,int userID,Date updatedOn);
}
