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
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;
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
import com.rainiersoft.iocl.dao.IOCLBayTypeDAO;
import com.rainiersoft.iocl.dao.IOCLFanslipDetailsDAO;
import com.rainiersoft.iocl.dao.IOCLSupportedBayStatusDAO;
import com.rainiersoft.iocl.dao.IOCLSupportedBayTypesDAO;
import com.rainiersoft.iocl.dao.IOCLTruckRegistrationDetailsDAO;
import com.rainiersoft.iocl.dao.IOCLUserDetailsDAO;
import com.rainiersoft.iocl.entity.IoclBayDetail;
import com.rainiersoft.iocl.entity.IoclBcBayoperation;
import com.rainiersoft.iocl.entity.IoclFanslipDetail;
import com.rainiersoft.iocl.entity.IoclSupportedBaystatus;
import com.rainiersoft.iocl.entity.IoclSupportedBaytype;
import com.rainiersoft.iocl.entity.IoclTruckregistrationDetail;
import com.rainiersoft.iocl.entity.IoclUserDetail;
import com.rainiersoft.iocl.exception.IOCLWSException;
import com.rainiersoft.iocl.util.ErrorMessageConstants;
import com.rainiersoft.response.dto.AllBayDetailsResponseBean;
import com.rainiersoft.response.dto.AvailableBaysResponseBean;
import com.rainiersoft.response.dto.BayCreationResponseBean;
import com.rainiersoft.response.dto.BayDeletionResponseBean;
import com.rainiersoft.response.dto.CurrentBayOperationResponseBean;
import com.rainiersoft.response.dto.GetBayStaticDataResponseBean;


/**
 * This is the class for Bays Management Services
 * @author RahulKumarPamidi
 */

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

	@Autowired
	IOCLBayTypeDAO iOCLBayTypeDAO;

	@Autowired
	IOCLTruckRegistrationDetailsDAO iOCLTruckRegistrationDetailsDAO;

	@Autowired
	IOCLUserDetailsDAO iOCLUserDetailsDAO;

	@Resource
	Properties appProps;

	@Value("${BayQueueMaxSize}")
	String bayQueueMaxSize;

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=IOCLWSException.class)
	public Response getAllBayDetails() throws IOCLWSException
	{
		LOG.info("Entered into getAllBayDetails service class method........");
		try
		{
			List<AllBayDetailsResponseBean> listAllBayDetailsResponseBean=new ArrayList<AllBayDetailsResponseBean>();
			List<IoclBayDetail> listOfIocBayDetails=iOCLBayDetailsDAO.findAllAvailableBaysInApplication();
			DateFormat df = new SimpleDateFormat(appProps.getProperty("AppDateFormat"));
			for(IoclBayDetail ioclBayDetail:listOfIocBayDetails)
			{
				AllBayDetailsResponseBean allBayDetailsResponseBean=new AllBayDetailsResponseBean();
				allBayDetailsResponseBean.setBayId(ioclBayDetail.getBayId());
				allBayDetailsResponseBean.setBayName(ioclBayDetail.getBayName());
				allBayDetailsResponseBean.setBayNum(ioclBayDetail.getBayNum());
				int bayTypeId=ioclBayDetail.getIoclBayTypes().get(0).getBayTypeId();
				IoclSupportedBaytype ioclSupportedBaytype=iOCLSupportedBayTypesDAO.findBayTypeByBayTypeId(bayTypeId);
				allBayDetailsResponseBean.setBayType(ioclSupportedBaytype.getBayType());
				allBayDetailsResponseBean.setFunctionalStatus(ioclBayDetail.getIoclSupportedBaystatus().getBayFunctionalStatus());

				if(ioclBayDetail.getBayUpdatedBy()!=0)
				{
					IoclUserDetail ioclUserDetail=iOCLUserDetailsDAO.findUserByUserId(ioclBayDetail.getBayUpdatedBy());
					allBayDetailsResponseBean.setBayUpdatedBy(ioclUserDetail.getUserName());
					allBayDetailsResponseBean.setBayUpdatedOn(df.format(ioclBayDetail.getBayUpdatedOn()));
				}
				if(ioclBayDetail.getBayCreatedBy()!=0)
				{
					IoclUserDetail ioclUserDetail=iOCLUserDetailsDAO.findUserByUserId(ioclBayDetail.getBayCreatedBy());
					allBayDetailsResponseBean.setBayCreatedBy(ioclUserDetail.getUserName());
					allBayDetailsResponseBean.setBayCreatedOn(df.format(ioclBayDetail.getBayCreatedOn()));
				}
				listAllBayDetailsResponseBean.add(allBayDetailsResponseBean);
			}
			return  Response.status(Response.Status.OK).entity(listAllBayDetailsResponseBean).build();
		}
		catch(Exception exception)
		{
			LOG.info("Logging the occured exception in the service class getAllBayDetails method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=IOCLWSException.class)
	public Response getBayStatus() throws IOCLWSException
	{
		LOG.info("Entered into getBayStatus service class method........");
		try
		{
			List<IoclSupportedBaystatus> listOfStatus=iOCLSupportedBayStatusDAO.findAllSupportedBayStatus();
			List<String> statusResp=new ArrayList<String>();
			for(IoclSupportedBaystatus ioclSupportedBaystatus:listOfStatus)
			{
				statusResp.add(ioclSupportedBaystatus.getBayFunctionalStatus());
			}
			return  Response.status(Response.Status.OK).entity(statusResp).build();
		}
		catch(Exception exception)
		{
			LOG.info("Logging the occured exception in the service class getBayStatus method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=IOCLWSException.class)
	public Response getBayTypes() throws IOCLWSException
	{
		LOG.info("Entered into getBayTypes service class method........");
		try
		{
			List<IoclSupportedBaytype> listOfTypes=iOCLSupportedBayTypesDAO.findAllSupportedBayTypes();	
			List<String> typesResp=new ArrayList<String>();
			for(IoclSupportedBaytype ioclSupportedBaytype:listOfTypes)
			{
				typesResp.add(ioclSupportedBaytype.getBayType());
			}
			return  Response.status(Response.Status.OK).entity(typesResp).build();
		}
		catch(Exception exception)
		{
			LOG.info("Logging the occured exception in the service class getBayTypes method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
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
	public Response bayCreation(String bayName,int bayNum,String bayType,String functionalStatus,String userName)  throws IOCLWSException
	{
		LOG.info("Entered into bayCreation service class method........");
		BayCreationResponseBean bayCreationResponseBean=new BayCreationResponseBean();
		try
		{
			IoclBayDetail ioclBayDetailBayName=iOCLBayDetailsDAO.findBayByBayName(bayName);
			if(ioclBayDetailBayName!=null)
			{
				throw new IOCLWSException(Response.Status.CONFLICT.getStatusCode(),ErrorMessageConstants.BayName_Already_Exist_Msg);
			}
			IoclBayDetail ioclBayDetail=iOCLBayDetailsDAO.findBayByBayNum(bayNum);
			LOG.info("ioclBayDetail:::::::"+ioclBayDetail);
			if(ioclBayDetail!=null)
			{
				throw new IOCLWSException(Response.Status.CONFLICT.getStatusCode(),ErrorMessageConstants.BayNum_Already_Exist_Msg);
			}
			else
			{
				IoclSupportedBaystatus ioclSupportedBaystatus=iOCLSupportedBayStatusDAO.findStatusIdByStatus(functionalStatus);
				if(null!=ioclSupportedBaystatus)
				{
					LOG.info("createdBy::::::"+userName);
					IoclUserDetail ioclUserDetail=iOCLUserDetailsDAO.findUserByUserName(userName);
					LOG.info("ioclUserDetail:::::::"+ioclUserDetail);
					int userID=ioclUserDetail.getUserId();
					LOG.info("usrId:::"+userID);

					DateFormat dateFormat = new SimpleDateFormat(appProps.getProperty("AppDateFormat"));
					Date bayCreatedDateobj = new Date();

					IoclSupportedBaytype ioclSupportedBaytype=iOCLSupportedBayTypesDAO.findBayTypeIdByBayType(bayType);
					int bayTypeId=ioclSupportedBaytype.getTypeId();
					Long bayId=iOCLBayDetailsDAO.insertBayDetails(bayName, bayNum, bayTypeId, ioclSupportedBaystatus,userID,bayCreatedDateobj);
					bayCreationResponseBean.setBayId(bayId);
					bayCreationResponseBean.setBayName(bayName);
					bayCreationResponseBean.setBayNum(bayNum);
					bayCreationResponseBean.setBayType(bayType);
					bayCreationResponseBean.setBayStatus(functionalStatus);
					bayCreationResponseBean.setUserName(userName);
					bayCreationResponseBean.setTimeStamp(dateFormat.format(bayCreatedDateobj));
					bayCreationResponseBean.setMessage("Bay SuccessFully Created : "+ bayName);
					bayCreationResponseBean.setSuccessFlag(true);
				}
			}
		}
		catch(IOCLWSException ioclexception)
		{
			LOG.info("Logging the occured exception in the service class bayCreation method custom catch block........"+ioclexception);
			throw ioclexception;
		}
		catch(Exception exception)
		{
			LOG.info("Logging the occured exception in the service class bayCreation method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
		return Response.status(Response.Status.OK).entity(bayCreationResponseBean).build();
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,readOnly=false,rollbackFor=IOCLWSException.class)
	public Response bayUpdation(int bayId,String bayName,int bayNum,String bayType,String functionalStatus,boolean bayNumEditFlag,boolean bayNameEditFlag,String userName) throws IOCLWSException
	{
		LOG.info("Entered into bayUpdation service class method........");
		try
		{
			BayCreationResponseBean bayUpdationResponseBean=new BayCreationResponseBean();
			List<IoclBayDetail> lIoclBayDetail=iOCLBayDetailsDAO.findAllAvailableBaysInApplication();
			List<String> listBayNames=new ArrayList<String>();
			List<Integer> listBayNums=new ArrayList<Integer>();
			for(IoclBayDetail ioclBayDetail:lIoclBayDetail)
			{
				listBayNames.add(ioclBayDetail.getBayName());
				listBayNums.add(ioclBayDetail.getBayNum());
			}

			boolean bayNameExistFlag=false;
			boolean bayNumExistFlag=false;

			for(String bayNames : listBayNames)
			{
				if(bayNames.equalsIgnoreCase(bayName))
				{
					bayNameExistFlag=true;
					break;
				}
			}
			for(int bayNums : listBayNums)
			{
				if(bayNums==bayNum)
				{
					bayNumExistFlag=true;
					break;
				}
			}

			if(bayNameEditFlag && bayNameExistFlag)
			{
				throw new IOCLWSException(Response.Status.CONFLICT.getStatusCode(),ErrorMessageConstants.BayName_Already_Exist_Msg);

			}else if(bayNumEditFlag && bayNumExistFlag)
			{
				throw new IOCLWSException(Response.Status.CONFLICT.getStatusCode(),ErrorMessageConstants.BayNum_Already_Exist_Msg);
			}
			else
			{
				IoclSupportedBaystatus ioclSupportedBaystatus=iOCLSupportedBayStatusDAO.findStatusIdByStatus(functionalStatus);
				IoclSupportedBaytype ioclSupportedBaytype=iOCLSupportedBayTypesDAO.findBayTypeIdByBayType(bayType);
				System.out.println("ioclSupportedBaytype:::::::"+ioclSupportedBaytype);

				int bayTypeId=ioclSupportedBaytype.getTypeId();
				LOG.info("bayId:::::::"+bayId);

				IoclBayDetail ioclBayDetail=iOCLBayDetailsDAO.findBayByBayId(bayId);
				System.out.println(":::::"+ioclBayDetail.getIoclBayTypes().get(0));

				LOG.info("createdBy::::::"+userName);
				IoclUserDetail ioclUserDetail=iOCLUserDetailsDAO.findUserByUserName(userName);
				LOG.info("ioclUserDetail:::::::"+ioclUserDetail);
				int userID=ioclUserDetail.getUserId();
				LOG.info("usrId:::"+userID);

				DateFormat dateFormat = new SimpleDateFormat(appProps.getProperty("AppDateFormat"));
				Date bayUpdatedDateobj = new Date();

				iOCLBayDetailsDAO.updateBayDetails(bayName, bayNum,bayTypeId, ioclSupportedBaystatus,ioclBayDetail,ioclSupportedBaytype,userID,bayUpdatedDateobj);

				bayUpdationResponseBean.setBayId(Long.valueOf(bayId));
				bayUpdationResponseBean.setBayName(bayName);
				bayUpdationResponseBean.setBayNum(bayNum);
				bayUpdationResponseBean.setBayType(bayType);
				bayUpdationResponseBean.setBayStatus(functionalStatus);
				bayUpdationResponseBean.setSuccessFlag(true);
				bayUpdationResponseBean.setUserName(userName);
				bayUpdationResponseBean.setTimeStamp(dateFormat.format(bayUpdatedDateobj));
				bayUpdationResponseBean.setMessage("Bay Updated Successfully");
			}
			return Response.status(Response.Status.OK).entity(bayUpdationResponseBean).build();
		}
		catch(IOCLWSException ioclwsException)
		{
			LOG.info("Logging the occured exception in the service class bayUpdation method custom catch block........"+ioclwsException);
			throw ioclwsException;
		}
		catch (Exception exception) 
		{
			LOG.info("Logging the occured exception in the service class bayUpdation method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,readOnly=false,rollbackFor=IOCLWSException.class)
	public Response getAvailableBays() throws IOCLWSException
	{
		LOG.info("Entered into getAvailableBays service class method........");
		try
		{
			List<AvailableBaysResponseBean> emptyBays=new ArrayList<AvailableBaysResponseBean>();
			List<AvailableBaysResponseBean> queueBays=new ArrayList<AvailableBaysResponseBean>();

			List<IoclBayDetail> listOfAllTheBays=iOCLBayDetailsDAO.findAllAvailableBaysInApplication();
			LOG.info("=======================Bays Logic Starts===============");
			LOG.info("List Of Available Bays In Application:::::::::"+listOfAllTheBays);
			for(IoclBayDetail ioclBayDetail:listOfAllTheBays)
			{
				LOG.info("BayNums Iteration........"+ioclBayDetail.getBayNum());
				AvailableBaysResponseBean availableBaysResponseBean=new AvailableBaysResponseBean();
				//List<FanslipsAssignedBean> listFanslipsAssignedBean=new ArrayList<FanslipsAssignedBean>();

				//Check in past 24h any fan slip is generated for the bay in fan slip table.
				//If not just cross check BC operation table past 24h for that particular day and 
				//check latest record by desc is completed or not. If completed place the bay number in empty list.
				DateFormat dateFormat = new SimpleDateFormat(appProps.getProperty("AppDateFormat"));
				Date currentDate = new Date();
				LOG.info("Current Date:::::"+dateFormat.format(currentDate));

				Calendar cal = Calendar.getInstance();
				cal.setTime(currentDate);
				cal.add(Calendar.HOUR, Integer.parseInt(appProps.getProperty("GetAvailableBaysPastHours")));
				Date hoursBack = cal.getTime();
				LOG.info("PastDate::::::"+dateFormat.format(hoursBack));

				int bayNumber=ioclBayDetail.getBayNum();
				String bayName=ioclBayDetail.getBayName();
				String functionalStatus=ioclBayDetail.getIoclSupportedBaystatus().getBayFunctionalStatus();

				int queueCounter = 0;
				int supportedQueueSize=Integer.parseInt(bayQueueMaxSize);

				LOG.info("supportedQueueSize::::::::::::::::::::::::::::::::::::::::::::::::::::"+supportedQueueSize);
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
						availableBaysResponseBean.setBayAvailableStatus(appProps.getProperty("BayAvailableEmptyFlag"));
						emptyBays.add(availableBaysResponseBean);
					}
					else
					{
						//This will never come because without fanslip not generated for that particular day then no records will not present in BC table
						LOG.info("This will never come because without fanslip not generated for that particular day then no records will not present in BC table");
						boolean allCompletedFlag=true;
						Set<String> pinSet=new HashSet<String>();
						for(IoclBcBayoperation ioclBcBayoperation:listOfBCUpdates)							
						{
							//else different status, then assign one more and keep in queue list
							//check top 10 records, count of different status, if greater then 2 put in queue
							IoclFanslipDetail ioclFanslipDetail=iOCLFanslipDetailsDAO.findFanPinStatusByFanPin(ioclBcBayoperation.getFanPin());
							if((ioclFanslipDetail.getIoclSupportedPinstatus().getFanPinStatus().equalsIgnoreCase("Expired")) || (ioclFanslipDetail.getIoclSupportedPinstatus().getFanPinStatus().equalsIgnoreCase("Cancelled")))
							{
								LOG.info("Fanslip is expired continue......");
								continue;
							}

							if(!(ioclBcBayoperation.getIoclSupportedBayoperationalstatus().getOperationalStatus().equalsIgnoreCase("completed")))
							{
								LOG.info("Add the fanslip........");
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
							availableBaysResponseBean.setBayAvailableStatus(appProps.getProperty("BayAvailableEmptyFlag"));
							emptyBays.add(availableBaysResponseBean);
						}
						else
						{
							/*for(String pinMap : pinSet)
							{*/
							//FanslipsAssignedBean fanslipsAssignedBean=new FanslipsAssignedBean();
							LOG.info("pinSetSize......"+pinSet.size());
							if(pinSet.size()<supportedQueueSize)
							{
								//fanslipsAssignedBean.setFanPin(pinMap);
								//IoclFanslipDetail ioclFanslipDetail=iOCLFanslipDetailsDAO.findFanPinStatusByFanPin(pinMap);
								//fanslipsAssignedBean.setFanPinStatus(ioclFanslipDetail.getIoclSupportedPinstatus().getFanPinStatus());
								//listFanslipsAssignedBean.add(fanslipsAssignedBean);
								availableBaysResponseBean.setBayNumber(bayNumber);
								availableBaysResponseBean.setBayName(bayName);
								availableBaysResponseBean.setBayFunctionalStatus(functionalStatus);
								availableBaysResponseBean.setBayAvailableStatus(appProps.getProperty("BayAvailableInqueueFlag"));
								//availableBaysResponseBean.setFanslipsAssignedBean(listFanslipsAssignedBean);
							}
							else if(pinSet.size()==supportedQueueSize)
							{
								//fanslipsAssignedBean.setFanPin(pinMap);
								//IoclFanslipDetail ioclFanslipDetail=iOCLFanslipDetailsDAO.findFanPinStatusByFanPin(pinMap);
								//fanslipsAssignedBean.setFanPinStatus(ioclFanslipDetail.getIoclSupportedPinstatus().getFanPinStatus());
								//listFanslipsAssignedBean.add(fanslipsAssignedBean);
								availableBaysResponseBean.setBayNumber(bayNumber);
								availableBaysResponseBean.setBayName(bayName);
								availableBaysResponseBean.setBayFunctionalStatus(functionalStatus);
								availableBaysResponseBean.setBayAvailableStatus(appProps.getProperty("BayNotAvailableFlag"));
								//availableBaysResponseBean.setFanslipsAssignedBean(listFanslipsAssignedBean);
							}
							//}
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
							if(!(ioclFanslipDetail.getIoclSupportedPinstatus().getFanPinStatus().equalsIgnoreCase("Expired")) && !(ioclFanslipDetail.getIoclSupportedPinstatus().getFanPinStatus().equalsIgnoreCase("Cancelled")))
							{
								LOG.info("ioclFanslipDetail.getIoclSupportedPinstatus...."+ioclFanslipDetail.getIoclSupportedPinstatus().getFanPinStatus());
								fanDetailsMap.put(ioclFanslipDetail.getFanPin(), appProps.getProperty("NOT_REACHED"));
							}
						}
						else
						{
							for(IoclBcBayoperation ioclBcBayoperation:listOfBCUpdates)
							{
								LOG.info("Else block IoclFanslipDetail.getIoclSupportedPinstatus...."+ioclFanslipDetail.getIoclSupportedPinstatus().getFanPinStatus());
								if(!(ioclFanslipDetail.getIoclSupportedPinstatus().getFanPinStatus().equalsIgnoreCase("Expired")) && !(ioclFanslipDetail.getIoclSupportedPinstatus().getFanPinStatus().equalsIgnoreCase("Cancelled")))
								{
									fanDetailsMap.put(ioclFanslipDetail.getFanPin(), ioclBcBayoperation.getIoclSupportedBayoperationalstatus().getOperationalStatus());
								}
							}
						}
					}

					for(Map.Entry<String,String> fanMap:fanDetailsMap.entrySet())
					{
						LOG.info("GET KEY::::"+fanMap.getKey()+"Get Val::::"+fanMap.getValue());
						if(!(fanMap.getValue().equalsIgnoreCase(appProps.getProperty("FanPinFillingCompletedStatusFromBC"))) /*|| (fanMap.getValue().equalsIgnoreCase("Not Reached")*/)
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
						availableBaysResponseBean.setBayAvailableStatus(appProps.getProperty("BayAvailableEmptyFlag"));
						emptyBays.add(availableBaysResponseBean);
					}
					else
					{
						LOG.info("Map Count::::::::"+pinSet.size());
						/*for(String fanpin : pinSet)
						{*/
						//FanslipsAssignedBean fanslipsAssignedBean=new FanslipsAssignedBean();
						LOG.info("GET Pins::::"+pinSet);
						LOG.info("pinSetSize......"+pinSet.size());
						if(pinSet.size()<supportedQueueSize)
						{
							/*fanslipsAssignedBean.setFanPin(fanpin);
								IoclFanslipDetail ioclFanslipDetail=iOCLFanslipDetailsDAO.findFanPinStatusByFanPin(fanpin);
								fanslipsAssignedBean.setFanPinStatus(ioclFanslipDetail.getIoclSupportedPinstatus().getFanPinStatus());
								fanslipsAssignedBean.setFanPinStatus(ioclFanslipDetail.getIoclSupportedPinstatus().getFanPinStatus());
								fanslipsAssignedBean.setContractorName(ioclFanslipDetail.getIoclContractorDetail().getContractorName());
								fanslipsAssignedBean.setDestination(ioclFanslipDetail.getDestination());
								fanslipsAssignedBean.setFanId(ioclFanslipDetail.getFanId());
								fanslipsAssignedBean.setFanPin(ioclFanslipDetail.getFanPin());
								fanslipsAssignedBean.setFanPinCreation(dateFormat.format(ioclFanslipDetail.getFanCreationOn()));
								fanslipsAssignedBean.setFanPinExpiration(dateFormat.format(ioclFanslipDetail.getFanExpirationOn()));
								fanslipsAssignedBean.setLocationCode(ioclFanslipDetail.getIoclLocationDetail().getLocationCode());
								fanslipsAssignedBean.setQuantity(ioclFanslipDetail.getQuantity());
								fanslipsAssignedBean.setBayOperationalStatus(fanDetailsMap.get(fanpin));
								IoclTruckregistrationDetail ioclTruckDetails=iOCLTruckRegistrationDetailsDAO.findTruckByTruckId(ioclFanslipDetail.getTruckId());
								fanslipsAssignedBean.setTruckNumber(ioclTruckDetails.getTruckNo());
								fanslipsAssignedBean.setDriverName(ioclTruckDetails.getDriverName());
								fanslipsAssignedBean.setCustomer(ioclTruckDetails.getCustomer());
								fanslipsAssignedBean.setVehicleWeight(ioclFanslipDetail.getVehicleWgt());
								listFanslipsAssignedBean.add(fanslipsAssignedBean);
							 */
							availableBaysResponseBean.setBayAvailableStatus(appProps.getProperty("BayAvailableInqueueFlag"));
							availableBaysResponseBean.setBayNumber(bayNumber);
							availableBaysResponseBean.setBayName(bayName);
							availableBaysResponseBean.setBayFunctionalStatus(functionalStatus);
							//availableBaysResponseBean.setFanslipsAssignedBean(listFanslipsAssignedBean);
						}
						else if(pinSet.size()==supportedQueueSize)
						{
							/*fanslipsAssignedBean.setFanPin(fanpin);
								IoclFanslipDetail ioclFanslipDetail=iOCLFanslipDetailsDAO.findFanPinStatusByFanPin(fanpin);
								fanslipsAssignedBean.setFanPinStatus(ioclFanslipDetail.getIoclSupportedPinstatus().getFanPinStatus());
								fanslipsAssignedBean.setContractorName(ioclFanslipDetail.getIoclContractorDetail().getContractorName());
								fanslipsAssignedBean.setDestination(ioclFanslipDetail.getDestination());
								fanslipsAssignedBean.setFanId(ioclFanslipDetail.getFanId());
								fanslipsAssignedBean.setFanPin(ioclFanslipDetail.getFanPin());
								fanslipsAssignedBean.setFanPinCreation(dateFormat.format(ioclFanslipDetail.getFanCreationOn()));
								fanslipsAssignedBean.setFanPinExpiration(dateFormat.format(ioclFanslipDetail.getFanExpirationOn()));
								fanslipsAssignedBean.setLocationCode(ioclFanslipDetail.getIoclLocationDetail().getLocationCode());
								fanslipsAssignedBean.setQuantity(ioclFanslipDetail.getQuantity());
								IoclTruckregistrationDetail ioclTruckDetails=iOCLTruckRegistrationDetailsDAO.findTruckByTruckId(ioclFanslipDetail.getTruckId());
								fanslipsAssignedBean.setTruckNumber(ioclTruckDetails.getTruckNo());
								fanslipsAssignedBean.setDriverName(ioclTruckDetails.getDriverName());
								fanslipsAssignedBean.setCustomer(ioclTruckDetails.getCustomer());
								fanslipsAssignedBean.setVehicleWeight(ioclFanslipDetail.getVehicleWgt());
								listFanslipsAssignedBean.add(fanslipsAssignedBean);*/
							availableBaysResponseBean.setBayAvailableStatus(appProps.getProperty("BayNotAvailableFlag"));
							availableBaysResponseBean.setBayNumber(bayNumber);
							availableBaysResponseBean.setBayName(bayName);
							availableBaysResponseBean.setBayFunctionalStatus(functionalStatus);
							//availableBaysResponseBean.setFanslipsAssignedBean(listFanslipsAssignedBean);
						}
						//}
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
		catch (Exception exception) 
		{
			LOG.info("Logging the occured exception in the service class getAvailableBays method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,readOnly=false,rollbackFor=IOCLWSException.class)
	public Response deleteBay(int bayID) throws IOCLWSException
	{
		LOG.info("Entered into deleteBay service class method........");
		try
		{
			BayDeletionResponseBean  bayDeletionResponseBean=new BayDeletionResponseBean();
			boolean deleteFalg=iOCLBayDetailsDAO.deleteBay(bayID);
			if(deleteFalg)
			{
				bayDeletionResponseBean.setSuccessFlag(true);
				bayDeletionResponseBean.setMsg("Bay Deleted SuccessFully");
				return  Response.status(Response.Status.OK).entity(bayDeletionResponseBean).build();	
			}
			else
			{
				bayDeletionResponseBean.setSuccessFlag(false);
				bayDeletionResponseBean.setMsg("Failed to delete bay");
				return  Response.status(Response.Status.OK).entity(bayDeletionResponseBean).build();
			}
		}
		catch (Exception exception) 
		{
			LOG.info("Logging the occured exception in the service class deleteBay method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,readOnly=false,rollbackFor=IOCLWSException.class)
	public Response supportedBayTypes() throws  IOCLWSException, Exception
	{
		LOG.info("Entered into supportedBayTypes service class method........");
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
			LOG.info("Logging the occured exception in the service class supportedBayTypes method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,readOnly=false,rollbackFor=IOCLWSException.class)
	public Response supportedBayFunctionalStatus() throws  IOCLWSException, Exception
	{
		LOG.info("Entered into supportedBayFunctionalStatus service class method........");
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
			LOG.info("Logging the occured exception in the service class supportedBayFunctionalStatus method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=IOCLWSException.class)
	public Response getData() throws IOCLWSException
	{
		LOG.info("Entered into getData service class method........");
		try
		{
			GetBayStaticDataResponseBean getBayStaticDataResponseBean=new GetBayStaticDataResponseBean();
			Map<String,List<String>> data=new HashMap<String,List<String>>();

			List<IoclSupportedBaytype> lIoclSupportedBayTypes=iOCLSupportedBayTypesDAO.findAll(IoclSupportedBaytype.class);
			List<String> bayTypes=new ArrayList<String>();
			for(IoclSupportedBaytype ioclSupportedBaytype:lIoclSupportedBayTypes)
			{
				bayTypes.add(ioclSupportedBaytype.getBayType());
			}

			List<IoclSupportedBaystatus> lIoclSupportedBaystatus=iOCLSupportedBayStatusDAO.findAll(IoclSupportedBaystatus.class);
			List<String> bayStatus=new ArrayList<String>();
			for(IoclSupportedBaystatus ioclSupportedBaystatus:lIoclSupportedBaystatus)
			{
				bayStatus.add(ioclSupportedBaystatus.getBayFunctionalStatus());
			}

			data.put("BayTypes",bayTypes);
			data.put("BayStatus",bayStatus);
			getBayStaticDataResponseBean.setData(data);
			return  Response.status(Response.Status.OK).entity(getBayStaticDataResponseBean).build();
		}
		catch (Exception exception) 
		{
			LOG.info("Logging the occured exception in the service class getData method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public Response getCurrentBayOperationalDetails() throws IOCLWSException
	{
		LOG.info("Entered into getCurrentBayOperationalDetails service class method........");
		List<CurrentBayOperationResponseBean> listAvailableBaysResponseBean=new ArrayList<CurrentBayOperationResponseBean>();
		try
		{
			List<IoclBayDetail> listOfAllTheBays=iOCLBayDetailsDAO.findAllAvailableBaysInApplication();
			LOG.info("=======================Bays Logic Starts===============");
			LOG.info("List Of Available Bays In Application:::::::::"+listOfAllTheBays);
			for(IoclBayDetail ioclBayDetail:listOfAllTheBays)
			{
				LOG.info("BayNums Iteration........"+ioclBayDetail.getBayNum());
				CurrentBayOperationResponseBean availableBaysResponseBean=new CurrentBayOperationResponseBean();
				DateFormat dateFormat = new SimpleDateFormat(appProps.getProperty("AppDateFormat"));
				Date currentDate = new Date();
				LOG.info("Current Date:::::"+dateFormat.format(currentDate));

				Calendar cal = Calendar.getInstance();
				cal.setTime(currentDate);
				cal.add(Calendar.HOUR, Integer.parseInt(appProps.getProperty("GetAvailableBaysPastHours")));
				Date hoursBack = cal.getTime();
				LOG.info("PastDate::::::"+dateFormat.format(hoursBack));

				int bayNumber=ioclBayDetail.getBayNum();
				String bayName=ioclBayDetail.getBayName();
				String functionalStatus=ioclBayDetail.getIoclSupportedBaystatus().getBayFunctionalStatus();

				List<IoclBcBayoperation> listOfBCUpdates=iOCLBCBayOperationsDAO.findTopBayUpdatesByBC(bayNumber,currentDate,hoursBack);
				LOG.info("findTopBayUpdatesByBC::::::::"+listOfBCUpdates);
				if(listOfBCUpdates.size()==0)
				{
					availableBaysResponseBean.setBayFunctionalStatus(functionalStatus);
					availableBaysResponseBean.setBayNumber(bayNumber);
					availableBaysResponseBean.setBayName(bayName);
					availableBaysResponseBean.setBayAvailableStatus(appProps.getProperty("BayAvailableEmptyFlag"));
					listAvailableBaysResponseBean.add(availableBaysResponseBean);
				}
				else
				{
					availableBaysResponseBean.setBayFunctionalStatus(functionalStatus);
					availableBaysResponseBean.setBayNumber(bayNumber);
					availableBaysResponseBean.setBayName(bayName);
					IoclBcBayoperation ioclBcBayoperation=listOfBCUpdates.get(0);
					availableBaysResponseBean.setBayAvailableStatus(ioclBcBayoperation.getIoclSupportedBayoperationalstatus().getOperationalStatus());
					IoclFanslipDetail ioclFanslipDetail=iOCLFanslipDetailsDAO.findFanSlipDetailsByFanPinAndBayNum(ioclBcBayoperation.getFanPin(),ioclBcBayoperation.getBayNum());
					availableBaysResponseBean.setContractorName(ioclFanslipDetail.getIoclContractorDetail().getContractorName());
					availableBaysResponseBean.setDestination(ioclFanslipDetail.getDestination());
					availableBaysResponseBean.setFanId(ioclFanslipDetail.getFanId());
					availableBaysResponseBean.setFanPinCreation(dateFormat.format(ioclFanslipDetail.getFanCreationOn()));
					availableBaysResponseBean.setFanPinExpiration(dateFormat.format(ioclFanslipDetail.getFanExpirationOn()));
					availableBaysResponseBean.setFanPinStatus(ioclFanslipDetail.getIoclSupportedPinstatus().getFanPinStatus());
					availableBaysResponseBean.setLocationCode(ioclFanslipDetail.getIoclLocationDetail().getLocationCode());
					availableBaysResponseBean.setQuantity(ioclFanslipDetail.getIoclQuantitiesDetail().getQuantityName()+"("+ioclFanslipDetail.getIoclQuantitiesDetail().getQuantity()+")");
					availableBaysResponseBean.setQuantityID(ioclFanslipDetail.getIoclQuantitiesDetail().getQuantityId());
					availableBaysResponseBean.setPreSet(ioclFanslipDetail.getQuantity());
					IoclTruckregistrationDetail ioclTruckDetails=iOCLTruckRegistrationDetailsDAO.findTruckByTruckId(ioclFanslipDetail.getTruckId());
					availableBaysResponseBean.setTruckNumber(ioclTruckDetails.getTruckNo());
					availableBaysResponseBean.setDriverName(ioclTruckDetails.getDriverName());
					availableBaysResponseBean.setCustomer(ioclTruckDetails.getCustomer());
					availableBaysResponseBean.setVehicleWeight(ioclFanslipDetail.getVehicleWgt());
					listAvailableBaysResponseBean.add(availableBaysResponseBean);
				}
			}
			return  Response.status(Response.Status.OK).entity(listAvailableBaysResponseBean).build();
		}
		catch (Exception exception) 
		{
			LOG.info("Logging the occured exception in the service class getCurrentBayOperationalDetails method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}
}