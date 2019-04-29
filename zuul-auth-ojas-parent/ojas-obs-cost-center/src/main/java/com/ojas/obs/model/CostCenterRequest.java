package com.ojas.obs.model;

import java.util.List;

public class CostCenterRequest {

	private List<CostCenter> costCenter;
	private String sessionId;
	private String transactionType;
	private int pageNo;
	private int pageSize;
	public List<CostCenter> getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(List<CostCenter> costCenter) {
		this.costCenter = costCenter;
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
	
	public CostCenterRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
