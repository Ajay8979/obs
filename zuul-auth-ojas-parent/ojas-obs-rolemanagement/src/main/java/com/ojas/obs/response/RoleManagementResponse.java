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
	private Integer pageNo;
	private Integer pageSize;
	private Integer totalCount;
	
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
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	

}
