package com.ojas.obs.facade;

import java.sql.SQLException;

import com.ojas.obs.request.ExperienceRequest;
import com.ojas.obs.response.ExperienceResponse;

public interface ExperienceFacade {

	ExperienceResponse setEmployeeExperienceDetails(
			ExperienceRequest employeeExperienceDetailsRequestObject) throws SQLException;

	ExperienceResponse getEmployeeExperienceDetails(
			ExperienceRequest employeeExperienceDetailsRequestObject) throws SQLException;
}
