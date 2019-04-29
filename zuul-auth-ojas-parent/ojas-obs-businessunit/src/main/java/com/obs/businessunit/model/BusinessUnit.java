package com.obs.businessunit.model;

public class BusinessUnit {
	private Integer id;
	private String businessUnitName;
	private int costCenterId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		} else if (!businessUnitName.equals(other.businessUnitName))
			return false;
		if (costCenterId != other.costCenterId)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
