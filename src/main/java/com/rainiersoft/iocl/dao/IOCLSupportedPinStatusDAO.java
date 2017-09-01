package com.rainiersoft.iocl.dao;

import com.rainiersoft.iocl.entity.IoclSupportedPinstatus;
import java.util.List;

public interface IOCLSupportedPinStatusDAO extends GenericDAO<IoclSupportedPinstatus, Long>
{
	public IoclSupportedPinstatus findPinStatusIdByPinStatus(String status);

	public List<IoclSupportedPinstatus> findAllPinStatus();
}
