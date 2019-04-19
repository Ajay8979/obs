package com.ojas.obs.employmentdetails.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ojas.obs.employmentdetails.controller.EmploymentDetailsController;
import com.ojas.obs.employmentdetails.exception.DataNotInsertedException;
import com.ojas.obs.employmentdetails.mapper.EmploymentDetailsRowMapper;
import com.ojas.obs.employmentdetails.model.EmploymentDetails;
import com.ojas.obs.employmentdetails.util.QueryUtil;

/**
 * dao class inserts,updates,deletes and retrieves data into/from
 * employment_details table
 * 
 * @author vjithin
 *
 */
@Repository
public class EmploymentDetailsDAOImpl implements EmploymentDetailsDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmploymentDetailsController.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private QueryUtil queryUtil;

	/**
	 * method inserts employment details into employement_table
	 * 
	 * @throws DataNotInsertedException
	 */
	@Override
	@Transactional(rollbackFor = DataNotInsertedException.class)
	public boolean saveEmploymentDetails(List<EmploymentDetails> employmentDetailsList)
			throws DataNotInsertedException {

		for (EmploymentDetails employmentDetails : employmentDetailsList) {

			if (null != employmentDetails) {
				try {
					Object[] inputArgs = { employmentDetails.getEmployeeId(), employmentDetails.getJoiningDate(),
							employmentDetails.getResourceType(), employmentDetails.getBondStatus(),
							employmentDetails.getFlag(), employmentDetails.getCreatedBy() }; //
					jdbcTemplate.update(queryUtil.getQuery("INSERT_EMPLOYMENT_DETAILS_STMT"), inputArgs);
				} catch (DataAccessException e) {
					LOGGER.debug("All requested Employee  records not get inserted ");
					throw new DataNotInsertedException(
							"failed to insert employee details with employee id " + employmentDetails.getEmployeeId());
				}
			}
		}

		LOGGER.debug("Employee details record inserted succefully ");
		return true;

	}

	/**
	 * method updates employment details into employement_table
	 * 
	 * @throws DataNotInsertedException
	 */
	@Override
	public boolean updateEmploymentDetails(List<EmploymentDetails> employmentDetailsList)
			throws DataNotInsertedException {

		for (EmploymentDetails employmentDetails : employmentDetailsList) {

			if (null != employmentDetails && isEmployeeExits(employmentDetails.getId())) {
				try {
					Object[] inputArgs = { employmentDetails.getResourceType(), employmentDetails.getBondStatus(),
							employmentDetails.getResignationDate(), employmentDetails.getExitDate(),
							employmentDetails.getSeparationType(), employmentDetails.getFlag(),
							employmentDetails.getUpdatedBy(), employmentDetails.getId() }; //
					jdbcTemplate.update(queryUtil.getQuery("UPDATE_EMPLOYMENT_DETAILS_STMT"), inputArgs);
				} catch (DataAccessException e) {
					LOGGER.debug("All requested Employee  records not get updated ");
					throw new DataNotInsertedException(
							"failed to update record with employee id " + employmentDetails.getEmployeeId());
				}
			} else {
				LOGGER.debug("All requested Employee  records not get updated ");
				throw new DataNotInsertedException(
						"Employee details not found for employee with  id " + employmentDetails.getId());
			}
		}

		LOGGER.debug("Employee  records not updated succefully ");
		return true;
	}

	private boolean isEmployeeExits(Integer id) {
		int count = jdbcTemplate.queryForObject("select count(*) from employment_details where id=?", Integer.class,
				id);
		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * method deletes employment details from employement_table
	 * 
	 * @throws DataNotInsertedException
	 */
	@Override
	public boolean deleteEmploymentDetails(List<EmploymentDetails> employmentDetailsList)
			throws DataNotInsertedException {

		for (EmploymentDetails employmentDetails : employmentDetailsList) {

			if (null != employmentDetails && isEmployeeExits(employmentDetails.getId())) {
				try {
					Object[] inputArgs = { 0, employmentDetails.getUpdatedBy(), employmentDetails.getId() }; //
					jdbcTemplate.update(queryUtil.getQuery("DELETE_EMPLOYMENT_DETAILS_STMT"), inputArgs);
				} catch (DataAccessException e) {
					LOGGER.debug("Employee  records are not  deleted as employee record with id "
							+ employmentDetails.getId() + " is not found");
					throw new DataNotInsertedException(
							"failed to update record with employee id " + employmentDetails.getEmployeeId());
				}
			} else {
				LOGGER.debug("All requested Employee  records not get deleted ");
				throw new DataNotInsertedException("Employee details not found for employee with employee id "
						+ employmentDetails.getEmployeeId());
			}
		}

		LOGGER.debug("All requested Employee  records  got deleted ");
		return true;
	}

	/**
	 * method retrieves employment details from employement_table
	 */
	@Override
	public List<EmploymentDetails> getAllEmploymentDetails() {

		RowMapper<EmploymentDetails> rowMapper = new EmploymentDetailsRowMapper();
		List<EmploymentDetails> employmentDetailsList = jdbcTemplate
				.query(queryUtil.getQuery("GET_EMPLOYMENT_DETAILS_STMT"), rowMapper);
		LOGGER.debug("Employee  details list " + employmentDetailsList);
		return employmentDetailsList;
	}

	@Override
	public List<EmploymentDetails> getEmploymentDetailsByEmploymentId(String employeeId) {

		RowMapper<EmploymentDetails> rowMapper = new EmploymentDetailsRowMapper();
		List<EmploymentDetails> employmentDetailsList = jdbcTemplate.query(
				queryUtil.getQuery("GET_EMPLOYMENT_DETAILS_BY_EMPLOYEE_ID_STMT"), new Object[] { employeeId },
				rowMapper);
		LOGGER.debug("Employee  details list " + employmentDetailsList);
		return employmentDetailsList;

	}
	
	@Override
	public List<EmploymentDetails> getEmploymentDetailsById(Integer id) {

		RowMapper<EmploymentDetails> rowMapper = new EmploymentDetailsRowMapper();
		List<EmploymentDetails> employmentDetailsList = jdbcTemplate.query(
				queryUtil.getQuery("GET_EMPLOYMENT_DETAILS_BY_ID_STMT"), new Object[] { id },
				rowMapper);
		LOGGER.debug("Employee  details list " + employmentDetailsList);
		return employmentDetailsList;

	}

}
