package com.ojas.obs.response;

import java.util.List;

import com.ojas.obs.model.DependentDetails;

public class DependentDetailsResponse {
	private List<DependentDetails> getDependentDetailsList;
	private String statusCode;
	private String statusMessage;
	private int pageNo;
	private int pageSize;
	private int totalCount;
	
	public DependentDetailsResponse() {}
	
	public DependentDetailsResponse(List<DependentDetails> getDependentDetailsList,
			int pageNo, int pageSize, int totalCount) {
		super();
		this.getDependentDetailsList = getDependentDetailsList;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
	}
	public List<DependentDetails> getGetDependentDetailsList() {
		return getDependentDetailsList;
	}
	public void setGetDependentDetailsList(List<DependentDetails> getDependentDetailsList) {
		this.getDependentDetailsList = getDependentDetailsList;
	}
	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
