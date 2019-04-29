package com.ojas.obs.controller;
import static com.ojas.obs.constants.Constants.DELETE;
import static com.ojas.obs.constants.Constants.GET;
import static com.ojas.obs.constants.Constants.SET;
import static com.ojas.obs.constants.Constants.UPDATE;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.obs.facade.StatesFacade;
import com.ojas.obs.model.ErrorResponse;
import com.ojas.obs.model.States;
import com.ojas.obs.request.StatesRequest;

/**
 * @author spuja
 *
 */
@RestController
//@RequestMapping(STATES)
public class StatesController {

	@Autowired
	private StatesFacade statesFacade;
	Logger logger = Logger.getLogger(this.getClass());

	@PostMapping(SET)
	public ResponseEntity<Object> setStates(@RequestBody StatesRequest statesRequestObj,
			HttpServletRequest servletRequest, HttpServletResponse servletResponse){
		String sessionId = null;
		logger.debug("incoming requests " + statesRequestObj);
		
		try {
			if (statesRequestObj == null) {
				ErrorResponse error = new ErrorResponse();
				logger.debug("request is not valid");
				error.setMessage("statesRequestObj is not valid");
				error.setStatusCode("422");
				return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			} else {
				sessionId = statesRequestObj.getSessionId();
				logger.debug("incoming request " + sessionId);
			}
			if (statesRequestObj.getSessionId() == null) {
				ErrorResponse error = new ErrorResponse();
				logger.debug("session id check");
				error.setMessage("session id must be provided");
				error.setStatusCode("422");
				return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			if (statesRequestObj.getTransactionType() == null) {
				ErrorResponse error = new ErrorResponse();
				logger.debug("transaction type check");
				error.setMessage("transactionType must be provided");
				error.setStatusCode("422");
				return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			for(States states: statesRequestObj.getStates()) {
					if (null ==states.getStateName()|| states.getStateName().isEmpty()) {
						ErrorResponse error = new ErrorResponse();
						error.setMessage("states can't be null or empty");
						error.setStatusCode("422");
						return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
					}
			}
			for(States states: statesRequestObj.getStates()) {
				if ((statesRequestObj.getTransactionType().equalsIgnoreCase(UPDATE)|| statesRequestObj.getTransactionType().equalsIgnoreCase(DELETE))&& 0 == states.getId()) {
				ErrorResponse error = new ErrorResponse();
				logger.debug("data is  invalid");
				error.setMessage("Request is  invalid");
				error.setStatusCode("422");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			}
			
            return statesFacade.setStates(statesRequestObj);

		}catch (DuplicateKeyException exception) {
			logger.debug("inside catch block.*******");
			ErrorResponse error = new ErrorResponse();
			error.setStatusMessage(exception.getCause().getLocalizedMessage());
			//error.setStatusCode("409");
			exception.printStackTrace();
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	  }
		catch (SQLException exception) {
			logger.debug("inside catch SQLblock.*******");
			ErrorResponse error = new ErrorResponse();
			error.setStatusMessage(exception.getMessage());
			//error.setStatusCode("409");
			exception.printStackTrace();
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	  }
		catch (Exception exception) {
			logger.debug("inside catch Exception block.*******");
			ErrorResponse error = new ErrorResponse();
			error.setStatusMessage(exception.getMessage());
			//error.setStatusCode("409");
			exception.printStackTrace();
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	  }
	}

	@PostMapping(GET)
	public ResponseEntity<Object> getStates(@RequestBody StatesRequest statesRequestObj) {
        String sessionId = null;
		try {
			logger.debug("requestObject received = " + statesRequestObj);
			if (statesRequestObj == null) {
				ErrorResponse error = new ErrorResponse();
				error.setMessage("statesRequestObj is not valid");
				error.setStatusCode("422");
				return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			} else {
				sessionId = statesRequestObj.getSessionId();
				logger.debug("incoming request " + sessionId);
			}
			return statesFacade.getStates(statesRequestObj);
		} catch (SQLException exception) {
			ErrorResponse error = new ErrorResponse();
			error.setStatusMessage(exception.getMessage());
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}
		catch (Exception exception) {
			ErrorResponse error = new ErrorResponse();
			error.setStatusMessage(exception.getMessage());
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}
	}
}