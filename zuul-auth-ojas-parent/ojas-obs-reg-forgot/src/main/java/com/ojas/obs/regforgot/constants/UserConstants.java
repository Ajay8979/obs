package com.ojas.obs.regforgot.constants;

/**
 * 
 * @author sdileep
 *
 */
public class UserConstants {

	public static final String SAVE = "save";
	public static final String UPDATE = "update";
	public static final String SENDMAIL = "sendMail";
	
	public static final String FAILED = "failed";

	
	

	// ======EmployeeInfo Queries==============//

	public static final String SAVEEMPINFO = "insert into obs_employeeinfo  (firstname,middlename,lastname,status,dob,gender,title,employeeId,flag,createdOn,createdBy) values (?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String SAVEEMPLOGININFO =  "insert into obs_employee_login(employeeId,password,createdOn,createdBy) values(?,?,?,?)";
	
	public static final String SAVEEMPROLE =  "insert into user_role(employee_id,role_id) values(?,?)";
			
}
