package com.ojas.obs.passport.facadeTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ojas.obs.passport.Request.PassportRequest;
import com.ojas.obs.passport.Response.PassportResponse;
import com.ojas.obs.passport.dao.PassportDao;
import com.ojas.obs.passport.daoImpl.PassportDaoImpl;
import com.ojas.obs.passport.facadeImpl.PassportFacadeImpl;
import com.ojas.obs.passport.model.ErrorResponse;
import com.ojas.obs.passport.model.Passport;

import jdk.nashorn.internal.ir.annotations.Ignore;
@Ignore
@RunWith(MockitoJUnitRunner.Silent.class)
public class PassportFacadeTest {

	@InjectMocks
	private PassportFacadeImpl passportFacadeImpl;

	@Mock
	PassportDao passportDao;

	@Mock
	PassportDaoImpl passportDaoImpl;

	@Spy
	ErrorResponse errorResponse = new ErrorResponse();

	@Spy
	ResponseEntity<Object> responseEntity = new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);

	@Spy
	ResponseEntity<Object> successEntity = new ResponseEntity<>(HttpStatus.OK);

	@Spy
	PassportRequest passportRequest2 = new PassportRequest();

	@Spy
	PassportResponse passportResponse = new PassportResponse();

	@Spy
	List<PassportResponse> passList = new ArrayList<PassportResponse>();
	@Spy
	List<Passport> passportList = new ArrayList<Passport>();

	@Before
	public void beforeTest() {
		passportFacadeImpl = new PassportFacadeImpl();
		passportDaoImpl = mock(PassportDaoImpl.class);
		setCollaborator(passportFacadeImpl, "passportDaoImpl", passportDaoImpl);
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

	public PassportRequest passportRequest() {

		passportRequest2 = new PassportRequest();
		passportRequest2.setPassportList(this.getPassport());
		passportRequest2.setPageNo(1);
		passportRequest2.setPageSize(2);
		passportRequest2.setSessionId("1234");
		return passportRequest2;
	}

	public List<Passport> getPassport() {
		Passport passport = new Passport();
		passport.setId(1);
		passport.setCenterName("centername1");
		//passport.setCreatedBy(1234);
		//passport.setUpdatedBy(1234);
		//passport.setFlag(true);
		Passport passport2 = new Passport();
		passport2.setId(1);
		passport2.setCenterName("centername1");
		//passport2.setCreatedBy(1234);
		//passport2.setUpdatedBy(1234);
		//passport2.setFlag(true);

		List<Passport> list = new ArrayList<Passport>();
		list.add(passport);
		list.add(passport2);
		return list;
	}
//save
	@Test//ok
	public void setSaveTest() {
	    passportRequest2 = new PassportRequest();
		//passportRequest2.setPassportList(this.getPassport());
		passportRequest2.setPageNo(1);
		passportRequest2.setPageSize(2);
		passportRequest2.setSessionId("1234");

		passportRequest2.setTransaactionType("save");
		try {
			when(passportDaoImpl.savePassport(passportRequest2)).thenReturn(true);
			when(passportDaoImpl.getcountPassport(passportRequest2)).thenReturn(2);
			ResponseEntity<Object> setPassport = passportFacadeImpl.setPassport(passportRequest2);
			HttpStatus statusCode = setPassport.getStatusCode();
			assertEquals(HttpStatus.OK, statusCode);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	// save Null
	//@Test//ok
	public void setSaveFalseTest() {
	 passportRequest2 = new PassportRequest();
		passportRequest2.setPassportList(this.getPassport());
		passportRequest2.setPageNo(1);
		passportRequest2.setPageSize(2);
		passportRequest2.setSessionId("1234");

		//passportRequest2.setTransaactionType("save");
		try {
			when(passportDaoImpl.savePassport(passportRequest2)).thenReturn(false);
			when(passportDaoImpl.getcountPassport(passportRequest2)).thenReturn(2);
			ResponseEntity<Object> setPassport = passportFacadeImpl.setPassport(passportRequest2);
			HttpStatus statusCode = setPassport.getStatusCode();
			assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
//update
	@Test//ok
	public void setUpdateTest() {
		PassportRequest passportRequest2 = new PassportRequest();
		passportRequest2.setPassportList(this.getPassport());
		passportRequest2.setPageNo(1);
		passportRequest2.setPageSize(2);
		passportRequest2.setSessionId("1234");
		passportRequest2.setTransaactionType("update");
		try {
			when(passportDaoImpl.updatePassport(passportRequest2)).thenReturn(true);
			when(passportDaoImpl.getcountPassport(passportRequest2)).thenReturn(2);
			ResponseEntity<Object> setPassport = passportFacadeImpl.setPassport(passportRequest2);
			HttpStatus statusCode = setPassport.getStatusCode();
			assertEquals(HttpStatus.OK, statusCode);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
//Update Null
	@Test //ok
	public void setUpdatefalseTest() {
		PassportRequest passportRequest2 = new PassportRequest();
		//passportRequest2.setPassportList(this.getPassport());
		passportRequest2.setPageNo(1);
		passportRequest2.setPageSize(2);
		passportRequest2.setSessionId("1234");
		//passportRequest2.setTransaactionType("update");
		try {
			when(passportDaoImpl.updatePassport(passportRequest2)).thenReturn(false);
			when(passportDaoImpl.getcountPassport(passportRequest2)).thenReturn(2);
			ResponseEntity<Object> setPassport = passportFacadeImpl.setPassport(passportRequest2);
			HttpStatus statusCode = setPassport.getStatusCode();
			assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test 
	public void setUpdateNullIDTest() {
	  passportRequest2 = new PassportRequest();
		passportRequest2.setPassportList(this.getPassport());
	   List<Passport> passport = this.getPassport();
		passport.get(0).setId(null);
		passportRequest2.setPageNo(1);
		passportRequest2.setPageSize(2);
		passportRequest2.setSessionId("1234");
		passportRequest2.setTransaactionType("update");
		try {
			when(passportDaoImpl.updatePassport(passportRequest2)).thenReturn(false);
			when(passportDaoImpl.getcountPassport(passportRequest2)).thenReturn(2);
			ResponseEntity<Object> setPassport = passportFacadeImpl.setPassport(passportRequest2);
			HttpStatus statusCode = setPassport.getStatusCode();
			assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
//Delete
	/*
	 * @Test public void setDeleteTrueTest() { passportRequest2 = new
	 * PassportRequest(); passportRequest2.setPassportList(this.getPassport());
	 * passportRequest2.setPageNo(1); passportRequest2.setPageSize(2);
	 * passportRequest2.setSessionId("1234");
	 * passportRequest2.setTransaactionType("delete"); try {
	 * when(passportDaoImpl.deletePassport(passportRequest2)).thenReturn(true);
	 * when(passportDaoImpl.getcountPassport(passportRequest2)).thenReturn(2);
	 * ResponseEntity<Object> setPassport =
	 * passportFacadeImpl.setPassport(passportRequest2); HttpStatus statusCode =
	 * setPassport.getStatusCode(); assertEquals(HttpStatus.OK, statusCode); } catch
	 * (Exception e) { e.printStackTrace(); }
	 * 
	 * } //Delete Null ID
	 * 
	 * @Test public void setDeleteIdNullTest() { passportRequest2 = new
	 * PassportRequest();
	 * 
	 * List<Passport> passport = this.getPassport(); passport.get(0).setId(null);
	 * 
	 * passportRequest2.setPassportList(passport); passportRequest2.setPageNo(1);
	 * passportRequest2.setPageSize(2); passportRequest2.setSessionId("1234");
	 * passportRequest2.setPassportList(getPassport());
	 * passportRequest2.getPassportList().get(0).setId(null);
	 * passportRequest2.setTransaactionType("delete"); try {
	 * when(passportDaoImpl.deletePassport(passportRequest2)).thenReturn(false);
	 * when(passportDaoImpl.getcountPassport(passportRequest2)).thenReturn(2);
	 * ResponseEntity<Object> setPassport =
	 * passportFacadeImpl.setPassport(passportRequest2); HttpStatus statusCode =
	 * setPassport.getStatusCode(); assertEquals(HttpStatus.UNPROCESSABLE_ENTITY,
	 * statusCode); } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 */
//Null transaction
	@Test// ok
	public void setTransactionNullTest() {
		 passportRequest2 = new PassportRequest();
		List<Passport> passport = this.getPassport();
		passportRequest2.setPassportList(passport);
		passportRequest2.setPageNo(1);
		passportRequest2.setPageSize(2);
		passportRequest2.setSessionId("1234");
		passportRequest2.setTransaactionType(null);
		try {
			when(passportDaoImpl.savePassport(passportRequest2)).thenReturn(false);
			when(passportDaoImpl.getcountPassport(passportRequest2)).thenReturn(2);
			ResponseEntity<Object> setPassport = passportFacadeImpl.setPassport(passportRequest2);
			HttpStatus statusCode = setPassport.getStatusCode();
			assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void getPassportForNoRecordTest() {
		 passportRequest2 = new PassportRequest();
		List<Passport> passportlist =  new ArrayList<Passport>();
		passportRequest2.setPassportList(getPassport());
		passportRequest2.setPageNo(1);
		passportRequest2.setPageSize(2);
		passportRequest2.setSessionId("1234");
		passportRequest2.setTransaactionType("getAll");
		try {
			when(passportDaoImpl.getAll(passportRequest2)).thenReturn(passportlist);
			when(passportDaoImpl.getcountPassport(passportRequest2)).thenReturn(2);
			ResponseEntity<Object> getPassport = passportFacadeImpl.getPassport(passportRequest2);
			HttpStatus statusCode = getPassport.getStatusCode();
			assertEquals(HttpStatus.CONFLICT, statusCode);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Test
	public void getPassportForRecordTest() {
		 passportRequest2 = new PassportRequest();
		passportRequest2.setPassportList(getPassport());
		passportRequest2.setPageNo(1);
		passportRequest2.setPageSize(2);
		passportRequest2.setSessionId("1234");
		passportRequest2.setTransaactionType("getAll");
		try {
			when(passportDaoImpl.getAll(passportRequest2)).thenReturn(getPassport());
			when(passportDaoImpl.getcountPassport(passportRequest2)).thenReturn(2);
			ResponseEntity<Object> getPassport = passportFacadeImpl.getPassport(passportRequest2);
			HttpStatus statusCode = getPassport.getStatusCode();
			assertEquals(HttpStatus.OK, statusCode);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Test
	public void getPassportNoPeginationTest() {
		 passportRequest2 = new PassportRequest();
		passportRequest2.setPassportList(getPassport());
		passportRequest2.setPageNo(0);
		passportRequest2.setPageSize(0);
		passportRequest2.setSessionId("1234");
		passportRequest2.setTransaactionType("getAll");
		try {
			when(passportDaoImpl.getAll(passportRequest2)).thenReturn(getPassport());
			when(passportDaoImpl.getcountPassport(passportRequest2)).thenReturn(2);
			ResponseEntity<Object> getPassport = passportFacadeImpl.getPassport(passportRequest2);
			HttpStatus statusCode = getPassport.getStatusCode();
			assertEquals(HttpStatus.OK, statusCode);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Test
	public void getPassportEmptyTest() {
		 passportRequest2 = new PassportRequest();
		passportRequest2.setPassportList(null);
		passportRequest2.setPageNo(1);
		passportRequest2.setPageSize(2);
		passportRequest2.setSessionId("1234");
		passportRequest2.setTransaactionType("getAll");
		try {
			when(passportDaoImpl.getAll(passportRequest2)).thenReturn(passportList);
			//when(passportDaoImpl.getcountPassport(passportRequest2)).thenReturn(2);
			ResponseEntity<Object> setPassport = passportFacadeImpl.getPassport(passportRequest2);
			HttpStatus statusCode = setPassport.getStatusCode();
			assertEquals(HttpStatus.CONFLICT, statusCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	
	//error
//	@Test
	public void getPgZeroTest() {
		 passportRequest2 = new PassportRequest();
		passportRequest2.setPassportList(getPassport());
		passportRequest2.setPageNo(1);;
		passportRequest2.setPageSize(0);
		passportRequest2.setSessionId("1234");
		passportRequest2.setTransaactionType("getAll");
		try {
			when(passportDaoImpl.getAll(passportRequest2)).thenReturn(passportList);
			when(passportDaoImpl.getcountPassport(passportRequest2)).thenReturn(2);
			ResponseEntity<Object> setPassport = passportFacadeImpl.getPassport(passportRequest2);
			HttpStatus statusCode = setPassport.getStatusCode();
			assertEquals(HttpStatus.OK, statusCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	//getById
	@Test
	public void getByidTest() {
		 passportRequest2 = new PassportRequest();
		List<Passport> passportlist =  new ArrayList<Passport>();
		passportRequest2.setPassportList(getPassport());
		passportRequest2.getPassportList().get(0).setId(1);
		passportRequest2.setPageNo(1);
		passportRequest2.setPageSize(2);
		passportRequest2.setSessionId("1234");
		passportRequest2.setTransaactionType("getById");
		try {
			when(passportDaoImpl.getById(passportRequest2)).thenReturn(passportlist);
			when(passportDaoImpl.getcountPassport(passportRequest2)).thenReturn(2);
			ResponseEntity<Object> getPassport = passportFacadeImpl.getPassport(passportRequest2);
			HttpStatus statusCode = getPassport.getStatusCode();
			assertEquals(HttpStatus.OK, statusCode);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
