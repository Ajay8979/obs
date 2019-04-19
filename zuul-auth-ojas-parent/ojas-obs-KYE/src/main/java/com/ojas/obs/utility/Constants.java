package com.ojas.obs.utility;

public class Constants {
	
	//============================================URL constants=========================================================================
	public static final String SET = "/set";
	public static final String GET = "/get";	
	public static final String KYE = "/kye";	

	//=============================================TransactionTypes======================================================================
	public static final String SAVE = "save";	
	public static final String UPDATE = "update";	
	public static final String DELETE = "delete";	
	public static final String GETALL = "getAll";	
	
	//=============================================sql quries ==========================================================================
	public static final String SAVE_KYE = "Insert into obs_kye(KYE_Type ,uan ,KYE_address,passport_no,Passport_date_of_Issue,Passport_date_of_expiry,place_of_issue ,Passport_address ,employee_Id, flag, created_by, created_date) values(?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String UPDATE_KYE = "Update obs_kye set KYE_Type =? ,uan=?,KYE_address=?,passport_no=?,Passport_date_of_Issue=?,Passport_date_of_expiry = ?,place_of_issue = ?,Passport_address=?, flag = ?,updated_by = ?, updated_date = ? where id = ?";
	public static final String DELETE_KYE = "Update obs_kye set flag = ?,updated_by = ?, updated_date = ? where id = ?";
	public static final String GETALL_KYE = "Select * from obs_kye where flag = false";
	public static final String GETALL_KYE_COUNT = "Select count(*) from obs_kye ";
	
}