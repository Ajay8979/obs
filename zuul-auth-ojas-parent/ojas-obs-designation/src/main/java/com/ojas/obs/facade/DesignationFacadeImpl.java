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
		logger.debug("inside set method : " + designationRequest);
		DesignationResponse designationResponse = null;
		try {
			if (designationRequest.getTransactionType().equalsIgnoreCase(SAVE)) {
				designationResponse = new DesignationResponse();
				boolean saveDesignation = designationDao.saveDesignation(designationRequest);
				int count = designationDao.getAllDesignationCount();
				logger.debug("inside  save condition.****** : " + saveDesignation);
				if (saveDesignation) {
					designationResponse.setStatusMessage("record added Successfully");
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
					designationResponse.setStatusMessage("record updated Successfully ");
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
			error.setStatusMessage(exception.getCause().getLocalizedMessage());
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
		
		try{

		if (designationRequest.getTransactionType().equalsIgnoreCase(GETALL)) {

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
		if (designationRequest.getTransactionType().equalsIgnoreCase(GETBYID)) {
			designationResponse = new DesignationResponse();
			List<Designation> list = designationDao.getById(designationRequest);
			logger.debug("inside  get_count condition.****** : ");
			if (list.size() == 0) {
				designationResponse.setStatusMessage("No record Present");
				designationResponse.setTotalCount(0);
				return new ResponseEntity<>(designationResponse, HttpStatus.CONFLICT);
			} else {
				designationResponse.setStatusMessage(SUCCESS);
				designationResponse.setListDesignation(list);
				return new ResponseEntity<>(designationResponse, HttpStatus.OK);
			}
		}
		
		

		return new ResponseEntity<Object>(designationResponse, HttpStatus.OK);
	}
		catch (Exception exception) {
			logger.debug("inside designationService catch block.****");
			ErrorResponse error = new ErrorResponse();
			logger.debug("data is  invalid");
			 error.setStatusMessage(exception.getCause().getLocalizedMessage());
			error.setStatusMessage("409");
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
}
		
	}
}
