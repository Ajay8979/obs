package com.ojas.obs.constants;

public class UserConstants {

	public static final String saveEmployeeSkillInfoStmt = "insert into  obs_employeeskilldetails(skill_id ,level_id,employee_id ,created_by ,flag,created_date ) values(?,?,?,?,?,?)";	
	public static final String getAll = "select * from  obs_employeeskilldetails";
	public static final String getById = "select * from  obs_employeeskilldetails where id =  ";
	public static final String getCount = "select count(*) from  obs_employeeskilldetails";
	public static final String updateSkillDetailsById = "update obs_employeeskilldetails set employee_id = ? ,skill_id=?, level_id =?, update_by=? ,updated_date =? where id = ? ";
}
