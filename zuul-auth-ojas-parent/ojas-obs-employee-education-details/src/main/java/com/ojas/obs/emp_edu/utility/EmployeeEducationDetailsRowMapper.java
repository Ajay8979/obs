package com.ojas.obs.emp_edu.utility;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ojas.obs.emp_edu.model.EmployeeEducationDetails;

public class EmployeeEducationDetailsRowMapper implements RowMapper<EmployeeEducationDetails>{

	@Override
	public EmployeeEducationDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
	
		EmployeeEducationDetails employeeEducationDetails =  new EmployeeEducationDetails();
		employeeEducationDetails.setId(rs.getInt(1));
		employeeEducationDetails.setEmployeeId(rs.getString(2));
		employeeEducationDetails.setQualification(rs.getInt(3));
		employeeEducationDetails.setYear_of_passing(rs.getString(4));
		employeeEducationDetails.setPercentage_marks(rs.getString(5));
		employeeEducationDetails.setInstitution_name(rs.getString(6));
		employeeEducationDetails.setCreatedBy(rs.getString(8));
		employeeEducationDetails.setUpdatedBy(rs.getString(9));
		employeeEducationDetails.setCreatedDate(rs.getTimestamp(10));
		employeeEducationDetails.setUpdatedDate(rs.getTimestamp(11));		
		return employeeEducationDetails;
	}


}
