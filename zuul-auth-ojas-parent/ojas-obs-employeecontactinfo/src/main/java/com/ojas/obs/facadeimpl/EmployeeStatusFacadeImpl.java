package com.ojas.obs.facadeimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ojas.obs.dao.EmployeeStatusDao;
import com.ojas.obs.facade.EmployeeStatusFacade;
import com.ojas.obs.model.EmployeeContactInfo;
import com.ojas.obs.requests.EmployeeContactInfoRequest;
import com.ojas.obs.response.EmployeeContactInfoResponse;
import com.ojas.obs.response.ErrorResponse;

/**
 * 
 * @author ksaiKrishna
 *
 */
@Service
public class EmployeeStatusFacadeImpl implements EmployeeStatusFacade {

	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private EmployeeStatusDao employeeStatusDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ojas.obs.facade.EmployeeStatusFacade#setEmployeeContactInfo(com.ojas.obs.
	 * requests.EmployeeContactInfoRequest)
	 */
	@Override
	public ResponseEntity<Object> setEmployeeContactInfo(EmployeeContactInfoRequest employeeContactInfoRequest)
			throws SQLException {
		EmployeeContactInfoResponse employeeContactInfoResponse = new EmployeeContactInfoResponse();
		ErrorResponse errorResponse = new ErrorResponse();
		if (employeeContactInfoRequest.getTransactionType().equalsIgnoreCase("save")) {
			boolean save = employeeStatusDao.saveEmployeeContactInfo(employeeContactInfoRequest);
			if (save) {
				employeeContactInfoResponse.setMessage("Successfully Record Saved");
				employeeContactInfoResponse.setStatusCode("200");
				return new ResponseEntity<Object>(employeeContactInfoResponse, HttpStatus.OK);
			} else {
				errorResponse.setMessage("Failed");
				errorResponse.setStatusCode("409");
				return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
			}
		}
		if (employeeContactInfoRequest.getTransactionType().equalsIgnoreCase("update")) {
			boolean update = employeeStatusDao.updateEmployeeContactInfo(employeeContactInfoRequest);
			if (update) {
				employeeContactInfoResponse.setMessage("Successfully Record Updated");
				employeeContactInfoResponse.setStatusCode("200");
				return new ResponseEntity<Object>(employeeContactInfoResponse, HttpStatus.OK);
			} else {
				errorResponse.setMessage("Failed");
				errorResponse.setStatusCode("409");
				return new ResponseEntity<Object>(errorResponse, HttpStatus.CONFLICT);
			}
		}
		if (employeeContactInfoRequest.getTransactionType().equalsIgnoreCase("delete")) {
			boolean delete = employeeStatusDao.deleteEmployeeContactInfo(employeeContactInfoRequest);
			if (delete) {
				employeeContactInfoResponse.setMessage("Successfully Record Deleted");
				employeeContactInfoResponse.setStatusCode("200");
				return new ResponseEntity<Object>(employeeContactInfoResponse, HttpStatus.OK);
			} else {
				errorResponse.setMessage("Failed");
				errorResponse.setStatusCode("409");
				return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
			}
		}
		errorResponse.setMessage("Transaction type miss matched");
		errorResponse.setStatusCode("422");
		return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ojas.obs.facade.EmployeeStatusFacade#getEmployeeContactInfo(com.ojas.obs.
	 * requests.EmployeeContactInfoRequest)
	 */
	@Override
	public ResponseEntity<Object> getEmployeeContactInfo(EmployeeContactInfoRequest employeeContactInfoRequest)
			throws SQLException {

		EmployeeContactInfoResponse employeeContactInfoResponse = new EmployeeContactInfoResponse();
		List<EmployeeContactInfo> employeeContactInfoList = new ArrayList<EmployeeContactInfo>();
		if (!employeeContactInfoRequest.getEmpInfo().isEmpty()) {
			if (employeeContactInfoRequest.getEmpInfo().get(0).getId() != null) {
				employeeContactInfoList = employeeStatusDao.showEmployeeContactInfoById(employeeContactInfoRequest);
				if (employeeContactInfoList == null) {
					employeeContactInfoResponse.setMessage("No records found");
					employeeContactInfoResponse.setStatusCode("422");
					return new ResponseEntity<Object>(employeeContactInfoResponse, HttpStatus.UNPROCESSABLE_ENTITY);
				} else {
					employeeContactInfoResponse.setMessage("Record Found");
					employeeContactInfoResponse.setStatusCode("200");
					employeeContactInfoResponse.setEmpContacts(employeeContactInfoList);
					return new ResponseEntity<Object>(employeeContactInfoResponse, HttpStatus.OK);
				}
			} else if(employeeContactInfoRequest.getEmpInfo().get(0).getEmpId() != null) {
				employeeContactInfoList = employeeStatusDao.showEmployeeContactInfoByEmpId(employeeContactInfoRequest);
				if (employeeContactInfoList == null) {
					employeeContactInfoResponse.setMessage("No records found");
					employeeContactInfoResponse.setStatusCode("422");
					return new ResponseEntity<Object>(employeeContactInfoResponse, HttpStatus.UNPROCESSABLE_ENTITY);
				} else {
					employeeContactInfoResponse.setMessage("Record Found");
					employeeContactInfoResponse.setStatusCode("200");
					employeeContactInfoResponse.setEmpContacts(employeeContactInfoList);
					return new ResponseEntity<Object>(employeeContactInfoResponse, HttpStatus.OK);
				}
			}else {
				employeeContactInfoResponse.setMessage("Id is not valid");
				employeeContactInfoResponse.setStatusCode("422");
				employeeContactInfoResponse.setEmpContacts(employeeContactInfoList);
				return new ResponseEntity<Object>(employeeContactInfoResponse, HttpStatus.UNPROCESSABLE_ENTITY);
			}
		}

		else {
			employeeContactInfoList = employeeStatusDao.getAll(employeeContactInfoRequest);
			if (employeeContactInfoList == null) {
				employeeContactInfoResponse.setMessage("No records found");
				employeeContactInfoResponse.setStatusCode("422");
				return new ResponseEntity<Object>(employeeContactInfoResponse, HttpStatus.UNPROCESSABLE_ENTITY);
			} else {
				employeeContactInfoResponse.setMessage("Record Found");
				employeeContactInfoResponse.setStatusCode("200");
				employeeContactInfoResponse.setEmpContacts(employeeContactInfoList);
				return new ResponseEntity<Object>(employeeContactInfoResponse, HttpStatus.OK);
			}
		}
	}

}
