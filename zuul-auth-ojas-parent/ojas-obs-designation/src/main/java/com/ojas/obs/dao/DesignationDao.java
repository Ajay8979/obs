package com.ojas.obs.dao;

import java.util.List;
import java.sql.SQLException;

import com.ojas.model.Designation;
import com.ojas.obs.request.DesignationRequest;


/**
 * 
 * @author nsrikanth
 *
 */
public interface DesignationDao {
	
	boolean saveDesignation(DesignationRequest designationRequest) throws SQLException;

	 boolean updateDesignation(DesignationRequest designationRequest) throws SQLException;

	// boolean deleteDesignation(int id) throws SQLException;
	
	 List<Designation> getAll(DesignationRequest designationRequest) throws SQLException;
	
	 int getAllDesignationCount() throws SQLException;
	 
	 public List<Designation> getById(DesignationRequest designationRequest);
	
	 //List<Designation> getCountPerPage(List<Designation> list, int pageSize, int pageNo);

}
