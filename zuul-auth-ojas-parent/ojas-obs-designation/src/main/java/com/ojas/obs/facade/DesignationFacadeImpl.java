package com.ojas.obs.facade;

import static com.ojas.obs.constants.DesignationServiceConstants.FAILED;
import static com.ojas.obs.constants.DesignationServiceConstants.UPDATE;
//import static com.ojas.obs.constants.DesignationServiceConstants.DELETE;
import static com.ojas.obs.constants.DesignationServiceConstants.SAVE;
import static com.ojas.obs.constants.DesignationServiceConstants.GETALL;
import static com.ojas.obs.constants.DesignationServiceConstants.GETBYID;
import static com.ojas.obs.constants.DesignationServiceConstants.SUCCESS;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.ojas.obs.dao.DesignationDao;
import com.ojas.obs.model.Designation;
import com.ojas.obs.request.DesignationRequest;
import com.ojas.obs.response.DesignationResponse;
import com.ojas.obs.utility.ErrorResponse;


@Service
public class DesignationFacadeImpl implements DesignationFacade {

	@Autowired
	private DesignationDao designationDao;
	Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ResponseEntity<Object> setDesignation(DesignationRequest designationRequest) throws SQLException {
		logger.info("The requests inside DesignationFacadeImpl setDesignation method : " + designationRequest);
		DesignationResponse designationResponse = null;
		
			if (designationRequest.getTransactionType().equalsIgnoreCase(SAVE)) {
				designationResponse = new DesignationResponse();
				boolean saveDesignation = designationDao.saveDesignation(designationRequest);
				int count = designationDao.getAllDesignationCount();
				logger.debug("Inside DesignationFacadeImpl save condition.****** : " + saveDesignation);
				if (saveDesignation) {
					designationResponse.setMessage("record added Successfully");
					designationResponse.setStatusCode("200");
					return new ResponseEntity<>(designationResponse, HttpStatus.OK);
				} else {
					logger.error("Inside DesignationFacadeImpl save condition.****** : " + saveDesignation);
					designationResponse.setMessage(FAILED);
					return new ResponseEntity<>(designationResponse, HttpStatus.UNPROCESSABLE_ENTITY);
				}
			}
			if (designationRequest.getTransactionType().equalsIgnoreCase(UPDATE)) {
				designationResponse = new DesignationResponse();
				int count = designationDao.getAllDesignationCount();
				boolean updateDesignation = designationDao.updateDesignation(designationRequest);
				logger.debug("Inside DesignationFacadeImpl update condition.****** : " + updateDesignation);
				if (updateDesignation) {
					designationResponse.setMessage("record updated Successfully ");
					designationResponse.setStatusCode("200");
					return new ResponseEntity<>(designationResponse, HttpStatus.OK);
				} else {
					logger.error("Inside DesignationFacadeImpl update condition.****** : " + updateDesignation);
					designationResponse.setMessage(FAILED);
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
		logger.info("The requests inside getDesignation in DesignationFacadeImpl " + designationRequest);
		
		

		if (designationRequest.getTransactionType().equalsIgnoreCase(GETALL)) {

			List<Designation> designationlist = designationDao.getAll(designationRequest);
			logger.debug("Inside DesignationFacadeImpl getAll condition " +designationRequest);
			if (designationlist.isEmpty() || designationlist == null) {
				logger.error("Inside DesignationFacadeImpl  listsize is zero " +designationRequest);
				designationResponse.setListDesignation(new ArrayList<>());
				designationResponse.setMessage("No records found");
				
				return new ResponseEntity<>(designationResponse, HttpStatus.UNPROCESSABLE_ENTITY);
			} else {
				
				int count = designationDao.getAllDesignationCount();
				designationResponse.setListDesignation(designationlist);
				designationResponse.setMessage("success");
				designationResponse.setStatusCode("200");
				return new ResponseEntity<Object>(designationResponse, HttpStatus.OK);
			}
		}
		if (designationRequest.getTransactionType().equalsIgnoreCase(GETBYID)) {
			designationResponse = new DesignationResponse();
			List<Designation> list = designationDao.getById(designationRequest);
			logger.debug("Inside DesignationFacadeImpl getById condition " +designationRequest);
			if (list.size() == 0) {
				logger.error("Inside DesignationFacadeImpl in list is no_records  " +designationRequest);
				designationResponse.setMessage("No record Present");
				
				return new ResponseEntity<>(designationResponse, HttpStatus.CONFLICT);
			} else {
				logger.debug("Inside DesignationFacadeImpl  list is " +designationRequest);
				designationResponse.setMessage(SUCCESS);
				designationResponse.setStatusCode("200");
				designationResponse.setListDesignation(list);
				return new ResponseEntity<>(designationResponse, HttpStatus.OK);
			}
		}
		
		

		return new ResponseEntity<Object>(designationResponse, HttpStatus.OK);
	
		
		
	}
}
