package com.rainiersoft.iocl.resources;

import com.rainiersoft.iocl.exception.IOCLWSException;
import com.rainiersoft.iocl.services.BaysManagementServices;
import com.rainiersoft.request.dto.BaysMangRequestBean;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
	@RolesAllowed({"Operator"})
	@GET
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response getAllBayDetails() {
		LOG.info("Entered into Fetch ALl Bay Details Resoutce........");
		return baysManagementServices.getAllBayDetails();
	}

	@Path("/getBayStatus")
	@PermitAll
	@RolesAllowed({"Operator"})
	@GET
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response getBaysStatus() throws IOCLWSException
	{
		LOG.info("Entered into Get Bay Status Resource Method......");
		return baysManagementServices.getBayStatus();
	}

	@Path("/getBayType")
	@PermitAll
	@RolesAllowed({"Operator"})
	@GET
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response getBayType() throws IOCLWSException
	{
		LOG.info("Entered into Get Bay Types Resource Method.....");
		return baysManagementServices.getBayTypes();
	}

	@Path("/bayscreation")
	@PermitAll
	@RolesAllowed({"Operator"})
	@POST
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response baysCreation(BaysMangRequestBean request)
			throws IOCLWSException
	{
		String bayName = request.getBayName();
		int bayNum = request.getBayNum();
		String bayType = request.getBayType();
		String functionalStatus = request.getFunctionalStatus();
		return baysManagementServices.bayCreation(bayName, bayNum, bayType, functionalStatus);
	}

	@Path("/getAvailableBays")
	@PermitAll
	@RolesAllowed({"Operator"})
	@GET
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response getAvailableBays()
			throws IOCLWSException
	{
		return baysManagementServices.getAvailableBays();
	}

	@Path("/deleteBay")
	@PermitAll
	@RolesAllowed({"Operator"})
	@DELETE
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response deleteBay(@QueryParam("bayID") int bayID) throws IOCLWSException
	{
		int bayNum = bayID;
		return baysManagementServices.deleteBay(bayNum);
	}
}
