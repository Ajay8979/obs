package com.ojas.obs.resourcetype.facade;

import com.ojas.obs.resourcetype.exception.DataNotInsertedException;
import com.ojas.obs.resourcetype.exception.EmploymentDetailsException;
import com.ojas.obs.resourcetype.model.ResourceTypeRequest;
import com.ojas.obs.resourcetype.model.ResourceTypeResponse;

public interface ResourceTypeFacade {

	ResourceTypeResponse saveResourceTypes(ResourceTypeRequest employmentDetailsRequest)
			throws EmploymentDetailsException, DataNotInsertedException;

	ResourceTypeResponse viewResourceTypes(ResourceTypeRequest resourceTypeRequest) throws EmploymentDetailsException;

}
