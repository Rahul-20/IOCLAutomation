package com.rainiersoft.iocl.dao;

import com.rainiersoft.iocl.entity.IoclUserPrivilegesMapping;
import java.util.List;

public interface IOCLUserPrivilegesMappingDAO extends GenericDAO<IoclUserPrivilegesMapping, Long>
{
	public List<IoclUserPrivilegesMapping> findPrivilegesByRole(int roleId);
}
