package com.ojas.obs.controller;

import static com.ojas.obs.constants.Constants.GET;
import static com.ojas.obs.constants.Constants.SET;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.obs.facade.SkillFacade;
import com.ojas.obs.model.ErrorResponse;
import com.ojas.obs.request.SkillRequest;

@RestController
public class SkillController {

	@Autowired
	private SkillFacade skillFacade;
	Logger logger = Logger.getLogger(this.getClass());

	@PostMapping(SET)
	public ResponseEntity<Object> setSkill(@RequestBody SkillRequest skillRequest, HttpServletRequest request,
			HttpServletResponse response) {
logger.debug("Enter the set method controller...");
		ErrorResponse errorResponse = new ErrorResponse();
		ResponseEntity<Object> responseEntity = null;
		try {
			// checking requestobj

			if (null == skillRequest) {
				logger.debug("checkimg request object is null...");
				errorResponse.setStatusCode("422");
				errorResponse.setMessage("Request object is null");
				return new ResponseEntity<Object>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
			}

			// Checking transaction type

			if (null == skillRequest.getTransactionType() || skillRequest.getTransactionType().isEmpty()) {
				logger.debug("checking transactiontype null....");
				errorResponse.setMessage("transationType is not valid");
				errorResponse.setStatusCode("422");
				return new ResponseEntity<Object>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			responseEntity = skillFacade.setSkillInfo(skillRequest);

		} 
		catch (Exception e) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage(e.getMessage());
			responseEntity = new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}
		return responseEntity;
	}

	@PostMapping(GET)
	public ResponseEntity<Object> getSkillInfo(@RequestBody SkillRequest skillRequest, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("Enter the get method controller...");
		ErrorResponse error = new ErrorResponse();
		ResponseEntity<Object> responseEntity = null;
		try {
			// validating the request object

			if (skillRequest == null) {
				logger.debug("checkimg request object is null...");
				error.setStatusMessage("Requestobj is not valid");
				error.setStatusCode("422");
				return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			// Checking transaction type

			if (null == skillRequest.getTransactionType() || skillRequest.getTransactionType().isEmpty()) {
				logger.debug("checkimg request object is null...");
				error.setMessage("transationType is not valid");
				error.setStatusCode("422");
				return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			responseEntity = skillFacade.getSkillInfo(skillRequest);

		}
		catch (Exception e) {
			error = new ErrorResponse();
			error.setMessage(e.getMessage());
			responseEntity = new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}
		return responseEntity;
	}

}
