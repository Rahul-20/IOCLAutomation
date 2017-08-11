package com.rainiersoft.iocl.dao;

import java.util.List;

import com.rainiersoft.iocl.entity.IoclUserroleMapping;

public interface IOCLUserroleMappingDAO extends GenericDAO<IoclUserroleMapping,Long>
{
	public List<IoclUserroleMapping> findRolesByUserID(String userId);
}
