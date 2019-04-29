package com.ojas.obs.response;

import java.util.List;

import com.ojas.obs.model.EmployeeContactInfo;

public class EmployeeContactInfoResponse {

	

	private String statusCode;
	private String statusMessage;
	
	private List<EmployeeContactInfo> empContacts;
	
	
	
	
	
	


	public List<EmployeeContactInfo> getEmpContacts() {
		return empContacts;
	}
	public void setEmpContacts(List<EmployeeContactInfo> empContacts) {
		this.empContacts = empContacts;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	
	
	
	
	
}
