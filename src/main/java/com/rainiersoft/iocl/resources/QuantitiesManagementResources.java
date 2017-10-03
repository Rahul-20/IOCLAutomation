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
import com.rainiersoft.iocl.services.QuantitiesManagementServices;
import com.rainiersoft.request.dto.QunatityMangRequestBean;

/**
 * This is the class for Qunatity management resources.
 * @author RahulKumarPamidi
 */

@Path("/quantitymanagement")
@Component
public class QuantitiesManagementResources 
{
	private static final Logger LOG = LoggerFactory.getLogger(QuantitiesManagementResources.class);

	@Autowired
	QuantitiesManagementServices quantitiesManagementServices;

	public QuantitiesManagementResources() {}

	@Path("/getQuantityDetails")
	@RolesAllowed({"Super Admin","Admin"})
	@GET
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response getQuantityDetails() throws IOCLWSException
	{
		LOG.info("Entered into getQuantityDetails resource class method........");
		try
		{
			return quantitiesManagementServices.getQuantityDetails();
		}
		catch(IOCLWSException ioclwsException)
		{
			LOG.info("Logging the occured exception in the resouce class getQuantityDetails method........"+ioclwsException);
			throw ioclwsException;
		}
	}

	@Path("/addQuantity")
	@RolesAllowed({"Super Admin","Admin"})
	@POST
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response addQunatityDetails(QunatityMangRequestBean qunatityMangRequestBean) throws IOCLWSException 
	{
		LOG.info("Entered into addQunatityDetails resource class method........");
		try
		{
			String quantityName=qunatityMangRequestBean.getQuantityName();
			String quantity=qunatityMangRequestBean.getQuantity();
			String quantityStatus=qunatityMangRequestBean.getQuantityStatus();
			LOG.info("Request Object For Add Quantity........"+qunatityMangRequestBean);
			return quantitiesManagementServices.addQunatity(quantityName,quantity,quantityStatus);
		}
		catch(IOCLWSException ioclwsException)
		{
			LOG.info("Logging the occured exception in the resouce class addQunatityDetails method........"+ioclwsException);
			throw ioclwsException;
		}
	}

	@Path("/updateQuantity")
	@RolesAllowed({"Super Admin","Admin"})
	@PUT
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response updateQuantityDetails(QunatityMangRequestBean qunatityMangRequestBean) throws IOCLWSException, Exception 
	{
		LOG.info("Entered into updateQuantityDetails resource class method........");
		try
		{
			int quantityId=qunatityMangRequestBean.getQuantityId();
			String quantityName=qunatityMangRequestBean.getQuantityName();
			String quantity=qunatityMangRequestBean.getQuantity();
			String quantityStatus=qunatityMangRequestBean.getQuantityStatus();
			LOG.info("Request Object For update Quantity........"+qunatityMangRequestBean);
			return quantitiesManagementServices.updateQuantity(quantityId,quantityName,quantity,quantityStatus,qunatityMangRequestBean.getEditQuantityNameFlag(),qunatityMangRequestBean.getEditQuantityFlag());
		}
		catch(IOCLWSException ioclwsException)
		{
			LOG.info("Logging the occured exception in the resouce class updateQuantityDetails method........"+ioclwsException);
			throw ioclwsException;
		}
	}

	@Path("/deleteQuantity")
	@RolesAllowed({"Super Admin","Admin"})
	@DELETE
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response deleteQunatity(@QueryParam("quantityID") int quantityID) throws IOCLWSException
	{
		LOG.info("Entered into deleteQunatity resource class method........");
		try
		{
			LOG.info("Query parameter for the method deleteQunatity..."+quantityID);
			return quantitiesManagementServices.deleteQuantity(quantityID);
		}
		catch(IOCLWSException iOCLWSException)
		{
			LOG.info("Logging the occured exception in the resouce class deleteQunatity method........"+iOCLWSException);
			throw iOCLWSException;
		}
	}

	@Path("/getQuantityStaticData")
	@RolesAllowed({"Super Admin","Admin"})
	@GET
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response getQuantityStaticData() throws IOCLWSException
	{
		try
		{
			return quantitiesManagementServices.getQuantityStaticData();
		}
		catch(IOCLWSException iOCLWSException)
		{
			throw iOCLWSException;
		}
	}
}