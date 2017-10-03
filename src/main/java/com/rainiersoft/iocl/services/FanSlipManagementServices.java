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

import javax.inject.Singleton;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rainiersoft.iocl.dao.IOCLBCBayOperationsDAO;
import com.rainiersoft.iocl.dao.IOCLContractorDetailsDAO;
import com.rainiersoft.iocl.dao.IOCLFanslipDetailsDAO;
import com.rainiersoft.iocl.dao.IOCLLocationDetailsDAO;
import com.rainiersoft.iocl.dao.IOCLSupportedPinStatusDAO;
import com.rainiersoft.iocl.dao.IOCLTruckRegistrationDetailsDAO;
import com.rainiersoft.iocl.dao.IOCLUserDetailsDAO;
import com.rainiersoft.iocl.entity.IoclBcBayoperation;
import com.rainiersoft.iocl.entity.IoclContractorDetail;
import com.rainiersoft.iocl.entity.IoclFanslipDetail;
import com.rainiersoft.iocl.entity.IoclLocationDetail;
import com.rainiersoft.iocl.entity.IoclSupportedPinstatus;
import com.rainiersoft.iocl.entity.IoclTruckregistrationDetail;
import com.rainiersoft.iocl.entity.IoclUserDetail;
import com.rainiersoft.iocl.exception.IOCLWSException;
import com.rainiersoft.iocl.util.CommonUtilites;
import com.rainiersoft.iocl.util.ErrorMessageConstants;
import com.rainiersoft.response.dto.FanPinCancellationResponseBean;
import com.rainiersoft.response.dto.FanPinCreatedResponse;
import com.rainiersoft.response.dto.GetAllLatestFanSlipsDataResponseBean;
import com.rainiersoft.response.dto.GetFanMangStaticDataResponseBean;


/**
 * This is the class for FanSlipManagementServices
 * @author RahulKumarPamidi
 */

@Service
@Singleton
public class FanSlipManagementServices 
{
	private static final Logger LOG = LoggerFactory.getLogger(FanSlipManagementServices.class);

	@Autowired
	IOCLTruckRegistrationDetailsDAO iOCLTruckRegistrationDetailsDAO;

	@Autowired
	IOCLUserDetailsDAO iOCLUserDetailsDAO;

	@Autowired
	IOCLFanslipDetailsDAO ioclFanslipDetailsDAO;

	@Autowired
	IOCLLocationDetailsDAO iOCLLocationDetailsDAO;

	@Autowired
	IOCLSupportedPinStatusDAO iOCLSupportedPinStatusDAO;

	@Autowired
	IOCLContractorDetailsDAO iOCLContractorDetailsDAO;

	@Autowired
	IOCLBCBayOperationsDAO iOCLBCBayOperationsDAO;

	@Autowired
	Properties appProps;


	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=IOCLWSException.class)
	public Response fanSlipGeneration(String truckNo,String driverName,String driverLicNo,String customer,String quantity,String vehicleWgt,String destination,String locationCode,String mobileNumber,int bayNum,String contarctorName,String createdBy) throws IOCLWSException
	{
		try
		{
			FanPinCreatedResponse fanPinCreatedResponse=new FanPinCreatedResponse();

			//Check if the truck is there in truck table,If not insert truck details.  else get the truck id and insert new row in fan table
			int truckID=0;
			IoclTruckregistrationDetail ioclTruckregistrationDetail=iOCLTruckRegistrationDetailsDAO.findTruckByTruckNo(truckNo);
			if(ioclTruckregistrationDetail==null)
			{
				iOCLTruckRegistrationDetailsDAO.insertTruckregistrationDetail(truckNo, driverName, driverLicNo, customer, mobileNumber);
				IoclTruckregistrationDetail ioclTruckDetails=iOCLTruckRegistrationDetailsDAO.findTruckByTruckNo(truckNo);
				if(null!=ioclTruckDetails)
				{
					truckID=ioclTruckDetails.getTruckId();
				}
				LOG.info("New Truck::::::::"+truckID);
			}
			else
			{
				IoclTruckregistrationDetail ioclTruckDetails=iOCLTruckRegistrationDetailsDAO.findTruckByTruckNo(truckNo);
				truckID=ioclTruckDetails.getTruckId();
				LOG.info("Existing Truck::::::::"+truckID);
			}

			if(truckID!=0)
			{
				int fanPin=0;
				while(true)
				{
					fanPin=CommonUtilites.pinGen();
					boolean flag=CommonUtilites.checkPinHasFourDigits(fanPin);
					if(flag)
					{
						break;
					}
				}

				DateFormat dateFormat = new SimpleDateFormat(appProps.getProperty("AppDateFormat"));
				Date createddateobj = new Date();

				Calendar cal = Calendar.getInstance();
				cal.setTime(createddateobj);
				cal.add(Calendar.HOUR, Integer.parseInt(appProps.getProperty("FanExpirationNumberOfHours")));
				Date fanExpirationTime = cal.getTime();
				LOG.info("fanExpirationTime::::::"+dateFormat.format(fanExpirationTime));


				//GET THE LOCATIONID and STATUSID
				IoclLocationDetail ioclLocationDetail=iOCLLocationDetailsDAO.findLocationIdByLocationCode(locationCode);

				IoclContractorDetail ioclContractorDetail=iOCLContractorDetailsDAO.findContractorByContractorName(contarctorName);

				IoclSupportedPinstatus ioclSupportedPinstatus=iOCLSupportedPinStatusDAO.findPinStatusIdByPinStatus(appProps.getProperty("FanCreatedStatus"));

				System.out.println("createdBy::::::"+createdBy);
				IoclUserDetail ioclUserDetail=iOCLUserDetailsDAO.findUserByUserName(createdBy);
				System.out.println("ioclUserDetail:::::::"+ioclUserDetail);
				int userID=ioclUserDetail.getUserId();

				System.out.println("usrId:::"+userID);
				if(null!=ioclLocationDetail && null!=ioclSupportedPinstatus && null!=ioclUserDetail)
				{
					Long fanId=ioclFanslipDetailsDAO.insertFanSlipDetails(bayNum, String.valueOf(fanPin),ioclSupportedPinstatus, truckID,createddateobj,quantity, vehicleWgt, destination, ioclLocationDetail,fanExpirationTime,ioclContractorDetail,userID);
					fanPinCreatedResponse.setFanPin(String.valueOf(fanPin));
					fanPinCreatedResponse.setBayNum(bayNum);
					fanPinCreatedResponse.setQuantity(quantity);
					fanPinCreatedResponse.setContractorName(contarctorName);
					fanPinCreatedResponse.setDestination(destination);
					fanPinCreatedResponse.setFanId(fanId);
					fanPinCreatedResponse.setFanPinCreation(dateFormat.format(createddateobj));
					fanPinCreatedResponse.setFanPinExpiration(dateFormat.format(fanExpirationTime));
					fanPinCreatedResponse.setFanPinStatus(appProps.getProperty("FanCreatedStatus"));
					fanPinCreatedResponse.setLocationCode(locationCode);
					fanPinCreatedResponse.setQuantity(quantity);
					fanPinCreatedResponse.setTruckNumber(truckNo);
					fanPinCreatedResponse.setDriverName(driverName);
					fanPinCreatedResponse.setCustomer(customer);
					fanPinCreatedResponse.setVehicleWeight(vehicleWgt);
				}
			}
			return Response.status(Response.Status.OK).entity(fanPinCreatedResponse).build();
		}
		catch(Exception exception)
		{
			LOG.info("Logging the occured exception in the service class getFanStaticData method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}	
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=IOCLWSException.class)
	public Response getFanStaticData() throws IOCLWSException
	{
		LOG.info("Entered into getFanStaticData service class method........");
		try
		{
			GetFanMangStaticDataResponseBean getFanMangStaticDataResponseBean=new GetFanMangStaticDataResponseBean();
			Map<String,List<String>> data=new HashMap<String,List<String>>();

			List<IoclContractorDetail> lIoclContractorDetail=iOCLContractorDetailsDAO.findAllContractorNames();
			LOG.info("Got the lIoclContractorDetail object......"+lIoclContractorDetail);
			List<IoclLocationDetail> lIoclLocationDetail=iOCLLocationDetailsDAO.findAllLocationCodes();
			LOG.info("Got the lIoclLocationDetail object......"+lIoclLocationDetail);

			Set<String> setContractorNames=new HashSet<String>();
			Set<String> setLocationCodes=new HashSet<String>();
			for(IoclContractorDetail ioclContractorDetail:lIoclContractorDetail)
			{
				if((!ioclContractorDetail.getIoclSupportedContractorstatus().equals("In Active")))
					setContractorNames.add(ioclContractorDetail.getContractorName());
			}
			for(IoclLocationDetail ioclLocationDetail:lIoclLocationDetail)
			{
				if((!ioclLocationDetail.getIoclSupportedLocationstatus().equals("In Active")))
					setLocationCodes.add(ioclLocationDetail.getLocationCode());
			}
			List<String> contractorNames=new ArrayList<String>(setContractorNames);
			List<String> locationCodes=new ArrayList<String>(setLocationCodes);
			data.put("ContractorNames",contractorNames);
			data.put("LocationCodes",locationCodes);
			getFanMangStaticDataResponseBean.setData(data);
			LOG.info("getFanStaticData response object......."+getFanMangStaticDataResponseBean);
			return  Response.status(Response.Status.OK).entity(getFanMangStaticDataResponseBean).build();
		}
		catch (Exception exception) 
		{
			LOG.info("Logging the occured exception in the service class getFanStaticData method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=IOCLWSException.class)
	public Response getAllLatestFanslipsData(String selectedDate) throws IOCLWSException
	{
		LOG.info("Entered into getAllLatestFanslipsData service class method........");
		try
		{
			List<GetAllLatestFanSlipsDataResponseBean> listGetAllLatestFanSlipsDataResponseBean=new ArrayList<GetAllLatestFanSlipsDataResponseBean>();

			DateFormat dateFormat = new SimpleDateFormat(appProps.getProperty("DatePickerFormat"));
			Date selDate=(Date)dateFormat.parse(selectedDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(selDate);
			LOG.info("PastFanSlipDetails Configured Value....."+appProps.getProperty("NumberOfHoursPastFanSlipDetails"));
			cal.add(Calendar.HOUR, Integer.parseInt(appProps.getProperty("NumberOfHoursPastFanSlipDetails")));
			Date hoursBack = cal.getTime();
			LOG.info("PastDate......."+dateFormat.format(hoursBack));
			
			cal.setTime(selDate);
			cal.add(Calendar.HOUR,23);
			Date selectDateWithTime = cal.getTime();
			
			
			List<IoclFanslipDetail> listOfPastFanslips=ioclFanslipDetailsDAO.findAllLatestFanSlips(selectDateWithTime,hoursBack);
			
			System.out.println("listOfPastFanslips......."+listOfPastFanslips);

			for(IoclFanslipDetail ioclFanslipDetail:listOfPastFanslips)
			{
				GetAllLatestFanSlipsDataResponseBean getAllLatestFanSlipsDataResponseBean=new GetAllLatestFanSlipsDataResponseBean();
				getAllLatestFanSlipsDataResponseBean.setBayNum(ioclFanslipDetail.getBayNo());
				getAllLatestFanSlipsDataResponseBean.setContractorName(ioclFanslipDetail.getIoclContractorDetail().getContractorName());
				getAllLatestFanSlipsDataResponseBean.setDestination(ioclFanslipDetail.getDestination());
				getAllLatestFanSlipsDataResponseBean.setFanId(ioclFanslipDetail.getFanId());
				getAllLatestFanSlipsDataResponseBean.setFanPin(ioclFanslipDetail.getFanPin());
				getAllLatestFanSlipsDataResponseBean.setFanPinCreation(dateFormat.format(ioclFanslipDetail.getFanCreationOn()));
				getAllLatestFanSlipsDataResponseBean.setFanPinExpiration(dateFormat.format(ioclFanslipDetail.getFanExpirationOn()));
				getAllLatestFanSlipsDataResponseBean.setFanPinStatus(ioclFanslipDetail.getIoclSupportedPinstatus().getFanPinStatus());
				getAllLatestFanSlipsDataResponseBean.setLocationCode(ioclFanslipDetail.getIoclLocationDetail().getLocationCode());
				getAllLatestFanSlipsDataResponseBean.setQuantity(ioclFanslipDetail.getQuantity());
				IoclTruckregistrationDetail ioclTruckDetails=iOCLTruckRegistrationDetailsDAO.findTruckByTruckId(ioclFanslipDetail.getTruckId());
				getAllLatestFanSlipsDataResponseBean.setTruckNumber(ioclTruckDetails.getTruckNo());
				getAllLatestFanSlipsDataResponseBean.setDriverName(ioclTruckDetails.getDriverName());
				getAllLatestFanSlipsDataResponseBean.setCustomer(ioclTruckDetails.getCustomer());
				getAllLatestFanSlipsDataResponseBean.setVehicleWeight(ioclFanslipDetail.getVehicleWgt());

				List<IoclBcBayoperation> listOfBCUpdates=iOCLBCBayOperationsDAO.findBayUpdatesByFanPin(ioclFanslipDetail.getFanPin());
				LOG.info("findBayUpdatesByFanPin::::::::"+listOfBCUpdates);

				//Fpin is generated but truck has not reached the point, so the BC table might not contain records for the truck.
				if(listOfBCUpdates.size()==0)
				{
					getAllLatestFanSlipsDataResponseBean.setBayStatus(appProps.getProperty("NOT_REACHED"));
				}
				else
				{
					getAllLatestFanSlipsDataResponseBean.setBayStatus(listOfBCUpdates.get(0).getIoclSupportedBayoperationalstatus().getOperationalStatus());	
				}

				listGetAllLatestFanSlipsDataResponseBean.add(getAllLatestFanSlipsDataResponseBean);
			}
			LOG.info("getAllLatestFanslipsData response object......."+listGetAllLatestFanSlipsDataResponseBean);
			return  Response.status(Response.Status.OK).entity(listGetAllLatestFanSlipsDataResponseBean).build();
		}
		catch (Exception exception) 
		{
			LOG.info("Logging the occured exception in the service class getAllLatestFanslipsData method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=IOCLWSException.class)
	public Response fanslipReGeneration(int fanId,String truckNo,String driverName,String driverLicNo,String customer,String quantity,String vehicleWgt,String destination,String locationCode,String mobileNumber,int bayNum,String contarctorName,String userName) throws IOCLWSException
	{
		try
		{
			FanPinCreatedResponse fanPinCreatedResponse=new FanPinCreatedResponse();

			IoclSupportedPinstatus ioclSupportedCancelPinstatus=iOCLSupportedPinStatusDAO.findPinStatusIdByPinStatus(appProps.getProperty("FanCancelledStatus"));

			IoclFanslipDetail ioclFanslipDetail=ioclFanslipDetailsDAO.findFanPinByFanId(fanId);

			IoclUserDetail ioclUserDetail=iOCLUserDetailsDAO.findUserByUserName(userName);
			int userID=ioclUserDetail.getUserId();

			DateFormat dateFormat = new SimpleDateFormat(appProps.getProperty("AppDateFormat"));
			Date updateTime = new Date();

			//Set the fan pin status to cancel
			ioclFanslipDetailsDAO.updateFanPinDetails(ioclFanslipDetail, ioclSupportedCancelPinstatus,userID,updateTime);

			//Check if the truck is there in truck table,If not insert truck details.  else get the truck id and insert new row in fan table
			IoclTruckregistrationDetail ioclTruckDetails=iOCLTruckRegistrationDetailsDAO.findTruckByTruckNo(truckNo);
			int truckID=ioclTruckDetails.getTruckId();

			int fanPin=0;
			while(true)
			{
				fanPin=CommonUtilites.pinGen();
				boolean flag=CommonUtilites.checkPinHasFourDigits(fanPin);
				if(flag)
				{
					break;
				}
			}

			//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date createddateobj = new Date();

			Calendar cal = Calendar.getInstance();
			cal.setTime(createddateobj);
			cal.add(Calendar.HOUR, Integer.parseInt(appProps.getProperty("FanExpirationNumberOfHours")));
			Date fanExpirationTime = cal.getTime();

			//GET THE LOCATIONID and STATUSID
			IoclLocationDetail ioclLocationDetail=iOCLLocationDetailsDAO.findLocationIdByLocationCode(locationCode);

			IoclContractorDetail ioclContractorDetail=iOCLContractorDetailsDAO.findContractorByContractorName(contarctorName);

			IoclSupportedPinstatus ioclSupportedPinstatus=iOCLSupportedPinStatusDAO.findPinStatusIdByPinStatus(appProps.getProperty("FanCreatedStatus"));

			if(null!=ioclLocationDetail && null!=ioclSupportedPinstatus)
			{
				Long latestFanId=ioclFanslipDetailsDAO.insertFanSlipDetails(bayNum, String.valueOf(fanPin),ioclSupportedPinstatus, truckID,createddateobj,quantity, vehicleWgt, destination, ioclLocationDetail,fanExpirationTime,ioclContractorDetail,userID);
				fanPinCreatedResponse.setFanPin(String.valueOf(fanPin));
				fanPinCreatedResponse.setBayNum(bayNum);
				fanPinCreatedResponse.setQuantity(quantity);
				fanPinCreatedResponse.setContractorName(contarctorName);
				fanPinCreatedResponse.setDestination(destination);
				fanPinCreatedResponse.setFanId(latestFanId);
				fanPinCreatedResponse.setFanPinCreation(dateFormat.format(createddateobj));
				fanPinCreatedResponse.setFanPinExpiration(dateFormat.format(fanExpirationTime));
				fanPinCreatedResponse.setFanPinStatus(appProps.getProperty("FanCreatedStatus"));
				fanPinCreatedResponse.setLocationCode(locationCode);
				fanPinCreatedResponse.setQuantity(quantity);
				fanPinCreatedResponse.setTruckNumber(truckNo);
				fanPinCreatedResponse.setDriverName(driverName);
				fanPinCreatedResponse.setCustomer(customer);
				fanPinCreatedResponse.setVehicleWeight(vehicleWgt);

			}
			return Response.status(Response.Status.OK).entity(fanPinCreatedResponse).build();
		}
		catch(Exception exception)
		{
			LOG.info("Logging the occured exception in the service class getFanStaticData method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}	
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=IOCLWSException.class)
	public Response fanslipCancellation(int fanId,String userName) throws IOCLWSException
	{
		LOG.info("Entered into fanslipCancellation service class method........");
		try
		{
			FanPinCancellationResponseBean fanPinCancellationResponseBean=new FanPinCancellationResponseBean();

			IoclSupportedPinstatus ioclSupportedCancelPinstatus=iOCLSupportedPinStatusDAO.findPinStatusIdByPinStatus(appProps.getProperty("FanCancelledStatus"));

			IoclFanslipDetail ioclFanslipDetail=ioclFanslipDetailsDAO.findFanPinByFanId(fanId);

			IoclUserDetail ioclUserDetail=iOCLUserDetailsDAO.findUserByUserName(userName);
			int userID=ioclUserDetail.getUserId();

			DateFormat dateFormat = new SimpleDateFormat(appProps.getProperty("AppDateFormat"));
			Date updateTime = new Date();

			//Set the fan pin status to cancel
			ioclFanslipDetailsDAO.updateFanPinDetails(ioclFanslipDetail, ioclSupportedCancelPinstatus,userID,updateTime);

			fanPinCancellationResponseBean.setMessage("SuccessFully Cancelled");
			fanPinCancellationResponseBean.setSuccessFlag(true);

			return  Response.status(Response.Status.OK).entity(fanPinCancellationResponseBean).build();
		}
		catch (Exception exception) 
		{
			LOG.info("Logging the occured exception in the service class getFanStaticData method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}
}