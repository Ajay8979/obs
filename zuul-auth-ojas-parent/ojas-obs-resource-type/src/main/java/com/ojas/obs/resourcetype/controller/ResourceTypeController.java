package com.ojas.obs.resourcetype.controller;

import static com.ojas.obs.resourcetype.constant.ResourceTypesConstants.GET;
import static com.ojas.obs.resourcetype.constant.ResourceTypesConstants.SET;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ojas.obs.resourcetype.facade.ResourceTypeFacade;
import com.ojas.obs.resourcetype.model.ResourceTypeRequest;
import com.ojas.obs.resourcetype.model.ResourceTypeResponse;
import com.ojas.obs.resourcetype.model.ErrorResponse;
//import static com.ojas.obs.resourcetype.constant.ResourceTypesConstants.EMPLOYMENT_DETAILS;
/**
 * resource class inserts,updates,deletes and retrieves data into/from
 * employment_details table
 * 
 * @author akrishna
 *
 */
@RestController
//@RequestMapping(EMPLOYMENT_DETAILS)
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
	public ResponseEntity<Object> setEmploymentDetails(@RequestBody ResourceTypeRequest resourceTypeRequest)
			throws SQLException {

		LOGGER.debug("The requested object is " + resourceTypeRequest);
		ResourceTypeResponse employementDetailsResponse = null;
		ErrorResponse error = new ErrorResponse();
		ResponseEntity<Object> responseEntity = null;
		try {
			if (null == resourceTypeRequest) {
				LOGGER.error("the requested object is null" + resourceTypeRequest);
				// throw new EmploymentDetailsException("The requested object is null");
			}
			employementDetailsResponse = resourceTypeFacade.saveResourceTypes(resourceTypeRequest);
			LOGGER.debug("response object is " + resourceTypeRequest);
			return new ResponseEntity<Object>(employementDetailsResponse, HttpStatus.OK);

		} catch (DuplicateKeyException dke) {
			error.setStatusMessage(dke.getCause().getMessage());
			error.setMessage("duplicates are not allowed");
			error.setStatusCode("409");
			dke.printStackTrace();
			error.setStatusMessage(dke.getCause().getLocalizedMessage());
			responseEntity = new ResponseEntity<Object>(error, HttpStatus.CONFLICT);
		} catch (SQLException ede) {
			error.setStatusMessage(ede.getCause().getMessage());
			error.setMessage("EmploymentDetailsException");
			error.setStatusCode("409");
			ede.printStackTrace();
			error.setStatusMessage(ede.getCause().getLocalizedMessage());
			error.setMessage("SQLException occured");
			responseEntity = new ResponseEntity<Object>(error, HttpStatus.CONFLICT);
		}  catch (Exception sq) {
			error.setStatusMessage(sq.getCause().getMessage());
			error.setMessage("Exception are not allowed");
			error.setStatusCode("409");
			sq.printStackTrace();
			error.setMessage(sq.getCause().getLocalizedMessage());
			responseEntity = new ResponseEntity<Object>(error, HttpStatus.CONFLICT);
		}

		return responseEntity;

	}

	/**
	 * resource retrieves data from employment_details table
	 * 
	 * @param employmentDetailsData
	 * @return
	 * @throws EmploymentDetailsException
	 */
	@PostMapping(GET)
	public ResponseEntity<Object> getResourceTypes(@RequestBody ResourceTypeRequest resourceTypeRequest) {

		LOGGER.debug("requested object is " + resourceTypeRequest);
		ResourceTypeResponse resourceTypeResponse = null;
		ErrorResponse error = new ErrorResponse();
		if (null != resourceTypeRequest) {
			LOGGER.error("the requested object is null" + resourceTypeRequest);
			try {
				LOGGER.debug("response object is " + resourceTypeResponse);
				resourceTypeResponse = resourceTypeFacade.viewResourceTypes(resourceTypeRequest);
			} catch (SQLException e) {
				error.setStatusMessage(e.getCause().getMessage());
				error.setMessage("EmploymentDetailsException");
				error.setStatusCode("409");
				e.printStackTrace();
				error.setMessage(e.getCause().getLocalizedMessage());
				return new ResponseEntity<Object>(error, HttpStatus.CONFLICT);
			}
			catch (Exception e) {
				error.setStatusMessage(e.getCause().getMessage());
				error.setMessage("EmploymentDetailsException");
				error.setStatusCode("409");
				e.printStackTrace();
				error.setMessage(e.getCause().getLocalizedMessage());
				return new ResponseEntity<Object>(error, HttpStatus.CONFLICT);
			}
		}

		return new ResponseEntity<Object>(resourceTypeResponse, HttpStatus.OK);

	}

}
