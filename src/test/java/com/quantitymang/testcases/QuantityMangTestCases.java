package com.quantitymang.testcases;

import javax.ws.rs.core.Response;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rainiersoft.iocl.services.QuantitiesManagementServices;

public class QuantityMangTestCases 
{
	public static void main(String[] args) throws Exception
	{
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("sc::" + ac);
		
		QuantitiesManagementServices qms=(QuantitiesManagementServices) ac.getBean("quantitiesManagementServices");
		Response res=qms.getQuantityDetails();
		System.out.println("Res...."+res.getEntity());
		//qms.addQunatity("BigTruck","100","Active");
		//qms.updateQuantity(1,"Big","10000","Not Active",true,true);
	}	
}
