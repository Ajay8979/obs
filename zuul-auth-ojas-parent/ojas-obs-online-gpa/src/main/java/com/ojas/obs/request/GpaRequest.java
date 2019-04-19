package com.ojas.obs.request;

import java.util.List;

import com.ojas.obs.model.GpaPlan;
/**
 * 
 * @author pnaveen
 *
 */

public class GpaRequest {

	private List<GpaPlan> gpaPlan;
	private String sessionId;
	private Integer totalCount;
	private Integer pageSize;
	private Integer pageNum;
	private String statusMessage;
	private String transactionType;
	public List<GpaPlan> getGpaPlan() {
		return gpaPlan;
	}
	public void setGpaPlan(List<GpaPlan> gpaPlan) {
		this.gpaPlan = gpaPlan;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gpaPlan == null) ? 0 : gpaPlan.hashCode());
		result = prime * result + ((pageNum == null) ? 0 : pageNum.hashCode());
		result = prime * result + ((pageSize == null) ? 0 : pageSize.hashCode());
		result = prime * result + ((sessionId == null) ? 0 : sessionId.hashCode());
		result = prime * result + ((statusMessage == null) ? 0 : statusMessage.hashCode());
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
		GpaRequest other = (GpaRequest) obj;
		if (gpaPlan == null) {
			if (other.gpaPlan != null)
				return false;
		} else if (!gpaPlan.equals(other.gpaPlan))
			return false;
		if (pageNum == null) {
			if (other.pageNum != null)
				return false;
		} else if (!pageNum.equals(other.pageNum))
			return false;
		if (pageSize == null) {
			if (other.pageSize != null)
				return false;
		} else if (!pageSize.equals(other.pageSize))
			return false;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
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
		if (transactionType == null) {
			if (other.transactionType != null)
				return false;
		} else if (!transactionType.equals(other.transactionType))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "GpaRequest [gpaPlan=" + gpaPlan + ", sessionId=" + sessionId + ", totalCount=" + totalCount
				+ ", pageSize=" + pageSize + ", pageNum=" + pageNum + ", statusMessage=" + statusMessage
				+ ", transactionType=" + transactionType + "]";
	}

	
}

