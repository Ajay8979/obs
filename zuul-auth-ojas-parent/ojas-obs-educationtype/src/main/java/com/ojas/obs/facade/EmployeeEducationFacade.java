package com.ojas.obs.facade;

import org.springframework.http.ResponseEntity;

import com.ojas.obs.modelrequest.EmployeeEducationRequest;

/**
 * 
 * @author mpraneethguptha
 *
 */
public interface EmployeeEducationFacade {
	public ResponseEntity<Object> setEmployeeEducationInfo(EmployeeEducationRequest employeeEducationRequest);

	public ResponseEntity<Object> getEmployeeEducationInfo(EmployeeEducationRequest employeeEducationRequest);

}
