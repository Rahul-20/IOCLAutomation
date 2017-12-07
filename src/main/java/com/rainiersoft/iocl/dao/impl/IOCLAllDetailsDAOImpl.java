package com.rainiersoft.iocl.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.rainiersoft.iocl.dao.IOCLAllDetailsDAO;
import com.rainiersoft.iocl.entity.IoclAlldetail;

@Repository
public class IOCLAllDetailsDAOImpl extends GenericDAOImpl<IoclAlldetail, Long> implements IOCLAllDetailsDAO
{
	@Override
	public List<IoclAlldetail> findAllDetails(int pageNumber,int pageSize,Date startDate,Date endDate,String bayNum) 
	{
		String countQ = "";
		Query query=null;
		if(bayNum.equalsIgnoreCase("ALL"))
		{
			countQ = "Select i from IoclAlldetail i where i.startTime>= :startDate and i.startTime<= :endDate and (i.operationalStatus= :statusCompleted or i.operationalStatus= :statusAborted)";
			query = getCurrentSession().createQuery(countQ);
			query.setParameter("startDate",startDate);
			query.setParameter("endDate",endDate);
			query.setParameter("statusCompleted","Completed");
			query.setParameter("statusAborted","Aborted");
		}
		else
		{
			int bayNumber=Integer.parseInt(bayNum);
			countQ = "Select i from IoclAlldetail i where i.startTime>= :startDate and i.startTime<= :endDate and i.bayNo= :bayNumber and (i.operationalStatus= :statusCompleted or i.operationalStatus= :statusAborted)";
			query = getCurrentSession().createQuery(countQ);
			query.setParameter("startDate",startDate);
			query.setParameter("endDate",endDate);
			query.setParameter("bayNumber",bayNumber);
			query.setParameter("statusCompleted","Completed");
			query.setParameter("statusAborted","Aborted");
		}
		query.setFirstResult((pageNumber-1) * pageSize);
		query.setMaxResults(pageSize);
		List<IoclAlldetail> allDetails= (List<IoclAlldetail>)query.list();
		return allDetails;
	}

	@Override
	public List<IoclAlldetail> findTotalizerDetails(int pageNumber, int pageSize, Date startDate, Date endDate,String bayNum)
	{
		String countQ = "";
		Query query=null;
		if(bayNum.equalsIgnoreCase("ALL"))
		{
			countQ = "Select i from IoclAlldetail i where i.startTime>= :startDate and i.startTime<= :endDate and (i.operationalStatus= :statusCompleted or i.operationalStatus= :statusAborted)";
			query = getCurrentSession().createQuery(countQ);
			query.setParameter("startDate",startDate);
			query.setParameter("endDate",endDate);
			query.setParameter("statusCompleted","Completed");
			query.setParameter("statusAborted","Aborted");
		}
		else
		{
			int bayNumber=Integer.parseInt(bayNum);
			countQ = "Select i from IoclAlldetail i where i.startTime>= :startDate and i.startTime<= :endDate and i.bayNo= :bayNumber and (i.operationalStatus= :statusCompleted or i.operationalStatus= :statusAborted)";
			query = getCurrentSession().createQuery(countQ);
			query.setParameter("startDate",startDate);
			query.setParameter("endDate",endDate);
			query.setParameter("bayNumber",bayNumber);
			query.setParameter("statusCompleted","Completed");
			query.setParameter("statusAborted","Aborted");
		}
		query.setFirstResult((pageNumber-1) * pageSize);
		query.setMaxResults(pageSize);
		List<IoclAlldetail> allDetails= (List<IoclAlldetail>)query.list();
		return allDetails;
	}

	@Override
	public Long findTotalNumberOfRecords(Date startDate, Date endDate,String bayNum) 
	{
		String countQ = "";
		Query query=null;
		if(bayNum.equalsIgnoreCase("ALL"))
		{
			countQ = "Select count(i.fanslipnum) from IoclAlldetail i where i.startTime>= :startDate and i.startTime<= :endDate and (i.operationalStatus= :statusCompleted or i.operationalStatus= :statusAborted)";
			query = getCurrentSession().createQuery(countQ);
			query.setParameter("startDate",startDate);
			query.setParameter("endDate",endDate);
			query.setParameter("statusCompleted","Completed");
			query.setParameter("statusAborted","Aborted");
		}
		else
		{
			int bayNumber=Integer.parseInt(bayNum);
			countQ = "Select count(i.fanslipnum) from IoclAlldetail i where i.startTime>= :startDate and i.startTime<= :endDate and i.bayNo= :bayNumber and (i.operationalStatus= :statusCompleted or i.operationalStatus= :statusAborted)";
			query = getCurrentSession().createQuery(countQ);
			query.setParameter("startDate",startDate);
			query.setParameter("endDate",endDate);
			query.setParameter("bayNumber",bayNumber);
			query.setParameter("statusCompleted","Completed");
			query.setParameter("statusAborted","Aborted");
		}
		Long countResults = (Long) query.uniqueResult();
		return countResults;
	}

	@Override
	public List<IoclAlldetail> findAllDetailsByStartDateAndEndDate(Date startDate, Date endDate,String bayNum) 
	{
		String countQ = "";
		Query query=null;
		if(bayNum.equalsIgnoreCase("ALL"))
		{
			countQ = "Select i from IoclAlldetail i where i.startTime>= :startDate and i.startTime<= :endDate and (i.operationalStatus= :statusCompleted or i.operationalStatus= :statusAborted)";
			query = getCurrentSession().createQuery(countQ);
			query.setParameter("startDate",startDate);
			query.setParameter("endDate",endDate);
			query.setParameter("statusCompleted","Completed");
			query.setParameter("statusAborted","Aborted");
		}
		else
		{
			int bayNumber=Integer.parseInt(bayNum);
			countQ = "Select i from IoclAlldetail i where i.startTime>= :startDate and i.startTime<= :endDate and i.bayNo= :bayNumber and (i.operationalStatus= :statusCompleted or i.operationalStatus= :statusAborted)";
			query = getCurrentSession().createQuery(countQ);
			query.setParameter("startDate",startDate);
			query.setParameter("endDate",endDate);
			query.setParameter("bayNumber",bayNumber);
			query.setParameter("statusCompleted","Completed");
			query.setParameter("statusAborted","Aborted");
		}
		List<IoclAlldetail> allDetails= (List<IoclAlldetail>)query.list();
		System.out.println(allDetails);
		return allDetails;
	}

	@Override
	public String findSumOfLoadedByStartDateAndEndDate(Date startDate, Date endDate, String bayNum) 
	{
		String countQ = "";
		Query query=null;
		if(bayNum.equalsIgnoreCase("ALL"))
		{
			countQ = "Select sum(i.loadedQuantity) from IoclAlldetail i where i.startTime>= :startDate and i.startTime<= :endDate and (i.operationalStatus= :statusCompleted or i.operationalStatus= :statusAborted)";
			query = getCurrentSession().createQuery(countQ);
			query.setParameter("startDate",startDate);
			query.setParameter("endDate",endDate);
			query.setParameter("statusCompleted","Completed");
			query.setParameter("statusAborted","Aborted");
		}
		else
		{
			int bayNumber=Integer.parseInt(bayNum);
			countQ = "Select sum(i.loadedQuantity) from IoclAlldetail i where i.startTime>= :startDate and i.startTime<= :endDate and i.bayNo= :bayNumber and (i.operationalStatus= :statusCompleted or i.operationalStatus= :statusAborted)";
			query = getCurrentSession().createQuery(countQ);
			query.setParameter("startDate",startDate);
			query.setParameter("endDate",endDate);
			query.setParameter("bayNumber",bayNumber);
			query.setParameter("statusCompleted","Completed");
			query.setParameter("statusAborted","Aborted");
		}
		String sumVal=(String) query.list().get(0);
		System.out.println("sumVal........."+sumVal);
		return sumVal;
	}
}