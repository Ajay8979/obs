package com.ojas.obs.controller;

//import static com.ojas.obs.constants.GpaServiceConstants.DELETE;
import static com.ojas.obs.constants.GpaServiceConstants.GET;
import static com.ojas.obs.constants.GpaServiceConstants.SAVE;
import static com.ojas.obs.constants.GpaServiceConstants.SET;
import static com.ojas.obs.constants.GpaServiceConstants.UPDATE;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.obs.errorMessage.ErrorResponse;
import com.ojas.obs.facade.GpaFacade;
import com.ojas.obs.model.GpaPlan;
import com.ojas.obs.request.GpaRequest;

@RestController
public class GpaController {

	@Autowired
	GpaFacade gpaFacade;

	Logger logger = Logger.getLogger(this.getClass());
	String transactionType = null;

	/**
	 * 
	 * @param gpaRequest
	 * @param servletRequest
	 * @param servletResponse
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(SET)
	public ResponseEntity<Object> saveGpa(@RequestBody GpaRequest gpaRequest, HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) throws SQLException {
		String sessionId = null;

		logger.debug("incoming requests " + gpaRequest);
		try {
			// Gson gson = new Gson();
			// GpaRequest gpaRequestObject = gson.fromJson(gpaRequest, GpaRequest.class);

			if (null == gpaRequest) {
				ErrorResponse error = new ErrorResponse();
				logger.debug("request is not valid");
				error.setMessage("Request is not a valid");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			sessionId = gpaRequest.getSessionId();
			logger.debug("incoming request " + sessionId);

			List<GpaPlan> gpa = gpaRequest.getGpaPlan();

			if (null == gpa) {
				ErrorResponse error = new ErrorResponse();
				error.setMessage("data must not be null");
				logger.debug("data is not valid");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			transactionType = gpaRequest.getTransactionType();

			for (GpaPlan plan : gpa) {
				if (transactionType.equalsIgnoreCase(SAVE) && ((null == plan.getGpaPlanType()
						|| plan.getGpaPlanType().isEmpty()) || (null == plan.getGpaPremium())
						|| (null == plan.getTotalPremium())
						|| (gpaRequest.getTransactionType() == null || (gpaRequest.getTransactionType().isEmpty())))) {
					ErrorResponse error = new ErrorResponse();
					logger.debug("data is  invalid");
					error.setMessage("Request is  invalid");
					return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);

				}

				if (((transactionType.equalsIgnoreCase(UPDATE))) && null == plan.getGpaPlanId()) {
					ErrorResponse errorResponse = new ErrorResponse();
					errorResponse = new ErrorResponse();
					errorResponse.setStatusCode("422");
					errorResponse.setMessage("invalid");
					logger.info("Request is not valid, No id provided");
					return new ResponseEntity<Object>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
				}
			}

			return gpaFacade.saveGpaPlan(gpaRequest);
		} catch (Exception e) {
			logger.debug("inside catch block.*******");
			ErrorResponse error = new ErrorResponse();
			error.setMessage(e.getMessage());
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}
		

	}

	/**
	 * 
	 * @param gpaPlanRequest
	 * @param servletRequest
	 * @param servletResponse
	 * @return
	 * @throws SQLException
	 */

	@RequestMapping(GET)
	public ResponseEntity<Object> getgpaDetails(@RequestBody GpaRequest gpaRequest, HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) throws SQLException {

		ResponseEntity<Object> responseEntity = null; // GpaRequest gpaRequest = null;
		String sessionId = null;
		try { 
			
			logger.debug("requestObject received = "); 
			
			if (null == gpaRequest) {

				ErrorResponse error = new ErrorResponse();
				logger.debug("data is not valid");
				error.setMessage("Request is not valid");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			sessionId = gpaRequest.getSessionId();
			logger.debug("incoming request " + sessionId);

			return gpaFacade.getAllGpaDetails(gpaRequest);

		} catch (Exception exception) {
			logger.debug("inside catch block.*******");

			ErrorResponse error = new ErrorResponse();
			error.setStatusMessage(exception.getMessage());
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}
		
	}

}

