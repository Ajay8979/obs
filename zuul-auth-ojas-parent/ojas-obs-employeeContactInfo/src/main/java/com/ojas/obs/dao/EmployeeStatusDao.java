package com.ojas.obs.dao;

import java.util.List;

import com.ojas.obs.model.EmployeeContactInfo;
import com.ojas.obs.requests.EmployeeContactInfoRequest;



public interface EmployeeStatusDao {
	String saveEmployeeContactInfo(EmployeeContactInfoRequest employeeContactInfo);

	EmployeeContactInfo showEmployeeContactInfo(EmployeeContactInfoRequest employeeContactInfo);

	List<EmployeeContactInfo> getAll(EmployeeContactInfoRequest employeeContactInfo);

	String updateEmployeeContactInfo(EmployeeContactInfoRequest employeeContactInfo);

	String deleteEmployeeContactInfo(EmployeeContactInfoRequest employeeContactInfo);

	EmployeeContactInfo showEmployeeContactInfoByEmpId(EmployeeContactInfoRequest empRequest);
	
	
	
}
