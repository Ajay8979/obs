package com.ojas.obs.facadeImpl;

import static com.ojas.obs.constants.URLconstants.GETAll;
import static com.ojas.obs.constants.URLconstants.GETBYID;
import static com.ojas.obs.constants.UserConstants.DELETE;
import static com.ojas.obs.constants.UserConstants.FAILED;
import static com.ojas.obs.constants.UserConstants.SAVE;
import static com.ojas.obs.constants.UserConstants.UPDATE;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ojas.obs.dao.EmployeeInfoDao;
import com.ojas.obs.facade.EmployeeInfoFacade;
import com.ojas.obs.model.EmployeeInfo;
import com.ojas.obs.request.EmployeeInfoRequest;
import com.ojas.obs.response.EmployeeInfoResponse;

/**
 * 
 * @author sdileep
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
	public ResponseEntity<Object> setEmployeeInfo(EmployeeInfoRequest employeeInfoRequest)
			throws SQLException {
		logger.debug("inside saveEmployee method : " + employeeInfoRequest);
		EmployeeInfoResponse empInfoResponse = null;

		if (employeeInfoRequest.getTransactionType().equalsIgnoreCase(SAVE)) {
			empInfoResponse = new EmployeeInfoResponse();
			boolean saveEmployee = employeeInfoDao.saveEmployeeInfo(employeeInfoRequest);
			logger.debug("employeefacadeImpl employee saved : " + saveEmployee);
			if (!saveEmployee) {
				empInfoResponse.setStatusCode("409");
				empInfoResponse.setMessage(FAILED);
				return new ResponseEntity<>(empInfoResponse, HttpStatus.CONFLICT);
			}
			empInfoResponse.setMessage("Successfully record added");
			empInfoResponse.setStatusCode("200");
			return new ResponseEntity<>(empInfoResponse, HttpStatus.OK);
		}

		if (employeeInfoRequest.getTransactionType().equalsIgnoreCase(UPDATE)) {
			empInfoResponse = new EmployeeInfoResponse();

			boolean updateEmployee = employeeInfoDao.updateEmployeeInfo(employeeInfoRequest);
			logger.info("employeefacadeImpl employee updated : " + updateEmployee);
			if (!updateEmployee) {
				empInfoResponse.setMessage(FAILED);
				empInfoResponse.setStatusCode("409");
				return new ResponseEntity<>(empInfoResponse, HttpStatus.CONFLICT);
			}
			empInfoResponse.setStatusCode("200");
			empInfoResponse.setMessage("Successfully record updated");
			return new ResponseEntity<>(empInfoResponse, HttpStatus.OK);
		}
		if (employeeInfoRequest.getTransactionType().equalsIgnoreCase(DELETE)) {
			empInfoResponse = new EmployeeInfoResponse();
			boolean deleteEmployeeRecord = employeeInfoDao.deleteEmployeeInfo(employeeInfoRequest);
			logger.debug("inside  delete condition : " + deleteEmployeeRecord);
			if (!deleteEmployeeRecord) {
				empInfoResponse.setMessage(FAILED);
				empInfoResponse.setStatusCode("409");
				return new ResponseEntity<>(empInfoResponse, HttpStatus.CONFLICT);
			}
			empInfoResponse.setStatusCode("200");
			empInfoResponse.setMessage("Successfully record deleted");
			return new ResponseEntity<>(empInfoResponse, HttpStatus.OK);
		}
		return new ResponseEntity<>(empInfoResponse, HttpStatus.CONFLICT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ojas.obs.facade.EmployeeInfoFacade#getAllEmployeeDetails(com.ojas.obs.
	 * request.EmployeeInfoRequest)
	 */

	@Override
	public ResponseEntity<Object> getAllEmployeeDetails(EmployeeInfoRequest employeeInfoRequest) throws SQLException {
		logger.debug("Inside getAllEmployeeDetails in EmployeeInfoFacade.");
		EmployeeInfoResponse employeeResponse = new EmployeeInfoResponse();
		if (employeeInfoRequest.getTransactionType().equalsIgnoreCase(GETAll)) {
			logger.debug("Inside getall in facade");
			List<EmployeeInfo> allEmployeeDetails = employeeInfoDao.getAllEmployeeDetails(employeeInfoRequest);
			logger.info("Inside getall in facade all employeedetails : " + allEmployeeDetails);
			if (allEmployeeDetails != null) {
				employeeResponse.setEmployeeInfo(allEmployeeDetails);
				employeeResponse.setMessage("Success");
				employeeResponse.setStatusCode("200");
				return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
			}
			employeeResponse.setEmployeeInfo(new ArrayList<>());
			employeeResponse.setStatusCode("200");
			employeeResponse.setMessage("No records found");
			return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
		} else if (employeeInfoRequest.getTransactionType().equalsIgnoreCase(GETBYID)) {
			logger.debug("Inside getbyid in facade");
			List<EmployeeInfo> allEmployeeDetails = employeeInfoDao.getById(employeeInfoRequest);
			logger.info("Fetched employee list : " + allEmployeeDetails);
			employeeResponse.setEmployeeInfo(allEmployeeDetails);
			employeeResponse.setMessage("Success");
			employeeResponse.setStatusCode("200");
			return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
		}
		return new ResponseEntity<>(employeeResponse, HttpStatus.CONFLICT);

	}

}
