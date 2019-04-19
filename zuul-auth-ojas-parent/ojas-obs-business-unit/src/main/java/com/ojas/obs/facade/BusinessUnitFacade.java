package com.ojas.obs.facade;

import java.sql.SQLException;

import org.springframework.http.ResponseEntity;

import com.ojas.obs.model.BusinessUnitRequest;

public interface BusinessUnitFacade {
	ResponseEntity<Object> setBusinessUnit(BusinessUnitRequest businessUnit) throws SQLException;

	ResponseEntity<Object> getBusinessUnit(BusinessUnitRequest businessUnitRequest) throws SQLException;

}
