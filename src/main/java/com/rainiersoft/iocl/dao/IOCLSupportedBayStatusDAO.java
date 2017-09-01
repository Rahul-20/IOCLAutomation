package com.rainiersoft.iocl.dao;

import com.rainiersoft.iocl.entity.IoclSupportedBaystatus;
import java.util.List;

public interface IOCLSupportedBayStatusDAO extends GenericDAO<IoclSupportedBaystatus, Long>
{
	public List<IoclSupportedBaystatus> findAllSupportedBayStatus();

	public IoclSupportedBaystatus findStatusIdByStatus(String status);
}
