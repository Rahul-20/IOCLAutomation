package com.rainiersoft.response.dto;

import java.util.List;
import java.util.Map;

public class TotalizerReportResponseBean
{
	private Map<String,List<TotalizerReportDataBean>> data;
	
	private Map<String,PaginationDataBean> details;
	
	public Map<String,PaginationDataBean> getDetails() {
		return details;
	}

	public void setDetails(Map<String,PaginationDataBean> details) {
		this.details = details;
	}

	public Map<String, List<TotalizerReportDataBean>> getData() {
		return data;
	}

	public void setData(Map<String, List<TotalizerReportDataBean>> data) {
		this.data = data;
	}
}
