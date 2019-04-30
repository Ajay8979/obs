package com.ojas.obs.controller;

import static com.ojas.obs.constants.UrlConstants.GET;
import static com.ojas.obs.constants.UrlConstants.*;
import static com.ojas.obs.constants.UrlConstants.SET;
import static com.ojas.obs.constants.UserConstants.EMPLOYEEEXPERINCEDETAILSOBJECTNULL;
import static com.ojas.obs.constants.UserConstants.REQUESTOBJECTNULL;
import static com.ojas.obs.constants.UserConstants.SESSIONIDNULL;
import static com.ojas.obs.constants.UserConstants.TRANSACTIONTYPENULL;

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

import com.ojas.obs.facade.ExperienceFacade;
import com.ojas.obs.model.ErrorResponse;
import com.ojas.obs.request.ExperienceRequest;
import com.ojas.obs.response.ExperienceResponse;
//@RequestMapping(EMPLOYEEEXPERIENCEDETAILS)
@RestController
public class ExprienceController {

	ResponseEntity<Object> responseEntity = null;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	ExperienceFacade experienceFacade;

	@PostMapping(value = SET)
	public ResponseEntity<Object> setEmployeeExprienceDetails(@RequestBody ExperienceRequest experienceRequest,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		logger.debug(" Received input data in EmployeeExperienceDetailssController :" + experienceRequest);
		// EmployeeExperienceDetails employeeExperienceDetails =
		// employeeExperienceDetailsRequest.getEmployeeExperienceDetails();
		try {
			if (experienceRequest == null) {
				ErrorResponse error = new ErrorResponse();
				error.setStatusCode("422");
				error.setMessage(REQUESTOBJECTNULL);
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			if (experienceRequest.getSessionId() == null) {
				ErrorResponse error = new ErrorResponse();
				error.setStatusCode("422");
				error.setMessage(SESSIONIDNULL);
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}

			if (experienceRequest.getTransactionType() == null) {
				ErrorResponse error = new ErrorResponse();
				error.setStatusCode("422");
				error.setMessage(TRANSACTIONTYPENULL);
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			if (experienceRequest.getEmployeeExperienceDetails() == null) {
				ErrorResponse error = new ErrorResponse();
				error.setStatusCode("422");
				error.setMessage(EMPLOYEEEXPERINCEDETAILSOBJECTNULL);
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}

			ExperienceResponse setEmployeeExperienceDetails = experienceFacade
					.setEmployeeExperienceDetails(experienceRequest);
			responseEntity = new ResponseEntity<>(setEmployeeExperienceDetails, HttpStatus.OK);
		} 
			  catch (SQLException e) { ErrorResponse error = new ErrorResponse();
			  error.setStatusCode(String.valueOf(e.getErrorCode()));
			  error.setMessage("sql exception"); error.setStatusMessage(e.getMessage());
			  responseEntity = new ResponseEntity<>(error, HttpStatus.CONFLICT); }
			 

		catch (Exception e) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage(e.getMessage());
			responseEntity = new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}

		return responseEntity;
	}

	@PostMapping(value = GET)
	public ResponseEntity<Object> getEmployeeExprienceDetails(@RequestBody ExperienceRequest experienceRequest,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		logger.debug(" Received input data in EmployeeExperienceDetailssController :" + experienceRequest);
		try {
		if (experienceRequest == null) {
			ErrorResponse error = new ErrorResponse();
			error.setStatusCode("422");
			error.setMessage(REQUESTOBJECTNULL);
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if (experienceRequest.getSessionId() == null) {
			ErrorResponse error = new ErrorResponse();
			error.setStatusCode("422");
			error.setMessage(SESSIONIDNULL);
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}

		if (experienceRequest.getTransactionType() == null) {
			ErrorResponse error = new ErrorResponse();
			error.setStatusCode("422");
			error.setMessage(TRANSACTIONTYPENULL);
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}

		

			ExperienceResponse setEmployeeExperienceDetails = experienceFacade
					.getEmployeeExperienceDetails(experienceRequest);
			responseEntity = new ResponseEntity<>(setEmployeeExperienceDetails, HttpStatus.OK);
		} 
			  catch (SQLException e) { ErrorResponse error = new ErrorResponse();
			  error.setStatusCode(String.valueOf(e.getErrorCode()));
			  error.setMessage("sql exception"); error.setStatusMessage(e.getMessage());
			  responseEntity = new ResponseEntity<>(error, HttpStatus.CONFLICT); }
			  catch (Exception e) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage(e.getMessage());
			responseEntity = new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}
		return responseEntity;
	}

}
