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
		
		Response availableUsersResp=ums.getAvailableUsers();
		//System.out.println("AvailableUsersResp:::::"+availableUsersResp.getEntity());

		Response availableUsersStatus=ums.supportedUserStatus();
		System.out.println("availableUsersStatus:::::"+availableUsersStatus.getEntity());
		
		Response availableUsersTypes=ums.supportedUserTypes();
		System.out.println("availableUsersTypes:::::"+availableUsersTypes.getEntity());
		
		
		

		/*ArrayList<String> roles=new ArrayList<String>();
		roles.add("Super Admin");
		try
		{
			Response creationResp=ums.createNewUser("Rahul5", "Rahul", "Rahul", "Rahul", "2017-08-31", "9901029102", roles, "9999999", "Active");
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
			Response updateUser=ums.updateUser("Rahul","Rahul","1111111","Not Active");			
			System.out.println("UpdateUser::::::"+updateUser.getEntity());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}*/
		
		/*Response deletUser=ums.deleteUser(5);
		System.out.println("Deleted User:::::::"+deletUser.getEntity());*/
	}
}