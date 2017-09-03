package com.rainiersoft.iocl.dao;

import java.util.List;
import com.rainiersoft.iocl.entity.IoclQuantitiesDetail;

public interface IOCLQuantityDetailsDAO extends GenericDAO<IoclQuantitiesDetail, Long> 
{
	public List<IoclQuantitiesDetail> findAllQuantites();
}
