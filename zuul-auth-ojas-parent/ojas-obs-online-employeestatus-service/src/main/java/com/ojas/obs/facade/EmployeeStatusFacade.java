package com.ojas.obs.facade;

import org.springframework.http.ResponseEntity;

import com.ojas.obs.request.EmployeeStatusRequest;
/**
 * 
 * @author Manohar
 *
 */
public interface EmployeeStatusFacade {
	public ResponseEntity<Object> setEmployeeStatus(EmployeeStatusRequest employeeStatusRequest);
	public ResponseEntity<Object> getEmployeeStatus(EmployeeStatusRequest employeeStatusRequest);

}
