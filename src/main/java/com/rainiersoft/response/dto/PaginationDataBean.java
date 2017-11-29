package com.rainiersoft.response.dto;

public class PaginationDataBean 
{
	@Override
	public String toString() {
		return "PaginationDataBean [totalNumberOfRecords=" + totalNumberOfRecords + ", pageNumber=" + pageNumber
				+ ", pageSize=" + pageSize + ", lastPageNumber=" + lastPageNumber + "]";
	}
	private Long totalNumberOfRecords;
	private int pageNumber;
	private int pageSize;
	private int lastPageNumber;
	
	public int getLastPageNumber() {
		return lastPageNumber;
	}
	public void setLastPageNumber(int lastPageNumber) {
		this.lastPageNumber = lastPageNumber;
	}
	public Long getTotalNumberOfRecords() {
		return totalNumberOfRecords;
	}
	public void setTotalNumberOfRecords(Long totalNumberOfRecords) {
		this.totalNumberOfRecords = totalNumberOfRecords;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
