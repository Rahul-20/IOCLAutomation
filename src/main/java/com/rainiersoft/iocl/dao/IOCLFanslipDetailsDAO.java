package com.rainiersoft.iocl.dao;

import com.rainiersoft.iocl.entity.IoclFanslipDetail;

public interface IOCLFanslipDetailsDAO extends GenericDAO<IoclFanslipDetail,Long> 
{
	public void insertFanSlipDetails(String bayNo,String fanPin,String fanPinStatus,int truckID,String createdOn);

}
