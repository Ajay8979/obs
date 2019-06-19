package com.ojas.obs.daoimpl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ojas.obs.dao.EmployeeContactRowMapper;
import com.ojas.obs.dao.EmployeeStatusDao;
import com.ojas.obs.model.EmployeeContactInfo;
import com.ojas.obs.requests.EmployeeContactInfoRequest;

/**
 * 
 * @author ksaiKrishna
 *
 */

@Repository
public class EmployeeStatusDaoImpl implements EmployeeStatusDao {

	Logger logger = Logger.getLogger(this.getClass());

	public static final String INSERTEMPLOYEECONTACTINFOSTMT = "INSERT INTO obs_employeecontactinfo(email,personal_mobileNo,alternate_mobileNo,current_Address_line1,current_Address_line2, current_City,current_State,current_Pin,permanent_Address_Line_1,emp_Id,created_By,created_date,flag) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String SHOWCONTACTINFOSTMT = "SELECT * FROM obs_employeecontactinfo WHERE id=? and flag = 1";
	public static final String SHOWCONTACTINFOSTMTEMPID = "SELECT * FROM obs_employeecontactinfo WHERE emp_Id=? and flag = 1";
	public static final String UPDATESTMT = "UPDATE obs_employeecontactinfo set email = ? ,personal_mobileNo = ?,alternate_mobileNo = ?,current_Address_line1 = ?,current_Address_line2 = ?, current_City = ?,current_State = ?,current_Pin = ?,permanent_Address_Line_1 = ?,updated_By = ?,updated_date = ? WHERE id = ? ";
	public static final String DELETECONTACT = "UPDATE obs_employeecontactinfo set flag = ?,updated_date = ?,updated_By = ? where id = ?";
	public static final String TOTALRECORDS = "select * from obs_employeecontactinfo where flag=1";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.EmployeeStatusDao#saveEmployeeContactInfo(com.ojas.obs.
	 * requests.EmployeeContactInfoRequest)
	 */

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean saveEmployeeContactInfo(EmployeeContactInfoRequest empRequest) throws SQLException {

		int[] save;
		logger.debug("@Saving employee contact info dao method");
		Timestamp sqlDate = new Timestamp(new java.util.Date().getTime());

		List<Object[]> inputList = new ArrayList<Object[]>();
		for (EmployeeContactInfo emp : empRequest.getEmpInfo()) {
			Object[] tmp = { emp.getEmail(), emp.getPersonalMobileNo(), emp.getAlternateMobileNo(),
					emp.getCurrentAddressLine1(), emp.getCurrentAddressLine2(), emp.getCurrentCity(),
					emp.getCurrentState(), emp.getCurrentPin(), emp.getPermanentAddressLine1(), emp.getEmpId(),
					emp.getCreatedBy(), sqlDate, true };
			inputList.add(tmp);

		}
		save = jdbcTemplate.batchUpdate(INSERTEMPLOYEECONTACTINFOSTMT, inputList);
		if (save.length > 0) {
			return true;
		} else {
			return false;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ojas.obs.dao.EmployeeStatusDao#updateEmployeeContactInfo(com.ojas.obs.
	 * requests.EmployeeContactInfoRequest)
	 */

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean updateEmployeeContactInfo(EmployeeContactInfoRequest empRequest) {

		int[] update;
		logger.debug("@updating employee contact info" + empRequest);
		Timestamp sqlUpdateDate = new Timestamp(new java.util.Date().getTime());
		List<Object[]> inputList = new ArrayList<Object[]>();
		for (EmployeeContactInfo emp : empRequest.getEmpInfo()) {
			Object[] tmp = { emp.getEmail(), emp.getPersonalMobileNo(), emp.getAlternateMobileNo(),
					emp.getCurrentAddressLine1(), emp.getCurrentAddressLine2(), emp.getCurrentCity(),
					emp.getCurrentState(), emp.getCurrentPin(), emp.getPermanentAddressLine1(), emp.getUpdatedBy(),
					sqlUpdateDate, emp.getId() };
			inputList.add(tmp);

		}
		update = jdbcTemplate.batchUpdate(UPDATESTMT, inputList);

		if (update.length > 0) {
			return true;
		} else {
			return false;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ojas.obs.dao.EmployeeStatusDao#deleteEmployeeContactInfo(com.ojas.obs.
	 * requests.EmployeeContactInfoRequest)
	 */

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean deleteEmployeeContactInfo(EmployeeContactInfoRequest employeeContactInfoRequest) {

		logger.debug("@Deleting employee contact info" + employeeContactInfoRequest);
		Timestamp sqlDeleteDate = new Timestamp(new java.util.Date().getTime());
		int[] delete;
		List<Object[]> inputList = new ArrayList<Object[]>();
		for (EmployeeContactInfo emp : employeeContactInfoRequest.getEmpInfo()) {
			Object[] tmp = { false, sqlDeleteDate, emp.getUpdatedBy(), emp.getId() };
			inputList.add(tmp);
		}
		delete = jdbcTemplate.batchUpdate(DELETECONTACT, inputList);
		if (delete.length > 0) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.EmployeeStatusDao#showEmployeeContactInfo(com.ojas.obs.
	 * requests.EmployeeContactInfoRequest)
	 */

	@Override
	public List<EmployeeContactInfo> showEmployeeContactInfoById(EmployeeContactInfoRequest empRequest) {
		List<EmployeeContactInfo> list = null;
		logger.debug("@getting employee contact info based on his id" + empRequest);
		list = jdbcTemplate.query(SHOWCONTACTINFOSTMT, new Object[] { empRequest.getEmpInfo().get(0).getId() },
				new EmployeeContactRowMapper());
		return list;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.EmployeeStatusDao#getAll(com.ojas.obs.requests.
	 * EmployeeContactInfoRequest)
	 */
	@Override
	public List<EmployeeContactInfo> getAll(EmployeeContactInfoRequest employeeContactInfo) {

		logger.debug("@Getting all employees contact info details");
		List<EmployeeContactInfo> list = null;
		list = jdbcTemplate.query(TOTALRECORDS, new EmployeeContactRowMapper());

		return list;
	}

	@Override
	public List<EmployeeContactInfo> showEmployeeContactInfoByEmpId(EmployeeContactInfoRequest empRequest) {
		List<EmployeeContactInfo> list = null;
		logger.debug("@getting employee contact info based on his empid");
		list = jdbcTemplate.query(SHOWCONTACTINFOSTMTEMPID, new Object[] { empRequest.getEmpInfo().get(0).getEmpId() },
				new EmployeeContactRowMapper());
		return list;

	}

}
