package com.rainiersoft.iocl.dao;

import com.rainiersoft.iocl.entity.IoclLocationDetail;
import java.util.List;

public interface IOCLLocationDetailsDAO extends GenericDAO<IoclLocationDetail, Long>
{
	public IoclLocationDetail findLocationIdByLocationCode(String locationCode);

	public List<IoclLocationDetail> findAllLocationCodes();
}
