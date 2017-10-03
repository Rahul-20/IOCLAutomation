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
import com.rainiersoft.iocl.services.ContractorsManagementServices;
import com.rainiersoft.request.dto.ContractorRequestBean;

/**
 * This is the class for contractors management resources.
 * @author RahulKumarPamidi
 */

@Path("/contractorsmanagement")
@Component
public class ContractorsManagementResources 
{
	private static final Logger LOG = LoggerFactory.getLogger(ContractorsManagementResources.class);

	@Autowired
	ContractorsManagementServices contractorsManagementServices;

	public ContractorsManagementResources() {}

	@Path("/getContractorDetails")
	@RolesAllowed({"Super Admin","Admin"})
	@GET
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response getContractorDetails() throws IOCLWSException
	{
		LOG.info("Entered into getContractorDetails resource class method........");
		try
		{
			return contractorsManagementServices.getContractorDetails();
		}
		catch(IOCLWSException iOCLWSException)
		{
			LOG.info("Logging the occured exception in the resouce class getContractorDetails method........"+iOCLWSException);
			throw iOCLWSException;
		}
	}

	@Path("/addContractor")
	@RolesAllowed({"Super Admin","Admin"})
	@POST
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response addContractorDetails(ContractorRequestBean contractorRequestBean) throws IOCLWSException, Exception 
	{
		LOG.info("Entered into addContractorDetails resource class method........");
		try
		{
			String contractorName=contractorRequestBean.getContractorName();
			String contractorAddress=contractorRequestBean.getContractorAddress();
			String contractorCity=contractorRequestBean.getContractorCity();
			String contractorOperationalStatus=contractorRequestBean.getContractorOperationalStatus();
			String contractorPinCode=contractorRequestBean.getContractorPinCode();
			String contractorState=contractorRequestBean.getContractorState();
			String contractorType=contractorRequestBean.getContractorType();
			LOG.info("Request Object For Contractor Creation........"+contractorRequestBean);
			return contractorsManagementServices.addContractor(contractorName,contractorType,contractorAddress,contractorCity,contractorOperationalStatus,contractorPinCode,contractorState);
		}
		catch(IOCLWSException iOCLWSException)
		{
			LOG.info("Logging the occured exception in the resouce class addContractorDetails method........"+iOCLWSException);
			throw iOCLWSException;
		}
	}

	@Path("/updateContractor")
	@RolesAllowed({"Super Admin","Admin"})
	@PUT
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response updateContractorDetails(ContractorRequestBean contractorRequestBean) throws IOCLWSException, Exception 
	{
		LOG.info("Entered into updateContractorDetails resource class method........");
		try
		{
			int contractorId=contractorRequestBean.getContractorId();
			String contractorName=contractorRequestBean.getContractorName();
			String contractorAddress=contractorRequestBean.getContractorAddress();
			String contractorCity=contractorRequestBean.getContractorCity();
			String contractorOperationalStatus=contractorRequestBean.getContractorOperationalStatus();
			String contractorPinCode=contractorRequestBean.getContractorPinCode();
			String contractorState=contractorRequestBean.getContractorState();
			String contractorType=contractorRequestBean.getContractorType();
			boolean editContractorFlag=contractorRequestBean.getEditContractorNameFlag();
			LOG.info("Request Object For Contractor Updation........"+contractorRequestBean);
			return contractorsManagementServices.updateContractor(contractorId,contractorName,contractorType,contractorAddress,contractorCity,contractorOperationalStatus,contractorPinCode,contractorState,editContractorFlag);
		}
		catch(IOCLWSException ioclwsException)
		{
			LOG.info("Logging the occured exception in the resouce class updateContractorDetails method........"+ioclwsException);
			throw ioclwsException;
		}
	}

	@Path("/deleteContractor")
	@RolesAllowed({"Admin", "Super Admin"})
	@DELETE
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response deleteContractor(@QueryParam("ContractorID") int contractorID) throws IOCLWSException
	{
		LOG.info("Entered into deleteContractor resource class method........");
		try
		{
			LOG.info("Query Parm For deleteContractor method........"+contractorID);
			return contractorsManagementServices.deleteContractor(contractorID);
		}
		catch(IOCLWSException iOCLWSException)
		{
			LOG.info("Logging the occured exception in the resouce class deleteContractor method........"+iOCLWSException);
			throw iOCLWSException;
		}
	}

	@Path("/getContractorStaticData")
	@RolesAllowed({"Super Admin","Admin"})
	@GET
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response getContractorStaticData() throws IOCLWSException
	{
		LOG.info("Entered into getContractorStaticData resource class method........");
		try
		{
			return contractorsManagementServices.getContractorStaticData();
		}
		catch(IOCLWSException iOCLWSException)
		{
			LOG.info("Logging the occured exception in the resouce class getContractorStaticData method........"+iOCLWSException);
			throw iOCLWSException;
		}
	}
}