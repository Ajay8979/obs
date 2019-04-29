package com.obs.businessunit.facade;

import org.springframework.http.ResponseEntity;

import com.obs.businessunit.request.BusinessUnitRequest;

public interface BusinessUnitFacade {
	ResponseEntity<Object> setBusinessUnit(BusinessUnitRequest businessUnitRequest);
	ResponseEntity<Object> getBusinessUnit(BusinessUnitRequest businessUnitRequest);
}
