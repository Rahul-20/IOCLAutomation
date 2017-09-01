package com.rainiersoft.iocl.dao;

import com.rainiersoft.iocl.entity.IoclBcBayoperation;
import java.util.Date;
import java.util.List;

public interface IOCLBCBayOperationsDAO extends GenericDAO<IoclBcBayoperation, Long>
{
	public List<IoclBcBayoperation> findBayUpdatesByBC(int bayNo, Date currDate, Date pastDate);

	public List<IoclBcBayoperation> findBayUpdatesByFanPin(String fanPin);
}
