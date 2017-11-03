package com.rainiersoft.iocl.dao;

import java.util.Date;
import java.util.List;

import com.rainiersoft.iocl.entity.IoclContractorDetail;
import com.rainiersoft.iocl.entity.IoclContractortypeDetail;
import com.rainiersoft.iocl.entity.IoclStatesDetail;
import com.rainiersoft.iocl.entity.IoclSupportedContractorstatus;

public interface IOCLContractorDetailsDAO extends GenericDAO<IoclContractorDetail, Long> 
{
	public List<IoclContractorDetail> findAllContractors();
	
	public IoclContractorDetail findContractorByContractorName(String contractorName);
	
	public IoclContractorDetail findContractorByContractorId(int contractorId);
	
	public Long insertContractorDetails(String contractorName,IoclContractortypeDetail contractorType,String contractorAddress,String contractorCity,IoclSupportedContractorstatus contractorOperationalStatus,String contractorPinCode,IoclStatesDetail contractorState,int userID,Date contractorCreatedOn);
	
	public void updateContractorDetails(String contractorName,IoclContractortypeDetail contractorType,String contractorAddress,String contractorCity,IoclSupportedContractorstatus contractorOperationalStatus,String contractorPinCode,IoclStatesDetail contractorState,IoclContractorDetail ioclContractorDetail,int userID,Date contractorUpdatedOn);
	
	public boolean deleteContractor(int contractorId);
	
	public List<IoclContractorDetail> findAllContractorTypes();
	
	public List<IoclContractorDetail> findAllContractorNames();
}