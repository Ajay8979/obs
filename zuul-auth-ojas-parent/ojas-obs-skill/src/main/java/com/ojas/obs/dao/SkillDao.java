package com.ojas.obs.dao;

import java.util.List;

import com.ojas.obs.model.Skill;
import com.ojas.obs.request.SkillRequest;

public interface SkillDao {

	public int saveSkillInfo(SkillRequest skillRequest);

	public int updateSkillInfo(SkillRequest skillRequest);

	public List<Skill> showSkillInfo(SkillRequest skillRequest);
	public List<Skill> getById(SkillRequest skillRequest);

	public int getAllCount();

	

}
