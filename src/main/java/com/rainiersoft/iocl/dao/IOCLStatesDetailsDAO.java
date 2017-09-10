package com.rainiersoft.iocl.dao;

import java.util.List;

import com.rainiersoft.iocl.entity.IoclStatesDetail;

public interface IOCLStatesDetailsDAO extends GenericDAO<IoclStatesDetail, Long> 
{
	public List<IoclStatesDetail> findAllStates();

	public IoclStatesDetail findStateIdByStateName(String stateName);
}
