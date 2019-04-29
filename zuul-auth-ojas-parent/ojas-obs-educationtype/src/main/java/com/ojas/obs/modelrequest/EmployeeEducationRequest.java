package com.ojas.obs.modelrequest;

import java.util.List;

import com.ojas.obs.model.EmployeeEducation;
/**
 * 
 * @author mpraneethguptha
 *
 */

public class EmployeeEducationRequest {

	private List<EmployeeEducation> listEmployeeEducations;

	private String sessionID;

	private String searchKey;

	private Integer pageNo;

	private Integer pageSize;

	private String transactionType;

	

	public List<EmployeeEducation> getListEmployeeEducations() {
		return listEmployeeEducations;
	}

	public void setListEmployeeEducations(List<EmployeeEducation> listEmployeeEducations) {
		this.listEmployeeEducations = listEmployeeEducations;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
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
		result = prime * result + ((listEmployeeEducations == null) ? 0 : listEmployeeEducations.hashCode());
		result = prime * result + ((pageNo == null) ? 0 : pageNo.hashCode());
		result = prime * result + ((pageSize == null) ? 0 : pageSize.hashCode());
		result = prime * result + ((searchKey == null) ? 0 : searchKey.hashCode());
		result = prime * result + ((sessionID == null) ? 0 : sessionID.hashCode());
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
		EmployeeEducationRequest other = (EmployeeEducationRequest) obj;
		if (listEmployeeEducations == null) {
			if (other.listEmployeeEducations != null)
				return false;
		} else if (!listEmployeeEducations.equals(other.listEmployeeEducations))
			return false;
		if (pageNo == null) {
			if (other.pageNo != null)
				return false;
		} else if (!pageNo.equals(other.pageNo))
			return false;
		if (pageSize == null) {
			if (other.pageSize != null)
				return false;
		} else if (!pageSize.equals(other.pageSize))
			return false;
		if (searchKey == null) {
			if (other.searchKey != null)
				return false;
		} else if (!searchKey.equals(other.searchKey))
			return false;
		if (sessionID == null) {
			if (other.sessionID != null)
				return false;
		} else if (!sessionID.equals(other.sessionID))
			return false;
		if (transactionType == null) {
			if (other.transactionType != null)
				return false;
		} else if (!transactionType.equals(other.transactionType))
			return false;
		return true;
	}

}