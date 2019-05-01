package com.ojas.obs.model;


import java.sql.Timestamp;

public class EmployeeContactInfo {

	
	private int id;
	private String email; 
	private String personal_mobileNo;
	private String alternate_mobileNo;
	private String current_Address_Line1; 
	private String current_Address_Line2;
	private String current_City;
	private int current_State;
	private int current_Pin;
	private String permanent_Address_Line_1; 
	private String emp_Id;
	private String created_By;
	private String updated_By;
	private Timestamp created_date;
	private Timestamp updated_date;
	private boolean flag;
	
	
	
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getEmp_Id() {
		return emp_Id;
	}
	public void setEmp_Id(String emp_Id) {
		this.emp_Id = emp_Id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getCurrent_Address_Line1() {
		return current_Address_Line1;
	}
	public void setCurrent_Address_Line1(String current_Address_Line1) {
		this.current_Address_Line1 = current_Address_Line1;
	}
	public String getCurrent_Address_Line2() {
		return current_Address_Line2;
	}
	public void setCurrent_Address_Line2(String current_Address_Line2) {
		this.current_Address_Line2 = current_Address_Line2;
	}
	public String getCurrent_City() {
		return current_City;
	}
	public void setCurrent_City(String current_City) {
		this.current_City = current_City;
	}


	public int getCurrent_State() {
		return current_State;
	}
	public void setCurrent_State(int current_State) {
		this.current_State = current_State;
	}
	public String getPermanent_Address_Line_1() {
		return permanent_Address_Line_1;
	}
	public void setPermanent_Address_Line_1(String permanent_Address_Line_1) {
		this.permanent_Address_Line_1 = permanent_Address_Line_1;
	}
	public String getPersonal_mobileNo() {
		return personal_mobileNo;
	}
	public void setPersonal_mobileNo(String personal_mobileNo) {
		this.personal_mobileNo = personal_mobileNo;
	}
	public String getAlternate_mobileNo() {
		return alternate_mobileNo;
	}
	public void setAlternate_mobileNo(String alternate_mobileNo) {
		this.alternate_mobileNo = alternate_mobileNo;
	}

	
	public int getCurrent_Pin() {
		return current_Pin;
	}
	public void setCurrent_Pin(int current_Pin) {
		this.current_Pin = current_Pin;
	}

	

	
	public String getCreated_By() {
		return created_By;
	}
	public void setCreated_By(String created_By) {
		this.created_By = created_By;
	}
	public String getUpdated_By() {
		return updated_By;
	}
	public void setUpdated_By(String updated_By) {
		this.updated_By = updated_By;
	}
	public Timestamp getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Timestamp created_date) {
		this.created_date = created_date;
	}
	public Timestamp getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(Timestamp updated_date) {
		this.updated_date = updated_date;
	}

	
	
	
	
	
}
