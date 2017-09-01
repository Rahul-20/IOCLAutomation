package com.rainiersoft.iocl.resources;

import com.rainiersoft.iocl.exception.IOCLWSException;
import com.rainiersoft.iocl.services.FanSlipManagementServices;
import com.rainiersoft.request.dto.FanSlipMangRequestBean;
import java.security.NoSuchAlgorithmException;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
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
	public Response fanSlipGeneration(FanSlipMangRequestBean request) throws IOCLWSException, NoSuchAlgorithmException, Exception {
		LOG.info("Entered into FanSlip Generation Resource Method");
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

		return fanSlipManagementServices.fanSlipGeneration(truckNo, driverName, driverLicNo, customer, quantity, vehicleWgt, destination, locationCode, mobileNumber, bayNum);
	}
}