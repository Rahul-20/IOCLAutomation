package com.rainiersoft.iocl.dao;

import java.util.List;
import com.rainiersoft.iocl.entity.IoclSupportedQuantitystatus;

public interface IOCLSupportedQunatityStatusDAO extends GenericDAO<IoclSupportedQuantitystatus, Long>
{
	public List<IoclSupportedQuantitystatus> findAllSupportedQuantityStatus();

	public IoclSupportedQuantitystatus findStatusIdByQuantityStatus(String status);
}


