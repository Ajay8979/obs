package com.ojas.obs.controller;

import static com.ojas.obs.constants.URLconstants.EMPLOYEEINFO;
import static com.ojas.obs.constants.URLconstants.GET;
import static com.ojas.obs.constants.URLconstants.SET;
import static com.ojas.obs.constants.UserConstants.DELETE;
import static com.ojas.obs.constants.UserConstants.SAVE;
import static com.ojas.obs.constants.UserConstants.UPDATE;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
			HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws SQLException {

		ResponseEntity<Object> responseEntity = null;

		try {

			if (null == employeeInfoRequest) {

				ErrorResponse error = new ErrorResponse();
				logger.debug("data is not valid");
				error.setMessage("Request is not valid");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}

			List<EmployeeInfo> empInfolist = employeeInfoRequest.getEmployeeInfo();

			if (null == empInfolist) {
				ErrorResponse error = new ErrorResponse();
				error.setMessage("data must not be null");
				logger.debug("data is not valid");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			transactionType = employeeInfoRequest.getTransactionType();

			for (EmployeeInfo empInfo : empInfolist) {

				if (transactionType.equalsIgnoreCase(SAVE)

						&& ((null == empInfo.getFirstname() || empInfo.getFirstname().isEmpty())
								|| (null == empInfo.getMiddlename() || empInfo.getMiddlename().isEmpty())
								|| (null == empInfo.getLastname() || empInfo.getLastname().isEmpty())
								|| (null == empInfo.getStatus() || empInfo.getStatus().isEmpty())
								|| (null == empInfo.getGender() || empInfo.getGender().isEmpty())
								|| (null == empInfo.getDob() || empInfo.getDob().isEmpty())
								|| (null == empInfo.getStatus() || empInfo.getStatus().isEmpty())
								|| (null == empInfo.getPassword() || empInfo.getPassword().isEmpty())
								|| null == empInfo.getEmployeeId() || empInfo.getEmployeeId().isEmpty()
								|| (employeeInfoRequest.getTransactionType() == null
										|| (employeeInfoRequest.getTransactionType().isEmpty())))) {

					ErrorResponse error = new ErrorResponse();
					logger.debug("data is  invalid");
					error.setMessage("Request is  invalid");

					return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);

				}

				if ((transactionType.equalsIgnoreCase(UPDATE) || transactionType.equalsIgnoreCase(DELETE))
						&& null == empInfo.getId()) {

					ErrorResponse errorResponse = new ErrorResponse();
					errorResponse = new ErrorResponse();
					errorResponse.setStatusCode("422");
					errorResponse.setMessage("id cannot be null");

					logger.info("Request is not valid, No id provided");
					return new ResponseEntity<Object>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
				}
			}
			responseEntity = employeeInfoFacade.setEmployeeInfo(employeeInfoRequest);
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
	public ResponseEntity<Object> getEmpDetails(@RequestBody EmployeeInfoRequest employeeInfoRequest,
			HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws SQLException {
        System.out.println("Inside controller");
		ResponseEntity<Object> responseEntity = null;
		try {

			if (null == employeeInfoRequest) {

				ErrorResponse error = new ErrorResponse();
				logger.debug("data is not valid");
				error.setMessage("Request is not valid");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}

			if (null == employeeInfoRequest.getTransactionType()
					|| employeeInfoRequest.getTransactionType().isEmpty()) {
				ErrorResponse error = new ErrorResponse();
				logger.debug("data is not valid");
				error.setMessage("transactiontype can't be null");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			return employeeInfoFacade.getAllEmployeeDetails(employeeInfoRequest);

		} catch (Exception exception) {
			logger.debug("inside catch block.*******");

			ErrorResponse error = new ErrorResponse();
			error.setStatusMessage(exception.getMessage());
		}
		return responseEntity;
	}
}
