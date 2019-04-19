package com.ojas.obs.controller;


import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.ojas.obs.facade.BusinessUnitFacade;
import com.ojas.obs.model.BusinessUnitRequest;
import com.ojas.obs.util.ErrorRespponse;


@RestController
public class BusinessUnitController {
	@Autowired
	BusinessUnitFacade businessUnitFacade;
	Logger logger = Logger.getLogger(this.getClass());

	
	
	@PostMapping("/set")
	public ResponseEntity<Object> setBusinessUnit(@RequestBody String busiessUnitRequest,
			HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws SQLException {
		
		String sessionId = null;
		logger.debug("incoming request " + busiessUnitRequest);
		try {
			Gson gson = new Gson();
			BusinessUnitRequest businessUnitRequestobject = gson.fromJson(busiessUnitRequest,
					BusinessUnitRequest.class);
			if (null == businessUnitRequestobject) {

				ErrorRespponse error = new ErrorRespponse();
				logger.debug("businessUnitRequestobject is not valid");
				error.setMessage("invalid data");
				error.setCode("422");
				return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);

			} else {
				sessionId = businessUnitRequestobject.getSessionId();
				logger.debug("incoming request " + sessionId);
			}
			if (null == businessUnitRequestobject.getBusinessUnit().getBusinessUnitName()
					|| businessUnitRequestobject.getBusinessUnit().getBusinessUnitName().isEmpty()
					|| 0 == businessUnitRequestobject.getBusinessUnit().getCostCenterId()) {
				com.ojas.obs.util.ErrorRespponse error = new com.ojas.obs.util.ErrorRespponse();
				error.setMessage("fields must not be null");
				logger.debug("data is not valid");
				error.setCode("422");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			if (null == businessUnitRequestobject.getTransactionType()
					|| businessUnitRequestobject.getTransactionType().isEmpty()) {
				ErrorRespponse error = new ErrorRespponse();
				error.setMessage("transationType is not valid");
				error.setCode("422");
				return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			if ((businessUnitRequestobject.getTransactionType().equalsIgnoreCase("UPDATE")
					|| businessUnitRequestobject.getTransactionType().equalsIgnoreCase("DELETE"))
					&& 0 == businessUnitRequestobject.getBusinessUnit().getId()) {
				com.ojas.obs.util.ErrorRespponse error = new com.ojas.obs.util.ErrorRespponse();
				logger.debug("TransactionType is  invalid");
				error.setMessage("id is invalid");
				error.setCode("422");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);

			}
			return businessUnitFacade.setBusinessUnit(businessUnitRequestobject);

		} catch (Exception exception) {
			logger.debug("inside catch block.*******");
			com.ojas.obs.util.ErrorRespponse error = new com.ojas.obs.util.ErrorRespponse();
			error.setMessage(exception.getMessage());
			error.setCode("409");
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}
	}

	@PostMapping("/get")
	public ResponseEntity<Object> getBusinessUnit(@RequestBody String businessUnitRequestObj,
			HttpServletRequest request, HttpServletResponse response) throws SQLException {

		String sessionId = null;
		try {
			Gson gson = new Gson();
			logger.debug("requestObject received = " + businessUnitRequestObj);
			BusinessUnitRequest businessUnitRequest = gson.fromJson(businessUnitRequestObj, BusinessUnitRequest.class);
			if (businessUnitRequest == null) {
				ErrorRespponse error = new ErrorRespponse();
				error.setMessage("statesRequestObj is not valid");
				error.setCode("422");
				return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			} else {
				sessionId = businessUnitRequest.getSessionId();
				logger.debug("incoming request " + sessionId);
			}
			return businessUnitFacade.getBusinessUnit(businessUnitRequest);
		} catch (Exception exception) {
			ErrorRespponse error = new ErrorRespponse();
			error.setMessage(exception.getMessage());
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}
	}
}