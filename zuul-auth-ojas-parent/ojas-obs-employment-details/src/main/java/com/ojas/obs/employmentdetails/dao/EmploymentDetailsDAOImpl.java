package com.ojas.obs.employmentdetails.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
		try {
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
		} finally {
			if (null != jdbcTemplate) {
				try {
					jdbcTemplate.getDataSource().getConnection().close();
				} catch (Exception exception) {
					exception.getMessage();
				}
			}
		}
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
		try {
			for (EmploymentDetails employmentObject : employmentDetailsList) {
				updatedRows = jdbcTemplate.update(queryUtil.getQuery("UPDATE_EMPLOYMENT_DETAILS_STMT"),
						employmentObject.getResourceType(), employmentObject.isBondStatus(),
						employmentObject.getResignationDate(), employmentObject.getExitDate(),
						employmentObject.getSeparationType(), flag, employmentObject.getUpdatedBy(), updatedDate_date,
						employmentObject.getId());
			}
			if (updatedRows > 0) {
				return true;
			}
			return false;
		} finally {
			if (null != jdbcTemplate) {
				try {
					jdbcTemplate.getDataSource().getConnection().close();
				} catch (Exception exception) {
					exception.getMessage();
				}
			}
		}
	}

	private boolean isEmployeeExits(Integer id) {
		try {
			int count = jdbcTemplate.queryForObject("select count(*) from employment_details where id=?", Integer.class,
					id);
			if (count == 0) {
				return false;
			} else {
				return true;
			}
		} finally {
			if (null != jdbcTemplate) {
				try {
					jdbcTemplate.getDataSource().getConnection().close();
				} catch (Exception exception) {
					exception.getMessage();
				}
			}
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
		try {
			for (EmploymentDetails employmentObject : employmentDetailsList) {
				deletedRows = jdbcTemplate.update(queryUtil.getQuery("DELETE_EMPLOYMENT_DETAILS_STMT"), flag,
						employmentObject.getId());
			}
			if (deletedRows > 0) {
				return true;
			}
			return false;
		} finally {
			if (null != jdbcTemplate) {
				try {
					jdbcTemplate.getDataSource().getConnection().close();
				} catch (Exception exception) {
					exception.getMessage();
				}
			}
		}
	}

	/**
	 * method retrieves employment details from employement_table
	 */
	@Override
	public List<EmploymentDetails> getAllEmploymentDetails() {

		RowMapper<EmploymentDetails> rowMapper = new EmploymentDetailsRowMapper();
		try {
			List<EmploymentDetails> employmentDetailsList = jdbcTemplate
					.query(queryUtil.getQuery("GET_EMPLOYMENT_DETAILS_STMT"), rowMapper);
			LOGGER.debug("Employee  details list " + employmentDetailsList);
			return employmentDetailsList;
		} finally {
			if (null != jdbcTemplate) {
				try {
					jdbcTemplate.getDataSource().getConnection().close();
				} catch (Exception exception) {
					exception.getMessage();
				}
			}
		}
	}


	@Override
	public List<EmploymentDetails> getEmploymentDetailsByEmploymentId(
			EmploymentDetailsRequest employmentDetailsRequest) {
//		List<EmploymentDetails> employmentDetails = employmentDetailsRequest.getEmploymentDetails();
//		List<EmploymentDetails> list = new ArrayList<EmploymentDetails>();
//		try {
//			List<EmploymentDetails> employmentDetailsList = jdbcTemplate.query(
//					queryUtil.getQuery("GET_EMPLOYMENT_DETAILS_BY_EMPLOYEE_ID_STMT"),
//					new BeanPropertyRowMapper<EmploymentDetails>(EmploymentDetails.class));
//			for (EmploymentDetails employmentDetails2 : employmentDetails) {
//				for (EmploymentDetails employmentDetail : employmentDetailsList) {
//					if (employmentDetail.getId() == employmentDetails2.getId()) {
//						list.add(employmentDetail);
//						return list;
//					}
//					if (employmentDetails2.getEmployeeId().equals(employmentDetail.getEmployeeId())) {
//						list.add(employmentDetail);
//						return list;
//					}
//				}
//			}
//
//			LOGGER.debug("Employee  details list " + employmentDetailsList);
//			return employmentDetailsList;
		
		
		List<EmploymentDetails> bankDetails = employmentDetailsRequest.getEmploymentDetails();

		List<EmploymentDetails> list = new ArrayList<EmploymentDetails>();
		try {
			List<EmploymentDetails> query = jdbcTemplate.query(
				queryUtil.getQuery("GET_EMPLOYMENT_DETAILS_BY_EMPLOYEE_ID_STMT"),
					new BeanPropertyRowMapper<EmploymentDetails>(EmploymentDetails.class));
			for (EmploymentDetails bankDetails2 : query) {
				for (EmploymentDetails bankDetails3 : bankDetails) {
					if (bankDetails2.getId() == bankDetails3.getId()) {
						list.add(bankDetails2);
						return list;
					}else if (bankDetails2.getEmployeeId().equals(bankDetails3.getEmployeeId())) {
						list.add(bankDetails2);
						return list;
					}
				}
			}

			return query;
		} finally {
			if (null != jdbcTemplate) {
				try {
					jdbcTemplate.getDataSource().getConnection().close();
				} catch (Exception exception) {
					exception.getMessage();
				}
			}
		}
	}

	@Override
	public List<EmploymentDetails> getEmploymentDetailsById(Integer id) {

		RowMapper<EmploymentDetails> rowMapper = new EmploymentDetailsRowMapper();
		try{
		List<EmploymentDetails> employmentDetailsList = jdbcTemplate
				.query(queryUtil.getQuery("GET_EMPLOYMENT_DETAILS_BY_ID_STMT"), new Object[] { id }, rowMapper);
		LOGGER.debug("Employee  details list " + employmentDetailsList);
		return employmentDetailsList;
		} finally {
			if (null != jdbcTemplate) {
				try {
					jdbcTemplate.getDataSource().getConnection().close();
				} catch (Exception exception) {
					exception.getMessage();
				}
			}
		}
	}

}
