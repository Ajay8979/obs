package com.ojas.obs.dao;

import java.sql.SQLException;
import java.util.List;

import com.ojas.obs.model.EmployeeInfo;
import com.ojas.obs.request.EmployeeInfoRequest;

/**
 * 
 * @author sdileep
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

	/**
	 * 
	 * @param employeeInfoRequest
	 * @return
	 * @throws SQLException
	 */
	public boolean updateEmployeeInfo(EmployeeInfoRequest employeeInfoRequest) throws SQLException;

	/**
	 * 
	 * @param employeeInfoRequest
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteEmployeeInfo(EmployeeInfoRequest employeeInfoRequest) throws SQLException;

	/**
	 * 
	 * @param employeeInfoRequest
	 * @return
	 * @throws SQLException
	 */
	public List<EmployeeInfo> getAllEmployeeDetails(EmployeeInfoRequest employeeInfoRequest) throws SQLException;

	/**
	 * 
	 * @param allemployeeDetails
	 * @param pageSize
	 * @param pageNum
	 * @return
	 * @throws SQLException 
	 */
	//public List<EmployeeInfo> getPageRecords(List<EmployeeInfo> allemployeeDetails, int pageSize, int pageNum) throws SQLException;

	/**
	 * 
	 * 
	 * @return
	 * @throws SQLException
	 */
	//public int getAllEmployeeDetailsCount() throws SQLException;

	public List<EmployeeInfo> getById(EmployeeInfoRequest employeeInfoRequest) throws SQLException;
	
	//public EmployeeRoleResponse getRoleById(EmployeeInfoRequest employeeInfoRequest) throws SQLException;
	
	
	
}
