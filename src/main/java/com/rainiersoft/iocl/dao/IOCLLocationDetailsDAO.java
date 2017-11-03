package com.rainiersoft.iocl.dao;

import java.util.Date;
import java.util.List;

import com.rainiersoft.iocl.entity.IoclLocationDetail;
import com.rainiersoft.iocl.entity.IoclStatesDetail;
import com.rainiersoft.iocl.entity.IoclSupportedLocationstatus;

public interface IOCLLocationDetailsDAO extends GenericDAO<IoclLocationDetail, Long>
{
	public IoclLocationDetail findLocationIdByLocationCode(String locationCode);
	
	public IoclLocationDetail findLocationByLocationName(String locationName);
	
	public IoclLocationDetail findLocationByLocationId(int locationId);
	
	public List<IoclLocationDetail> findAllLocationCodes();
	
	public Long insertLocationDetails(String locationName,String locationCode,IoclSupportedLocationstatus locationStatus,String locationAddress,String city,String pinCode,IoclStatesDetail ioclStatesDetail,int userID,Date locationCreateddateobj);
	
	public void updateLocationDetails(String locationName,String locationCode,IoclSupportedLocationstatus locationStatus,String locationAddress,int locationId,IoclLocationDetail ioclLocationDetail,String city,String pinCode,IoclStatesDetail ioclStatesDetail,int userID,Date locationCreateddateobj);
	
	public boolean deleteLocation(int locationId);
}
