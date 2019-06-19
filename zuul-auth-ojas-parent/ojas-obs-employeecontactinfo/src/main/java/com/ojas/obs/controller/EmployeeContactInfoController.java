package com.ojas.obs.controller;

import static com.ojas.obs.constants.UserConstants.GET;
import static com.ojas.obs.constants.UserConstants.SET;

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
import org.springframework.web.bind.annotation.RestController;

import com.ojas.obs.facade.EmployeeStatusFacade;
import com.ojas.obs.model.EmployeeContactInfo;
import com.ojas.obs.requests.EmployeeContactInfoRequest;
import com.ojas.obs.response.ErrorResponse;

/**
 * 
 * @author ksaiKrishna
 * 
 */

@RestController
//@RequestMapping("/employeecontactinfo")
public class EmployeeContactInfoController {

	@Autowired
	private EmployeeStatusFacade empFacade;

	Logger logger = Logger.getLogger(this.getClass());

	// service to save,update or delete employeeContactInfo
	@PostMapping(SET)
	public ResponseEntity<Object> setEmployeeContactInfo(
			@RequestBody EmployeeContactInfoRequest employeeContactInfoRequest, HttpServletRequest request,
			HttpServletResponse response) {
		ErrorResponse errorResponse = new ErrorResponse();
		logger.info("The request inside controller set method " + employeeContactInfoRequest);
		if (employeeContactInfoRequest == null) {
			logger.error("List feild is null " + employeeContactInfoRequest);
			errorResponse.setStatusMessage("Request object is null");
			errorResponse.setStatusCode("422");
			return new ResponseEntity<Object>(errorResponse, HttpStatus.UNAUTHORIZED);
		} else if (employeeContactInfoRequest.getTransactionType() == null
				|| employeeContactInfoRequest.getTransactionType().equalsIgnoreCase("")) {
			logger.error("Request does not contain transactiontype");
			ErrorResponse err = new ErrorResponse();
			err.setStatusMessage("Transaction Type is null");
			err.setStatusCode("422");
			return new ResponseEntity<Object>(err, HttpStatus.UNAUTHORIZED);
		} else if (employeeContactInfoRequest.getTransactionType().equalsIgnoreCase("save")
				|| employeeContactInfoRequest.getTransactionType().equalsIgnoreCase("update")) {

			for (EmployeeContactInfo empRequest : employeeContactInfoRequest.getEmpInfo()) {
				if (((empRequest.getEmail() == null) || (empRequest.getEmail().isEmpty()))
						|| ((empRequest.getPersonalMobileNo() == null)
								|| (empRequest.getPersonalMobileNo().length() != 10)
								|| (empRequest.getPersonalMobileNo().isEmpty()))
						|| ((empRequest.getAlternateMobileNo() == null)
								|| (empRequest.getAlternateMobileNo().length() != 10)
								|| (empRequest.getAlternateMobileNo().isEmpty()))
						|| ((empRequest.getCurrentAddressLine1() == null)
								|| (empRequest.getCurrentAddressLine1().isEmpty()))
						|| ((empRequest.getCurrentAddressLine2() == null)
								|| (empRequest.getCurrentAddressLine2().isEmpty()))
						|| ((empRequest.getCurrentCity() == null) || (empRequest.getCurrentCity().isEmpty()))
						|| (empRequest.getCurrentState() == null) || (empRequest.getCurrentPin() == null)
						|| ((empRequest.getEmpId() == null) || (empRequest.getEmpId()).isEmpty())
						|| ((empRequest.getPermanentAddressLine1() == null)
								|| (empRequest.getPermanentAddressLine1().isEmpty()))) {

					errorResponse.setStatusCode("422");
					errorResponse.setStatusMessage("Improper save data");
					return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
				}
			}
		}  if (employeeContactInfoRequest.getTransactionType().equalsIgnoreCase("update")
				|| employeeContactInfoRequest.getTransactionType().equalsIgnoreCase("delete")) {
			for (EmployeeContactInfo empRequest : employeeContactInfoRequest.getEmpInfo()) {
				if (empRequest.getId() == null) {
					errorResponse.setStatusCode("422");
					errorResponse.setMessage("Id is not given");
					return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
				}
			}

		}
		try {

			return empFacade.setEmployeeContactInfo(employeeContactInfoRequest);
		} catch (DuplicateKeyException exception) {
			errorResponse.setStatusCode("409");
			errorResponse.setMessage("duplicate's are not allowed");
			errorResponse.setStatusMessage(exception.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
		} catch (SQLException exception) {
			errorResponse.setStatusCode("409");
			errorResponse.setMessage("duplicate's are not allowed");
			errorResponse.setStatusMessage(exception.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
		} catch (Exception exception) {
			logger.error("Inside catch block");
			errorResponse.setStatusCode("409");
			errorResponse.setMessage("Request is Invalid");
			errorResponse.setStatusMessage(exception.getMessage());
			logger.error("Exception raised");
			return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
		}
	}

	// Service to get EmployeeContactInfo
	@PostMapping(GET)
	public ResponseEntity<Object> getEmployeeContactInfo(@RequestBody EmployeeContactInfoRequest employeeContactInfoRequest, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("The request details in service get method" + employeeContactInfoRequest);
		try {
			ErrorResponse errorResponse = new ErrorResponse();
			if (employeeContactInfoRequest == null) {
				logger.error("The request object is null");

				errorResponse.setMessage("Request is null");
				errorResponse.setStatusCode("422");
				return new ResponseEntity<Object>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
			} else if (!employeeContactInfoRequest.getTransactionType().equalsIgnoreCase("getAll")) {
				errorResponse.setMessage("transactionType not valid");
				errorResponse.setStatusCode("422");
				return new ResponseEntity<Object>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			return empFacade.getEmployeeContactInfo(employeeContactInfoRequest);

		} catch (DuplicateKeyException exception) {
			ErrorResponse error = new ErrorResponse();
			error.setStatusCode("409");
			error.setMessage("duplicate's are not allowed");
			error.setStatusMessage(exception.getMessage());
			return new ResponseEntity<Object>(error, HttpStatus.CONFLICT);
		} catch (SQLException exception) {
			ErrorResponse error = new ErrorResponse();
			error.setStatusCode("409");
			error.setMessage("duplicate's are not allowed");
			error.setStatusMessage(exception.getMessage());
			return new ResponseEntity<Object>(error, HttpStatus.CONFLICT);
		} catch (Exception exception) {
			logger.error("Inside catch block");
			ErrorResponse error = new ErrorResponse();
			error.setStatusCode("409");
			error.setMessage("Request is Invalid");
			error.setStatusMessage(exception.getMessage());
			logger.error("Exception raised");
			return new ResponseEntity<Object>(error, HttpStatus.CONFLICT);
		}
	}
}
