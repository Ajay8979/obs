package com.ojas.obs.facadeImpl;

import static com.ojas.obs.constants.URLconstants.GETBYID;
import static com.ojas.obs.constants.URLconstants.GETROLEBYID;
import static com.ojas.obs.constants.URLconstants.GETAll;
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
import com.ojas.obs.errorResponse.ErrorResponse;
import com.ojas.obs.facade.EmployeeInfoFacade;
import com.ojas.obs.model.EmployeeInfo;
import com.ojas.obs.request.EmployeeInfoRequest;
import com.ojas.obs.response.EmployeeInfoResponse;
import com.ojas.obs.response.EmployeeRoleResponse;

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

				boolean updateEmployee = employeeInfoDao.updateEmployeeInfo(employeeInfoRequest);
				if (updateEmployee) {
					empInfoResponse.setStatusMessage("Success fully record updated");
					return new ResponseEntity<>(empInfoResponse, HttpStatus.OK);
				} else {
					empInfoResponse.setStatusMessage(FAILED);
					return new ResponseEntity<>(empInfoResponse, HttpStatus.CONFLICT);
				}
			}
			if (employeeInfoRequest.getTransactionType().equalsIgnoreCase(DELETE)) {
				empInfoResponse = new EmployeeInfoResponse();

				boolean deleteEmployeeRecord = employeeInfoDao.deleteEmployeeInfo(employeeInfoRequest);

				logger.debug("**inside  delete condition.******  : " + deleteEmployeeRecord);

				if (deleteEmployeeRecord) {
					empInfoResponse.setStatusMessage("Success fully record deleted");
					return new ResponseEntity<>(empInfoResponse, HttpStatus.OK);
				} else {
					empInfoResponse.setStatusMessage(FAILED);
					return new ResponseEntity<>(empInfoResponse, HttpStatus.CONFLICT);
				}
			}
		} catch (Exception exception) {
			logger.debug("**inside employeeInfoFacade catch block.****");
			ErrorResponse error = new ErrorResponse();
			logger.debug("data is  invalid");
			error.setMessage(exception.getMessage());
		}
		return null;

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
		logger.debug("inside getAllEmployeeDetails in EmployeeInfoFacade.***");
		EmployeeInfoResponse employeeResponse = new EmployeeInfoResponse();
		if (employeeInfoRequest.getTransactionType().equalsIgnoreCase(GETAll)) {

			List<EmployeeInfo> allEmployeeDetails = employeeInfoDao.getAllEmployeeDetails(employeeInfoRequest);
//			List<EmployeeInfo> recordsPerPage = employeeInfoDao.getPageRecords(allEmployeeDetails,
//					employeeInfoRequest.getPageSize(), employeeInfoRequest.getPageNum());
			if (allEmployeeDetails == null) {
				employeeResponse.setEmployeeInfo(new ArrayList<>());
				employeeResponse.setStatusMessage("No records found");
				employeeResponse.setTotalCount(0);
				return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
			} else {
				int count = employeeInfoDao.getAllEmployeeDetailsCount();
				employeeResponse.setEmployeeInfo(allEmployeeDetails);
				employeeResponse.setStatusMessage("Success");
				employeeResponse.setTotalCount(count);
				return new ResponseEntity<Object>(employeeResponse, HttpStatus.OK);
			}
		} else if (employeeInfoRequest.getTransactionType().equalsIgnoreCase(GETBYID)) {
			List<EmployeeInfo> allEmployeeDetails = employeeInfoDao.getById(employeeInfoRequest);
			int count = employeeInfoDao.getAllEmployeeDetailsCount();
			employeeResponse.setEmployeeInfo(allEmployeeDetails);
			employeeResponse.setStatusMessage("Success");
			employeeResponse.setTotalCount(count);
			return new ResponseEntity<Object>(employeeResponse, HttpStatus.OK);
		} else if (employeeInfoRequest.getTransactionType().equalsIgnoreCase(GETROLEBYID)) {
			System.out.println("Inside Role");
			EmployeeRoleResponse allEmployeeDetails = employeeInfoDao.getRoleById(employeeInfoRequest);
			int count = employeeInfoDao.getAllEmployeeDetailsCount();
			
			allEmployeeDetails.setStatusMessage("Success");
			allEmployeeDetails.setTotalCount(count);
			System.out.println("Resp"+allEmployeeDetails.getRoleName());
			return new ResponseEntity<Object>(allEmployeeDetails, HttpStatus.OK);
		}

		return null;

	}

}
