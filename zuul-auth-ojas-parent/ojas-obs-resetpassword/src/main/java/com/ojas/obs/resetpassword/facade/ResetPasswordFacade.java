package com.ojas.obs.resetpassword.facade;

import org.springframework.http.ResponseEntity;

import com.ojas.obs.resetpassword.request.ResetPasswordRequest;

public interface ResetPasswordFacade {
	public ResponseEntity<Object> setPassword(ResetPasswordRequest resetPasswordRequest);
}
