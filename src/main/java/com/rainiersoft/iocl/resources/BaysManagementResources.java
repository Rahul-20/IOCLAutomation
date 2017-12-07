package com.rainiersoft.iocl.resources;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
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
import com.rainiersoft.iocl.services.BaysManagementServices;
import com.rainiersoft.request.dto.BaysMangRequestBean;

/**
 * This is the class for bays management resources.
 * @author RahulKumarPamidi
 */

@Path("/baysmanagement")
@Singleton
@Component
public class BaysManagementResources
{
	private static final Logger LOG = LoggerFactory.getLogger(BaysManagementResources.class);

	@Autowired
	BaysManagementServices baysManagementServices;

	public BaysManagementResources() {}

	@Path("/getAllBayDetails")
	@PermitAll
	@GET
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response getAllBayDetails() throws IOCLWSException
	{
		try
		{
			LOG.info("Entered into getAllBayDetails resource class method........");
			return baysManagementServices.getAllBayDetails();
		}
		catch(IOCLWSException iOCLWSException)
		{
			LOG.info("Logging the occured exception in the resouce class getAllBayDetails method........"+iOCLWSException);
			throw iOCLWSException;
		}
	}

	@Path("/getBayStatus")
	@RolesAllowed({"Admin", "Super Admin"})
	@GET
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response getBaysStatus() throws IOCLWSException
	{
		try
		{
			LOG.info("Entered into getBaysStatus resource class method........");
			return baysManagementServices.getBayStatus();
		}
		catch(IOCLWSException ioclwsException)
		{
			LOG.info("Logging the occured exception in the resouce class getBaysStatus method........"+ioclwsException);
			throw ioclwsException;
		}
	}

	@Path("/getBayType")
	@RolesAllowed({"Admin", "Super Admin"})
	@GET
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response getBayType() throws IOCLWSException
	{
		LOG.info("Entered into getBayType resource class method........");
		try
		{
			return baysManagementServices.getBayTypes();
		}
		catch(IOCLWSException ioclwsException)
		{
			LOG.info("Logging the occured exception in the resouce class getBayTypes method........"+ioclwsException);
			throw ioclwsException;
		}
	}

	@Path("/bayscreation")
	@RolesAllowed({"Admin", "Super Admin"})
	@POST
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response baysCreation(BaysMangRequestBean request) throws IOCLWSException
	{
		LOG.info("Entered into baysCreation resource class method........");
		try
		{
			String bayName = request.getBayName();
			int bayNum = request.getBayNum();
			String bayType = request.getBayType();
			String functionalStatus = request.getFunctionalStatus();
			String userName=request.getUserName();
			LOG.info("Request Object For Bay Creation........"+request);
			return baysManagementServices.bayCreation(bayName, bayNum, bayType, functionalStatus,userName);
		}
		catch(IOCLWSException ioclwsException)
		{
			LOG.info("Logging the occured exception in the resouce class baysCreation method........"+ioclwsException);
			throw ioclwsException;
		}
	}

	@Path("/getAvailableBays")
	@RolesAllowed({"TTES Operator","Supervisor"})
	@GET
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response getAvailableBays() throws IOCLWSException
	{
		LOG.info("Entered into getAvailableBays resource class method........");
		try
		{
			return baysManagementServices.getAvailableBays();
		}
		catch(IOCLWSException iOCLWSException)
		{
			LOG.info("Logging the occured exception in the resouce class getAvailableBays method........"+iOCLWSException);
			throw iOCLWSException;
		}
	}

	@Path("/deleteBay")
	@RolesAllowed({"Admin", "Super Admin"})
	@DELETE
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response deleteBay(@QueryParam("bayID") int bayID) throws IOCLWSException
	{
		LOG.info("Entered into deleteBay resource class method........");
		try
		{
			LOG.info("Query parameter for the method deleteBay..."+bayID);
			return baysManagementServices.deleteBay(bayID);
		}
		catch(IOCLWSException iOCLWSException)
		{
			LOG.info("Logging the occured exception in the resouce class deleteBay method........"+iOCLWSException);
			throw iOCLWSException;
		}
	}

	@Path("/getBayStaticData")
	@RolesAllowed({"Admin", "Super Admin"})
	@GET
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response getData() throws IOCLWSException
	{
		LOG.info("Entered into getData resource class method........");
		try
		{
			return baysManagementServices.getData();
		}
		catch(IOCLWSException iOCLWSException)
		{
			LOG.info("Logging the occured exception in the resouce class getData method........"+iOCLWSException);
			throw iOCLWSException;
		}
	}

	@Path("/baysupdation")
	@RolesAllowed({"Admin", "Super Admin"})
	@PUT
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response baysUpdation(BaysMangRequestBean request) throws IOCLWSException
	{
		LOG.info("Entered into baysUpdation resource class method........");
		try
		{
			LOG.info("Request Object For Bay Updation........"+request);
			return baysManagementServices.bayUpdation(request.getBayId(),request.getBayName(),request.getBayNum(), request.getBayType(),request.getFunctionalStatus(),request.getEditbayNumFlag(),request.getEditbayNameFlag(),request.getUserName());
		}
		catch(IOCLWSException iOCLWSException)
		{
			LOG.info("Logging the occured exception in the resouce class baysUpdation method........"+iOCLWSException);
			throw iOCLWSException;
		}
	}
	
	@Path("/getCurrentBayOperationalDetails")
	//@RolesAllowed({"Admin", "Super Admin"})
	@PermitAll
	@GET
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response getCurrentBayOperationalDetails() throws IOCLWSException
	{
		try
		{
			LOG.info("Entered into getCurrentBayOperationalDetails resource class method........");
			return baysManagementServices.getCurrentBayOperationalDetails();
		}
		catch(IOCLWSException iOCLWSException)
		{
			LOG.info("Logging the occured exception in the resouce class getAllBayDetails method........"+iOCLWSException);
			throw iOCLWSException;
		}
	}
}