package com.rainiersoft.iocl.resources;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.rainiersoft.iocl.exception.IOCLWSException;
import com.rainiersoft.iocl.services.LocationManagementServices;
import com.rainiersoft.request.dto.LocationMangRequestBean;

/**
 * This is the class for location management resources.
 * @author Rahul Kumar Pamidi
 */

@Path("/locationsmanagement")
@Component
public class LocationsManagementResources 
{
	private static final Logger LOG = LoggerFactory.getLogger(LocationsManagementResources.class);

	@Autowired
	LocationManagementServices locationManagementServices;

	public LocationsManagementResources() {}

	@Path("/getLocationDetails")
	@RolesAllowed({"Super Admin","Admin"})
	@GET
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response getLocationDetails() throws IOCLWSException
	{
		LOG.info("Entered into getLocationDetails resource class method........");
		try
		{
			return locationManagementServices.getLocationDetails();
		}
		catch(IOCLWSException ioclwsException)
		{
			LOG.info("Logging the occured exception in the resouce class getLocationDetails method........"+ioclwsException);
			throw ioclwsException;
		}
	}

	@Path("/addLocation")
	@RolesAllowed({"Super Admin","Admin"})
	@POST
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response addLocationDetails(LocationMangRequestBean locationMangRequestBean) throws IOCLWSException 
	{	
		LOG.info("Entered into addLocationDetails resource class method........");
		try
		{
			String locationName=locationMangRequestBean.getLocationName();
			String locationCode=locationMangRequestBean.getLocationCode();
			String quantityStatus=locationMangRequestBean.getOperationalStatus();
			String locationAddress=locationMangRequestBean.getLocationAddress();
			return locationManagementServices.addLocation(locationName,locationCode,quantityStatus,locationAddress);
		}
		catch(IOCLWSException iOCLWSException)
		{
			LOG.info("Logging the occured exception in the resouce class addLocationDetails method........"+iOCLWSException);
			throw iOCLWSException;
		}
	}

	@Path("/updateLocation")
	@RolesAllowed({"Super Admin","Admin"})
	@PUT
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response updateLocationDetails(LocationMangRequestBean locationMangRequestBean) throws IOCLWSException 
	{
		LOG.info("Entered into updateLocationDetails resource class method........");
		try
		{
			String locationName=locationMangRequestBean.getLocationName();
			String locationCode=locationMangRequestBean.getLocationCode();
			String quantityStatus=locationMangRequestBean.getOperationalStatus();
			String locationAddress=locationMangRequestBean.getLocationAddress();
			int locationId=locationMangRequestBean.getLocationId();
			return locationManagementServices.updateLocation(locationName,locationCode,quantityStatus,locationAddress,locationId,locationMangRequestBean.getEditLocationNameFlag(),locationMangRequestBean.getEditLocationCodeFlag());
		}
		catch(IOCLWSException iOCLWSException)
		{
			LOG.info("Logging the occured exception in the resouce class updateLocationDetails method........"+iOCLWSException);
			throw iOCLWSException;
		}
	}

	@Path("/deleteLocation")
	@RolesAllowed({"Super Admin","Admin"})
	@DELETE
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response deleteLocation(@QueryParam("LocationID") int locationID) throws IOCLWSException
	{
		LOG.info("Entered into deleteLocation resource class method........");
		try
		{
			LOG.info("Query parameter for the method deleteLocation..."+locationID);
			return locationManagementServices.deleteLocation(locationID);
		}
		catch(IOCLWSException iOCLWSException)
		{
			LOG.info("Logging the occured exception in the resouce class deleteLocation method........"+iOCLWSException);
			throw iOCLWSException;
		}
	}

	@Path("/getStaticLocationData")
	@RolesAllowed({"Super Admin","Admin"})
	@GET
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response getData() throws IOCLWSException
	{
		LOG.info("Entered into getData resource class method........");
		try
		{
			return locationManagementServices.getData();
		}
		catch(IOCLWSException ioclwsException)
		{
			LOG.info("Logging the occured exception in the resouce class getData method........"+ioclwsException);
			throw ioclwsException;
		}
	}
}