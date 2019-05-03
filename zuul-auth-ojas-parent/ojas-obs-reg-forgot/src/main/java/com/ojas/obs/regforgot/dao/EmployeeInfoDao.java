package com.ojas.obs.regforgot.dao;

import java.sql.SQLException;

import com.ojas.obs.regforgot.request.EmployeeInfoRequest;

/**
 * 
 * @author Manohar
 *
 */
public interface EmployeeInfoDao {

	/**
	 * 
	 * @param employeeInfoRequest
	 * @return
	 * @throws SQLException
	 */
	public boolean saveEmployeeInfo(EmployeeInfoRequest employeeInfoRequest) throws SQLException;

	
}
