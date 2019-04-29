package com.ojas.obs.dao;

import java.util.List;

import com.ojas.obs.model.EmployeeStatus;
import com.ojas.obs.request.EmployeeStatusRequest;
/**
 * 
 * @author Manohar
 *
 */
public interface EmployeeStatusDao {
	public boolean saveEmployeeStatus(EmployeeStatusRequest employeeStatusRequest);
	public boolean updateEmployeeStatus(EmployeeStatusRequest employeeStatusRequest);
	//public boolean deleteEmployeeStatus(EmployeeStatusRequest employeeStatusRequest);
	public List<EmployeeStatus> getAllStatus();
	public List<EmployeeStatus> getById(Integer id);
}
