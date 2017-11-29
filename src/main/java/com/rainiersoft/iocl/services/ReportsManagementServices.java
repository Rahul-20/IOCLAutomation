package com.rainiersoft.iocl.services;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rainiersoft.iocl.dao.IOCLAllDetailsDAO;
import com.rainiersoft.iocl.entity.IoclAlldetail;
import com.rainiersoft.iocl.exception.IOCLWSException;
import com.rainiersoft.iocl.util.ErrorMessageConstants;
import com.rainiersoft.response.dto.BayWiseLoadingReportResponseBean;
import com.rainiersoft.response.dto.BaywiseReportDataBean;
import com.rainiersoft.response.dto.PaginationDataBean;

@Service
public class ReportsManagementServices
{
	private static final Logger LOG = LoggerFactory.getLogger(ReportsManagementServices.class);

	@Autowired
	IOCLAllDetailsDAO iOCLAllDetailsDAO;

	@Autowired
	Properties appProps;

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,readOnly=false,rollbackFor=IOCLWSException.class)
	public Response getBayWiseLoadingReport(int pageNumber,int pageSize,Date startDate,Date endDate,String bayNum) throws IOCLWSException
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

			Calendar calendar = Calendar.getInstance();
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
			Date eDate=calendar.getTime();

			Long totalNumberOfRecords=iOCLAllDetailsDAO.findTotalNumberOfRecords(sDate, eDate,bayNum);

			LOG.info("totalNumberOfRecords........."+totalNumberOfRecords);
			paginationDataBean.setTotalNumberOfRecords(totalNumberOfRecords);
			int lastPageNumber = (int) (Math.ceil(totalNumberOfRecords / pageSize));
			paginationDataBean.setLastPageNumber(lastPageNumber);

			List<IoclAlldetail> listIoclAlldetails=iOCLAllDetailsDAO.findAllDetails(pageNumber,pageSize,sDate,eDate,bayNum);

			List<BaywiseReportDataBean> listBaywiseReportDataBean=new ArrayList<BaywiseReportDataBean>();

			for(IoclAlldetail IoclAlldetail:listIoclAlldetails)
			{
				BaywiseReportDataBean baywiseReportDataBean=new BaywiseReportDataBean();
				baywiseReportDataBean.setTruckNo(IoclAlldetail.getTruckNo());
				baywiseReportDataBean.setCustomer(IoclAlldetail.getContractorName());
				if(null!=IoclAlldetail.getBccompletedtime())
					baywiseReportDataBean.setEndTime(IoclAlldetail.getBccompletedtime().toString());
				if(null!=IoclAlldetail.getBcinputtime())
					baywiseReportDataBean.setStartTime(IoclAlldetail.getBcinputtime().toString());
				baywiseReportDataBean.setFanNumber(IoclAlldetail.getFanslipnum());
				baywiseReportDataBean.setFilledQty(IoclAlldetail.getLoadedQuantity());
				baywiseReportDataBean.setInvoiceQty(IoclAlldetail.getQuantity());
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
	public Response getTotalizerReport(int pageNumber,int pageSize,Date startDate,Date endDate) throws IOCLWSException
	{
		LOG.info("Entered into getTotalizerReport service class method........");
		try
		{
			iOCLAllDetailsDAO.findTotalizerDetails(pageNumber,pageSize,startDate,endDate);
		}
		catch (Exception exception) 
		{
			LOG.info("Logging the occured exception in the service class getTotalizerReport method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
		return null;
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,readOnly=false,rollbackFor=IOCLWSException.class)
	public Response exportBayWiseLoadingReport(String startDate,String endDate,String bayNum) throws IOCLWSException
	{
		LOG.info("Entered into exportBayWiseLoadingReport service class method........");
		StreamingOutput fileStream=null;
		String fileName="";
		int length=0;
		try
		{
			DateFormat dateFormat = new SimpleDateFormat(appProps.getProperty("DatePickerFormat"));
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
			Date eDate=calendar.getTime();

			List<IoclAlldetail> listIoclAlldetails=iOCLAllDetailsDAO.findAllDetailsByStartDateAndEndDate(sDate,eDate,bayNum);

			String[][] allDetails = null;
			final StringBuilder all=new StringBuilder();


			all.append("CreationDate,TruckRegNum,FanNumber,Customer,InvoiceQty,FilledQty,StartTime,EndTime");
			//all.append("CreationDate"+"\t"+"TruckRegNum"+"\t"+"FanNumber"+"\t"+"Customer"+"\t"+"InvoiceQty"+"\t"+"FilledQty"+"\t"+"StartTime"+"\t"+"EndTime");
			all.append("\n");
			for(IoclAlldetail ioclAlldetail:listIoclAlldetails)
			{
				String aa=ioclAlldetail.getFanCreationOn().toString()+","+ioclAlldetail.getTruckNo()+","+String.valueOf(ioclAlldetail.getFanslipnum())+","+ioclAlldetail.getContractorName()+","+ioclAlldetail.getPreset()+","+ioclAlldetail.getLoadedQuantity();
				//String aa=ioclAlldetail.getFanCreationOn().toString()+"\t"+ioclAlldetail.getTruckNo()+"\t"+String.valueOf(ioclAlldetail.getFanslipnum())+"\t"+ioclAlldetail.getContractorName()+"\t"+ioclAlldetail.getPreset()+"\t"+ioclAlldetail.getLoadedQuantity();
				if(null!=ioclAlldetail.getStartTime() && null!=ioclAlldetail.getBccompletedtime())
				{
					aa=aa+","+ioclAlldetail.getStartTime().toString()+","+ioclAlldetail.getBccompletedtime().toString();
					//aa=aa+"\t"+ioclAlldetail.getStartTime().toString()+"\t"+ioclAlldetail.getBccompletedtime().toString();
				}
				all.append(aa);
				all.append("\n");
			}
			length=String.valueOf(all).getBytes().length;
			fileStream =  new StreamingOutput()
			{
				@Override
				public void write(OutputStream output) throws IOException, WebApplicationException 
				{
					byte[] data = String.valueOf(all).getBytes();
					output.write(data);
					output.flush();
				}
			};
			fileName="BayWiseLoading_"+bayNum+"_"+startDate+"_"+endDate+".csv";
		}
		catch (Exception exception) 
		{
			LOG.info("Logging the occured exception in the service class exportBayWiseLoadingReport method catch block........"+exception);
			throw new IOCLWSException(ErrorMessageConstants.Unprocessable_Entity_Code,ErrorMessageConstants.Internal_Error);
		}
		return Response.ok(fileStream, MediaType.APPLICATION_OCTET_STREAM).header("content-length",length).header("content-disposition","attachment;filename="+fileName).build();
	}
}