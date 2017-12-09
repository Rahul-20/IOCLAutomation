package com.reportsmang.testcases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.core.Response;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rainiersoft.iocl.services.ReportsManagementServices;
import com.rainiersoft.iocl.util.PDFUtilities;

public class ReportMangTest 
{
	public static void main(String[] args) throws Exception
	{
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		//System.out.println("sc::" + ac);

		ReportsManagementServices qms=(ReportsManagementServices) ac.getBean("reportsManagementServices");
		//Response res=qms.getBayWiseLoadingReport(1, 2,new Date(),new Date());

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date selDate=(Date)dateFormat.parse("2017-11-28");
		Response res=qms.exportTruckFillingReport("2017-11-27 01:00:00","2017-12-06 23:00:00");
		System.err.println("Respone....."+res.getEntity());

	//	Response res=qms.getTruckFillingReport(1, 3,selDate,selDate);

		
/*		PDFUtilities pdfu=new PDFUtilities(2);
		pdfu.createPdfFile(header,l,"SAMple.pdf");
*/
		//Response res=qms.exportTotalizerReport("2017-11-27 01:00:00","2017-12-06 23:00:00","ALL");
		//System.out.println(res.getEntity());
		//qms.addQunatity("BigTruck","100","Active");
		//qms.updateQuantity(1,"Big","10000","Not Active",true,true);
	}
}