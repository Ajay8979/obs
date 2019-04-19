package com.ojas.obs.facade;

import org.springframework.http.ResponseEntity;

import com.ojas.obs.request.SubBusinessUnitRequest;

public interface SubBusinessUnitFacade {
	ResponseEntity<Object> setSubBusinessUnit(SubBusinessUnitRequest subBusinessUnitRequest);
	ResponseEntity<Object> getSubBusinessUnit(SubBusinessUnitRequest subBusinessUnitRequest);

}
