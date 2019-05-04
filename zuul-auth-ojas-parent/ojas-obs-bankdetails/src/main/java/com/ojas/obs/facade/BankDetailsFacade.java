package com.ojas.obs.facade;

import static com.ojas.obs.constants.UserConstants.DELETE;
import static com.ojas.obs.constants.UserConstants.FAILED;
import static com.ojas.obs.constants.UserConstants.SAVE;
import static com.ojas.obs.constants.UserConstants.UPDATE;
import static com.ojas.obs.constants.UserConstants.SUCCESS;
import static com.ojas.obs.constants.UserConstants.NORECORDS;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ojas.obs.dao.BankDetailsDAO;
import com.ojas.obs.error.ErrorResponse;
import com.ojas.obs.model.BankDetails;
import com.ojas.obs.request.BankDetailsRequest;
import com.ojas.obs.response.BankDetailsResponse;

/**
 * 
 * @author akrishna
 *
 */

@Service
public class BankDetailsFacade {

	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private BankDetailsDAO bankDetailsDAO;

	/**
	 * 
	 * @param bankDetailsRequest
	 * @return
	 */
	public ResponseEntity<Object> setBankDetails(BankDetailsRequest bankDetailsRequest) {
		// ResponseEntity<Object> responseEntity = null;
		BankDetailsResponse bankDetailsResponse = null;
		logger.debug("incomming request in facade " + bankDetailsRequest);
		try {
			if (bankDetailsRequest.getTransactionType().equalsIgnoreCase(SAVE)) {
				logger.debug("Transactiontype is  " + SAVE);
				bankDetailsResponse = new BankDetailsResponse();
				boolean saveBank = bankDetailsDAO.saveEmployeeBankDetails(bankDetailsRequest);

				if (saveBank) {
					logger.debug("object stored in db " + saveBank);
					int bankDetailsCount = bankDetailsDAO.getAllEmployeeBankDetailsCount();
					bankDetailsResponse.setTotalCount(bankDetailsCount);
					bankDetailsResponse.setStatusMessage("BankDetails record is saved Successfully");
					return new ResponseEntity<>(bankDetailsResponse, HttpStatus.OK);
				}
			}
			if (bankDetailsRequest.getTransactionType().equalsIgnoreCase(UPDATE)) {
				logger.debug("Transactiontype is  " + UPDATE);
				bankDetailsResponse = new BankDetailsResponse();
				boolean updateBank = bankDetailsDAO.updateEmployeeBankDetails(bankDetailsRequest);
				if (updateBank) {
					logger.debug("object updated in db " + updateBank);
					int bankDetailsCount = bankDetailsDAO.getAllEmployeeBankDetailsCount();
					bankDetailsResponse.setTotalCount(bankDetailsCount);
					bankDetailsResponse.setStatusMessage("BankDetails record is updated Successfully");
					return new ResponseEntity<>(bankDetailsResponse, HttpStatus.OK);
				}
			}
			if (bankDetailsRequest.getTransactionType().equalsIgnoreCase(DELETE)) {
				logger.debug("Transactiontype is  " + DELETE);
				bankDetailsResponse = new BankDetailsResponse();

				boolean deleteBank = bankDetailsDAO.deleteEmployeeBankDetails(bankDetailsRequest);

				if (deleteBank) {
					logger.debug("object deleted in db " + deleteBank);
					bankDetailsResponse.setStatusMessage("BankDetails record is deleted Successfully");
					return new ResponseEntity<>(bankDetailsResponse, HttpStatus.OK);
				}
			}
			logger.debug("object not deleted in db ");
			bankDetailsResponse.setStatusMessage(FAILED);
			return new ResponseEntity<>(bankDetailsResponse, HttpStatus.CONFLICT);
			
		} catch (Exception exception) {
			logger.debug("inside facade catch block ");
			ErrorResponse error = new ErrorResponse();
			exception.printStackTrace();
			error.setMessage("BankDetails is not seved in DB");
			error.setStatusCode("422");
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}
	}

	/**
	 * 
	 * @param bankDetailsRequest
	 * @return
	 */
	public ResponseEntity<Object> getBankDetails(BankDetailsRequest bankDetailsRequest) {
		BankDetailsResponse bankDetailsResponse = null;
		logger.debug("get request inside facade " + bankDetailsRequest);
		try {
			List<BankDetails> allBankDetails = bankDetailsDAO.getAllBankDetails(bankDetailsRequest);
			logger.debug("response in facade " + allBankDetails);
			if (null == allBankDetails || allBankDetails.isEmpty()) {
				bankDetailsResponse = new BankDetailsResponse();
				bankDetailsResponse.setStatusMessage(NORECORDS);
				bankDetailsResponse.setListBankDetails(new ArrayList<>());
				return new ResponseEntity<>(bankDetailsResponse, HttpStatus.NOT_ACCEPTABLE);
			} else {
				int bankDetailsCount = bankDetailsDAO.getAllEmployeeBankDetailsCount();
				bankDetailsResponse = new BankDetailsResponse();
				bankDetailsResponse.setStatusMessage(SUCCESS);
				bankDetailsResponse.setTotalCount(bankDetailsCount);
				bankDetailsResponse.setListBankDetails(allBankDetails);

			}

			return new ResponseEntity<>(bankDetailsResponse, HttpStatus.OK);
		} catch (Exception exception) {
			logger.debug("inside facade catch block " + exception.getMessage());
			ErrorResponse error = new ErrorResponse();
			error.setMessage("inside facade. Data is null");
			error.setStatusCode("422");
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}
	}
}
