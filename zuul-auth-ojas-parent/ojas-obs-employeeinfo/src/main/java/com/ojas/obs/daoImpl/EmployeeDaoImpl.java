package com.ojas.obs.daoImpl;

import static com.ojas.obs.constants.UserConstants.DELETEEMPINFO;
import static com.ojas.obs.constants.UserConstants.GETEMPBYEMPID;
import static com.ojas.obs.constants.UserConstants.GETEMPBYID;
import static com.ojas.obs.constants.UserConstants.GETEMPDETAILS;
import static com.ojas.obs.constants.UserConstants.SAVEEMPINFO;
import static com.ojas.obs.constants.UserConstants.UPDATEEMPINFO;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ojas.obs.dao.EmployeeInfoDao;
import com.ojas.obs.model.EmployeeInfo;
import com.ojas.obs.request.EmployeeInfoRequest;

@Repository
public class EmployeeDaoImpl implements EmployeeInfoDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	Logger logger = Logger.getLogger(this.getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.EmployeeInfoDao#saveEmployeeInfo(com.ojas.obs.request.
	 * EmployeeInfoRequest)
	 */

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean saveEmployeeInfo(EmployeeInfoRequest employeeInfoRequest) throws SQLException {
		logger.debug("inside save employee in employeeDao");
		List<Object[]> employeeInfoList = new ArrayList<>();

		for (EmployeeInfo employeeInfo : employeeInfoRequest.getEmployeeInfo()) {
			java.sql.Date date = java.sql.Date.valueOf(employeeInfo.getDob());
			Object[] empinfo = { employeeInfo.getFirstname(), employeeInfo.getMiddlename(), employeeInfo.getLastname(),
					employeeInfo.getStatus(), date, employeeInfo.getGender(), employeeInfo.getTitle(),
					employeeInfo.getEmployeeId(), true, new Timestamp(new Date().getTime()),
					employeeInfo.getCreatedBy() };
			employeeInfoList.add(empinfo);
		}
		jdbcTemplate.batchUpdate(SAVEEMPINFO, employeeInfoList);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ojas.obs.dao.EmployeeInfoDao#updateEmployeeInfo(com.ojas.obs.request.
	 * EmployeeInfoRequest)
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean updateEmployeeInfo(EmployeeInfoRequest employeeInfoRequest) throws SQLException {
		logger.debug("inside update employee in employeeDao");
		List<Object[]> inputList = new ArrayList<>();
		for (EmployeeInfo employeeInfo : employeeInfoRequest.getEmployeeInfo()) {
			java.sql.Date date = java.sql.Date.valueOf(employeeInfo.getDob());
			Object[] emp = { employeeInfo.getFirstname(), employeeInfo.getMiddlename(), employeeInfo.getLastname(),
					employeeInfo.getStatus(), date, employeeInfo.getGender(), employeeInfo.getTitle(),
					employeeInfo.getEmployeeId(), new Timestamp(new Date().getTime()), employeeInfo.getUpdatedBy(),
					employeeInfo.getId() };
			inputList.add(emp);
		}
		jdbcTemplate.batchUpdate(UPDATEEMPINFO, inputList);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ojas.obs.dao.EmployeeInfoDao#deleteEmployeeInfo(com.ojas.obs.request.
	 * EmployeeInfoRequest)
	 */
	@Override
	public boolean deleteEmployeeInfo(EmployeeInfoRequest employeeInfoRequest) throws SQLException {
		logger.debug("inside delete employee in employeeDao");
		List<Object[]> inputList = new ArrayList<Object[]>();
		EmployeeInfo employeeInfo = employeeInfoRequest.getEmployeeInfo().get(0);
		Object[] emp = { false, new Timestamp(new Date().getTime()), employeeInfo.getId() };
		inputList.add(emp);
		jdbcTemplate.batchUpdate(DELETEEMPINFO, inputList);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ojas.obs.dao.EmployeeInfoDao#getAllEmployeeDetails(com.ojas.obs.request.
	 * EmployeeInfoRequest)
	 */

//	@Override
//	public List<EmployeeInfo> getAllEmployeeDetails(EmployeeInfoRequest employeeInfoRequest) throws SQLException {
//		List<EmployeeInfo> employeeList = employeeInfoRequest.getEmployeeInfo();
//		logger.debug("**employeeInfoRequest in EmployeeDaoImpl ::" + employeeInfoRequest);
//		StringBuilder builder = new StringBuilder();
//		builder.append(GETEMPDETAILS);
//		
//		
//		
//		for (EmployeeInfo getList : employeeList) {
//			if (getList.getId() != 0) {
//				builder.append("where id = " + getList.getId());
//
//			} else {
//				List<EmployeeInfo> empInfo = jdbcTemplate.query(builder.toString(),
//						new BeanPropertyRowMapper<>(EmployeeInfo.class));
//
//				
//
//				return empInfo;
//			}
//		}
//		return null;
//	}

	@Override
	public List<EmployeeInfo> getById(EmployeeInfoRequest employeeInfoRequest) throws SQLException {

		List<EmployeeInfo> employeeinfo = null;
		EmployeeInfo employee = employeeInfoRequest.getEmployeeInfo().get(0);
		if (employee.getId() != null) {
			logger.debug("inside get by id in employeeDao with id : " + employee.getId());
			employeeinfo = jdbcTemplate.query(GETEMPBYID + employee.getId(),
					new BeanPropertyRowMapper<>(EmployeeInfo.class));
		} else {
			logger.debug("inside get by employeeId in employeeDao with employeeId : " + employee.getEmployeeId());
			employeeinfo = jdbcTemplate.query(GETEMPBYEMPID + employee.getEmployeeId(),
					new BeanPropertyRowMapper<>(EmployeeInfo.class));
		}
		return employeeinfo;
	}

	@Override
	public List<EmployeeInfo> getAllEmployeeDetails(EmployeeInfoRequest employeeInfoRequest) throws SQLException {
		logger.debug("inside get all employee details in employeeDao");
		return jdbcTemplate.query(GETEMPDETAILS, new BeanPropertyRowMapper<>(EmployeeInfo.class));
	}
}
