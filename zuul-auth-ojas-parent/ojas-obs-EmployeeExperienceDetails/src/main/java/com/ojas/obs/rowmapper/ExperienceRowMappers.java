package com.ojas.obs.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ojas.obs.model.EmployeeExperienceDetails;

public class ExperienceRowMappers  implements RowMapper<EmployeeExperienceDetails> {
	

	@Override
	public EmployeeExperienceDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmployeeExperienceDetails employeeExperienceDetails = new EmployeeExperienceDetails();
		employeeExperienceDetails.setId(rs.getInt(1));
		employeeExperienceDetails.setCompany_name(rs.getString(2));
		employeeExperienceDetails.setJoining_date(rs.getDate(3));
		employeeExperienceDetails.setExit_date(rs.getDate(4));
		employeeExperienceDetails.setSalary(rs.getDouble(5));
		employeeExperienceDetails.setLocation(rs.getString(6));
		employeeExperienceDetails.setIs_current_company(rs.getBoolean(7));
		employeeExperienceDetails.setEmployee_Id(rs.getString(8));
		employeeExperienceDetails.setFirst_Reference_Name(rs.getString(9));
		employeeExperienceDetails.setFirst_Reference_Contact(rs.getString(10));
		employeeExperienceDetails.setSecond_Reference_Name(rs.getString(11));
		employeeExperienceDetails.setSecond_Reference_Contact(rs.getString(12));
		employeeExperienceDetails.setFlag(rs.getBoolean(13));
		employeeExperienceDetails.setCreated_by(rs.getString(14));
		employeeExperienceDetails.setUpdated_by(rs.getString(15));
		employeeExperienceDetails.setCreated_date(rs.getTimestamp(16));
		employeeExperienceDetails.setUpdated_date(rs.getTimestamp(17));
		
		
		
		return employeeExperienceDetails;
	}

}
