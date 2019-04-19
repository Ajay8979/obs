package com.ojas.obs.conistants;

public class RestClientConistants {
	public static final String  URL ="http://localhost:8080";
	public static final String GETPSSPORTSERVICE =  "http://localhost:8080/ojas-obs-passport-service/obs/passportService/get";
	public static final String SETPSSPORTSERVICE =  "http://localhost:8080/ojas-obs-passport-service/obs/passportService/set";
	public static final String X_CORRELATION_ID = "X-Correlation-ID";
	public static final String BLANK = "";
	public static final String DASH = "-";
	
	
	//==================================== Designation ==================================================
		public static final String GETDESIGNATION = "http://localhost:8080/ojas-obs-designation-service/obs/designation/service/get";
		public static final String SETDESIGNATION = "http://localhost:8080/ojas-obs-designation-service/obs/designation/service/set";


		//==================================== BusinessUnit ==================================================
		public static final String GETBUSINESSUNIT = "http://localhost:8080/ojas-obs-businessunit-service/obs/businessUnit/service/get";
		public static final String SETBUSINESSUNIT = "http://localhost:8080/ojas-obs-businessunit-service/obs/businessUnit/service/set";


		//==================================== states ==================================================
		public static final String GETSTATES = "http://localhost:8080/ojas-obs-states-service/obs/states/service/get";
		public static final String SETSTATES = "http://localhost:8080/ojas-obs-states-service/obs/states/service/set";

	
	
	//----------------CostCenter RestClientConstants--------------------------
	
	
	public static final String GETCOSTCENTER = "http://localhost:8080/cost-center/obs/costcenter/get";
	public static final String SETCOSTCENTER = "http://localhost:8080/cost-center/obs/costcenter/set";
	
	//...................................Shantilata sub business unit url.......................................................

	//public static final String  URL2 ="localhost:8080";
	public static final String GETSUBUNIT = "http://localhost:8080/ojas-obs-subbusinessunit-service/obs/service/subbusinessunit/get";
	public static final String SETSUBUNIT = "http://localhost:8080/ojas-obs-subbusinessunit-service/obs/service/subbusinessunit/set";
	

	//----------------GPA RestClientConstants--------------------------
	
	public static final String GETGPA = "http://localhost:8080/ojas-obs-online-gpa-service/obs/service/gpa/get";
	public static final String SETGPA = "http://localhost:8080/ojas-obs-online-gpa-service/obs/service/gpa/set";
	
	//----------------Employee Education RestClientConstants--------------------------
	public static final String EDUCATIONGET = "http://localhost:8080/ojas-obs-educationtype/obs/service/education/GetEductionDetails";
	public static final String EDUCATIONSET = "http://localhost:8080/ojas-obs-educationtype/obs/service/education/SaveEmployeeEducation";


	
	//----------------ROLEMANAGEMENT  RestClientConstants--------------------------
	public static final String ROLEMANAGEMENTGET = "http://localhost:8080/ojas-obs-online-rolemanagement-service/obs/GetRoleDetails/service/get";
	public static final String ROLEMANAGEMENTSET = "http://localhost:8080/ojas-obs-online-rolemanagement-service/obs/service/role/SaveRole";




	public static final String SET_EMPLOYEE_STATUS = "http://localhost:8080/ojas-obs-employeestatus-service/obs/emplyoeestatus/set";
	public static final String GET_EMPLOYEE_STATUS = "http://localhost:8020/ojas-obs-employeestatus-service/obs/emplyoeestatus/get";
	



	//-----------------------------------------Separation type End points-------------------------------------------------------
	public static final String SEPARATION_TYPE_SET = "http://localhost:8080/ojas-obs-online-separationType-service/obs/separationType/set";
	public static final String SEPARATION_TYPE_GET = "http://localhost:8080/ojas-obs-online-separationType-service/obs/separationType/get";

	

}