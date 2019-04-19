
package com.ojas.obs.constants;

public class UserConstants {
	public static final String FAILED = "failed";

	// ...........................UserConstants for Passport.................//

	public static final String REQUESTOBJECTNULL = "reqest object is null";
	public static final String PASSPOROTBJECTNULL = "passport object is null";
	public static final String SESSIONIDNULL = "sessionid is null";
	public static final String TRANSACTIONTYPENULL = "transactiontype is null";

	public static final String SAVE = "save";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";
	public static final String GETALL = "getAll";

	public static final String INSERTPASSPORTSTMT = "insert into ojas_obs.obs_passport (obs_passport.center,obs_passport.deletestatus) values ( ?,?)";
	public static final String GETTOTALSTMT = "select * from ojas_obs.obs_passport where deletestatus = 'false'";
	public static final String DELETESTMT = "update ojas_obs.obs_passport set obs_passport.deletestatus = ? where obs_passport.id = ?";
	public static final String UPDATESTMT = "update ojas_obs.obs_passport set obs_passport.center = ? , obs_passport.deletestatus = ? where obs_passport.id = ?";

	// ....................UserConstants for subBusinessUnit..............//

	public static final String SUBBUSINESSUNITREQUESTOBJECTNULL = "reqest object is null";
	public static final String SUBBUSINESSOBJECTNULL = "subbusinessunit object is null";
	public static final String SUBBUSINESSUNITSESSIONIDNULL = "sessionid is null";
	public static final String SUBBUSINESSUNITTRANSACTIONTYPENULL = "transactiontype is null";

	public static final String INSERTSUBBUSINESSUNITDATA = "Insert into obs_subbusinessunitt(NAME,BUSINESSUNITID) VALUES (?,?)";
	public static final String DELETESUBBUSINESSUNITBYID = "delete from obs_subbusinessunitt where id = ?";
	public static final String UPDATESUBBUSINESSUNIT = "update obs_subbusinessunitt set name=? where id = ? ";
	public static final String GETTOTALSUBBUSINESSUNIT = "select * from obs_subbusinessunitt";

	// ============================================Designation=======================================================
	public static final String INSERT_DESIGNATION = "insert into obs_designation values(?,?)";
	public static final String UPDATE_DESIGNATION = "update obs_designation set designation= ? where id= ?";
	public static final String DELETE_DESIGNATION = "delete from obs_designation where id = ?";
	public static final String SELECT_DESIGNATION = "select * from obs_designation";
	public static final String DESIGNATIONCOUNT = "select count(*) from obs_designation";

	// ============================================EmployeeEducation=======================================================
	public static final String INSERT = "insert into education_table(degree,pg,degree_stream,pg_stream) values(?,?,?,?)";
	public static final String UPDATEEDUCATIONDATA = "update education_table set degree=?,pg =?,degree_stream=?,pg_stream=? where id =?";
	public static final String GETEDUCATION = "select * from education_table";
	public static final String DELETEEDUCATION = "delete from education_table where id = ?";
	public static final String TOTALRECORDS = "select count(*) from education_table";

	// ============================================RoleManagement=======================================================
	public static final String INSERTROLE = "insert into obs_role(rolename) values(?)";
	public static final String UPDATEROLE = "update obs_role set rolename=? where id =?";
	public static final String GETROLE = "select * from obs_role";
	public static final String GETROLECOUNT = "select count(*) from obs_role";
	public static final String DELETEROLE = "delete from obs_role where id = ?";

	// ============================================Businessunit=======================================================
	public static final String INSERT_BUSINESSUNIT = "insert into obs_businessunit values(?,?,?)";
	public static final String UPDATE_BUSINESSUNIT = "update obs_businessunit set businessUnitName=?, costCenterId=? where id =?";
	public static final String DELETE_BUSINESSUNIT = "delete from obs_businessunit where id = ?";
	public static final String SELECT_BUSINESSUNIT = "select * from obs_businessunit";
	public static final String BUSINESSUNITCOUNT = "select count(*) from obs_businessunit";

	// ============================================States=======================================================
	public static final String INSERT_STATES = "insert into obs_states values(?,?)";
	public static final String UPDATE_STATES = "update obs_states set stateName= ? where id= ?";
	public static final String DELETE_STATES = "delete from obs_states where id = ?";
	public static final String SELECT_STATES = "select * from obs_states";
	public static final String STATESCOUNT = "select count(*) from obs_states";

	// ----------------CostCenter----------------------------------------------------//
	public static final String INSERTCOSTCENTER = "insert into obs_costcenter(id,costcentercode) values (?,?)";
	public static final String UPDATECOSTCENTER = "update obs_costcenter set costcentercode= ? where id= ?";
	public static final String DELETECOSTCENTER = "delete from obs_costcenter where id = ? ";
	public static final String GETCOSTCENTER = "select * from obs_costcenter";
	public static final String GETCOSTCENTERCOUNT = "select count(*) from obs_costcenter";

	// ====================GPA====================
	public static final String INSERTGPA = "INSERT INTO gpaplan(gpaplanid,gpaplantype,gpapremium,totalpremium)"
			+ "VALUES(?,?,?,?)";
	public static final String GETGPAPLAN = "select * from gpaplan";
	public static final String DELETEGPA = "delete from gpaplan where gpaplanid = ?";
	public static final String GETGPACOUNT = "select count(*) from gpaplan";
	public static final String UPDATEGPAPLAN = "update gpaplan set  gpaplantype =?,gpapremium=?,totalpremium=? where gpaplanid =?";

	// ....................EmployeeStatus..............//
	public static final String INSERTEMPLOYEESTMT = "INSERT INTO OBS_EMPSTATUS(OBS_EMPSTATUS.STATUS,OBS_EMPSTATUS.DELETED) VALUES (?,?)";
	public static final String GETEMPLOYEETOTALSTMT = "SELECT * FROM OJAS_OBS.OBS_EMPSTATUS WHERE DELETED= 'false'";
	public static final String DELETEEMPLOYEESTMT = "UPDATE OJAS_OBS.OBS_EMPSTATUS SET OBS_EMPSTATUS.DELETED = ? WHERE OBS_EMPSTATUS.ID = ?";
	public static final String UPDATEEMPLOYEESTMT = "UPDATE OJAS_OBS.OBS_EMPSTATUS SET OBS_EMPSTATUS.STATUS= ?,OBS_EMPSTATUS.DELETED = ? WHERE OBS_EMPSTATUS.ID = ?";

	public static final String EMPLOYEE_REQUEST_NULL = "reqest object is null";
	public static final String EMPLOYEE_OBJECT_NULL = "Employee object is null";
	public static final String SESSION_ID_NULL = "sessionid is null";
	public static final String TRANSACTION_TYPE_NULL = "transactiontype is null";
	public static final String ID_IS_NULL = "id is null";
	public static final String EMPLOYEE_STATUS_NULL = "Employee Status is null";

}
