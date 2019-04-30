package com.ojas.obs.dao;

import static com.ojas.obs.constants.UserConstants.getAll;
import static com.ojas.obs.constants.UserConstants.getById;
import static com.ojas.obs.constants.UserConstants.getCount;
import static com.ojas.obs.constants.UserConstants.saveSkillInfo;
import static com.ojas.obs.constants.UserConstants.updateSkillById;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ojas.obs.model.Skill;
import com.ojas.obs.request.SkillRequest;

@Repository
public class SkillDaoImpl implements SkillDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	Logger logger = Logger.getLogger(this.getClass());

	@Override
	public int saveSkillInfo(SkillRequest skillRequest) throws SQLException {
		// TODO Auto-generated method stub
		logger.debug("executing save method()...");
		List<Skill> listOfSkill = skillRequest.getListOfSkill();
		List<Object[]> inputList = new ArrayList<Object[]>();
		try {
			for (Skill skillList : listOfSkill) {

				Object[] save = { skillList.getSkill_name() };
				inputList.add(save);
			}
			int[] batchUpdate = jdbcTemplate.batchUpdate(saveSkillInfo, inputList);
			if (batchUpdate.length > 0) {
				return 1;
			}
			return 0;
		} finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}
	}

	@Override
	public int updateSkillInfo(SkillRequest skillRequest) throws SQLException {
		logger.debug("enter into the update method()...");

		List<Skill> listSkillInfo = skillRequest.getListOfSkill();
		List<Object[]> inputList = new ArrayList<Object[]>();
		try {
			for (Skill skillDetails : listSkillInfo) {

				Object[] update = { skillDetails.getSkill_name(), skillDetails.getId() };
				inputList.add(update);
			}
			int[] batchUpdate = jdbcTemplate.batchUpdate(updateSkillById, inputList);
			if (batchUpdate.length > 0) {
				return 1;
			}
			return 0;
		} finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}
	}

	@Override
	public List<Skill> showSkillInfo(SkillRequest skillRequest) throws SQLException {
		try {
			return jdbcTemplate.query(getAll, new BeanPropertyRowMapper(Skill.class));
		} finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}
	}

	@Override
	public int getAllCount() throws SQLException {
		try {

			return jdbcTemplate.queryForObject(getCount, Integer.class);
		} finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}
	}

	@Override
	public List<Skill> getById(SkillRequest skillRequest) throws SQLException {
		List<Object[]> inputList = new ArrayList<Object[]>();
		List<Skill> listOfSkill = skillRequest.getListOfSkill();
		Object[] update = null;
		try {
			for (Skill skill : listOfSkill) {

				update = new Object[] { skill.getId() };
				inputList.add(update);
			}
			return jdbcTemplate.query(getById, update, new BeanPropertyRowMapper<>(Skill.class));
		} finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}
	}

}
