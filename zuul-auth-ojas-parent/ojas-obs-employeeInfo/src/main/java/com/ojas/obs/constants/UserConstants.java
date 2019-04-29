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

	
	

	// ======EmployeeInfo Queries==============//

	public static final String SAVEEMPINFO = "insert into ojas_employeeinfo  (firstname,middlename,lastname,status,dob,gender,password,employeeId,flag,createdOn,createdBy) values (?,?,?,?,?,?,?,?,?,?,?)";
	public static final String UPDATEEMPINFO = "update ojas_employeeinfo set  firstname =?,middlename=?,lastname=? ,status =?,dob=?,gender=? ,password=?, employeeId=?,updatedOn =? ,updatedBy=? where id =?";

	public static final String DELETEEMPINFO = "update ojas_obs.ojas_employeeinfo set flag = ?,updatedOn = ? where id =?";
	public static final String GETEMPDETAILS = "select * from ojas_employeeinfo where flag = true ";

	public static final String GETEMPCOUNT = "select count(*) from ojas_employeeinfo";
	
	public static final String GETEMPBYID ="select * from ojas_employeeinfo where flag = true and id= ";
			
	public static final String GETEMPBYEMPID ="select * from ojas_employeeinfo where flag = true and employeeId= ";
	
	public static final String GETROLEBYEMPID ="SELECT a.role_name,a.role_id, e.employeeId,e.firstname,e.middlename,e.lastname FROM ojas_employeeinfo e, user_role u , app_role a where e.employeeId =u.employeeId and u.role_id =a.role_id and e.employeeId = ? ";
	
	
}
