package com.ojas.security.auth.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ojas.security.auth.dao.UserDao;
import com.ojas.security.auth.model.AppUser;
import com.ojas.security.auth.model.AppUserMapper;

import ch.qos.logback.classic.Logger;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public AppUser findUserAccount(String userName) {

		
		String sql = AppUserMapper.BASE_SQL + " where e.employeeId = ? ";
		Object[] params = new Object[] { userName };
		AppUserMapper mapper = new AppUserMapper();
		try {
		
			AppUser userInfo = jdbcTemplate.queryForObject(sql, params, mapper);
			try {
				jdbcTemplate.getDataSource().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	
	}

 }
