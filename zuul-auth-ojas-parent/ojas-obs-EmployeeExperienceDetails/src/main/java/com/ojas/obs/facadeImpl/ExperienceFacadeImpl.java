package com.ojas.obs.facadeImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ojas.obs.dao.ExperienceDao;
import com.ojas.obs.facade.ExperienceFacade;
import com.ojas.obs.model.EmployeeExperienceDetails;
import com.ojas.obs.request.ExperienceRequest;
import com.ojas.obs.response.ExperienceResponse;

import static com.ojas.obs.constants.UserConstants.*;

import java.sql.SQLException;
import java.util.List;

@Component
public class ExperienceFacadeImpl implements ExperienceFacade {
	@Autowired
	ExperienceDao employeeExperienceDetailsDao;
	ExperienceResponse experienceResponse = null;
	Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ExperienceResponse setEmployeeExperienceDetails(ExperienceRequest experienceRequestObject)
			throws SQLException {
		List<EmployeeExperienceDetails> employeeExperienceDetailsList = experienceRequestObject
				.getEmployeeExperienceDetails();
		for (EmployeeExperienceDetails employeeExperienceDetails : employeeExperienceDetailsList) {
			experienceResponse = new ExperienceResponse();

			// -------------------------------SAVE METHOD-------------------------------

			if (experienceRequestObject.getTransactionType().equalsIgnoreCase(SAVE)) {
				experienceResponse.setStatusCode("409");
				experienceResponse.setStatusMessage("sorry EmployeeExperienceDetails is not saved");

				int saveRes = employeeExperienceDetailsDao.saveEmployeeExperienceDetails(experienceRequestObject);
				logger.debug("received at service by calling the saveEmployeeExperienceDetails is" + saveRes);
				if (saveRes > 0) {
					experienceResponse.setStatusCode("200");
					experienceResponse.setStatusMessage("EmployeeExperienceDetails has saved successfully");

				}
				return experienceResponse;
			}

			// -------------------------------UPDATE METHOD-------------------------------

			if (experienceRequestObject.getTransactionType().equalsIgnoreCase(UPDATE)) {
				if (null != employeeExperienceDetails.getId()) {
					int updateEmployeeExperienceDetails = employeeExperienceDetailsDao
							.updateEmployeeExperienceDetails(experienceRequestObject);
					experienceResponse.setStatusCode("409");
					experienceResponse.setStatusMessage("sorry EmployeeExperienceDetails is not updated");
					logger.debug("received at service by calling the updateEmployeeExperienceDetails is"
							+ updateEmployeeExperienceDetails);

					if (updateEmployeeExperienceDetails > 0) {
						experienceResponse.setStatusCode("200");
						experienceResponse.setStatusMessage("EmployeeExperienceDetails has updated successfully");
					}
				} else {
					experienceResponse.setStatusCode("422");
					experienceResponse.setStatusMessage("please provide the id");
				}
				return experienceResponse;
			}

			// -------------------------------DELETE METHOD-------------------------------

			if (experienceRequestObject.getTransactionType().equalsIgnoreCase(DELETE)) {
				if (null != employeeExperienceDetails.getId()) {

					int deleteEmployeeExperienceDetails = employeeExperienceDetailsDao
							.deleteEmployeeExperienceDetails(experienceRequestObject);
					experienceResponse.setStatusCode("409");
					experienceResponse.setStatusMessage("sorry EmployeeExperienceDetails is not deactivated");
					logger.debug("received at service by calling the deleteEmployeeExperienceDetails is"
							+ deleteEmployeeExperienceDetails);
					if (deleteEmployeeExperienceDetails > 0) {
						experienceResponse.setStatusCode("200");
						experienceResponse.setStatusMessage("EmployeeExperienceDetails has deleted sucesfully");
						return experienceResponse;
					}
				} else {
					experienceResponse.setStatusCode("422");
					experienceResponse.setStatusMessage("please provide the id");
					return experienceResponse;
				}
			}

		}
		return experienceResponse;

	}

	@Override
	public ExperienceResponse getEmployeeExperienceDetails(ExperienceRequest experienceRequestObject)
			throws SQLException {
		List<EmployeeExperienceDetails> employeeExperienceDetailslist = experienceRequestObject
				.getEmployeeExperienceDetails();
		for (EmployeeExperienceDetails employeeExperienceDetails : employeeExperienceDetailslist) {

			// ---------------GETBYID METHOD--------------------

			ExperienceResponse experienceResponse = new ExperienceResponse();
			if (experienceRequestObject.getTransactionType().equalsIgnoreCase(GETBYID)) {
				List<EmployeeExperienceDetails> employeeExperienceDetailsListById = employeeExperienceDetailsDao
						.getById(employeeExperienceDetails.getId());
				logger.debug(
						"received at service by calling the getById method is" + employeeExperienceDetailsListById);
				if (!employeeExperienceDetailsListById.isEmpty()) {
					experienceResponse.setStatusCode("200");
					experienceResponse.setStatusMessage("you got employeeExperienceDetails successfully");
					experienceResponse.setEmployeeExperienceDetails(employeeExperienceDetailsListById);
					
				} else {
					experienceResponse.setStatusCode("422");
					experienceResponse.setStatusMessage("Employee ID is null");
				}

				return experienceResponse;
			}

			// -------------------------GETALL METHOD------------------------------------

			if (experienceRequestObject.getTransactionType().equalsIgnoreCase(GETALL)) {
				List<EmployeeExperienceDetails> employeeExperienceDetailsList = employeeExperienceDetailsDao.getAll();
				logger.debug("received at service by calling the getAllmethod is" + employeeExperienceDetailsList);
				if (!employeeExperienceDetailsList.isEmpty()) {
					experienceResponse.setStatusCode("200");
					experienceResponse.setStatusMessage("you got list of employeeExperienceDetails successfully");
					experienceResponse.setEmployeeExperienceDetails(employeeExperienceDetailsList);
					return experienceResponse;

				} else {
					experienceResponse.setStatusCode("200");
					experienceResponse.setStatusMessage("no record found");
					return experienceResponse;
				}
			}
		}
		return experienceResponse;
	}

}
