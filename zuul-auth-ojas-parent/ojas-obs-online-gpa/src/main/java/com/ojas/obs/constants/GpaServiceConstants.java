package com.ojas.obs.constants;
/**
 * 
 * @author pnaveen
 *
 */
public class GpaServiceConstants {

	public static final String FAILED = "failed";
	
	//==================================Request==========================================================
	/*
	 * public static final String GPA = "/obs/service/gpa"; */	
	public static final String SET = "/service/set";
	public static final String GET = "/service/get";

	
	//==================================TransactionTypes======================================================
	public static final String SAVE = "save";
	public static final String GETALLRECORDS = "getGpaDetails";
	//public static final String DELETE = "delete";
	public static final String UPDATE = "update";


	
//	===================================DatabaseQuries========================================================
	
	public static final String INSERTGPA = "INSERT INTO obs_gpaplan(gpaplantype,gpapremium,totalpremium)"
			+ "VALUES(?,?,?)";
	//public static final String DELETEGPA = "update obs_gpaplan set  updated_on = ? where gpaplanid = ?";
	public static final String GETGPAPLAN = "select * from obs_gpaplan";
	public static final String GETGPACOUNT = "select count(*) from obs_gpaplan";
	public static final String UPDATEGPAPLAN = "update obs_gpaplan set  gpaplantype =?,gpapremium=?,totalpremium=? where gpaplanid =?";

}

