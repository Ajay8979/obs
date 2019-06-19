package com.ojas.obs.constants;

public class SubBusinessUnitConstants {
	
	public static final String SUBBUSINESSUNIT = "/subbusinessunit";
	public static final String SET = "/set";
	public static final String GET = "/get";
	public static final String GETALL = "getAll";
	public static final String GETBYID = "getById";
	public static final String SAVE = "save";
	public static final String UPDATE = "update";
	public static final String FAILED = "falied";
	public static final String SUCCESS = "success";
	
	public static final String SAVEBUSINESSUNIT = "Insert into obs_subbusinessunit(name,businessUnitId,CostCenterId) VALUES (?,?,?)";
	public static final String UPDATEBUSINESSUNITID = "update obs_subbusinessunit set name = ?, businessUnitId = ?, CostCenterId = ? where id = ? ";
	//public static final String GETALLSUBBUSINESSUNITS = "select sbu.*, bu.businessUnitName from obs_subbusinessunit sbu, obs_businessunit bu WHERE bu.id = sbu.businessUnitId";
	public static final String GETALLSUBBUSINESSUNITS = "select * from obs_subbusinessunit";
	public static final String GETBYIDBUSINESSUNITS = "select * from obs_subbusinessunit where id = ?";
	

}