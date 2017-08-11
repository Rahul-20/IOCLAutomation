package com.rainiersoft.iocl.dao;

import java.util.List;

import com.rainiersoft.iocl.entity.IoclUserPrivilegesMapping;

public interface IOCLUserPrivilegesMappingDAO  extends GenericDAO<IoclUserPrivilegesMapping,Long>  
{
	public List<IoclUserPrivilegesMapping> findPrivilegesByRole(String userRole);
}
