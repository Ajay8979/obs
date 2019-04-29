package com.obs.businessunit.facadeimpl;

import static com.obs.businessunit.constants.UserConstants.FAILED;
import static com.obs.businessunit.constants.UserConstants.GETALL;
import static com.obs.businessunit.constants.UserConstants.GETBYID;
import static com.obs.businessunit.constants.UserConstants.SAVE;
import static com.obs.businessunit.constants.UserConstants.SUCCESS;
import static com.obs.businessunit.constants.UserConstants.UPDATE;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.obs.businessunit.dao.BusinessUnitDao;
import com.obs.businessunit.error.ErrorResponse;
import com.obs.businessunit.facade.BusinessUnitFacade;
import com.obs.businessunit.model.BusinessUnit;
import com.obs.businessunit.request.BusinessUnitRequest;
import com.obs.businessunit.response.BusinessUnitResponse;


@Service
public class BusinessUnitFacadeImpl implements BusinessUnitFacade{
	@Autowired
	BusinessUnitDao businessUnitDao;
	Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ResponseEntity<Object> setBusinessUnit(BusinessUnitRequest businessUnitRequest){

		logger.debug("inside saveEmployeeEducation method : " + businessUnitRequest);
		BusinessUnitResponse businessUnitResponse = null;
		try {

			if (businessUnitRequest.getTransactionType().equalsIgnoreCase(SAVE)) {
				businessUnitResponse = new BusinessUnitResponse();
				boolean businessUnit = businessUnitDao.saveBusinessUnit(businessUnitRequest);
				logger.debug("inside  save condition.****** : " + businessUnit);
				if (businessUnit) {
					businessUnitResponse.setStatusMessage("Success fully record added");
					return new ResponseEntity<>(businessUnitResponse, HttpStatus.OK);
				} else {
					businessUnitResponse.setStatusMessage(FAILED);
					return new ResponseEntity<>(businessUnitResponse, HttpStatus.UNPROCESSABLE_ENTITY);
				}
			}
			if (businessUnitRequest.getTransactionType().equalsIgnoreCase(UPDATE)) {
				businessUnitResponse = new BusinessUnitResponse();
				boolean updateEducation = businessUnitDao.updateBusinessUnit(businessUnitRequest);
				if (updateEducation) {
					businessUnitResponse.setStatusMessage("Success fully record updated");
					return new ResponseEntity<>(businessUnitResponse, HttpStatus.OK);
				} else {
					businessUnitResponse.setStatusMessage(FAILED);
					return new ResponseEntity<>(businessUnitResponse, HttpStatus.UNPROCESSABLE_ENTITY);
				}
			}
		} catch (DuplicateKeyException exception) {
			logger.debug("inside EmployeeEducationFacde catch block.****");
			ErrorResponse error = new ErrorResponse();
			logger.debug("data is  invalid");
			error.setMessage(exception.getCause().getLocalizedMessage());
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		catch (Exception exception) {
			logger.debug("inside EmployeeEducationFacde catch block.****");
			ErrorResponse error = new ErrorResponse();
			logger.debug("data is  invalid");
			error.setMessage(exception.getMessage());
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return null;
	}
	@Override
	public ResponseEntity<Object> getBusinessUnit(BusinessUnitRequest businessUnitRequest) {
		BusinessUnitResponse businessUnitResponse = new BusinessUnitResponse();
		List<BusinessUnit> allBusinessUnitDetails=null;
		logger.debug("inside getAllEducationDetails in EmployeeEducationFacde.***");
		if(businessUnitRequest.getTransactionType().equalsIgnoreCase(GETALL)) {
		 allBusinessUnitDetails = businessUnitDao.getAllBussinessDetails(businessUnitRequest);
		}
		if (businessUnitRequest.getTransactionType().equalsIgnoreCase(GETBYID)){
			allBusinessUnitDetails = businessUnitDao.getById(businessUnitRequest);
		}

		if (allBusinessUnitDetails == null) {
			businessUnitResponse.setBusinessUnitList(new ArrayList<>());
			businessUnitResponse.setStatusMessage("No records found");
		} else {
							businessUnitResponse.setBusinessUnitList(allBusinessUnitDetails);
				businessUnitResponse.setStatusMessage(SUCCESS);
			}
		return new ResponseEntity<Object>(businessUnitResponse, HttpStatus.OK);
	}
}
