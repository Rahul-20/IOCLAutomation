package com.rainiersoft.response.dto;

public class BaywiseReportDataBean 
{
	@Override
	public String toString() {
		return "BaywiseReportDataBean [TruckNo=" + TruckNo + ", FanNumber=" + FanNumber + ", Customer=" + Customer
				+ ", InvoiceQty=" + InvoiceQty + ", FilledQty=" + FilledQty + ", StartTime=" + StartTime + ", EndTime="
				+ EndTime + "]";
	}
	private String TruckNo;
	private int FanNumber;
	private String Customer;
	private String InvoiceQty;
	private String FilledQty;
	private String StartTime;
	private String EndTime;
	
	public String getTruckNo() {
		return TruckNo;
	}
	public void setTruckNo(String truckNo) {
		TruckNo = truckNo;
	}
	public int getFanNumber() {
		return FanNumber;
	}
	public void setFanNumber(int fanNumber) {
		FanNumber = fanNumber;
	}
	public String getCustomer() {
		return Customer;
	}
	public void setCustomer(String customer) {
		Customer = customer;
	}
	public String getInvoiceQty() {
		return InvoiceQty;
	}
	public void setInvoiceQty(String invoiceQty) {
		InvoiceQty = invoiceQty;
	}
	public String getFilledQty() {
		return FilledQty;
	}
	public void setFilledQty(String filledQty) {
		FilledQty = filledQty;
	}
	public String getStartTime() {
		return StartTime;
	}
	public void setStartTime(String startTime) {
		StartTime = startTime;
	}
	public String getEndTime() {
		return EndTime;
	}
	public void setEndTime(String endTime) {
		EndTime = endTime;
	}
	

}
