package com.rainiersoft.iocl.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Singleton;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rainiersoft.iocl.dao.IOCLContractorDetailsDAO;
import com.rainiersoft.iocl.dao.IOCLContractorTypeDAO;
import com.rainiersoft.iocl.dao.IOCLStatesDetailsDAO;
import com.rainiersoft.iocl.dao.IOCLSupportedContractorStatusDAO;
import com.rainiersoft.iocl.entity.IoclContractorDetail;
import com.rainiersoft.iocl.entity.IoclContractortypeDetail;
import com.rainiersoft.iocl.entity.IoclStatesDetail;
import com.rainiersoft.iocl.entity.IoclSupportedContractorstatus;
import com.rainiersoft.iocl.exception.IOCLWSException;
import com.rainiersoft.iocl.util.ErrorMessageConstants;
import com.rainiersoft.response.dto.ContractorCreationAndUpdationResponseBean;
import com.rainiersoft.response.dto.ContractorDeletionResponseBean;
import com.rainiersoft.response.dto.ContractorDetailsResponseBean;
import com.rainiersoft.response.dto.GetContractorStaticDataResponseBean;

/**
 * This is the class for contractors management services
 * @author Rahul Kumar Pamidi
 */

@Service
@Singleton
public class ContractorsManagementServices
{
	private static final Logger LOG = LoggerFactory.getLogger(ContractorsManagementServices.class);

	@Autowired
	IOCLContractorDetailsDAO iOCLContractorDetailsDAO;

	@Autowired
	IOCLSupportedContractorStatusDAO iOCLSupportedContractorStatusDAO;

	@Autowired
	IOCLContractorTypeDAO iOCLContractorTypeDAO;

	@Autowired
	IOCLStatesDetailsDAO iOCLStatesDetailsDAO;

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=IOCLWSException.class)
	public Response getContractorDetails() throws IOCLWSException
	{
		try
		{
			LOG.info("Entered into getContractorDetails service class method........");
			List<ContractorDetailsResponseBean> listContractorDetailsResponseBean=new ArrayList<ContractorDetailsResponseBean>();
			List<IoclContractorDetail> lIoclContractorDetail=iOCLContractorDetailsDAO.findAllContractors();
			LOG.info("Got the lIoclContractorDetail object......"+lIoclContractorDetail);
			for(IoclContractorDetail ioclContractorDetail:lIoclContractorDetail)
			{
				ContractorDetailsResponseBean contractorDetailsResponseBean=new ContractorDetailsResponseBean();
				contractorDetailsResponseBean.setContractorId(ioclContractorDetail.getContractorId());
				contractorDetailsResponseBean.setContractorAddress(ioclContractorDetail.getContractorAddress());
				contractorDetailsResponseBean.setContractorCity(ioclContractorDetail.getContractorCity());
				contractorDetailsResponseBean.setContractorName(ioclContractorDetail.getContractorName());
				contractorDetailsResponseBean.setContractorOperationalStatus(ioclContractorDetail.getIoclSupportedContractorstatus().getContractorStatus());
				contractorDetailsResponseBean.setContractorPinCode(ioclContractorDetail.getZipCode());
				contractorDetailsResponseBean.setContractorState(ioclContractorDetail.getIoclStatesDetail().getStateName());
				contractorDetailsResponseBean.setContractorType(ioclContractorDetail.getIoclContractortypeDetail().getContractorType());
				listContractorDetailsResponseBean.add(contractorDetailsResponseBean);
			}
			LOG.info("getContractorDetails response object::::::"+listContractorDetailsResponseBean);
			return Response.status(Response.Status.OK).entity(listContractorDetailsResponseBean).build();
		}catch(Exception exception)
		{
			LOG.info("Logging the occured exception in the service class getContractorDetails method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=IOCLWSException.class)
	public Response addContractor(String contractorName,String contractorType,String contractorAddress,String contractorCity,String contractorOperationalStatus,String contractorPinCode,String contractorState) throws IOCLWSException
	{
		ContractorCreationAndUpdationResponseBean contractorCreationAndUpdationResponseBean=new ContractorCreationAndUpdationResponseBean();
		try
		{
			LOG.info("Entered into addContractor service class method........");
			//Check if the bay already exist in database.
			IoclContractorDetail ioclContractorDetail=iOCLContractorDetailsDAO.findContractorByContractorName(contractorName);
			LOG.info("Got the ioclContractorDetail object......"+ioclContractorDetail);
			if(ioclContractorDetail!=null)
			{
				throw new IOCLWSException(Response.Status.CONFLICT.getStatusCode(),ErrorMessageConstants.ContractorName_Already_Exist);
			}
			else
			{
				IoclSupportedContractorstatus ioclSupportedContractorstatus=iOCLSupportedContractorStatusDAO.findStatusIdByContractorStatus(contractorOperationalStatus);
				LOG.info("Got the ioclSupportedContractorstatus object......"+ioclSupportedContractorstatus);
				IoclContractortypeDetail ioclContractortypeDetail=iOCLContractorTypeDAO.findContractorIdByContractorType(contractorType);
				LOG.info("Got the ioclContractortypeDetail object......"+ioclContractortypeDetail);
				IoclStatesDetail ioclStatesDetail=iOCLStatesDetailsDAO.findStateIdByStateName(contractorState);
				LOG.info("Got the ioclStatesDetail object......"+ioclStatesDetail);
				if(null!=ioclSupportedContractorstatus && null!=ioclContractortypeDetail && null!=ioclStatesDetail)
				{
					Long contractorId=iOCLContractorDetailsDAO.insertContractorDetails(contractorName, ioclContractortypeDetail, contractorAddress, contractorCity, ioclSupportedContractorstatus, contractorPinCode, ioclStatesDetail);
					contractorCreationAndUpdationResponseBean.setContractorAddress(contractorAddress);
					contractorCreationAndUpdationResponseBean.setContractorCity(contractorCity);
					contractorCreationAndUpdationResponseBean.setContractorId(contractorId);
					contractorCreationAndUpdationResponseBean.setContractorName(contractorName);
					contractorCreationAndUpdationResponseBean.setContractorOperationalStatus(contractorOperationalStatus);
					contractorCreationAndUpdationResponseBean.setContractorPinCode(contractorPinCode);
					contractorCreationAndUpdationResponseBean.setContractorState(contractorState);
					contractorCreationAndUpdationResponseBean.setContractorType(contractorType);
					contractorCreationAndUpdationResponseBean.setMessage("Contractor SuccessFully Created : "+contractorName);
					contractorCreationAndUpdationResponseBean.setSuccessFlag(true);
					LOG.info("addContractor response object::::::"+contractorCreationAndUpdationResponseBean);
				}
			}			
		}
		catch(IOCLWSException ioclwsException)
		{
			LOG.info("Logging the occured exception in the service class addContractor method custom catch block........"+ioclwsException);
			throw ioclwsException;
		}
		catch(Exception exception)
		{
			LOG.info("Logging the occured exception in the service class addContractor method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
		return Response.status(Response.Status.OK).entity(contractorCreationAndUpdationResponseBean).build();	
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=IOCLWSException.class)
	public Response updateContractor(int contractorId,String contractorName,String contractorType,String contractorAddress,String contractorCity,String contractorOperationalStatus,String contractorPinCode,String contractorState,boolean editContractorNameFlag) throws IOCLWSException
	{
		LOG.info("Entered into updateContractor service class method........");
		ContractorCreationAndUpdationResponseBean contractorCreationAndUpdationResponseBean=new ContractorCreationAndUpdationResponseBean();
		try
		{
			if(editContractorNameFlag)
			{
				IoclContractorDetail ioclContractorDetail=iOCLContractorDetailsDAO.findContractorByContractorName(contractorName);
				LOG.info("Got the ioclContractorDetail object......"+ioclContractorDetail);
				if(ioclContractorDetail!=null)
				{
					throw new IOCLWSException(Response.Status.CONFLICT.getStatusCode(),ErrorMessageConstants.ContractorName_Already_Exist);
				}
			}
			IoclContractorDetail ioclContractorDetail=iOCLContractorDetailsDAO.findContractorByContractorId(contractorId);
			LOG.info("Got the ioclContractorDetail object......"+ioclContractorDetail);
			IoclSupportedContractorstatus ioclSupportedContractorstatus=iOCLSupportedContractorStatusDAO.findStatusIdByContractorStatus(contractorOperationalStatus);
			LOG.info("Got the ioclSupportedContractorstatus object......"+ioclSupportedContractorstatus);
			IoclContractortypeDetail ioclContractortypeDetail=iOCLContractorTypeDAO.findContractorIdByContractorType(contractorType);
			LOG.info("Got the ioclContractortypeDetail object......"+ioclContractortypeDetail);
			IoclStatesDetail ioclStatesDetail=iOCLStatesDetailsDAO.findStateIdByStateName(contractorState);
			LOG.info("Got the ioclStatesDetail object......"+ioclStatesDetail);
			iOCLContractorDetailsDAO.updateContractorDetails(contractorName, ioclContractortypeDetail, contractorAddress, contractorCity, ioclSupportedContractorstatus, contractorPinCode, ioclStatesDetail, ioclContractorDetail);
			contractorCreationAndUpdationResponseBean.setContractorAddress(contractorAddress);
			contractorCreationAndUpdationResponseBean.setContractorCity(contractorCity);
			contractorCreationAndUpdationResponseBean.setContractorAddress(contractorAddress);
			contractorCreationAndUpdationResponseBean.setContractorCity(contractorCity);
			contractorCreationAndUpdationResponseBean.setContractorId(Long.valueOf(contractorId));
			contractorCreationAndUpdationResponseBean.setContractorName(contractorName);
			contractorCreationAndUpdationResponseBean.setContractorOperationalStatus(contractorOperationalStatus);
			contractorCreationAndUpdationResponseBean.setContractorPinCode(contractorPinCode);
			contractorCreationAndUpdationResponseBean.setContractorState(contractorState);
			contractorCreationAndUpdationResponseBean.setContractorType(contractorType);
			contractorCreationAndUpdationResponseBean.setMessage("Contractor SuccessFully Updated : "+contractorName);
			contractorCreationAndUpdationResponseBean.setSuccessFlag(true);
			LOG.info("updateContractor response object::::::"+contractorCreationAndUpdationResponseBean);
		}
		catch(IOCLWSException ioclwsException)
		{
			LOG.info("Logging the occured exception in the service class updateContractor method custom catch block........"+ioclwsException);
			throw ioclwsException;
		}
		catch(Exception exception)
		{
			LOG.info("Logging the occured exception in the service class updateContractor method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
		return Response.status(Response.Status.OK).entity(contractorCreationAndUpdationResponseBean).build();
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,readOnly=false,rollbackFor=IOCLWSException.class)
	public Response deleteContractor(int contractorID) throws IOCLWSException
	{
		LOG.info("Entered into deleteContractor service class method........");
		try
		{
			ContractorDeletionResponseBean  contractorDeletionResponseBean=new ContractorDeletionResponseBean();
			boolean deleteFalg=iOCLContractorDetailsDAO.deleteContractor(contractorID);
			if(deleteFalg)
			{
				contractorDeletionResponseBean.setSuccessFlag(true);
				contractorDeletionResponseBean.setMessage("Contractor Deleted SuccessFully");
				LOG.info("deleteContractor response object::::::"+contractorDeletionResponseBean);
				return  Response.status(Response.Status.OK).entity(contractorDeletionResponseBean).build();	
			}
			else
			{
				contractorDeletionResponseBean.setSuccessFlag(false);
				contractorDeletionResponseBean.setMessage("Failed To Delete Contractor");
				LOG.info("deleteContractor response object::::::"+contractorDeletionResponseBean);
				return  Response.status(Response.Status.OK).entity(contractorDeletionResponseBean).build();
			}
		}
		catch (Exception exception) 
		{
			LOG.info("Logging the occured exception in the service class deleteContractor method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,readOnly=false,rollbackFor=IOCLWSException.class)
	public Response getContractorStaticData() throws IOCLWSException
	{
		LOG.info("Entered into getContractorStaticData service class method........");
		try
		{
			GetContractorStaticDataResponseBean getContractorStaticDataResponseBean=new GetContractorStaticDataResponseBean();
			Map<String,List<String>> data=new HashMap<String,List<String>>();

			List<IoclSupportedContractorstatus> lIoclSupportedContractorstatus=iOCLSupportedContractorStatusDAO.findAllSupportedContractorStatus();
			LOG.info("Got the lIoclSupportedContractorstatus object......"+lIoclSupportedContractorstatus);
			List<IoclContractortypeDetail> lioclContractortypeDetail=iOCLContractorTypeDAO.findAllContractorTypes();
			LOG.info("Got the lioclContractortypeDetail object......"+lioclContractortypeDetail);
			List<IoclStatesDetail> lioclStatesDetail=iOCLStatesDetailsDAO.findAllStates();
			LOG.info("Got the lioclStatesDetail object......"+lioclStatesDetail);

			List<String> supportedStatus=new ArrayList<String>();
			Set<String> setStates=new HashSet<String>();
			Set<String> setTypes=new HashSet<String>();
			for(IoclSupportedContractorstatus ioclSupportedContractorstatus:lIoclSupportedContractorstatus)
			{
				supportedStatus.add(ioclSupportedContractorstatus.getContractorStatus());
			}
			for(IoclContractortypeDetail ioclContractortypeDetail:lioclContractortypeDetail)
			{
				setTypes.add(ioclContractortypeDetail.getContractorType());
			}
			for(IoclStatesDetail ioclStatesDetail:lioclStatesDetail)
			{
				setStates.add(ioclStatesDetail.getStateName());
			}
			List<String> States=new ArrayList<String>(setStates);
			List<String> types=new ArrayList<String>(setTypes);
			data.put("Types",types);
			data.put("States",States);
			data.put("ContractorStatus",supportedStatus);
			getContractorStaticDataResponseBean.setData(data);
			LOG.info("getContractorStaticData response object......."+getContractorStaticDataResponseBean);
			return  Response.status(Response.Status.OK).entity(getContractorStaticDataResponseBean).build();
		}
		catch (Exception exception) 
		{
			LOG.info("Logging the occured exception in the service class getContractorStaticData method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}
}