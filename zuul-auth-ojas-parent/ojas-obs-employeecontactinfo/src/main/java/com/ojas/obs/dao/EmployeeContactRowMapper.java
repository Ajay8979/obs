package com.ojas.obs.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ojas.obs.model.EmployeeContactInfo;

public class EmployeeContactRowMapper implements RowMapper<EmployeeContactInfo>{

	@Override
	public EmployeeContactInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmployeeContactInfo emp=new EmployeeContactInfo();
		if(rs==null) { 
		 return null;	
		}else {
		emp.setAlternate_mobileNo(rs.getString("alternate_mobileNo"));
		emp.setCreated_By(rs.getString("created_By")); 
		emp.setCreated_date(rs.getTimestamp("created_date"));
		emp.setCurrent_Address_Line1(rs.getString("current_Address_Line1"));
		emp.setCurrent_Address_Line2(rs.getString("current_Address_Line2"));
		emp.setCurrent_City(rs.getString("current_City")); 
		emp.setCurrent_Pin(rs.getInt("current_Pin"));
		emp.setCurrent_State(rs.getInt("current_State"));
		emp.setEmail(rs.getString("email"));
		emp.setEmp_Id(rs.getString("emp_Id"));
		emp.setFlag(rs.getBoolean("flag"));
		emp.setId(rs.getInt("id"));
		emp.setPermanent_Address_Line_1(rs.getString("permanent_Address_Line_1"));
		emp.setPersonal_mobileNo(rs.getString("personal_mobileNo"));
		emp.setUpdated_By(rs.getString("updated_By"));
		emp.setUpdated_date(rs.getTimestamp("updated_date"));
		
		
		return emp;
		}
	}

}
