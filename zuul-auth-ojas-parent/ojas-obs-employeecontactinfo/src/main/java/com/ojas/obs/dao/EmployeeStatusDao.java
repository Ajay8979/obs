package com.ojas.obs.dao;

import java.sql.SQLException;
import java.util.List;

import com.ojas.obs.model.EmployeeContactInfo;
import com.ojas.obs.requests.EmployeeContactInfoRequest;

public interface EmployeeStatusDao {
	boolean saveEmployeeContactInfo(EmployeeContactInfoRequest employeeContactInfo) throws SQLException;

	boolean updateEmployeeContactInfo(EmployeeContactInfoRequest employeeContactInfo) throws SQLException;

	boolean deleteEmployeeContactInfo(EmployeeContactInfoRequest employeeContactInfo) throws SQLException;

	List<EmployeeContactInfo> getAll(EmployeeContactInfoRequest employeeContactInfo) throws SQLException;

	List<EmployeeContactInfo> showEmployeeContactInfoByEmpId(EmployeeContactInfoRequest empRequest) throws SQLException;

	List<EmployeeContactInfo> showEmployeeContactInfoById(EmployeeContactInfoRequest empRequest) throws SQLException;

}
