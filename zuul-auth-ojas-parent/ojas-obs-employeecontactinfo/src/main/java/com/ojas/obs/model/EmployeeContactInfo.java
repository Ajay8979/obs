package com.ojas.obs.model;

import java.sql.Timestamp;

public class EmployeeContactInfo {

	private Integer id;
	private String email;
	private String personalMobileNo;
	private String alternateMobileNo;
	private String currentAddressLine1;
	private String currentAddressLine2;
	private String currentCity;
	private Integer currentState;
	private Integer currentPin;
	private String permanentAddressLine1;
	private String empId;
	private String createdBy;
	private String updatedBy;
	private Timestamp createdDate;
	private Timestamp updatedDate;
	private boolean flag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPersonalMobileNo() {
		return personalMobileNo;
	}

	public void setPersonalMobileNo(String personalMobileNo) {
		this.personalMobileNo = personalMobileNo;
	}

	public String getAlternateMobileNo() {
		return alternateMobileNo;
	}

	public void setAlternateMobileNo(String alternateMobileNo) {
		this.alternateMobileNo = alternateMobileNo;
	}

	public String getCurrentAddressLine1() {
		return currentAddressLine1;
	}

	public void setCurrentAddressLine1(String currentAddressLine1) {
		this.currentAddressLine1 = currentAddressLine1;
	}

	public String getCurrentAddressLine2() {
		return currentAddressLine2;
	}

	public void setCurrentAddressLine2(String currentAddressLine2) {
		this.currentAddressLine2 = currentAddressLine2;
	}

	public String getCurrentCity() {
		return currentCity;
	}

	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}

	public Integer getCurrentState() {
		return currentState;
	}

	public void setCurrentState(Integer currentState) {
		this.currentState = currentState;
	}

	public Integer getCurrentPin() {
		return currentPin;
	}

	public void setCurrentPin(Integer currentPin) {
		this.currentPin = currentPin;
	}

	public String getPermanentAddressLine1() {
		return permanentAddressLine1;
	}

	public void setPermanentAddressLine1(String permanentAddressLine1) {
		this.permanentAddressLine1 = permanentAddressLine1;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "EmployeeContactInfo [id=" + id + ", email=" + email + ", personalMobileNo=" + personalMobileNo
				+ ", alternateMobileNo=" + alternateMobileNo + ", currentAddressLine1=" + currentAddressLine1
				+ ", currentAddressLine2=" + currentAddressLine2 + ", currentCity=" + currentCity + ", currentState="
				+ currentState + ", currentPin=" + currentPin + ", permanentAddressLine1=" + permanentAddressLine1
				+ ", empId=" + empId + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + ", createdDate="
				+ createdDate + ", updatedDate=" + updatedDate + ", flag=" + flag + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alternateMobileNo == null) ? 0 : alternateMobileNo.hashCode());
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((currentAddressLine1 == null) ? 0 : currentAddressLine1.hashCode());
		result = prime * result + ((currentAddressLine2 == null) ? 0 : currentAddressLine2.hashCode());
		result = prime * result + ((currentCity == null) ? 0 : currentCity.hashCode());
		result = prime * result + ((currentPin == null) ? 0 : currentPin.hashCode());
		result = prime * result + ((currentState == null) ? 0 : currentState.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((empId == null) ? 0 : empId.hashCode());
		result = prime * result + (flag ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((permanentAddressLine1 == null) ? 0 : permanentAddressLine1.hashCode());
		result = prime * result + ((personalMobileNo == null) ? 0 : personalMobileNo.hashCode());
		result = prime * result + ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = prime * result + ((updatedDate == null) ? 0 : updatedDate.hashCode());
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
		EmployeeContactInfo other = (EmployeeContactInfo) obj;
		if (alternateMobileNo == null) {
			if (other.alternateMobileNo != null)
				return false;
		} else if (!alternateMobileNo.equals(other.alternateMobileNo))
			return false;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (currentAddressLine1 == null) {
			if (other.currentAddressLine1 != null)
				return false;
		} else if (!currentAddressLine1.equals(other.currentAddressLine1))
			return false;
		if (currentAddressLine2 == null) {
			if (other.currentAddressLine2 != null)
				return false;
		} else if (!currentAddressLine2.equals(other.currentAddressLine2))
			return false;
		if (currentCity == null) {
			if (other.currentCity != null)
				return false;
		} else if (!currentCity.equals(other.currentCity))
			return false;
		if (currentPin == null) {
			if (other.currentPin != null)
				return false;
		} else if (!currentPin.equals(other.currentPin))
			return false;
		if (currentState == null) {
			if (other.currentState != null)
				return false;
		} else if (!currentState.equals(other.currentState))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (empId == null) {
			if (other.empId != null)
				return false;
		} else if (!empId.equals(other.empId))
			return false;
		if (flag != other.flag)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (permanentAddressLine1 == null) {
			if (other.permanentAddressLine1 != null)
				return false;
		} else if (!permanentAddressLine1.equals(other.permanentAddressLine1))
			return false;
		if (personalMobileNo == null) {
			if (other.personalMobileNo != null)
				return false;
		} else if (!personalMobileNo.equals(other.personalMobileNo))
			return false;
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		if (updatedDate == null) {
			if (other.updatedDate != null)
				return false;
		} else if (!updatedDate.equals(other.updatedDate))
			return false;
		return true;
	}

}
