package com.rainiersoft.iocl.dao;

import com.rainiersoft.iocl.entity.IoclBayDetail;
import com.rainiersoft.iocl.entity.IoclBayType;
import com.rainiersoft.iocl.entity.IoclSupportedBaystatus;
import java.util.List;

public interface IOCLBayDetailsDAO extends GenericDAO<IoclBayDetail, Long>
{
	public List<IoclBayDetail> findAllAvailableBaysInApplication();

	public Long insertBayDetails(String bayName, int bayNum, int bayType, IoclSupportedBaystatus ioclSupportedBaystatus);

	public IoclBayDetail findBayByBayNum(int bayNum);
	
	public IoclBayDetail findBayByBayName(String bayName);
	
	public IoclBayDetail findBayByBayId(int bayNum);

	public boolean deleteBay(int userId);
	
	public void updateBayDetails(String bayName,int bayNum,List<IoclBayType> listIoclBayType, IoclSupportedBaystatus ioclSupportedBaystatus,IoclBayDetail ioclBayDetail);
}
