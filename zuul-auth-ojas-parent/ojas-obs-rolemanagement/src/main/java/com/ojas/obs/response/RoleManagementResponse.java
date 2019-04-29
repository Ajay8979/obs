package com.ojas.obs.response;

import java.util.List;

import com.ojas.obs.model.RoleManagement;

/**
 * 
 * @author asuneel
 *
 */
public class RoleManagementResponse {

	private List<RoleManagement> roleManagementList;
	private String statusMessage;

	public List<RoleManagement> getRoleManagementList() {
		return roleManagementList;
	}

	public void setRoleManagementList(List<RoleManagement> roleManagementList) {
		this.roleManagementList = roleManagementList;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	@Override
	public String toString() {
		return "RoleManagementResponse [roleManagementList=" + roleManagementList + ", statusMessage=" + statusMessage
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleManagementList == null) ? 0 : roleManagementList.hashCode());
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
		RoleManagementResponse other = (RoleManagementResponse) obj;
		if (roleManagementList == null) {
			if (other.roleManagementList != null)
				return false;
		} else if (!roleManagementList.equals(other.roleManagementList))
			return false;
		if (statusMessage == null) {
			if (other.statusMessage != null)
				return false;
		} else if (!statusMessage.equals(other.statusMessage))
			return false;
		return true;
	}

}
