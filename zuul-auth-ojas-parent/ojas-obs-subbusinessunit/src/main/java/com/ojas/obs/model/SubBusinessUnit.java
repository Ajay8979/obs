package com.ojas.obs.model;
/**
 * 
 * @author asuneel
 *
 */
public class SubBusinessUnit {

	private Integer id;
	private String name;
	private Integer costCenterId;
	private Integer businessUnitId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCostCenterId() {
		return costCenterId;
	}
	public void setCostCenterId(Integer costCenterId) {
		this.costCenterId = costCenterId;
	}
	public Integer getBusinessUnitId() {
		return businessUnitId;
	}
	public void setBusinessUnitId(Integer businessUnitId) {
		this.businessUnitId = businessUnitId;
	}
	
	@Override
	public String toString() {
		return "SubBusinessUnit [id=" + id + ", name=" + name + ", costCenterId=" + costCenterId + ", businessUnitId="
				+ businessUnitId + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((businessUnitId == null) ? 0 : businessUnitId.hashCode());
		result = prime * result + ((costCenterId == null) ? 0 : costCenterId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		SubBusinessUnit other = (SubBusinessUnit) obj;
		if (businessUnitId == null) {
			if (other.businessUnitId != null)
				return false;
		} else if (!businessUnitId.equals(other.businessUnitId))
			return false;
		if (costCenterId == null) {
			if (other.costCenterId != null)
				return false;
		} else if (!costCenterId.equals(other.costCenterId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
	
	

}
