package com.ojas.obs.emp_edu.daoimpl;

import static com.ojas.obs.emp_edu.utility.Constants.DELETE_STMT;
import static com.ojas.obs.emp_edu.utility.Constants.GETALL_STMT;
import static com.ojas.obs.emp_edu.utility.Constants.GETBYID_STMT;
import static com.ojas.obs.emp_edu.utility.Constants.INSERT_STMT;
import static com.ojas.obs.emp_edu.utility.Constants.UPDATE_STMT;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	@Override
	public int[] saveEmployeeEducationDetails(EmployeeEducationDetailsRequest empEducationDetailsRequest)
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
					employeeEducationDetails.getUpdatedBy() };
			paramsList.add(param);

		}
		int[] batchUpdate = jdbctemplate.batchUpdate(INSERT_STMT, paramsList);

		return batchUpdate;
	}

	@Override
	public int[] deleteEmployeeEducationDetails(EmployeeEducationDetailsRequest empEducationDetailsRequest)
			throws SQLException {
		List<EmployeeEducationDetails> employeeEducationDetailsList = empEducationDetailsRequest
				.getEmployeeEducationDetailsList();
		List<Object[]> paramsList = new ArrayList<>();
		for (EmployeeEducationDetails employeeEducationDetails : employeeEducationDetailsList) {
			Object[] params = new Object[] { employeeEducationDetails.getId() };
			paramsList.add(params);
		}
		int[] batchUpdate = jdbctemplate.batchUpdate(DELETE_STMT, paramsList);
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
					employeeEducationDetails.getUpdatedBy(),employeeEducationDetails.getId() };
			paramsList.add(param);

		}
		int[] batchUpdate = jdbctemplate.batchUpdate(UPDATE_STMT, paramsList);

		return batchUpdate;
	}

	@Override
	public List<EmployeeEducationDetails> getEmployeeEducationDetails(
			EmployeeEducationDetailsRequest empEducationDetailsRequest) throws SQLException {
		List<EmployeeEducationDetails> query = jdbctemplate.query(GETALL_STMT,
				new BeanPropertyRowMapper<>(EmployeeEducationDetails.class));
		return query;
	}
	
	@Override
	public List<EmployeeEducationDetails> getEmployeeEducationDetailsById(
			EmployeeEducationDetailsRequest empEducationDetailsRequest) throws SQLException {
		List<EmployeeEducationDetails> employeeEducationDetailsList = empEducationDetailsRequest.getEmployeeEducationDetailsList();
		EmployeeEducationDetails employeeEducationDetails = employeeEducationDetailsList.get(0);
		Object[] param = null;	
		param	 = new Object[] { employeeEducationDetails.getId() };
		List<EmployeeEducationDetails> query = jdbctemplate.query(GETBYID_STMT,param,
				new BeanPropertyRowMapper<>(EmployeeEducationDetails.class));
		return query;
	
	}

}
