package com.ojas.obs.passport.controller;

import static com.ojas.obs.passport.utility.Constants.GET;
import static com.ojas.obs.passport.utility.Constants.PASSPOROTBJECTFIELDNULL;
import static com.ojas.obs.passport.utility.Constants.PASSPOROTBJECTNULL;
import static com.ojas.obs.passport.utility.Constants.REQUESTOBJECTNULL;
import static com.ojas.obs.passport.utility.Constants.SAVE;
import static com.ojas.obs.passport.utility.Constants.SESSIONIDNULL;
import static com.ojas.obs.passport.utility.Constants.SET;
import static com.ojas.obs.passport.utility.Constants.TRANSACTIONTYPENULL;
import static com.ojas.obs.passport.utility.Constants.UPDATE;
//import static com.ojas.obs.passport.utility.Constants.PASSPORTSERVICE;

import java.sql.SQLException;
import java.util.List;

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

import com.ojas.obs.passport.Request.PassportRequest;
import com.ojas.obs.passport.facade.PassportFacade;
import com.ojas.obs.passport.model.ErrorResponse;
import com.ojas.obs.passport.model.Passport;


@RestController
//@RequestMapping(PASSPORTSERVICE)
public class PassportController {
	Logger logger = Logger.getLogger(this.getClass());
	ResponseEntity<Object> responseEntity = null;

	@Autowired
	PassportFacade passportFacadeImpl;

	@PostMapping(value = SET)
	public ResponseEntity<Object> setPassport(@RequestBody PassportRequest passportRequestObject,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		logger.debug("received input data is in controller" + passportRequestObject);
		ErrorResponse error = new ErrorResponse();
		List<Passport> passport = passportRequestObject.getPassportList();
		try {
			
		if ((null==passportRequestObject)||(null == passportRequestObject.getSessionId())||(null == passportRequestObject.getPassportList())||(null == passportRequestObject.getTransaactionType())) {
			error.setStatusCode("422");
			error.setMessage(REQUESTOBJECTNULL);
			return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}

		
		if(passportRequestObject.getTransaactionType().equalsIgnoreCase(SAVE)) {
		for(Passport passport2:passport) {	
		if((null == passport2.getCenterName() || passport2.getCenterName().isEmpty())) {
			error.setStatusCode("422");
			error.setMessage(PASSPOROTBJECTFIELDNULL);
			return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		}
		}
	
		if(passportRequestObject.getTransaactionType().equalsIgnoreCase(UPDATE)) {
		for(Passport passport2:passport) {	
		if((null == passport2.getCenterName() || passport2.getCenterName().isEmpty())) {
			error.setStatusCode("422");
			error.setMessage(PASSPOROTBJECTFIELDNULL);
			return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		}
		}
		
		ResponseEntity<Object> setPassport = passportFacadeImpl.setPassport(passportRequestObject);
		return responseEntity = new ResponseEntity<Object>(setPassport, HttpStatus.OK);

		}
		catch (DuplicateKeyException e) { 
			
			error.setMessage("DuplicateKeyException");
			e.printStackTrace();
			error.setStatusMessage(e.getCause().getLocalizedMessage());
			responseEntity = new ResponseEntity<>(error, HttpStatus.CONFLICT);
			
		} catch (SQLException e) { 
			error.setStatusCode(String.valueOf(e.getErrorCode()));
			error.setMessage("sql exception");
			e.printStackTrace();
			error.setStatusMessage(e.getMessage());
			responseEntity = new ResponseEntity<>(error, HttpStatus.CONFLICT);
			
		} catch (Exception e) { 
			error.setMessage(e.getMessage());
			e.printStackTrace();
			responseEntity = new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}
		logger.debug("returned output data from controller" + responseEntity);
		return responseEntity;
		
	}
	
	@PostMapping(value = GET)
	public ResponseEntity<Object> getPaasport(@RequestBody PassportRequest passportRequestObject,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		logger.debug("received input data for GET in controller" + passportRequestObject);
		try {
		if (passportRequestObject == null) {
			ErrorResponse error = new ErrorResponse();
			error.setStatusCode("422");
			error.setMessage(REQUESTOBJECTNULL);
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if (null == passportRequestObject.getSessionId()) {
			ErrorResponse error = new ErrorResponse();
			error.setStatusCode("422");
			error.setMessage(SESSIONIDNULL);
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if (null == passportRequestObject.getPassportList()) {
			ErrorResponse error = new ErrorResponse();
			error.setStatusCode("422");
			error.setMessage(PASSPOROTBJECTNULL);
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if (null == passportRequestObject.getTransaactionType()) {
			ErrorResponse error = new ErrorResponse();
			error.setStatusCode("422");
			error.setMessage(TRANSACTIONTYPENULL);
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
			ResponseEntity<Object> setPassport = passportFacadeImpl.getPassport(passportRequestObject);
			responseEntity = new ResponseEntity<>(setPassport, HttpStatus.OK);
		} catch (Exception e) {
			ErrorResponse error = new ErrorResponse();
			error.setStatusCode("422");
			e.printStackTrace();
			error.setStatusMessage(e.getMessage());
			responseEntity = new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}
		logger.debug("returned output data from GET in controller" + passportRequestObject);
		return responseEntity;
	}
	
}
