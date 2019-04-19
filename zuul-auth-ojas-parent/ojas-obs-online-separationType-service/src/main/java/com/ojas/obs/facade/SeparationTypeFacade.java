package com.ojas.obs.facade;

import java.sql.SQLException;
import java.util.List;

import static com.ojas.obs.constants.SeparationTypeConstants.SAVE;
import static com.ojas.obs.constants.SeparationTypeConstants.UPDATE;
//import static com.ojas.obs.constants.SeparationTypeConstants.DELETE;


import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ojas.obs.constants.SeparationTypeConstants;
import com.ojas.obs.dao.SeparationTypeDao;
import com.ojas.obs.model.SeparationType;
import com.ojas.obs.request.SeparationTypeRequest;
import com.ojas.obs.response.SeparationTypeResponse;

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
		 catch(Exception e) {
				logger.info("Inside setSeparationTypeDetails() catch block:"); 
				separationTypeResponse.setStatusMessage(e.getMessage());
				//return new ResponseEntity<Object>(separationTypeResponse, HttpStatus.CONFLICT);
			}
		 
		 return new ResponseEntity<Object>(separationTypeResponse, HttpStatus.CONFLICT);
			}
	
	/**
	 *  
	 * @return
	 * @throws SQLException
	 */
	public ResponseEntity<Object> getAllSeparationType() throws SQLException { 
		logger.info("Inside getAllSeparationType():");
		SeparationTypeResponse separationTypeResponse = new SeparationTypeResponse();
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
	

}
