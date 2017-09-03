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
import com.rainiersoft.iocl.services.ContractorsManagementServices;
import com.rainiersoft.iocl.services.LocationManagementServices;
import com.rainiersoft.request.dto.ContractorRequestBean;

@Path("/contractorsmanagement")
@Component
public class ContractorsManagementResources 
{
	private static final Logger LOG = LoggerFactory.getLogger(ContractorsManagementResources.class);

	@Autowired
	ContractorsManagementServices contractorsManagementServices;

	public ContractorsManagementResources() {}

	@Path("/getContractorDetails")
	@PermitAll
	@RolesAllowed({"Super Admin"})
	@POST
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response getContractorDetails() throws IOCLWSException, Exception 
	{
		return contractorsManagementServices.getContractorDetails();
	}
	
	@Path("/addContractor")
	@PermitAll
	@RolesAllowed({"Super Admin"})
	@POST
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response addContractorDetails(ContractorRequestBean contractorRequestBean) throws IOCLWSException, Exception 
	{
		String contractorName=contractorRequestBean.getContractorName();
		String contractorAddress=contractorRequestBean.getContractorAddress();
		String contractorCity=contractorRequestBean.getContractorCity();
		String contractorOperationalStatus=contractorRequestBean.getContractorOperationalStatus();
		String contractorPinCode=contractorRequestBean.getContractorPinCode();
		String contractorState=contractorRequestBean.getContractorState();
		String contractorType=contractorRequestBean.getContractorType();
		return contractorsManagementServices.addContractor(contractorName,contractorType,contractorAddress,contractorCity,contractorOperationalStatus,contractorPinCode,contractorState);
	}
}
