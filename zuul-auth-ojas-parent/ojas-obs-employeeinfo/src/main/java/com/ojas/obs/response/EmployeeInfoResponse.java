package com.ojas.obs.response;

import java.util.List;

import com.ojas.obs.model.EmployeeInfo;

/**
 * 
 * @author sdileep
 *
 */
public class EmployeeInfoResponse {
	List<EmployeeInfo> employeeInfo;
	private String message;
	private String statusCode;

	public List<EmployeeInfo> getEmployeeInfo() {
		return employeeInfo;
	}

	public void setEmployeeInfo(List<EmployeeInfo> employeeInfo) {
		this.employeeInfo = employeeInfo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
		result = prime * result + ((employeeInfo == null) ? 0 : employeeInfo.hashCode());
		result = prime * result + ((statusCode == null) ? 0 : statusCode.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
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
		EmployeeInfoResponse other = (EmployeeInfoResponse) obj;
		if (employeeInfo == null) {
			if (other.employeeInfo != null)
				return false;
		} else if (!employeeInfo.equals(other.employeeInfo))
			return false;
		if (statusCode == null) {
			if (other.statusCode != null)
				return false;
		} else if (!statusCode.equals(other.statusCode))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmployeeInfoResponse [employeeInfo=" + employeeInfo + ", message=" + message + ", statusCode="
				+ statusCode + "]";
	}

}
