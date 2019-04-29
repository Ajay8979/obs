package com.ojas.obs.model;

import java.util.List;

public class CostCenterResponse {

	private List<CostCenter> listOfCostCenter;
	private String statusMessage;
	private Integer totalCount;
	private int pageNo;
	private int pageSize;
	public List<CostCenter> getListOfCostCenter() {
		return listOfCostCenter;
	}
	public void setListOfCostCenter(List<CostCenter> listOfCostCenter) {
		this.listOfCostCenter = listOfCostCenter;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
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
	public CostCenterResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CostCenterResponse [listOfCostCenter=" + listOfCostCenter + ", statusMessage=" + statusMessage
				+ ", totalCount=" + totalCount + ", pageNo=" + pageNo + ", pageSize=" + pageSize + "]";
	}
	
	
}
