package com.ojas.security.auth.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ojas.security.auth.dao.RoleDao;

@Repository
public class RoleDaoImpl implements RoleDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<String> getRoleNames(String employeeId)//(Long userId)
	{List<String> roles=null;
		try {
		String sql = "Select r.role_name from user_role ur, app_role r where ur.role_id = r.role_id and ur.employeeId = ? ";
		Object[] params = new Object[] { employeeId };
		roles = jdbcTemplate.queryForList(sql, params, String.class);
		
			jdbcTemplate.getDataSource().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roles;
	}
}
