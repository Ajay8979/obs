package com.ojas.obs.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ojas.obs.model.EmployeeEducation;
import com.ojas.obs.modelrequest.EmployeeEducationRequest;

/**
 * 
 * @author mpraneethguptha
 *
 */
@Repository
public interface EmployeeEducationDao {
	
	boolean saveEmployeeEducation(EmployeeEducationRequest employeeEducationRequest);
	
	boolean updateEmployeeEducation(EmployeeEducationRequest employeeEducationRequest);
	
	boolean deleteEmployeeEducation(int id);
	
	List<EmployeeEducation> getAllEmployeeEducation(EmployeeEducationRequest employeeEducationRequest);
	
	int getAllRecordsCount();
	

}
