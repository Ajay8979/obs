package com.ojas.obs.constants;

public class UserConstants {

	public static final String saveEmployeeSkillInfoStmt = "insert into  obs_employeeSkillDetails(id,skill_id ,level_id,employee_id ,created_by ,flag,created_date ) values(?,?,?,?,?,?,?)";	
	public static final String getAll = "select * from  obs_employeeSkillDetails";
	public static final String getById = "select * from  obs_employeeSkillDetails where id =  ";
	public static final String getCount = "select count(*) from  obs_employeeSkillDetails";
	public static final String updateSkillDetailsById = "update obs_employeeSkillDetails set employee_id = ? ,skill_id=?, level_id =?, update_by=? ,updated_date =? where id = ? ";
}
