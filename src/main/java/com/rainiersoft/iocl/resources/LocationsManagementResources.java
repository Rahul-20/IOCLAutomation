package com.rainiersoft.iocl.resources;

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

import com.rainiersoft.iocl.exception.IOCLWSException;
import com.rainiersoft.iocl.services.FanSlipManagementServices;
import com.rainiersoft.iocl.services.LocationManagementServices;
import com.rainiersoft.request.dto.FanSlipMangRequestBean;

@Path("/locationsmanagement")
@Component
public class LocationsManagementResources 
{
	private static final Logger LOG = LoggerFactory.getLogger(LocationsManagementResources.class);

	@Autowired
	LocationManagementServices locationManagementServices;

	public LocationsManagementResources() {}

	@Path("/getLocationDetails")
	@PermitAll
	@RolesAllowed({"Super Admin"})
	@POST
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response getLocationDetails() throws IOCLWSException, Exception 
	{
		return locationManagementServices.getLocationDetails();
	}
}
