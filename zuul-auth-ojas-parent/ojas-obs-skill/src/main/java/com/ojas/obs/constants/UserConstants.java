package com.ojas.obs.constants;

public class UserConstants {

	public static final String saveSkillInfo = "insert into obs_skill(skill_name) values(?)";
	public static final String getAll = "select * from obs_skill";
	public static final String getCount = "select count(*) from obs_skill";
	public static final String updateSkillById = "update obs_skill set skill_name = ? where id = ?";
	public static final String getById = "select * from obs_skill where id = ?";

}
