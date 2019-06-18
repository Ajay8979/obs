package com.ojas.obs.model;

import java.util.List;

public class CostCenterResponse {

	private List<CostCenter> listOfCostCenter;
	private String message;
	private String statusCode;
	public List<CostCenter> getListOfCostCenter() {
		return listOfCostCenter;
	}
	public void setListOfCostCenter(List<CostCenter> listOfCostCenter) {
		this.listOfCostCenter = listOfCostCenter;
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
	public String toString() {
		return "CostCenterResponse [listOfCostCenter=" + listOfCostCenter + ", message=" + message + ", statusCode="
				+ statusCode + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listOfCostCenter == null) ? 0 : listOfCostCenter.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((statusCode == null) ? 0 : statusCode.hashCode());
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
		CostCenterResponse other = (CostCenterResponse) obj;
		if (listOfCostCenter == null) {
			if (other.listOfCostCenter != null)
				return false;
		} else if (!listOfCostCenter.equals(other.listOfCostCenter))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (statusCode == null) {
			if (other.statusCode != null)
				return false;
		} else if (!statusCode.equals(other.statusCode))
			return false;
		return true;
	}
	
	
	
	
}
