package com.rainiersoft.iocl.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.rainiersoft.iocl.dao.IOCLSupportedLocationStatusDAO;
import com.rainiersoft.iocl.entity.IoclLocationDetail;
import com.rainiersoft.iocl.entity.IoclSupportedLocationstatus;
import com.rainiersoft.iocl.exception.IOCLWSException;
import com.rainiersoft.iocl.util.ErrorMessageConstants;
import com.rainiersoft.response.dto.GetLocationStaticDataResponseBean;
import com.rainiersoft.response.dto.LocationCreationResponseBean;
import com.rainiersoft.response.dto.LocationDeletionResponseBean;
import com.rainiersoft.response.dto.LocationDetailsResponseBean;

@Service
@Singleton
public class LocationManagementServices 
{
	private static final Logger LOG = LoggerFactory.getLogger(LocationManagementServices.class);

	@Autowired
	IOCLLocationDetailsDAO iOCLLocationDetailsDAO;

	@Autowired
	IOCLSupportedLocationStatusDAO iOCLSupportedLocationStatusDAO;

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=IOCLWSException.class)
	public Response getLocationDetails() throws IOCLWSException
	{
		try
		{
			List<LocationDetailsResponseBean> listLocationDetailsResponseBean=new ArrayList<LocationDetailsResponseBean>();
			List<IoclLocationDetail> lIoclLocationDetails=iOCLLocationDetailsDAO.findAllLocationCodes();
			for(IoclLocationDetail ioclLocationDetail:lIoclLocationDetails)
			{
				LocationDetailsResponseBean locationDetailsResponseBean=new LocationDetailsResponseBean();
				locationDetailsResponseBean.setLocationId(ioclLocationDetail.getLocationID());
				locationDetailsResponseBean.setLocationAddress(ioclLocationDetail.getLocationAddress());
				locationDetailsResponseBean.setLocationCode(ioclLocationDetail.getLocationCode());
				locationDetailsResponseBean.setLocationName(ioclLocationDetail.getLocationName());
				locationDetailsResponseBean.setOperationalStatus(ioclLocationDetail.getIoclSupportedLocationstatus().getLocationStatus());
				listLocationDetailsResponseBean.add(locationDetailsResponseBean);
			}
			return Response.status(Response.Status.OK).entity(listLocationDetailsResponseBean).build();
		}
		catch(Exception exception)
		{
			LOG.info("Exception Occured in Bay Creation:::::::::"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}

	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=IOCLWSException.class)
	public Response addLocation(String locationName,String locationCode,String locationStatus,String locationAddress) throws IOCLWSException
	{
		LocationCreationResponseBean locationCreationResponseBean=new LocationCreationResponseBean();
		try
		{
			IoclLocationDetail ioclLocationDetail=iOCLLocationDetailsDAO.findLocationByLocationName(locationName);
			LOG.info("IoclLocationDetail:::::::"+ioclLocationDetail);
			if(ioclLocationDetail!=null)
			{
				LOG.info("ioclLocationDetail Already Exist!!!!!!!");
				throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.LocationName_Already_Exist_Msg);
			}
			IoclLocationDetail ioclLocationDetailCode=iOCLLocationDetailsDAO.findLocationIdByLocationCode(locationCode);
			if(ioclLocationDetailCode!=null)
			{
				LOG.info("ioclLocationDetailCode Already Exist!!!!!!!");
				throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.LocationCode_Already_Exist_Msg);
			}
			else
			{
				IoclSupportedLocationstatus ioclSupportedLocationstatus=iOCLSupportedLocationStatusDAO.findStatusIdByLocationStatus(locationStatus);
				LOG.info("Before runnning insert statement");
				if(null!=ioclSupportedLocationstatus)
				{
					Long locationId=iOCLLocationDetailsDAO.insertLocationDetails(locationName, locationCode, ioclSupportedLocationstatus, locationAddress);
					locationCreationResponseBean.setLocationAddress(locationAddress);
					locationCreationResponseBean.setLocationCode(locationCode);
					locationCreationResponseBean.setLocationId(locationId);
					locationCreationResponseBean.setLocationName(locationName);
					locationCreationResponseBean.setMessage("Location SuccessFully Created : "+locationName);
					locationCreationResponseBean.setSuccessFlag(true);
				}
			}
		}
		catch(IOCLWSException ioclexception)
		{
			throw ioclexception;
		}
		catch(Exception exception)
		{
			LOG.info("Exception Occured in Bay Creation:::::::::"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
		return Response.status(Response.Status.OK).entity(locationCreationResponseBean).build();	
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=IOCLWSException.class)
	public Response updateLocation(String locationName,String locationCode,String locationStatus,String locationAddress,int locationId,boolean locationNameEditFlag,boolean locationCodeEditFlag) throws IOCLWSException
	{
		LocationCreationResponseBean locationCreationResponseBean=new LocationCreationResponseBean();
		try
		{
			if(locationNameEditFlag)
			{
				IoclLocationDetail ioclLocationDetail=iOCLLocationDetailsDAO.findLocationByLocationName(locationName);
				LOG.info("IoclLocationDetail:::::::"+ioclLocationDetail);
				if(ioclLocationDetail!=null)
				{
					LOG.info("ioclLocationDetail Already Exist!!!!!!!");
					throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.LocationName_Already_Exist_Msg);
				}
			}
			if(locationCodeEditFlag)
			{
				IoclLocationDetail ioclLocationDetailCode=iOCLLocationDetailsDAO.findLocationIdByLocationCode(locationCode);
				if(ioclLocationDetailCode!=null)
				{
					LOG.info("ioclLocationDetailCode Already Exist!!!!!!!");
					throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.LocationCode_Already_Exist_Msg);
				}
			}

			IoclSupportedLocationstatus ioclSupportedLocationstatus=iOCLSupportedLocationStatusDAO.findStatusIdByLocationStatus(locationStatus);
			LOG.info("Before runnning insert statement");
			if(null!=ioclSupportedLocationstatus)
			{
				IoclLocationDetail ioclLocationDetail=iOCLLocationDetailsDAO.findLocationByLocationId(locationId);
				iOCLLocationDetailsDAO.updateLocationDetails(locationName, locationCode, ioclSupportedLocationstatus, locationAddress,locationId,ioclLocationDetail);
				locationCreationResponseBean.setLocationAddress(locationAddress);
				locationCreationResponseBean.setLocationCode(locationCode);
				locationCreationResponseBean.setLocationId(Long.valueOf(locationId));
				locationCreationResponseBean.setLocationName(locationName);
				locationCreationResponseBean.setMessage("Location SuccessFully Updated : "+locationName);
				locationCreationResponseBean.setSuccessFlag(true);
			}

		}
		catch(IOCLWSException ioclexception)
		{
			throw ioclexception;
		}
		catch(Exception exception)
		{
			LOG.info("Exception Occured in Bay Creation:::::::::"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
		return Response.status(Response.Status.OK).entity(locationCreationResponseBean).build();	
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,readOnly=false,rollbackFor=IOCLWSException.class)
	public Response deleteLocation(int locationID) throws IOCLWSException
	{
		try
		{
			LocationDeletionResponseBean  locationDeletionResponseBean=new LocationDeletionResponseBean();
			boolean deleteFalg=iOCLLocationDetailsDAO.deleteLocation(locationID);
			if(deleteFalg)
			{
				locationDeletionResponseBean.setSuccessFlag(true);
				locationDeletionResponseBean.setMessage("Location Deleted SuccessFully");
				return  Response.status(Response.Status.OK).entity(locationDeletionResponseBean).build();	
			}
			else
			{
				locationDeletionResponseBean.setSuccessFlag(false);
				locationDeletionResponseBean.setMessage("Failed to delete bay");
				return  Response.status(Response.Status.OK).entity(locationDeletionResponseBean).build();
			}
		}catch (Exception exception) {
			LOG.info("Exception Occured in Bay Updation:::::::::"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,readOnly=false,rollbackFor=IOCLWSException.class)
	public Response getData() throws IOCLWSException
	{
		try
		{
			GetLocationStaticDataResponseBean getLocationStaticDataResponseBean=new GetLocationStaticDataResponseBean();

			Map<String,List<String>> data=new HashMap<String,List<String>>();

			List<String> supportedStatus=new ArrayList<String>();
			List<IoclSupportedLocationstatus> lIoclSupportedLocationstatus=iOCLSupportedLocationStatusDAO.findAllSupportedLocationStatus();
			for(IoclSupportedLocationstatus ioclSupportedLocationstatus:lIoclSupportedLocationstatus)
			{
				supportedStatus.add(ioclSupportedLocationstatus.getLocationStatus());
			}
			data.put("LocationStatus",supportedStatus);
			getLocationStaticDataResponseBean.setData(data);
			return  Response.status(Response.Status.OK).entity(getLocationStaticDataResponseBean).build();
		}catch (Exception exception) {
			LOG.info("Exception Occured in Bay Updation:::::::::"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}

}