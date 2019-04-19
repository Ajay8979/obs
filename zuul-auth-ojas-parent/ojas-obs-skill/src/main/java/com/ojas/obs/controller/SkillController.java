package com.ojas.obs.controller;

import static com.ojas.obs.constants.Constants.SET;


import java.sql.SQLException;

import static com.ojas.obs.constants.Constants.GET;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.obs.facade.SkillFacade;
import com.ojas.obs.model.ErrorResponse;
import com.ojas.obs.request.SkillRequest;

@RestController
public class SkillController {

	
	@Autowired
	private SkillFacade skillFacade;
	
	@PostMapping(SET)
	public ResponseEntity<Object> setSkill(@RequestBody SkillRequest skillRequest, HttpServletRequest request,
			HttpServletResponse response) {

	
		ErrorResponse errorResponse = new ErrorResponse();

		// checking requestobj

		if (null == skillRequest) {
			errorResponse.setStatusCode("422");
			errorResponse.setMessage("Request object is null");
			return new ResponseEntity<Object>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
		}

		// Checking transaction type

		if (null == skillRequest.getTransactionType() || skillRequest.getTransactionType().isEmpty()) {
			errorResponse.setMessage("transationType is not valid");
			errorResponse.setStatusCode("422");
			return new ResponseEntity<Object>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
			
				return skillFacade.setSkillInfo(skillRequest);
	}
	

	@PostMapping(GET)
	public ResponseEntity<Object> getSkillInfo(@RequestBody SkillRequest skillRequest,
			HttpServletRequest request, HttpServletResponse response) throws SQLException {

		ErrorResponse error = new ErrorResponse();
		
		// validating the request object

		if (skillRequest == null) {
			error.setStatusMessage("Requestobj is not valid");
			error.setStatusCode("422");
			return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		// Checking transaction type

		if (null == skillRequest.getTransactionType()
				|| skillRequest.getTransactionType().isEmpty()) {
			error.setMessage("transationType is not valid");
			error.setStatusCode("422");
			return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}

		return skillFacade.getSkillInfo(skillRequest);
	}


}
