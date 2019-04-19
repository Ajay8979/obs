package com.ojas.obs.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.ojas.obs.errorResponse.ErrorResponse;
import com.ojas.obs.facade.EmployeeInfoFacade;
import com.ojas.obs.model.EmployeeInfo;
import com.ojas.obs.request.EmployeeInfoRequest;

import static com.ojas.obs.constants.URLconstants.GET;
import static com.ojas.obs.constants.URLconstants.SET;
import static com.ojas.obs.constants.UserConstants.SAVE;
import static com.ojas.obs.constants.UserConstants.UPDATE;
import static com.ojas.obs.constants.UserConstants.DELETE;


/**
 * 
 * @author sdileep
 *
 */

@RestController
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
	public ResponseEntity<Object> saveEmployeeInfo(@RequestBody String employeeInfoRequest,
			HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws SQLException {

		ResponseEntity<Object> responseEntity = null;
		EmployeeInfoRequest employeeRequestObject = null;
		// String sessionId = null;
		try {
			Gson gson = new Gson();
			logger.debug("requestObject received = ");
			employeeRequestObject = gson.fromJson(employeeInfoRequest, EmployeeInfoRequest.class);

			if (null == employeeRequestObject) {

				ErrorResponse error = new ErrorResponse();
				logger.debug("data is not valid");
				error.setMessage("Request is not valid");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			// sessionId = employeeRequestObject.getSessionId();
			// logger.debug("incoming request " + sessionId);
			EmployeeInfo empInfo = employeeRequestObject.getEmployeeInfo();

			if (null == empInfo) {
				ErrorResponse error = new ErrorResponse();
				error.setMessage("data must not be null");
				logger.debug("data is not valid");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			transactionType = employeeRequestObject.getTransactionType();

			if (transactionType.equalsIgnoreCase(SAVE)

					&& ((null == empInfo.getFirstname() || empInfo.getFirstname().isEmpty())
							|| (null == empInfo.getMiddlename() || empInfo.getMiddlename().isEmpty())
							|| (null == empInfo.getLastname() || empInfo.getLastname().isEmpty())
							|| (null == empInfo.getStatus() || empInfo.getStatus().isEmpty())
							|| (null == empInfo.getGender() || empInfo.getGender().isEmpty())
							|| (null == empInfo.getDob() || empInfo.getDob().isEmpty())
							|| (null == empInfo.getStatus() || empInfo.getStatus().isEmpty())
							|| (null == empInfo.getPassword() || empInfo.getPassword().isEmpty())
							|| null == empInfo.getEmployee_id() || empInfo.getEmployee_id().isEmpty()
							|| (employeeRequestObject.getTransactionType() == null
									|| (employeeRequestObject.getTransactionType().isEmpty())))) {

				ErrorResponse error = new ErrorResponse();
				logger.debug("data is  invalid");
				error.setMessage("Request is  invalid");

				return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);

			}

			if ((transactionType.equalsIgnoreCase(UPDATE) || transactionType.equalsIgnoreCase(DELETE))
					&& null == employeeRequestObject.getEmployeeInfo().getId()) {

				ErrorResponse errorResponse = new ErrorResponse();
				errorResponse = new ErrorResponse();
				errorResponse.setStatusCode("422");
				errorResponse.setMessage("id cannot be null");

				logger.info("Request is not valid, No id provided");
				return new ResponseEntity<Object>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			responseEntity = employeeInfoFacade.saveEmployeeInfo(employeeRequestObject);
			return responseEntity;

		} catch (Exception e) {

			logger.debug("inside catch block.*******");
			ErrorResponse error = new ErrorResponse();
			error.setMessage(e.getMessage());
		}
		return responseEntity;

	}

	/**
	 * 
	 * @param employeeInfoRequest
	 * @param servletRequest
	 * @param servletResponse
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(GET)

	public ResponseEntity<Object> getEmpDetails(@RequestBody String employeeInfoRequest,
			HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws SQLException {

		ResponseEntity<Object> responseEntity = null;
		EmployeeInfoRequest employeeRequest = null;
		// String sessionId = null;
		try {
			Gson gson = new Gson();
			logger.debug("requestObject received = ");
			employeeRequest = gson.fromJson(employeeInfoRequest, EmployeeInfoRequest.class);

			if (null == employeeRequest) {

				ErrorResponse error = new ErrorResponse();
				logger.debug("data is not valid");
				error.setMessage("Request is not valid");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			// sessionId = employeeRequest.getSessionId();
			// logger.debug("incoming request " + sessionId);
			if (null == employeeRequest.getTransactionType() || employeeRequest.getTransactionType().isEmpty()) {
				ErrorResponse error = new ErrorResponse();
				logger.debug("data is not valid");
				error.setMessage("transactiontype can't be null");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			return employeeInfoFacade.getAllEmployeeDetails(employeeRequest);

		} catch (Exception exception) {
			logger.debug("inside catch block.*******");

			ErrorResponse error = new ErrorResponse();
			error.setStatusMessage(exception.getMessage());
		}
		return responseEntity;
	}

}
