package com.ojas.obs.request;

import java.util.List;

import com.ojas.obs.model.EmployeeInfo;

/**
 * 
 * @author sdileep
 *
 */
public class EmployeeInfoRequest {

	private List<EmployeeInfo> employeeInfo;
	private String transactionType;

	public List<EmployeeInfo> getEmployeeInfo() {
		return employeeInfo;
	}

	public void setEmployeeInfo(List<EmployeeInfo> employeeInfo) {
		this.employeeInfo = employeeInfo;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employeeInfo == null) ? 0 : employeeInfo.hashCode());
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
		EmployeeInfoRequest other = (EmployeeInfoRequest) obj;
		if (employeeInfo == null) {
			if (other.employeeInfo != null)
				return false;
		} else if (!employeeInfo.equals(other.employeeInfo))
			return false;
		if (transactionType == null) {
			if (other.transactionType != null)
				return false;
		} else if (!transactionType.equals(other.transactionType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmployeeInfoRequest [employeeInfo=" + employeeInfo + ", transactionType=" + transactionType + "]";
	}

}