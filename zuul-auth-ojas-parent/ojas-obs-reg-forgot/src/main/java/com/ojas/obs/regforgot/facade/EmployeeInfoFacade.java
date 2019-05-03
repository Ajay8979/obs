package com.ojas.obs.regforgot.facade;

import java.sql.SQLException;

import org.springframework.http.ResponseEntity;

import com.ojas.obs.regforgot.request.EmployeeInfoRequest;

/**
 * 
 * @author sdileep
 *
 */
public interface EmployeeInfoFacade {
	/**
	 * 
	 * @param employeeInfoRequest
	 * @return
	 * @throws SQLException
	 */
	public ResponseEntity<Object> setEmployeeInfo(EmployeeInfoRequest employeeInfoRequest) throws SQLException;

}
