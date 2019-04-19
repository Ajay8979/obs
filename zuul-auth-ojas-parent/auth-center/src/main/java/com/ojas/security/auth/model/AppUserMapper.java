package com.ojas.security.auth.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class AppUserMapper implements RowMapper<AppUser> {

	//public static final String BASE_SQL = "Select u.User_Id, u.User_Name, u.Encryted_Password From App_User u ";
	public static final String BASE_SQL = "Select e.employeeId, e.firstname, e.password From ojas_employeeinfo e ";

	@Override
	public AppUser mapRow(ResultSet rs, int arg1) throws SQLException {
		
		String userId = rs.getString("employeeId"); 
		String userName = rs.getString("firstname"); 
		String encrytedPassword = rs.getString("password"); 
		return new AppUser(userId, userName,encrytedPassword);
	}

}
