package com.ojas.obs.request;

import java.util.List;

import com.ojas.obs.model.DependentDetails;

public class DependentDetailsRequest {
	private List<DependentDetails> dependentDetails;
	private String sessionId;
	private String transactionType;
	private int pageNo;
	private int pageSize;
	private int totalCount;

	public DependentDetailsRequest() {
	}

	public DependentDetailsRequest(List<DependentDetails> dependentDetails, String sessionId, String transactionType,
			int pageNo, int pageSize, int totalCount) {
		super();
		this.dependentDetails = dependentDetails;
		this.sessionId = sessionId;
		this.transactionType = transactionType;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
	}

	public List<DependentDetails> getDependentDetails() {
		return dependentDetails;
	}

	public void setDependentDetails(List<DependentDetails> dependentDetails) {
		this.dependentDetails = dependentDetails;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
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
