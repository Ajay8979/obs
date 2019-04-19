package com.ojas.obs.controller;

import static org.junit.Assert.assertEquals;
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

import com.ojas.obs.facade.SkillFacade;
import com.ojas.obs.facade.SkillFacadeImpl;
import com.ojas.obs.model.ErrorResponse;
import com.ojas.obs.model.Skill;
import com.ojas.obs.request.SkillRequest;
import com.ojas.obs.response.SkillResponse;

public class SkillControllerTest {

	@InjectMocks
	private SkillController skillController;
	
	@Mock
	SkillFacade skillFacade;
	
	@Spy
	ErrorResponse errorResponse = new ErrorResponse();
	@Spy
	ResponseEntity<Object> responseEntity = new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
	@Spy
	ResponseEntity<Object> successEntity = new ResponseEntity<>(HttpStatus.OK);
	
	@Spy
	SkillRequest skillRequest=new SkillRequest();
	@Spy
	SkillResponse skillResponse=new SkillResponse();
	
	@Before
	public void beforeTest() {
		skillController = new SkillController();
		skillFacade = mock(	SkillFacadeImpl.class);
		setCollaborator(skillController, "skillFacade", skillFacade);
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
	
	public List<Skill> getSkill() {
		ArrayList<Skill> arrayList = new ArrayList<>();
		Skill skill = new Skill();
		skill.setId(1);
		skill.setSkill_id(123);
		skill.setSkill_name("java");
		Skill skill1 = new Skill();
		skill1.setId(2);
		skill1.setSkill_id(124);
		skill1.setSkill_name("c");

		arrayList.add(skill);
		arrayList.add(skill1);
		return arrayList;
	}
	
	//----------nullTransaction---------
	@Test
	public void nullTransaction() {
		skillRequest.setListOfSkill(this.getSkill());
		String s=null;
		skillRequest.setTransactionType(s);
		HttpServletRequest request = null;
		HttpServletResponse response = null;
			when(skillFacade.setSkillInfo(skillRequest)).thenReturn(successEntity);
	
			ResponseEntity<Object> setSkill = skillController.setSkill(skillRequest, request, response);
			HttpStatus statusCode = setSkill.getStatusCode();
			assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
	}
	
	//---------nullRequestTest------------
	@Test
	public void nullRequest() {
		skillRequest=null;
		HttpServletRequest request = null;
		HttpServletResponse response = null;
			when(skillFacade.setSkillInfo(skillRequest)).thenReturn(successEntity);
	
			ResponseEntity<Object> setSkill = skillController.setSkill(skillRequest, request, response);
			HttpStatus statusCode = setSkill.getStatusCode();
			assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
	}
	
	
	
	
	//-------set method-------------
	@Test
	public void setSkillTest() throws Exception {
		skillRequest.setListOfSkill(this.getSkill());
		skillRequest.setTransactionType("save");
		HttpServletRequest request = null;
		HttpServletResponse response = null;
			when(skillFacade.setSkillInfo(skillRequest)).thenReturn(successEntity);
	
			ResponseEntity<Object> setSkill = skillController.setSkill(skillRequest, request, response);
			HttpStatus statusCode = setSkill.getStatusCode();
			assertEquals(HttpStatus.OK, statusCode);
	}
	
	
	//-------get method-------------
		@Test
		public void getSkillTest() throws Exception {
			//skillRequest.setListOfSkill(this.getSkillEmpty());
			skillRequest.setTransactionType("getAll");
			HttpServletRequest request = null;
			HttpServletResponse response = null;
				when(skillFacade.getSkillInfo(skillRequest)).thenReturn(successEntity);
		
				ResponseEntity<Object> setSkill = skillController.getSkillInfo(skillRequest, request, response);
				HttpStatus statusCode = setSkill.getStatusCode();
				assertEquals(HttpStatus.OK, statusCode);
		}
		
		//---------nullRequest-------------
		@Test
		public void nullRequestGet() throws SQLException {
			skillRequest=null;
			HttpServletRequest request = null;
			HttpServletResponse response = null;
				when(skillFacade.getSkillInfo(skillRequest)).thenReturn(successEntity);
		
				ResponseEntity<Object> setSkill = skillController.getSkillInfo(skillRequest, request, response);
				HttpStatus statusCode = setSkill.getStatusCode();
				assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
		}
		//nullTransaction--------
		@Test
		public void nullTransactionGet() throws SQLException {
			String s=null;
			skillRequest.setTransactionType(s);
			HttpServletRequest request = null;
			HttpServletResponse response = null;
				when(skillFacade.getSkillInfo(skillRequest)).thenReturn(successEntity);
		
				ResponseEntity<Object> setSkill = skillController.getSkillInfo(skillRequest, request, response);
				HttpStatus statusCode = setSkill.getStatusCode();
				assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
		}
}
