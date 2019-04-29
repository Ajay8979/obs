package com.ojas.obs.controller;

import static com.ojas.obs.constants.DesignationServiceConstants.SET;
import static com.ojas.obs.constants.DesignationServiceConstants.DESIGNATION;
import static com.ojas.obs.constants.DesignationServiceConstants.GET;

//import java.awt.peer.DesktopPeer;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.log4j.Logger;

import com.ojas.model.Designation;
//import com.google.gson.Gson;
import com.ojas.obs.facade.DesignationFacade;
import com.ojas.obs.request.DesignationRequest;
import com.ojas.utility.ErrorResponse;

/**
 * 
 * @author nsrikanth
 *
 */

@RestController
//@RequestMapping(DESIGNATION)
public class DesignationController {

	@Autowired
	private DesignationFacade designationFacade;
	Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 
	 * @param designationRequest
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException
	 */

	@PostMapping(SET)
	public ResponseEntity<Object> setDesignation(@RequestBody DesignationRequest designationRequest,
			HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String sessionId = null;
		logger.debug("incoming requests " + designationRequest);
		List<Designation> listDesignation = designationRequest.getDesignation();

		if (listDesignation == null || listDesignation.isEmpty()) {
			ErrorResponse error = new ErrorResponse();
			logger.debug("request is not valid");
			error.setStatusMessage("designationRequestobj is not valid"); 
			error.setStatusCode("422");
			return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		} else {
			sessionId = designationRequest.getSessionId();
			logger.debug("incoming request " + sessionId);
		}
		try {
			for (Designation des : listDesignation) {

				if ((designationRequest.getTransactionType().equalsIgnoreCase("update")
						|| designationRequest.getTransactionType().equalsIgnoreCase("delete")) && null == des.getId()) {
					ErrorResponse error = new ErrorResponse();
					logger.debug("data is  invalid");
					error.setStatusMessage("id can't be null");
					error.setStatusCode("422");
					return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
				}
			}
			return designationFacade.setDesignation(designationRequest);

		} catch (Exception exception) {
			logger.debug("inside catch block.*******");
			ErrorResponse error = new ErrorResponse();
			error.setStatusMessage(exception.getMessage());
			error.setStatusCode("409");
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}

	}

	/**
	 * 
	 * @param designationRequest 
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException
	 */

	@PostMapping(GET)
	public ResponseEntity<Object> getDesignation(@RequestBody DesignationRequest designationRequest,
			HttpServletRequest request, HttpServletResponse response) throws SQLException {

		String sessionId = null;
		
		try {

			logger.debug("requestObject received = " + designationRequest);
 
			if (designationRequest == null) {
				ErrorResponse error = new ErrorResponse();
				error.setStatusMessage("desRequestobj is not valid");
				error.setStatusCode("422");
				return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			} else {
				sessionId = designationRequest.getSessionId();
				logger.debug("incoming request " + sessionId);
			}
			return designationFacade.getDesignation(designationRequest);

		} catch (Exception exception) {
			ErrorResponse error = new ErrorResponse();
			error.setStatusMessage(exception.getMessage()); 
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);

		}
	}
}
 