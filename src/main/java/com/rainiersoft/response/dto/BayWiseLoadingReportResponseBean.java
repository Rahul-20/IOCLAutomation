package com.rainiersoft.response.dto;

import java.util.List;
import java.util.Map;

public class BayWiseLoadingReportResponseBean 
{
	@Override
	public String toString() {
		return "BayWiseLoadingReportResponseBean [data=" + data + ", details=" + details + "]";
	}

	private Map<String,List<BaywiseReportDataBean>> data;
	
	private Map<String,PaginationDataBean> details;
	
	public Map<String,PaginationDataBean> getDetails() {
		return details;
	}

	public void setDetails(Map<String,PaginationDataBean> details) {
		this.details = details;
	}

	public Map<String, List<BaywiseReportDataBean>> getData() {
		return data;
	}

	public void setData(Map<String, List<BaywiseReportDataBean>> data) {
		this.data = data;
	}
}
