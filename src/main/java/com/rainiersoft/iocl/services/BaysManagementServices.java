package com.rainiersoft.iocl.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rainiersoft.iocl.dao.IOCLBCBayOperationsDAO;
import com.rainiersoft.iocl.dao.IOCLBayDetailsDAO;
import com.rainiersoft.iocl.dao.IOCLFanslipDetailsDAO;
import com.rainiersoft.iocl.dao.IOCLSupportedBayStatusDAO;
import com.rainiersoft.iocl.dao.IOCLSupportedBayTypesDAO;
import com.rainiersoft.iocl.entity.IoclBayDetail;
import com.rainiersoft.iocl.entity.IoclBcBayoperation;
import com.rainiersoft.iocl.entity.IoclFanslipDetail;
import com.rainiersoft.iocl.entity.IoclSupportedBaystatus;
import com.rainiersoft.iocl.entity.IoclSupportedBaytype;
import com.rainiersoft.iocl.exception.IOCLWSException;
import com.rainiersoft.iocl.util.ErrorMessageConstants;
import com.rainiersoft.response.dto.AllBayDetailsResponseBean;
import com.rainiersoft.response.dto.AvailableBaysResponseBean;
import com.rainiersoft.response.dto.BayCreationResponseBean;
import com.rainiersoft.response.dto.FanslipsAssignedBean;

@Service
@Singleton
public class BaysManagementServices
{
	private static final Logger LOG = LoggerFactory.getLogger(BaysManagementServices.class);

	@Autowired
	IOCLSupportedBayStatusDAO iOCLSupportedBayStatusDAO;

	@Autowired
	IOCLSupportedBayTypesDAO iOCLSupportedBayTypesDAO;

	@Autowired
	IOCLBayDetailsDAO iOCLBayDetailsDAO;

	@Autowired
	IOCLFanslipDetailsDAO iOCLFanslipDetailsDAO;

	@Autowired
	IOCLBCBayOperationsDAO iOCLBCBayOperationsDAO;

	@Value("${BayQueueMaxSize}")
	String bayQueueMaxSize;

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public Response getAllBayDetails()
	{
		List<AllBayDetailsResponseBean> listAllBayDetailsResponseBean=new ArrayList<AllBayDetailsResponseBean>();

		List<IoclBayDetail> listOfIocBayDetails=iOCLBayDetailsDAO.findAllAvailableBaysInApplication();

		for(IoclBayDetail ioclBayDetail:listOfIocBayDetails)
		{
			AllBayDetailsResponseBean allBayDetailsResponseBean=new AllBayDetailsResponseBean();
			allBayDetailsResponseBean.setBayName(ioclBayDetail.getBayName());
			allBayDetailsResponseBean.setBayNum(ioclBayDetail.getBayNum());
			allBayDetailsResponseBean.setBayType(ioclBayDetail.getIoclBayTypes().get(0).getBayType());
			allBayDetailsResponseBean.setFunctionalStatus(ioclBayDetail.getIoclSupportedBaystatus().getBayFunctionalStatus());
			listAllBayDetailsResponseBean.add(allBayDetailsResponseBean);
		}
		return  Response.status(Response.Status.OK).entity(listAllBayDetailsResponseBean).build();
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public Response getBayStatus()
	{
		LOG.info("Entered into GetBayStatus Service method......");
		List<IoclSupportedBaystatus> listOfStatus=iOCLSupportedBayStatusDAO.findAllSupportedBayStatus();
		List<String> statusResp=new ArrayList<String>();
		for(IoclSupportedBaystatus ioclSupportedBaystatus:listOfStatus)
		{
			statusResp.add(ioclSupportedBaystatus.getBayFunctionalStatus());
		}
		return  Response.status(Response.Status.OK).entity(statusResp).build();
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public Response getBayTypes() throws IOCLWSException
	{
		List<IoclSupportedBaytype> listOfTypes=iOCLSupportedBayTypesDAO.findAllSupportedBayTypes();	
		List<String> typesResp=new ArrayList<String>();
		for(IoclSupportedBaytype ioclSupportedBaytype:listOfTypes)
		{
			typesResp.add(ioclSupportedBaytype.getBayType());
		}
		return  Response.status(Response.Status.OK).entity(typesResp).build();
	}

	/*@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public Response getAvailableBays() throws IOCLWSException
	{
		AvailableBaysResponseBean availableBaysResponseBean=new AvailableBaysResponseBean();
		Set<Integer> emptyBays=new HashSet<Integer>();
		Set<Integer> queueBays=new HashSet<Integer>();
		Set<Integer> availableBaysResponse =new HashSet<Integer>();
		List<IoclBayDetail> listOfAllTheBays=iOCLBayDetailsDAO.findAllAvailableBaysInApplication();
		LOG.info("=======================Bays Logic Starts===============");
		LOG.info("List Of Available Bays In Application:::::::::"+listOfAllTheBays);
		for(IoclBayDetail ioclBayDetail:listOfAllTheBays)
		{
			//Check in past 24h any fan slip is generated for the bay in fan slip table.
			//If not just cross check BC operation table past 24h for that particular day and 
			//check latest record by desc is completed or not. If completed place the bay number in empty list.
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date currentDate = new Date();
			//			String currDate=dateFormat.format(currentDate);
			LOG.info("Current Date:::::"+dateFormat.format(currentDate));

			Calendar cal = Calendar.getInstance();
			cal.setTime(currentDate);
			cal.add(Calendar.HOUR, -24);
			Date hoursBack = cal.getTime();
			LOG.info("PastDate::::::"+dateFormat.format(hoursBack));
			//			String pasteDate=dateFormat.format(hoursBack);

			int bayNumber=ioclBayDetail.getBayNum();
			int queueCounter = 0;
			int supportedQueueSize=2;
			if(!(ioclBayDetail.getIoclSupportedBaystatus().getBayFunctionalStatus().equalsIgnoreCase("Not Active")))
			{
				List<IoclFanslipDetail> listOfPastFanslips=iOCLFanslipDetailsDAO.findAnyBayIsAssignedInPast(bayNumber,currentDate,hoursBack);
				LOG.info("findAnyBayIsAssignedInPast::::::::::"+listOfPastFanslips);

				//Case when no fan slip is generated. Consider this will only occur when the fan slip table is completely empty.
				//or in past 24h the bay might not be assigned for any of the truck.
				if(listOfPastFanslips.size()==0)
				{
					//cross check past 24h latest record, bay operational status is completed or not.
					//If completed, then treat bay as empty bay
					List<IoclBcBayoperation> listOfBCUpdates=iOCLBCBayOperationsDAO.findBayUpdatesByBC(bayNumber,currentDate,hoursBack);
					LOG.info("findBayUpdatesByBC::::::::"+listOfBCUpdates);
					if(listOfBCUpdates.size()==0)
					{
						emptyBays.add(bayNumber);
					}
					else
					{
						boolean allCompletedFlag=true;
						for(IoclBcBayoperation ioclBcBayoperation:listOfBCUpdates)
						{
							//else different status, then assign one more and keep in queue list
							//check top 10 records, count of different status, if greater then 2 put in queue
							if(!(ioclBcBayoperation.getOperationalStatus().equalsIgnoreCase("completed")))
							{
								allCompletedFlag=false;
								queueCounter=queueCounter+1;
							}
						}
						if(allCompletedFlag)
						{
							emptyBays.add(bayNumber);
						}
						if(queueCounter<supportedQueueSize)
						{
							//Add to the queue list
							queueBays.add(bayNumber);
						}
					}
				}
				else
				{

					Map<String,String> fanDetailsMap=new HashMap<String,String>();
					boolean allCompletedFlag=true;

					for(IoclFanslipDetail ioclFanslipDetail:listOfPastFanslips)
					{
						List<IoclBcBayoperation> listOfBCUpdates=iOCLBCBayOperationsDAO.findBayUpdatesByFanPin(ioclFanslipDetail.getFanPin());
						LOG.info("findBayUpdatesByFanPin::::::::"+listOfBCUpdates);
						//Fpin is generated but truck has not reached the point, so the BC table might not contain records for the truck.
						if(listOfBCUpdates.size()==0)
						{
							fanDetailsMap.put(ioclFanslipDetail.getFanPin(), "Not Reached");
						}
						else
						{
							for(IoclBcBayoperation ioclBcBayoperation:listOfBCUpdates)
							{
								fanDetailsMap.put(ioclFanslipDetail.getFanPin(), ioclBcBayoperation.getOperationalStatus());
							}
						}
					}

					for(Map.Entry<String,String> fanMap:fanDetailsMap.entrySet())
					{
						if(!(fanMap.getValue().equalsIgnoreCase("completed")) || fanMap.getValue().equalsIgnoreCase("Not Reached"))
						{
							allCompletedFlag=false;
							queueCounter=queueCounter+1;
						}
					}

					//If all the status in the map are completed then treat as empty bay
					if(allCompletedFlag)
					{
						emptyBays.add(bayNumber);
					}

					if(queueCounter<supportedQueueSize)
					{
						//Add to the queue list
						queueBays.add(bayNumber);
					}

				}		
			}			
		}

		if(!(emptyBays.size()<=0))
		{
			availableBaysResponse=emptyBays;
			availableBaysResponseBean.setCurrentBayAssignedStatus("EmptyBays");;
		}
		else if(!(queueBays.size()<=0))
		{
			availableBaysResponse=queueBays;
			availableBaysResponseBean.setCurrentBayAssignedStatus("Inqueued Bays");
		}
		else
		{
			availableBaysResponseBean.setCurrentBayAssignedStatus("No Bays Available. Please try after sometime");
			//No Bays available max queue size reached for all the bays.Please try after some time.
		}

		availableBaysResponseBean.setBayNumbers(availableBaysResponse);

		return  Response.status(Response.Status.OK).entity(availableBaysResponseBean).build();
	}
	 */

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,readOnly=false,rollbackFor=IOCLWSException.class)
	public Response bayCreation(String bayName,int bayNum,String bayType,String functionalStatus)  throws IOCLWSException
	{
		LOG.info("Entered Into Bay Creation Service Method......");
		//boolean inTransaction = TransactionSynchronizationManager.isActualTransactionActive();
		BayCreationResponseBean bayCreationResponseBean=new BayCreationResponseBean();
		try
		{
			IoclSupportedBaystatus ioclSupportedBaystatus=iOCLSupportedBayStatusDAO.findStatusIdByStatus(functionalStatus);

			//Check if the bay already exist in database.
			IoclBayDetail ioclBayDetail=iOCLBayDetailsDAO.findBayByBayNum(bayNum);
			LOG.info("ioclBayDetail:::::::"+ioclBayDetail);
			if(null==ioclBayDetail)
			{
				LOG.info("Before runnning insert statement");
				if(null!=ioclSupportedBaystatus)
				{
					iOCLBayDetailsDAO.insertBayDetails(bayName, bayNum, bayType, ioclSupportedBaystatus);
				}
				bayCreationResponseBean.setMessage("Bay SuccessFully Created : "+ bayName);
				bayCreationResponseBean.setSuccessFlag(true);
			}
			else
			{
				LOG.info("Bay Already Exist!!!!!!!");
				throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Bay_Already_Exist);
			}
		}
		catch(IOCLWSException ioclexception)
		{
			throw ioclexception;
		}
		catch(Exception exception)
		{
			LOG.info("Exception Occured in Bay Creation:::::::::"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
		return Response.status(Response.Status.OK).entity(bayCreationResponseBean).build();
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,readOnly=false,rollbackFor=IOCLWSException.class)
	public Response getAvailableBays() throws IOCLWSException
	{
		List<AvailableBaysResponseBean> emptyBays=new ArrayList<AvailableBaysResponseBean>();
		List<AvailableBaysResponseBean> queueBays=new ArrayList<AvailableBaysResponseBean>();

		List<IoclBayDetail> listOfAllTheBays=iOCLBayDetailsDAO.findAllAvailableBaysInApplication();
		LOG.info("=======================Bays Logic Starts===============");
		LOG.info("List Of Available Bays In Application:::::::::"+listOfAllTheBays);
		for(IoclBayDetail ioclBayDetail:listOfAllTheBays)
		{
			AvailableBaysResponseBean availableBaysResponseBean=new AvailableBaysResponseBean();
			List<FanslipsAssignedBean> listFanslipsAssignedBean=new ArrayList<FanslipsAssignedBean>();
			//Check in past 24h any fan slip is generated for the bay in fan slip table.
			//If not just cross check BC operation table past 24h for that particular day and 
			//check latest record by desc is completed or not. If completed place the bay number in empty list.
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date currentDate = new Date();
			//			String currDate=dateFormat.format(currentDate);
			LOG.info("Current Date:::::"+dateFormat.format(currentDate));

			Calendar cal = Calendar.getInstance();
			cal.setTime(currentDate);
			cal.add(Calendar.HOUR, -24);
			Date hoursBack = cal.getTime();
			LOG.info("PastDate::::::"+dateFormat.format(hoursBack));
			//String pasteDate=dateFormat.format(hoursBack);

			int bayNumber=ioclBayDetail.getBayNum();
			String bayName=ioclBayDetail.getBayName();
			String functionalStatus=ioclBayDetail.getIoclSupportedBaystatus().getBayFunctionalStatus();

			int queueCounter = 0;
			int supportedQueueSize=Integer.parseInt(bayQueueMaxSize);

			System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::"+supportedQueueSize);
			List<IoclFanslipDetail> listOfPastFanslips=iOCLFanslipDetailsDAO.findAnyBayIsAssignedInPast(bayNumber,currentDate,hoursBack);
			LOG.info("findAnyBayIsAssignedInPast::::::::::"+listOfPastFanslips);

			//Case when no fan slip is generated. Consider this will only occur when the fan slip table is completely empty.
			//or in past 24h the bay might not be assigned for any of the truck.
			if(listOfPastFanslips.size()==0)
			{
				//cross check past 24h latest record, bay operational status is completed or not.
				//If completed, then treat bay as empty bay
				List<IoclBcBayoperation> listOfBCUpdates=iOCLBCBayOperationsDAO.findBayUpdatesByBC(bayNumber,currentDate,hoursBack);
				LOG.info("findBayUpdatesByBC::::::::"+listOfBCUpdates);
				if(listOfBCUpdates.size()==0)
				{
					availableBaysResponseBean.setBayFunctionalStatus(functionalStatus);
					availableBaysResponseBean.setBayNumber(bayNumber);
					availableBaysResponseBean.setBayName(bayName);
					availableBaysResponseBean.setBayAvailableStatus("Empty");
					emptyBays.add(availableBaysResponseBean);
				}
				else
				{
					//This will never come because with fanslip is not generated for that particular day then no records will not present in BC table
					boolean allCompletedFlag=true;
					Set<String> pinSet=new HashSet<String>();
					for(IoclBcBayoperation ioclBcBayoperation:listOfBCUpdates)							
					{
						//else different status, then assign one more and keep in queue list
						//check top 10 records, count of different status, if greater then 2 put in queue
						if(!(ioclBcBayoperation.getOperationalStatus().equalsIgnoreCase("completed")) || !(ioclBcBayoperation.getOperationalStatus().equalsIgnoreCase("Errored Out")))
						{
							pinSet.add(ioclBcBayoperation.getFanPin());
							allCompletedFlag=false;
							queueCounter=queueCounter+1;
						}
					}
					if(allCompletedFlag)
					{
						availableBaysResponseBean.setBayFunctionalStatus(functionalStatus);
						availableBaysResponseBean.setBayNumber(bayNumber);
						availableBaysResponseBean.setBayName(bayName);
						availableBaysResponseBean.setBayAvailableStatus("Empty");
						emptyBays.add(availableBaysResponseBean);
					}
					else
					{
						for(String pinMap : pinSet)
						{
							FanslipsAssignedBean fanslipsAssignedBean=new FanslipsAssignedBean();
							if(pinSet.size()<supportedQueueSize)
							{
								fanslipsAssignedBean.setFanPin(pinMap);
								IoclFanslipDetail ioclFanslipDetail=iOCLFanslipDetailsDAO.findFanPinStatusByFanPin(pinMap);
								fanslipsAssignedBean.setFanPinStatus(ioclFanslipDetail.getIoclSupportedPinstatus().getFanPinStatus());
								listFanslipsAssignedBean.add(fanslipsAssignedBean);
								availableBaysResponseBean.setBayNumber(bayNumber);
								availableBaysResponseBean.setBayName(bayName);
								availableBaysResponseBean.setBayFunctionalStatus(functionalStatus);
								availableBaysResponseBean.setBayAvailableStatus("InQueue");
								availableBaysResponseBean.setFanslipsAssignedBean(listFanslipsAssignedBean);

								//queueBays.add(availableBaysResponseBean);
							}
							else if(pinSet.size()==supportedQueueSize)
							{
								fanslipsAssignedBean.setFanPin(pinMap);
								IoclFanslipDetail ioclFanslipDetail=iOCLFanslipDetailsDAO.findFanPinStatusByFanPin(pinMap);
								fanslipsAssignedBean.setFanPinStatus(ioclFanslipDetail.getIoclSupportedPinstatus().getFanPinStatus());
								listFanslipsAssignedBean.add(fanslipsAssignedBean);
								availableBaysResponseBean.setBayNumber(bayNumber);
								availableBaysResponseBean.setBayName(bayName);
								availableBaysResponseBean.setBayFunctionalStatus(functionalStatus);
								availableBaysResponseBean.setBayAvailableStatus("Not Available");
								availableBaysResponseBean.setFanslipsAssignedBean(listFanslipsAssignedBean);
								//queueBays.add(availableBaysResponseBean);
							}
						}
						queueBays.add(availableBaysResponseBean);
					}
				}
			}
			else
			{
				HashMap<String,String> fanDetailsMap=new HashMap<String,String>();
				boolean allCompletedFlag=true;
				Set<String> pinSet=new HashSet<String>();
				LOG.info("listOfPastFanslips:::::::::"+listOfPastFanslips.size());
				for(IoclFanslipDetail ioclFanslipDetail:listOfPastFanslips)
				{
					List<IoclBcBayoperation> listOfBCUpdates=iOCLBCBayOperationsDAO.findBayUpdatesByFanPin(ioclFanslipDetail.getFanPin());
					LOG.info("findBayUpdatesByFanPin::::::::"+listOfBCUpdates.size());
					//Fpin is generated but truck has not reached the point, so the BC table might not contain records for the truck.
					if(listOfBCUpdates.size()==0)
					{
						fanDetailsMap.put(ioclFanslipDetail.getFanPin(), "Not Reached");
					}
					else
					{
						for(IoclBcBayoperation ioclBcBayoperation:listOfBCUpdates)
						{
							fanDetailsMap.put(ioclFanslipDetail.getFanPin(), ioclBcBayoperation.getOperationalStatus());
						}
					}
				}

				for(Map.Entry<String,String> fanMap:fanDetailsMap.entrySet())
				{
					LOG.info("GET KEY::::"+fanMap.getKey()+"Get Val::::"+fanMap.getValue());
					if(!(fanMap.getValue().equalsIgnoreCase("completed")) /*|| (fanMap.getValue().equalsIgnoreCase("Not Reached")*/)
					{
						pinSet.add(fanMap.getKey());
						allCompletedFlag=false;
						queueCounter=queueCounter+1;
					}
				}

				//If all the status in the map are completed then treat as empty bay
				if(allCompletedFlag)
				{
					availableBaysResponseBean.setBayFunctionalStatus(functionalStatus);
					availableBaysResponseBean.setBayNumber(bayNumber);
					availableBaysResponseBean.setBayName(bayName);
					availableBaysResponseBean.setBayAvailableStatus("Empty");
					emptyBays.add(availableBaysResponseBean);
				}
				else
				{
					LOG.info("Map Count::::::::"+pinSet.size());
					for(String fanpin : pinSet)
					{
						FanslipsAssignedBean fanslipsAssignedBean=new FanslipsAssignedBean();
						LOG.info("GET Pins::::"+pinSet);
						if(pinSet.size()<supportedQueueSize)
						{
							fanslipsAssignedBean.setFanPin(fanpin);
							IoclFanslipDetail ioclFanslipDetail=iOCLFanslipDetailsDAO.findFanPinStatusByFanPin(fanpin);
							fanslipsAssignedBean.setFanPinStatus(ioclFanslipDetail.getIoclSupportedPinstatus().getFanPinStatus());
							listFanslipsAssignedBean.add(fanslipsAssignedBean);
							availableBaysResponseBean.setBayAvailableStatus("InQueue");
							availableBaysResponseBean.setBayNumber(bayNumber);
							availableBaysResponseBean.setBayName(bayName);
							availableBaysResponseBean.setBayFunctionalStatus(functionalStatus);
							availableBaysResponseBean.setBayAvailableStatus("InQueue");
							availableBaysResponseBean.setFanslipsAssignedBean(listFanslipsAssignedBean);
							LOG.info("Before Adding once:::::"+availableBaysResponseBean);
						}
						else if(pinSet.size()==supportedQueueSize)
						{
							fanslipsAssignedBean.setFanPin(fanpin);
							IoclFanslipDetail ioclFanslipDetail=iOCLFanslipDetailsDAO.findFanPinStatusByFanPin(fanpin);
							fanslipsAssignedBean.setFanPinStatus(ioclFanslipDetail.getIoclSupportedPinstatus().getFanPinStatus());
							listFanslipsAssignedBean.add(fanslipsAssignedBean);
							availableBaysResponseBean.setBayAvailableStatus("Not Available");
							availableBaysResponseBean.setBayNumber(bayNumber);
							availableBaysResponseBean.setBayName(bayName);
							availableBaysResponseBean.setBayFunctionalStatus(functionalStatus);
							availableBaysResponseBean.setBayAvailableStatus("Not Available");
							availableBaysResponseBean.setFanslipsAssignedBean(listFanslipsAssignedBean);
							LOG.info("Equal::::: Adding once:::::"+availableBaysResponseBean);
						}
					}
					LOG.info("Before Adding once:::::"+availableBaysResponseBean);
					queueBays.add(availableBaysResponseBean);
				}
			}
		}

		LOG.info("Before Merge:::::::::::::emptyBays:::::"+emptyBays);
		LOG.info("Before Merge:::::::::::::queueBays:::::"+queueBays);

		List<AvailableBaysResponseBean> listAvailableBaysResponseBean=new ArrayList<AvailableBaysResponseBean>(emptyBays);
		listAvailableBaysResponseBean.addAll(queueBays);
		LOG.info("After Merge:::::::::::::queueBays:::::"+listAvailableBaysResponseBean);
		return  Response.status(Response.Status.OK).entity(listAvailableBaysResponseBean).build();
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,readOnly=false,rollbackFor=IOCLWSException.class)
	public Response deleteBay(int bayNum)
	{

		boolean deleteFalg=iOCLBayDetailsDAO.deleteBay(bayNum);

		if(deleteFalg)
		{
			return  Response.status(Response.Status.OK).entity("Deleted Success Fully").build();	
		}
		else
		{
			return  Response.status(Response.Status.OK).entity("Failed To Delete").build();
		}
	}


	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,readOnly=false,rollbackFor=IOCLWSException.class)
	public Response supportedBayTypes() throws  IOCLWSException, Exception
	{
		try
		{
			List<IoclSupportedBaytype> lIoclSupportedBayTypes=iOCLSupportedBayTypesDAO.findAll(IoclSupportedBaytype.class);
			List<String> bayTypes=new ArrayList<String>();
			for(IoclSupportedBaytype ioclSupportedBaytype:lIoclSupportedBayTypes)
			{
				bayTypes.add(ioclSupportedBaytype.getBayType());
			}
			return  Response.status(Response.Status.OK).entity(bayTypes).build();
		}
		catch(Exception exception)
		{
			LOG.info("Supported BayTypes Service Method Exception Block:::::::"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,readOnly=false,rollbackFor=IOCLWSException.class)
	public Response supportedBayFunctionalStatus() throws  IOCLWSException, Exception
	{
		try
		{
			List<IoclSupportedBaystatus> lIoclSupportedBaystatus=iOCLSupportedBayStatusDAO.findAll(IoclSupportedBaystatus.class);
			List<String> bayStatus=new ArrayList<String>();
			for(IoclSupportedBaystatus ioclSupportedBaystatus:lIoclSupportedBaystatus)
			{
				bayStatus.add(ioclSupportedBaystatus.getBayFunctionalStatus());
			}
			return  Response.status(Response.Status.OK).entity(bayStatus).build();
		}
		catch(Exception exception)
		{
			LOG.info("Supported BayStatus Service Method Exception Block:::::::"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}
}