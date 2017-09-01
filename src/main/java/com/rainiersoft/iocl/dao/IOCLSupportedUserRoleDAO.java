package com.rainiersoft.iocl.dao;

import com.rainiersoft.iocl.entity.IoclSupportedUserrole;
import java.util.List;

public interface IOCLSupportedUserRoleDAO extends GenericDAO<IoclSupportedUserrole, Long>
{
	public IoclSupportedUserrole findRoleIdByRoleName(String roleName);

	public List<IoclSupportedUserrole> findAllRoleNames();
}
