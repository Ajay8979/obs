package com.ojas.obs.employmentdetails.controller;


import static com.ojas.obs.employmentdetails.constant.EmploymentDetailsConstants.GET;
import static com.ojas.obs.employmentdetails.constant.EmploymentDetailsConstants.SET;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.obs.employmentdetails.exception.DataNotInsertedException;
import com.ojas.obs.employmentdetails.exception.EmploymentDetailsException;
import com.ojas.obs.employmentdetails.facade.EmploymentDetailsFacade;
import com.ojas.obs.employmentdetails.model.EmploymentDetailsRequest;
import com.ojas.obs.employmentdetails.model.EmploymentDetailsResponse;

/**
 * resource class inserts,updates,deletes and retrieves data into/from
 * employment_details table
 * 
 * @author vjithin
 *
 */
@RestController
public class EmploymentDetailsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmploymentDetailsController.class);

	@Autowired
	private EmploymentDetailsFacade employmentDetailsService;

	/**
	 * resource inserts,updates,deletes data into employment_details table
	 * 
	 * @param employmentDetailsData
	 * @return
	 * @throws EmploymentDetailsException
	 * @throws DataNotInsertedException
	 */
	@PostMapping(SET)
	public ResponseEntity<Object> setEmploymentDetails(@RequestBody EmploymentDetailsRequest employmentDetailsRequest)
			throws EmploymentDetailsException, DataNotInsertedException {

		LOGGER.debug("The requested object is " + employmentDetailsRequest);

		if (null == employmentDetailsRequest) {
			LOGGER.error("the requested object is null" + employmentDetailsRequest);
			throw new EmploymentDetailsException("The requested object is null");
		}

		EmploymentDetailsResponse employementDetailsResponse = employmentDetailsService
				.saveEmploymentDetails(employmentDetailsRequest);
		LOGGER.debug("response object is " + employementDetailsResponse);
		return new ResponseEntity<>(employementDetailsResponse, HttpStatus.OK);

	}

	/**
	 * resource retrieves data from employment_details table
	 * 
	 * @param employmentDetailsData
	 * @return
	 * @throws EmploymentDetailsException
	 */
	@PostMapping(GET)
	public ResponseEntity<Object> getEmploymentDetails(@RequestBody EmploymentDetailsRequest employmentDetailsRequest)
			throws EmploymentDetailsException {

		LOGGER.debug("requested object is " + employmentDetailsRequest);

		if (null == employmentDetailsRequest) {
			LOGGER.error("the requested object is null" + employmentDetailsRequest);
			throw new EmploymentDetailsException("The requested object is null");
		}

		EmploymentDetailsResponse employementDetailsResponse = employmentDetailsService
				.viewEmploymentDetails(employmentDetailsRequest);
		LOGGER.debug("response object is " + employementDetailsResponse);
		return new ResponseEntity<Object>(employementDetailsResponse, HttpStatus.OK);

	}

}
