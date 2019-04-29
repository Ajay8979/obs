package com.ojas.obs.emp_edu.model;

import java.util.List;

public class EmployeeEducationDetailsRequest {

	private List<EmployeeEducationDetails> employeeEducationDetailsList;
	private String transaactionType;
	private Integer pageNo;
	private Integer pageSize;
	public List<EmployeeEducationDetails> getEmployeeEducationDetailsList() {
		return employeeEducationDetailsList;
	}
	public void setEmployeeEducationDetailsList(List<EmployeeEducationDetails> employeeEducationDetailsList) {
		this.employeeEducationDetailsList = employeeEducationDetailsList;
	}
	public String getTransaactionType() {
		return transaactionType;
	}
	public void setTransaactionType(String transaactionType) {
		this.transaactionType = transaactionType;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((employeeEducationDetailsList == null) ? 0 : employeeEducationDetailsList.hashCode());
		result = prime * result + ((pageNo == null) ? 0 : pageNo.hashCode());
		result = prime * result + ((pageSize == null) ? 0 : pageSize.hashCode());
		result = prime * result + ((transaactionType == null) ? 0 : transaactionType.hashCode());
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
		EmployeeEducationDetailsRequest other = (EmployeeEducationDetailsRequest) obj;
		if (employeeEducationDetailsList == null) {
			if (other.employeeEducationDetailsList != null)
				return false;
		} else if (!employeeEducationDetailsList.equals(other.employeeEducationDetailsList))
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
		if (transaactionType == null) {
			if (other.transaactionType != null)
				return false;
		} else if (!transaactionType.equals(other.transaactionType))
			return false;
		return true;
	}
	
	
}
