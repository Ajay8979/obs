package com.ojas.obs.model;

import java.util.List;

public class EmployeeSkillInfoResponse {

	
	private List<EmployeeSkillInfo> getSkillInfoList;
	private int pageNo;
	private int pageSize;
	private int totalCount;
	private String statusMessage;
	
	public List<EmployeeSkillInfo> getGetSkillInfoList() {
		return getSkillInfoList;
	}
	public void setGetSkillInfoList(List<EmployeeSkillInfo> getSkillInfoList) {
		this.getSkillInfoList = getSkillInfoList;
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
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getSkillInfoList == null) ? 0 : getSkillInfoList.hashCode());
		result = prime * result + pageNo;
		result = prime * result + pageSize;
		result = prime * result + ((statusMessage == null) ? 0 : statusMessage.hashCode());
		result = prime * result + totalCount;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeSkillInfoResponse other = (EmployeeSkillInfoResponse) obj;
		if (getSkillInfoList == null) {
			if (other.getSkillInfoList != null)
				return false;
		} else if (!getSkillInfoList.equals(other.getSkillInfoList))
			return false;
		if (pageNo != other.pageNo)
			return false;
		if (pageSize != other.pageSize)
			return false;
		if (statusMessage == null) {
			if (other.statusMessage != null)
				return false;
		} else if (!statusMessage.equals(other.statusMessage))
			return false;
		if (totalCount != other.totalCount)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "EmployeeSkillInfoResponse [getSkillInfoList=" + getSkillInfoList + ", pageNo=" + pageNo + ", pageSize="
				+ pageSize + ", totalCount=" + totalCount + ", statusMessage=" + statusMessage + "]";
	}
	

	
	
}
