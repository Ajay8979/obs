package com.ojas.obs.constants;

public class UserConstants {

	public static final String GET = "/get";

	public static final String SET = "/set";
	public static final String GETAll = "getall";

	public static final String GETBYID = "getbyid";
	
	public static final String INSERTEMPLOYEECONTACTINFOSTMT = "INSERT INTO obs_employeecontactinfo(email,personal_mobileNo,alternate_mobileNo,current_Address_line1,current_Address_line2, current_City,current_State,current_Pin,permanent_Address_Line_1,emp_Id,created_By,created_date,flag) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String SHOWCONTACTINFOSTMT = "SELECT * FROM obs_employeecontactinfo WHERE id=?";
	public static final String SHOWCONTACTINFOSTMTEMPID = "SELECT * FROM obs_employeecontactinfo WHERE emp_Id=?";
	public static final String UPDATESTMT = "UPDATE obs_employeecontactinfo set email = ? ,personal_mobileNo = ?,alternate_mobileNo = ?,current_Address_line1 = ?,current_Address_line2 = ?, current_City = ?,current_State = ?,current_Pin = ?,permanent_Address_Line_1 = ?,updated_By = ?,updated_date = ? WHERE id = ? ";
	public static final String DELETECONTACT = "UPDATE obs_employeecontactinfo set flag = ?,updated_date = ?,updated_By = ? where id = ?";
	public static final String TOTALRECORDS = "select * from obs_employeecontactinfo where flag=true";
	
	
	
}
