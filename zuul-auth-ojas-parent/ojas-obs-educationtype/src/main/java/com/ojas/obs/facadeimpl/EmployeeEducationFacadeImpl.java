package com.ojas.obs.facadeimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ojas.obs.constants.UserConstants;
import com.ojas.obs.dao.EmployeeEducationDao;
import com.ojas.obs.facade.EmployeeEducationFacade;
import com.ojas.obs.model.EmployeeEducation;
import com.ojas.obs.modelrequest.EmployeeEducationRequest;
import com.ojas.obs.modelresponse.EmployeeEducationResponse;

/**
 * 
 * @author mpraneethguptha
 *
 */
@Service
public class EmployeeEducationFacadeImpl implements EmployeeEducationFacade {

	@Autowired
	EmployeeEducationDao employeeEducationDao;

	Logger logger = Logger.getLogger(this.getClass());
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ojas.obs.facade.EmployeeEducationFacade#setEmployeeEducationInfo(com.ojas
	 * .obs.modelrequest.EmployeeEducationRequest)
	 */

	@Override
	public ResponseEntity<Object> setEmployeeEducationInfo(EmployeeEducationRequest employeeEducationRequest)
			throws SQLException {
		EmployeeEducationResponse employeeEducationResponse = null;
		logger.info("The request details in service set method" + employeeEducationRequest);

		if (employeeEducationRequest.getTransactionType().equalsIgnoreCase(UserConstants.SAVE)) {
			employeeEducationResponse = new EmployeeEducationResponse();
			boolean employeeEducation = employeeEducationDao.saveEmployeeEducation(employeeEducationRequest);
			if (employeeEducation) {
				int allRecordsCount = employeeEducationDao.getAllRecordsCount();
				logger.debug("The record count being saved " + allRecordsCount);
				logger.debug("Details saved " + employeeEducation);
				employeeEducationResponse.setMessage("Successfully employeee education record saved");
				return new ResponseEntity<>(employeeEducationResponse, HttpStatus.OK);
			} else {
				employeeEducationResponse.setMessage("FAILED");
				logger.error("request details not saved");
				return new ResponseEntity<>(employeeEducationResponse, HttpStatus.CONFLICT);

			}
		}
		if (employeeEducationRequest.getTransactionType().equalsIgnoreCase(UserConstants.DELETE)) {
			employeeEducationResponse = new EmployeeEducationResponse();
			boolean isDeleted = false;
			List<EmployeeEducation> employeeEducations = employeeEducationRequest.getListEmployeeEducations();
			for (EmployeeEducation employeeEducation : employeeEducations) {
				int id = employeeEducation.getId();
				isDeleted = employeeEducationDao.deleteEmployeeEducation(id);
			}
			if (isDeleted) {
				int allRecordsCount = employeeEducationDao.getAllRecordsCount();
				logger.debug("The employee education record deleted " + allRecordsCount);
				employeeEducationResponse.setMessage("Successfully employeee education deleted " + allRecordsCount);
				return new ResponseEntity<>(employeeEducationResponse, HttpStatus.OK);
			} else {
				employeeEducationResponse.setMessage("FAILED");
				logger.error("Requested object is not deleted");
				return new ResponseEntity<>(employeeEducationResponse, HttpStatus.CONFLICT);
			}

		}
		if (employeeEducationRequest.getTransactionType().equalsIgnoreCase(UserConstants.UPDATE)) {
			employeeEducationResponse = new EmployeeEducationResponse();
			boolean employeeEducation = employeeEducationDao.updateEmployeeEducation(employeeEducationRequest);
			if (employeeEducation) {
				int allRecordsCount = employeeEducationDao.getAllRecordsCount();
				logger.debug("employeeEducation details updated");
				employeeEducationResponse.setMessage("succesfully updated");
				return new ResponseEntity<Object>(employeeEducationResponse, HttpStatus.OK);
			} else {
				employeeEducationResponse.setMessage("FAILED");
				logger.error("employeeEducation details not updated");
				return new ResponseEntity<Object>(employeeEducationResponse, HttpStatus.CONFLICT);
			}
		}

		return new ResponseEntity<Object>(employeeEducationResponse, HttpStatus.CONFLICT);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ojas.obs.facade.EmployeeEducationFacade#getEmployeeEducationInfo(com.ojas
	 * .obs.modelrequest.EmployeeEducationRequest)
	 */

	@Override
	public ResponseEntity<Object> getEmployeeEducationInfo(EmployeeEducationRequest employeeEducationRequest)
			throws SQLException {
		EmployeeEducationResponse employeeEducationResponse = null;
		logger.info("The request details in service get method" + employeeEducationRequest);

		if (employeeEducationRequest.getTransactionType().equalsIgnoreCase(UserConstants.GETALL)) {
			if (null != employeeEducationRequest.getListEmployeeEducations().get(0).getId()) {
				List<EmployeeEducation> byId = employeeEducationDao.getAllEmployeeEducation(employeeEducationRequest);
				if (null != byId) {
					employeeEducationResponse = new EmployeeEducationResponse();
					employeeEducationResponse.setListCourse(byId);
					employeeEducationResponse.setMessage("successfully got all records");
					logger.debug("Succesfully retrieved requested record using " + byId);
					return new ResponseEntity<Object>(employeeEducationResponse, HttpStatus.OK);
				} else {
					employeeEducationResponse.setMessage("Failed");
					logger.error("The request record using" + byId + " not retrieved");
					return new ResponseEntity<Object>(employeeEducationResponse, HttpStatus.CONFLICT);
				}
			} else {
				List<EmployeeEducation> emplList = employeeEducationDao
						.getAllEmployeeEducation(employeeEducationRequest);

				employeeEducationResponse = new EmployeeEducationResponse();

				if (null != emplList) {
					employeeEducationResponse.setListCourse(emplList);
					employeeEducationResponse.setMessage("successfully got all records");
					logger.debug("List of employee records retrieved " + emplList);
					return new ResponseEntity<Object>(employeeEducationResponse, HttpStatus.OK);
				} else {
					employeeEducationResponse.setMessage("Failed");
					logger.error("List of employee records not retrieved");
					return new ResponseEntity<Object>(employeeEducationResponse, HttpStatus.CONFLICT);
				}
			}
		}
		return new ResponseEntity<Object>(employeeEducationResponse, HttpStatus.CONFLICT);
	}

	/**
	 * 
	 * @param allEducationDetails
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */

	public List<EmployeeEducation> recordsPerPage(List<EmployeeEducation> allEducationDetails, int pageSize,
			int pageNo) {

		List<EmployeeEducation> getAllFiltered = new ArrayList<>();
		if (allEducationDetails != null && !allEducationDetails.isEmpty()) {
			pageSize = pageSize > 0 ? pageSize : pageSize * -1;
			pageNo = pageNo > 0 ? pageNo : pageNo == 0 ? 1 : pageNo * -1;
			if (pageSize != 0) {
				int endIndex = pageNo * pageSize;
				int startIndex = endIndex - pageSize;
				endIndex = endIndex < allEducationDetails.size() ? endIndex : allEducationDetails.size();
				startIndex = startIndex < allEducationDetails.size() ? startIndex : 0;
				getAllFiltered = allEducationDetails.subList(startIndex, endIndex);
			}
		}
		return getAllFiltered;
	}

}
