package com.ojas.obs.model;

import java.sql.Date;
import java.sql.Timestamp;

public class DependentDetails {

		private int id;
		private String dependent_Name;
		private String relation;
		private Date date_Of_Birth;
		private String employee_Id;
		private String created_By;
		private Timestamp created_Date;
		private String updated_By;
		private Timestamp updated_Date;
		private boolean flag;
		
		public DependentDetails() {}

		public DependentDetails(int id, String dependent_Name, String relation, Date date_Of_Birth, String employee_Id,
				String created_By, Timestamp created_Date, String updated_By, Timestamp updated_Date, boolean flag) {
			super();
			this.id = id;
			this.dependent_Name = dependent_Name;
			this.relation = relation;
			this.date_Of_Birth = date_Of_Birth;
			this.employee_Id = employee_Id;
			this.created_By = created_By;
			this.created_Date = created_Date;
			this.updated_By = updated_By;
			this.updated_Date = updated_Date;
			this.flag = flag;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getDependent_Name() {
			return dependent_Name;
		}

		public void setDependent_Name(String dependent_Name) {
			this.dependent_Name = dependent_Name;
		}

		public String getRelation() {
			return relation;
		}

		public void setRelation(String relation) {
			this.relation = relation;
		}

		public Date getDate_Of_Birth() {
			return date_Of_Birth;
		}

		public void setDate_Of_Birth(Date date_Of_Birth) {
			this.date_Of_Birth = date_Of_Birth;
		}

		public String getEmployee_Id() {
			return employee_Id;
		}

		public void setEmployee_Id(String employee_Id) {
			this.employee_Id = employee_Id;
		}

		public String getCreated_By() {
			return created_By;
		}

		public void setCreated_By(String created_By) {
			this.created_By = created_By;
		}

		public Timestamp getCreated_Date() {
			return created_Date;
		}

		public void setCreated_Date(Timestamp created_Date) {
			this.created_Date = created_Date;
		}

		public String getUpdated_By() {
			return updated_By;
		}

		public void setUpdated_By(String updated_By) {
			this.updated_By = updated_By;
		}

		public Timestamp getUpdated_Date() {
			return updated_Date;
		}

		public void setUpdated_Date(Timestamp updated_Date) {
			this.updated_Date = updated_Date;
		}

		public boolean isFlag() {
			return flag;
		}

		public void setFlag(boolean flag) {
			this.flag = flag;
		}

	

		

}
