package com.ojas.obs.dao;

import java.sql.SQLException;
import java.util.List;

import com.ojas.obs.model.BusinessUnit;
import com.ojas.obs.model.BusinessUnitRequest;

public interface BusinessUnitDao {
	
	Boolean saveBusinessUnit(BusinessUnitRequest businessUnitRequest) throws SQLException;

	Boolean updateBusinessUnit(BusinessUnitRequest businessUnitRequestobject) throws SQLException;

	boolean deleteBusinessUnit(Integer courseId) throws SQLException;

	List<BusinessUnit> getAllBussinessDetails(BusinessUnitRequest request) throws SQLException;

	int getAllBussinessCount() throws SQLException;
	 
	List<BusinessUnit> getCountPerPage(List<BusinessUnit> list, int pageSize, int pageNo);
}
