package com.ojas.obs.facade;

import static com.ojas.obs.utility.Constants.DELETE;
import static com.ojas.obs.utility.Constants.GETALL;
import static com.ojas.obs.utility.Constants.SAVE;
import static com.ojas.obs.utility.Constants.UPDATE;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ojas.obs.dao.KyeDao;
import com.ojas.obs.model.KYE;
import com.ojas.obs.request.KYERequest;
import com.ojas.obs.response.KyeResponse;
import com.ojas.obs.utility.ErrorResponse;

/**
 * 
 * @author tshiva
 *
 */
@Service
public class KyeFacadeImpl implements KyeFacade {

	@Autowired
	private KyeDao kyeDao;
	Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ResponseEntity<Object> setKYE(KYERequest kyeRequest) {
		logger.debug("@kyeRequest in KyeFacadeImpl ::" + kyeRequest);
		try {
			if (kyeRequest.getTransactionType().equalsIgnoreCase(SAVE)) {
				boolean saveKYE = kyeDao.saveKYE(kyeRequest);
				if (saveKYE) {
					KyeResponse response = new KyeResponse();
					response.setMessage("record added successfully");
					response.setStatusCode("200");
					return new ResponseEntity<>(response, HttpStatus.OK);
				}
				logger.debug("request is not valid");
				ErrorResponse error = new ErrorResponse();
				error.setMessage("record not added");
				error.setStatusCode("409");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			if (kyeRequest.getTransactionType().equalsIgnoreCase(UPDATE)) {
				boolean updateKYE = kyeDao.updateKYE(kyeRequest);
				if (updateKYE) {
					KyeResponse response = new KyeResponse();
					response.setMessage("record updated successfully");
					response.setStatusCode("200");
					return new ResponseEntity<>(response, HttpStatus.OK);
				}
				ErrorResponse error = new ErrorResponse();
				error.setMessage("record not updated");
				error.setStatusCode("401");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			if (kyeRequest.getTransactionType().equalsIgnoreCase(DELETE)) {
				boolean deleteKYE = kyeDao.deleteKYE(kyeRequest);
				if (deleteKYE) {
					KyeResponse response = new KyeResponse();
					response.setMessage("record deleted successfully");
					response.setStatusCode("200");
					return new ResponseEntity<>(response, HttpStatus.OK);
				}
				ErrorResponse error = new ErrorResponse();
				error.setMessage("record not deleted");
				error.setStatusCode("422");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
		} catch (Exception e) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage(e.getMessage());
			error.setStatusCode("409");
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}
		return null;
	}

	@Override
	public ResponseEntity<Object> getKYE(KYERequest kyeRequest) {
		KyeResponse kyeResponse = new KyeResponse();
		logger.debug("@kyeRequest in KyeFacadeImpl ::" + kyeRequest);
		try {
			if (kyeRequest.getTransactionType().equalsIgnoreCase(GETALL)) {
				List<KYE> allKYE = kyeDao.getAllKYE(kyeRequest);

				if (allKYE == null || allKYE.isEmpty()) {
					kyeResponse.setKyeList(new ArrayList<>());
					kyeResponse.setMessage("No records found");
					kyeResponse.setStatusCode("422");
					return new ResponseEntity<>(kyeResponse, HttpStatus.UNPROCESSABLE_ENTITY);
				} else {
					kyeResponse.setKyeList(allKYE);
					kyeResponse.setMessage("success");
					kyeResponse.setStatusCode("200");
					return new ResponseEntity<>(kyeResponse, HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage(e.getMessage());
			error.setStatusCode("409");
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}
		return null;
	}
}