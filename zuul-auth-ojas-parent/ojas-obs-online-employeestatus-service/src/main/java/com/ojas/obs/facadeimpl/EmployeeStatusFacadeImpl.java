package com.ojas.obs.facadeimpl;

import static com.ojas.obs.constants.UserConstants.FAILED;
import static com.ojas.obs.constants.UserConstants.SAVE;
import static com.ojas.obs.constants.UserConstants.SUCCESS;
import static com.ojas.obs.constants.UserConstants.UPDATE;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ojas.obs.dao.EmployeeStatusDao;
import com.ojas.obs.facade.EmployeeStatusFacade;
import com.ojas.obs.model.EmployeeStatus;
import com.ojas.obs.request.EmployeeStatusRequest;
import com.ojas.obs.response.EmployeeStatusResponse;

/**
 * 
 * @author Manohar
 *
 */
@Service
public class EmployeeStatusFacadeImpl implements EmployeeStatusFacade {
	@Autowired
	private EmployeeStatusDao employeeStatusDao;
	Logger logger = Logger.getLogger(this.getClass());
	@Override
	public ResponseEntity<Object> setEmployeeStatus(EmployeeStatusRequest employeeStatusRequest) {
		logger.debug("inside setEmployeeStatus method : " + employeeStatusRequest);
		EmployeeStatusResponse employeeStatusResponse = null;
		if (employeeStatusRequest.getTransactionType().equalsIgnoreCase(SAVE)) {
			employeeStatusResponse = new EmployeeStatusResponse();
			boolean save = employeeStatusDao.saveEmployeeStatus(employeeStatusRequest);
			logger.debug("*****inside  save condition.***** : " + save);
			if (!save) {
				logger.error("**Failed to save the record(s)***");
				employeeStatusResponse.setStatus(FAILED);
				return new ResponseEntity<>(employeeStatusResponse, HttpStatus.CONFLICT);
			}
			employeeStatusResponse.setStatus("Record successfully saved");
			return new ResponseEntity<>(employeeStatusResponse, HttpStatus.OK);
		}
		if (employeeStatusRequest.getTransactionType().equalsIgnoreCase(UPDATE)) {
			employeeStatusResponse = new EmployeeStatusResponse();
			boolean update = employeeStatusDao.updateEmployeeStatus(employeeStatusRequest);
			logger.debug("*****inside  update condition.***** : " + update);
			if (!update) {
				logger.error("**Failed to update the record(s)***");
				employeeStatusResponse.setStatus(FAILED);
				return new ResponseEntity<>(employeeStatusResponse, HttpStatus.CONFLICT);
			}
			employeeStatusResponse.setStatus("Record Successfully updated");
			return new ResponseEntity<>(employeeStatusResponse, HttpStatus.OK);
		}
		/*
		 * if (employeeStatusRequest.getTransactionType().equalsIgnoreCase(DELETE)) {
		 * employeeStatusResponse = new EmployeeStatusResponse(); boolean delete =
		 * employeeStatusDao.deleteEmployeeStatus(employeeStatusRequest);
		 * logger.debug("*****inside  delete condition.***** : " + delete); if (!delete)
		 * { employeeStatusResponse.setStatus("Record already deleted"); return new
		 * ResponseEntity<>(employeeStatusResponse, HttpStatus.CONFLICT); }
		 * employeeStatusResponse.setStatus("Record successfully deleted"); return new
		 * ResponseEntity<>(employeeStatusResponse, HttpStatus.OK); }
		 */
		return new ResponseEntity<>(employeeStatusResponse, HttpStatus.CONFLICT);
	}
	
	public ResponseEntity<Object> getEmployeeStatus() {
		logger.debug("*****inside  getEmployeeStatus method in EmployeeStatusFacade*****");
		EmployeeStatusResponse statusResponse = new EmployeeStatusResponse();
		List<EmployeeStatus> allEmpStatus = employeeStatusDao.getAllStatus();
		if (null == allEmpStatus || allEmpStatus.isEmpty()) {
			logger.error("**Failed to fetch the record(s)***");
			statusResponse.setStatus(FAILED);
			statusResponse.setEmployeeStatusList(allEmpStatus);
			return new ResponseEntity<>(statusResponse, HttpStatus.CONFLICT);
		} else {
			statusResponse.setStatus(SUCCESS);
			statusResponse.setEmployeeStatusList(allEmpStatus);
			return new ResponseEntity<>(statusResponse, HttpStatus.OK);
		}
	}
}