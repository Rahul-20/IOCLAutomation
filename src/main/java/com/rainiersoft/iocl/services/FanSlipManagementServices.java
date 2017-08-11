package com.rainiersoft.iocl.services;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.rainiersoft.iocl.dao.IOCLTruckRegistrationDetailsDAO;
import com.rainiersoft.iocl.dao.IOCLUserDetailsDAO;
import com.rainiersoft.iocl.dao.IOCLUserroleMappingDAO;
import com.rainiersoft.iocl.entity.IoclTruckregistrationDetail;
import com.rainiersoft.iocl.exception.IOCLWSException;
import com.rainiersoft.iocl.util.CommonUtilites;

@Service
@Singleton
public class FanSlipManagementServices 
{
	private static final Logger LOG = LoggerFactory.getLogger(FanSlipManagementServices.class);

	@Autowired
	IOCLTruckRegistrationDetailsDAO iOCLTruckRegistrationDetailsDAO;

	@Autowired
	IOCLFanslipDetailsDAO ioclFanslipDetailsDAO;

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public Response fanSlipGeneration(String truckNo,String driverName,String driverLicNo,String customer,String quantity,String vehicleWgt,String destination,String locationCode,String mobileNumber,String bayNum) throws IOCLWSException, NoSuchAlgorithmException
	{
		//Check if the truck is there in truck table,If not insert truck details.  else get the truck id and insert new row in fan table
		int truckID=0;
		IoclTruckregistrationDetail ioclTruckregistrationDetail=iOCLTruckRegistrationDetailsDAO.findTruckByTruckNo(truckNo);
		if(ioclTruckregistrationDetail==null)
		{
			iOCLTruckRegistrationDetailsDAO.insertTruckregistrationDetail(truckNo, driverName, driverLicNo, customer, mobileNumber, quantity, vehicleWgt, destination, locationCode);
			IoclTruckregistrationDetail ioclTruckDetails=iOCLTruckRegistrationDetailsDAO.findTruckByTruckNo(truckNo);
			truckID=ioclTruckDetails.getTruckId();
		}
		else
		{
			IoclTruckregistrationDetail ioclTruckDetails=iOCLTruckRegistrationDetailsDAO.findTruckByTruckNo(truckNo);
			truckID=ioclTruckDetails.getTruckId();
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
			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			Date dateobj = new Date();
			String fanCreatedTimeStamp=df.format(dateobj).toString();
			
			ioclFanslipDetailsDAO.insertFanSlipDetails(bayNum, String.valueOf(fanPin), "Assigned", truckID,fanCreatedTimeStamp);
		}
		return Response.status(Response.Status.OK).entity("FanPin:::").build();	
	}
}
