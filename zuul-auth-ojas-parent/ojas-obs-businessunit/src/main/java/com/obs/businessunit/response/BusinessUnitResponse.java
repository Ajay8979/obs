package com.obs.businessunit.response;

import java.util.List;

import com.obs.businessunit.model.BusinessUnit;

public class BusinessUnitResponse {
	List<BusinessUnit> businessUnitList;
	private String message;
	private String statusCode;
	public List<BusinessUnit> getBusinessUnitList() {
		return businessUnitList;
	}
	public void setBusinessUnitList(List<BusinessUnit> businessUnitList) {
		this.businessUnitList = businessUnitList;
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

}
