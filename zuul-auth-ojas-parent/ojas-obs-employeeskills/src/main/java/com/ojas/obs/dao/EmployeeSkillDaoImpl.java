
package com.ojas.obs.dao;

import static com.ojas.obs.constants.UserConstants.getAll;
import static com.ojas.obs.constants.UserConstants.getCount;
import static com.ojas.obs.constants.UserConstants.saveEmployeeSkillInfoStmt;
import static com.ojas.obs.constants.UserConstants.updateSkillDetailsById;
import static com.ojas.obs.constants.UserConstants.getById;
import static com.ojas.obs.constants.UserConstants.getByEmpId;


import java.util.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ojas.obs.model.EmployeeSkillInfo;
import com.ojas.obs.model.EmployeeSkillInfoRequest;


@Repository
public class EmployeeSkillDaoImpl implements EmployeeSkillDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	Logger logger = Logger.getLogger(this.getClass());

	// saving the data into database

	@Override
	public int saveEmployeeSkillInfo(EmployeeSkillInfoRequest employeeSkillInfoRequest) throws SQLException {
		List<EmployeeSkillInfo> listEmployeeSkillInfo = employeeSkillInfoRequest.getSkillInfoModel();
		List<Object[]> inputList = new ArrayList<Object[]>();
		for(EmployeeSkillInfo skillDetails : listEmployeeSkillInfo) {
 
			logger.info("@@@@@Inside the save method()....");
			Object[] save = {skillDetails.getSkill_id(),
				skillDetails.getLevel_id(),
				skillDetails.getEmployee_id(),
				skillDetails.getCreated_by(),
				false,
				new Timestamp(new Date().getTime()) };
			inputList.add(save);
	}
		//try {
		int[] batchUpdate = jdbcTemplate.batchUpdate(saveEmployeeSkillInfoStmt, inputList );
		if(batchUpdate.length > 0) {
			return 1;
		}
		return 0;
//		} finally {
//			jdbcTemplate.getDataSource().getConnection().close();
//		}
	}
	
		
	

	// updating the data into database

	@Override
	public int updateEmployeeSkillInfo(EmployeeSkillInfoRequest employeeSkillInfoRequest) throws SQLException {

		List<EmployeeSkillInfo> listEmployeeSkillInfo = employeeSkillInfoRequest.getSkillInfoModel();
		List<Object[]> inputList = new ArrayList<Object[]>();
		for(EmployeeSkillInfo skillDetails : listEmployeeSkillInfo) {
			logger.debug("@@@@@Inside the update method()....");
		
				Object[] update = { 
						skillDetails.getEmployee_id(),
						skillDetails.getSkill_id(),
						skillDetails.getLevel_id(),
				skillDetails.getUpdate_by(),
				new Timestamp(new Date().getTime()),
				skillDetails.getId()};
	inputList.add(update);
		}
		//try {
		int[] batchUpdate = jdbcTemplate.batchUpdate(updateSkillDetailsById, inputList );
		if(batchUpdate.length>0) {
			return 1;
		}
		return 0;
		//} finally {
			//jdbcTemplate.getDataSource().getConnection().close();
		//} 
	}

	// retrieving the list of data

	@Override
	public List<EmployeeSkillInfo> showEmployeeSkillInfo(EmployeeSkillInfoRequest employeeSkillInfoRequest)
			throws SQLException {
		logger.debug("@@@@@Inside the show  method()....");
		
		/*List<EmployeeSkillInfo> skillList = employeeSkillInfoRequest.getSkillInfoModel();
		for (EmployeeSkillInfo listSkill : skillList) {
			StringBuffer buffer = new StringBuffer();
			buffer.append(getAll);
			if (listSkill.getId() != 0) {
				buffer.append(" and id = " + listSkill.getId());
			}
			return jdbcTemplate.query(buffer.toString(), new BeanPropertyRowMapper<>(EmployeeSkillInfo.class));
		}
		return null;*/

		//try {
		return jdbcTemplate.query(getAll, new BeanPropertyRowMapper(EmployeeSkillInfo.class));
	
	/*}finally {
		jdbcTemplate.getDataSource().getConnection().close();
	}*/
}

	@Override
	
	public int getAllCount() throws SQLException {
		logger.debug("@@@@@Inside the getAll method()....");
		
		//try {
		return jdbcTemplate.queryForObject(getCount, Integer.class);
		/*} finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}*/
	}

	/*@Override
	public List<EmployeeSkillInfo> getCountPerPage(List<EmployeeSkillInfo> list, int pageSize, int pageNo)
			throws SQLException {

		List<EmployeeSkillInfo> getAllFilteredList = new ArrayList<>();
		if (list != null && !list.isEmpty()) {
			pageSize = pageSize > 0 ? pageSize : pageSize * -1;
			pageNo = pageNo > 0 ? pageNo : pageNo == 0 ? 1 : pageNo * -1;
			if (pageSize != 0) {
				int endIndex = pageNo * pageSize;
				int startIndex = endIndex - pageSize;
				endIndex = endIndex < list.size() ? endIndex : list.size();
				startIndex = startIndex < list.size() ? startIndex : 0;
				getAllFilteredList = list.subList(startIndex, endIndex);
			}
		}
		return getAllFilteredList;
	}*/




	@Override
	public List<EmployeeSkillInfo> getById(Integer id) throws SQLException {
		logger.debug("@@@@@Inside the getById method()....");
		
		//try {
		List<EmployeeSkillInfo> list = jdbcTemplate.query(getById + id, new BeanPropertyRowMapper<>(EmployeeSkillInfo.class));
		return list;
		/*}finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}*/
		//List<EmployeeSkillInfo> employeeExperienceDetailsList = jdbcTemplate.query("getById"), params, new ExperienceRowMappers());
		
		
	}

    @Override
	public List<EmployeeSkillInfo> getByEmpId(String empId) throws SQLException {
    	logger.debug("@@@@@Inside the empId method()....");
		
    	//try {
    		List<EmployeeSkillInfo> list = jdbcTemplate.query(getByEmpId + empId, new BeanPropertyRowMapper<>(EmployeeSkillInfo.class));
    		return list;
    		/*}finally {
    			jdbcTemplate.getDataSource().getConnection().close();
    		}*/
    		//List<EmployeeSkillInfo> employeeExperienceDetailsList = jdbcTemplate.query("getById"), params, new ExperienceRowMappers());
    		
	}

}
