package com.ojas.obs.emp_edu.utility;

public class Constants {

	// =================================query constants===========================
	public static final String INSERT_STMT = "insert into ojas_obs.obs_education_details (employeeId,qualification,year_of_passing,percentage_marks,institution_name,flag,createdBy,updatedBy,createdDate,updatedDate) values (?,?,?,?,?,?,?,?,now(),now())";
	public static final String DELETE_STMT = "update ojas_obs.obs_education_details set flag = 0 where id = ?;";
	public static final String UPDATE_STMT = "update ojas_obs.obs_education_details set employeeId = ?,qualification =?,year_of_passing =? ,percentage_marks= ?,institution_name = ?,flag = ? ,createdBy = ?, updatedBy = ?,createdDate = now(), updatedDate = now() where id = ?";
	public static final String GETALL_STMT = "select * from ojas_obs.obs_education_details where flag = 1";
	public static final String GETBYID_STMT = "select * from ojas_obs.obs_education_details where flag = 1 and id = ?;";
	// =================================url constants===========================
	/*
	 * public static final String EMP_EDU_DETAILS = "/obs/employeeEducationDetails";
	 */	public static final String SET = "/set";
	public static final String GET = "/get";
	// =================================user constants===========================
	public static final String REQUEST_NULL = "Request Object is Empty";
	public static final String OBJECT_NULL = "EducationDetails Object is Empty";
	public static final String ID_NULL = "id is empty please provide the id";
	public static final String PAGE_NO_NULL = "page no is not defined";
	public static final String PAGE_SIZE_NULL = "page size is not defined";
	public static final String LIST_OBTAINED = "you got a list";
	// =================================Transaction constants===========================
	public static final String TRANSACTIONTYPE_NULL = "transaction types is not defined";
	public static final String VALID_TRANSACTIONTYPE = "transactiontype is not valid";
	public static final String SAVE = "save";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";
	public static final String GETALL = "getAll";
	public static final String GETBYID = "getById";
	// =================================Exception constants===========================
	public static final String SQL_EXCEPTION = "sql exception occured";
	public static final String EXCEPTION = "exception occured";
}
