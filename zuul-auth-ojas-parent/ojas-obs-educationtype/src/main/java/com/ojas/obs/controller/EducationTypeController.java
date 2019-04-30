package com.ojas.obs.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.obs.constants.UserConstants;
import com.ojas.obs.error.ErrorResponse;
import com.ojas.obs.facade.EmployeeEducationFacade;
import com.ojas.obs.model.EmployeeEducation;
import com.ojas.obs.modelrequest.EmployeeEducationRequest;

/**
 * 
 * @author mpraneethguptha
 *
 */
@RestController
public class EducationTypeController {

	@Autowired
	EmployeeEducationFacade employeeEducationFacade;
	Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 
	 * @param employeeEducationRequestObject
	 * @param httpServletRequest
	 * @param servletresponse
	 * @return
	 */
	@PostMapping("/set")
	public ResponseEntity<Object> setEmployeeEductaionInfo(
			@RequestBody EmployeeEducationRequest employeeEducationRequestObject, HttpServletRequest httpServletRequest,
			HttpServletResponse servletresponse) {

		try {
			List<EmployeeEducation> listEmployeeEducations = employeeEducationRequestObject.getListEmployeeEducations();
			if (null == listEmployeeEducations || listEmployeeEducations.isEmpty()) {

				ErrorResponse error = new ErrorResponse();
				logger.debug("logger is not valid");
				error.setMessage("Request is not valid");
				error.setStatusCode("422");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}

			for (EmployeeEducation employeeEducation : listEmployeeEducations) {

				if (!employeeEducationRequestObject.getTransactionType().equalsIgnoreCase(UserConstants.DELETE)) {
					if (((null == employeeEducation.getEducationType()
							|| employeeEducation.getEducationType().isEmpty()))) {
						ErrorResponse error = new ErrorResponse();
						logger.debug("this employeeEducation contains null values");
						error.setMessage("Request is  invalid");
						error.setStatusCode("422");
						return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
					}
				}
				if ((employeeEducationRequestObject.getTransactionType().equalsIgnoreCase(UserConstants.UPDATE)
						|| employeeEducationRequestObject.getTransactionType().equalsIgnoreCase(UserConstants.DELETE))
						&& (employeeEducation.getId() == null)) {
					logger.debug("request object id is null");
					ErrorResponse error = new ErrorResponse();
					error.setMessage("Request is Invalid");
					error.setStatusCode("422");
					return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
				}
			}
			return employeeEducationFacade.setEmployeeEducationInfo(employeeEducationRequestObject);

		} catch (Exception exception) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage(exception.getMessage());
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

	/**
	 * 
	 * @param courseRequest
	 * @param servletRequest
	 * @param servletResponse
	 * @return
	 * @throws SQLException
	 */
	@PostMapping("/get")
	public ResponseEntity<Object> getEductionDetails(@RequestBody EmployeeEducationRequest employeeEducationRequest,
			HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws SQLException {

		String sessionId = null;
		try {
			logger.debug("requestObject received = " + employeeEducationRequest);
			if (null == employeeEducationRequest) {
				ErrorResponse error = new ErrorResponse();
				error.setMessage("Data is invalid");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			if (!employeeEducationRequest.getTransactionType().equalsIgnoreCase("getAll")) {
				ErrorResponse error = new ErrorResponse();
				error.setMessage("Data is invalid");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			sessionId = employeeEducationRequest.getSessionID();
			logger.debug("sessionId is = " + sessionId);

			return employeeEducationFacade.getEmployeeEducationInfo(employeeEducationRequest);

		} catch (Exception exception) {
			logger.debug("inside catch block.*******");
			ErrorResponse error = new ErrorResponse();
			error.setStatusMessage(exception.getMessage());
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}}
