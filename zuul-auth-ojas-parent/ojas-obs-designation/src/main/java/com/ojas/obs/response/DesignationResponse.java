package com.ojas.obs.response;

import java.util.List;

import com.ojas.model.Designation;

/**
 * 
 * @author nsrikanth
 *
 */
public class DesignationResponse {

	private List<Designation> listDesignation;
	private String statusMessage;
	private int totalCount;

	public List<Designation> getListDesignation() {
		return listDesignation;
	}

	public void setListDesignation(List<Designation> listDesignation) {
		this.listDesignation = listDesignation;
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

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listDesignation == null) ? 0 : listDesignation.hashCode());
		result = prime * result + ((statusMessage == null) ? 0 : statusMessage.hashCode());
		result = prime * result + totalCount;
		return result;
	}

	@Override
	public String toString() {
		return "DesignationResponse [listDesignation=" + listDesignation + ", statusMessage=" + statusMessage
				+ ", totalCount=" + totalCount + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DesignationResponse other = (DesignationResponse) obj;
		if (listDesignation == null) {
			if (other.listDesignation != null)
				return false;
		} else if (!listDesignation.equals(other.listDesignation))
			return false;
		if (statusMessage == null) {
			if (other.statusMessage != null)
				return false;
		} else if (!statusMessage.equals(other.statusMessage))
			return false;
		if (totalCount != other.totalCount)
			return false;
		return true;
	}

}
