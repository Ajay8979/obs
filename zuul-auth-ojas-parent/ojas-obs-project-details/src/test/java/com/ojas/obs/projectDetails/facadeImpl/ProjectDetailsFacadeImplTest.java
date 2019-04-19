package com.ojas.obs.projectDetails.facadeImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.ojas.obs.projectDetails.Dao.ProjectDetailsDao;
import com.ojas.obs.projectDetails.DaoImpl.ProjectDetailsDaoImpl;
import com.ojas.obs.projectDetails.controller.ProjectDetailsController;
import com.ojas.obs.projectDetails.facade.ProjectDetailsFacade;
import com.ojas.obs.projectDetails.model.ErrorResponse;
import com.ojas.obs.projectDetails.model.ProjectDetails;
import com.ojas.obs.projectDetails.request.ProjectDetailsRequest;
import com.ojas.obs.projectDetails.response.ProjectDetailsResponse;

@RunWith(SpringRunner.class)
public class ProjectDetailsFacadeImplTest {
	@Mock
	ProjectDetailsFacade projectDetailsFacade;
	@InjectMocks
	ProjectDetailsFacadeImpl projectDetailsFacadeImpl;
	@Mock
	ProjectDetailsDao projectDetailsDao;
	@Mock
	ProjectDetailsDaoImpl projectDetailsDaoImpl;
	@InjectMocks
	ProjectDetailsController projectDetailsController;

	@Spy
	ErrorResponse errorResponse = new ErrorResponse();

	@Spy
	ResponseEntity<Object> failureResponse = new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);

	ResponseEntity<Object> conflict = new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);

	@Spy
	ResponseEntity<Object> sucessResponse = new ResponseEntity<>(errorResponse, HttpStatus.OK);

	@Spy
	ProjectDetailsRequest projectDetailsRequest = new ProjectDetailsRequest();

	@Spy
	ProjectDetailsResponse projectDetailsResponse = new ProjectDetailsResponse();

	@Spy
	List<ProjectDetails> projectDetailsList = new ArrayList<>();

	@Spy
	ProjectDetails projectDetails = new ProjectDetails();

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Before
	public void init() {
		projectDetailsFacade = new ProjectDetailsFacadeImpl();

		projectDetailsDaoImpl = mock(ProjectDetailsDaoImpl.class);
		setCollaborator(projectDetailsFacadeImpl, "projectDetailsDao", projectDetailsDaoImpl);
	}

	public void setCollaborator(ProjectDetailsFacadeImpl projectDetailsFacadeImpl, String projectDetailsDao,
			ProjectDetailsDaoImpl projectDetailsDaoImpl) {
		Field field;
		try {
			field = projectDetailsFacadeImpl.getClass().getDeclaredField(projectDetailsDao);
			field.setAccessible(true);
			field.set(projectDetailsFacadeImpl, projectDetailsDaoImpl);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public List<ProjectDetails> projectDetailsList() throws ParseException {

		List<ProjectDetails> list = new ArrayList<ProjectDetails>();

		projectDetails.setContractId(2);
		projectDetails.setRateId(3);
		projectDetails.setStartDate(new Timestamp(dateFormat.parse("2019-02-28").getTime()));
		projectDetails.setEndDate(new Timestamp(dateFormat.parse("2019-03-28").getTime()));
		projectDetails.setBillingId(987);
		projectDetails.setEmployeeId("877");
		projectDetails.setProjectTechStack("project");
		projectDetails.setCustomer("ojas");
		projectDetails.setLocation("hyd");
		projectDetails.setGstApplicable(true);
		projectDetails.setProjectType("running");
		projectDetails.setProjectStatus("internal");
		projectDetails.setBdmContact("emp2");
		projectDetails.setProjectName("obs");
		projectDetails.setInternal(true);
		projectDetails.setFlag(true);
		projectDetails.setCreatedBy("877");

		list.add(projectDetails);

		return list;

	}

	public List<ProjectDetails> projectDetailsUpdatedList() throws ParseException {

		List<ProjectDetails> list = new ArrayList<ProjectDetails>();

		projectDetails.setContractId(2);
		projectDetails.setRateId(3);
		projectDetails.setStartDate(new Timestamp(dateFormat.parse("2019-02-28").getTime()));
		projectDetails.setEndDate(new Timestamp(dateFormat.parse("2019-03-28").getTime()));
		projectDetails.setBillingId(987);
		projectDetails.setEmployeeId("877");
		projectDetails.setProjectTechStack("project");
		projectDetails.setCustomer("ojas");
		projectDetails.setLocation("hyd");
		projectDetails.setGstApplicable(true);
		projectDetails.setProjectType("running");
		projectDetails.setProjectStatus("internal");
		projectDetails.setBdmContact("emp2");
		projectDetails.setInternal(true);
		projectDetails.setFlag(true);
		projectDetails.setCreatedBy("877");
		projectDetails.setUpdatedBy("877");
		projectDetails.setProjectName("obs");
		projectDetails.setId(1);

		list.add(projectDetails);

		return list;
	}

	public ProjectDetails projectDetails() throws ParseException {

		projectDetails.setContractId(2);
		projectDetails.setRateId(3);
		projectDetails.setStartDate(new Timestamp(dateFormat.parse("2019-02-28").getTime()));
		projectDetails.setEndDate(new Timestamp(dateFormat.parse("2019-03-28").getTime()));
		projectDetails.setBillingId(987);
		projectDetails.setEmployeeId("877");
		projectDetails.setProjectTechStack("project");
		projectDetails.setCustomer("ojas");
		projectDetails.setLocation("hyd");
		projectDetails.setGstApplicable(true);
		projectDetails.setProjectType("running");
		projectDetails.setProjectStatus("internal");
		projectDetails.setBdmContact("emp2");
		projectDetails.setInternal(true);
		projectDetails.setFlag(true);
		projectDetails.setProjectName("obs");
		projectDetails.setCreatedBy("877");
		projectDetails.setUpdatedBy("877");
		return projectDetails;
	}

	public List<ProjectDetails> projectDetailsDeleteData() {
		List<ProjectDetails> al = new ArrayList<ProjectDetails>();

		projectDetails.setUpdatedBy("67");
		;
		projectDetails.setId(2);
		al.add(projectDetails);
		return al;
	}

	public ProjectDetailsRequest projectDetailsSaveReq() throws ParseException {

		projectDetailsRequest.setProjectDetailsList(projectDetailsList());

		projectDetailsRequest.setTransactionType("save");
		return projectDetailsRequest;

	}

	public ProjectDetailsRequest projectDetailsUpdateReq() throws ParseException {

		projectDetailsRequest.setProjectDetailsList(projectDetailsUpdatedList());
		projectDetailsRequest.setTransactionType("update");
		return projectDetailsRequest;

	}

	public ProjectDetailsRequest projectDetailsDeleteReq() {

		projectDetailsRequest.setProjectDetailsList(projectDetailsDeleteData());
		projectDetailsRequest.setTransactionType("delete");
		return projectDetailsRequest;

	}

	public ProjectDetailsRequest getByIdprojectDetailsReq() throws ParseException {
		List<ProjectDetails> list = projectDetailsUpdatedList();
		projectDetailsRequest.setProjectDetailsList(list);

		projectDetailsRequest.setTransactionType("getAll");
		return projectDetailsRequest;

	}

	public ProjectDetailsRequest allProjectDetailsReq() {
		projectDetailsRequest.setProjectDetailsList(null);
		projectDetailsRequest.setTransactionType("getAll");
		return projectDetailsRequest;

	}

	public ProjectDetailsResponse projectDetailsRes() {
		projectDetailsResponse.setStatusMessage("success");
		projectDetailsResponse.setProjectDetailsList(projectDetailsList);
		return projectDetailsResponse;
	}

	@Test
	public void saveProjectDetails() throws SQLException, ParseException {

		try {
			when(projectDetailsDaoImpl.saveProjectDetails(projectDetailsSaveReq())).thenReturn(1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		ProjectDetailsResponse setProjectDetails = projectDetailsFacadeImpl.setProjectDetails(projectDetailsSaveReq());
		String status = setProjectDetails.getStatusCode();

		assertEquals("200", status);

	}

	@Test
	public void updateProjectDetails() throws SQLException, ParseException {

		try {
			when(projectDetailsDaoImpl.updateProjectDetails(projectDetailsUpdateReq())).thenReturn(1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		ProjectDetailsResponse setProjectDetails = projectDetailsFacadeImpl
				.setProjectDetails(projectDetailsUpdateReq());
		String status = setProjectDetails.getStatusCode();
		assertEquals("200", status);

	}

	@Test
	public void deleteProjectDetails() throws SQLException {

		try {
			when(projectDetailsDaoImpl.deleteProjectDetails(projectDetailsDeleteReq())).thenReturn(1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		ProjectDetailsResponse setProjectDetails = projectDetailsFacadeImpl
				.setProjectDetails(projectDetailsDeleteReq());
		String status = setProjectDetails.getStatusCode();

		assertEquals("200", status);

	}

	@Test
	public void showProjectDetailsById() throws SQLException, ParseException {
		try {

			List<ProjectDetails> list = projectDetailsUpdatedList();
			when(projectDetailsDaoImpl.getById(1)).thenReturn(list);

		} catch (Exception e) {
			e.printStackTrace();
		}

		ProjectDetailsResponse setProjectDetails = projectDetailsFacadeImpl
				.getProjectDetails(getByIdprojectDetailsReq());
		if (setProjectDetails != null) {
			String status = setProjectDetails.getStatusCode();

			assertEquals("200", status);

		}

	}

	@Test
	public void showProjectDetailsByIdNullData() throws SQLException, ParseException {
		try {

			List<ProjectDetails> list = new ArrayList<ProjectDetails>();
			when(projectDetailsDaoImpl.getById(1)).thenReturn(list);

		} catch (Exception e) {
			e.printStackTrace();
		}

		ProjectDetailsResponse setProjectDetails = projectDetailsFacadeImpl
				.getProjectDetails(getByIdprojectDetailsReq());
		if (setProjectDetails != null) {
			String status = setProjectDetails.getStatusCode();

			assertEquals("200", status);

		}

	}

	@Test
	public void showAllProjectDetails() throws SQLException {

		try {
			List<ProjectDetails> list = projectDetailsUpdatedList();
			when(projectDetailsDaoImpl.getAll()).thenReturn(list);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ProjectDetailsResponse setProjectDetails = projectDetailsFacadeImpl.getProjectDetails(allProjectDetailsReq());

		if (setProjectDetails != null) {
			String status = setProjectDetails.getStatusCode();

			assertEquals("200", status);

		}
	}

	@Test
	public void showAllProjectDetailsWithNullData() throws SQLException {

		List<ProjectDetails> list = new ArrayList<ProjectDetails>();
		when(projectDetailsDaoImpl.getAll()).thenReturn(list);

		ProjectDetailsResponse setProjectDetails = projectDetailsFacadeImpl.getProjectDetails(allProjectDetailsReq());

		if (setProjectDetails != null) {
			String status = setProjectDetails.getStatusCode();

			assertEquals("200", status);

		}
	}

}