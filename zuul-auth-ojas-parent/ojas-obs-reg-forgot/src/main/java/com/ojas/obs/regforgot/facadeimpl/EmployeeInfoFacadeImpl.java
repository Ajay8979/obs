package com.ojas.obs.regforgot.facadeimpl;

import static com.ojas.obs.regforgot.constants.UserConstants.FAILED;
import static com.ojas.obs.regforgot.constants.UserConstants.SAVE;
import static com.ojas.obs.regforgot.constants.UserConstants.UPDATE;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ojas.obs.regforgot.dao.EmployeeInfoDao;
import com.ojas.obs.regforgot.error.ErrorResponse;
import com.ojas.obs.regforgot.facade.EmployeeInfoFacade;
import com.ojas.obs.regforgot.request.EmployeeInfoRequest;
import com.ojas.obs.regforgot.response.EmployeeInfoResponse;

/**
 * 
 * @author Manohar
 *
 */
@Service
public class EmployeeInfoFacadeImpl implements EmployeeInfoFacade {

	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	EmployeeInfoDao employeeInfoDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ojas.obs.facade.EmployeeInfoFacade#saveEmployeeInfo(com.ojas.obs.request.
	 * EmployeeInfoRequest)
	 */

	@Override
	public ResponseEntity<Object> setEmployeeInfo(EmployeeInfoRequest employeeInfoRequest) throws SQLException {
		logger.debug("inside saveEmployee method : " + employeeInfoRequest);
		EmployeeInfoResponse empInfoResponse = null;

		try {
			if (employeeInfoRequest.getTransactionType().equalsIgnoreCase(SAVE)) {
				empInfoResponse = new EmployeeInfoResponse();
				boolean saveEmployee = employeeInfoDao.saveEmployeeInfo(employeeInfoRequest);
				logger.debug("**inside  save condition.****** : " + saveEmployee);
				if (saveEmployee) {

					empInfoResponse.setStatusMessage("Success fully record added");
					return new ResponseEntity<>(empInfoResponse, HttpStatus.OK);
				} else {
					empInfoResponse.setStatusMessage(FAILED);
					return new ResponseEntity<>(empInfoResponse, HttpStatus.CONFLICT);
				}
			}
			if (employeeInfoRequest.getTransactionType().equalsIgnoreCase(UPDATE)) {
				empInfoResponse = new EmployeeInfoResponse();

				return null;
			}

		} catch (DuplicateKeyException exception) {
			logger.debug("**DuplicateKeyException-inside employeeInfoFacade catch block.****");
			ErrorResponse error = new ErrorResponse();
			logger.debug("data is  invalid-DuplicateKeyException ");
			error.setMessage(exception.getCause().getLocalizedMessage());
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		} catch (Exception exception) {
			logger.debug("**inside employeeInfoFacade catch block.****");
			ErrorResponse error = new ErrorResponse();
			logger.debug("data is  invalid");
			error.setMessage(exception.getMessage());
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}
		return null;

	}

}
