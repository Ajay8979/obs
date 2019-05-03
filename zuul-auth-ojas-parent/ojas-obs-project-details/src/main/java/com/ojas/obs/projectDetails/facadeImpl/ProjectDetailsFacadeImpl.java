package com.ojas.obs.projectDetails.facadeImpl;

import java.sql.SQLException;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ojas.obs.projectDetails.Dao.ProjectDetailsDao;
import com.ojas.obs.projectDetails.facade.ProjectDetailsFacade;
import com.ojas.obs.projectDetails.model.ProjectDetails;
import com.ojas.obs.projectDetails.request.ProjectDetailsRequest;
import com.ojas.obs.projectDetails.response.ProjectDetailsResponse;

import static com.ojas.obs.projectDetails.constants.UserConstants.*;

@Component
public class ProjectDetailsFacadeImpl implements ProjectDetailsFacade {
	@Autowired
	ProjectDetailsDao projectDetailsDao;

	ProjectDetailsResponse projectDetailsResponse = null;
	Logger logger = Logger.getLogger(this.getClass());
@Transactional
	@Override
	public ProjectDetailsResponse setProjectDetails(ProjectDetailsRequest projectDetailsRequestObject)
			throws SQLException {
		logger.debug(
				" Received input data in ProjectDetailsServiceImpl/setProjectDetails :" + projectDetailsRequestObject);
		projectDetailsResponse = new ProjectDetailsResponse();

		if (projectDetailsRequestObject.getTransactionType().equalsIgnoreCase(SAVE)) {

			logger.debug(" Received input data in ProjectDetailsServiceImpl/saveProjectDetails :"
					+ projectDetailsRequestObject);
			projectDetailsResponse.setStatusCode("409");
			projectDetailsResponse.setStatusMessage("sorry ProjectDetails is not saved");
			Integer saveProjectDetails = projectDetailsDao.saveProjectDetails(projectDetailsRequestObject);
			logger.debug("received at service by calling the saveProjectDetails is" + saveProjectDetails);
			if (saveProjectDetails > 0 ) {
				projectDetailsResponse.setStatusCode("200");
				projectDetailsResponse.setStatusMessage("ProjectDetails has saved successfully");
			}
			return projectDetailsResponse;
		}

		if (projectDetailsRequestObject.getTransactionType().equalsIgnoreCase(UPDATE)) {

			logger.debug(" Received input data in ProjectDetailsServiceImpl/updateProjectDetails :"
					+ projectDetailsRequestObject);

			Integer updateProjectDetails = projectDetailsDao.updateProjectDetails(projectDetailsRequestObject);
			projectDetailsResponse.setStatusCode("409");
			projectDetailsResponse.setStatusMessage("sorry ProjectDetails is not updated");
			logger.debug("received at service by calling the updateProjectDetails is" + updateProjectDetails);
			if (updateProjectDetails>0) {
				projectDetailsResponse.setStatusCode("200");
				projectDetailsResponse.setStatusMessage("ProjectDetails has updated successfully");
			}
			return projectDetailsResponse;
		}

		if (projectDetailsRequestObject.getTransactionType().equalsIgnoreCase(DELETE)) {

			logger.debug(" Received input data in ProjectDetailsServiceImpl/deleteProjectDetails :"
					+ projectDetailsRequestObject);

			Integer deleteProjectDetails = projectDetailsDao.deleteProjectDetails(projectDetailsRequestObject);
			projectDetailsResponse.setStatusCode("409");
			projectDetailsResponse.setStatusMessage("sorry ProjectDetails is not delete");
			logger.debug("received at service by calling the deleteProjectDetails is" + deleteProjectDetails);
			if (deleteProjectDetails> 0) {
				projectDetailsResponse.setStatusCode("200");
				projectDetailsResponse.setStatusMessage("ProjectDetails has deactivated successfully");
			}
			return projectDetailsResponse;
		}

		return projectDetailsResponse;
	}

	@Override
	public ProjectDetailsResponse getProjectDetails(ProjectDetailsRequest projectDetailsRequestObject)
			throws SQLException {
		logger.debug(
				" Received input data in ProjectDetailsServiceImpl/getProjectDetails :" + projectDetailsRequestObject);
		projectDetailsResponse = new ProjectDetailsResponse();
		if (projectDetailsRequestObject.getProjectDetailsList()==null ||projectDetailsRequestObject.getProjectDetailsList().isEmpty()) {

			logger.debug(" Received input data in ProjectDetailsServiceImpl/getAll ");
			List<ProjectDetails> projectDetailsList = projectDetailsDao.getAll();
			logger.debug("received at service by calling the getAllmethod is" + projectDetailsList);
			if (!projectDetailsList.isEmpty()) {
				projectDetailsResponse.setStatusCode("200");
				projectDetailsResponse.setStatusMessage("you got the list of projectDetails successfully");
				projectDetailsResponse.setProjectDetailsList(projectDetailsList);
			} else {
				projectDetailsResponse.setProjectDetailsList(projectDetailsList);
				projectDetailsResponse.setStatusCode("200");
				projectDetailsResponse.setStatusMessage("no records found");
			}
			return projectDetailsResponse;
		}
		else if(null != projectDetailsRequestObject.getProjectDetailsList().get(0).getId()) {
			logger.debug(" Received input data in ProjectDetailsServiceImpl/getById :" + projectDetailsRequestObject);

			List<ProjectDetails> projectDetailsListById = projectDetailsDao
					.getById(projectDetailsRequestObject.getProjectDetailsList().get(0).getId());
			logger.debug("received at service by calling the getByIdmethod is" + projectDetailsListById);
			if (!projectDetailsListById.isEmpty()) {
				projectDetailsResponse.setStatusCode("200");
				projectDetailsResponse.setStatusMessage("you got projectDetails successfully");
				projectDetailsResponse.setProjectDetailsList(projectDetailsListById);
			} else {
				projectDetailsResponse.setProjectDetailsList(projectDetailsListById);
				projectDetailsResponse.setStatusCode("200");
				projectDetailsResponse.setStatusMessage("no records found");
			}

			return projectDetailsResponse;
		}
		else if(null != projectDetailsRequestObject.getProjectDetailsList().get(0).getEmployeeId()) {
			logger.debug(" Received input data in ProjectDetailsServiceImpl/getByEmpId :" + projectDetailsRequestObject);

			List<ProjectDetails> projectDetailsListById = projectDetailsDao
					.getByEmpId(projectDetailsRequestObject.getProjectDetailsList().get(0).getEmployeeId());
			logger.debug("received at service by calling the getByIdmethod is" + projectDetailsListById);
			if (!projectDetailsListById.isEmpty()) {
				projectDetailsResponse.setStatusCode("200");
				projectDetailsResponse.setStatusMessage("you got projectDetails successfully");
				projectDetailsResponse.setProjectDetailsList(projectDetailsListById);
			} else {
				projectDetailsResponse.setProjectDetailsList(projectDetailsListById);
				projectDetailsResponse.setStatusCode("200");
				projectDetailsResponse.setStatusMessage("no records found");
			}

			return projectDetailsResponse;
		}
		
		return projectDetailsResponse;
		
	}

	
}
