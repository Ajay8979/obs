package com.ojas.obs.response;

import java.util.List;

import com.ojas.obs.model.Genders;

public class GenderResponse {
	private List<Genders> genderList;
	private String message;
	private String statusCode;
	public List<Genders> getGenderList() {
		return genderList;
	}
	public void setGenderList(List<Genders> genderList) {
		this.genderList = genderList;
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
		result = prime * result + ((genderList == null) ? 0 : genderList.hashCode());
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
		GenderResponse other = (GenderResponse) obj;
		if (genderList == null) {
			if (other.genderList != null)
				return false;
		} else if (!genderList.equals(other.genderList))
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
