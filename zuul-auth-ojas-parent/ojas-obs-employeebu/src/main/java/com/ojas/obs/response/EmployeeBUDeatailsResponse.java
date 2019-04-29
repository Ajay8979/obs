package com.ojas.obs.response;

import java.util.List;

import com.ojas.obs.model.EmployeeBUDetails;

public class EmployeeBUDeatailsResponse {

	private List<EmployeeBUDetails> listCourse;
	private Integer totalCount;
	private Integer pageNo;
	private Integer pageSize;
	private String statusMessage;

	public List<EmployeeBUDetails> getListCourse() {
		return listCourse;
	}

	public void setListCourse(List<EmployeeBUDetails> listCourse) {
		this.listCourse = listCourse;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
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

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listCourse == null) ? 0 : listCourse.hashCode());
		result = prime * result + ((pageNo == null) ? 0 : pageNo.hashCode());
		result = prime * result + ((pageSize == null) ? 0 : pageSize.hashCode());
		result = prime * result + ((statusMessage == null) ? 0 : statusMessage.hashCode());
		result = prime * result + ((totalCount == null) ? 0 : totalCount.hashCode());
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
		EmployeeBUDeatailsResponse other = (EmployeeBUDeatailsResponse) obj;
		if (listCourse == null) {
			if (other.listCourse != null)
				return false;
		} else if (!listCourse.equals(other.listCourse))
			return false;
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
		return true;
	}

	@Override
	public String toString() {
		return "EmployeeBUDeatailsResponse [listCourse=" + listCourse + ", totalCount=" + totalCount + ", pageNo="
				+ pageNo + ", pageSize=" + pageSize + ", statusMessage=" + statusMessage + "]";
	}

	
}
