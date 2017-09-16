package com.rainiersoft.iocl.dao.impl;

import java.util.List;

import javax.inject.Singleton;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.rainiersoft.iocl.dao.IOCLContractorDetailsDAO;
import com.rainiersoft.iocl.entity.IoclContractorDetail;
import com.rainiersoft.iocl.entity.IoclContractortypeDetail;
import com.rainiersoft.iocl.entity.IoclStatesDetail;
import com.rainiersoft.iocl.entity.IoclSupportedContractorstatus;

@Repository
@Singleton
public class IOCLContractorDetailsDAOImpl extends GenericDAOImpl<IoclContractorDetail, Long> implements IOCLContractorDetailsDAO 
{
	@Override
	public List<IoclContractorDetail> findAllContractors()
	{
		List<IoclContractorDetail> listIoclContractorDetail = findAll(IoclContractorDetail.class);
		return listIoclContractorDetail;
	}

	@Override
	public Long insertContractorDetails(String contractorName, IoclContractortypeDetail contractorType, String contractorAddress,
			String contractorCity, IoclSupportedContractorstatus contractorOperationalStatus, String contractorPinCode,
			IoclStatesDetail contractorState) {
		Session session=getCurrentSession();
		IoclContractorDetail ioclContractorDetail=new IoclContractorDetail();
		ioclContractorDetail.setContractorAddress(contractorAddress);
		ioclContractorDetail.setIoclContractortypeDetail(contractorType);
		ioclContractorDetail.setContractorCity(contractorCity);
		ioclContractorDetail.setContractorName(contractorName);
		ioclContractorDetail.setIoclStatesDetail(contractorState);
		ioclContractorDetail.setIoclSupportedContractorstatus(contractorOperationalStatus);
		ioclContractorDetail.setZipCode(contractorPinCode);
		Integer contractorId=(Integer) session.save(ioclContractorDetail);
		return contractorId.longValue();
	}

	@Override
	public void updateContractorDetails(String contractorName, IoclContractortypeDetail contractorType, String contractorAddress,
			String contractorCity, IoclSupportedContractorstatus contractorOperationalStatus, String contractorPinCode, IoclStatesDetail contractorState,
			IoclContractorDetail ioclContractorDetail) {
		Session session=getCurrentSession();
		ioclContractorDetail.setContractorAddress(contractorAddress);
		ioclContractorDetail.setContractorCity(contractorCity);
		ioclContractorDetail.setContractorName(contractorName);
		ioclContractorDetail.setIoclStatesDetail(contractorState);
		ioclContractorDetail.setIoclContractortypeDetail(contractorType);
		ioclContractorDetail.setIoclSupportedContractorstatus(contractorOperationalStatus);
		ioclContractorDetail.setZipCode(contractorPinCode);
		session.update(ioclContractorDetail);
	}

	@Override
	public IoclContractorDetail findContractorByContractorName(String contractorName) 
	{
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findContractorByContractorName");
		query.setParameter("contractorName",contractorName);
		IoclContractorDetail ioclContractorDetail = (IoclContractorDetail)findObject(query);
		return ioclContractorDetail;
	}

	@Override
	public IoclContractorDetail findContractorByContractorId(int contractorId) 
	{
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findContractorByContractorId");
		query.setParameter("contractorId",contractorId);
		IoclContractorDetail ioclContractorDetail = (IoclContractorDetail)findObject(query);
		return ioclContractorDetail;
	}

	@Override
	public boolean deleteContractor(int contractorId)
	{
		return deleteById(IoclContractorDetail.class, Integer.valueOf(contractorId));
	}

	@Override
	public List<IoclContractorDetail> findAllContractorTypes() 
	{
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findAllContractorTypes");
		List<IoclContractorDetail> lIoclContractorDetail = (List<IoclContractorDetail>)findObjectCollection(query);
		return lIoclContractorDetail;
	}

	@Override
	public List<IoclContractorDetail> findAllContractorNames() 
	{
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findAllContractorNames");
		List<IoclContractorDetail> lIoclContractorDetail =  (List<IoclContractorDetail>)findObjectCollection(query);
		return lIoclContractorDetail;
	}
}