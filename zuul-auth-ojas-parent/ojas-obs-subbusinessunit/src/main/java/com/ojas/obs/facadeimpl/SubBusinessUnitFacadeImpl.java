package com.ojas.obs.facadeimpl;

import static com.ojas.obs.constants.SubBusinessUnitConstants.FAILED;
import static com.ojas.obs.constants.SubBusinessUnitConstants.SAVE;
import static com.ojas.obs.constants.SubBusinessUnitConstants.SUCCESS;
import static com.ojas.obs.constants.SubBusinessUnitConstants.UPDATE;
import static com.ojas.obs.constants.SubBusinessUnitConstants.GETALL;
import static com.ojas.obs.constants.SubBusinessUnitConstants.GETBYID;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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

		logger.info("inside saveSubBusinessUnit method : " + subBusinessUnitRequest);
		SubBusinessUnitResponse subBusinessUnitResponse = null;
		try {

			if (subBusinessUnitRequest.getTransactionType().equalsIgnoreCase(SAVE)) {
				subBusinessUnitResponse = new SubBusinessUnitResponse();
				boolean saveSubBusinessUnit = subBusinessDAO.saveSubBusinessUnit(subBusinessUnitRequest);
				logger.info("inside  save condition.****** : " + saveSubBusinessUnit);
				if (saveSubBusinessUnit) {
					subBusinessUnitResponse.setMessage("Successfully record added");
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
				logger.info("inside  update condition.****** : " + updateSubBusinessUnit);
				if (updateSubBusinessUnit) {
					subBusinessUnitResponse.setMessage("Successfully record updated");
					return new ResponseEntity<>(subBusinessUnitResponse, HttpStatus.OK);
				} else {
					ErrorResponse error = new ErrorResponse();
					error.setMessage(FAILED);
					error.setStatusCode("409");
					return new ResponseEntity<>(error, HttpStatus.CONFLICT);
				}
			}
			
			ErrorResponse error = new ErrorResponse();
			error.setMessage(FAILED);
			error.setStatusCode("409");
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			
		}catch (DuplicateKeyException exception) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage(exception.getCause().getLocalizedMessage());
			logger.error("DuplicateKeyException");
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}
		
		catch (Exception exception) {
			logger.error("inside SubBusinessUnitFacade catch block.****");
			ErrorResponse error = new ErrorResponse();
			logger.error("data is  invalid");
			error.setMessage(exception.getMessage());
			error.setStatusCode("409");
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}

	}

	@Override
	public ResponseEntity<Object> getSubBusinessUnit(SubBusinessUnitRequest subBusinessUnitRequest) {

		SubBusinessUnitResponse subBusinessUnitResponse = new SubBusinessUnitResponse();
		logger.info("inside getSubBusinessUnit in SubBusinessUnitFacade.***");

		try {
			List<SubBusinessUnit> allSubBusinessUnitDetails = null;
			if (subBusinessUnitRequest.getTransactionType().equalsIgnoreCase(GETALL)) {
			 allSubBusinessUnitDetails = subBusinessDAO.getAllSubBusinessUnitDetails();
			}
			if (subBusinessUnitRequest.getTransactionType().equalsIgnoreCase(GETBYID)) {
				Integer id = subBusinessUnitRequest.getSubBusinessUnitModel().get(0).getId();
				 allSubBusinessUnitDetails = subBusinessDAO.getByIdSubBusinessUnitDetails(id);
				}
			if (allSubBusinessUnitDetails == null || allSubBusinessUnitDetails.isEmpty()) {
				subBusinessUnitResponse.setMessage("No records found");
				subBusinessUnitResponse.setSubBusinessUnitList(allSubBusinessUnitDetails);
				return new ResponseEntity<>(subBusinessUnitResponse, HttpStatus.CONFLICT);
			} else {
				subBusinessUnitResponse.setSubBusinessUnitList(allSubBusinessUnitDetails);
				subBusinessUnitResponse.setMessage(SUCCESS);
				return new ResponseEntity<>(subBusinessUnitResponse, HttpStatus.OK);
			}

		} catch (Exception exception) {
			logger.error("inside getSubBusinessUnit catch block in SubBusinessUnitFacade.***");
			ErrorResponse error = new ErrorResponse();
			error.setMessage(exception.getMessage());
			error.setStatusCode("409");
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}

	}

}
