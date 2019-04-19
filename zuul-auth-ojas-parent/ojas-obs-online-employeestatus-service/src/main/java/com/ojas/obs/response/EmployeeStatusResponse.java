package com.ojas.obs.response;

import java.util.List;

import com.ojas.obs.model.EmployeeStatus;

/**
 * 
 * @author Manohar
 *
 */
public class EmployeeStatusResponse {
	private String Status;
	private List<EmployeeStatus> employeeStatusList;

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public List<EmployeeStatus> getEmployeeStatusList() {
		return employeeStatusList;
	}

	public void setEmployeeStatusList(List<EmployeeStatus> employeeStatusList) {
		this.employeeStatusList = employeeStatusList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Status == null) ? 0 : Status.hashCode());
		result = prime * result + ((employeeStatusList == null) ? 0 : employeeStatusList.hashCode());
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
		EmployeeStatusResponse other = (EmployeeStatusResponse) obj;
		if (Status == null) {
			if (other.Status != null)
				return false;
		} else if (!Status.equals(other.Status))
			return false;
		if (employeeStatusList == null) {
			if (other.employeeStatusList != null)
				return false;
		} else if (!employeeStatusList.equals(other.employeeStatusList))
			return false;
		return true;
	}

}
