package com.ojas.obs.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ojas.obs.dao.EmployeeStatusDao;
import com.ojas.obs.model.EmployeeStatus;
import com.ojas.obs.request.EmployeeStatusRequest;

/**
 * 
 * @author Manohar
 *
 */
@Repository
public class EmployeeStatusDaoImpl implements EmployeeStatusDao {
	public static final String INSERTEMPLOYEESTATUSSTMT = "insert into ojas_obs.obs_employeestatus(obs_employeestatus.status) values (?)";
	public static final String UPDATESTMT = "update ojas_obs.obs_employeestatus set obs_employeestatus.status= ? where obs_employeestatus.id = ?";
//	public static final String DELETESTMT = "update ojas_obs.obs_employeestatus set obs_employeestatus.delete = '1' where obs_employeestatus.id = ? and obs_employeestatus.delete = '0'";
	public static final String GETTOTALSTMT = "select * FROM ojas_obs.obs_employeestatus";
	public static final String GETBYIDSTMT = "select * FROM ojas_obs.obs_employeestatus where obs_employeestatus.id = ?";
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean saveEmployeeStatus(EmployeeStatusRequest employeeStatusRequest) throws SQLException {
		boolean b = false;
		int[] save;
		List<EmployeeStatus> employeeStatusList = employeeStatusRequest.getEmployeeStatus();
		List<Object[]> list = new ArrayList<>();
		for (EmployeeStatus employeeStatus : employeeStatusList) {
			Object[] emp = new Object[] { employeeStatus.getStatus() };
			list.add(emp);
		}
		try {
			save = jdbcTemplate.batchUpdate(INSERTEMPLOYEESTATUSSTMT, list);
			if (save.length > 0) {
				b = true;

			}
			return b;
		} finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}
	}

	@Override
	public boolean updateEmployeeStatus(EmployeeStatusRequest employeeStatusRequest) throws SQLException {
		List<EmployeeStatus> employeeStatusList = employeeStatusRequest.getEmployeeStatus();
		boolean b = false;
		int[] update;
		List<Object[]> list = new ArrayList<>();
		for (EmployeeStatus employeeStatus : employeeStatusList) {
			Object[] emp = new Object[] { employeeStatus.getStatus(), employeeStatus.getId() };
			list.add(emp);
		}
		try {
			update = jdbcTemplate.batchUpdate(UPDATESTMT, list);

			if (update.length > 0) {
				b = true;
			}
			return b;
		} finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}
	}

	/*
	 * @Override public boolean deleteEmployeeStatus(EmployeeStatusRequest
	 * employeeStatusRequest) { boolean b = false; int[] delete = null;
	 * List<EmployeeStatus> employeeStatusList =
	 * employeeStatusRequest.getEmployeeStatus(); List<Object[]> list = new
	 * ArrayList<>(); for (EmployeeStatus employeeStatus : employeeStatusList) {
	 * Object[] emp = new Object[] {employeeStatus.getId()}; list.add(emp); } delete
	 * = jdbcTemplate.batchUpdate(DELETESTMT, list); int NoOfRecords =
	 * delete.length; int count = 0; if (delete.length > 0) { for(int i = 0; i <
	 * delete.length ; i++) { if (delete[i] == 1) count++; } if (NoOfRecords ==
	 * count) b = true; } return b; }
	 */

	@Override
	public List<EmployeeStatus> getAllStatus() throws SQLException {
		try {
		return jdbcTemplate.query(GETTOTALSTMT, new BeanPropertyRowMapper<EmployeeStatus>(EmployeeStatus.class));
		} finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}
	}

	@Override
	public List<EmployeeStatus> getById(Integer id) throws SQLException {
		Object[] param = { id };
		try {
		return jdbcTemplate.query(GETBYIDSTMT, param, new BeanPropertyRowMapper<EmployeeStatus>(EmployeeStatus.class));
		} finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}
	}
}
