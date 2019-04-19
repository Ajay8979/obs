package com.ojas.obs.projectDetails.facade;

import java.sql.SQLException;

import com.ojas.obs.projectDetails.request.ProjectDetailsRequest;
import com.ojas.obs.projectDetails.response.ProjectDetailsResponse;

public interface ProjectDetailsFacade {

	ProjectDetailsResponse setProjectDetails(ProjectDetailsRequest projectDetailsRequestObject) throws SQLException;

	ProjectDetailsResponse getProjectDetails(ProjectDetailsRequest projectDetailsRequestObject) throws SQLException;

}
