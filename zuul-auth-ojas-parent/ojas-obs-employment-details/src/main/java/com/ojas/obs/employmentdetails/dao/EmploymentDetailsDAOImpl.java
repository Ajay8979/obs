package com.ojas.obs.employmentdetails.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ojas.obs.employmentdetails.controller.EmploymentDetailsController;
import com.ojas.obs.employmentdetails.exception.DataNotInsertedException;
import com.ojas.obs.employmentdetails.mapper.EmploymentDetailsRowMapper;
import com.ojas.obs.employmentdetails.model.EmploymentDetails;
import com.ojas.obs.employmentdetails.model.EmploymentDetailsRequest;
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
		Timestamp created_date = new Timestamp(new Date().getTime());
		int savedRows = 0;
		for (EmploymentDetails employmentObject : employmentDetailsList) {

			savedRows = jdbcTemplate.update(queryUtil.getQuery("INSERT_EMPLOYMENT_DETAILS_STMT"),
					employmentObject.getEmployeeId(), employmentObject.getJoiningDate(),
					employmentObject.getResourceType(), employmentObject.isBondStatus(),
					employmentObject.getResignationDate(), employmentObject.getExitDate(),
					employmentObject.getSeparationType(), 1, employmentObject.getCreatedBy(), created_date);
		}
		if (savedRows > 0) {
			LOGGER.debug("Employee details record inserted succefully ");
			return true;
		}
		LOGGER.debug("Employee details record not inserted succefully ");
		return false;
	}

	/**
	 * method updates employment details into employement_table
	 * 
	 * @throws DataNotInsertedException
	 */
	@Override
	public boolean updateEmploymentDetails(List<EmploymentDetails> employmentDetailsList)
			throws DataNotInsertedException {
		boolean flag = true;
		int updatedRows = 0;
		Timestamp updatedDate_date = new Timestamp(new Date().getTime());
		for (EmploymentDetails employmentObject : employmentDetailsList) {
			updatedRows = jdbcTemplate.update(queryUtil.getQuery("UPDATE_EMPLOYMENT_DETAILS_STMT"),
					employmentObject.getResourceType(), employmentObject.isBondStatus(),
					employmentObject.getResignationDate(), employmentObject.getExitDate(),
					employmentObject.getSeparationType(), flag, employmentObject.getUpdatedBy(),updatedDate_date,
					employmentObject.getId());
		}
		if (updatedRows > 0) {
			return true;
		}
		return false;
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
		int deletedRows = 0;
		boolean flag = false;
		for (EmploymentDetails employmentObject : employmentDetailsList) {
			deletedRows = jdbcTemplate.update(queryUtil.getQuery("DELETE_EMPLOYMENT_DETAILS_STMT"),
					flag, employmentObject.getId());
		}
		if (deletedRows > 0) {
			return true;
		}
		return false;
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
	public List<EmploymentDetails> getEmploymentDetailsByEmploymentId(EmploymentDetailsRequest employmentDetailsRequest) {
		boolean flag= true;
		RowMapper<EmploymentDetails> rowMapper = new EmploymentDetailsRowMapper();
		List<EmploymentDetails> employmentDetails = employmentDetailsRequest.getEmploymentDetails();
		List<EmploymentDetails> list = new ArrayList<EmploymentDetails>();
		
		List<EmploymentDetails> employmentDetailsList = jdbcTemplate.query(
				queryUtil.getQuery("GET_EMPLOYMENT_DETAILS_BY_EMPLOYEE_ID_STMT"),new BeanPropertyRowMapper<EmploymentDetails>(EmploymentDetails.class));
		for (EmploymentDetails employmentDetails2 : employmentDetails) {
			for (EmploymentDetails employmentDetail : employmentDetailsList) {
				if(employmentDetails2.getId() == employmentDetail.getId()){
					list.add(employmentDetail);
					return list;
				}
			}
		}
		
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
