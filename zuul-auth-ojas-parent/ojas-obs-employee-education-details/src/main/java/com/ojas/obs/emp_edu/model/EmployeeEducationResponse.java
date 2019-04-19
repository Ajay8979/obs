package com.ojas.obs.emp_edu.model;

import java.util.List;

public class EmployeeEducationResponse {
	private List<EmployeeEducationDetails> employeeEducationDetailsList;
	private String statusMessage;
	private String statusCode;

	public List<EmployeeEducationDetails> getEmployeeEducationDetailsList() {
		return employeeEducationDetailsList;
	}

	public void setEmployeeEducationDetailsList(List<EmployeeEducationDetails> employeeEducationDetailsList) {
		this.employeeEducationDetailsList = employeeEducationDetailsList;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((employeeEducationDetailsList == null) ? 0 : employeeEducationDetailsList.hashCode());
		result = prime * result + ((statusCode == null) ? 0 : statusCode.hashCode());
		result = prime * result + ((statusMessage == null) ? 0 : statusMessage.hashCode());
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
		EmployeeEducationResponse other = (EmployeeEducationResponse) obj;
		if (employeeEducationDetailsList == null) {
			if (other.employeeEducationDetailsList != null)
				return false;
		} else if (!employeeEducationDetailsList.equals(other.employeeEducationDetailsList))
			return false;
		if (statusCode == null) {
			if (other.statusCode != null)
				return false;
		} else if (!statusCode.equals(other.statusCode))
			return false;
		if (statusMessage == null) {
			if (other.statusMessage != null)
				return false;
		} else if (!statusMessage.equals(other.statusMessage))
			return false;
		return true;
	}

}
