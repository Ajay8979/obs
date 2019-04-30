/*
 * package com.obs.employeeCertificationDetails.controllerTest; import static
 * org.junit.Assert.assertEquals; import static
 * org.junit.Assert.assertNotEquals; import static org.mockito.Mockito.mock;
 * import static org.mockito.Mockito.when; import java.lang.reflect.Field;
 * import java.sql.Date; import java.sql.SQLException; import
 * java.sql.Timestamp; import java.util.ArrayList; import java.util.List; import
 * org.junit.Before; import org.junit.Test; import org.mockito.InjectMocks;
 * import org.mockito.Mock; import org.mockito.Spy; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * com.obs.employeeCertificationDetails.controller.
 * CertificationDetailsController; import
 * com.obs.employeeCertificationDetails.error.ErrorResponse; import
 * com.obs.employeeCertificationDetails.facade.CertificationDetailsFacade;
 * import com.obs.employeeCertificationDetails.facadeImpl.
 * CertificationDetailsFacadeImpl; import
 * com.obs.employeeCertificationDetails.model.CertificationDetails; import
 * com.obs.employeeCertificationDetails.request.CertificationDetailsRequest;
 * import
 * com.obs.employeeCertificationDetails.response.CertificationDetailsResponse;
 * 
 * public class EmployeeCertificationDetailsControllerTest {
 * 
 * @Mock CertificationDetailsFacade certificationDetailsFacadeImpl;
 * 
 * @InjectMocks CertificationDetailsController controller;
 * 
 * @Spy ErrorResponse errorResponse = new ErrorResponse();
 * 
 * @Spy ResponseEntity<Object> failureResponse = new
 * ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
 * 
 * @Spy ResponseEntity<Object> conflict = new
 * ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
 * 
 * @Spy ResponseEntity<Object> sucessResponse = new
 * ResponseEntity<>(errorResponse, HttpStatus.OK);
 * 
 * @Spy CertificationDetailsRequest certiRequest = new
 * CertificationDetailsRequest();
 * 
 * @Spy CertificationDetailsResponse certiResponse = new
 * CertificationDetailsResponse();
 * 
 * 
 * @Before public void init() { controller= new
 * CertificationDetailsController(); certificationDetailsFacadeImpl=
 * mock(CertificationDetailsFacadeImpl.class);
 * setCollaborator(controller,"CertificationDetailsFacadeImpl",
 * certificationDetailsFacadeImpl); } private void setCollaborator(Object
 * object, String name, Object service) { Field field; try { field =
 * object.getClass().getDeclaredField(name); field.setAccessible(true);
 * field.set(object, service); } catch (Exception e) { //e.printStackTrace(); }
 * } public List<CertificationDetails> getModel() { List<CertificationDetails>
 * list = new ArrayList<CertificationDetails>(); CertificationDetails model= new
 * CertificationDetails(); model.setCertificationName("dsgdhg");
 * model.setCreatedBy("12345"); model.setUpdatedBy("1221");
 * model.setIssuedBy("hdjhf"); model.setEmployeeId("1212");
 * model.setCreatedDate(new Timestamp(112134)); model.setDateOfIssue(new
 * Date(545454545) ); model.setFlag(true); list.add(model); return list; }
 * 
 * //@Test //done public void modelObjectNullCheck() throws SQLException {
 * certiRequest =new CertificationDetailsRequest();
 * certiRequest.setTransactionType("save");
 * //certiRequest.setCertificationDetailsModel(getModel());
 * certiRequest.setSessionId("21212");
 * when(certificationDetailsFacadeImpl.setCertificationDetails(certiRequest)).
 * thenReturn(failureResponse); ResponseEntity<Object> contResponse =
 * controller.setCertificationDetails(certiRequest); HttpStatus statusCode =
 * contResponse.getStatusCode(); assertNotEquals(HttpStatus.OK, statusCode); }
 * //@Test //done public void modelObjectPositiveNullCheck() throws SQLException
 * { CertificationDetailsRequest certiRequest = new
 * CertificationDetailsRequest();
 * certiRequest.setCertificationDetailsModel(getModel());
 * certiRequest.setSessionId("21212"); certiRequest.setTransactionType("save");
 * when(certificationDetailsFacadeImpl.setCertificationDetails(certiRequest)).
 * thenReturn(sucessResponse); ResponseEntity<Object> contResponse =
 * controller.setCertificationDetails(certiRequest); HttpStatus statusCode =
 * contResponse.getStatusCode(); assertEquals(HttpStatus.OK, statusCode); }
 * //@Test // public void requestObjectNullCheck() throws SQLException {
 * certiRequest =null; //certiRequest.setTransactionType("save");
 * when(certificationDetailsFacadeImpl.setCertificationDetails(certiRequest)).
 * thenReturn(failureResponse); ResponseEntity<Object> contResponse =
 * controller.setCertificationDetails(certiRequest); HttpStatus statusCode =
 * contResponse.getStatusCode(); assertNotEquals(HttpStatus.CONFLICT,
 * statusCode); } //@Test //done public void requestObjectPositiveNullCheck()
 * throws SQLException { CertificationDetailsRequest certiRequest = new
 * CertificationDetailsRequest(); certiRequest.setSessionId("1214h");
 * certiRequest.setTransactionType("save");
 * certiRequest.setCertificationDetailsModel(getModel());
 * when(certificationDetailsFacadeImpl.setCertificationDetails(certiRequest)).
 * thenReturn(sucessResponse); ResponseEntity<Object> contResponse =
 * controller.setCertificationDetails(certiRequest); HttpStatus statusCode =
 * contResponse.getStatusCode(); assertEquals(HttpStatus.OK, statusCode); }
 * 
 * //@Test //done public void sessionIdPositiveNullCheck() throws SQLException {
 * CertificationDetailsRequest certiRequest = new CertificationDetailsRequest();
 * //certiRequest.setSessionId("1214h");
 * certiRequest.setTransactionType("save");
 * certiRequest.setCertificationDetailsModel(getModel());
 * when(certificationDetailsFacadeImpl.setCertificationDetails(certiRequest)).
 * thenReturn(failureResponse); ResponseEntity<Object> contResponse =
 * controller.setCertificationDetails(certiRequest); HttpStatus statusCode =
 * contResponse.getStatusCode(); assertEquals(HttpStatus.UNPROCESSABLE_ENTITY,
 * statusCode); } //@Test //done public void sessionIdNullCheck() throws
 * SQLException { CertificationDetailsRequest certiRequest = new
 * CertificationDetailsRequest(); //certiRequest.setSessionId("1214h");
 * certiRequest.setTransactionType("save");
 * certiRequest.setCertificationDetailsModel(getModel());
 * when(certificationDetailsFacadeImpl.setCertificationDetails(certiRequest)).
 * thenReturn(failureResponse); ResponseEntity<Object> contResponse =
 * controller.setCertificationDetails(certiRequest); HttpStatus statusCode =
 * contResponse.getStatusCode(); assertNotEquals(HttpStatus.OK, statusCode); }
 * //@Test //done public void idNullCheck() throws SQLException {
 * CertificationDetailsRequest certiRequest = new CertificationDetailsRequest();
 * certiRequest.setSessionId("1214h");
 * certiRequest.setTransactionType("update"); certiRequest.setPageNo(1);
 * certiRequest.setPageSize(2);
 * certiRequest.setCertificationDetailsModel(getModel());
 * 
 * certiRequest.getCertificationDetailsModel().get(1).setId(null);
 * certiRequest.setCertificationDetailsModel(getModel());
 * 
 * when(certificationDetailsFacadeImpl.setCertificationDetails(certiRequest)).
 * thenReturn(failureResponse); ResponseEntity<Object> contResponse =
 * controller.setCertificationDetails(certiRequest); HttpStatus statusCode =
 * contResponse.getStatusCode(); assertNotEquals(HttpStatus.OK, statusCode); }
 * 
 * //@Test //done public void exceptionCheck() { CertificationDetailsRequest
 * certiRequest = new CertificationDetailsRequest();
 * certiRequest.setSessionId("1214h"); certiRequest.setTransactionType("save");
 * certiRequest.setCertificationDetailsModel(getModel()); try {
 * when(certificationDetailsFacadeImpl.setCertificationDetails(certiRequest)).
 * thenThrow(SQLException.class); ResponseEntity<Object> contResponse
 * =controller.setCertificationDetails(certiRequest); HttpStatus statusCode =
 * contResponse.getStatusCode(); assertEquals(HttpStatus.CONFLICT, statusCode);
 * } catch (SQLException e) { // TODO Auto-generated catch block
 * e.printStackTrace(); }
 * 
 * 
 * }
 * 
 * @Test public void modelObjectFieldsNullCheck()throws SQLException {
 * CertificationDetailsRequest certiRequest = new CertificationDetailsRequest();
 * certiRequest.setCertificationDetailsModel(getModel());
 * when(certificationDetailsFacadeImpl.setCertificationDetails(certiRequest)).
 * thenReturn(failureResponse); ResponseEntity<Object> contResponse =
 * controller.setCertificationDetails(certiRequest); HttpStatus statusCode =
 * contResponse.getStatusCode(); assertEquals(HttpStatus.UNPROCESSABLE_ENTITY,
 * statusCode); }
 * 
 * @Test // public void requestObjectForGetNullCheck() throws SQLException {
 * certiRequest =null; //certiRequest.setTransactionType("save");
 * when(certificationDetailsFacadeImpl.getCertificationDetails(certiRequest)).
 * thenReturn(failureResponse); ResponseEntity<Object> contResponse =
 * controller.getCertificationDetails(certiRequest); HttpStatus statusCode =
 * contResponse.getStatusCode(); assertNotEquals(HttpStatus.CONFLICT,
 * statusCode); }
 * 
 * @Test //done public void requestObjectPositiveFOrGetNullCheck() throws
 * SQLException { CertificationDetailsRequest certiRequest = new
 * CertificationDetailsRequest(); certiRequest.setSessionId("1214h");
 * certiRequest.setTransactionType("save");
 * certiRequest.setCertificationDetailsModel(getModel());
 * when(certificationDetailsFacadeImpl.getCertificationDetails(certiRequest)).
 * thenReturn(sucessResponse); ResponseEntity<Object> contResponse =
 * controller.getCertificationDetails(certiRequest); HttpStatus statusCode =
 * contResponse.getStatusCode(); assertEquals(HttpStatus.OK, statusCode); }
 * 
 * @Test //done public void exceptionCheckForGet() { CertificationDetailsRequest
 * certiRequest = new CertificationDetailsRequest();
 * certiRequest.setSessionId("1214h"); certiRequest.setTransactionType("save");
 * certiRequest.setCertificationDetailsModel(getModel());
 * 
 * try {
 * when(certificationDetailsFacadeImpl.getCertificationDetails(certiRequest)).
 * thenThrow(SQLException.class); } catch (SQLException e) { // TODO
 * Auto-generated catch block e.printStackTrace(); } ResponseEntity<Object>
 * contResponse =controller.getCertificationDetails(certiRequest); HttpStatus
 * statusCode = contResponse.getStatusCode(); assertEquals(HttpStatus.CONFLICT,
 * statusCode);
 * 
 * 
 * 
 * }
 * 
 * 
 * }
 */