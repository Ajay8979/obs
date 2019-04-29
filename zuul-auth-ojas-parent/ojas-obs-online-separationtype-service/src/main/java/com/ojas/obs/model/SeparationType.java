package com.ojas.obs.model;
/**
 * 
 * @author nsrikanth
 *
 */

public class SeparationType {
	 
	private Integer separationTypeId;
	
	private String separationType;


	public Integer getSeparationTypeId() {
		return separationTypeId;
	}

	public void setSeparationTypeId(Integer separationTypeId) {
		this.separationTypeId = separationTypeId;
	}

	public String getSeparationType() {
		return separationType;
	}

	public void setSeparationType(String separationType) {
		this.separationType = separationType;
	}

	@Override
	public String toString() {
		return "SeparationType [separationTypeId=" + separationTypeId + ", separationType=" + separationType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((separationType == null) ? 0 : separationType.hashCode());
		result = prime * result + ((separationTypeId == null) ? 0 : separationTypeId.hashCode());
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
		SeparationType other = (SeparationType) obj;
		if (separationType == null) {
			if (other.separationType != null)
				return false;
		} else if (!separationType.equals(other.separationType))
			return false;
		if (separationTypeId == null) {
			if (other.separationTypeId != null)
				return false;
		} else if (!separationTypeId.equals(other.separationTypeId))
			return false;
		return true;
	}	
	

}
