package com.ojas.obs.controller;

import static com.ojas.obs.constants.SeparationTypeConstants.DELETE;
import static com.ojas.obs.constants.SeparationTypeConstants.GET;
import static com.ojas.obs.constants.SeparationTypeConstants.SET;
import static com.ojas.obs.constants.SeparationTypeConstants.UPDATE;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import com.ojas.model.Designation;

//port com.google.gson.Gson;

import com.ojas.obs.constants.SeparationTypeConstants;
import com.ojas.obs.facade.SeparationTypeFacade;
import com.ojas.obs.model.SeparationType;
import com.ojas.obs.request.SeparationTypeRequest;
import com.ojas.obs.utility.ErrorResponse;

/**
 * 
 * @author nsrikanth  
 *
 */
@RestController
public class SeparationTypeController {   

	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private SeparationTypeFacade separationTypeFacade;

	/**
	 *  
	 * @param separationTypeRequest
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = SET, method = RequestMethod.POST)
	public ResponseEntity<Object> setSeparationType(@RequestBody SeparationTypeRequest separationTypeRequest,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws SQLException {
		ErrorResponse errorResponse = null; 
		String transactionType = null; 
		List<SeparationType> listSeparationType = separationTypeRequest.getSeparationType();
 
		
		try {
			if (null == listSeparationType || listSeparationType.isEmpty()) {
				errorResponse = new ErrorResponse();
				errorResponse.setStatusMessage("separationtypeRequest obj is not valid");
				errorResponse.setStatusCode("422");
				/*
				 * errorResponse.setStatusCode("422");
				 * errorResponse.setMessage(SeparationTypeConstants.INVALID_REQUEST);
				 */
				logger.info("Request is not valid"); 
				return new ResponseEntity<Object>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			for (SeparationType sep : listSeparationType) { 

				if ((separationTypeRequest.getTransactionType().equalsIgnoreCase("update")
						|| separationTypeRequest.getTransactionType().equalsIgnoreCase("delete")) && null == sep.getSeparationTypeId()) {
					ErrorResponse error = new ErrorResponse();
					logger.debug("data is  invalid");
					error.setStatusMessage("id can't be null");
					error.setStatusCode("422");
					return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
				}
			}
			return separationTypeFacade.setSeparationTypeDetails(separationTypeRequest);

		} catch (Exception exception) {
			logger.debug("inside catch block.*******");
			ErrorResponse error = new ErrorResponse();
			error.setStatusMessage(exception.getMessage());
			error.setStatusCode("409");
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}

	}

		
		
		
		
	/*
	 * for (SeparationType sep : listSeparationType) { if (null ==
	 * separationTypeRequest.getSeparationType() ||
	 * separationTypeRequest.getSeparationType().isEmpty()) { errorResponse = new
	 * ErrorResponse(); errorResponse.setStatusMessage("422");
	 * errorResponse.setStatusMessage(SeparationTypeConstants.INVALID_ENTITY);
	 * logger.info("Request is not valid, Invalid entity"); return new
	 * ResponseEntity<Object>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY); } if
	 * (null == separationTypeRequest.getTransactionType()) { errorResponse = new
	 * ErrorResponse(); errorResponse.setStatusCode("422");
	 * errorResponse.setMessage(SeparationTypeConstants.INVALID_TRANSACTION_TYPE);
	 * logger.info("Request is not valid, Invalid transaction type"); return new
	 * ResponseEntity<Object>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY); } if
	 * (null == separationTypeRequest.getSessionId()) { errorResponse = new
	 * ErrorResponse(); errorResponse.setStatusCode("422");
	 * errorResponse.setMessage(SeparationTypeConstants.INVALID_SESSION_ID);
	 * logger.info("Request is not valid, Invalid session id"); return new
	 * ResponseEntity<Object>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY); }
	 * transactionType = separationTypeRequest.getTransactionType(); if
	 * ((transactionType.equalsIgnoreCase(UPDATE) ||
	 * transactionType.equalsIgnoreCase(DELETE)) && null ==
	 * sep.getSeparationTypeId()) {
	 * 
	 * errorResponse = new ErrorResponse(); errorResponse.setStatusCode("422");
	 * errorResponse.setMessage(SeparationTypeConstants.INVALID_ID);
	 * logger.info("Request is not valid, No id provided"); return new
	 * ResponseEntity<Object>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
	 * 
	 * } } return
	 * separationTypeFacade.setSeparationTypeDetails(separationTypeRequest); }
	 */

	/**
	 * 
	 * @param separationTypeRequest
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = GET, method = RequestMethod.POST)
	public ResponseEntity<Object> getAllSeparationType(@RequestBody SeparationTypeRequest separationTypeRequest,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws SQLException {
		logger.info("Inside getAllSeparationType()");
		ErrorResponse errorResponse = null; 
		// Gson gson = new Gson();
		// SeparationTypeRequest separationTypeReq =
		// gson.fromJson(separationTypeRequest, SeparationTypeRequest.class);
		if (null == separationTypeRequest) {
			errorResponse = new ErrorResponse();
			errorResponse.setStatusCode("422");
			errorResponse.setMessage(SeparationTypeConstants.INVALID_REQUEST);
			logger.info("Request is not valid");
			return new ResponseEntity<Object>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY); 
		}
		if (null == separationTypeRequest.getSessionId()) {
			errorResponse = new ErrorResponse();
			errorResponse.setStatusCode("422");
			errorResponse.setMessage(SeparationTypeConstants.INVALID_SESSION_ID);
			logger.info("Request is not valid, Invalid session id");
			return new ResponseEntity<Object>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return separationTypeFacade.getSeparationType(separationTypeRequest);
	}

}
