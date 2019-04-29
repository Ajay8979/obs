package com.ojas.obs.resourcetype.dao;

import java.util.List;

import com.ojas.obs.resourcetype.exception.DataNotInsertedException;
import com.ojas.obs.resourcetype.model.ResourceType;

public interface ResourceTypeDAO {
	
	  boolean saveResourceTypes(List<ResourceType> resourceTypes) throws
	  DataNotInsertedException;
	  
	  boolean updateResourceTypes(List<ResourceType> resourceTypes) throws
	  DataNotInsertedException;
	  
	  boolean deleteResourceTypes(List<ResourceType> resourceTypes) throws
	  DataNotInsertedException;
	 
	List<ResourceType> getAllResourceTypes();

	List<ResourceType> getResourceTypeById(Integer employeeId);
}
