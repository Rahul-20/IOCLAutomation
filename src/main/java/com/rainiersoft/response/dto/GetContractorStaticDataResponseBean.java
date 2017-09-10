package com.rainiersoft.response.dto;

import java.util.List;
import java.util.Map;

public class GetContractorStaticDataResponseBean 
{
	@Override
	public String toString() {
		return "GetContractorStaticDataResponseBean [data=" + data + "]";
	}

	private Map<String,List<String>> data;

	public Map<String, List<String>> getData() {
		return data;
	}

	public void setData(Map<String, List<String>> data) {
		this.data = data;
	}
}
