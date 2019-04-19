package com.ojas.obs.dao;

import java.util.List;

import com.ojas.obs.model.SubBusinessUnit;
import com.ojas.obs.request.SubBusinessUnitRequest;

public interface SubBusinessUnitDao {

	boolean saveSubBusinessUnit(SubBusinessUnitRequest subBusinessUnitRequest);
	boolean updateSubBusinessUnit(SubBusinessUnitRequest subBusinessUnitRequest);
	//boolean deleteSubBusinessUnit(SubBusinessUnitRequest subBusinessUnitRequest);
	List<SubBusinessUnit> getAllSubBusinessUnitDetails();
	int getAllSubBusinessUnitDetailsCount();
}
