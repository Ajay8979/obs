package com.ojas.obs.model;

import java.sql.Timestamp;
import java.util.Date;

public class EmployeeExperienceDetails {
    private Integer id;
	private String employee_Id;
	private String company_name;
	private Date joining_date;
	private Date exit_date;
	private  Double salary;
	private String location;
	private String is_current_company;
	private String first_Reference_Name;
	private String first_Reference_Contact;
	private String second_Reference_Name;
	private String second_Reference_Contact;
	private boolean flag;
	private String created_by;
	private String updated_by;
	private Timestamp created_date;
	private Timestamp updated_date;
	
	public EmployeeExperienceDetails() {}

	public EmployeeExperienceDetails(Integer id, String employee_Id, String company_name, Date joining_date,
			Date exit_date, Double salary, String location, String is_current_company, String first_Reference_Name,
			String first_Reference_Contact, String second_Reference_Name, String second_Reference_Contact, boolean flag,
			String created_by, String updated_by, Timestamp created_date, Timestamp updated_date) {
		super();
		this.id = id;
		this.employee_Id = employee_Id;
		this.company_name = company_name;
		this.joining_date = joining_date;
		this.exit_date = exit_date;
		this.salary = salary;
		this.location = location;
		this.is_current_company = is_current_company;
		this.first_Reference_Name = first_Reference_Name;
		this.first_Reference_Contact = first_Reference_Contact;
		this.second_Reference_Name = second_Reference_Name;
		this.second_Reference_Contact = second_Reference_Contact;
		this.flag = flag;
		this.created_by = created_by;
		this.updated_by = updated_by;
		this.created_date = created_date;
		this.updated_date = updated_date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmployee_Id() {
		return employee_Id;
	}

	public void setEmployee_Id(String employee_Id) {
		this.employee_Id = employee_Id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public Date getJoining_date() {
		return joining_date;
	}

	public void setJoining_date(Date joining_date) {
		this.joining_date = joining_date;
	}

	public Date getExit_date() {
		return exit_date;
	}

	public void setExit_date(Date exit_date) {
		this.exit_date = exit_date;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getIs_current_company() {
		return is_current_company;
	}

	public void setIs_current_company(String is_current_company) {
		this.is_current_company = is_current_company;
	}

	public String getFirst_Reference_Name() {
		return first_Reference_Name;
	}

	public void setFirst_Reference_Name(String first_Reference_Name) {
		this.first_Reference_Name = first_Reference_Name;
	}

	public String getFirst_Reference_Contact() {
		return first_Reference_Contact;
	}

	public void setFirst_Reference_Contact(String first_Reference_Contact) {
		this.first_Reference_Contact = first_Reference_Contact;
	}

	public String getSecond_Reference_Name() {
		return second_Reference_Name;
	}

	public void setSecond_Reference_Name(String second_Reference_Name) {
		this.second_Reference_Name = second_Reference_Name;
	}

	public String getSecond_Reference_Contact() {
		return second_Reference_Contact;
	}

	public void setSecond_Reference_Contact(String second_Reference_Contact) {
		this.second_Reference_Contact = second_Reference_Contact;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
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
	

	
			