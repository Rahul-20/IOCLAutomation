package com.rainiersoft.iocl.util;

public class IOCLConstants 
{
	public static final String[] BayWiseLoadingReportHeader={"BayNo","FanNo","TruckNo","StartTime","EndTime","InvoiceQty","FilledQty"};
	public static final String[] TotalizerLoadingReportHeader={"BayNo","FanNo","StartTime","EndTime","OpenReading","ClosedReading","LoadedQty"};
	public static final String[] TruckFillingReportHeader={"BayNo","FanNo","TruckNo","StartTime","EndTime","PresetQty","LoadedQty"};
	
	public static final String FileExtension=".pdf";
	
	
	public static final String PdfCommonHeader="INDIAN OIL CORPORATION LIMITED";
	public static final String PdfCommonSubHeader="Bottling Plant-Balasore";
	
	public static final String PdfBayWiseReportHeader="BayWise Loading Report";
	public static final String PdfTruckFillingReportHeader="Truck Filling Report";
	public static final String PdfTotalizerReportHeader="Totalizer Report";
	
	public static final String pdfStartDateLabel="Start Date:";
	public static final String pdfEndDateLabel="End Date:";
	public static final String pdfPrintDateLabel="Print Date:";
	
}