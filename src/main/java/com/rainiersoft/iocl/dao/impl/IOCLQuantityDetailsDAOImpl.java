package com.rainiersoft.iocl.dao.impl;

import java.util.List;

import javax.inject.Singleton;

import org.springframework.stereotype.Repository;

import com.rainiersoft.iocl.dao.IOCLQuantityDetailsDAO;
import com.rainiersoft.iocl.entity.IoclQuantitiesDetail;

@Repository
@Singleton
public class IOCLQuantityDetailsDAOImpl extends GenericDAOImpl<IoclQuantitiesDetail, Long> implements IOCLQuantityDetailsDAO 
{
	@Override
	public List<IoclQuantitiesDetail> findAllQuantites() 
	{
		List<IoclQuantitiesDetail> listOfIoclQuantitiesDetail = findAll(IoclQuantitiesDetail.class);
		return listOfIoclQuantitiesDetail;
	}
}
