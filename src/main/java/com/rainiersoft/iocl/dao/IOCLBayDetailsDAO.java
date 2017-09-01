package com.rainiersoft.iocl.dao;

import com.rainiersoft.iocl.entity.IoclBayDetail;
import com.rainiersoft.iocl.entity.IoclSupportedBaystatus;
import java.util.List;

public interface IOCLBayDetailsDAO extends GenericDAO<IoclBayDetail, Long>
{
	public List<IoclBayDetail> findAllAvailableBaysInApplication();

	public void insertBayDetails(String bayName, int bayNum, String bayType, IoclSupportedBaystatus ioclSupportedBaystatus);

	public IoclBayDetail findBayByBayNum(int bayNum);

	public boolean deleteBay(int userId);
}
