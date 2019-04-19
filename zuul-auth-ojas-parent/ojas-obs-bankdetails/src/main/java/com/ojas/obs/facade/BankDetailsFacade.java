package com.ojas.obs.facade;

import static com.ojas.obs.constants.UserConstants.DELETE;
import static com.ojas.obs.constants.UserConstants.FAILED;
import static com.ojas.obs.constants.UserConstants.SAVE;
import static com.ojas.obs.constants.UserConstants.UPDATE;
import static com.ojas.obs.constants.UserConstants.SUCCESS;

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
		ResponseEntity<Object> responseEntity = null;
		BankDetailsResponse bankDetailsResponse = null;
		logger.debug("incomming request in facade " + bankDetailsRequest);
		try {
			if (bankDetailsRequest.getTransactionType().equalsIgnoreCase(SAVE)) {
				bankDetailsResponse = new BankDetailsResponse();
				boolean saveBank = bankDetailsDAO.saveEmployeeBankDetails(bankDetailsRequest);

				if (saveBank) {
					logger.debug("object stored in db " + saveBank);
					int bankDetailsCount = bankDetailsDAO.getAllEmployeeBankDetailsCount();
					bankDetailsResponse.setTotalCount(bankDetailsCount);
					bankDetailsResponse.setStatusMessage("Successfully BankDetails record saved");
					return new ResponseEntity<>(bankDetailsResponse, HttpStatus.OK);
				} else {
					logger.debug("object not stored in db");
					bankDetailsResponse.setStatusMessage(FAILED);
					return new ResponseEntity<>(bankDetailsResponse, HttpStatus.CONFLICT);
				}
			}

			if (bankDetailsRequest.getTransactionType().equalsIgnoreCase(UPDATE)) {
				bankDetailsResponse = new BankDetailsResponse();
				boolean updateBank = bankDetailsDAO.updateEmployeeBankDetails(bankDetailsRequest);
				if (updateBank) {
					logger.debug("object updated in db " + updateBank);
					int bankDetailsCount = bankDetailsDAO.getAllEmployeeBankDetailsCount();
					bankDetailsResponse.setTotalCount(bankDetailsCount);
					bankDetailsResponse.setStatusMessage("Successfully BankDetails record updated");
					return new ResponseEntity<>(bankDetailsResponse, HttpStatus.OK);
				} else {
					logger.debug("object not updated in db ");
					bankDetailsResponse.setStatusMessage(FAILED);
					return new ResponseEntity<>(bankDetailsResponse, HttpStatus.CONFLICT);
				}
			}

			if (bankDetailsRequest.getTransactionType().equalsIgnoreCase(DELETE)) {
				bankDetailsResponse = new BankDetailsResponse();

				boolean deleteBank = bankDetailsDAO.deleteEmployeeBankDetails(bankDetailsRequest);

				if (deleteBank) {
					logger.debug("object deleted in db " + deleteBank);
					bankDetailsResponse.setStatusMessage("Successfully BankDetails record deleted"
							+ ""
							+ "");
					return new ResponseEntity<>(bankDetailsResponse, HttpStatus.OK);
				} else {
					logger.debug("object not deleted in db ");
					bankDetailsResponse.setStatusMessage(FAILED);
					return new ResponseEntity<>(bankDetailsResponse, HttpStatus.CONFLICT);
				}

			}
		} catch (Exception exception) {
			logger.debug("inside facade catch block ");
			ErrorResponse error = new ErrorResponse();
			exception.printStackTrace();
			error.setMessage("Data is null @@@@@@@@@@@@@@@@@@@");
			error.setStatusCode("422");
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}
		return responseEntity;

	}

	/**
	 * 
	 * @param bankDetailsRequest
	 * @return
	 */
	public ResponseEntity<Object> getBankDetails(BankDetailsRequest bankDetailsRequest) {
		BankDetailsResponse bankDetailsResponse = null;
		try {
			List<BankDetails> allBankDetails = bankDetailsDAO.getAllBankDetails(bankDetailsRequest);
			if (null == allBankDetails || allBankDetails.isEmpty()) {
				bankDetailsResponse = new BankDetailsResponse();
				bankDetailsResponse.setStatusMessage(FAILED);
				return new ResponseEntity<>(bankDetailsResponse, HttpStatus.CONFLICT);
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
			error.setMessage("Data is null");
			error.setStatusCode("422");
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}
	}
}
