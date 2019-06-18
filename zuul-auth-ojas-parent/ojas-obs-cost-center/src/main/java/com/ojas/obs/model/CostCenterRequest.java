package com.ojas.obs.model;

import java.util.List;

public class CostCenterRequest {

	private List<CostCenter> costCenter;
	private String transactionType;
	public List<CostCenter> getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(List<CostCenter> costCenter) {
		this.costCenter = costCenter;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
	@Override
	public String toString() {
		return "CostCenterRequest [costCenter=" + costCenter + ", transactionType=" + transactionType + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((costCenter == null) ? 0 : costCenter.hashCode());
		result = prime * result + ((transactionType == null) ? 0 : transactionType.hashCode());
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
		CostCenterRequest other = (CostCenterRequest) obj;
		if (costCenter == null) {
			if (other.costCenter != null)
				return false;
		} else if (!costCenter.equals(other.costCenter))
			return false;
		if (transactionType == null) {
			if (other.transactionType != null)
				return false;
		} else if (!transactionType.equals(other.transactionType))
			return false;
		return true;
	}
	

	
	
}
