package com.ojas.obs.request;

import java.util.List;

import com.ojas.obs.model.RoleManagement;

public class RoleManagementRequest {

	private List<RoleManagement> roleManagement;
	private String sessionId;
	private Integer pageNo;
	private Integer pageSize;
	private String transactionType;
	public List<RoleManagement> getRoleManagement() {
		return roleManagement;
	}
	public void setRoleManagement(List<RoleManagement> roleManagement) {
		this.roleManagement = roleManagement;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	@Override
	public String toString() {
		return "RoleManagementRequest [roleManagement=" + roleManagement + ", sessionId=" + sessionId + ", pageNo="
				+ pageNo + ", pageSize=" + pageSize + ", transactionType=" + transactionType + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pageNo == null) ? 0 : pageNo.hashCode());
		result = prime * result + ((pageSize == null) ? 0 : pageSize.hashCode());
		result = prime * result + ((roleManagement == null) ? 0 : roleManagement.hashCode());
		result = prime * result + ((sessionId == null) ? 0 : sessionId.hashCode());
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
		RoleManagementRequest other = (RoleManagementRequest) obj;
		if (pageNo == null) {
			if (other.pageNo != null)
				return false;
		} else if (!pageNo.equals(other.pageNo))
			return false;
		if (pageSize == null) {
			if (other.pageSize != null)
				return false;
		} else if (!pageSize.equals(other.pageSize))
			return false;
		if (roleManagement == null) {
			if (other.roleManagement != null)
				return false;
		} else if (!roleManagement.equals(other.roleManagement))
			return false;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
			return false;
		if (transactionType == null) {
			if (other.transactionType != null)
				return false;
		} else if (!transactionType.equals(other.transactionType))
			return false;
		return true;
	}

	

}
