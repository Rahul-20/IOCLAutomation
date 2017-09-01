package com.rainiersoft.iocl.dao;

import com.rainiersoft.iocl.entity.IoclSupportedBaytype;
import java.util.List;

public interface IOCLSupportedBayTypesDAO extends GenericDAO<IoclSupportedBaytype, Long>
{
	public List<IoclSupportedBaytype> findAllSupportedBayTypes();
}
