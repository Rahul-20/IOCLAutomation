package com.rainiersoft.iocl.dao;

import com.rainiersoft.iocl.entity.IoclSupportedUserstatus;
import java.util.List;

public interface IOCLSupportedUserStatusDAO extends GenericDAO<IoclSupportedUserstatus, Long>
{
	public IoclSupportedUserstatus findUserStatusIdByUserStatus(String status);

	public List<IoclSupportedUserstatus> findAllUserStatus();
}
