package com.rainiersoft.iocl.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rainiersoft.iocl.dao.IOCLContractorDetailsDAO;
import com.rainiersoft.iocl.entity.IoclContractorDetail;
import com.rainiersoft.response.dto.ContractorDetailsResponseBean;

@Service
@Singleton
public class ContractorsManagementServices
{
	private static final Logger LOG = LoggerFactory.getLogger(ContractorsManagementServices.class);

	@Autowired
	IOCLContractorDetailsDAO iOCLContractorDetailsDAO;

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public Response getContractorDetails()
	{
		List<ContractorDetailsResponseBean> listContractorDetailsResponseBean=new ArrayList<ContractorDetailsResponseBean>();
		List<IoclContractorDetail> lIoclContractorDetail=iOCLContractorDetailsDAO.findAllContractors();
		for(IoclContractorDetail ioclContractorDetail:lIoclContractorDetail)
		{
			ContractorDetailsResponseBean contractorDetailsResponseBean=new ContractorDetailsResponseBean();
			contractorDetailsResponseBean.setContractorAddress(ioclContractorDetail.getContractorAddress());
			contractorDetailsResponseBean.setContractorCity(ioclContractorDetail.getContractorCity());
			contractorDetailsResponseBean.setContractorName(ioclContractorDetail.getContractorName());
			contractorDetailsResponseBean.setContractorOperationalStatus(ioclContractorDetail.getOperationalStatus());
			contractorDetailsResponseBean.setContractorPinCode(ioclContractorDetail.getZipCode());
			contractorDetailsResponseBean.setContractorState(ioclContractorDetail.getContractorState());
			contractorDetailsResponseBean.setContractorType(ioclContractorDetail.getContractorType());
			listContractorDetailsResponseBean.add(contractorDetailsResponseBean);
		}
		return Response.status(Response.Status.OK).entity(listContractorDetailsResponseBean).build();
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public Response addContractor(String contractorName,String contractorType,String contractorAddress,String contractorCity,String contractorOperationalStatus,String contractorPinCode,String contractorState)
	{
		return null;
	}
}
