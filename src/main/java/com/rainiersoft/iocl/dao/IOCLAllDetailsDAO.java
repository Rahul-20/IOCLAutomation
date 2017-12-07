package com.rainiersoft.iocl.dao;

import java.util.Date;
import java.util.List;

import com.rainiersoft.iocl.entity.IoclAlldetail;

public interface IOCLAllDetailsDAO extends GenericDAO<IoclAlldetail, Long>
{
	public List<IoclAlldetail> findAllDetails(int pageNumber,int pageSize,Date startDate,Date endDate,String bayNum);
	
	public List<IoclAlldetail> findAllDetailsByStartDateAndEndDate(Date startDate,Date endDate,String bayNum);
	
	public String findSumOfLoadedByStartDateAndEndDate(Date startDate,Date endDate,String bayNum);
	
	public Long findTotalNumberOfRecords(Date startDate,Date endDate,String bayNum);
	
	public List<IoclAlldetail> findTotalizerDetails(int pageNumber,int pageSize,Date startDate,Date endDate,String bayNum);
}
