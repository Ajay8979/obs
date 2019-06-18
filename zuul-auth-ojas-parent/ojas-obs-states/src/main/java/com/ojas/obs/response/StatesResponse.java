package com.ojas.obs.response;

import java.util.List;

import com.ojas.obs.model.States;

public class StatesResponse{
	private List<States> statesList;
	private String message;
	private String  statusCode;
	public List<States> getStatesList() {
		return statesList;
	}
	public void setStatesList(List<States> statesList) {
		this.statesList = statesList;
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
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((statesList == null) ? 0 : statesList.hashCode());
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
		StatesResponse other = (StatesResponse) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (statesList == null) {
			if (other.statesList != null)
				return false;
		} else if (!statesList.equals(other.statesList))
			return false;
		if (statusCode == null) {
			if (other.statusCode != null)
				return false;
		} else if (!statusCode.equals(other.statusCode))
			return false;
		return true;
	}
	

}
