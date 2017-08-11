package com.rainiersoft.iocl.resources;

import java.security.NoSuchAlgorithmException;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rainiersoft.iocl.exception.IOCLWSException;
import com.rainiersoft.iocl.model.FanSlipMangRequestBean;
import com.rainiersoft.iocl.services.FanSlipManagementServices;

@Path(value = "/fanslipmanagement")
@Singleton
@Component
public class FanSlipManagementResources 
{
	private static final Logger LOG = LoggerFactory.getLogger(FanSlipManagementResources.class);

	@Autowired
	FanSlipManagementServices fanSlipManagementServices;

	@Path(value="/fanslipgen")
	@PermitAll
	@RolesAllowed({"Operator"})
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response fanSlipGeneration(FanSlipMangRequestBean request) throws IOCLWSException, NoSuchAlgorithmException
	{
		String truckNo=request.getTruckNo();
		String driverName=request.getDriverName();
		String driverLicNo=request.getDriverLicNo();
		String customer=request.getCustomer();
		String quantity=request.getQuantity();
		String vehicleWgt=request.getVehicleWgt();
		String destination=request.getDestination();
		String locationCode=request.getLocationCode();
		String bayNum=request.getBayNum();
		String mobileNumber=request.getMobileNumber();

		return fanSlipManagementServices.fanSlipGeneration(truckNo,driverName,driverLicNo,customer,quantity,vehicleWgt,destination,locationCode,mobileNumber,bayNum);
	}
}
