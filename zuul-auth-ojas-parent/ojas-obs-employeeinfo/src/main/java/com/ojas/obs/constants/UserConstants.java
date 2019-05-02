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

	public static final String SAVEEMPINFO = "insert into obs_employeeinfo  (firstname,middlename,lastname,status,dob,gender,title,employeeId,flag,createdOn,createdBy) values (?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String SAVEEMPLOGININFO =  "insert into obs_employee_login(employeeId,password,createdOn,createdBy) values(?,?,?,?)";
	
	public static final String SAVEEMPROLE =  "insert into user_role(employee_id,role_id) values(?,?)";
			
	public static final String DELETEEMPROLE ="delete from user_role where employee_id =?";
		
	public static final String UPDATEEMPINFO = "update obs_employeeinfo set  firstname =?,middlename=?,lastname=? ,status =?,dob=?,gender=?, title=?, employeeId=?,updatedOn =? ,updatedBy=? where id =?";

	public static final String DELETEEMPINFO = "update ojas_obs.obs_employeeinfo set flag = ?,updatedOn = ? where employeeId =?";
	public static final String GETEMPDETAILS = "select * from obs_employeeinfo where flag = true ";

	public static final String GETEMPCOUNT = "select count(*) from obs_employeeinfo";
	
	public static final String GETEMPBYID ="select firstname,middlename,lastname,status,dob,gender,title,employeeId,flag,createdOn,createdBy from obs_employeeinfo where flag = '1' and id= ";
	
	public static final String ROLEEMPBYID ="select role_id from user_role where  employee_id= ";
			
	public static final String GETEMPBYEMPID ="select * from ojas_employeeinfo where flag = true and employeeId= ";
	
	public static final String GETROLEBYEMPID ="SELECT a.role_name,a.role_id, e.employeeId,e.firstname,e.middlename,e.lastname FROM obs_employeeinfo e, user_role u , app_role a where e.employeeId =u.employeeId and u.role_id =a.role_id and e.employeeId = ? ";
	
	
}
