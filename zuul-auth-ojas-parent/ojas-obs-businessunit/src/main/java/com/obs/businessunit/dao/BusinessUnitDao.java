package com.obs.businessunit.dao;

import java.util.List;

import com.obs.businessunit.model.BusinessUnit;
import com.obs.businessunit.request.BusinessUnitRequest;

public interface BusinessUnitDao {
	Boolean saveBusinessUnit(BusinessUnitRequest businessUnitRequest);

	Boolean updateBusinessUnit(BusinessUnitRequest businessUnitRequestobject);

	List<BusinessUnit> getAllBussinessDetails(BusinessUnitRequest request);

	public List<BusinessUnit> getById(BusinessUnitRequest request);
}
