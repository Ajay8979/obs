package com.ojas.obs.facade;

import static com.ojas.obs.constants.GpaServiceConstants.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ojas.obs.dao.GpaPlanDao;
import com.ojas.obs.errorMessage.ErrorResponse;
import com.ojas.obs.model.GpaPlan;
import com.ojas.obs.request.GpaRequest;
import com.ojas.obs.response.GpaResponse;

@Service
public class GpaFacade {

	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	GpaPlanDao gpaPlanDaoImpl;

	/**
	 * 
	 * @param gpaRequest
	 * @return
	 * @throws SQLException
	 */
	public ResponseEntity<Object> saveGpaPlan(GpaRequest gpaRequest) throws SQLException {

		logger.debug("inside saveGpa method : " + gpaRequest);
		GpaResponse gpaResponse = null;
		try {

			if (gpaRequest.getTransactionType().equalsIgnoreCase(SAVE)) {
				gpaResponse = new GpaResponse();
				boolean saveGpa = gpaPlanDaoImpl.saveGpaPlan(gpaRequest);
				logger.debug("inside  save condition.****** : " + saveGpa);
				if (saveGpa) {
					gpaResponse.setStatusMessage("Success fully record added");
					return new ResponseEntity<>(gpaResponse, HttpStatus.OK);
				} else {
					gpaResponse.setStatusMessage(FAILED);
					return new ResponseEntity<>(gpaResponse, HttpStatus.CONFLICT);
				}
			}

			if (gpaRequest.getTransactionType().equalsIgnoreCase(UPDATE)) {
				gpaResponse = new GpaResponse();
				boolean updateGpa = gpaPlanDaoImpl.updateGpa(gpaRequest);
				if (updateGpa) {
					gpaResponse.setStatusMessage("Success fully record updated");
					return new ResponseEntity<>(gpaResponse, HttpStatus.OK);
				} else {
					gpaResponse.setStatusMessage(FAILED);
					return new ResponseEntity<>(gpaResponse, HttpStatus.CONFLICT);
				}
			}

//			if (gpaRequest.getTransactionType().equalsIgnoreCase(DELETE)) {
//				gpaResponse = new GpaResponse();
//				Integer planId = gpaRequest.getGpaPlan().getGpaPlanId();
//				boolean deleteGpaRecord = gpaPlanDaoImpl.deleteGpaRecord(planId);
//
//				logger.debug("inside  delete condition.****** and id is : " + planId);
//				if (deleteGpaRecord) {
//					gpaResponse.setStatusMessage("Success fully record deleted");
//					return new ResponseEntity<>(gpaResponse, HttpStatus.OK);
//				} else {
//					gpaResponse.setStatusMessage(FAILED);
//					return new ResponseEntity<>(gpaResponse, HttpStatus.CONFLICT);
//				}
			// }
		} catch (Exception exception) {
			logger.debug("inside gpaFacade catch block.****");
			ErrorResponse error = new ErrorResponse();
			logger.debug("data is  invalid");
			error.setStatusMessage(exception.getCause().getLocalizedMessage()); 
			//error.setMessage(exception.getMessage());
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}
		return null;
	}

	/**
	 * 
	 * @param gpaRequest
	 * @return
	 * @throws SQLException
	 */
	public ResponseEntity<Object> getAllGpaDetails(GpaRequest gpaRequest) throws SQLException {
		GpaResponse gpaResponse = new GpaResponse();



		try {
		
		if (gpaRequest.getTransactionType().equalsIgnoreCase(GETALL)) {
		
		logger.debug("inside getAllGpaDetails in gpaFacade.***");

		List<GpaPlan> allGpaDetails = gpaPlanDaoImpl.getAllGpaDetails(gpaRequest);

		/*
		 * List<GpaPlan> recordsPerPage = gpaPlanDaoImpl.getPageRecords(allGpaDetails,
		 * gpaRequest.getPageSize(), gpaRequest.getPageNum());
		 */
		if (allGpaDetails == null || allGpaDetails .isEmpty()) {
			gpaResponse.setGpa(new ArrayList<>());
			gpaResponse.setStatusMessage("No records found");
			gpaResponse.setTotalCount(0);
			return new ResponseEntity<>(gpaResponse, HttpStatus.CONFLICT);
		} else {
			  
		//	if (gpaRequest.getPageNum() == 0 || gpaRequest.getPageSize() == 0) {
				int count = gpaPlanDaoImpl.getAllGpaDetailsCount();
				
				gpaResponse.setGpa(allGpaDetails);
				gpaResponse.setStatusMessage("Success");
				gpaResponse.setTotalCount(count);
				return new ResponseEntity<>(gpaResponse, HttpStatus.OK);
			} /*else {
				int count = gpaPlanDaoImpl.getAllGpaDetailsCount();
				gpaResponse.setGpa(recordsPerPage);
				gpaResponse.setStatusMessage("Success");
				gpaResponse.setPageNum(gpaRequest.getPageNum());
				gpaResponse.setPageSize(gpaRequest.getPageSize());

				gpaResponse.setTotalCount(count);
			}

*/		//return new ResponseEntity<>(gpaResponse, HttpStatus.OK);

		}
		if (gpaRequest.getTransactionType().equalsIgnoreCase(GETID)) {
			
			logger.debug("inside getByidGpaDetails in gpaFacade.***");
            // System.out.println("Inside facade"+gpaRequest.getGpaPlan().get(0).getGpaPlanId());
			List<GpaPlan> allGpaDetails = gpaPlanDaoImpl.getById(gpaRequest.getGpaPlan().get(0).getGpaPlanId());
			if (allGpaDetails == null || allGpaDetails .isEmpty()) {
				gpaResponse.setGpa(new ArrayList<>());
				gpaResponse.setStatusMessage("No records found");
				gpaResponse.setTotalCount(0);
				return new ResponseEntity<>(gpaResponse, HttpStatus.CONFLICT);
			}
			else {
				int count = gpaPlanDaoImpl.getAllGpaDetailsCount();
				gpaResponse.setGpa(allGpaDetails);
				gpaResponse.setStatusMessage("Success");
				gpaResponse.setTotalCount(count);
				return new ResponseEntity<>(gpaResponse, HttpStatus.OK);
			}
			//return new ResponseEntity<>(gpaResponse, HttpStatus.OK);
			
		}
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
		
		
	}

}
