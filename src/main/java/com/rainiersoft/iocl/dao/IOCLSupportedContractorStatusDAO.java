package com.rainiersoft.iocl.dao;

import java.util.List;
import com.rainiersoft.iocl.entity.IoclSupportedContractorstatus;

public interface IOCLSupportedContractorStatusDAO extends GenericDAO<IoclSupportedContractorstatus, Long>
{
	public List<IoclSupportedContractorstatus> findAllSupportedContractorStatus();

	public IoclSupportedContractorstatus findStatusIdByContractorStatus(String status);
}



