package com.rainiersoft.iocl.dao;

import java.util.List;

import com.rainiersoft.iocl.entity.IoclContractortypeDetail;

public interface IOCLContractorTypeDAO extends GenericDAO<IoclContractortypeDetail, Long> 
{
	public List<IoclContractortypeDetail> findAllContractorTypes();

	public IoclContractortypeDetail findContractorIdByContractorType(String contractorType);
}