package com.ojas.obs.emp_edu.daoimpl;

import static com.ojas.obs.emp_edu.utility.Constants.DELETE_STMT;
import static com.ojas.obs.emp_edu.utility.Constants.GETALL_STMT;
import static com.ojas.obs.emp_edu.utility.Constants.INSERT_STMT;
import static com.ojas.obs.emp_edu.utility.Constants.UPDATE_STMT;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ojas.obs.emp_edu.dao.EmployeeEducationDetailsDao;
import com.ojas.obs.emp_edu.model.EmployeeEducationDetails;
import com.ojas.obs.emp_edu.model.EmployeeEducationDetailsRequest;

@Repository
public class EmployeeEducationDetailsDaoImpl implements EmployeeEducationDetailsDao {
	@Autowired
	private JdbcTemplate jdbctemplate;

	Logger logger = Logger.getLogger(this.getClass());

	@Override
	public int[] saveEmployeeEducationDetails(EmployeeEducationDetailsRequest empEducationDetailsRequest)
			throws SQLException {

			logger.debug("the input to dao save method is " + empEducationDetailsRequest);
			logger.debug("jdbc template " + jdbctemplate);
			List<EmployeeEducationDetails> employeeEducationDetailsList = empEducationDetailsRequest
					.getEmployeeEducationDetailsList();
			List<Object[]> paramsList = new ArrayList<>();
			for (EmployeeEducationDetails employeeEducationDetails : employeeEducationDetailsList) {
				Object[] param = new Object[] { employeeEducationDetails.getEmployeeId(),
						employeeEducationDetails.getQualification(), employeeEducationDetails.getYear_of_passing(),
						employeeEducationDetails.getPercentage_marks(), employeeEducationDetails.getInstitution_name(),
						employeeEducationDetails.getFlag(), employeeEducationDetails.getCreatedBy(),
						employeeEducationDetails.getUpdatedBy() };
				paramsList.add(param);

			}
			int[] batchUpdate = jdbctemplate.batchUpdate(INSERT_STMT, paramsList);
			logger.debug("the output from dao save method is " + batchUpdate);
			return batchUpdate;
		

	}

	@Override
	public int[] deleteEmployeeEducationDetails(EmployeeEducationDetailsRequest empEducationDetailsRequest)
			throws SQLException {

			logger.debug("the input from dao delete method is " + empEducationDetailsRequest);
			List<EmployeeEducationDetails> employeeEducationDetailsList = empEducationDetailsRequest
					.getEmployeeEducationDetailsList();
			List<Object[]> paramsList = new ArrayList<>();
			for (EmployeeEducationDetails employeeEducationDetails : employeeEducationDetailsList) {
				Object[] params = new Object[] { employeeEducationDetails.getId() };
				paramsList.add(params);
			}
			int[] batchUpdate = jdbctemplate.batchUpdate(DELETE_STMT, paramsList);
			logger.debug("the output from dao delete method is " + batchUpdate);
			return batchUpdate;

	}

	@Override
	public int[] updateEmployeeEducationDetails(EmployeeEducationDetailsRequest empEducationDetailsRequest)
			throws SQLException {
			System.out.println("jdbc template " + jdbctemplate);
			List<EmployeeEducationDetails> employeeEducationDetailsList = empEducationDetailsRequest
					.getEmployeeEducationDetailsList();
			List<Object[]> paramsList = new ArrayList<>();
			for (EmployeeEducationDetails employeeEducationDetails : employeeEducationDetailsList) {
				Object[] param = new Object[] { employeeEducationDetails.getEmployeeId(),
						employeeEducationDetails.getQualification(), employeeEducationDetails.getYear_of_passing(),
						employeeEducationDetails.getPercentage_marks(), employeeEducationDetails.getInstitution_name(),
						employeeEducationDetails.getFlag(), employeeEducationDetails.getCreatedBy(),
						employeeEducationDetails.getUpdatedBy(), employeeEducationDetails.getId() };
				paramsList.add(param);

			}
			int[] batchUpdate = jdbctemplate.batchUpdate(UPDATE_STMT, paramsList);

			return batchUpdate;

	}

	@Override
	public List<EmployeeEducationDetails> getEmployeeEducationDetails(
			EmployeeEducationDetailsRequest empEducationDetailsRequest) throws SQLException {

			logger.debug("the input from dao getEmployeeEducationDetails method is " + empEducationDetailsRequest);
			List<EmployeeEducationDetails> query = jdbctemplate.query(GETALL_STMT,
					new BeanPropertyRowMapper<>(EmployeeEducationDetails.class));
			logger.debug("the output from dao delete method is " + query);
			return query;

	}

	@Override
	public List<EmployeeEducationDetails> getEmployeeEducationDetailsById(
			EmployeeEducationDetailsRequest empEducationDetailsRequest) throws SQLException {

			logger.debug("the input from dao getById method is " + empEducationDetailsRequest);
			List<EmployeeEducationDetails> employeeEducationDetailsList = empEducationDetailsRequest
					.getEmployeeEducationDetailsList();
			EmployeeEducationDetails employeeEducationDetails = employeeEducationDetailsList.get(0);
			Object[] param = null;

			StringBuffer sb = new StringBuffer(GETALL_STMT);
			List<EmployeeEducationDetails> list = new ArrayList<EmployeeEducationDetails>();

			if (null != employeeEducationDetails.getId() && null == employeeEducationDetails.getEmployeeId()) {
				sb.append(" and id = ?");
				String query = sb.toString();
				param = new Object[] { employeeEducationDetails.getId() };
				list = jdbctemplate.query(query, param, new BeanPropertyRowMapper<>(EmployeeEducationDetails.class));
			} else if ((null == employeeEducationDetails.getId())
					&& (null != employeeEducationDetails.getEmployeeId())) {
				sb.append(" and employeeId = ?");
				String query = sb.toString();
				param = new Object[] { employeeEducationDetails.getEmployeeId() };
				list = jdbctemplate.query(query, param, new BeanPropertyRowMapper<>(EmployeeEducationDetails.class));

			} else {
				sb.append(" and id = ? and employeeId = ?");
				String query = sb.toString();
				param = new Object[] { employeeEducationDetails.getId(), employeeEducationDetails.getEmployeeId() };
				list = jdbctemplate.query(query, param, new BeanPropertyRowMapper<>(EmployeeEducationDetails.class));
			}
			logger.debug("the output from dao getById method is " + list);

			return list;

	}

}
