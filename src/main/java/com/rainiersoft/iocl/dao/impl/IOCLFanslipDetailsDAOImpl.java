package com.rainiersoft.iocl.dao.impl;

import java.util.Date;

import javax.inject.Singleton;

import org.springframework.stereotype.Repository;

import com.rainiersoft.iocl.dao.IOCLFanslipDetailsDAO;
import com.rainiersoft.iocl.entity.IoclFanslipDetail;

@Repository
@Singleton
public class IOCLFanslipDetailsDAOImpl extends GenericDAOImpl<IoclFanslipDetail, Long> implements IOCLFanslipDetailsDAO 
{
	@Override
	public void insertFanSlipDetails(String bayNo, String fanPin, String fanPinStatus, int truckID,String createdOn) {
		IoclFanslipDetail ioclFanslipDetail=new IoclFanslipDetail();
		ioclFanslipDetail.setBayNo(bayNo);
		ioclFanslipDetail.setFanPin(fanPin);
		ioclFanslipDetail.setFANPinStatus(fanPinStatus);
		ioclFanslipDetail.setTruckId(truckID);
		ioclFanslipDetail.setFanCreationOn(new Date(createdOn));
		saveOrUpdate(ioclFanslipDetail);
	}
}
