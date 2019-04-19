package com.ojas.obs.controller;

import static com.ojas.obs.constants.URLConstants.DEPENDENTDETAILSSERVICE;
import static com.ojas.obs.constants.URLConstants.GET;
import static com.ojas.obs.constants.URLConstants.SET;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ojas.obs.model.ErrorResponse;
import com.ojas.obs.request.DependentDetailsRequest;
import com.ojas.obs.response.DependentDetailsResponse;
import com.ojas.obs.service.DependentDetailsService;

/**
 * 
 * @author srinukummari
 *
 */

@RestController
public class DependentDetailsController {
	Logger logger = Logger.getLogger(this.getClass());
	ResponseEntity<Object> responseEntity = null;
	@Autowired
	DependentDetailsService dependentDetailsServiceImpl;

	@PostMapping(value = SET)
	public ResponseEntity<Object> setDependentDetails(@RequestBody DependentDetailsRequest dependentDetailsrequest,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		logger.debug("received input data is in controller" + dependentDetailsrequest);
		
		if (dependentDetailsrequest == null) {
			ErrorResponse error = new ErrorResponse();
			error.setStatusCode("422");
			error.setMessage("Request Object is Null");
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if (null == dependentDetailsrequest.getSessionId()) {
			ErrorResponse error = new ErrorResponse();
			error.setStatusCode("422");
			error.setMessage("Session Id is Null");
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if (null == dependentDetailsrequest.getDependentDetails()) {
			ErrorResponse error = new ErrorResponse();
			error.setStatusCode("422");
			error.setMessage("DependentDetails Object is Null");
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if (null == dependentDetailsrequest.getTransactionType()) {
			ErrorResponse error = new ErrorResponse();
			error.setStatusCode("422");
			error.setMessage("Transaction Type is Null");
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		try {
			DependentDetailsResponse setDependentDetails = dependentDetailsServiceImpl
					.setDependentDetails(dependentDetailsrequest);
			return new ResponseEntity<>(setDependentDetails, HttpStatus.OK);
		} catch (SQLException exception) {
			exception.printStackTrace();
			ErrorResponse error = new ErrorResponse();
			error.setStatusCode(String.valueOf(exception.getErrorCode()));
			error.setMessage("sql exception");
			error.setStatusMessage(exception.getMessage());
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		} catch (Exception e) {
			e.printStackTrace();
			ErrorResponse error = new ErrorResponse();
			error.setMessage(e.getMessage());
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}

	}
	
	/**
	 * 
	 * @param dependentDetailsRequest
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	@PostMapping(value = GET)
	public ResponseEntity<Object> getDependentDetails(@RequestBody DependentDetailsRequest dependentDetailsRequest,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		if (dependentDetailsRequest == null) {
			ErrorResponse error = new ErrorResponse();
			error.setStatusCode("422");
			error.setMessage("Request Object is Null");
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if (null == dependentDetailsRequest.getSessionId()) {
			ErrorResponse error = new ErrorResponse();
			error.setStatusCode("422");
			error.setMessage("Session Id is Null");
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if (null == dependentDetailsRequest.getDependentDetails()) {
			ErrorResponse error = new ErrorResponse();
			error.setStatusCode("422");
			error.setMessage("DependentDetails Object is Null");
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if (null == dependentDetailsRequest.getTransactionType()) {
			ErrorResponse error = new ErrorResponse();
			error.setStatusCode("422");
			error.setMessage("Transaction Type is Null");
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		try {
			DependentDetailsResponse setDependentDetails = dependentDetailsServiceImpl
					.getDependentDetails(dependentDetailsRequest);
			responseEntity = new ResponseEntity<>(setDependentDetails, HttpStatus.OK);
		} catch (Exception e) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage(e.getMessage());
			responseEntity = new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}
		return responseEntity;
	}

}
