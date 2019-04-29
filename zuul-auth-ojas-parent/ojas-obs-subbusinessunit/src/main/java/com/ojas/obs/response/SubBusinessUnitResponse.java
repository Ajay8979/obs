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

	@Override
	public String toString() {
		return "SubBusinessUnitResponse [subBusinessUnitList=" + subBusinessUnitList + ", message=" + message + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((subBusinessUnitList == null) ? 0 : subBusinessUnitList.hashCode());
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
		if (subBusinessUnitList == null) {
			if (other.subBusinessUnitList != null)
				return false;
		} else if (!subBusinessUnitList.equals(other.subBusinessUnitList))
			return false;
		return true;
	}

}
