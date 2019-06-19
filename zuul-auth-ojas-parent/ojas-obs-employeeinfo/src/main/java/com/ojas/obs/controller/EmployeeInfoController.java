package com.ojas.obs.controller;

import static com.ojas.obs.constants.URLconstants.GET;
import static com.ojas.obs.constants.URLconstants.SET;
import static com.ojas.obs.constants.URLconstants.EMPLOYEEINFO;
import static com.ojas.obs.constants.UserConstants.DELETE;
import static com.ojas.obs.constants.UserConstants.SAVE;
import static com.ojas.obs.constants.UserConstants.UPDATE;
import static com.ojas.obs.constants.UserConstants.EXCEPTION;
import static com.ojas.obs.constants.UserConstants.SQLEXCEPTION;


import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.obs.errorResponse.ErrorResponse;
import com.ojas.obs.facade.EmployeeInfoFacade;
import com.ojas.obs.model.EmployeeInfo;
import com.ojas.obs.request.EmployeeInfoRequest;

/**
 * 
 * @author sdileep
 *
 */

@RestController
//@RequestMapping(EMPLOYEEINFO)
public class EmployeeInfoController {

	@Autowired
	EmployeeInfoFacade employeeInfoFacade;

	Logger logger = Logger.getLogger(this.getClass());
	String transactionType = null;

	/**
	 * 
	 * @param employeeInfoRequest
	 * @param servletRequest
	 * @param servletResponse
	 * @return
	 * @throws SQLException
	 */

	@RequestMapping(SET)
	public ResponseEntity<Object> setEmployeeInfo(@RequestBody EmployeeInfoRequest employeeInfoRequest,
			HttpServletRequest servletRequest, HttpServletResponse servletResponse) {

		try {

			if (null == employeeInfoRequest) {
				ErrorResponse error = new ErrorResponse();
				logger.debug("data is not valid");
				error.setStatusCode("422");
				error.setMessage("Request is not valid");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			List<EmployeeInfo> empInfolist = employeeInfoRequest.getEmployeeInfo();
			if (null == empInfolist) {
				ErrorResponse error = new ErrorResponse();
				error.setMessage("data must not be null");
				error.setStatusCode("422");
				logger.debug("data is not valid");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			transactionType = employeeInfoRequest.getTransactionType();
			for (EmployeeInfo empInfo : empInfolist) {
				if (transactionType.equalsIgnoreCase(SAVE)
						&& ((null == empInfo.getFirstname() || empInfo.getFirstname().isEmpty())
								|| (null == empInfo.getMiddlename() || empInfo.getMiddlename().isEmpty())
								|| (null == empInfo.getLastname() || empInfo.getLastname().isEmpty())
								|| (null == empInfo.getStatus()) || (null == empInfo.getGender())
								|| (null == empInfo.getDob() || empInfo.getDob().isEmpty())
								|| (null == empInfo.getTitle()) || null == empInfo.getEmployeeId()
								|| empInfo.getEmployeeId().isEmpty())) {
					ErrorResponse error = new ErrorResponse();
					logger.debug("data is  invalid");
					error.setMessage("Request is  invalid");
					error.setStatusCode("422");
					return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
				}

				if ((transactionType.equalsIgnoreCase(UPDATE) && null == empInfo.getId())
						|| (transactionType.equalsIgnoreCase(DELETE) && null == empInfo.getId())) {
					ErrorResponse errorResponse = new ErrorResponse();
					errorResponse = new ErrorResponse();
					errorResponse.setStatusCode("422");
					errorResponse.setMessage("Id must not be null");
					logger.info("Request is not valid, No id provided");
					return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
				}
			}
			return employeeInfoFacade.setEmployeeInfo(employeeInfoRequest);
		} catch (DuplicateKeyException e) {
			logger.debug("DuplicateKeyException caught!");
			ErrorResponse error = new ErrorResponse();
			error.setStatusCode("409");
			error.setMessage("Duplicates are not allowed!");
			error.setStatusMessage(e.getCause().getLocalizedMessage());
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		} catch (SQLException e) {
			logger.debug("SQLException caught!");
			ErrorResponse error = new ErrorResponse();
			error.setStatusCode("409");
			error.setMessage(SQLEXCEPTION);
			error.setStatusMessage(e.getMessage());
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		} catch (Exception e) {
			logger.debug("Exception caught!");
			ErrorResponse error = new ErrorResponse();
			error.setStatusCode("409");
			error.setMessage(EXCEPTION);
			error.setStatusMessage(e.getMessage());
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}
	}

	/**
	 * 
	 * @param employeeInfoRequest
	 * @param servletRequest
	 * @param servletResponse
	 * @return
	 */

	@RequestMapping(GET)
	public ResponseEntity<Object> getEmpDetails(@RequestBody EmployeeInfoRequest employeeInfoRequest,
			HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		try {
			if (null == employeeInfoRequest) {

				ErrorResponse error = new ErrorResponse();
				logger.debug("data is not valid");
				error.setStatusCode("422");
				error.setMessage("Request is not valid");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			if (null == employeeInfoRequest.getTransactionType()
					|| employeeInfoRequest.getTransactionType().isEmpty()) {
				ErrorResponse error = new ErrorResponse();
				logger.debug("data is not valid");
				error.setStatusCode("422");
				error.setMessage("Transaction type should not be null");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			return employeeInfoFacade.getAllEmployeeDetails(employeeInfoRequest);
		} catch (SQLException exception) {
			logger.debug("SQLException caught");
			ErrorResponse error = new ErrorResponse();
			error.setStatusCode("409");
			error.setMessage(SQLEXCEPTION);
			error.setStatusMessage(exception.getMessage());
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		} catch (Exception exception) {
			logger.debug("Exception caught");
			ErrorResponse error = new ErrorResponse();
			error.setStatusCode("409");
			error.setMessage(EXCEPTION);
			error.setStatusMessage(exception.getMessage());
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}
	}
}
