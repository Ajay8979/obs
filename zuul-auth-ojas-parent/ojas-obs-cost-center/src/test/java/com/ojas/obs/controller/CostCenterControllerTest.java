package com.ojas.obs.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
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
import static org.mockito.Matchers.anyObject;
import com.ojas.obs.facade.CostCenterFacade;
import com.ojas.obs.facade.CostCenterFacadeImpl;
import com.ojas.obs.model.CostCenter;
import com.ojas.obs.model.CostCenterRequest;
import com.ojas.obs.model.CostCenterResponse;
import com.ojas.obs.utility.ErrorResponse;

public class CostCenterControllerTest {

	@InjectMocks
	private CostCenterController costCenterController;
	@Mock
	CostCenterFacade costCenterFacade;
	@Spy
	ErrorResponse errorResponse = new ErrorResponse();
	@Spy
	ResponseEntity<Object> responseEntity = new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
	@Spy
	ResponseEntity<Object> successEntity = new ResponseEntity<>(HttpStatus.OK);
	@Spy
	CostCenterRequest costCenterRequest = new CostCenterRequest();
	@Spy
	CostCenterResponse costCenterResponse = new CostCenterResponse();
	@Spy
	List<CostCenterResponse> costList = new ArrayList<CostCenterResponse>();

	@Before
	public void beforeTest() {
		costCenterController = new CostCenterController();
		costCenterFacade = mock(CostCenterFacadeImpl.class);
		setCollaborator(costCenterController, "costCenterService", costCenterFacade);
	}

	private void setCollaborator(Object object, String name, Object service) {

		Field field;
		try {
			field = object.getClass().getDeclaredField(name);
			field.setAccessible(true);

			field.set(object, service);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public List<CostCenter> getCostCenter() {
		ArrayList<CostCenter> arrayList = new ArrayList<>();
		CostCenter costCenter = new CostCenter();
		costCenter.setId(1);
		costCenter.setCostCenterCode(123);
		//costCenter.setFlag(true);
		CostCenter costCenter2 = new CostCenter();
		costCenter2.setId(1);
		costCenter2.setCostCenterCode(123);
		//costCenter2.setFlag(true);
		arrayList.add(costCenter);
		arrayList.add(costCenter2);
		return arrayList;
	}
	public List<CostCenter> emptyCostCenter() {
		ArrayList<CostCenter> arrayList = new ArrayList<>();
		CostCenter costCenter = new CostCenter();
			CostCenter costCenter2 = new CostCenter();
		
		arrayList.add(costCenter);
		arrayList.add(costCenter2);
		return arrayList;
	}
	@Test
	public void nullTranscationTest() throws Exception {
		CostCenterRequest costCenterRequest2 = new CostCenterRequest();
		costCenterRequest2.setCostCenter(this.getCostCenter());
		costCenterRequest2.setPageNo(1);
		costCenterRequest2.setPageSize(2);
		costCenterRequest2.setSessionId("1234");
		

		costCenterRequest2.setTransactionType("");
		HttpServletRequest request = null;
		HttpServletResponse response = null;
			when(costCenterFacade.set(costCenterRequest2)).thenReturn(successEntity);
		
		ResponseEntity<Object> setCostCenter = costCenterController.set(costCenterRequest2, request, response);
		HttpStatus statusCode = setCostCenter.getStatusCode();
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
		
	}
	@Test
	public void nullTest() throws Exception {
		CostCenterRequest costCenterRequest2 = costCenterRequest;
		costCenterRequest2.setCostCenter(this.emptyCostCenter());
		costCenterRequest2.setPageNo(1);
		costCenterRequest2.setPageSize(2);
		costCenterRequest2.setSessionId("1234");
		

		costCenterRequest2.setTransactionType("save");
		HttpServletRequest request = null;
		HttpServletResponse response = null;
			when(costCenterFacade.set(costCenterRequest2)).thenReturn(successEntity);
		
		ResponseEntity<Object> setCostCenter = costCenterController.set(costCenterRequest2, request, response);
		HttpStatus statusCode = setCostCenter.getStatusCode();
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
	}
//---------------null checking requestobject--------------
	
	@Test
	public void nullObjectTest() throws Exception {
		//CostCenterRequest costCenterRequest2 = new CostCenterRequest();
		costCenterRequest=null;
		HttpServletRequest request = null;
		HttpServletResponse response = null;
			when(costCenterFacade.set(costCenterRequest)).thenReturn(null);
		
		ResponseEntity<Object> setCostCenter = costCenterController.set(costCenterRequest, request, response);
		HttpStatus statusCode = setCostCenter.getStatusCode();
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
		
	}

	@Test
	public void transcationTest() throws Exception {
		CostCenterRequest costCenterRequest2 = new CostCenterRequest();
		costCenterRequest2.setCostCenter(this.getCostCenter());
		costCenterRequest2.setPageNo(1);
		costCenterRequest2.setPageSize(2);
		costCenterRequest2.setSessionId("123");
		

		costCenterRequest2.setTransactionType("get");
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		try {
			when(costCenterFacade.set(costCenterRequest2)).thenReturn(successEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseEntity<Object> setCostCenter = costCenterController.set(costCenterRequest2, request, response);
		HttpStatus statusCode = setCostCenter.getStatusCode();
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
	}
	@Test
	public void setTest() throws Exception {
		CostCenterRequest costCenterRequest2 = new CostCenterRequest();
		costCenterRequest2.setCostCenter(this.getCostCenter());
		costCenterRequest2.setPageNo(1);
		costCenterRequest2.setPageSize(2);
		costCenterRequest2.setSessionId("1234");
		

		costCenterRequest2.setTransactionType("save");
		HttpServletRequest request = null;
		HttpServletResponse response = null;
			when(costCenterFacade.set(costCenterRequest2)).thenReturn(successEntity);
		
		ResponseEntity<Object> setCostCenter = costCenterController.set(costCenterRequest2, request, response);
		HttpStatus statusCode = setCostCenter.getStatusCode();
		assertEquals(HttpStatus.OK, statusCode);
	}

	@Test
	public void getTest() throws Exception {
		CostCenterRequest costCenterRequest2 = new CostCenterRequest();
		costCenterRequest2.setPageNo(1);
		costCenterRequest2.setPageSize(2);
		costCenterRequest2.setSessionId("1234");

		costCenterRequest2.setTransactionType("get");
		HttpServletRequest request = null;
		HttpServletResponse response = null;
			when(costCenterFacade.get(costCenterRequest2)).thenReturn(successEntity);
		
		ResponseEntity<Object> setCostCenter = costCenterController.get(costCenterRequest2, request, response);
		HttpStatus statusCode = setCostCenter.getStatusCode();
		assertEquals(HttpStatus.OK, setCostCenter.getStatusCode());
	}
//-------------get null checking-------------
	@Test
	public void nullGetTest() throws Exception {
		//CostCenterRequest costCenterRequest2 = new CostCenterRequest();
		costCenterRequest=null;
		HttpServletRequest request = null;
		HttpServletResponse response = null;
			when(costCenterFacade.get(costCenterRequest)).thenReturn(null);
		
		ResponseEntity<Object> setCostCenter = costCenterController.get(costCenterRequest, request, response);
		HttpStatus statusCode = setCostCenter.getStatusCode();
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
		
	}
	
	//-------------sessionid null checking-----------
	@Test
	public void nullSessionIdTest() throws Exception {
		CostCenterRequest costCenterRequest2 = new CostCenterRequest();
		costCenterRequest2.setCostCenter(this.getCostCenter());
		costCenterRequest2.setPageNo(1);
		costCenterRequest2.setPageSize(2);
		costCenterRequest2.setSessionId("");
		

		costCenterRequest2.setTransactionType("get");
		HttpServletRequest request = null;
		HttpServletResponse response = null;
			when(costCenterFacade.get(costCenterRequest2)).thenReturn(successEntity);
		
		ResponseEntity<Object> setCostCenter = costCenterController.get(costCenterRequest2, request, response);
		HttpStatus statusCode = setCostCenter.getStatusCode();
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
	}
	
}
