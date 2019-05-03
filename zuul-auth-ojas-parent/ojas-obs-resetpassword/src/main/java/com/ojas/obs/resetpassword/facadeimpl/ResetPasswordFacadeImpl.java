package com.ojas.obs.resetpassword.facadeimpl;

import static com.ojas.obs.resetpassword.constants.UserConstants.FAILED;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ojas.obs.resetpassword.dao.ResetPasswordDao;
import com.ojas.obs.resetpassword.error.ErrorResponse;
import com.ojas.obs.resetpassword.facade.ResetPasswordFacade;
import com.ojas.obs.resetpassword.request.ResetPasswordRequest;
import com.ojas.obs.resetpassword.response.ResetPasswordResponse;

@Service
public class ResetPasswordFacadeImpl implements ResetPasswordFacade {
	@Autowired
	private ResetPasswordDao resetPasswordDao;
	Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ResponseEntity<Object> setPassword(ResetPasswordRequest resetPasswordRequest) {
		logger.debug("inside password facade " + resetPasswordRequest);
		ResetPasswordResponse resetPasswordResponse = new ResetPasswordResponse();
		try {
		boolean updatePassword = resetPasswordDao.updatePassword(resetPasswordRequest);
		if (updatePassword) {
			resetPasswordResponse.setStatus("Success fully record updated");
			return new ResponseEntity<>(resetPasswordResponse, HttpStatus.OK);
		} else {
			resetPasswordResponse.setStatus(FAILED);
			return new ResponseEntity<>(resetPasswordResponse, HttpStatus.CONFLICT);
		}
	} catch (DuplicateKeyException exception) {
		logger.error("***** inside catch block *****");
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatusCode("422");
		errorResponse.setMessage(exception.getCause().getLocalizedMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	}catch (Exception exception) {
			logger.error("***** inside catch block *****");
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setStatusCode("422");
			errorResponse.setMessage(exception.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
		}
	}
}
