package com.rainiersoft.iocl.dao;

import java.util.List;
import com.rainiersoft.iocl.entity.IoclContractorDetail;

public interface IOCLContractorDetailsDAO extends GenericDAO<IoclContractorDetail, Long> 
{
	public List<IoclContractorDetail> findAllContractors();
	public Long insertContractorDetails(String contractorName,String contractorType,String contractorAddress,String contractorCity,String contractorOperationalStatus,String contractorPinCode,String contractorState);
}

