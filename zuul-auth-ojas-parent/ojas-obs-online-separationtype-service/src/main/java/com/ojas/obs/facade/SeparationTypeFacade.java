package com.ojas.obs.facade;

import java.sql.SQLException;
import java.util.List;

import static com.ojas.obs.constants.SeparationTypeConstants.SAVE;
import static com.ojas.obs.constants.SeparationTypeConstants.UPDATE;
import static com.ojas.obs.constants.SeparationTypeConstants.GETBYID;
import static com.ojas.obs.constants.SeparationTypeConstants.SUCCESS;
import static com.ojas.obs.constants.SeparationTypeConstants.GETALL;
//import static com.ojas.obs.constants.SeparationTypeConstants.DELETE;


import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ojas.obs.constants.SeparationTypeConstants;
import com.ojas.obs.dao.SeparationTypeDao;
import com.ojas.obs.model.SeparationType;
import com.ojas.obs.request.SeparationTypeRequest;
import com.ojas.obs.response.SeparationTypeResponse;
import com.ojas.obs.utility.ErrorResponse;

/**
 *  
 * @author nsrikanth
 * 
 */
@Service
public class SeparationTypeFacade {  
	 
	Logger logger=Logger.getLogger(this.getClass());
	
	@Autowired
	private SeparationTypeDao separationTypeDao;
	
	/**
	 * 
	 * @param separationTypeRequest
	 * @return 
	 * @throws SQLException  
	 */
	
	public ResponseEntity<Object> setSeparationTypeDetails(SeparationTypeRequest separationTypeRequest)
			throws SQLException {
		logger.info("Inside setSeparationTypeDetails():");
		
		SeparationTypeResponse separationTypeResponse=new SeparationTypeResponse();
		 boolean result=false;
		 try {
			 if(separationTypeRequest.getTransactionType().equalsIgnoreCase(SAVE)) {   
				 result=separationTypeDao.saveSeparationType(separationTypeRequest);
				if(result) {
					separationTypeResponse.setStatusMessage(SeparationTypeConstants.SAVED);
					return new ResponseEntity<Object>(separationTypeResponse, HttpStatus.OK);
				}else {
					separationTypeResponse.setStatusMessage(SeparationTypeConstants.FAILED);
					return new ResponseEntity<Object>(separationTypeResponse, HttpStatus.CONFLICT); 
				}
				
			 }
			 if (separationTypeRequest.getTransactionType().equalsIgnoreCase(UPDATE)) {
					result = separationTypeDao.updateSeparationType(separationTypeRequest); 
					if (result) {
						separationTypeResponse.setStatusMessage(SeparationTypeConstants.UPDATED);
						return new ResponseEntity<Object>(separationTypeResponse, HttpStatus.OK);
					} else {
						separationTypeResponse.setStatusMessage(SeparationTypeConstants.FAILED);
						return new ResponseEntity<Object>(separationTypeResponse, HttpStatus.CONFLICT);
					} 
				}
			 
			/*
			 * if (separationTypeRequest.getTransactionType().equalsIgnoreCase(DELETE)) {
			 * List<SeparationType> separationType =
			 * separationTypeRequest.getSeparationType(); for ( SeparationType sepa
			 * :separationType) result =
			 * separationTypeDao.deleteSeparationType(sepa.getSeparationTypeId()); if
			 * (result) {
			 * separationTypeResponse.setStatusMessage(SeparationTypeConstants.DELETED);
			 * return new ResponseEntity<Object>(separationTypeResponse, HttpStatus.OK); }
			 * else {
			 * separationTypeResponse.setStatusMessage(SeparationTypeConstants.FAILED);
			 * return new ResponseEntity<Object>(separationTypeResponse,
			 * HttpStatus.CONFLICT); } }
			 */
			 
		 }
		 catch (DuplicateKeyException exception) {
				logger.debug("inside designationService catch block.****"); 
				ErrorResponse error = new ErrorResponse();
				logger.debug("data is  invalid");
				error.setStatusMessage(exception.getCause().getLocalizedMessage());
				return new ResponseEntity<>(error, HttpStatus.CONFLICT);
			}
		 catch(Exception e) {
			 logger.debug("inside designationService catch block.****"); 
				ErrorResponse error = new ErrorResponse();
				logger.debug("data is  invalid");
				error.setStatusMessage(e.getMessage());
				return new ResponseEntity<>(error, HttpStatus.CONFLICT);
			}
		return null; 
		 
			} 
	
	/**
	 *  
	 * @return
	 * @throws SQLException
	 */
	public ResponseEntity<Object> getSeparationType(SeparationTypeRequest separationTypeRequest) throws SQLException {  
		SeparationTypeResponse separationTypeResponse = new SeparationTypeResponse();

		
		logger.info("Inside getAllSeparationType():"); 
		try {
		
		if (separationTypeRequest.getTransactionType().equalsIgnoreCase(GETALL)) {
		List<SeparationType> separationTypeList = separationTypeDao.getAllSeparationType();
		if (null != separationTypeList && separationTypeList.size() != 0) { 
			separationTypeResponse.setSeparationTypeList(separationTypeList);
			separationTypeResponse.setStatusMessage(SeparationTypeConstants.SUCCESS);
			return new ResponseEntity<Object>(separationTypeResponse, HttpStatus.OK);
		} else {
			separationTypeResponse.setStatusMessage(SeparationTypeConstants.NO_RECORDS);
			return new ResponseEntity<Object>(separationTypeResponse, HttpStatus.CONFLICT);
		}
	
	}
		if (separationTypeRequest.getTransactionType().equalsIgnoreCase(GETBYID)) {
			separationTypeResponse = new SeparationTypeResponse();
			List<SeparationType> list = separationTypeDao.getById(separationTypeRequest);
			logger.debug("inside  get_count condition.****** : ");
			if (list.size() == 0) {
				separationTypeResponse.setStatusMessage("No record Present");
				//separationTypeResponse.setTotalCount(0);
				return new ResponseEntity<>(separationTypeResponse, HttpStatus.CONFLICT);
			} else {
				separationTypeResponse.setStatusMessage(SUCCESS);
				separationTypeResponse.setSeparationTypeList(list);
				return new ResponseEntity<>(separationTypeResponse, HttpStatus.OK);
			} 
		
	}
		return new ResponseEntity<Object>(separationTypeResponse, HttpStatus.OK);
		
	}	catch (Exception exception) {
		logger.debug("inside designationService catch block.****");
		ErrorResponse error = new ErrorResponse();
		logger.debug("data is  invalid");
		 error.setStatusMessage(exception.getMessage());
		error.setStatusMessage("409");
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
}
	}
	
}
