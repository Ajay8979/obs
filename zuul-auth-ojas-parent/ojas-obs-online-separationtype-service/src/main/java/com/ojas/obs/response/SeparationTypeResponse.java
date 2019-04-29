package com.ojas.obs.response;

import java.util.List;

import com.ojas.obs.model.SeparationType;


/**
 * 
 * @author nsrikanth
 *
 */
public class SeparationTypeResponse {
	 
    //private SeparationType separationType;
	
	private String statusMessage;
	
	private List<SeparationType> separationTypeList;

	/*
	 * public SeparationType getSeparationType() { return separationType; }
	 */

	/*
	 * public void setSeparationType(SeparationType separationType) {
	 * this.separationType = separationType; }
	 */
	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public List<SeparationType> getSeparationTypeList() {
		return separationTypeList;
	}

	public void setSeparationTypeList(List<SeparationType> separationTypeList) {
		this.separationTypeList = separationTypeList;
	}

	@Override
	public String toString() {
		return "SeparationTypeResponse [statusMessage=" + statusMessage + ", separationTypeList=" + separationTypeList
				+ ", getStatusMessage()=" + getStatusMessage() + ", getSeparationTypeList()=" + getSeparationTypeList()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((separationTypeList == null) ? 0 : separationTypeList.hashCode());
		result = prime * result + ((statusMessage == null) ? 0 : statusMessage.hashCode());
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
		SeparationTypeResponse other = (SeparationTypeResponse) obj;
		if (separationTypeList == null) {
			if (other.separationTypeList != null)
				return false;
		} else if (!separationTypeList.equals(other.separationTypeList))
			return false;
		if (statusMessage == null) {
			if (other.statusMessage != null)
				return false;
		} else if (!statusMessage.equals(other.statusMessage))
			return false;
		return true;
	}
	
	
	

/*	@Override
	public String toString() {
		return "SeparationTypeResponse [separationType=" + separationType + ", statusMessage=" + statusMessage
				+ ", separationTypeList=" + separationTypeList + ", getSeparationType()=" + getSeparationType()
				+ ", getStatusMessage()=" + getStatusMessage() + ", getSeparationTypeList()=" + getSeparationTypeList()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((separationType == null) ? 0 : separationType.hashCode());
		result = prime * result + ((separationTypeList == null) ? 0 : separationTypeList.hashCode());
		result = prime * result + ((statusMessage == null) ? 0 : statusMessage.hashCode());
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
		SeparationTypeResponse other = (SeparationTypeResponse) obj;
		if (separationType == null) {
			if (other.separationType != null)
				return false;
		} else if (!separationType.equals(other.separationType))
			return false;
		if (separationTypeList == null) {
			if (other.separationTypeList != null)
				return false;
		} else if (!separationTypeList.equals(other.separationTypeList))
			return false;
		if (statusMessage == null) {
			if (other.statusMessage != null)
				return false;
		} else if (!statusMessage.equals(other.statusMessage))
			return false;
		return true*/;
	}
	
	
	


