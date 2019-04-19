package com.ojas.obs.facadeimpl;

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
	public ResponseEntity<Object> setEmployeeEducationInfo(EmployeeEducationRequest employeeEducationRequest) {
		EmployeeEducationResponse employeeEducationResponse = null;
		try {
			if (employeeEducationRequest.getTransactionType().equalsIgnoreCase(UserConstants.SAVE)) {
				employeeEducationResponse = new EmployeeEducationResponse();

				System.out.println("Inside save method");
				boolean employeeEducation = employeeEducationDao.saveEmployeeEducation(employeeEducationRequest);
				if (employeeEducation) {
					int allRecordsCount = employeeEducationDao.getAllRecordsCount();
					employeeEducationResponse.setTotalCount(allRecordsCount);
					logger.debug("the response for request is ready");
					logger.debug("inside the save method" + employeeEducation);
					employeeEducationResponse.setStatusMessage("Successfully employeee education record saved");
					return new ResponseEntity<>(employeeEducationResponse, HttpStatus.OK);
				} else {
					employeeEducationResponse.setStatusMessage("FAILED");
					logger.debug("request not valid");
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
					employeeEducationResponse.setTotalCount(allRecordsCount);
					employeeEducationResponse.setStatusMessage("Successfully employeee education deleted");
					return new ResponseEntity<>(employeeEducationResponse, HttpStatus.OK);
				} else {
					employeeEducationResponse.setStatusMessage("FAILED");
					logger.debug("request not valid");
					return new ResponseEntity<>(employeeEducationResponse, HttpStatus.CONFLICT);
				}

			}
			if (employeeEducationRequest.getTransactionType().equalsIgnoreCase(UserConstants.UPDATE)) {
				employeeEducationResponse = new EmployeeEducationResponse();
				boolean employeeEducation = employeeEducationDao.updateEmployeeEducation(employeeEducationRequest);
				if (employeeEducation) {
					int allRecordsCount = employeeEducationDao.getAllRecordsCount();
					employeeEducationResponse.setTotalCount(allRecordsCount);
					logger.debug("succesfully employeeEducation updated");
					employeeEducationResponse.setStatusMessage("succesfully updated");
					return new ResponseEntity<Object>(employeeEducationResponse, HttpStatus.OK);
				} else {
					employeeEducationResponse.setStatusMessage("FAILED");
					logger.debug("request not valid");
					return new ResponseEntity<Object>(employeeEducationResponse, HttpStatus.CONFLICT);
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
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
	public ResponseEntity<Object> getEmployeeEducationInfo(EmployeeEducationRequest employeeEducationRequest) {
		EmployeeEducationResponse employeeEducationResponse = null;
		try {
			if (employeeEducationRequest.getTransactionType().equalsIgnoreCase(UserConstants.GETALL)) {
				List<EmployeeEducation> emplList = employeeEducationDao
						.getAllEmployeeEducation(employeeEducationRequest);

				employeeEducationResponse = new EmployeeEducationResponse();

				if (null != emplList) {

					int allRecordsCount = employeeEducationDao.getAllRecordsCount();
					employeeEducationResponse.setListCourse(emplList);
					employeeEducationResponse.setStatusMessage("successfully got all records");
					employeeEducationResponse.setTotalCount(allRecordsCount);
					return new ResponseEntity<Object>(employeeEducationResponse, HttpStatus.OK);
				} else {
					employeeEducationResponse.setStatusMessage("Failed");
					return new ResponseEntity<Object>(employeeEducationResponse, HttpStatus.CONFLICT);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
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
