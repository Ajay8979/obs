package com.ojas.obs.controller;

import static com.ojas.obs.constants.UserConstants.GET;
import static com.ojas.obs.constants.UserConstants.GETALL;
import static com.ojas.obs.constants.UserConstants.SAVE;
import static com.ojas.obs.constants.UserConstants.SET;
import static com.ojas.obs.constants.UserConstants.UPDATE;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.obs.error.ErrorResponse;
import com.ojas.obs.facadeimpl.EmployeeStatusFacadeImpl;
import com.ojas.obs.model.EmployeeStatus;
import com.ojas.obs.request.EmployeeStatusRequest;

/**
 * 
 * @author Manohar
 * 
 */
@RestController
public class EmployeeStatusController {

	@Autowired
	private EmployeeStatusFacadeImpl employeeStatusFacade;
	Logger logger = Logger.getLogger(this.getClass());
	EmployeeStatusRequest employeeStatusRequest = null;

	@RequestMapping(SET)
	public ResponseEntity<Object> setEmployeeStatus(@RequestBody EmployeeStatusRequest employeeStatusRequest,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		logger.debug("incoming requests " + employeeStatusRequest);
		try {
			List<EmployeeStatus> employeeStatusList = employeeStatusRequest.getEmployeeStatus();
			if (null == employeeStatusList) {
				logger.error("Request is not valid");
				ErrorResponse errorResponse = new ErrorResponse();
				errorResponse.setStatusCode("422");
				errorResponse.setMessage("Data must not be null");
				return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			for (EmployeeStatus employeeStatus : employeeStatusList) {
				if ((employeeStatusRequest.getTransactionType() == null
						|| employeeStatusRequest.getTransactionType().isEmpty() || employeeStatus == null)) {
					logger.error("Request is not valid");
					ErrorResponse errorResponse = new ErrorResponse();
					errorResponse.setStatusCode("422");
					errorResponse.setMessage("Data must not be null");
					return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
				}
				if (employeeStatusRequest.getTransactionType().equalsIgnoreCase(SAVE)
						&& (employeeStatus.getStatus() == null || employeeStatus.getStatus().isEmpty())) {
					logger.error("Request is not valid");
					ErrorResponse errorResponse = new ErrorResponse();
					errorResponse.setStatusCode("422");
					errorResponse.setMessage("Data must not be null");
					return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
				}
				if ((employeeStatusRequest.getTransactionType().equalsIgnoreCase(UPDATE)
						&& ((employeeStatus.getStatus() == null || employeeStatus.getStatus().isEmpty())
								|| employeeStatus.getId() == null))
				/*
				 * || (employeeStatusRequest.getTransactionType().equalsIgnoreCase(DELETE) &&
				 * employeeStatus.getId() == null
				 */) {
					logger.error("Request is not valid, No id provided");
					ErrorResponse errorResponse = new ErrorResponse();
					errorResponse.setStatusCode("422");
					errorResponse.setMessage("Id must not be null");
					return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
				}
			}
			return employeeStatusFacade.setEmployeeStatus(employeeStatusRequest);

		} catch (Exception e) {
			logger.error("inside catch block.*******");
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setStatusCode("422");
			errorResponse.setMessage(e.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@RequestMapping(GET)
	public ResponseEntity<Object> getEmployeeStatus(@RequestBody EmployeeStatusRequest employeeStatusRequest,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		logger.debug("incoming requests " + employeeStatusRequest);
		try {
			logger.info("requestObject received = " + employeeStatusRequest);
			if (!employeeStatusRequest.getTransactionType().equalsIgnoreCase(GETALL)) {
				logger.error("Request is not valid, No id provided");
				ErrorResponse errorResponse = new ErrorResponse();
				errorResponse.setStatusCode("422");
				errorResponse.setMessage("Type must be getall");
				return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			return employeeStatusFacade.getEmployeeStatus();
		} catch (Exception e) {
			logger.error("inside catch block.*******");
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setStatusCode("422");
			errorResponse.setMessage(e.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}