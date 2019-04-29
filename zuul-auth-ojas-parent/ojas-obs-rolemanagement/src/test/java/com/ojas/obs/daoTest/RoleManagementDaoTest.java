package com.ojas.obs.daoTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ojas.obs.daoimpl.RoleManagementDaoImpl;
import com.ojas.obs.error.ErrorResponse;
import com.ojas.obs.model.RoleManagement;
import com.ojas.obs.request.RoleManagementRequest;
import com.ojas.obs.response.RoleManagementResponse;

public class RoleManagementDaoTest {
	@Mock
	JdbcTemplate jdbcTemplate;

	@InjectMocks
	private RoleManagementDaoImpl roleManagementDaoImpl;

	@Spy
	ErrorResponse errorResponse = new ErrorResponse();

	@Spy
	RoleManagementRequest roleManagementRequest;

	@Spy
	RoleManagementResponse roleManagementResponse;

	@Spy
	RoleManagement roleManagement;
	@Spy
	List<RoleManagement> roleManagemenList;

	@Spy
	List<RoleManagement> roleManagementList = new ArrayList<RoleManagement>();
	int[] count = { 1 };
	int[] zeroCount = {};

	@Before
	public void init() {
		roleManagementDaoImpl = new RoleManagementDaoImpl();
		roleManagementList.add(roleManagement);
		jdbcTemplate = mock(JdbcTemplate.class);
		setCollaborator(roleManagementDaoImpl, "jdbcTemplate", jdbcTemplate);
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

	public RoleManagementRequest roleManagementRequest() {
		roleManagementRequest = new RoleManagementRequest();
		RoleManagement roleManagement1 = new RoleManagement();
		roleManagement1.setId(1);
		roleManagement1.setRoleName("admin");
		RoleManagement roleManagement2 = new RoleManagement();
		roleManagement2.setId(2);
		roleManagement2.setRoleName("user");
		List<RoleManagement> roleManagementList = new ArrayList<>();
		roleManagementList.add(roleManagement1);
		roleManagementList.add(roleManagement2);
		roleManagementRequest.setRoleManagement(roleManagementList);
		return roleManagementRequest;
	}

	@Test
	public void saveRoleManagementSuccess() {
		roleManagementRequest = roleManagementRequest();

		when(jdbcTemplate.batchUpdate(anyString(), Mockito.anyList())).thenReturn(count);

		boolean save = roleManagementDaoImpl.saveRoleManagement(roleManagementRequest);
		assertEquals(true, save);
	}

	@Test
	public void saveRoleManagementFail() {
		roleManagementRequest = roleManagementRequest();
		when(jdbcTemplate.batchUpdate(anyString(), Mockito.anyList())).thenReturn(zeroCount);

		boolean save = roleManagementDaoImpl.saveRoleManagement(roleManagementRequest);
		assertEquals(false, save);
	}

	@Test
	public void updateRoleManagementSuccess() {
		roleManagementRequest = roleManagementRequest();
		when(jdbcTemplate.batchUpdate(anyString(), Mockito.anyList())).thenReturn(count);

		boolean update = roleManagementDaoImpl.updateRoleManagement(roleManagementRequest);
		assertEquals(true, update);
	}

	@Test
	public void updateRoleManagementFail() {
		roleManagementRequest = roleManagementRequest();
		when(jdbcTemplate.batchUpdate(anyString(), Mockito.anyList())).thenReturn(zeroCount);

		boolean update = roleManagementDaoImpl.updateRoleManagement(roleManagementRequest);
		assertEquals(false, update);
	}

	@Test
	public void getAllRollManagements() throws SQLException {

		roleManagementRequest = roleManagementRequest();
		String GETALLRECORDS = "select * from obs_RoleManagement";
		when(jdbcTemplate.query(GETALLRECORDS, new BeanPropertyRowMapper<RoleManagement>(RoleManagement.class)))
				.thenReturn(roleManagementList);

		List<RoleManagement> getAll = roleManagementDaoImpl.getAllRollManagements();
		boolean status = getAll.isEmpty();

		assertEquals(true, status);
	}

	@Test
	public void getAllRollManagementsCount() {
		roleManagementRequest = roleManagementRequest();
		String TOTALCOUNT = "select count(*) from obs_RoleManagement";
		when(jdbcTemplate.queryForObject(TOTALCOUNT, Integer.class)).thenReturn(1);
		assertEquals(1, count);
	}

}
