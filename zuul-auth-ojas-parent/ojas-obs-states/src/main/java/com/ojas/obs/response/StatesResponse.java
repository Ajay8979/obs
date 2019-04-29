package com.ojas.obs.response;

import java.util.List;

import com.ojas.obs.model.States;

public class StatesResponse{
	private List<States> statesList;
	private String statusMessage;
	private String  statusCode;
	private int totalCount;

	public List<States> getStatesList() {
		return statesList;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatesList(List<States> statesList) {
		this.statesList = statesList;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((statesList == null) ? 0 : statesList.hashCode());
		result = prime * result + ((statusMessage == null) ? 0 : statusMessage.hashCode());
		result = prime * result + totalCount;
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
		if (statesList == null) {
			if (other.statesList != null)
				return false;
		} 
		else if (!statesList.equals(other.statesList))
			return false;
		if (statusMessage == null) {
			if (other.statusMessage != null)
				return false;
		}
		else if (!statusMessage.equals(other.statusMessage))
			return false;
		if (totalCount != other.totalCount)
			return false;
		return true;
	}
}
