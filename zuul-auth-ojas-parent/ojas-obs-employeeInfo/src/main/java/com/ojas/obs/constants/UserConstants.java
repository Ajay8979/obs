package com.ojas.obs.constants;

/**
 * 
 * @author sdileep
 *
 */
public class UserConstants {

	public static final String SAVE = "save";

	public static final String UPDATE = "update";

	public static final String DELETE = "delete";

	public static final String FAILED = "failed";

	
	//==============transactionType==========
	
	
	
	public static final String SAVED = "saved";

	public static final String UPDATED = "updated";

	public static final String DELETED = "deleted";
	
	

	// ======EmployeeInfo Queries==============//

	public static final String SAVEEMPINFO = "insert into ojas_employeeinfo  (firstname,middlename,lastname,status,dob,gender,password,employee_num,flag,created_on,created_by) values (?,?,?,?,?,?,?,?,?,?,?)";
	public static final String UPDATEEMPINFO = "update ojas_employeeinfo set  firstname =?,middlename=?,lastname=? ,status =?,dob=?,gender=? ,password=?, employee_num=?,flag=?,updated_on =? ,updated_by=? where id =?";

	public static final String DELETEEMPINFO = "update ojas_obs.ojas_employeeinfo set flag = ?,updated_on = ? where id =?";
	public static final String GETEMPDETAILS = "select * from ojas_employeeinfo ";

	public static final String GETEMPCOUNT = "select count(*) from ojas_employeeinfo";
	

}
