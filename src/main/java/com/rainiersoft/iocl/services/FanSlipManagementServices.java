package com.rainiersoft.iocl.services;

import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Singleton;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rainiersoft.iocl.dao.IOCLFanslipDetailsDAO;
import com.rainiersoft.iocl.dao.IOCLLocationDetailsDAO;
import com.rainiersoft.iocl.dao.IOCLSupportedPinStatusDAO;
import com.rainiersoft.iocl.dao.IOCLTruckRegistrationDetailsDAO;
import com.rainiersoft.iocl.entity.IoclLocationDetail;
import com.rainiersoft.iocl.entity.IoclSupportedPinstatus;
import com.rainiersoft.iocl.entity.IoclTruckregistrationDetail;
import com.rainiersoft.iocl.exception.IOCLWSException;
import com.rainiersoft.iocl.util.CommonUtilites;
import com.rainiersoft.response.dto.FanPinCreatedResponse;

@Service
@Singleton
public class FanSlipManagementServices 
{
	private static final Logger LOG = LoggerFactory.getLogger(FanSlipManagementServices.class);

	@Autowired
	IOCLTruckRegistrationDetailsDAO iOCLTruckRegistrationDetailsDAO;

	@Autowired
	IOCLFanslipDetailsDAO ioclFanslipDetailsDAO;

	@Autowired
	IOCLLocationDetailsDAO iOCLLocationDetailsDAO;

	@Autowired
	IOCLSupportedPinStatusDAO iOCLSupportedPinStatusDAO;


	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=org.springframework.dao.DataIntegrityViolationException.class)
	public Response fanSlipGeneration(String truckNo,String driverName,String driverLicNo,String customer,String quantity,String vehicleWgt,String destination,String locationCode,String mobileNumber,int bayNum) throws IOCLWSException, NoSuchAlgorithmException,Exception
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
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date dateobj = new Date();
				//String fanCreatedTimeStamp=df.format(dateobj).toString();
				//GET THE LOCATIONID and STATUSID
				IoclLocationDetail ioclLocationDetail=iOCLLocationDetailsDAO.findLocationIdByLocationCode(locationCode);

				IoclSupportedPinstatus ioclSupportedPinstatus=iOCLSupportedPinStatusDAO.findPinStatusIdByPinStatus("Created");

				if(null!=ioclLocationDetail && null!=ioclSupportedPinstatus)
				{
					ioclFanslipDetailsDAO.insertFanSlipDetails(bayNum, String.valueOf(fanPin),ioclSupportedPinstatus, truckID,dateobj,quantity, vehicleWgt, destination, ioclLocationDetail);
					fanPinCreatedResponse.setFanPin(String.valueOf(fanPin));
					fanPinCreatedResponse.setBayNumber(bayNum);
					fanPinCreatedResponse.setTruckNumber(String.valueOf(truckID));
					fanPinCreatedResponse.setQuantity(quantity);
				}					
			}
			return Response.status(Response.Status.OK).entity(fanPinCreatedResponse).build();
		}catch(Exception e)
		{
			throw e;
		}	
	}
}