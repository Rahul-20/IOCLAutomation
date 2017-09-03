package com.rainiersoft.iocl.dao.impl;

import java.util.List;

import javax.inject.Singleton;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.rainiersoft.iocl.dao.IOCLContractorDetailsDAO;
import com.rainiersoft.iocl.entity.IoclContractorDetail;

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
	public Long insertContractorDetails(String contractorName, String contractorType, String contractorAddress,
			String contractorCity, String contractorOperationalStatus, String contractorPinCode,
			String contractorState) {
		Session session=getCurrentSession();
		IoclContractorDetail ioclContractorDetail=new IoclContractorDetail();
		ioclContractorDetail.setContractorAddress(contractorAddress);
		ioclContractorDetail.setContractorCity(contractorCity);
		//ioclContractorDetail.setContractorId(contractorId);
		ioclContractorDetail.setContractorName(contractorName);
		ioclContractorDetail.setContractorState(contractorState);
		ioclContractorDetail.setOperationalStatus(contractorOperationalStatus);
		ioclContractorDetail.setZipCode(contractorPinCode);
		Integer contractorId=(Integer) session.save(ioclContractorDetail);
		return contractorId.longValue();
	}
}