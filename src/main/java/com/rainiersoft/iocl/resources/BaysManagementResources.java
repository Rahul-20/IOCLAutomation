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
import org.springframework.stereotype.Component;

import com.rainiersoft.iocl.exception.IOCLWSException;
import com.rainiersoft.iocl.model.BaysMangRequestBean;

@Path(value = "/baysmanagement")
@Singleton
@Component
public class BaysManagementResources 
{
	private static final Logger LOG = LoggerFactory.getLogger(BaysManagementResources.class);
	
	@Path(value="/bayscreation")
	@PermitAll
	@RolesAllowed({"Operator"})
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response baysCreation(BaysMangRequestBean request) throws IOCLWSException
	{
		return null;
	}
}
