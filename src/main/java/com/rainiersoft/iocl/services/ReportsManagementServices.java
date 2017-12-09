package com.rainiersoft.iocl.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.DocumentException;
import com.rainiersoft.iocl.dao.IOCLAllDetailsDAO;
import com.rainiersoft.iocl.entity.IoclAlldetail;
import com.rainiersoft.iocl.exception.IOCLWSException;
import com.rainiersoft.iocl.util.ErrorMessageConstants;
import com.rainiersoft.iocl.util.IOCLConstants;
import com.rainiersoft.iocl.util.PDFUtilities;
import com.rainiersoft.response.dto.BayWiseLoadingReportResponseBean;
import com.rainiersoft.response.dto.BaywiseReportDataBean;
import com.rainiersoft.response.dto.PaginationDataBean;
import com.rainiersoft.response.dto.TotalizerReportDataBean;
import com.rainiersoft.response.dto.TotalizerReportResponseBean;
import com.rainiersoft.response.dto.TruckFillingReportDataBean;
import com.rainiersoft.response.dto.TruckFillingReportResponseBean;

@Service
public class ReportsManagementServices
{
	private static final Logger LOG = LoggerFactory.getLogger(ReportsManagementServices.class);

	@Autowired
	IOCLAllDetailsDAO iOCLAllDetailsDAO;

	@Autowired
	Properties appProps;

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,readOnly=false,rollbackFor=IOCLWSException.class)
	public Response getBayWiseLoadingReport(int pageNumber,int pageSize,String startDate,String endDate,String bayNum) throws IOCLWSException
	{
		LOG.info("Entered into getBayWiseLoadingReport service class method........");
		BayWiseLoadingReportResponseBean bayWiseLoadingReportResponseBean=new BayWiseLoadingReportResponseBean();
		Map<String,List<BaywiseReportDataBean>> dataMap =new HashMap<String,List<BaywiseReportDataBean>>();
		Map<String,PaginationDataBean> paginationMap =new HashMap<String,PaginationDataBean>();
		try
		{
			PaginationDataBean paginationDataBean=new PaginationDataBean();
			paginationDataBean.setPageNumber(pageNumber);
			paginationDataBean.setPageSize(pageSize);

			DateFormat dformat=new SimpleDateFormat(appProps.getProperty("AppDateFormat"));
			Date sDate=dformat.parse(startDate);
			Date eDate=dformat.parse(endDate);

			/*Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			Date sDate=calendar.getTime();
			 */
			/*calendar.setTime(endDate);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 999);
			Date eDate=calendar.getTime();
			 */
			Long totalNumberOfRecords=iOCLAllDetailsDAO.findTotalNumberOfRecords(sDate, eDate,bayNum);

			LOG.info("totalNumberOfRecords........."+totalNumberOfRecords);
			paginationDataBean.setTotalNumberOfRecords(totalNumberOfRecords);
			int lastPageNumber = (int) (Math.ceil(totalNumberOfRecords / pageSize));
			paginationDataBean.setLastPageNumber(lastPageNumber);

			List<IoclAlldetail> listIoclAlldetails=iOCLAllDetailsDAO.findAllDetails(pageNumber,pageSize,sDate,eDate,bayNum);

			System.out.println("listIoclAlldetails.........."+listIoclAlldetails);

			List<BaywiseReportDataBean> listBaywiseReportDataBean=new ArrayList<BaywiseReportDataBean>();

			for(IoclAlldetail IoclAlldetail:listIoclAlldetails)
			{
				System.out.println(IoclAlldetail);
				BaywiseReportDataBean baywiseReportDataBean=new BaywiseReportDataBean();
				baywiseReportDataBean.setTruckNo(IoclAlldetail.getTruckNo());
				baywiseReportDataBean.setCustomer(IoclAlldetail.getCustomer());
				baywiseReportDataBean.setFanNumber(IoclAlldetail.getFanslipnum());

				if(null!=IoclAlldetail.getBccompletedtime())
					baywiseReportDataBean.setEndTime(IoclAlldetail.getBccompletedtime().toString());
				if(null!=IoclAlldetail.getStartTime())
					baywiseReportDataBean.setStartTime(IoclAlldetail.getStartTime().toString());				
				if(null!=IoclAlldetail.getLoadedQuantity() && IoclAlldetail.getLoadedQuantity().length()>0)
					baywiseReportDataBean.setFilledQty(String.valueOf(Math.round(Float.parseFloat(IoclAlldetail.getLoadedQuantity()))));

				baywiseReportDataBean.setInvoiceQty(IoclAlldetail.getPreset());
				baywiseReportDataBean.setFanCreationDate(IoclAlldetail.getFanCreationOn().toString());
				baywiseReportDataBean.setBayNumber(IoclAlldetail.getBayNo());
				listBaywiseReportDataBean.add(baywiseReportDataBean);
			}
			dataMap.put("data", listBaywiseReportDataBean);
			paginationMap.put("Details",paginationDataBean);
			bayWiseLoadingReportResponseBean.setData(dataMap);
			bayWiseLoadingReportResponseBean.setDetails(paginationMap);
		}
		catch (Exception exception) 
		{
			LOG.info("Logging the occured exception in the service class getBayWiseLoadingReport method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
		return Response.status(Response.Status.OK).entity(bayWiseLoadingReportResponseBean).build();
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,readOnly=false,rollbackFor=IOCLWSException.class)
	public Response getTotalizerReport(int pageNumber,int pageSize,String startDate,String endDate,String bayNum) throws IOCLWSException
	{
		LOG.info("Entered into getTotalizerReport service class method........");
		TotalizerReportResponseBean totalizerReportResponseBean=new TotalizerReportResponseBean();
		Map<String,List<TotalizerReportDataBean>> dataMap =new HashMap<String,List<TotalizerReportDataBean>>();
		Map<String,PaginationDataBean> paginationMap =new HashMap<String,PaginationDataBean>();
		try
		{
			//check if start and end dates are equal or not
			//Get the date difference between start and end

			PaginationDataBean paginationDataBean=new PaginationDataBean();
			paginationDataBean.setPageNumber(pageNumber);
			paginationDataBean.setPageSize(pageSize);

			/*			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			Date sDate=calendar.getTime();

			calendar.setTime(endDate);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 999);
			Date eDate=calendar.getTime();*/

			DateFormat dformat=new SimpleDateFormat(appProps.getProperty("AppDateFormat"));
			Date sDate=dformat.parse(startDate);
			Date eDate=dformat.parse(endDate);

			Long totalNumberOfRecords=iOCLAllDetailsDAO.findTotalNumberOfRecords(sDate, eDate,bayNum);

			LOG.info("totalNumberOfRecords........."+totalNumberOfRecords);
			paginationDataBean.setTotalNumberOfRecords(totalNumberOfRecords);
			int lastPageNumber = (int) (Math.ceil(totalNumberOfRecords / pageSize));
			paginationDataBean.setLastPageNumber(lastPageNumber);

			List<IoclAlldetail> listIoclAlldetails=iOCLAllDetailsDAO.findTotalizerDetails(pageNumber,pageSize,sDate,eDate,bayNum);

			List<TotalizerReportDataBean> listTotalizerReportDataBean=new ArrayList<TotalizerReportDataBean>();

			for(IoclAlldetail IoclAlldetail:listIoclAlldetails)
			{
				TotalizerReportDataBean totalizerReportDataBean=new TotalizerReportDataBean();
				totalizerReportDataBean.setBayNum(IoclAlldetail.getBayNo());
				if(null!=IoclAlldetail.getTotalizerendvalue() && IoclAlldetail.getTotalizerendvalue().length()>0)
					totalizerReportDataBean.setClosingReading(String.valueOf(Math.round(Float.parseFloat(IoclAlldetail.getTotalizerendvalue()))));
				if(null!=IoclAlldetail.getTotalizerstartvalue() && IoclAlldetail.getTotalizerstartvalue().length()>0)
					totalizerReportDataBean.setOpeningReading(String.valueOf(Math.round(Float.parseFloat(IoclAlldetail.getTotalizerstartvalue()))));
				if(null!=IoclAlldetail.getLoadedQuantity() &&  IoclAlldetail.getLoadedQuantity().length()>0)
					totalizerReportDataBean.setLoadedQuantity(String.valueOf(Math.round(Float.parseFloat(IoclAlldetail.getLoadedQuantity()))));
				if(null!=IoclAlldetail.getStartTime())
					totalizerReportDataBean.setDate(IoclAlldetail.getStartTime().toString());
				if(null!=IoclAlldetail.getStartTime())
					totalizerReportDataBean.setStartTime(IoclAlldetail.getStartTime().toString());
				if(null!=IoclAlldetail.getBccompletedtime())
					totalizerReportDataBean.setEndTime(IoclAlldetail.getBccompletedtime().toString());
				totalizerReportDataBean.setFanSlipNum(String.valueOf(IoclAlldetail.getFanslipnum()));

				listTotalizerReportDataBean.add(totalizerReportDataBean);
			}
			dataMap.put("data", listTotalizerReportDataBean);
			paginationMap.put("Details",paginationDataBean);
			totalizerReportResponseBean.setData(dataMap);
			totalizerReportResponseBean.setDetails(paginationMap);
		}
		catch (Exception exception) 
		{
			LOG.info("Logging the occured exception in the service class getTotalizerReport method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
		return Response.status(Response.Status.OK).entity(totalizerReportResponseBean).build();
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,readOnly=false,rollbackFor=IOCLWSException.class)
	public Response exportBayWiseLoadingReport(String startDate,String endDate,String bayNum) throws IOCLWSException
	{
		LOG.info("Entered into exportBayWiseLoadingReport service class method........");
		String fileName="";
		try
		{
			/*DateFormat dateFormat = new SimpleDateFormat(appProps.getProperty("DatePickerFormat"));
			Calendar calendar = Calendar.getInstance();
			Date selDate=(Date)dateFormat.parse(startDate);
			calendar.setTime(selDate);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			Date sDate=calendar.getTime();

			Date secDate=(Date)dateFormat.parse(endDate);
			calendar.setTime(secDate);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 999);
			Date eDate=calendar.getTime();*/
			LOG.info("Properties......"+appProps.getProperty("AppDateFormat"));
			DateFormat dformat=new SimpleDateFormat(appProps.getProperty("AppDateFormat"));
			Date sDate=dformat.parse(startDate);
			LOG.info("sDate....."+sDate);
			Date eDate=dformat.parse(endDate);
			LOG.info("eDate....."+eDate);


			List<IoclAlldetail> listIoclAlldetails=iOCLAllDetailsDAO.findAllDetailsByStartDateAndEndDate(sDate,eDate,bayNum);

			LOG.info("listIoclAlldetails......."+listIoclAlldetails);

			String[] header=IOCLConstants.BayWiseLoadingReportHeader;

			List<String[]> listOfRows=new ArrayList<String[]>();
			for(IoclAlldetail ioclAlldetail:listIoclAlldetails)
			{
				String startTime="";
				String endTime="";
				String loadedQty="";

				if(null!=ioclAlldetail.getStartTime())
					startTime=ioclAlldetail.getStartTime().toString();	
				if(null!=ioclAlldetail.getBccompletedtime())
					endTime=ioclAlldetail.getBccompletedtime().toString();	
				if(null!=ioclAlldetail.getLoadedQuantity() && ioclAlldetail.getLoadedQuantity().length()>0)
					loadedQty=String.valueOf(Math.round(Float.parseFloat(ioclAlldetail.getLoadedQuantity())));

				String[] data=new String[]{String.valueOf(ioclAlldetail.getBayNo()),String.valueOf(ioclAlldetail.getFanslipnum()),ioclAlldetail.getTruckNo(),startTime,endTime,ioclAlldetail.getPreset(),loadedQty};
				listOfRows.add(data);
			}

			final String tempFileName=this.createTempFile("BayWiseLoading");

			//Create Report Details if table is using then below code is necessary
			/*List<String[]> reportDetails=new ArrayList<String[]>();
			String[] reportName={"Report Name:","BayWise Loading Report"};
			String[] reportStartDate={"Start Date:",startDate};
			String[] reportEndDate={"End Date:",endDate};
			reportDetails.add(reportName);
			reportDetails.add(reportStartDate);
			reportDetails.add(reportEndDate);*/

			//Create Report Details
			Map<String,String> reportDetails=new HashMap<String,String>();

			DateFormat reportPrintDateFormat=new SimpleDateFormat("dd/MM/yyyy");			
			String printDate=reportPrintDateFormat.format(new Date()).toString();
			String reportStartDate=IOCLConstants.pdfStartDateLabel+startDate;
			String reportEndDate=IOCLConstants.pdfEndDateLabel+endDate;
			String reportPrintDate=IOCLConstants.pdfPrintDateLabel+printDate;
			reportDetails.put("ReportName",IOCLConstants.PdfBayWiseReportHeader);
			reportDetails.put("StartDate",reportStartDate);
			reportDetails.put("EndDate",reportEndDate);
			reportDetails.put("PrintDate",reportPrintDate);

			this.generatePDFDoc(header,reportDetails,listOfRows,tempFileName,"");

			final File file = new File(appProps.getProperty("TempReportFilePath")+tempFileName);

			fileName="BayWiseLoading_BayNum"+"("+bayNum+")"+"_"+startDate+"_"+endDate+IOCLConstants.FileExtension;

			final InputStream responseStream = new FileInputStream(file);

			return Response.ok().entity(new StreamingOutput() {
				@Override
				public void write(final OutputStream out) throws IOException, WebApplicationException 
				{
					int length;
					byte[] buffer = new byte[1024];
					while((length = responseStream.read(buffer)) != -1)
					{
						out.write(buffer, 0, length);
					}
					out.flush();
					responseStream.close();
					boolean isDeleted = file.delete();
				}}).header("content-disposition","attachment;filename="+fileName).build();
		}
		catch (Exception exception) 
		{
			exception.printStackTrace();
			LOG.info("Logging the occured exception in the service class exportBayWiseLoadingReport method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,readOnly=false,rollbackFor=IOCLWSException.class)
	public Response exportTotalizerReport(String startDate,String endDate,String bayNum) throws IOCLWSException
	{
		LOG.info("Entered into exportTotalizerReport service class method........");
		String fileName="";
		try
		{
			/*DateFormat dateFormat = new SimpleDateFormat(appProps.getProperty("DatePickerFormat"));
			Calendar calendar = Calendar.getInstance();
			Date selDate=(Date)dateFormat.parse(startDate);
			calendar.setTime(selDate);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			Date sDate=calendar.getTime();

			Date secDate=(Date)dateFormat.parse(endDate);
			calendar.setTime(secDate);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 999);
			Date eDate=calendar.getTime();*/

			DateFormat dformat=new SimpleDateFormat(appProps.getProperty("AppDateFormat"));
			Date sDate=dformat.parse(startDate);
			Date eDate=dformat.parse(endDate);

			List<IoclAlldetail> listIoclAlldetails=iOCLAllDetailsDAO.findAllDetailsByStartDateAndEndDate(sDate,eDate,bayNum);

			String[] header=IOCLConstants.TotalizerLoadingReportHeader;

			List<String[]> listOfRows=new ArrayList<String[]>();
			for(IoclAlldetail ioclAlldetail:listIoclAlldetails)
			{
				String totalizerStartValue="";
				String totalizerEndValue="";
				String loadedQuantity="";
				String startTime="";
				String endTime="";

				if(null!=ioclAlldetail.getStartTime())
					startTime=ioclAlldetail.getStartTime().toString();
				if(null!=ioclAlldetail.getBccompletedtime())
					endTime=ioclAlldetail.getBccompletedtime().toString();
				if(null!=ioclAlldetail.getTotalizerstartvalue() && ioclAlldetail.getTotalizerstartvalue().length()>0)
					totalizerStartValue=String.valueOf(Math.round(Float.parseFloat(ioclAlldetail.getTotalizerstartvalue())));
				if(null!=ioclAlldetail.getTotalizerendvalue() && ioclAlldetail.getTotalizerendvalue().length()>0)
					totalizerEndValue=String.valueOf(Math.round(Float.parseFloat(ioclAlldetail.getTotalizerendvalue())));
				if(null!=ioclAlldetail.getLoadedQuantity() && ioclAlldetail.getLoadedQuantity().length()>0)
					loadedQuantity=String.valueOf(Math.round(Float.parseFloat(ioclAlldetail.getLoadedQuantity())));

				String[] data=new String[]{String.valueOf(ioclAlldetail.getBayNo()),String.valueOf(ioclAlldetail.getFanslipnum()),startTime,endTime,totalizerStartValue,totalizerEndValue,loadedQuantity};
				listOfRows.add(data);
			}

			String sumOfLoadedQty="";
			if(listIoclAlldetails.size()>0)
			{
				sumOfLoadedQty=iOCLAllDetailsDAO.findSumOfLoadedByStartDateAndEndDate(sDate,eDate,"ALL");
				if(sumOfLoadedQty.length()>0)
					sumOfLoadedQty=String.valueOf(Math.round(Float.parseFloat(sumOfLoadedQty)));
			}
			final String tempFileName=this.createTempFile("TotalizerReport");

			//Create Report Details if table is used
			/*List<String[]> reportDetails=new ArrayList<String[]>();
			String[] reportName={"Report Name:","Totalizer Report"};
			String[] reportStartDate={"Start Date:",startDate};
			String[] reportEndDate={"End Date:",endDate};
			reportDetails.add(reportName);
			reportDetails.add(reportStartDate);
			reportDetails.add(reportEndDate);*/

			//Create Report Details
			Map<String,String> reportDetails=new HashMap<String,String>();
			
			DateFormat reportPrintDateFormat=new SimpleDateFormat("dd/MM/yyyy");			
			String printDate=reportPrintDateFormat.format(new Date()).toString();
			String reportStartDate=IOCLConstants.pdfStartDateLabel+startDate;
			String reportEndDate=IOCLConstants.pdfEndDateLabel+endDate;
			String reportPrintDate=IOCLConstants.pdfPrintDateLabel+printDate;
			reportDetails.put("ReportName",IOCLConstants.PdfTotalizerReportHeader);
			reportDetails.put("StartDate",reportStartDate);
			reportDetails.put("EndDate",reportEndDate);
			reportDetails.put("PrintDate",reportPrintDate);
			
			this.generatePDFDoc(header,reportDetails,listOfRows,tempFileName,sumOfLoadedQty);

			final File file = new File(appProps.getProperty("TempReportFilePath")+tempFileName);

			fileName="TotalizerReport_BayNum"+"("+bayNum+")"+"_"+startDate+"_"+endDate+IOCLConstants.FileExtension;

			final InputStream responseStream = new FileInputStream(file);

			return Response.ok().entity(new StreamingOutput() {
				@Override
				public void write(final OutputStream out) throws IOException, WebApplicationException 
				{
					int length;
					byte[] buffer = new byte[1024];
					while((length = responseStream.read(buffer)) != -1)
					{
						out.write(buffer, 0, length);
					}
					out.flush();
					responseStream.close();
					boolean isDeleted = file.delete();
				}}).header("content-disposition","attachment;filename="+fileName).build();
		}
		catch (Exception exception) 
		{
			LOG.info("Logging the occured exception in the service class totalizerReport method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,readOnly=false,rollbackFor=IOCLWSException.class)
	public Response exportTruckFillingReport(String startDate,String endDate) throws IOCLWSException
	{
		LOG.info("Entered into exportTruckFillingReport service class method........");
		String fileName="";
		try
		{
			/*DateFormat dateFormat = new SimpleDateFormat(appProps.getProperty("DatePickerFormat"));
			Calendar calendar = Calendar.getInstance();
			Date selDate=(Date)dateFormat.parse(startDate);
			calendar.setTime(selDate);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			Date sDate=calendar.getTime();

			Date secDate=(Date)dateFormat.parse(endDate);
			calendar.setTime(secDate);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 999);
			Date eDate=calendar.getTime();*/

			DateFormat dformat=new SimpleDateFormat(appProps.getProperty("AppDateFormat"));
			Date sDate=dformat.parse(startDate);
			Date eDate=dformat.parse(endDate);

			List<IoclAlldetail> listIoclAlldetails=iOCLAllDetailsDAO.findAllDetailsByStartDateAndEndDate(sDate,eDate,"ALL");

			String[] header=IOCLConstants.TruckFillingReportHeader;

			List<String[]> listOfRows=new ArrayList<String[]>();
			for(IoclAlldetail ioclAlldetail:listIoclAlldetails)
			{
				String startTime="";
				String endTime="";
				String loadedQuantity="";
				if(null!=ioclAlldetail.getStartTime())
					startTime=ioclAlldetail.getStartTime().toString();
				if(null!=ioclAlldetail.getBccompletedtime())
					endTime=ioclAlldetail.getBccompletedtime().toString();
				if(null!=ioclAlldetail.getLoadedQuantity() && ioclAlldetail.getLoadedQuantity().length()>0)
					loadedQuantity=String.valueOf(Math.round(Float.parseFloat(ioclAlldetail.getLoadedQuantity())));

				String[] data=new String[]{String.valueOf(ioclAlldetail.getBayNo()),String.valueOf(ioclAlldetail.getFanslipnum()),ioclAlldetail.getTruckNo(),startTime,endTime,ioclAlldetail.getPreset(),loadedQuantity};
				listOfRows.add(data);
			}

			final String tempFileName=this.createTempFile("TruckFilling");
			//Create Report Details for table
			/*List<String[]> reportDetails=new ArrayList<String[]>();
			String[] reportName={"Report Name:","Truck Filling Report"};
			String[] reportStartDate={"Start Date:",startDate};
			String[] reportEndDate={"End Date:",endDate};
			reportDetails.add(reportName);
			reportDetails.add(reportStartDate);
			reportDetails.add(reportEndDate);*/

			//Create Report Details
			Map<String,String> reportDetails=new HashMap<String,String>();
			DateFormat reportPrintDateFormat=new SimpleDateFormat("dd/MM/yyyy");			
			String printDate=reportPrintDateFormat.format(new Date()).toString();
			String reportStartDate=IOCLConstants.pdfStartDateLabel+startDate;
			String reportEndDate=IOCLConstants.pdfEndDateLabel+endDate;
			String reportPrintDate=IOCLConstants.pdfPrintDateLabel+printDate;
			reportDetails.put("ReportName",IOCLConstants.PdfTruckFillingReportHeader);
			reportDetails.put("StartDate",reportStartDate);
			reportDetails.put("EndDate",reportEndDate);
			reportDetails.put("PrintDate",reportPrintDate);

			this.generatePDFDoc(header,reportDetails,listOfRows,tempFileName,"");

			final File file = new File(appProps.getProperty("TempReportFilePath")+tempFileName);

			fileName="TruckFillingReport_"+startDate+"_"+endDate+IOCLConstants.FileExtension;

			final InputStream responseStream = new FileInputStream(file);

			return Response.ok().entity(new StreamingOutput() {
				@Override
				public void write(final OutputStream out) throws IOException, WebApplicationException 
				{
					int length;
					byte[] buffer = new byte[1024];
					while((length = responseStream.read(buffer)) != -1)
					{
						out.write(buffer, 0, length);
					}
					out.flush();
					responseStream.close();
					boolean isDeleted = file.delete();
				}}).header("content-disposition","attachment;filename="+fileName).build();
		}
		catch (Exception exception) 
		{
			LOG.info("Logging the occured exception in the service class exportTruckFillingReport method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,readOnly=false,rollbackFor=IOCLWSException.class)
	public Response getTruckFillingReport(int pageNumber,int pageSize,String startDate,String endDate) throws IOCLWSException
	{
		LOG.info("Entered into getTruckFillingReport service class method........");
		TruckFillingReportResponseBean truckFillingReportResponseBean=new TruckFillingReportResponseBean();
		Map<String,List<TruckFillingReportDataBean>> dataMap =new HashMap<String,List<TruckFillingReportDataBean>>();
		Map<String,PaginationDataBean> paginationMap =new HashMap<String,PaginationDataBean>();
		try
		{
			PaginationDataBean paginationDataBean=new PaginationDataBean();
			paginationDataBean.setPageNumber(pageNumber);
			paginationDataBean.setPageSize(pageSize);

			/*Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			Date sDate=calendar.getTime();

			calendar.setTime(endDate);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 999);
			Date eDate=calendar.getTime();*/

			DateFormat dformat=new SimpleDateFormat(appProps.getProperty("AppDateFormat"));
			Date sDate=dformat.parse(startDate);
			Date eDate=dformat.parse(endDate);

			Long totalNumberOfRecords=iOCLAllDetailsDAO.findTotalNumberOfRecords(sDate, eDate,"ALL");

			LOG.info("totalNumberOfRecords........."+totalNumberOfRecords);
			paginationDataBean.setTotalNumberOfRecords(totalNumberOfRecords);
			int lastPageNumber = (int) (Math.ceil(totalNumberOfRecords / pageSize));
			paginationDataBean.setLastPageNumber(lastPageNumber);

			List<IoclAlldetail> listIoclAlldetails=iOCLAllDetailsDAO.findAllDetails(pageNumber, pageSize, sDate, eDate,"ALL");

			LOG.info("listIoclAlldetails.........."+listIoclAlldetails);

			List<TruckFillingReportDataBean> listTruckFillingReportDataBean=new ArrayList<TruckFillingReportDataBean>();

			for(IoclAlldetail ioclAlldetail:listIoclAlldetails)
			{
				TruckFillingReportDataBean truckFillingReportDataBean=new TruckFillingReportDataBean();
				if(null!=ioclAlldetail.getBccompletedtime())
					truckFillingReportDataBean.setEndTime(ioclAlldetail.getBccompletedtime().toString());
				if(null!=ioclAlldetail.getLoadedQuantity() && ioclAlldetail.getLoadedQuantity().length()>0)
					truckFillingReportDataBean.setLoadedQty(String.valueOf(Math.round(Float.parseFloat(ioclAlldetail.getLoadedQuantity()))));
				if(null!=ioclAlldetail.getStartTime())
					truckFillingReportDataBean.setStartTime(ioclAlldetail.getStartTime().toString());

				truckFillingReportDataBean.setBayNo(String.valueOf(ioclAlldetail.getBayNo()));
				truckFillingReportDataBean.setFanSlipNum(String.valueOf(ioclAlldetail.getFanslipnum()));
				truckFillingReportDataBean.setPresetQty(ioclAlldetail.getPreset());
				truckFillingReportDataBean.setTruckNo(ioclAlldetail.getTruckNo());
				listTruckFillingReportDataBean.add(truckFillingReportDataBean);
			}
			dataMap.put("data", listTruckFillingReportDataBean);
			paginationMap.put("Details",paginationDataBean);
			truckFillingReportResponseBean.setData(dataMap);
			truckFillingReportResponseBean.setDetails(paginationMap);
		}
		catch (Exception exception) 
		{
			LOG.info("Logging the occured exception in the service class getTruckFillingReport method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
		return Response.status(Response.Status.OK).entity(truckFillingReportResponseBean).build();
	}

	private void generatePDFDoc(String[] header,Map<String,String> reportDetails,List<String[]> data,String tempFileName,String calculatedValue) throws URISyntaxException, IOException, DocumentException
	{
		PDFUtilities pdfObj=new PDFUtilities(header.length,appProps);
		pdfObj.createPdfFile(header,reportDetails,data,tempFileName,calculatedValue);
	}

	private String createTempFile(String reportName)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date currentTime=new Date();
		String fileTime=dateFormat.format(currentTime);
		String fileName=reportName+"_"+fileTime+IOCLConstants.FileExtension;
		return fileName;
	}
}