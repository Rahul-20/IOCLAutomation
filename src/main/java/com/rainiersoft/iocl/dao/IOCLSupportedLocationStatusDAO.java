
package com.rainiersoft.iocl.dao;

import java.util.List;
import com.rainiersoft.iocl.entity.IoclSupportedLocationstatus;

public interface IOCLSupportedLocationStatusDAO extends GenericDAO<IoclSupportedLocationstatus, Long>
{
	public List<IoclSupportedLocationstatus> findAllSupportedLocationStatus();

	public IoclSupportedLocationstatus findStatusIdByLocationStatus(String status);
}




