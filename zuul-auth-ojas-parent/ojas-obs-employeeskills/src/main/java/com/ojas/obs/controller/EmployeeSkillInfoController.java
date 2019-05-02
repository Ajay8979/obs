package com.ojas.obs.controller;


import static com.ojas.obs.constants.Constants.GET;
import static com.ojas.obs.constants.Constants.SET;

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

import com.ojas.obs.facade.EmployeeSkillFacade;
import com.ojas.obs.model.EmployeeSkillInfo;
import com.ojas.obs.model.EmployeeSkillInfoRequest;
import com.ojas.obs.utility.ErrorResponse;

@RestController
public class EmployeeSkillInfoController {

	@Autowired
	private EmployeeSkillFacade employeeSkillService;

	Logger logger = Logger.getLogger(this.getClass());

	@PostMapping(SET)
	public ResponseEntity<Object> setEmployeeSkillInfo(@RequestBody EmployeeSkillInfoRequest employeeSkillInfoRequest,
			HttpServletRequest request, HttpServletResponse response) {

		ResponseEntity<Object> responseEntity = null;
		ErrorResponse error = new ErrorResponse();
		logger.debug("Enter the controller set method...");

		// validating the request object

		if (employeeSkillInfoRequest == null) {
			logger.debug("checking object is null or not...");
			error.setStatusMessage("Requestobj is not valid");
			error.setStatusCode("422");
			return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}

		// Checking transaction type

		if (null == employeeSkillInfoRequest.getTransactionType()
				|| employeeSkillInfoRequest.getTransactionType().isEmpty()) {
			logger.debug("checking transactiontype equal to null or empty...");
			error.setMessage("transationType is not valid");
			error.setStatusCode("422");
			return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}

		// checking the model object

		List<EmployeeSkillInfo> listEmployeeSkillInfo = employeeSkillInfoRequest.getSkillInfoModel();

		if (listEmployeeSkillInfo.isEmpty() || listEmployeeSkillInfo == null) {
			error = new ErrorResponse();
			error.setMessage("Data must not be null");
			error.setStatusCode("422");
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		for (EmployeeSkillInfo skillDetails : listEmployeeSkillInfo) {
			if (employeeSkillInfoRequest.getTransactionType().equalsIgnoreCase("UPDATE")) {
				if ((null == skillDetails.getUpdate_by()) && (skillDetails.getEmployee_id() == null)) {
					error = new ErrorResponse();
					error.setMessage("Data must not be null");
					error.setStatusCode("422");
					return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
				}
			}
			if (employeeSkillInfoRequest.getTransactionType().equalsIgnoreCase("SAVE")) {
				if (null == skillDetails.getCreated_by()) {
					error = new ErrorResponse();
					error.setMessage("Data must not be null");
					error.setStatusCode("422");
					return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
				}
			}
		}
		try {
			responseEntity = employeeSkillService.setEmployeeSkillInfo(employeeSkillInfoRequest);
		} catch (SQLException se) {
			error.setMessage("duplicates are not alowed");
			error.setStatusCode("422");
			error.setStatusMessage(se.getMessage());
			responseEntity = new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (Exception e) {
			error.setStatusCode("409");
			error.setStatusMessage(e.getCause().getMessage());
			responseEntity = new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return responseEntity;

	}

	@PostMapping(GET)
	public ResponseEntity<Object> getEmployeeSkillInfo(@RequestBody EmployeeSkillInfoRequest employeeSkillInfoRequest,
			HttpServletRequest request, HttpServletResponse response) {

		ErrorResponse error = new ErrorResponse();
		logger.debug("Enter the controller get method...");

		// validating the request object
		
		try {
			if (employeeSkillInfoRequest == null) {
				error.setStatusMessage("Requestobj is not valid");
				error.setStatusCode("422");
				return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			
			
			// Checking transaction type
			
			if (null == employeeSkillInfoRequest.getTransactionType()
					|| employeeSkillInfoRequest.getTransactionType().isEmpty()) {
				error.setMessage("transationType is not valid");
				error.setStatusCode("422");
				return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}

			return employeeSkillService.getEmployeeSkillInfo(employeeSkillInfoRequest);

		} catch (Exception exception) {
			logger.debug("inside catch block.*******");
			error.setStatusMessage(exception.getMessage());
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}
