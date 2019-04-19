package com.ojas.obs.facade;

import static com.ojas.obs.constants.UserConstants.DELETE;
import static com.ojas.obs.constants.UserConstants.FAILED;
import static com.ojas.obs.constants.UserConstants.SAVE;
import static com.ojas.obs.constants.UserConstants.UPDATE;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ojas.obs.dao.BusinessUnitDao;
import com.ojas.obs.model.BusinessUnit;
import com.ojas.obs.model.BusinessUnitRequest;
import com.ojas.obs.model.BusinessUnitResponse;

import com.ojas.obs.util.ErrorRespponse;

@Service
public class BusinessUnitFacadeImpl implements BusinessUnitFacade {

	@Autowired
	BusinessUnitDao businessUnitDao;
	Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ResponseEntity<Object> setBusinessUnit(BusinessUnitRequest businessUnitRequest) throws SQLException {

		logger.debug("inside saveEmployeeEducation method : " + businessUnitRequest);
		BusinessUnitResponse businessUnitResponse = null;
		try {

			if (businessUnitRequest.getTransactionType().equalsIgnoreCase(SAVE)) {
				businessUnitResponse = new BusinessUnitResponse();
				boolean businessUnit = businessUnitDao.saveBusinessUnit(businessUnitRequest);
				int count = ((BusinessUnitDao) businessUnitDao).getAllBussinessCount();
				logger.debug("inside  save condition.****** : " + businessUnit);
				if (businessUnit) {
					businessUnitResponse.setStatusMessage("Success fully record added");
					businessUnitResponse.setTotalCount(String.valueOf(count));
					return new ResponseEntity<>(businessUnitResponse, HttpStatus.OK);
				} else {
					businessUnitResponse.setStatusMessage(FAILED);
					return new ResponseEntity<>(businessUnitResponse, HttpStatus.UNPROCESSABLE_ENTITY);
				}
			}
			if (businessUnitRequest.getTransactionType().equalsIgnoreCase(UPDATE)) {
				businessUnitResponse = new BusinessUnitResponse();
				boolean updateEducation = businessUnitDao.updateBusinessUnit(businessUnitRequest);
				int count = businessUnitDao.getAllBussinessCount();
				if (updateEducation) {
					businessUnitResponse.setStatusMessage("Success fully record updated");
					businessUnitResponse.setTotalCount(String.valueOf(count));
					return new ResponseEntity<>(businessUnitResponse, HttpStatus.OK);
				} else {
					businessUnitResponse.setStatusMessage(FAILED);
					return new ResponseEntity<>(businessUnitResponse, HttpStatus.UNPROCESSABLE_ENTITY);
				}
			}

			if (businessUnitRequest.getTransactionType().equalsIgnoreCase(DELETE)) {
				businessUnitResponse = new BusinessUnitResponse();
				Integer courseId = businessUnitRequest.getBusinessUnit().getId();
				boolean deleteEducationRecord = businessUnitDao.deleteBusinessUnit(courseId);
				int count = businessUnitDao.getAllBussinessCount();
				logger.debug("inside  delete condition.****** and id is : " + courseId);
				if (deleteEducationRecord) {
					businessUnitResponse.setStatusMessage("Success fully record deleted");
					businessUnitResponse.setTotalCount(String.valueOf(count));
					return new ResponseEntity<>(businessUnitResponse, HttpStatus.OK);
				} else {
					businessUnitResponse.setStatusMessage(FAILED);
					return new ResponseEntity<>(businessUnitResponse, HttpStatus.UNPROCESSABLE_ENTITY);
				}
			}
		} catch (Exception exception) {
			logger.debug("inside EmployeeEducationFacde catch block.****");
			ErrorRespponse error = new ErrorRespponse();
			logger.debug("data is  invalid");
			error.setMessage(exception.getMessage());
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return null;
	}

	@Override
	public ResponseEntity<Object> getBusinessUnit(BusinessUnitRequest businessUnitRequest) throws SQLException {

		BusinessUnitResponse businessUnitResponse = new BusinessUnitResponse();
		logger.debug("inside getAllEducationDetails in EmployeeEducationFacde.***");

		List<BusinessUnit> allBusinessUnitDetails = businessUnitDao.getAllBussinessDetails(businessUnitRequest);
		List<BusinessUnit> list = businessUnitDao.getCountPerPage(allBusinessUnitDetails,
				businessUnitRequest.getPageSize(), businessUnitRequest.getPageNo());
		if (list == null || allBusinessUnitDetails == null) {
			businessUnitResponse.setBusinessUnitList(new ArrayList<>());
			businessUnitResponse.setStatusMessage("No records found");
			businessUnitResponse.setTotalCount("");
		} else {
			int count = businessUnitDao.getAllBussinessCount();
			if (businessUnitRequest.getPageNo() == 0 || businessUnitRequest.getPageSize() == 0) {
				businessUnitResponse.setBusinessUnitList(allBusinessUnitDetails);
				businessUnitResponse.setStatusMessage("Success");
				businessUnitResponse.setTotalCount(String.valueOf(count));
			} else {
				businessUnitResponse.setBusinessUnitList(list);
				businessUnitResponse.setStatusMessage("success");
				businessUnitResponse.setTotalCount(String.valueOf(count));
			}
		}
		return new ResponseEntity<Object>(businessUnitResponse, HttpStatus.OK);
	}
}
