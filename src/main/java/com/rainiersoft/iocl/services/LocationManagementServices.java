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
import com.rainiersoft.iocl.dao.IOCLStatesDetailsDAO;
import com.rainiersoft.iocl.dao.IOCLSupportedLocationStatusDAO;
import com.rainiersoft.iocl.entity.IoclLocationDetail;
import com.rainiersoft.iocl.entity.IoclStatesDetail;
import com.rainiersoft.iocl.entity.IoclSupportedLocationstatus;
import com.rainiersoft.iocl.exception.IOCLWSException;
import com.rainiersoft.iocl.util.ErrorMessageConstants;
import com.rainiersoft.response.dto.GetLocationStaticDataResponseBean;
import com.rainiersoft.response.dto.LocationCreationResponseBean;
import com.rainiersoft.response.dto.LocationDeletionResponseBean;
import com.rainiersoft.response.dto.LocationDetailsResponseBean;


/**
 * This is the class for LocationManagementServices
 * @author RahulKumarPamidi
 */

@Service
@Singleton
public class LocationManagementServices 
{
	private static final Logger LOG = LoggerFactory.getLogger(LocationManagementServices.class);

	@Autowired
	IOCLLocationDetailsDAO iOCLLocationDetailsDAO;

	@Autowired
	IOCLSupportedLocationStatusDAO iOCLSupportedLocationStatusDAO;
	
	@Autowired
	IOCLStatesDetailsDAO iOCLStatesDetailsDAO;

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=IOCLWSException.class)
	public Response getLocationDetails() throws IOCLWSException
	{
		try
		{
			LOG.info("Entered into getLocationDetails service class method........");
			List<LocationDetailsResponseBean> listLocationDetailsResponseBean=new ArrayList<LocationDetailsResponseBean>();
			List<IoclLocationDetail> lIoclLocationDetails=iOCLLocationDetailsDAO.findAllLocationCodes();
			for(IoclLocationDetail ioclLocationDetail:lIoclLocationDetails)
			{
				LocationDetailsResponseBean locationDetailsResponseBean=new LocationDetailsResponseBean();
				locationDetailsResponseBean.setLocationId(ioclLocationDetail.getLocationID());
				locationDetailsResponseBean.setLocationAddress(ioclLocationDetail.getLocationAddress());
				locationDetailsResponseBean.setLocationCode(ioclLocationDetail.getLocationCode());
				locationDetailsResponseBean.setLocationName(ioclLocationDetail.getLocationName());
				locationDetailsResponseBean.setPinCode(ioclLocationDetail.getPinCode());
				locationDetailsResponseBean.setState(ioclLocationDetail.getIoclStatesDetail().getStateName());
				locationDetailsResponseBean.setCity(ioclLocationDetail.getCity());
				locationDetailsResponseBean.setOperationalStatus(ioclLocationDetail.getIoclSupportedLocationstatus().getLocationStatus());
				listLocationDetailsResponseBean.add(locationDetailsResponseBean);
			}
			return Response.status(Response.Status.OK).entity(listLocationDetailsResponseBean).build();
		}
		catch(Exception exception)
		{
			LOG.info("Logging the occured exception in the service class getLocationDetails method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}

	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=IOCLWSException.class)
	public Response addLocation(String locationName,String locationCode,String locationStatus,String locationAddress,String city,String pinCode,String state) throws IOCLWSException
	{
		LOG.info("Entered into addLocation service class method........");
		LocationCreationResponseBean locationCreationResponseBean=new LocationCreationResponseBean();
		try
		{
			IoclLocationDetail ioclLocationDetail=iOCLLocationDetailsDAO.findLocationByLocationName(locationName);
			LOG.info("IoclLocationDetail:::::::"+ioclLocationDetail);
			if(ioclLocationDetail!=null)
			{
				throw new IOCLWSException(Response.Status.CONFLICT.getStatusCode(),ErrorMessageConstants.LocationName_Already_Exist_Msg);
			}
			IoclLocationDetail ioclLocationDetailCode=iOCLLocationDetailsDAO.findLocationIdByLocationCode(locationCode);
			if(ioclLocationDetailCode!=null)
			{
				throw new IOCLWSException(Response.Status.CONFLICT.getStatusCode(),ErrorMessageConstants.LocationCode_Already_Exist_Msg);
			}
			else
			{
				IoclSupportedLocationstatus ioclSupportedLocationstatus=iOCLSupportedLocationStatusDAO.findStatusIdByLocationStatus(locationStatus);
				IoclStatesDetail ioclStatesDetail=iOCLStatesDetailsDAO.findStateIdByStateName(state);
				if(null!=ioclSupportedLocationstatus && null!=ioclStatesDetail)
				{
					Long locationId=iOCLLocationDetailsDAO.insertLocationDetails(locationName, locationCode, ioclSupportedLocationstatus, locationAddress,city,pinCode,ioclStatesDetail);
					locationCreationResponseBean.setLocationAddress(locationAddress);
					locationCreationResponseBean.setLocationCode(locationCode);
					locationCreationResponseBean.setLocationId(locationId);
					locationCreationResponseBean.setLocationName(locationName);
					locationCreationResponseBean.setCity(city);
					locationCreationResponseBean.setPinCode(pinCode);
					locationCreationResponseBean.setState(state);
					locationCreationResponseBean.setMessage("Location SuccessFully Created : "+locationName);
					locationCreationResponseBean.setSuccessFlag(true);
				}
			}
		}
		catch(IOCLWSException ioclexception)
		{
			LOG.info("Logging the occured exception in the service class addLocation method custom catch block........"+ioclexception);
			throw ioclexception;
		}
		catch(Exception exception)
		{
			LOG.info("Logging the occured exception in the service class addLocation method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
		return Response.status(Response.Status.OK).entity(locationCreationResponseBean).build();	
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=IOCLWSException.class)
	public Response updateLocation(String locationName,String locationCode,String locationStatus,String locationAddress,int locationId,boolean locationNameEditFlag,boolean locationCodeEditFlag,String city,String pinCode,String state) throws IOCLWSException
	{
		LOG.info("Entered into updateLocation service class method........");
		LocationCreationResponseBean locationCreationResponseBean=new LocationCreationResponseBean();
		try
		{
			if(locationNameEditFlag)
			{
				IoclLocationDetail ioclLocationDetail=iOCLLocationDetailsDAO.findLocationByLocationName(locationName);
				LOG.info("IoclLocationDetail:::::::"+ioclLocationDetail);
				if(ioclLocationDetail!=null)
				{
					throw new IOCLWSException(Response.Status.CONFLICT.getStatusCode(),ErrorMessageConstants.LocationName_Already_Exist_Msg);
				}
			}
			if(locationCodeEditFlag)
			{
				IoclLocationDetail ioclLocationDetailCode=iOCLLocationDetailsDAO.findLocationIdByLocationCode(locationCode);
				if(ioclLocationDetailCode!=null)
				{
					throw new IOCLWSException(Response.Status.CONFLICT.getStatusCode(),ErrorMessageConstants.LocationCode_Already_Exist_Msg);
				}
			}

			IoclSupportedLocationstatus ioclSupportedLocationstatus=iOCLSupportedLocationStatusDAO.findStatusIdByLocationStatus(locationStatus);
			if(null!=ioclSupportedLocationstatus)
			{
				IoclLocationDetail ioclLocationDetail=iOCLLocationDetailsDAO.findLocationByLocationId(locationId);
				IoclStatesDetail ioclStatesDetail=iOCLStatesDetailsDAO.findStateIdByStateName(state);
				iOCLLocationDetailsDAO.updateLocationDetails(locationName, locationCode, ioclSupportedLocationstatus, locationAddress,locationId,ioclLocationDetail,city,pinCode,ioclStatesDetail);
				locationCreationResponseBean.setLocationAddress(locationAddress);
				locationCreationResponseBean.setLocationCode(locationCode);
				locationCreationResponseBean.setLocationId(Long.valueOf(locationId));
				locationCreationResponseBean.setLocationName(locationName);
				locationCreationResponseBean.setPinCode(ioclLocationDetail.getPinCode());
				locationCreationResponseBean.setState(ioclLocationDetail.getIoclStatesDetail().getStateName());
				locationCreationResponseBean.setCity(ioclLocationDetail.getCity());
				locationCreationResponseBean.setMessage("Location SuccessFully Updated : "+locationName);
				locationCreationResponseBean.setSuccessFlag(true);
			}
		}
		catch(IOCLWSException ioclexception)
		{
			LOG.info("Logging the occured exception in the service class updateLocation method custom catch block........"+ioclexception);
			throw ioclexception;
		}
		catch(Exception exception)
		{
			LOG.info("Logging the occured exception in the service class updateLocation method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
		return Response.status(Response.Status.OK).entity(locationCreationResponseBean).build();	
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,readOnly=false,rollbackFor=IOCLWSException.class)
	public Response deleteLocation(int locationID) throws IOCLWSException
	{
		LOG.info("Entered into deleteLocation service class method........");
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
		}
		catch (Exception exception) 
		{
			LOG.info("Logging the occured exception in the service class deleteLocation method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,readOnly=false,rollbackFor=IOCLWSException.class)
	public Response getData() throws IOCLWSException
	{
		LOG.info("Entered into getData service class method........");
		try
		{
			GetLocationStaticDataResponseBean getLocationStaticDataResponseBean=new GetLocationStaticDataResponseBean();

			Map<String,List<String>> data=new HashMap<String,List<String>>();

			List<String> supportedStatus=new ArrayList<String>();
			Set<String> setStates=new HashSet<String>();
			List<IoclSupportedLocationstatus> lIoclSupportedLocationstatus=iOCLSupportedLocationStatusDAO.findAllSupportedLocationStatus();
			
			List<IoclStatesDetail> lioclStatesDetail=iOCLStatesDetailsDAO.findAllStates();
			LOG.info("Got the lioclStatesDetail object......"+lioclStatesDetail);
			
			for(IoclStatesDetail ioclStatesDetail:lioclStatesDetail)
			{
				setStates.add(ioclStatesDetail.getStateName());
			}
			List<String> states=new ArrayList<String>(setStates);
			
			for(IoclSupportedLocationstatus ioclSupportedLocationstatus:lIoclSupportedLocationstatus)
			{
				supportedStatus.add(ioclSupportedLocationstatus.getLocationStatus());
			}
			data.put("LocationStatus",supportedStatus);
			data.put("States", states);
			getLocationStaticDataResponseBean.setData(data);
			return  Response.status(Response.Status.OK).entity(getLocationStaticDataResponseBean).build();
		}
		catch (Exception exception) 
		{
			LOG.info("Logging the occured exception in the service class getData method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}
}