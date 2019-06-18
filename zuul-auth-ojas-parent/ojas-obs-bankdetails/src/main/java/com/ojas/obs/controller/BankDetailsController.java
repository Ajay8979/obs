package com.ojas.obs.controller;


import static com.ojas.obs.constants.UserConstants.GET;
import static com.ojas.obs.constants.UserConstants.SET;

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

import com.ojas.obs.error.ErrorResponse;
import com.ojas.obs.facade.BankDetailsFacade;
import com.ojas.obs.model.BankDetails;
import com.ojas.obs.request.BankDetailsRequest;

/**
 * 
 * @author akrishna
 *
 */
@RestController
//@RequestMapping("/obs/BankDetails")
public class BankDetailsController {

	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private BankDetailsFacade bankDetailsFacade;

	/**
	 * 
	 * @param bankDetailsRequest
	 * @param servletRequest
	 * @param servletResponse
	 * @return
	 */
	@RequestMapping(SET)
	ResponseEntity<Object> setBankDetails(@RequestBody BankDetailsRequest bankDetailsRequest,
			HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		try {
			logger.debug("incoming request in setmethod" + bankDetailsRequest);

			List<BankDetails> listBankDetails = bankDetailsRequest.getBankDetails();

			if (listBankDetails.isEmpty() || listBankDetails == null) {
				ErrorResponse error = new ErrorResponse();
				error.setMessage("Data is null from request obj");
				error.setStatusCode("422");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			for (BankDetails bankDetails : listBankDetails) { 
				if (bankDetailsRequest.getTransactionType().equalsIgnoreCase("UPDATE")
						|| bankDetailsRequest.getTransactionType().equalsIgnoreCase("SAVE")) {
					if ((null == bankDetails.getBank_account_no() || bankDetails.getBank_account_no().isEmpty())
							|| (null == bankDetails.getBank_name() || bankDetails.getBank_name().isEmpty())
							|| (null == bankDetails.getBank_city() || bankDetails.getBank_city().isEmpty())
							|| (null == bankDetails.getBank_branch() || bankDetails.getBank_branch().isEmpty())
							|| (null == bankDetails.getBank_ifsc_code() || bankDetails.getBank_ifsc_code().isEmpty())
							|| (null == bankDetails.getBank_account_status()
									|| bankDetails.getBank_account_status().isEmpty())
							|| (null == bankDetails.getEmployee_id())) {
						ErrorResponse error = new ErrorResponse();
						error.setMessage("Data must not be null...");
						error.setStatusCode("422");
						return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
					}
				}

			}
			return bankDetailsFacade.setBankDetails(bankDetailsRequest);

		} catch (Exception exception) {
			logger.debug("inside catch block " + exception.getMessage());
			ErrorResponse error = new ErrorResponse();
			error.setMessage("Getting null Data in  get request");
			error.setStatusMessage(exception.getMessage());
			exception.printStackTrace();
			error.setStatusCode("422");
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	/**
	 * 
	 * @param bankDetailsRequest
	 * @param servletRequest
	 * @param servletResponse
	 * @return
	 */
	@RequestMapping(GET)
	ResponseEntity<Object> getBankDetails(@RequestBody BankDetailsRequest bankDetailsRequest,
			HttpServletRequest servletRequest, HttpServletResponse servletResponse) {

		try {
			
			logger.debug("incoming request in getmethod" + bankDetailsRequest);
			if (!(bankDetailsRequest.getTransactionType().equalsIgnoreCase("GETALL"))
					&& (bankDetailsRequest.getTransactionType().isEmpty())) {
				ErrorResponse error = new ErrorResponse();
				error.setMessage("Data must not be null");
				error.setStatusCode("422");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			
			 ResponseEntity<Object> bankDetails = bankDetailsFacade.getBankDetails(bankDetailsRequest);
			 logger.debug("response in getmethod" + bankDetails);
			return bankDetails;
		} catch (Exception exception) {
			logger.debug("inside get catch block " + exception.getMessage());
			ErrorResponse error = new ErrorResponse();
			error.setMessage("Getting null Data in  get request");
			error.setStatusMessage(exception.getMessage());
			error.setStatusCode("422");
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}
}
