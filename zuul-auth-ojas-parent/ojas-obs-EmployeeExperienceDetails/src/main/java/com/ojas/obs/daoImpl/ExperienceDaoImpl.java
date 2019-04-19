package com.ojas.obs.daoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.ojas.obs.constants.PropsReaderUtil;
import com.ojas.obs.dao.ExperienceDao;
import com.ojas.obs.model.EmployeeExperienceDetails;
import com.ojas.obs.request.ExperienceRequest;
import com.ojas.obs.rowmapper.ExperienceRowMappers;

@Component
public class ExperienceDaoImpl implements ExperienceDao {
	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	PropsReaderUtil propsReaderUtil;

	// -------------------------SAVE METHOD------------------------------------

	@Override
	public int saveEmployeeExperienceDetails(ExperienceRequest experienceRequestObject) {
		List<EmployeeExperienceDetails> employeeExperienceDetailsList = experienceRequestObject
				.getEmployeeExperienceDetails();
		List<Object[]> inputList = new ArrayList<Object[]>();

		for (EmployeeExperienceDetails employeeExperienceDetails : employeeExperienceDetailsList) {

			Object[] params = new Object[] { employeeExperienceDetails.getCompany_name(),
					employeeExperienceDetails.getJoining_date(), employeeExperienceDetails.getExit_date(),
					employeeExperienceDetails.getSalary(), employeeExperienceDetails.getLocation(),
					employeeExperienceDetails.isIs_current_company(), employeeExperienceDetails.getEmployee_Id(),
					employeeExperienceDetails.getFirst_Reference_Name(),
					employeeExperienceDetails.getFirst_Reference_Contact(),
					employeeExperienceDetails.getSecond_Reference_Name(),
					employeeExperienceDetails.getSecond_Reference_Contact(), employeeExperienceDetails.isFlag(),
					employeeExperienceDetails.getCreated_by(), employeeExperienceDetails.getUpdated_by() };
			inputList.add(params);

		}
		int[] batchUpdate = jdbcTemplate.batchUpdate(propsReaderUtil.getValue("insert_employee_experience_details"),
				inputList);
		if (batchUpdate.length > 0) {
			return 1;
		}

		return 0;

	}

	// -------------------------UPDATE METHOD------------------------------------

	@Override
	public int updateEmployeeExperienceDetails(ExperienceRequest experienceRequestObject) {
		List<EmployeeExperienceDetails> employeeExperienceDetailsList = experienceRequestObject
				.getEmployeeExperienceDetails();
		List<Object[]> inputList = new ArrayList<Object[]>();
		/*
		 * EmployeeExperienceDetails employeeExperienceDetails =
		 * employeeExperienceDetailsRequestObject .getEmployeeExperienceDetails();
		 */

		for (EmployeeExperienceDetails employeeExperienceDetails : employeeExperienceDetailsList) {
			Object[] params = new Object[] { employeeExperienceDetails.getCompany_name(),
					employeeExperienceDetails.getJoining_date(), employeeExperienceDetails.getExit_date(),
					employeeExperienceDetails.getSalary(), employeeExperienceDetails.getLocation(),
					employeeExperienceDetails.isIs_current_company(), employeeExperienceDetails.getEmployee_Id(),
					employeeExperienceDetails.getFirst_Reference_Name(),
					employeeExperienceDetails.getFirst_Reference_Contact(),
					employeeExperienceDetails.getSecond_Reference_Name(),
					employeeExperienceDetails.getSecond_Reference_Contact(), false,
					employeeExperienceDetails.getUpdated_by(), employeeExperienceDetails.getId() };
			inputList.add(params);
		}

		int[] batchUpdate = jdbcTemplate.batchUpdate(propsReaderUtil.getValue("update_employee_experience_details"),
				inputList);
		/*
		 * int[] batchUpdate =
		 * jdbcTemplate.batchUpdate("update_employee_experience_details", inputList);
		 */

		if (batchUpdate.length > 0) {
			return 1;
		}
		return 0;
	}

	// -------------------------DELETE METHOD------------------------------------

	@Override
	public int deleteEmployeeExperienceDetails(ExperienceRequest experienceRequestObject) throws SQLException {
		List<EmployeeExperienceDetails> employeeExperienceDetailsList = experienceRequestObject
				.getEmployeeExperienceDetails();
		List<Object[]> inputList = new ArrayList<Object[]>();

		for (EmployeeExperienceDetails employeeExperienceDetails : employeeExperienceDetailsList) {
			Object[] params = new Object[] { true, employeeExperienceDetails.getUpdated_by(),
					employeeExperienceDetails.getId() };
			inputList.add(params);
		}

		int[] batchUpdate = jdbcTemplate.batchUpdate(propsReaderUtil.getValue("delete_employee_experience_details"),
				inputList);

//		int[] batchUpdate = jdbcTemplate.batchUpdate("delete_employee_experience_details", inputList);
		if (batchUpdate.length > 0) {
			return 1;
		}

		return 0;
	}

	// -------------------------GETBYID METHOD------------------------------------

	@Override
	public List<EmployeeExperienceDetails> getById(Integer id) throws SQLException {
		Object[] params = new Object[] { id };

		List<EmployeeExperienceDetails> employeeExperienceDetailsList = jdbcTemplate.query(
				propsReaderUtil.getValue("getById_employee_experience_details"), params, new ExperienceRowMappers());

		/*
		 * List<EmployeeExperienceDetails> employeeExperienceDetailsList = jdbcTemplate
		 * .query("getById_employee_experience_details", params, new
		 * ExperienceRowMappers());
		 */

		return employeeExperienceDetailsList;
	}

	// -------------------------GETALL METHOD------------------------------------

	@Override
	public List<EmployeeExperienceDetails> getAll() throws SQLException {

		List<EmployeeExperienceDetails> employeeExperienceDetailsList = jdbcTemplate
				.query(propsReaderUtil.getValue("getAll_employee_experience_details"), new ExperienceRowMappers());

		/*
		 * List<EmployeeExperienceDetails> employeeExperienceDetailsList = jdbcTemplate
		 * .query("getAll_employee_experience_details", new ExperienceRowMappers());
		 */
		return employeeExperienceDetailsList;
	}

}
