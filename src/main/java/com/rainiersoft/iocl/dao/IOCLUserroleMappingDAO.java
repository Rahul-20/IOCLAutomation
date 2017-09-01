package com.rainiersoft.iocl.dao;

import com.rainiersoft.iocl.entity.IoclUserroleMapping;
import java.util.List;

public interface IOCLUserroleMappingDAO extends GenericDAO<IoclUserroleMapping, Long>
{
	public List<IoclUserroleMapping> findRolesByUserID(String userId);
}
