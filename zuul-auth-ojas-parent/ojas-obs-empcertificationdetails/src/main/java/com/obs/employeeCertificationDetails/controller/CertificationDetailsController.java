package com.obs.employeeCertificationDetails.controller;


import static com.obs.employeeCertificationDetails.constants.Constants.DELETE;
import static com.obs.employeeCertificationDetails.constants.Constants.GETCERTIFICATION;
import static com.obs.employeeCertificationDetails.constants.Constants.ID_NULL;
import static com.obs.employeeCertificationDetails.constants.Constants.MODEL_NULL;
import static com.obs.employeeCertificationDetails.constants.Constants.REQUEST_DATA_MISSING;
import static com.obs.employeeCertificationDetails.constants.Constants.REQUEST_NULL;
import static com.obs.employeeCertificationDetails.constants.Constants.SAVE;
import static com.obs.employeeCertificationDetails.constants.Constants.SESSIONID_NULL;
import static com.obs.employeeCertificationDetails.constants.Constants.SETCERTIFICATION;
import static com.obs.employeeCertificationDetails.constants.Constants.UPDATE;
//import static com.obs.employeeCertificationDetails.constants.Constants.CERTIFICATION;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.obs.employeeCertificationDetails.error.ErrorResponse;
import com.obs.employeeCertificationDetails.facade.CertificationDetailsFacade;
import com.obs.employeeCertificationDetails.model.CertificationDetails;
import com.obs.employeeCertificationDetails.request.CertificationDetailsRequest;

@RestController
//@RequestMapping(CERTIFICATION)
public class CertificationDetailsController {
	@Autowired
	private CertificationDetailsFacade CertificationDetailsFacadeImpl;
	Logger logger = Logger.getLogger(this.getClass());

	@PostMapping(SETCERTIFICATION)
	public ResponseEntity<Object> setCertificationDetails(@RequestBody CertificationDetailsRequest certificationDetailsRequestObject) {
		ErrorResponse error = null;
		
		try {

            if (null == certificationDetailsRequestObject) { 
            	logger.debug("@@@@@@@@@@@@@@request is not valid");
				error = new ErrorResponse();
				error.setMessage(REQUEST_NULL);
				error.setCode("422");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
            List<CertificationDetails> certificationDetailsList = certificationDetailsRequestObject.getCertificationDetailsModel();
            if (certificationDetailsList == null) {
				logger.debug("@@@@@@@@@@@ Model is null  request is not valid");
				error = new ErrorResponse();
				error.setMessage(MODEL_NULL);
				error.setCode("422");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			if (null == certificationDetailsRequestObject.getSessionId()) {
				logger.debug("@@@@@Session Id is not provided");
				error = new ErrorResponse();
				error.setCode("422");
				error.setMessage(SESSIONID_NULL);
				return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			} 
            for(CertificationDetails certificationDetails:certificationDetailsList) {
            	if (certificationDetailsRequestObject.getTransactionType().equalsIgnoreCase(SAVE)) {
    				if ((null == certificationDetails.getCertificationName())
    						|| (null == certificationDetails.getCreatedBy())
    						|| (null == certificationDetails.getIssuedBy())){
    					logger.debug("@@@@@@@@@@@data is  invalid");
    					error = new ErrorResponse();
    					error.setMessage(REQUEST_DATA_MISSING);
    					error.setCode("422");
    					return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    				}
    		    }
    			if((certificationDetailsRequestObject.getTransactionType().equalsIgnoreCase(UPDATE) 
    					|| certificationDetailsRequestObject.getTransactionType().equalsIgnoreCase(DELETE))
    					&&(certificationDetails.getId()==null)
    					&&(certificationDetails.getUpdatedBy()==null)) {
    				logger.debug("@@@@@@@@@data is  invalid");
    				error = new ErrorResponse();
    				error.setMessage(ID_NULL);
    				error.setCode("422");
    				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    			}
            }
			return CertificationDetailsFacadeImpl.setCertificationDetails(certificationDetailsRequestObject);
        } catch (SQLException exception) {
			logger.debug("@@@Inside Catch block");
            error = new ErrorResponse();
			exception.printStackTrace();
			error.setMessage(exception.getMessage());
			return new ResponseEntity<Object>(error, HttpStatus.CONFLICT);
		}
		
	}

	// get and get All Function call
	@PostMapping(GETCERTIFICATION)
	public ResponseEntity<Object> getCertificationDetails(@RequestBody CertificationDetailsRequest certificationDetailsRequestObject) {
		ErrorResponse error = null;
		try {
            if (null == certificationDetailsRequestObject) {
				logger.debug("request is not valid");
                error = new ErrorResponse();
				error.setMessage(REQUEST_NULL);
				error.setCode("422");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			return CertificationDetailsFacadeImpl.getCertificationDetails(certificationDetailsRequestObject);
		} catch (SQLException exception) {
            error = new ErrorResponse();
			exception.printStackTrace();
			error.setMessage(exception.getMessage());
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}

	}
}
