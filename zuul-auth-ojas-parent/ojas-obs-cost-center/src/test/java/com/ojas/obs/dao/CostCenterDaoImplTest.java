 package com.ojas.obs.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ojas.obs.model.CostCenter;
import com.ojas.obs.model.CostCenterRequest;
import com.ojas.obs.utility.ErrorResponse;
import static com.ojas.obs.constants.UserConstants.GETCOSTCENTER;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CostCenterDaoImplTest {

	@Mock
	private CostCenterDao costCenterDao;

	@Mock
	private CostCenterImpl costCenterImpl;

	@Spy
	CostCenterRequest costCenterRequest;

	@Spy
	ErrorResponse error = new ErrorResponse();

	@Mock
	DataSource dataSource;

	@Mock
	Connection connection;

	@Mock
	JdbcTemplate jdbcTemplate;

	@Spy
	List<CostCenter> costCenterList = new ArrayList<CostCenter>();

	@Before
	public void init() {
		costCenterImpl = new CostCenterImpl();
		// costCenterDao=mock(CostCenterDao.class);
		jdbcTemplate = mock(JdbcTemplate.class);
		dataSource = mock(DataSource.class);
		connection = mock(Connection.class);
		this.setCollaborator(costCenterImpl, "jdbcTemplate", jdbcTemplate);

	}

	public void setCollaborator(Object object, String name, Object collab)

	{
		Field field;
		try {
			field = object.getClass().getDeclaredField(name);
			field.setAccessible(true);
			field.set(object, collab);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("exception occured");
		}

	}

	@Test
	public void getDataSource1() throws Exception {
		when(jdbcTemplate.getDataSource()).thenReturn(dataSource);
		assertEquals(dataSource, jdbcTemplate.getDataSource());
	}
//----------------save test case-------------------
	@Test
	public void testSave() throws Exception {
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

		when(costCenterDao.save(arrayList)).thenReturn(true);

		boolean save = costCenterDao.save(arrayList);
		assertEquals(true, save);

	}
	
	//-----------------save null test case-------------------
	
	
	@Test
	public void testSaveNull() throws Exception {
		ArrayList<CostCenter> arrayList = new ArrayList<>();

		when(costCenterDao.save(arrayList)).thenReturn(false);

		boolean save = costCenterDao.save(arrayList);
		assertEquals(false, save);

	}
	
	//------------------update test case---------------
	
	@Test
	public void testUpdate() throws Exception {
		ArrayList<CostCenter> arrayList = new ArrayList<>();
		CostCenter costCenter = new CostCenter();
		costCenter.setId(1);
		costCenter.setCostCenterCode(123);
		CostCenter costCenter2 = new CostCenter();
		costCenter2.setId(1);
		costCenter2.setCostCenterCode(123);
		arrayList.add(costCenter);
		arrayList.add(costCenter2);
		
		when(costCenterDao.updateCenter(arrayList)).thenReturn(true);
		boolean save = costCenterDao.updateCenter(arrayList);
		assertEquals(true, save);

	}

	//------------null update test case----------------
	

	@Test
	public void testUpdateNull() throws Exception {
		ArrayList<CostCenter> arrayList = new ArrayList<>();
		
		when(costCenterDao.save(arrayList)).thenReturn(false);

		boolean save = costCenterDao.save(arrayList);
		assertEquals(false, save);

	}
	
	//-------------delete test case---------------------
	
	@Test
	public void testDelete() throws Exception {
		ArrayList<CostCenter> arrayList = new ArrayList<>();
		CostCenter costCenter = new CostCenter();
		costCenter.setId(1);
		CostCenter costCenter2 = new CostCenter();
		costCenter2.setId(1);
		arrayList.add(costCenter);
		arrayList.add(costCenter2);
		
		for (CostCenter costCenter3 : arrayList) {

			when(costCenterDao.deleteCenterCode(costCenter3.getId())).thenReturn(true);

			boolean save = costCenterDao.deleteCenterCode(costCenter3.getId());
			assertEquals(true, save);
		}
	}

	//---------------------null delete test case---------------
	

	@Test
	public void testDeleteNull() throws Exception {
		ArrayList<CostCenter> arrayList = new ArrayList<>();
		
		when(costCenterDao.save(arrayList)).thenReturn(false);

		boolean save = costCenterDao.save(arrayList);
		assertEquals(false, save);

	}
	
	public List<CostCenter> getAllCostCenter() {
		CostCenter costCenter = new CostCenter();

		ArrayList<CostCenter> arrayList = new ArrayList<>();
		arrayList.add(costCenter);
		return arrayList;
	}

	public CostCenterRequest getCostCenterRequest() {
		CostCenterRequest costCenterRequest1 = new CostCenterRequest();
		costCenterRequest1.setCostCenter(this.getAllCostCenter());
		costCenterRequest1.setPageNo(1);
		costCenterRequest1.setPageSize(2);
		costCenterRequest1.setSessionId("1234");
		costCenterRequest1.setTransactionType("get");
		return costCenterRequest1;
	}

	@Test
	public void testGetAllCostCenter() throws Exception {
		
		
		List<CostCenter> list = new ArrayList<CostCenter>();
		when(jdbcTemplate.query(GETCOSTCENTER, new BeanPropertyRowMapper<>(CostCenter.class))).thenReturn(list);
		List<CostCenter> costCenterList1 = costCenterDao.getAllCostCenter(getCostCenterRequest());
		assertEquals(list, costCenterList1);

			}

}
