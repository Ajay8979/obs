package com.ojas.obs.emp_edu.model;

import java.util.List;

public class EmployeeEducationDetailsRequest {

	private List<EmployeeEducationDetails> employeeEducationDetailsList;
	private String transactionType;
	public List<EmployeeEducationDetails> getEmployeeEducationDetailsList() {
		return employeeEducationDetailsList;
	}
	public void setEmployeeEducationDetailsList(List<EmployeeEducationDetails> employeeEducationDetailsList) {
		this.employeeEducationDetailsList = employeeEducationDetailsList;
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
		result = prime * result
				+ ((employeeEducationDetailsList == null) ? 0 : employeeEducationDetailsList.hashCode());
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
		EmployeeEducationDetailsRequest other = (EmployeeEducationDetailsRequest) obj;
		if (employeeEducationDetailsList == null) {
			if (other.employeeEducationDetailsList != null)
				return false;
		} else if (!employeeEducationDetailsList.equals(other.employeeEducationDetailsList))
			return false;
		if (transactionType == null) {
			if (other.transactionType != null)
				return false;
		} else if (!transactionType.equals(other.transactionType))
			return false;
		return true;
	}
	
}
