package com.ojas.obs.model;

public class EmployeeSkillInfoRequest {

	
	private EmployeeSkillInfo skillInfoModel;
	private String sessionId;
	private int pageNo;
	private int pageSize;
	private int totalCount;
	private String transactionType;
	
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public EmployeeSkillInfo getSkillInfoModel() {
		return skillInfoModel;
	}
	public void setSkillInfoModel(EmployeeSkillInfo skillInfoModel) {
		this.skillInfoModel = skillInfoModel;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
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
	
	public EmployeeSkillInfoRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
