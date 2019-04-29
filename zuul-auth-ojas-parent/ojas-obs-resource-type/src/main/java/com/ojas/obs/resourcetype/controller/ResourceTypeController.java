package com.ojas.obs.resourcetype.controller;


import static com.ojas.obs.resourcetype.constant.ResourceTypesConstants.GET;
import static com.ojas.obs.resourcetype.constant.ResourceTypesConstants.SET;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.obs.resourcetype.exception.DataNotInsertedException;
import com.ojas.obs.resourcetype.exception.EmploymentDetailsException;
import com.ojas.obs.resourcetype.facade.ResourceTypeFacade;
import com.ojas.obs.resourcetype.model.ResourceTypeRequest;
import com.ojas.obs.resourcetype.model.ResourceTypeResponse;

/**
 * resource class inserts,updates,deletes and retrieves data into/from
 * employment_details table
 * 
 * @author vjithin
 *
 */
@RestController
public class ResourceTypeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceTypeController.class);

	@Autowired
	private ResourceTypeFacade resourceTypeFacade;

	/**
	 * resource inserts,updates,deletes data into employment_details table
	 * 
	 * @param employmentDetailsData
	 * @return
	 * @throws EmploymentDetailsException
	 * @throws DataNotInsertedException
	 */
	@PostMapping(SET)
	public ResponseEntity<ResourceTypeResponse> setEmploymentDetails(@RequestBody ResourceTypeRequest resourceTypeRequest)
			throws EmploymentDetailsException, DataNotInsertedException {

		LOGGER.debug("The requested object is " + resourceTypeRequest);

		if (null == resourceTypeRequest) {
			LOGGER.error("the requested object is null" + resourceTypeRequest);
			throw new EmploymentDetailsException("The requested object is null");
		}

		ResourceTypeResponse employementDetailsResponse = resourceTypeFacade
				.saveResourceTypes(resourceTypeRequest);
		LOGGER.debug("response object is " + resourceTypeRequest);
		return new ResponseEntity<ResourceTypeResponse>(employementDetailsResponse, HttpStatus.OK);

	}

	/**
	 * resource retrieves data from employment_details table
	 * 
	 * @param employmentDetailsData
	 * @return
	 * @throws EmploymentDetailsException
	 */
	@PostMapping(GET)
	public ResponseEntity<Object> getResourceTypes(@RequestBody ResourceTypeRequest resourceTypeRequest)
			throws EmploymentDetailsException {

		LOGGER.debug("requested object is " + resourceTypeRequest);

		if (null == resourceTypeRequest) {
			LOGGER.error("the requested object is null" + resourceTypeRequest);
			throw new EmploymentDetailsException("The requested object is null");
		}

		ResourceTypeResponse resourceTypeResponse = resourceTypeFacade.viewResourceTypes(resourceTypeRequest);
		LOGGER.debug("response object is " + resourceTypeResponse);
		return new ResponseEntity<Object>(resourceTypeResponse, HttpStatus.OK);

	}

}
