package com.ojas.obs.controllertest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ojas.obs.controller.EmployeeStatusController;
import com.ojas.obs.error.ErrorResponse;
import com.ojas.obs.facade.EmployeeStatusFacade;
import com.ojas.obs.facadeimpl.EmployeeStatusFacadeImpl;
import com.ojas.obs.model.EmployeeStatus;
import com.ojas.obs.request.EmployeeStatusRequest;
import com.ojas.obs.response.EmployeeStatusResponse;

public class EmployeeStatusControllerTest {
	@InjectMocks
	EmployeeStatusController statusController;
	@Mock
	EmployeeStatusFacadeImpl employeeStatusFacadeImpl;

	@Mock
	EmployeeStatusFacade employeeStatusFacade;

	@Spy
	EmployeeStatusRequest employeeStatusRequest;
	@Spy
	ErrorResponse errorResponse = new ErrorResponse();
	@Spy
	EmployeeStatusResponse employeeStatusResponse = new EmployeeStatusResponse();
	@Spy
	ResponseEntity<Object> failureResponse = new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
	@Spy
	ResponseEntity<Object> conflict = new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	@Spy
	ResponseEntity<Object> successResponse = new ResponseEntity<>(employeeStatusResponse, HttpStatus.OK);
	@Spy
	EmployeeStatus employeeStatus;

	@Before
	public void init() throws Exception {
		statusController = new EmployeeStatusController();
		employeeStatusFacadeImpl = mock(EmployeeStatusFacadeImpl.class);
		setCollaborator(statusController, "employeeStatusFacade", employeeStatusFacadeImpl);
	}

	public void setCollaborator(Object object, String name, Object service) throws Exception {
		Field field;
		field = object.getClass().getDeclaredField(name);
		field.setAccessible(true);
		field.set(object, service);
	}

	public List<EmployeeStatus> getEmpStatusList() {
		List<EmployeeStatus> empStatusList = new ArrayList<EmployeeStatus>();
		EmployeeStatus employeeStatus = new EmployeeStatus();
		employeeStatus.setId(1);
		employeeStatus.setStatus("Active");
		empStatusList.add(employeeStatus);
		return empStatusList;
	}

	@Test
	public void setEmployeeStatusNullList() throws SQLException {
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		EmployeeStatusRequest employeeStatusRequest = new EmployeeStatusRequest();
		//when(employeeStatusFacadeImpl.setEmployeeStatus(employeeStatusRequest)).thenReturn(failureResponse);
		ResponseEntity<Object> setEmp = statusController.setEmployeeStatus(employeeStatusRequest, request, response);
		HttpStatus statusCode = setEmp.getStatusCode();
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
	}

	@Test
	public void setEmpStatusNullType() throws SQLException {
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		EmployeeStatusRequest employeeStatusRequest = new EmployeeStatusRequest();
		employeeStatusRequest.setEmployeeStatus(getEmpStatusList());
		//when(employeeStatusFacadeImpl.setEmployeeStatus(employeeStatusRequest)).thenReturn(failureResponse);
		ResponseEntity<Object> setEmp = statusController.setEmployeeStatus(employeeStatusRequest, request, response);
		HttpStatus statusCode = setEmp.getStatusCode();
		assertNotEquals(HttpStatus.OK, statusCode);
	}

	@Test
	public void setEmpStatusEmptyType() throws SQLException {
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		EmployeeStatusRequest employeeStatusRequest = new EmployeeStatusRequest();
		employeeStatusRequest.setEmployeeStatus(getEmpStatusList());
		employeeStatusRequest.setTransactionType("");
		//when(employeeStatusFacadeImpl.setEmployeeStatus(employeeStatusRequest)).thenReturn(failureResponse);
		ResponseEntity<Object> setEmp = statusController.setEmployeeStatus(employeeStatusRequest, request, response);
		HttpStatus statusCode = setEmp.getStatusCode();
		assertNotEquals(HttpStatus.OK, statusCode);
	}

	@Test
	public void setEmployeeStatusNullEmpStatus() throws SQLException {
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		EmployeeStatusRequest employeeStatusRequest = new EmployeeStatusRequest();
		List<EmployeeStatus> empStatusList = new ArrayList<EmployeeStatus>();
		EmployeeStatus empStatus = null;
		empStatusList.add(empStatus);
		employeeStatusRequest.setEmployeeStatus(empStatusList);
		employeeStatusRequest.setTransactionType("save");
		//when(employeeStatusFacadeImpl.setEmployeeStatus(employeeStatusRequest)).thenReturn(failureResponse);
		ResponseEntity<Object> setEmp = statusController.setEmployeeStatus(employeeStatusRequest, request, response);
		HttpStatus statusCode = setEmp.getStatusCode();
		assertNotEquals(HttpStatus.OK, statusCode);
	}

	@Test
	public void setEmployeeStatusSaveStatusNull() throws SQLException {
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		EmployeeStatusRequest employeeStatusRequest = new EmployeeStatusRequest();
		employeeStatusRequest.setEmployeeStatus(getEmpStatusList());
		employeeStatusRequest.getEmployeeStatus().get(0).setStatus(null);
		employeeStatusRequest.setTransactionType("save");
		//when(employeeStatusFacadeImpl.setEmployeeStatus(employeeStatusRequest)).thenReturn(failureResponse);
		ResponseEntity<Object> setEmp = statusController.setEmployeeStatus(employeeStatusRequest, request, response);
		HttpStatus statusCode = setEmp.getStatusCode();
		assertNotEquals(HttpStatus.OK, statusCode);
	}

	@Test
	public void setEmployeeStatusSaveStatusEmpty() throws SQLException {
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		EmployeeStatusRequest employeeStatusRequest = new EmployeeStatusRequest();
		employeeStatusRequest.setEmployeeStatus(getEmpStatusList());
		employeeStatusRequest.getEmployeeStatus().get(0).setStatus("");
		employeeStatusRequest.setTransactionType("save");
		//when(employeeStatusFacadeImpl.setEmployeeStatus(employeeStatusRequest)).thenReturn(failureResponse);
		ResponseEntity<Object> setEmp = statusController.setEmployeeStatus(employeeStatusRequest, request, response);
		HttpStatus statusCode = setEmp.getStatusCode();
		assertNotEquals(HttpStatus.OK, statusCode);
	}

	@Test
	public void setEmployeeStatusUpdateTest() throws SQLException {
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		EmployeeStatusRequest employeeStatusRequest = new EmployeeStatusRequest();
		employeeStatusRequest.setEmployeeStatus(getEmpStatusList());
		employeeStatusRequest.setTransactionType("update");
		when(employeeStatusFacadeImpl.setEmployeeStatus(employeeStatusRequest)).thenReturn(successResponse);
		ResponseEntity<Object> setEmp = statusController.setEmployeeStatus(employeeStatusRequest, request, response);
		HttpStatus statusCode = setEmp.getStatusCode();
		assertEquals(HttpStatus.OK, statusCode);
	}

	@Test
	public void setEmpStatusUpdateIdNullCheck() throws SQLException {
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		EmployeeStatusRequest employeeStatusRequest = new EmployeeStatusRequest();
		employeeStatusRequest.setEmployeeStatus(getEmpStatusList());
		employeeStatusRequest.getEmployeeStatus().get(0).setId(null);
		employeeStatusRequest.setTransactionType("update");
		//when(employeeStatusFacadeImpl.setEmployeeStatus(employeeStatusRequest)).thenReturn(failureResponse);
		ResponseEntity<Object> setEmp = statusController.setEmployeeStatus(employeeStatusRequest, request, response);
		HttpStatus statusCode = setEmp.getStatusCode();
		assertNotEquals(HttpStatus.OK, statusCode);
	}

	@Test
	public void setEmpStatusUpdateStatusNullCheck() throws SQLException {
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		EmployeeStatusRequest employeeStatusRequest = new EmployeeStatusRequest();
		EmployeeStatus empStatus = new EmployeeStatus();
		EmployeeStatus empStatus1 = new EmployeeStatus();
		empStatus.setId(4);
		empStatus.setStatus("");
		empStatus1.setId(5);
		List<EmployeeStatus> empStatusList = new ArrayList<EmployeeStatus>();
		empStatusList.add(empStatus);
		empStatusList.add(empStatus1);
		employeeStatusRequest.setEmployeeStatus(empStatusList);
		employeeStatusRequest.setTransactionType("update");
		//when(employeeStatusFacadeImpl.setEmployeeStatus(employeeStatusRequest)).thenReturn(failureResponse);
		ResponseEntity<Object> setEmp = statusController.setEmployeeStatus(employeeStatusRequest, request, response);
		HttpStatus statusCode = setEmp.getStatusCode();
		assertNotEquals(HttpStatus.OK, statusCode);
	}

	@Test
	public void setEmpStatusUpdateStatusIdNullCheck() throws SQLException {
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		EmployeeStatusRequest employeeStatusRequest = new EmployeeStatusRequest();
		List<EmployeeStatus> employeeStatus = new ArrayList<EmployeeStatus>();
		EmployeeStatus empStatus = new EmployeeStatus();
		EmployeeStatus empStatus1 = new EmployeeStatus();
		empStatus.setId(8);
		empStatus.setStatus("abc");
		// empStatus1.setId(6); //empStatus1.setStatus("def");
		employeeStatus.add(empStatus);
		employeeStatus.add(empStatus1);
		employeeStatusRequest.setEmployeeStatus(employeeStatus);
		employeeStatusRequest.setTransactionType("update");
		//when(employeeStatusFacadeImpl.setEmployeeStatus(employeeStatusRequest)).thenReturn(failureResponse);
		ResponseEntity<Object> setEmp = statusController.setEmployeeStatus(employeeStatusRequest, request, response);
		HttpStatus statusCode = setEmp.getStatusCode();
		assertNotEquals(HttpStatus.OK, statusCode);
	}

	@Test
	public void setEmpStatusDeleteTest() throws SQLException {
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		EmployeeStatusRequest employeeStatusRequest = new EmployeeStatusRequest();
		List<EmployeeStatus> employeeStatus = new ArrayList<EmployeeStatus>();
		EmployeeStatus empStatus = new EmployeeStatus();
		EmployeeStatus empStatus1 = new EmployeeStatus();
		empStatus.setId(8);
		empStatus1.setId(5);
		employeeStatus.add(empStatus);
		employeeStatus.add(empStatus1);
		employeeStatusRequest.setEmployeeStatus(employeeStatus);
		employeeStatusRequest.setTransactionType("delete");
		when(employeeStatusFacadeImpl.setEmployeeStatus(employeeStatusRequest)).thenReturn(failureResponse);
		ResponseEntity<Object> setEmp = statusController.setEmployeeStatus(employeeStatusRequest, request, response);
		HttpStatus statusCode = setEmp.getStatusCode();
		assertNotEquals(HttpStatus.OK, statusCode);
	}

	@Test
	public void setEmpStatusDeleteIdNullCheck() throws SQLException {
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		EmployeeStatusRequest employeeStatusRequest = new EmployeeStatusRequest();
		employeeStatusRequest.setEmployeeStatus(getEmpStatusList());
		employeeStatusRequest.getEmployeeStatus().get(0).setId(null);
		employeeStatusRequest.setTransactionType("delete");
		when(employeeStatusFacadeImpl.setEmployeeStatus(employeeStatusRequest)).thenReturn(failureResponse);
		ResponseEntity<Object> setEmp = statusController.setEmployeeStatus(employeeStatusRequest, request, response);
		HttpStatus statusCode = setEmp.getStatusCode();
		assertNotEquals(HttpStatus.OK, statusCode);
	}

	@Test
	public void setEmployeeStatusTestCatch() throws SQLException {
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		EmployeeStatusRequest employeeStatusRequest = new EmployeeStatusRequest();
		employeeStatusRequest.setEmployeeStatus(getEmpStatusList());
		employeeStatusRequest.setTransactionType("save");
		when(employeeStatusFacadeImpl.setEmployeeStatus(employeeStatusRequest)).thenThrow(new RuntimeException());
		ResponseEntity<Object> setEmp = statusController.setEmployeeStatus(employeeStatusRequest, request, response);
		HttpStatus statusCode = setEmp.getStatusCode();
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
	}

	@Test
	public void testSetEmployeeStatusSave() throws SQLException {
		HttpServletRequest httpServletRequest = null;
		HttpServletResponse httpServletResponse = null;
		EmployeeStatusRequest employeeStatusRequest = new EmployeeStatusRequest();
		EmployeeStatus empStatus = new EmployeeStatus();
		EmployeeStatus empStatus1 = new EmployeeStatus();
		List<EmployeeStatus> employeeStatus = new ArrayList<EmployeeStatus>();
		empStatus.setStatus("Active");
		empStatus1.setStatus("Inactive");
		employeeStatus.add(empStatus);
		employeeStatus.add(empStatus1);
		employeeStatusRequest.setEmployeeStatus(employeeStatus);
		employeeStatusRequest.setTransactionType("save");
		when(employeeStatusFacadeImpl.setEmployeeStatus(employeeStatusRequest)).thenReturn(successResponse);
		ResponseEntity<Object> saveStatus = statusController.setEmployeeStatus(employeeStatusRequest,
				httpServletRequest, httpServletResponse);
		HttpStatus status = saveStatus.getStatusCode();
		assertEquals(HttpStatus.OK, status);
	}

	@Test
	public void testSetEmployeeStatusSaveNull() throws SQLException {
		HttpServletRequest httpServletRequest = null;
		HttpServletResponse httpServletResponse = null;
		EmployeeStatusRequest employeeStatusRequest = new EmployeeStatusRequest();
		EmployeeStatus empStatus = new EmployeeStatus();
		EmployeeStatus empStatus1 = new EmployeeStatus();
		List<EmployeeStatus> employeeStatus = new ArrayList<EmployeeStatus>();
		empStatus.setStatus("");
		empStatus1.setStatus("Inactive");
		employeeStatus.add(empStatus);
		employeeStatus.add(empStatus1);
		employeeStatusRequest.setEmployeeStatus(employeeStatus);
		employeeStatusRequest.setTransactionType("save");
		when(employeeStatusFacadeImpl.setEmployeeStatus(employeeStatusRequest)).thenReturn(successResponse);
		ResponseEntity<Object> saveStatus = statusController.setEmployeeStatus(employeeStatusRequest,
				httpServletRequest, httpServletResponse);
		HttpStatus status = saveStatus.getStatusCode();
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, status);
	}

	@Test
	public void testGetEmployeeStatus() throws SQLException {
		HttpServletRequest httpServletRequest = null;
		HttpServletResponse httpServletResponse = null;
		employeeStatusRequest = new EmployeeStatusRequest();
		employeeStatusRequest.setTransactionType("getall");
		when(employeeStatusFacadeImpl.getEmployeeStatus()).thenReturn(successResponse);
		ResponseEntity<Object> saveStatus = statusController.getEmployeeStatus(employeeStatusRequest,
				httpServletRequest, httpServletResponse);
		HttpStatus status = saveStatus.getStatusCode();
		assertEquals(HttpStatus.OK, status);
	}

	@Test
	public void testGetEmployeeStatusNullCheck() {
		HttpServletRequest httpServletRequest = null;
		HttpServletResponse httpServletResponse = null;
		employeeStatusRequest = new EmployeeStatusRequest();
		employeeStatusRequest.setTransactionType("");
		when(employeeStatusFacadeImpl.getEmployeeStatus()).thenReturn(failureResponse);
		ResponseEntity<Object> saveStatus = statusController.getEmployeeStatus(employeeStatusRequest,
				httpServletRequest, httpServletResponse);
		HttpStatus status = saveStatus.getStatusCode();
		assertNotEquals(HttpStatus.OK, status);
	}

	@Test
	public void testGetEmployeeStatusCatch() {
		HttpServletRequest httpServletRequest = null;
		HttpServletResponse httpServletResponse = null;
		employeeStatusRequest = new EmployeeStatusRequest();
		employeeStatusRequest.setTransactionType("getall");
		when(employeeStatusFacadeImpl.getEmployeeStatus()).thenThrow(new RuntimeException());
		ResponseEntity<Object> saveStatus = statusController.getEmployeeStatus(employeeStatusRequest,
				httpServletRequest, httpServletResponse);
		HttpStatus status = saveStatus.getStatusCode();
		assertNotEquals(HttpStatus.OK, status);
	}

}
