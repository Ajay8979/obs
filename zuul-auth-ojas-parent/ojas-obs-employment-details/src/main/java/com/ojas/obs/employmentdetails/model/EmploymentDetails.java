package com.ojas.obs.employmentdetails.model;

import java.sql.Date;
import java.sql.Timestamp;

public class EmploymentDetails {

	private Integer id;

	private String employeeId;

	private Date joiningDate;

	private int resourceType;

	private boolean bondStatus;

	private Date resignationDate;

	private Date exitDate;

	private int separationType;

	private Boolean flag;

	private String createdBy;

	private String updatedBy;

	private Timestamp createdDate;

	private Timestamp updatedDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public int getResourceType() {
		return resourceType;
	}

	public void setResourceType(int resourceType) {
		this.resourceType = resourceType;
	}

	

	public boolean isBondStatus() {
		return bondStatus;
	}

	public void setBondStatus(boolean bondStatus) {
		this.bondStatus = bondStatus;
	}

	public Date getResignationDate() {
		return resignationDate;
	}

	public void setResignationDate(Date resignationDate) {
		this.resignationDate = resignationDate;
	}

	public Date getExitDate() {
		return exitDate;
	}

	public void setExitDate(Date exitDate) {
		this.exitDate = exitDate;
	}

	public int getSeparationType() {
		return separationType;
	}

	public void setSeparationType(int separationType) {
		this.separationType = separationType;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
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

	@Override
	public String toString() {
		return "EmploymentDetails [id=" + id + ", employeeId=" + employeeId + ", joiningDate=" + joiningDate
				+ ", resourceType=" + resourceType + ", bondStatus=" + bondStatus + ", resignationDate="
				+ resignationDate + ", exitDate=" + exitDate + ", separationType=" + separationType + ", flag=" + flag
				+ ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + ", createdDate=" + createdDate
				+ ", updatedDate=" + updatedDate + "]";
	}

}
