package com.ojas.obs.facade;

import java.util.List;

import com.ojas.obs.model.EmployeeContactInfo;
import com.ojas.obs.requests.EmployeeContactInfoRequest;
import com.ojas.obs.response.EmployeeContactInfoResponse;

public interface EmployeeStatusFacade {

EmployeeContactInfoResponse setEmployeeContactInfo(EmployeeContactInfoRequest empRequest);
	
	List<EmployeeContactInfo> getEmployeeContactInfo(EmployeeContactInfoRequest empRequest);
	
	EmployeeContactInfo showEmployeeContactInfo(EmployeeContactInfoRequest empRequest);

	EmployeeContactInfo showEmployeeContactInfoByEmpId(EmployeeContactInfoRequest empRequest);
	EmployeeContactInfo showEmployeeContactInfoById(EmployeeContactInfoRequest empRequest);
	
}
