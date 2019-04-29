package com.ojas.obs.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.obs.error.ErrorResponse;
import com.ojas.obs.facade.EmployeeEducationFacade;
import com.ojas.obs.modelrequest.EmployeeEducationRequest;

/**
 * 
 * @author mpraneethguptha
 *
 */
@RestController
//@RequestMapping("/obseducationdetails")
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
		ResponseEntity<Object> responseEntity = null;
		if(null == employeeEducationRequestObject) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage("please  check Request objecct");
			error.setStatusCode("422");
			error.setStatusMessage("Request objecct is null");
			return new ResponseEntity<>(error,HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if(null == employeeEducationRequestObject.getTransactionType()) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage("please  check Request transactiontype");
			error.setStatusCode("422");
			error.setStatusMessage("Transaction type is null");
			return new ResponseEntity<>(error,HttpStatus.UNPROCESSABLE_ENTITY); 
		}
		if(null == employeeEducationRequestObject.getListEmployeeEducations()) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage("please  check Request objecct");
			error.setStatusCode("422");
			error.setStatusMessage("List  objecct is null");
			return new ResponseEntity<>(error,HttpStatus.UNPROCESSABLE_ENTITY); 
		}
		
		boolean  b =  ( employeeEducationRequestObject.getTransactionType().equalsIgnoreCase("save")|| employeeEducationRequestObject.getTransactionType().equalsIgnoreCase("update") || employeeEducationRequestObject.getTransactionType().equalsIgnoreCase("delete"));
		
		if ( !(b || employeeEducationRequestObject.getTransactionType().equalsIgnoreCase("create")) ) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage("please  check Request Transaction Type");
			error.setStatusCode("422");
			error.setStatusMessage("Transaction Typeis null");
			return new ResponseEntity<>(error,HttpStatus.UNPROCESSABLE_ENTITY); 
		}
		try {
			 responseEntity = employeeEducationFacade.setEmployeeEducationInfo(employeeEducationRequestObject);
		}catch(DuplicateKeyException dke) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage("duplicates are not alowed");
			error.setStatusCode("422");
			error.setStatusMessage(dke.getMessage());
			responseEntity = new ResponseEntity<>(error,HttpStatus.UNPROCESSABLE_ENTITY);
		}catch (Exception e) {
			ErrorResponse error = new ErrorResponse();
			error.setStatusCode("409");
			error.setStatusMessage(e.getCause().getMessage());
			responseEntity = new ResponseEntity<>(error,HttpStatus.UNPROCESSABLE_ENTITY);
		
		}
		
		return responseEntity;
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

		ResponseEntity<Object> responseEntity = null;
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
	}
}
