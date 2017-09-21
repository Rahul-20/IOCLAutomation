package com.rainiersoft.iocl.resources;

import java.sql.Date;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rainiersoft.iocl.exception.IOCLWSException;
import com.rainiersoft.iocl.services.FanSlipManagementServices;
import com.rainiersoft.request.dto.FanSlipMangRequestBean;


@Path("/fanslipmanagement")
@Component
public class FanSlipManagementResources
{
	private static final Logger LOG = LoggerFactory.getLogger(FanSlipManagementResources.class);

	@Autowired
	FanSlipManagementServices fanSlipManagementServices;

	public FanSlipManagementResources() {}

	@Path("/fanslipgen")
	@PermitAll
	@RolesAllowed({"Operator"})
	@POST
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response fanSlipGeneration(FanSlipMangRequestBean request) throws IOCLWSException
	{
		try
		{
			LOG.info("Entered into fanSlipGeneration resource class method........");
			LOG.info("Entered into fanSlipGeneration resource class method........");
			String truckNo = request.getTruckNo();
			String driverName = request.getDriverName();
			String driverLicNo = request.getDriverLicNo();
			String customer = request.getCustomer();
			String quantity = request.getQuantity();
			String vehicleWgt = request.getVehicleWgt();
			String destination = request.getDestination();
			String locationCode = request.getLocationCode();
			int bayNum = request.getBayNum();
			String mobileNumber = request.getMobileNumber();
			String contractorName=request.getContractorName();
			String createdBy=request.getFanCreatedBy();
			return fanSlipManagementServices.fanSlipGeneration(truckNo, driverName, driverLicNo, customer, quantity, vehicleWgt, destination, locationCode, mobileNumber, bayNum,contractorName,createdBy);
		}
		catch(IOCLWSException iOCLWSException)
		{
			LOG.info("Logging the occured exception in the resouce class fanSlipGeneration method........"+iOCLWSException);
			throw iOCLWSException;
		}
	}

	@Path("/getFanStaticData")
	@RolesAllowed({"Admin", "Super Admin"})
	@PermitAll
	@GET
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response getData() throws IOCLWSException
	{
		LOG.info("Entered into getData resource class method........");
		LOG.info("Entered into getData resource class method........");
		try
		{
			return fanSlipManagementServices.getFanStaticData();
		}
		catch(IOCLWSException iOCLWSException)
		{
			LOG.info("Logging the occured exception in the resouce class getData method........"+iOCLWSException);
			throw iOCLWSException;
		}
	}

	@Path("/getFanslipsDetails")
	@RolesAllowed({"Admin", "Super Admin"})
	@PermitAll
	@GET
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response getFanslipsDetails(@QueryParam("selectedDate") String selectedDate) throws IOCLWSException
	{
		LOG.info("Entered into getFanslipsDetails resource class method........");
		LOG.info("Entered into getFanslipsDetails resource class method........");
		try
		{
			return fanSlipManagementServices.getAllLatestFanslipsData(selectedDate);
		}
		catch(IOCLWSException iOCLWSException)
		{
			LOG.info("Logging the occured exception in the resouce class getAllLatestFanslipsData method........"+iOCLWSException);
			throw iOCLWSException;
		}
	}

	@Path("/fanslipReGeneration")
	@PermitAll
	@RolesAllowed({"Operator"})
	@POST
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response fanslipReGeneration(FanSlipMangRequestBean request) throws IOCLWSException
	{
		try
		{
			LOG.info("Entered into fanSlipGeneration resource class method........");
			LOG.info("Entered into fanSlipGeneration resource class method........");
			String truckNo = request.getTruckNo();
			String driverName = request.getDriverName();
			String driverLicNo = request.getDriverLicNo();
			String customer = request.getCustomer();
			String quantity = request.getQuantity();
			String vehicleWgt = request.getVehicleWgt();
			String destination = request.getDestination();
			String locationCode = request.getLocationCode();
			int bayNum = request.getBayNum();
			String mobileNumber = request.getMobileNumber();
			String contractorName=request.getContractorName();
			int fanId=request.getFanId();
			String userName=request.getFanCreatedBy();
			return fanSlipManagementServices.fanslipReGeneration(fanId,truckNo, driverName, driverLicNo, customer, quantity, vehicleWgt, destination, locationCode, mobileNumber, bayNum,contractorName,userName);
		}
		catch(IOCLWSException iOCLWSException)
		{
			LOG.info("Logging the occured exception in the resouce class fanSlipGeneration method........"+iOCLWSException);
			throw iOCLWSException;
		}
	}
	
	@Path("/fanslipCancellation")
	@PermitAll
	@RolesAllowed({"Operator"})
	@POST
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response fanslipCancellation(@QueryParam("FanId") int fanId, @QueryParam("UserName") String userName) throws IOCLWSException
	{
		try
		{
			LOG.info("Entered into fanSlipGeneration resource class method........");
			LOG.info("Entered into fanSlipGeneration resource class method........");
			
			return fanSlipManagementServices.fanslipCancellation(fanId,userName);
		}
		catch(IOCLWSException iOCLWSException)
		{
			LOG.info("Logging the occured exception in the resouce class fanSlipGeneration method........"+iOCLWSException);
			throw iOCLWSException;
		}
	}

}