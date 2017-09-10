package com.rainiersoft.iocl.dao;

import java.util.List;

import com.rainiersoft.iocl.entity.IoclQuantitiesDetail;
import com.rainiersoft.iocl.entity.IoclSupportedQuantitystatus;

public interface IOCLQuantityDetailsDAO extends GenericDAO<IoclQuantitiesDetail, Long> 
{
	public List<IoclQuantitiesDetail> findAllQuantites();
	
	public IoclQuantitiesDetail findQuantityByQuantityName(String quantityName);
	
	public IoclQuantitiesDetail findQuantityByQuantityId(int quantityId);
	
	public IoclQuantitiesDetail findQuantityByQunatity(String quantity);
	
	public boolean deleteQunatity(int quantityId);
	
	public Long insertQuantitiesDetails(String quantityName,String quantity,IoclSupportedQuantitystatus quantityStatus);
	
	public void updateQuantitiesDetails(String quantityName,String quantity,IoclSupportedQuantitystatus quantityStatus,IoclQuantitiesDetail ioclQuantitiesDetail);
}
