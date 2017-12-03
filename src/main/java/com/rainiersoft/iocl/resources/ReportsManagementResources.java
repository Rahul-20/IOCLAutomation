package com.rainiersoft.iocl.resources;

import javax.annotation.security.PermitAll;
import javax.inject.Singleton;
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
import com.rainiersoft.iocl.services.ReportsManagementServices;
import com.rainiersoft.request.dto.ReportsRequestBean;

@Path("/reportsManagement")
@Singleton
@Component
public class ReportsManagementResources 
{
	private static final Logger LOG = LoggerFactory.getLogger(ReportsManagementResources.class);

	@Autowired
	ReportsManagementServices reportsManagementServices;

	public ReportsManagementResources() {}

	@Path("/getBayWiseLoadingReport")
	@PermitAll
	@POST
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response getBayWiseLoadingReport(ReportsRequestBean reportsRequestBean) throws IOCLWSException
	{
		LOG.info("Entered into getBayWiseLoadingReport resource class method........");
		try
		{
			return reportsManagementServices.getBayWiseLoadingReport(reportsRequestBean.getPageNumber(),reportsRequestBean.getPageSize(),reportsRequestBean.getStartDate(),reportsRequestBean.getEndDate(),reportsRequestBean.getBayNumber());
		}
		catch(IOCLWSException ioclwsException)
		{
			LOG.info("Logging the occured exception in the resouce class getBayWiseLoadingReport method........"+ioclwsException);
			throw ioclwsException;
		}
	}

	@Path("/exportBayWiseLoadingReport")
	@PermitAll
	@GET
	@Produces("application/pdf")
	public Response exportBayWiseLoadingReport(@QueryParam("BayNum") String bayNum,@QueryParam("StartDate") String startDate,@QueryParam("EndDate") String endDate) throws IOCLWSException
	{
		LOG.info("Entered into exportBayWiseLoadingReport resource class method........"+bayNum+".."+startDate+"..."+endDate);
		try
		{
			return reportsManagementServices.exportBayWiseLoadingReport(startDate,endDate,bayNum);
		}
		catch(IOCLWSException ioclwsException)
		{
			LOG.info("Logging the occured exception in the resouce class exportBayWiseLoadingReport method........"+ioclwsException);
			throw ioclwsException;
		}
	}

	@Path("/getTotalizerReport")
	@PermitAll
	@POST
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response getTotalizerReport(ReportsRequestBean reportsRequestBean) throws IOCLWSException
	{
		LOG.info("Entered into getTotalizerReport resource class method........");
		try
		{
			return reportsManagementServices.getTotalizerReport(reportsRequestBean.getPageNumber(),reportsRequestBean.getPageSize(),reportsRequestBean.getStartDate(),reportsRequestBean.getEndDate(),reportsRequestBean.getBayNumber());
		}
		catch(IOCLWSException ioclwsException)
		{
			LOG.info("Logging the occured exception in the resouce class getTotalizerReport method........"+ioclwsException);
			throw ioclwsException;
		}
	}
	
	@Path("/exportTotalizerReport")
	@PermitAll
	@GET
	@Produces("application/pdf")
	public Response exportTotalizerReport(@QueryParam("BayNum") String bayNum,@QueryParam("StartDate") String startDate,@QueryParam("EndDate") String endDate) throws IOCLWSException
	{
		LOG.info("Entered into exportBayWiseLoadingReport resource class method........"+bayNum+".."+startDate+"..."+endDate);
		try
		{
			return reportsManagementServices.exportTotalizerReport(startDate,endDate,bayNum);
		}
		catch(IOCLWSException ioclwsException)
		{
			LOG.info("Logging the occured exception in the resouce class exportBayWiseLoadingReport method........"+ioclwsException);
			throw ioclwsException;
		}
	}
	
	@Path("/getTruckFillingReport")
	@PermitAll
	@POST
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response getTruckFillingReport(ReportsRequestBean reportsRequestBean) throws IOCLWSException
	{
		LOG.info("Entered into getTruckFillingReport resource class method........");
		try
		{
			return reportsManagementServices.getTruckFillingReport(reportsRequestBean.getPageNumber(),reportsRequestBean.getPageSize(),reportsRequestBean.getStartDate(),reportsRequestBean.getEndDate());
		}
		catch(IOCLWSException ioclwsException)
		{
			LOG.info("Logging the occured exception in the resouce class getTruckFillingReport method........"+ioclwsException);
			throw ioclwsException;
		}
	}
	
	@Path("/exportTruckFillingReport")
	@PermitAll
	@GET
	@Produces("application/pdf")
	public Response exportTruckFillingReport(@QueryParam("BayNum") String bayNum,@QueryParam("StartDate") String startDate,@QueryParam("EndDate") String endDate) throws IOCLWSException
	{
		LOG.info("Entered into exportTruckFillingReport resource class method........"+bayNum+".."+startDate+"..."+endDate);
		try
		{
			return reportsManagementServices.exportTruckFillingReport(startDate,endDate);
		}
		catch(IOCLWSException ioclwsException)
		{
			LOG.info("Logging the occured exception in the resouce class exportTruckFillingReport method........"+ioclwsException);
			throw ioclwsException;
		}
	}

}
