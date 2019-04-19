package com.obs.employeeCertificationDetails.constants;

public class Constants {
	public static final String SAVE="save";
	public static final String UPDATE="update";
	public static final String DELETE="delete";
	public static final String GET="get";
	public static final String GETBYID="getbyid";
	public  static final String GET_COUNT="get_count";
	public static final String FAILED="failed";
	public static final String SUCCESS="success";
	public static final String REQUEST_NULL="request Object is null";
	public static final String MODEL_NULL="model Object is null";
	public static final String TRANSACTIONTYPE_NULL="transaction type is null";
	public static final String SESSIONID_NULL="session id is null";
	public static final String ID_NULL="id is not provided";
	public static final String REQUEST_DATA_MISSING="some data is missing";
	
	//url patterns
	/* public static final String CERTIFICATION="/obs/certifications"; */ ///service/employeeCertifications/setEmployeeCertifications
	public static final String SETCERTIFICATION="/setCertificationDetails";
	public static final String GETCERTIFICATION="/getCertificationDetails";
    
	public static final String INSERT="insert into obs_certificationdetails( certification_name, Issued_by, Date_of_issue, employee_id,created_by,flag,created_date) values(?,?,?,?,?,?,now())";
	public static final String UPDATEQ="update obs_CertificationDetails set certification_name=?, Issued_by=?, Date_of_issue=? ,created_by=?, updated_by=?, created_date=now(),updated_date=now() where id = ?";
	public static final String DELETECertificationDetails = "update obs_CertificationDetails set flag = false ,updated_by=? where id = ?";
	public static final String GETCertificationDetailsCOUNT = "select count(*) from obs_CertificationDetails where flag= true";
	public static final String GETCertificationDetails="Select * from obs_CertificationDetails where flag=1";
	public static final String GETCertificationDetailById="Select * from obs_CertificationDetails where flag = 1 and id = ?";
}
