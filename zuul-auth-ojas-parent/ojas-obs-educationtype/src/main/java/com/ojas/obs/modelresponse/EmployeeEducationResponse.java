package com.ojas.obs.modelresponse;

import java.util.List;


import com.ojas.obs.model.EmployeeEducation;

/**
 * 
 * @author mpraneethguptha
 *
 */
public class EmployeeEducationResponse {

	private List<EmployeeEducation> listCourse;

	private Integer totalCount;

	private Integer pageNo;

	private Integer pageSize;

	private String statusMessage;

	public List<EmployeeEducation> getListCourse() {
		return listCourse;
	}

	public void setListCourse(List<EmployeeEducation> listCourse) {
		this.listCourse = listCourse;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
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

	@Override
	public String toString() {
		return "EducationResponse [listCourse=" + listCourse + ", totalCount=" + totalCount + ", pageNo=" + pageNo
				+ ", pageSize=" + pageSize + ", statusMessage=" + statusMessage + "]";
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
		EmployeeEducationResponse other = (EmployeeEducationResponse) obj;
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

}
