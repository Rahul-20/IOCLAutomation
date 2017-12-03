package com.rainiersoft.response.dto;

import java.util.List;
import java.util.Map;

public class TruckFillingReportResponseBean 
{
	@Override
	public String toString() {
		return "TruckFillingReportResponseBean [data=" + data + ", details=" + details + "]";
	}

	private Map<String,List<TruckFillingReportDataBean>> data;
	
	private Map<String,PaginationDataBean> details;
	
	public Map<String,PaginationDataBean> getDetails() {
		return details;
	}

	public void setDetails(Map<String,PaginationDataBean> details) {
		this.details = details;
	}

	public Map<String, List<TruckFillingReportDataBean>> getData() {
		return data;
	}

	public void setData(Map<String, List<TruckFillingReportDataBean>> data) {
		this.data = data;
	}

}
