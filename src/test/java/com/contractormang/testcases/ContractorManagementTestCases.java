package com.contractormang.testcases;

import java.util.ArrayList;

import javax.ws.rs.core.Response;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rainiersoft.iocl.exception.IOCLWSException;
import com.rainiersoft.iocl.services.ContractorsManagementServices;

public class ContractorManagementTestCases
{
	public ContractorManagementTestCases() {}

	public static void main(String[] args) throws Exception
	{
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("sc::" + ac);


		ContractorsManagementServices cms=(ContractorsManagementServices)ac.getBean("contractorsManagementServices");

		Response addresp=cms.addContractor("ABCCC","iocl","qa" ,"dddd","Active","500035","AP", "IOCLAdmin");
		System.out.println("Add Contractors:::::"+addresp.getEntity());

		//Response updateresp=cms.updateContractor(2,"RahulKumar","IOCL","Saroornagar","Hyderabad","Not Active","500035","Telangana",true);
		//System.out.println("Add Contractors:::::"+updateresp.getEntity());
		
		Response resp=cms.getContractorDetails();
		System.out.println("Get ALL Contractors:::::"+resp.getEntity());
		
		//Response respStat=cms.getContractorStaticData();
		//System.out.println("Get ALL Contractors:::::"+respStat.getEntity());
		
		//Response resp=cms.getData();
		//System.out.println("Get ALL Contractors:::::"+resp.getEntity());

		//Response delresp=cms.deleteContractor(3);
		//System.out.println("Del Cont:::::"+delresp.getEntity());
		
	}
}