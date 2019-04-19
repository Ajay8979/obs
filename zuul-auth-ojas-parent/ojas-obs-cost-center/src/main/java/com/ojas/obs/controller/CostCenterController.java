package com.ojas.obs.controller;

import static com.ojas.obs.constants.Constants.GET;
import static com.ojas.obs.constants.Constants.SET;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.obs.facade.CostCenterFacade;
import com.ojas.obs.model.CostCenter;
import com.ojas.obs.model.CostCenterRequest;
import com.ojas.obs.utility.ErrorResponse;

@RestController
public class CostCenterController {

	@Autowired
	private CostCenterFacade costCenterService;

	Logger logger = Logger.getLogger(this.getClass());

	@PostMapping(SET)
	public ResponseEntity<Object> set(@RequestBody CostCenterRequest codeCenterRequest, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.debug("Enter the controller set method...");
	
		ResponseEntity<Object> responseEntity = null;
		ErrorResponse error = new ErrorResponse();
		String sessionId = null;

		logger.debug("conrverting the JSON object to String object...");

		if (codeCenterRequest == null) {
			error = new ErrorResponse();
			logger.debug("checking object is null or not...");

			error.setMessage("Requestobj is not valid");
			error.setStatusCode("422");
			return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);

		}
		List<CostCenter> costCenter = codeCenterRequest.getCostCenter();
		sessionId = codeCenterRequest.getSessionId();
		if (!codeCenterRequest.getTransactionType().equalsIgnoreCase("DELETE")) {
			for (CostCenter costCenter2 : costCenter) {

				if (costCenter2.getCostCenterCode() == 0) {
					logger.debug("checking costcentercode is zero or ...");

					error = new ErrorResponse();
					error.setMessage("Request is  invalid????");
					return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
				}
			}
		}
		if (null == codeCenterRequest.getTransactionType() || codeCenterRequest.getTransactionType().isEmpty()) {
			logger.debug("checking transactiontype equal to null or empty...");

			error = new ErrorResponse();
			error.setMessage("transationType is not valid");
			error.setStatusCode("422");
			return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}

		List<CostCenter> costCenterObj = codeCenterRequest.getCostCenter();

		if ((codeCenterRequest.getTransactionType().equalsIgnoreCase("SAVE")||codeCenterRequest.getTransactionType().equalsIgnoreCase("UPDATE")
				|| codeCenterRequest.getTransactionType().equalsIgnoreCase("DELETE"))) {
				ResponseEntity<Object> set2 = costCenterService.set(codeCenterRequest);

			return set2;


		} else {
			error = new ErrorResponse();
			error.setMessage("Request is  invalid....");
			return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		
		}
	}

	@PostMapping(GET)
	public ResponseEntity<Object> get(@RequestBody CostCenterRequest costCenterRequest, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.debug("Enter the controller get method...");

		ResponseEntity<Object> responseEntity = null;
		// CostCenterRequest costCenterRequestObject = null;
		String sessionId = null;
		ErrorResponse error = null;

			if (costCenterRequest == null) {
				error = new ErrorResponse();
				error.setMessage("Requestobj is not valid");
				error.setStatusCode("422");
				return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			} else {
				sessionId = costCenterRequest.getSessionId();
			}
			if (null == sessionId || sessionId.isEmpty()) {

				error = new ErrorResponse();
				error.setMessage("sessionId can't be null or empty");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}

			costCenterService.get(costCenterRequest);

		
		return costCenterService.get(costCenterRequest);
	}

}
