package com.ojas.obs.controller;

import static com.ojas.obs.utility.Constants.DELETE;
import static com.ojas.obs.utility.Constants.GET;
import static com.ojas.obs.utility.Constants.SAVE;
import static com.ojas.obs.utility.Constants.SET;
import static com.ojas.obs.utility.Constants.UPDATE;
//import static com.ojas.obs.utility.Constants.KYE;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.obs.facade.KyeFacade;
import com.ojas.obs.model.KYE;
import com.ojas.obs.request.KYERequest;
import com.ojas.obs.utility.ErrorResponse;

/**
 * 
 * @author tshiva
 *
 */

@RestController
//@RequestMapping(KYE)
public class KyeController {

	@Autowired
	private KyeFacade kyeFacade;
	Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 
	 * @param kyerequest
	 * @param request
	 * @param response
	 * @return
	 */

	@PostMapping(SET)
	public ResponseEntity<Object> setKye(@RequestBody KYERequest kyeRequestObj, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("@request in string" + kyeRequestObj);
		List<KYE> kyeList = kyeRequestObj.getKye();
		for (KYE kye : kyeList) {
			try {
				if (kye == null) {
					logger.debug("@request is not valid");
					ErrorResponse errorResponse = new ErrorResponse();
					errorResponse.setMessage("kyeRequestObj is null");
					errorResponse.setStatusCode("422");
					return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
				}
				if ((kyeRequestObj.getTransactionType().equalsIgnoreCase(SAVE)
						|| kyeRequestObj.getTransactionType().equalsIgnoreCase(UPDATE))
						&& ((null == kye.getkYE_Type() || kye.getkYE_address().isEmpty())
						|| (null == kye.getkYE_Type() || kye.getkYE_Type().isEmpty())
						|| (null == kye.getPassport_address() || kye.getPassport_address().isEmpty())
						|| (null == kye.getPassport_no() || kye.getPassport_no().isEmpty())
						|| (null == kye.getPlace_of_issue() || kye.getPlace_of_issue().isEmpty())
						|| (null == kye.getUan() || kye.getUan().isEmpty()) || null == kye.getEmployee_Id() || kye.getEmployee_Id().isEmpty())) {
					logger.debug("@fields is not valid");
					ErrorResponse error = new ErrorResponse();
					error.setMessage("fields can't be null");
					error.setStatusCode("422");
					return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
				}

				if (null == kyeRequestObj.getTransactionType() || kyeRequestObj.getTransactionType().isEmpty()) {
					logger.debug("@transactionType is not valid");
					ErrorResponse error = new ErrorResponse();
					error.setMessage("transactionType can't be null");
					return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
				}
				if ((kyeRequestObj.getTransactionType().equalsIgnoreCase(UPDATE)
						|| kyeRequestObj.getTransactionType().equalsIgnoreCase(DELETE)) && kye.getId() == 0) {
					logger.debug("@id is null");
					ErrorResponse error = new ErrorResponse();
					error.setMessage("id can't be null");
					error.setStatusCode("422");
					return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
				}
				return kyeFacade.setKYE(kyeRequestObj);

			} catch (Exception exception) {
			logger.debug("inside CostCenterController-SQLException catch block.****");
			ErrorResponse error = new ErrorResponse();
			logger.debug("Exception is  invalid");
			error.setMessage("Exception");
			error.setStatusMessage(exception.getCause().getMessage());
			error.setStatusCode("409");
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}

		}
		return null;
	}

	/**
	 * 
	 * @param kyerequest
	 * @param request
	 * @param response
	 * @return
	 */

	@PostMapping(GET)
	public ResponseEntity<Object> getKey(@RequestBody KYERequest kyeRequestObj, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("@request in string" + kyeRequestObj);
		try {
			logger.debug("@kyeRequestObj::" + kyeRequestObj);
			if (kyeRequestObj == null) {
				logger.debug("@request is not valid");
				ErrorResponse errorResponse = new ErrorResponse();
				errorResponse.setMessage("kyeRequestObj is null");
				errorResponse.setStatusCode("422");
				return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);

			}
			if (null == kyeRequestObj.getTransactionType() || kyeRequestObj.getTransactionType().isEmpty()) {
				logger.debug("transaction is not valid");
				ErrorResponse error = new ErrorResponse();
				error.setMessage("transactionType can't be null");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			return kyeFacade.getKYE(kyeRequestObj);
		} catch (Exception exception) {
		logger.debug("inside CostCenterController-SQLException catch block.****");
		ErrorResponse error = new ErrorResponse();
		logger.debug("Exception is  invalid");
		error.setMessage("Exception");
		error.setStatusMessage(exception.getCause().getMessage());
		error.setStatusCode("409");
		return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	}
}