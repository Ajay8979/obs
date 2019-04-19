package com.ojas.obs.request;

import java.util.List;

import com.ojas.obs.model.EmployeeBUDetails;

public class EmployeeBUDetailsRequest {
	private List<EmployeeBUDetails> employeeBUDeatils;
	private String sessionId;
	private int pageNo;
	private int pageSize;
	private int totalCount;
	private String transactionType;

	public List<EmployeeBUDetails> getEmployeeBUDeatils() {
		return employeeBUDeatils;
	}

	public void setEmployeeBUDeatils(List<EmployeeBUDetails> employeeBUDeatils) {
		this.employeeBUDeatils = employeeBUDeatils;
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

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	@Override
	public String toString() {
		return "EmployeeBUDetailsRequest [employeeBUDeatils=" + employeeBUDeatils + ", sessionId=" + sessionId
				+ ", pageNo=" + pageNo + ", pageSize=" + pageSize + ", totalCount=" + totalCount + ", transactionType="
				+ transactionType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employeeBUDeatils == null) ? 0 : employeeBUDeatils.hashCode());
		result = prime * result + pageNo;
		result = prime * result + pageSize;
		result = prime * result + ((sessionId == null) ? 0 : sessionId.hashCode());
		result = prime * result + totalCount;
		result = prime * result + ((transactionType == null) ? 0 : transactionType.hashCode());
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
		EmployeeBUDetailsRequest other = (EmployeeBUDetailsRequest) obj;
		if (employeeBUDeatils == null) {
			if (other.employeeBUDeatils != null)
				return false;
		} else if (!employeeBUDeatils.equals(other.employeeBUDeatils))
			return false;
		if (pageNo != other.pageNo)
			return false;
		if (pageSize != other.pageSize)
			return false;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
			return false;
		if (totalCount != other.totalCount)
			return false;
		if (transactionType == null) {
			if (other.transactionType != null)
				return false;
		} else if (!transactionType.equals(other.transactionType))
			return false;
		return true;
	}

}
