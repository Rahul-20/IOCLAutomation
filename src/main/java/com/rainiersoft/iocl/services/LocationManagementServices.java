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

import com.rainiersoft.iocl.dao.IOCLLocationDetailsDAO;
import com.rainiersoft.iocl.entity.IoclLocationDetail;
import com.rainiersoft.response.dto.LocationDetailsResponseBean;

@Service
@Singleton
public class LocationManagementServices 
{
	private static final Logger LOG = LoggerFactory.getLogger(LocationManagementServices.class);
	
	@Autowired
	IOCLLocationDetailsDAO iOCLLocationDetailsDAO;
		
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public Response getLocationDetails()
	{
		List<LocationDetailsResponseBean> listLocationDetailsResponseBean=new ArrayList<LocationDetailsResponseBean>();
		List<IoclLocationDetail> lIoclLocationDetails=iOCLLocationDetailsDAO.findAllLocationCodes();
		for(IoclLocationDetail ioclLocationDetail:lIoclLocationDetails)
		{
			LocationDetailsResponseBean locationDetailsResponseBean=new LocationDetailsResponseBean();
			locationDetailsResponseBean.setLocationAddress(ioclLocationDetail.getLocationAddress());
			locationDetailsResponseBean.setLocationCode(ioclLocationDetail.getLocationCode());
			locationDetailsResponseBean.setLocationName(ioclLocationDetail.getLocationName());
			locationDetailsResponseBean.setOperationalStatus(ioclLocationDetail.getOperationalStatus());
			listLocationDetailsResponseBean.add(locationDetailsResponseBean);
		}
		return Response.status(Response.Status.OK).entity(listLocationDetailsResponseBean).build();
	}
}
