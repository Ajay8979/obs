package com.ojas.obs.response;

import java.util.List;

import com.ojas.obs.model.Genders;

public class GenderResponse {
	private List<Genders> genderList;
	private String statusMessage;
	private int totalCount;
	private String statusCode;
	public List<Genders> getGenderList() {
		return genderList;
	}
	public void setGenderList(List<Genders> genderList) {
		this.genderList = genderList;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
