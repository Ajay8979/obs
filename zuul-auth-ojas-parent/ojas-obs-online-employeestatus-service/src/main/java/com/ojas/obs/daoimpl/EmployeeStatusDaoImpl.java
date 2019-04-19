package com.ojas.obs.daoimpl;

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
	public static final String INSERTEMPLOYEESTATUSSTMT = "INSERT INTO OBS_EMPSTATUS(OBS_EMPSTATUS.STATUS) VALUES (?)";
	public static final String UPDATESTMT = "UPDATE OJAS_OBS.OBS_EMPSTATUS SET OBS_EMPSTATUS.STATUS= ? WHERE OBS_EMPSTATUS.ID = ?";
//	public static final String DELETESTMT = "UPDATE OJAS_OBS.OBS_EMPSTATUS SET OBS_EMPSTATUS.DELETE = '1' WHERE OBS_EMPSTATUS.ID = ? AND OBS_EMPSTATUS.DELETE = '0'";
	public static final String GETTOTALSTMT = "SELECT * FROM OJAS_OBS.OBS_EMPSTATUS";
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public boolean saveEmployeeStatus(EmployeeStatusRequest employeeStatusRequest) {
		boolean b = false;
		int[] save;
		List<EmployeeStatus> employeeStatusList = employeeStatusRequest.getEmployeeStatus();
		List<Object[]> list = new ArrayList<>();
		for (EmployeeStatus employeeStatus : employeeStatusList) {
			 Object[] emp = new Object[] {employeeStatus.getStatus()};
			list.add(emp);
		}
		save = jdbcTemplate.batchUpdate(INSERTEMPLOYEESTATUSSTMT, list);
		
		if (save.length > 0) {
			b = true;
			
		}
		return b;
	}

	@Override
	public boolean updateEmployeeStatus(EmployeeStatusRequest employeeStatusRequest) {
		List<EmployeeStatus> employeeStatusList = employeeStatusRequest.getEmployeeStatus();
		boolean b = false;
		int[] update;
		List<Object[]> list = new ArrayList<>();
		for (EmployeeStatus employeeStatus : employeeStatusList) {
			 Object[] emp = new Object[] {employeeStatus.getStatus(), employeeStatus.getId()};
			list.add(emp);
		}
		update = jdbcTemplate.batchUpdate(UPDATESTMT, list);
		
		if (update.length > 0) {
			b = true;
		} 
		return b;
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
	public List<EmployeeStatus> getAllStatus() {
		return jdbcTemplate.query(GETTOTALSTMT, new BeanPropertyRowMapper<EmployeeStatus>(EmployeeStatus.class));
	}

}
