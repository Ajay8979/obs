package com.ojas.obs.response;

import java.util.List;

import com.ojas.obs.model.SubBusinessUnit;

/**
 * 
 * @author asuneel
 *
 */
public class SubBusinessUnitResponse {

	private List<SubBusinessUnit> subBusinessUnitList;

	private String message;
	private Integer pageNo;
	private Integer pageSize;
	private Integer totalCount;

	public List<SubBusinessUnit> getSubBusinessUnitList() {
		return subBusinessUnitList;
	}

	public void setSubBusinessUnitList(List<SubBusinessUnit> subBusinessUnitList) {
		this.subBusinessUnitList = subBusinessUnitList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	@Override
	public String toString() {
		return "SubBusinessUnitResponse [subBusinessUnitList=" + subBusinessUnitList + ", message=" + message
				+ ", pageNo=" + pageNo + ", pageSize=" + pageSize + ", totalCount=" + totalCount + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((pageNo == null) ? 0 : pageNo.hashCode());
		result = prime * result + ((pageSize == null) ? 0 : pageSize.hashCode());
		result = prime * result + ((subBusinessUnitList == null) ? 0 : subBusinessUnitList.hashCode());
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
		SubBusinessUnitResponse other = (SubBusinessUnitResponse) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
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
		if (subBusinessUnitList == null) {
			if (other.subBusinessUnitList != null)
				return false;
		} else if (!subBusinessUnitList.equals(other.subBusinessUnitList))
			return false;
		if (totalCount == null) {
			if (other.totalCount != null)
				return false;
		} else if (!totalCount.equals(other.totalCount))
			return false;
		return true;
	}

}
