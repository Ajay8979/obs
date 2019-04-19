package com.ojas.obs.facade;

import org.springframework.http.ResponseEntity;

import com.ojas.obs.request.KYERequest;

/**
 * 
 * @author tshiva
 *
 */
public interface KyeFacade {
	ResponseEntity<Object> setKYE(KYERequest kyeRequest);

	ResponseEntity<Object> getKYE(KYERequest kyeRequest);
}