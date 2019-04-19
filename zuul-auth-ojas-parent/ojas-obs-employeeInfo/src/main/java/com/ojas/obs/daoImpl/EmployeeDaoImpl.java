package com.ojas.obs.daoImpl;

import static com.ojas.obs.constants.UserConstants.DELETEEMPINFO;
import static com.ojas.obs.constants.UserConstants.GETEMPCOUNT;
import static com.ojas.obs.constants.UserConstants.GETEMPDETAILS;
import static com.ojas.obs.constants.UserConstants.SAVEEMPINFO;
import static com.ojas.obs.constants.UserConstants.UPDATEEMPINFO;
import static com.ojas.obs.constants.UserConstants.SAVED;
import static com.ojas.obs.constants.UserConstants.UPDATED;
import static com.ojas.obs.constants.UserConstants.DELETED;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ojas.obs.dao.EmployeeInfoDao;
import com.ojas.obs.model.EmployeeInfo;
import com.ojas.obs.request.EmployeeInfoRequest;

@Repository
public class EmployeeDaoImpl implements EmployeeInfoDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.EmployeeInfoDao#saveEmployeeInfo(com.ojas.obs.request.
	 * EmployeeInfoRequest)
	 */

	@Override
	public boolean saveEmployeeInfo(EmployeeInfoRequest employeeInfoRequest) {

		java.sql.Date date = java.sql.Date.valueOf(employeeInfoRequest.getEmployeeInfo().getDob());

		int saveEmployee = jdbcTemplate.update(SAVEEMPINFO,

				employeeInfoRequest.getEmployeeInfo().getFirstname(),
				employeeInfoRequest.getEmployeeInfo().getMiddlename(),
				employeeInfoRequest.getEmployeeInfo().getLastname(), employeeInfoRequest.getEmployeeInfo().getStatus(),
				date, employeeInfoRequest.getEmployeeInfo().getGender(),
				employeeInfoRequest.getEmployeeInfo().getPassword(),
				employeeInfoRequest.getEmployeeInfo().getEmployee_id(), SAVED, new Timestamp(new Date().getTime()),
				employeeInfoRequest.getEmployeeInfo().getCreated_by());

		if (saveEmployee > 0) {
			return true;
		}

		return false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ojas.obs.dao.EmployeeInfoDao#updateEmployeeInfo(com.ojas.obs.request.
	 * EmployeeInfoRequest)
	 */
	@Override
	public boolean updateEmployeeInfo(EmployeeInfoRequest employeeInfoRequest) throws SQLException {
		java.sql.Date date = java.sql.Date.valueOf(employeeInfoRequest.getEmployeeInfo().getDob());
		int updateEmployee = jdbcTemplate.update(UPDATEEMPINFO, employeeInfoRequest.getEmployeeInfo().getFirstname(),
				employeeInfoRequest.getEmployeeInfo().getMiddlename(),
				employeeInfoRequest.getEmployeeInfo().getLastname(), employeeInfoRequest.getEmployeeInfo().getStatus(),
				date, employeeInfoRequest.getEmployeeInfo().getGender(),
				employeeInfoRequest.getEmployeeInfo().getPassword(),
				employeeInfoRequest.getEmployeeInfo().getEmployee_id(), UPDATED, new Timestamp(new Date().getTime()),
				employeeInfoRequest.getEmployeeInfo().getUpdated_by(), employeeInfoRequest.getEmployeeInfo().getId());

		if (updateEmployee > 0) {
			return true;
		}

		return false;

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

		int delete = jdbcTemplate.update(DELETEEMPINFO, DELETED, new Timestamp(new Date().getTime()),
				employeeInfoRequest.getEmployeeInfo().getId());
		if (delete > 0) {
			return true;
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ojas.obs.dao.EmployeeInfoDao#getAllEmployeeDetails(com.ojas.obs.request.
	 * EmployeeInfoRequest)
	 */
	@Override
	public List<EmployeeInfo> getAllEmployeeDetails(EmployeeInfoRequest employeeInfoRequest) throws SQLException {

		List<EmployeeInfo> empInfo = jdbcTemplate.query(GETEMPDETAILS, new BeanPropertyRowMapper<>(EmployeeInfo.class));
		return empInfo;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.EmployeeInfoDao#getPageRecords(java.util.List, int,
	 * int)
	 */
	@Override
	public List<EmployeeInfo> getPageRecords(List<EmployeeInfo> allemployeeDetails, int pageSize, int pageNum) {

		List<EmployeeInfo> empInfo = new ArrayList<>();

		if (allemployeeDetails != null && !allemployeeDetails.isEmpty()) {

			pageSize = pageSize > 0 ? pageSize : pageSize * -1;

			pageNum = pageNum > 0 ? pageNum : pageNum == 0 ? 1 : pageNum * -1;

			if (pageSize != 0) {

				int endIndex = pageNum * pageSize;

				int startIndex = endIndex - pageSize;

				endIndex = endIndex < allemployeeDetails.size() ? endIndex : allemployeeDetails.size();

				startIndex = startIndex < allemployeeDetails.size() ? startIndex : 0;

				empInfo = allemployeeDetails.subList(startIndex, endIndex);
			}
		}
		return empInfo;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.EmployeeInfoDao#getAllEmployeeDetailsCount()
	 */

	@Override
	public int getAllEmployeeDetailsCount() throws SQLException {

		int count = jdbcTemplate.queryForObject(GETEMPCOUNT, Integer.class);

		return count;

	}

	

}
