package com.ojas.obs.dao;

import java.sql.SQLException;
import java.util.List;

import com.ojas.obs.model.EmployeeExperienceDetails;
import com.ojas.obs.request.ExperienceRequest;

public interface ExperienceDao {

	public int saveEmployeeExperienceDetails(ExperienceRequest employeeExperienceDetailsRequestObject)
			throws SQLException;

	public int updateEmployeeExperienceDetails(ExperienceRequest employeeExperienceDetailsRequestObject)
			throws SQLException;

	public List<EmployeeExperienceDetails> getById(Integer employeeId) throws SQLException;

	public List<EmployeeExperienceDetails> getAll() throws SQLException;

	int deleteEmployeeExperienceDetails(ExperienceRequest experienceRequestObject) throws SQLException;

}
