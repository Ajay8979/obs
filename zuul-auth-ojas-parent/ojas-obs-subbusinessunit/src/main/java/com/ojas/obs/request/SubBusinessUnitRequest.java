package com.ojas.obs.request;

import java.util.List;

import com.ojas.obs.model.SubBusinessUnit;

/**
 * 
 * @author asuneel
 *
 */
public class SubBusinessUnitRequest {

	private List<SubBusinessUnit> subBusinessUnitModel;
	private Integer totalCount;
	private String message;
	private String transactionType;
	public List<SubBusinessUnit> getSubBusinessUnitModel() {
		return subBusinessUnitModel;
	}
	public void setSubBusinessUnitModel(List<SubBusinessUnit> subBusinessUnitModel) {
		this.subBusinessUnitModel = subBusinessUnitModel;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	@Override
	public String toString() {
		return "SubBusinessUnitRequest [subBusinessUnitModel=" + subBusinessUnitModel + ", totalCount=" + totalCount
				+ ", message=" + message + ", transactionType=" + transactionType + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((subBusinessUnitModel == null) ? 0 : subBusinessUnitModel.hashCode());
		result = prime * result + ((totalCount == null) ? 0 : totalCount.hashCode());
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
		SubBusinessUnitRequest other = (SubBusinessUnitRequest) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (subBusinessUnitModel == null) {
			if (other.subBusinessUnitModel != null)
				return false;
		} else if (!subBusinessUnitModel.equals(other.subBusinessUnitModel))
			return false;
		if (totalCount == null) {
			if (other.totalCount != null)
				return false;
		} else if (!totalCount.equals(other.totalCount))
			return false;
		if (transactionType == null) {
			if (other.transactionType != null)
				return false;
		} else if (!transactionType.equals(other.transactionType))
			return false;
		return true;
	}

	

}