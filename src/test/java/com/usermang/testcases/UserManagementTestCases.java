package com.usermang.testcases;

import java.util.ArrayList;

import javax.ws.rs.core.Response;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rainiersoft.iocl.exception.IOCLWSException;
import com.rainiersoft.iocl.services.UserManagementServices;

public class UserManagementTestCases
{
	public UserManagementTestCases() {}

	public static void main(String[] args) throws Exception
	{
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("sc::" + ac);


		UserManagementServices ums=(UserManagementServices)ac.getBean("userManagementServices");

		//Response availableUsersResp=ums.getAvailableUsers("Admin");
		//System.out.println("AvailableUsersResp:::::"+availableUsersResp.getEntity());

		Response getDate=ums.getAvailableUsers("Admin");
		System.out.println("getDate:::::"+getDate.getEntity());

		//Response availableUsersStatus=ums.supportedUserStatus();
		//System.out.println("availableUsersStatus:::::"+availableUsersStatus.getEntity());

		//Response availableUsersTypes=ums.supportedUserTypes();
		//System.out.println("availableUsersTypes:::::"+availableUsersTypes.getEntity());*/




		ArrayList<String> roles=new ArrayList<String>();
		roles.add("Admin");
		/*try
		{
			Response creationResp=ums.createNewUser();
			System.out.println("Creation Response:::::::::"+creationResp.getEntity());
		}
		catch(IOCLWSException e)
		{
			System.out.println("Exception:::::"+e.getErrorMessage());
		}*/

		/*try 
		{
			Response validateUser=ums.validateUser("Rahul", "Rahul", "");
			System.out.println("Validate User Resonse"+validateUser.getEntity());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}*/

		/*try
		{
			Response updateUser=ums.updateUser(4,"rahul15","rahul","111111111","Not Active",false,"ddddd","llll", "2017-09-09", "111111", roles);
			System.out.println("UpdateUser::::::"+updateUser.getEntity());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}*/

		//Response deletUser=ums.deleteUser(2);
		//System.out.println("Deleted User:::::::"+deletUser.getEntity());

		/*		BaysManagementServices bms=(BaysManagementServices)ac.getBean("baysManagementServices");
		//bms.bayCreation("Bay1", 1, "LPG", "Active");

		bms.getAllBayDetails();
		Response res=bms.getData();
		System.out.println("Respo::"+res.getEntity());
		 */	

		//Response val=ums.validateUser("IOCLAdmin","IOCLAdmin@123","");
		//System.out.println("VALLLLLLLLL....."+val.getEntity());

	}
}