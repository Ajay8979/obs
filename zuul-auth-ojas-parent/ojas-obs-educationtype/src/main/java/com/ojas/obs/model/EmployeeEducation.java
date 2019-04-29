package com.ojas.obs.model;
/**
 * 
 * @author mpraneethguptha
 *
 */
public class EmployeeEducation {

	private Integer id;
	private String educationType;
	


	public String getEducationType() {
		return educationType;
	}

	public void setEducationType(String educationType) {
		this.educationType = educationType;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((educationType == null) ? 0 : educationType.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		EmployeeEducation other = (EmployeeEducation) obj;
		if (educationType == null) {
			if (other.educationType != null)
				return false;
		} else if (!educationType.equals(other.educationType))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void setId(Integer id) {
		this.id = id;
	}


}
