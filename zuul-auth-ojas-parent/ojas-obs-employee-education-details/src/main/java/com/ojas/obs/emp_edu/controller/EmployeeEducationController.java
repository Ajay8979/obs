package com.ojas.obs.emp_edu.controller;

import com.ojas.obs.emp_edu.facade.EmployeeEducationFacade;
import com.ojas.obs.emp_edu.model.EmployeeEducationDetailsRequest;
import com.ojas.obs.emp_edu.model.ErrorResponse;
import static com.ojas.obs.emp_edu.utility.Constants.SET;
import static com.ojas.obs.emp_edu.utility.Constants.GET;
import static com.ojas.obs.emp_edu.utility.Constants.TRANSACTIONTYPE_NULL;
import static com.ojas.obs.emp_edu.utility.Constants.REQUEST_NULL;
import static com.ojas.obs.emp_edu.utility.Constants.OBJECT_NULL;
import static com.ojas.obs.emp_edu.utility.Constants.PAGE_NO_NULL;
import static com.ojas.obs.emp_edu.utility.Constants.PAGE_SIZE_NULL;
import static com.ojas.obs.emp_edu.utility.Constants.EXCEPTION;
import static com.ojas.obs.emp_edu.utility.Constants.SQL_EXCEPTION;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeEducationController {
	@Autowired
	EmployeeEducationFacade employeeEducationFacade;

	ResponseEntity<Object> responseEntity = null;

	@PostMapping(value = SET)
	public ResponseEntity<Object> setEmployeeEducationDeatails(@RequestBody EmployeeEducationDetailsRequest  emplEduDetailsRequestObj,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (null == emplEduDetailsRequestObj) {
			ErrorResponse error = new ErrorResponse();
			error.setStatuscode("422");
			error.setStatusMessage(REQUEST_NULL);
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}

		if (null == emplEduDetailsRequestObj.getEmployeeEducationDetailsList()) {
			ErrorResponse error = new ErrorResponse();
			error.setStatuscode("422");
			error.setStatusMessage(OBJECT_NULL);
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if (null == emplEduDetailsRequestObj.getPageSize()) {
			ErrorResponse error = new ErrorResponse();
			error.setStatuscode("422");
			error.setStatusMessage(PAGE_SIZE_NULL);
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if (null == emplEduDetailsRequestObj.getPageNo()) {
			ErrorResponse error = new ErrorResponse();
			error.setStatuscode("422");
			error.setStatusMessage(PAGE_NO_NULL);
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if (null == emplEduDetailsRequestObj.getTransaactionType()
				|| emplEduDetailsRequestObj.getTransaactionType().isEmpty()) {
			ErrorResponse error = new ErrorResponse();
			error.setStatuscode("422");
			error.setStatusMessage(TRANSACTIONTYPE_NULL);
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		try {
			responseEntity = employeeEducationFacade.setEmployeeEducationDetails(emplEduDetailsRequestObj);
			return responseEntity;
		 } catch (SQLException se) {
			ErrorResponse error = new ErrorResponse();
			error.setStatuscode(String.valueOf(se.getErrorCode()));
			error.setStatusMessage(se.getMessage());
			error.setMessage(SQL_EXCEPTION);
			responseEntity = new ResponseEntity<Object>(error, HttpStatus.CONFLICT);
		} catch (Exception e) {
			ErrorResponse error = new ErrorResponse();
			error.setStatuscode("409");
			error.setStatusMessage(e.getMessage());
			error.setMessage(EXCEPTION);
			responseEntity = new ResponseEntity<Object>(error, HttpStatus.CONFLICT);
		}
		return responseEntity;

	}

	@PostMapping(value = GET)
	public ResponseEntity<Object> getEmployeeEducationDetails(@RequestBody EmployeeEducationDetailsRequest emplEduDetailsRequestObj,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
		if (null == emplEduDetailsRequestObj) {
			ErrorResponse error = new ErrorResponse();
			error.setStatuscode("422");
			error.setStatusMessage(REQUEST_NULL);
			return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if (null == emplEduDetailsRequestObj.getPageNo()) {
			ErrorResponse error = new ErrorResponse();
			error.setStatuscode("422");
			error.setStatusMessage(PAGE_NO_NULL);
			return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if (null == emplEduDetailsRequestObj.getPageSize()) {
			ErrorResponse error = new ErrorResponse();
			error.setStatuscode("422");
			error.setStatusMessage(PAGE_SIZE_NULL);
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if (null == emplEduDetailsRequestObj.getTransaactionType()
				|| emplEduDetailsRequestObj.getTransaactionType().isEmpty()) {
			ErrorResponse error = new ErrorResponse();
			error.setStatuscode("422");
			error.setStatusMessage(TRANSACTIONTYPE_NULL);
			return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if (null == emplEduDetailsRequestObj.getTransaactionType()) {
			ErrorResponse error = new ErrorResponse();
			error.setStatuscode("422");
			error.setStatusMessage(TRANSACTIONTYPE_NULL);
			return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		 try {
			responseEntity = employeeEducationFacade.getEmployeeEducationDetails(emplEduDetailsRequestObj);
		return responseEntity;
	 } catch (SQLException se) {
		ErrorResponse error = new ErrorResponse();
		error.setStatuscode(String.valueOf(se.getErrorCode()));
		error.setStatusMessage(se.getMessage());
		error.setMessage(SQL_EXCEPTION);
		responseEntity = new ResponseEntity<Object>(error, HttpStatus.CONFLICT);
	} catch (Exception e) {
		ErrorResponse error = new ErrorResponse();
		error.setStatuscode("409");
		error.setStatusMessage(e.getMessage());
		error.setMessage(EXCEPTION);
		responseEntity = new ResponseEntity<Object>(error, HttpStatus.CONFLICT);
	}
		return responseEntity;
	}

	
}
