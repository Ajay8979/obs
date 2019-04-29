package com.obs.businessunit.constants;

public class UserConstants {
	public static final String FAILED = "Failed";
	public static final String SUCCESS = "Success";
	public static final String BUSINESSUNIT = "/businessUnit";
	public static final String SET = "/set";
	public static final String GET = "/get";
	public static final String SAVE = "save";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";
	public static final String GETALL = "getAll";
	public static final String GETBYID = "getById";
	
	public static final String INSERT_BUSINESSUNIT = "insert into obs_businessunit(businessUnitName,costCenterId) values(?,?)";
	public static final String UPDATE_BUSINESSUNIT = "update obs_businessunit set businessUnitName=?, costCenterId=? where id =?";
	public static final String DELETE_BUSINESSUNIT = "delete from obs_businessunit where id = ?";
	public static final String SELECT_BUSINESSUNIT = "select * from obs_businessunit";
	public static final String GETBYIDSTMT = "select * from obs_businessunit where id=?";
}
