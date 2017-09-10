package com.baymang.testcases;

import javax.ws.rs.core.Response;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rainiersoft.iocl.services.BaysManagementServices;

public class BayManagementTestCases
{
	public BayManagementTestCases() {}

	public static void main(String[] args) throws Exception
	{
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("sc::" + ac);

		BaysManagementServices baysManagementServices=(BaysManagementServices)ac.getBean("baysManagementServices");
		//Response allBayDetailsResp=baysManagementServices.getAllBayDetails();
		//System.out.println("All BayDetails::::::"+allBayDetailsResp.getEntity());

		//Response bayCreation=baysManagementServices.bayCreation("bay1", 1, "LPG", "Active");
//		/System.out.println("All BayDetails::::::"+bayCreation.getEntity());

		/*Response bayCreation=baysManagementServices.bayUpdation(1, "Bay1", 1, "LPG", "Active", false, false);
		System.out.println("All BayDetails::::::"+bayCreation.getEntity());
		*/
		Response bayCreation=baysManagementServices.deleteBay(2);
		System.out.println("All BayDetails::::::"+bayCreation.getEntity());

	}
}