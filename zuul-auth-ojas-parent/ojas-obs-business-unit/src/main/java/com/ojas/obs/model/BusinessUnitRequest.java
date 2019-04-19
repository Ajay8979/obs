package com.ojas.obs.model;

import com.ojas.obs.model.BusinessUnit;

public class BusinessUnitRequest {

	private BusinessUnit businessUnit;
	private String sessionId;
	private int pageNo;
	private int pageSize;
	private int totalCount;
	private String transactionType;

	public BusinessUnit getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(BusinessUnit businessUnit) {
		this.businessUnit = businessUnit;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((businessUnit == null) ? 0 : businessUnit.hashCode());
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
		BusinessUnitRequest other = (BusinessUnitRequest) obj;
		if (businessUnit == null) {
			if (other.businessUnit != null)
				return false;
		} 
		else if (!businessUnit.equals(other.businessUnit))
			return false;
		if (pageNo != other.pageNo)
			return false;
		if (pageSize != other.pageSize)
			return false;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		}
		else if (!sessionId.equals(other.sessionId))
			return false;
		if (totalCount != other.totalCount)
			return false;
		if (transactionType == null) {
			if (other.transactionType != null)
				return false;
		} 
		else if (!transactionType.equals(other.transactionType))
			return false;
		return true;
	}

}