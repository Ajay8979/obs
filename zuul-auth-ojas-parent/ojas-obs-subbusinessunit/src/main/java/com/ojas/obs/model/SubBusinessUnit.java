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
	private String businessUnitName;
	private String businessUnitId;

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

	public String getBusinessUnitName() {
		return businessUnitName;
	}

	public void setBusinessUnitName(String businessUnitName) {
		this.businessUnitName = businessUnitName;
	}

	public String getBusinessUnitId() {
		return businessUnitId;
	}

	public void setBusinessUnitId(String businessUnitId) {
		this.businessUnitId = businessUnitId;
	}

	@Override
	public String toString() {
		return "SubBusinessUnit [id=" + id + ", name=" + name + ", costCenterId=" + costCenterId + ", businessUnitName="
				+ businessUnitName + ", businessUnitId=" + businessUnitId + "]";
	}

}
