package com.rainiersoft.iocl.resources;

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
import com.rainiersoft.iocl.services.QuantitiesManagementServices;


@Path("/quantitymanagement")
@Component
public class QuantitiesManagementResources 
{
	private static final Logger LOG = LoggerFactory.getLogger(QuantitiesManagementResources.class);

	@Autowired
	QuantitiesManagementServices quantitiesManagementServices;

	public QuantitiesManagementResources() {}

	@Path("/getQuantityDetails")
	@PermitAll
	@RolesAllowed({"Super Admin"})
	@POST
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response getQuantityDetails() throws IOCLWSException, Exception 
	{
		return quantitiesManagementServices.getQuantityDetails();
	}
}