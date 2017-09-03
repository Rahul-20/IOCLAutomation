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

import com.rainiersoft.iocl.dao.IOCLQuantityDetailsDAO;
import com.rainiersoft.iocl.entity.IoclQuantitiesDetail;
import com.rainiersoft.response.dto.QuantityDetailsResponseBean;

@Service
@Singleton
public class QuantitiesManagementServices 
{
	private static final Logger LOG = LoggerFactory.getLogger(QuantitiesManagementServices.class);
	
	@Autowired
	IOCLQuantityDetailsDAO ioclQuantityDetailsDAO;
	
	
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public Response getQuantityDetails()
	{
		List<QuantityDetailsResponseBean> listQuantityDetailsResponseBean=new ArrayList<QuantityDetailsResponseBean>();
		List<IoclQuantitiesDetail> lIoclQuantitiesDetail=ioclQuantityDetailsDAO.findAllQuantites();
		for(IoclQuantitiesDetail ioclQuantitiesDetail:lIoclQuantitiesDetail)
		{
			QuantityDetailsResponseBean quantityDetailsResponseBean=new QuantityDetailsResponseBean();
			quantityDetailsResponseBean.setOperationalStatus(ioclQuantitiesDetail.getOperationalStatus());
			quantityDetailsResponseBean.setQuantity(ioclQuantitiesDetail.getQuantity());
			quantityDetailsResponseBean.setQuantityName(ioclQuantitiesDetail.getQuantityName());
			quantityDetailsResponseBean.setQuantityUnit(ioclQuantitiesDetail.getQuantityUnits());
			listQuantityDetailsResponseBean.add(quantityDetailsResponseBean);
		}
		return Response.status(Response.Status.OK).entity(listQuantityDetailsResponseBean).build();
	}

}
