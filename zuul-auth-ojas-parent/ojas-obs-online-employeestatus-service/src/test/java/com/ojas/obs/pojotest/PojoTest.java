package com.ojas.obs.pojotest;

import org.junit.Test;
import org.mockito.Spy;

import com.ojas.obs.model.EmployeeStatus;
import com.ojas.obs.request.EmployeeStatusRequest;
import com.ojas.obs.response.EmployeeStatusResponse;

public class PojoTest {
	@Spy
	EmployeeStatus empStatus;

	@Test
	public void modelTest() {
		EmployeeStatus empStatus = new EmployeeStatus();
		empStatus.setId(1);
		empStatus.setStatus("");
	//	empStatus.setDelete(false);
		empStatus.getId();
		empStatus.getStatus();
	//	empStatus.getDelete();
		empStatus.toString();
		empStatus.equals(empStatus);
		empStatus.hashCode();
		
	}
	
	@Test
	public void modelTest2() {
		EmployeeStatusRequest employeeStatusRequest = new EmployeeStatusRequest();
		EmployeeStatus empStatus = new EmployeeStatus();
		empStatus.setId(1);
		//empStatus.setStatus("");
//		empStatus.setDelete(true);
		EmployeeStatus empStatus1 = new EmployeeStatus();
		empStatus1.setId(1);
		empStatus1.setStatus("");
//		empStatus1.setDelete(false);
		empStatus.getId();
		empStatus.getStatus();
//		empStatus.getDelete();
		empStatus.equals(empStatus1);
		empStatus.equals(employeeStatusRequest);
	}
	
	@Test
	public void modelTest3() {
		EmployeeStatusRequest employeeStatusRequest = new EmployeeStatusRequest();
		EmployeeStatus empStatus = new EmployeeStatus();
		//empStatus.setId(1);
		empStatus.setStatus("");
//		empStatus.setDelete(true);
		EmployeeStatus empStatus1 = new EmployeeStatus();
		empStatus1.setId(1);
		empStatus1.setStatus("");
//		empStatus1.setDelete(true);
		empStatus.getId();
		empStatus.getStatus();
//		empStatus.getDelete();
		empStatus.equals(empStatus1);
		empStatus1.equals(employeeStatusRequest);
	}

	@Test
	public void modelTest4() {
		EmployeeStatus empStatus = new EmployeeStatus();
		empStatus.setId(1);
		empStatus.setStatus("c");
//		empStatus.setDelete(true);
		EmployeeStatus empStatus1 = new EmployeeStatus();
		empStatus1.setId(1);
		empStatus1.setStatus("c");
	//	empStatus1.setDelete(true);
		empStatus.getId();
		empStatus.getStatus();
	//	empStatus.getDelete();
		empStatus.equals(empStatus1);
		empStatus.equals(empStatus);
	}
	
	@Test
	public void requestTest() {
		EmployeeStatusRequest request = new EmployeeStatusRequest();
		EmployeeStatusRequest request1 = new EmployeeStatusRequest();
		request.setSessionId("1");
		request.setStatusCode("1");
		request.setTransactionType("");
		request1.setSessionId("1");
		request1.setStatusCode("1");
		request1.setTransactionType("");
		request.hashCode();
		request.getSessionId();
		request.getStatusCode();
		request.getTransactionType();
		request.equals(request1);
	}
	
	@Test
	public void requestTest1() {
		EmployeeStatusRequest request = new EmployeeStatusRequest();
		EmployeeStatusRequest request1 = new EmployeeStatusRequest();
		request.setSessionId("1");
		request.setStatusCode("1");
		request.setTransactionType("a");
		request1.setSessionId("1");
		//request1.setStatusCode();
		request1.setTransactionType("a");
		request.hashCode();
		request.getSessionId();
		request.getStatusCode();
		request.getTransactionType();
		request.equals(request1);
	}
	
	@Test
	public void responseTest() {
		EmployeeStatusResponse response = new EmployeeStatusResponse();
		EmployeeStatusResponse response1 = new EmployeeStatusResponse();
		response.setStatus("");
		response1.setStatus("");
		response.equals(response1);
		response.hashCode();
	}
	@Test
	public void responseTest1() {
		EmployeeStatusResponse response = new EmployeeStatusResponse();
		EmployeeStatusResponse response1 = new EmployeeStatusResponse();
		response.setStatus("");
		response1.setStatus("a");
		response.getEmployeeStatusList();
		response1.getStatus();
		response.equals(response1);
		response.hashCode();
	}
}
