package com.ojas.obs.facade;

import static com.ojas.obs.constants.DesignationServiceConstants.FAILED;
import static com.ojas.obs.constants.DesignationServiceConstants.UPDATE;
//import static com.ojas.obs.constants.DesignationServiceConstants.DELETE;
import static com.ojas.obs.constants.DesignationServiceConstants.SAVE;


import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ojas.model.Designation;
import com.ojas.obs.dao.DesignationDao;
import com.ojas.obs.request.DesignationRequest;
import com.ojas.obs.response.DesignationResponse;
import com.ojas.utility.ErrorResponse;

@Service 
public class DesignationFacadeImpl implements DesignationFacade {     
 
	@Autowired 
	private DesignationDao designationDao;
	Logger logger = Logger.getLogger(this.getClass()); 

	@Override 
	public ResponseEntity<Object> setDesignation(DesignationRequest designationRequest) throws SQLException {
		logger.debug("inside set method : " + designationRequest);
		DesignationResponse designationResponse = null; 
		try {
			if (designationRequest.getTransactionType().equalsIgnoreCase(SAVE)) {
				designationResponse = new DesignationResponse();
				boolean saveDesignation = designationDao.saveDesignation(designationRequest);
				int count = designationDao.getAllDesignationCount();
				logger.debug("inside  save condition.****** : " + saveDesignation);
				if (saveDesignation) {
					designationResponse.setStatusMessage("Successfully record added");
					designationResponse.setTotalCount(count);
					return new ResponseEntity<>(designationResponse, HttpStatus.OK);
				} else { 
					designationResponse.setStatusMessage(FAILED); 
					return new ResponseEntity<>(designationResponse, HttpStatus.UNPROCESSABLE_ENTITY);
				}
			}
			if (designationRequest.getTransactionType().equalsIgnoreCase(UPDATE)) {
				designationResponse = new DesignationResponse();
				int count = designationDao.getAllDesignationCount();
				boolean updateDesignation = designationDao.updateDesignation(designationRequest);
				logger.debug("inside  update condition.****** : " + updateDesignation);
				if (updateDesignation) {
					designationResponse.setStatusMessage("Successfully record updated");
					designationResponse.setTotalCount(count);
					return new ResponseEntity<>(designationResponse, HttpStatus.OK);
				} else {
					designationResponse.setStatusMessage(FAILED);
					return new ResponseEntity<>(designationResponse, HttpStatus.UNPROCESSABLE_ENTITY);
				}
			}
			
			/*
			 * if (designationRequest.getTransactionType().equalsIgnoreCase(DELETE)) {
			 * boolean deleteDesignation = false; designationResponse = new
			 * DesignationResponse(); int count = designationDao.getAllDesignationCount();
			 * List<Designation> designationrequest = designationRequest.getDesignation();
			 * for (Designation designation : designationrequest) { deleteDesignation =
			 * designationDao.deleteDesignation(designation.getId());
			 * logger.debug("inside  delete condition.****** and id is : " +
			 * designation.getId()); System.out.println("Delete::" + deleteDesignation); }
			 * if (deleteDesignation) {
			 * designationResponse.setStatusMessage("Successfully record deleted");
			 * designationResponse.setTotalCount(count); return new
			 * ResponseEntity<>(designationResponse, HttpStatus.OK); } else {
			 * designationResponse.setStatusMessage(FAILED); return new
			 * ResponseEntity<>(designationResponse, HttpStatus.UNPROCESSABLE_ENTITY); } }
			 */
			
		} catch (Exception exception) {
			logger.debug("inside designationService catch block.****");
			ErrorResponse error = new ErrorResponse();
			logger.debug("data is  invalid");
			error.setStatusMessage(exception.getMessage());
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		} 
		return null;
	}
	/* 
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ojas.obs.facade.DesignationFacade#getDesignation(com.ojas.obs.request.
	 * DesignationRequest)
	 */

	@Override
	public ResponseEntity<Object> getDesignation(DesignationRequest designationRequest) throws SQLException {
		DesignationResponse designationResponse = new DesignationResponse();
		logger.debug("inside get in DesignationServiceImpl.***");

		List<Designation> designationlist = designationDao.getAll(designationRequest);

		if (designationlist.isEmpty() || designationlist == null) {
			designationResponse.setListDesignation(new ArrayList<>());
			designationResponse.setStatusMessage("No records found");
			designationResponse.setTotalCount(0);
			return new ResponseEntity<>(designationResponse, HttpStatus.UNPROCESSABLE_ENTITY);
		} else {
			int count = designationDao.getAllDesignationCount();
			designationResponse.setListDesignation(designationlist);
			designationResponse.setStatusMessage("success");
			designationResponse.setTotalCount(count);
			return new ResponseEntity<Object>(designationResponse, HttpStatus.OK);
		}

	}

} 
