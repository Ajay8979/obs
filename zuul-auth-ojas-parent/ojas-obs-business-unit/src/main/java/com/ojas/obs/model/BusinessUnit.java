
package com.ojas.obs.model;

public class BusinessUnit {
	private int id;
	private String businessUnitName;
	private int costCenterId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBusinessUnitName() {
		return businessUnitName;
	}

	public void setBusinessUnitName(String businessUnitName) {
		this.businessUnitName = businessUnitName;
	}

	public int getCostCenterId() {
		return costCenterId;
	}

	public void setCostCenterId(int costCenterId) {
		this.costCenterId = costCenterId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((businessUnitName == null) ? 0 : businessUnitName.hashCode());
		result = prime * result + costCenterId;
		result = prime * result + id;
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
		BusinessUnit other = (BusinessUnit) obj;
		if (businessUnitName == null) {
			if (other.businessUnitName != null)
				return false;
		} 
		else if (!businessUnitName.equals(other.businessUnitName))
			return false;
		if (costCenterId != other.costCenterId)
			return false;
		if (id != other.id)
			return false;
		return true;
	}
}