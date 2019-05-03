package com.ojas.obs.regforgot.controller;

//import static com.ojas.obs.regforgot.constants.URLconstants.REGFORGOT;
import static com.ojas.obs.regforgot.constants.URLconstants.SET;
import static com.ojas.obs.regforgot.constants.UserConstants.SAVE;

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

import com.ojas.obs.regforgot.error.ErrorResponse;
import com.ojas.obs.regforgot.facade.EmployeeInfoFacade;
import com.ojas.obs.regforgot.model.EmployeeInfo;
import com.ojas.obs.regforgot.request.EmployeeInfoRequest;

/**
 * 
 * @author Manohar
 *
 */

@RestController
//@RequestMapping(REGFORGOT)
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
								|| (null == empInfo.getPassword() || empInfo.getPassword().isEmpty())
								|| (null == empInfo.getTitle()) || (null == empInfo.getEmployeeId()
								|| empInfo.getEmployeeId().isEmpty())
								|| (employeeInfoRequest.getTransactionType() == null
										|| (employeeInfoRequest.getTransactionType().isEmpty())))) {

					ErrorResponse error = new ErrorResponse();
					logger.debug("data is  invalid");
					error.setMessage("Request is  invalid");

					return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);

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

}
