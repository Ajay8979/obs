package com.ojas.obs.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ojas.obs.dao.EmployeeEducationDao;
import com.ojas.obs.model.EmployeeEducation;
import com.ojas.obs.modelrequest.EmployeeEducationRequest;

import static com.ojas.obs.constants.UserConstants.INSERTEMPLOYEEEDUCATIONINFOSTMT;
import static com.ojas.obs.constants.UserConstants.UPDATESTMT;
import static com.ojas.obs.constants.UserConstants.DELETEEDUCATION;
import static com.ojas.obs.constants.UserConstants.COUNTRECORDS;
import static com.ojas.obs.constants.UserConstants.TOTALRECORDS;

/**
 * 
 * @author mpraneethguptha
 *
 */
@Repository
public class EmployeeEducationDaoImpl implements EmployeeEducationDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ojas.obs.dao.EmployeeEducationDao#saveEmployeeEducation(com.ojas.obs.
	 * modelrequest.EmployeeEducationRequest)
	 */

	@Override
	public boolean saveEmployeeEducation(EmployeeEducationRequest employeeEducationRequest) throws SQLException {
		List<EmployeeEducation> employeeEducations = employeeEducationRequest.getListEmployeeEducations();
		int[] batchSave = null;
		List<Object[]> input = new ArrayList<Object[]>();
		for (EmployeeEducation employeeEducation : employeeEducations) {
			Object[] saveList = { employeeEducation.getEducationType() };
			input.add(saveList);
		}
		try {
			batchSave = jdbcTemplate.batchUpdate(INSERTEMPLOYEEEDUCATIONINFOSTMT, input);

			if (batchSave.length > 0)
				return true;
		} finally {
			if (jdbcTemplate != null) {
				jdbcTemplate.getDataSource().getConnection().close();
			}
		}

		return false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ojas.obs.dao.EmployeeEducationDao#updateEmployeeEducation(com.ojas.obs.
	 * modelrequest.EmployeeEducationRequest)
	 */
	@Override
	public boolean updateEmployeeEducation(EmployeeEducationRequest employeeEducationRequest) throws SQLException {
		List<EmployeeEducation> employeeEducations = employeeEducationRequest.getListEmployeeEducations();
		int[] batchSave = null;
		List<Object[]> input = new ArrayList<Object[]>();
		for (EmployeeEducation employeeEducation : employeeEducations) {
			Object[] saveList = { employeeEducation.getEducationType(), employeeEducation.getId() };
			input.add(saveList);
		}
		batchSave = jdbcTemplate.batchUpdate(UPDATESTMT, input);
		try {
			if (batchSave.length > 0)
				return true;
		} finally {
			if (jdbcTemplate != null) {
				jdbcTemplate.getDataSource().getConnection().close();
			}
		}
		return false;

	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.EmployeeEducationDao#deleteEmployeeEducation(int)
	 */

	@Override
	public boolean deleteEmployeeEducation(int id) throws SQLException {
		try {
			int delete = jdbcTemplate.update(DELETEEDUCATION, id);

			if (delete > 0)
				return true;
		} finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}
		return false;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ojas.obs.dao.EmployeeEducationDao#getAllEmployeeEducation(com.ojas.obs.
	 * modelrequest.EmployeeEducationRequest)
	 */

	@Override
	public List<EmployeeEducation> getAllEmployeeEducation(EmployeeEducationRequest employeeEducationRequest)
			throws SQLException {
		try {
			return jdbcTemplate.query(TOTALRECORDS,
					new BeanPropertyRowMapper<EmployeeEducation>(EmployeeEducation.class));
		} finally {

			jdbcTemplate.getDataSource().getConnection().close();
		}

	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.EmployeeEducationDao#getAllRecordsCount()
	 */

	@Override
	public int getAllRecordsCount() throws SQLException {
		try {
			return jdbcTemplate.queryForObject(COUNTRECORDS, Integer.class);
		} finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}
	}

}
