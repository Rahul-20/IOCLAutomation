package com.rainiersoft.iocl.resources;

import com.rainiersoft.iocl.exception.IOCLWSException;
import com.rainiersoft.iocl.services.FanSlipManagementServices;
import com.rainiersoft.request.dto.FanSlipMangRequestBean;
import java.security.NoSuchAlgorithmException;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


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
			return fanSlipManagementServices.fanSlipGeneration(truckNo, driverName, driverLicNo, customer, quantity, vehicleWgt, destination, locationCode, mobileNumber, bayNum,contractorName);
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

	@Path("/getAllLatestFanslipsData")
	@RolesAllowed({"Admin", "Super Admin"})
	@PermitAll
	@GET
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response getAllLatestFanslipsData() throws IOCLWSException
	{
		LOG.info("Entered into getAllLatestFanslipsData resource class method........");
		LOG.info("Entered into getAllLatestFanslipsData resource class method........");
		try
		{
			return fanSlipManagementServices.getAllLatestFanslipsData();
		}
		catch(IOCLWSException iOCLWSException)
		{
			LOG.info("Logging the occured exception in the resouce class getAllLatestFanslipsData method........"+iOCLWSException);
			throw iOCLWSException;
		}
	}
}