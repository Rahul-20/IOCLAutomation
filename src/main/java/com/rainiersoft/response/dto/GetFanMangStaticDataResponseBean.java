package com.rainiersoft.response.dto;

import java.util.List;
import java.util.Map;

public class GetFanMangStaticDataResponseBean 
{

	@Override
	public String toString() {
		return "GetFanMangStaticDataResponseBean [data=" + data + ", quantitesData=" + quantitesData + "]";
	}

	private Map<String,List<String>> data;
	private Map<String,Map<Integer,String>> quantitesData;

	public Map<String, List<String>> getData() {
		return data;
	}

	public Map<String, Map<Integer, String>> getQuantitesData() {
		return quantitesData;
	}

	public void setQuantitesData(Map<String, Map<Integer, String>> quantitesData) {
		this.quantitesData = quantitesData;
	}

	public void setData(Map<String, List<String>> data) {
		this.data = data;
	}
}