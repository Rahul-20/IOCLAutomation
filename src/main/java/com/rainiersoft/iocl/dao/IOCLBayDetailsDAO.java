package com.rainiersoft.iocl.dao;

import java.util.Date;
import java.util.List;

import com.rainiersoft.iocl.entity.IoclBayDetail;
import com.rainiersoft.iocl.entity.IoclSupportedBaystatus;
import com.rainiersoft.iocl.entity.IoclSupportedBaytype;

public interface IOCLBayDetailsDAO extends GenericDAO<IoclBayDetail, Long>
{
	public List<IoclBayDetail> findAllAvailableBaysInApplication();

	public Long insertBayDetails(String bayName, int bayNum, int bayType, IoclSupportedBaystatus ioclSupportedBaystatus,int userID,Date bayCreatedOn);

	public IoclBayDetail findBayByBayNum(int bayNum);
	
	public IoclBayDetail findBayByBayName(String bayName);
	
	public IoclBayDetail findBayByBayId(int bayId);

	public boolean deleteBay(int bayId);
	
	public void updateBayDetails(String bayName,int bayNum,int bayTypeId, IoclSupportedBaystatus ioclSupportedBaystatus,IoclBayDetail ioclBayDetail,IoclSupportedBaytype ioclSupportedBaytype,int userID,Date bayUpdatedOn);
}
