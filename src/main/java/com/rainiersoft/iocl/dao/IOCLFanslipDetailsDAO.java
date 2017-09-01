package com.rainiersoft.iocl.dao;

import com.rainiersoft.iocl.entity.IoclFanslipDetail;
import com.rainiersoft.iocl.entity.IoclLocationDetail;
import com.rainiersoft.iocl.entity.IoclSupportedPinstatus;
import java.util.Date;
import java.util.List;

public interface IOCLFanslipDetailsDAO extends GenericDAO<IoclFanslipDetail, Long>
{
	public void insertFanSlipDetails(int bayNo, String fanPin, IoclSupportedPinstatus fanPinStatusId, int truckID, Date createdOn, String quantity, String vehicleWgt, String destination, IoclLocationDetail locationId);

	public List<IoclFanslipDetail> findAnyBayIsAssignedInPast(int bayNo, Date currDate, Date pastDate);

	public IoclFanslipDetail findFanPinStatusByFanPin(String fanPin);
}
