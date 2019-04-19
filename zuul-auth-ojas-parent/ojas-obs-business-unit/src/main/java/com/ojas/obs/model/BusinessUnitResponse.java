package com.ojas.obs.model;

import java.util.List;

import com.ojas.obs.model.BusinessUnit;

public class BusinessUnitResponse {
	List<BusinessUnit> businessUnitList;
	private String statusMessage;
	private String totalCount;

	public List<BusinessUnit> getBusinessUnitList() {
		return businessUnitList;
	}

	public void setBusinessUnitList(List<BusinessUnit> businessUnitList) {
		this.businessUnitList = businessUnitList;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((businessUnitList == null) ? 0 : businessUnitList.hashCode());
		result = prime * result + ((statusMessage == null) ? 0 : statusMessage.hashCode());
		result = prime * result + ((totalCount == null) ? 0 : totalCount.hashCode());
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
		BusinessUnitResponse other = (BusinessUnitResponse) obj;
		if (businessUnitList == null) {
			if (other.businessUnitList != null)
				return false;
		} else if (!businessUnitList.equals(other.businessUnitList))
			return false;
		if (statusMessage == null) {
			if (other.statusMessage != null)
				return false;
		} else if (!statusMessage.equals(other.statusMessage))
			return false;
		if (totalCount == null) {
			if (other.totalCount != null)
				return false;
		} else if (!totalCount.equals(other.totalCount))
			return false;
		return true;
	}
}
