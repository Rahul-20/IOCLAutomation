package com.baymang.testcases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.ws.rs.core.Response;

import org.glassfish.jersey.internal.util.Base64;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.format.annotation.DateTimeFormat;

import com.rainiersoft.iocl.services.BaysManagementServices;
import com.rainiersoft.iocl.util.CommonUtilites;

public class BayManagementTestCases
{
	public BayManagementTestCases() {}

	public static void main(String[] args) throws Exception
	{
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("sc::" + ac);
		BaysManagementServices baysManagementServices=(BaysManagementServices)ac.getBean("baysManagementServices");
		baysManagementServices.getAvailableBays();

		//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		/*String aaa="cmFodWxAMTIzOg";
		String de=new String (Base64.decode(aaa.getBytes()));
		System.out.println("DE"+de);
		*/
		//BaysManagementServices baysManagementServices=(BaysManagementServices)ac.getBean("baysManagementServices");
		//Response allBayDetailsResp=baysManagementServices.getAllBayDetails();
		//System.out.println("All BayDetails::::::"+allBayDetailsResp.getEntity());

		//Response AvailableBays=baysManagementServices.getAvailableBays();
		//System.out.println("getAvailableBays::::::"+AvailableBays.getEntity());
		
		//Response getCurrentBayOperationalDetails=baysManagementServices.getCurrentBayOperationalDetails();
		//System.out.println("getCurrentBayOperationalDetails::::::"+getCurrentBayOperationalDetails.getEntity());
		
		//Response bayCreation=baysManagementServices.bayCreation("bay2",2, "LPG", "Active");
		//System.out.println("All BayDetails::::::"+bayCreation.getEntity());

		//Response bayCreation1=baysManagementServices.bayUpdation(2, "Bay31",31, "LPG", "Active",true,true);
		//System.out.println("All BayDetails::::::"+bayCreation1.getEntity());
		
		//Response bayCreation=baysManagementServices.deleteBay(2);
		//System.out.println("All BayDetails::::::"+bayCreation.getEntity());

	}
}