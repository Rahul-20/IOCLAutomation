package com.locationsmang.testcases;

import javax.ws.rs.core.Response;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rainiersoft.iocl.services.LocationManagementServices;

public class LocationsMangTestCases 
{
	public static void main(String[] args) throws Exception
	{
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("sc::" + ac);
		
		LocationManagementServices lms=(LocationManagementServices) ac.getBean("locationManagementServices");
		//lms.addLocation("Banglore10","BNG12","Not Active","Mumbai");
		
		//lms.updateLocation("Banglore1","BNG1","Not Active","Mumbai",2,false,true);
		
		Response resp=lms.getLocationDetails();
		System.out.println("Response:::::"+resp.getEntity());
		
		lms.deleteLocation(2);
		
	}
}
