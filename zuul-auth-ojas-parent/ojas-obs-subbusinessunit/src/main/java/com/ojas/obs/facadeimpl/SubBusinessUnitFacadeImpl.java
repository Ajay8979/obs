package com.ojas.obs.facadeimpl;

import static com.ojas.obs.constants.SubBusinessUnitConstants.DELETE;
import static com.ojas.obs.constants.SubBusinessUnitConstants.FAILED;
import static com.ojas.obs.constants.SubBusinessUnitConstants.SAVE;
import static com.ojas.obs.constants.SubBusinessUnitConstants.SUCCESS;
import static com.ojas.obs.constants.SubBusinessUnitConstants.UPDATE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ojas.obs.dao.SubBusinessUnitDao;
import com.ojas.obs.error.ErrorResponse;
import com.ojas.obs.facade.SubBusinessUnitFacade;
import com.ojas.obs.model.SubBusinessUnit;
import com.ojas.obs.request.SubBusinessUnitRequest;
import com.ojas.obs.response.SubBusinessUnitResponse;

import org.apache.log4j.Logger;

/**
 * 
 * @author asuneel
 *
 */
@Service
public class SubBusinessUnitFacadeImpl implements SubBusinessUnitFacade {

	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	SubBusinessUnitDao subBusinessDAO;

	@Override
	public ResponseEntity<Object> setSubBusinessUnit(SubBusinessUnitRequest subBusinessUnitRequest) {

		logger.debug("inside saveSubBusinessUnit method : " + subBusinessUnitRequest);
		SubBusinessUnitResponse subBusinessUnitResponse = null;
		try {

			if (subBusinessUnitRequest.getTransactionType().equalsIgnoreCase(SAVE)) {
				subBusinessUnitResponse = new SubBusinessUnitResponse();
				boolean saveSubBusinessUnit = subBusinessDAO.saveSubBusinessUnit(subBusinessUnitRequest);
				logger.debug("inside  save condition.****** : " + saveSubBusinessUnit);
				if (saveSubBusinessUnit) {
					subBusinessUnitResponse.setMessage("Success fully record added");
					int totalCount = subBusinessDAO.getAllSubBusinessUnitDetailsCount();
					subBusinessUnitResponse.setTotalCount(totalCount);
					return new ResponseEntity<>(subBusinessUnitResponse, HttpStatus.OK);
				} else {
					ErrorResponse error = new ErrorResponse();
					error.setMessage(FAILED);
					error.setStatusCode("409");
					return new ResponseEntity<>(error, HttpStatus.CONFLICT);
				}

			}
			if (subBusinessUnitRequest.getTransactionType().equalsIgnoreCase(UPDATE)) {
				subBusinessUnitResponse = new SubBusinessUnitResponse();
				boolean updateSubBusinessUnit = subBusinessDAO.updateSubBusinessUnit(subBusinessUnitRequest);
				if (updateSubBusinessUnit) {
					subBusinessUnitResponse.setMessage("Success fully record updated");
					int totalCount = subBusinessDAO.getAllSubBusinessUnitDetailsCount();
					subBusinessUnitResponse.setTotalCount(totalCount);
					return new ResponseEntity<>(subBusinessUnitResponse, HttpStatus.OK);
				} else {
					ErrorResponse error = new ErrorResponse();
					error.setMessage(FAILED);
					error.setStatusCode("409");
					return new ResponseEntity<>(error, HttpStatus.CONFLICT);
				}
			}
			/*
			 * if (subBusinessUnitRequest.getTransactionType().equalsIgnoreCase(DELETE)) {
			 * subBusinessUnitResponse = new SubBusinessUnitResponse(); boolean
			 * deleteEducationRecord =
			 * subBusinessDAO.deleteSubBusinessUnit(subBusinessUnitRequest);
			 * 
			 * logger.debug("inside  delete condition.******  : " + subBusinessUnitRequest);
			 * if (deleteEducationRecord) {
			 * subBusinessUnitResponse.setMessage("Success fully record deleted"); int
			 * totalCount = subBusinessDAO.getAllSubBusinessUnitDetailsCount();
			 * subBusinessUnitResponse.setTotalCount(totalCount); return new
			 * ResponseEntity<>(subBusinessUnitResponse, HttpStatus.OK); } else {
			 * ErrorResponse error = new ErrorResponse(); error.setMessage(FAILED);
			 * error.setStatusCode("409"); return new ResponseEntity<>(error,
			 * HttpStatus.CONFLICT); }
			 * 
			 * }
			 */
			ErrorResponse error = new ErrorResponse();
			error.setMessage(FAILED);
			error.setStatusCode("409");
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		} catch (Exception exception) {
			logger.debug("inside SubBusinessUnitFacade catch block.****");
			ErrorResponse error = new ErrorResponse();
			logger.debug("data is  invalid");
			error.setMessage(exception.getMessage());
			error.setStatusCode("409");
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}

	}

	@Override
	public ResponseEntity<Object> getSubBusinessUnit(SubBusinessUnitRequest subBusinessUnitRequest) {

		SubBusinessUnitResponse subBusinessUnitResponse = new SubBusinessUnitResponse();
		logger.debug("inside getSubBusinessUnit in SubBusinessUnitFacade.***");

		try {
			List<SubBusinessUnit> allSubBusinessUnitDetails = subBusinessDAO.getAllSubBusinessUnitDetails();
			if (allSubBusinessUnitDetails == null || allSubBusinessUnitDetails.isEmpty()) {
				subBusinessUnitResponse.setMessage("No records found");
				subBusinessUnitResponse.setTotalCount(0);
				subBusinessUnitResponse.setSubBusinessUnitList(allSubBusinessUnitDetails);
				return new ResponseEntity<>(subBusinessUnitResponse, HttpStatus.CONFLICT);
			} else {
				int count = subBusinessDAO.getAllSubBusinessUnitDetailsCount();
				subBusinessUnitResponse.setSubBusinessUnitList(allSubBusinessUnitDetails);
				subBusinessUnitResponse.setMessage(SUCCESS);
				subBusinessUnitResponse.setTotalCount(count);
				return new ResponseEntity<>(subBusinessUnitResponse, HttpStatus.OK);
			}

		} catch (Exception exception) {
			logger.debug("inside getSubBusinessUnit catch block in SubBusinessUnitFacade.***");
			ErrorResponse error = new ErrorResponse();
			error.setMessage(exception.getMessage());
			error.setStatusCode("409");
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}

	}

}
