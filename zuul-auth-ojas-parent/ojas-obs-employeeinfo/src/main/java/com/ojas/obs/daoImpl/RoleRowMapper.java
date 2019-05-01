package com.ojas.obs.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ojas.obs.response.RoleResponse;



public class RoleRowMapper implements RowMapper<RoleResponse> {

	@Override
	public RoleResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
		RoleResponse emp=new RoleResponse();
		if(rs==null) { 
		 return null;	
		}else {
		emp.setEmployeeId(rs.getString("employeeId"));
		emp.setRoleId(rs.getInt("role_id")); 
		emp.setRoleName(rs.getString("role_name"));
		emp.setFirstName(rs.getString("firstname"));
		emp.setLastName(rs.getString("lastname"));
		emp.setMiddleName(rs.getString("middlename"));
	
		return emp;
		}
	}
	
}
