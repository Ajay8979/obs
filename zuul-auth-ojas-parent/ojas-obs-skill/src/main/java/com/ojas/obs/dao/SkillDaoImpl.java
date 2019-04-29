package com.ojas.obs.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import static com.ojas.obs.constants.UserConstants.saveSkillInfo;
import static com.ojas.obs.constants.UserConstants.updateSkillById;
import static com.ojas.obs.constants.UserConstants.getAll;
import static com.ojas.obs.constants.UserConstants.getCount;
import static com.ojas.obs.constants.UserConstants.getById;
import com.ojas.obs.model.Skill;
import com.ojas.obs.request.SkillRequest;

@Repository
public class SkillDaoImpl implements SkillDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int saveSkillInfo(SkillRequest skillRequest) {
		// TODO Auto-generated method stub
		
		List<Skill> listOfSkill = skillRequest.getListOfSkill();
		List<Object[]> inputList = new ArrayList<Object[]>();
		for(Skill skillList : listOfSkill) {
 
			Object[] save = {skillList.getId(),skillList.getSkill_id(),skillList.getSkill_name()};
			inputList.add(save);
	}
		int[] batchUpdate = jdbcTemplate.batchUpdate(saveSkillInfo, inputList );
		if(batchUpdate.length > 0) {
			return 1;
		}
		return 0;
	}

	@Override
	public int updateSkillInfo(SkillRequest skillRequest) {
		List<Skill> listSkillInfo = skillRequest.getListOfSkill();
		List<Object[]> inputList = new ArrayList<Object[]>();
		for(Skill skillDetails : listSkillInfo) {
			
		
				Object[] update = { 
						skillDetails.getSkill_id(),skillDetails.getSkill_name(),skillDetails.getId()};
	inputList.add(update);
		}
		int[] batchUpdate = jdbcTemplate.batchUpdate(updateSkillById, inputList );
		if(batchUpdate.length>0) {
			return 1;
		}
		return 0;
	}

	@Override
	public List<Skill> showSkillInfo(SkillRequest skillRequest) {

		return jdbcTemplate.query(getAll, new BeanPropertyRowMapper(Skill.class));
	}

	@Override
	public int getAllCount() {

		
		return jdbcTemplate.queryForObject(getCount,Integer.class);
	}

	@Override
	public List<Skill> getById(SkillRequest skillRequest) {
		List<Object[]> inputList = new ArrayList<Object[]>();
		List<Skill> listOfSkill = skillRequest.getListOfSkill();
		Object[] update=null;
		for (Skill skill : listOfSkill) {

		 update = new Object[] { skill.getId() };
			inputList.add(update);
		}
		return jdbcTemplate.query(getById,update,
				new BeanPropertyRowMapper<>(Skill.class));
	}

}
