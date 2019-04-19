package com.ojas.obs.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ojas.obs.dao.RoleManagementDao;
import com.ojas.obs.model.RoleManagement;
import com.ojas.obs.request.RoleManagementRequest;

/**
 * 
 * @author asuneel
 *
 */
@Repository
public class RoleManagementDaoImpl implements RoleManagementDao {

	public static final String SAVEROLE = "insert into obs_RoleManagement(rolename) values(?)";
	public static final String UPDATEROLE = "update obs_RoleManagement set rolename = ? where id = ?";
//	public static final String DELETEROLE = "delete from obs_RoleManagement where id = ?";
	public static final String GETALLRECORDS = "select * from obs_RoleManagement";
	public static final String TOTALCOUNT = "select count(*) from obs_RoleManagement";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean saveRoleManagement(RoleManagementRequest roleManagementRequest) {
		int[] save;
		List<RoleManagement> roleManagementList = roleManagementRequest.getRoleManagement();
		List<Object[]> list = new ArrayList<>();
		for (RoleManagement roleManagementItem : roleManagementList) {
			Object[] role = new Object[] {roleManagementItem.getRoleName()};
			list.add(role);

		}
			save = jdbcTemplate.batchUpdate(SAVEROLE, list);
		if (save.length > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateRoleManagement(RoleManagementRequest roleManagementRequest) {
		int[] update;
		List<RoleManagement> roleManagementList = roleManagementRequest.getRoleManagement();
		List<Object[]> list = new ArrayList<>();
		
		for (RoleManagement roleManagementItem : roleManagementList) {
			Object[] role = new Object[] {roleManagementItem.getRoleName(), roleManagementItem.getId()};
			list.add(role);
		}
		update = jdbcTemplate.batchUpdate(UPDATEROLE, list);
		

		if (update.length > 0) {
			return true;
		}
		return false;
	}

	/*
	 * @Override public boolean deleteRoleManagement(RoleManagementRequest
	 * roleManagementRequest) { int[] delete; List<RoleManagement>
	 * roleManagementList = roleManagementRequest.getRoleManagement();
	 * List<Object[]> list = new ArrayList<>(); for(RoleManagement
	 * roleManagementItem : roleManagementList) {
	 * 
	 * Object[] role = new Object[] {roleManagementItem.getId()}; list.add(role); }
	 * delete = jdbcTemplate.batchUpdate(DELETEROLE, list); if (delete.length > 0) {
	 * return true; } return false; }
	 */

	@Override
	public List<RoleManagement> getAllRollManagements() throws SQLException{
		return jdbcTemplate.query(GETALLRECORDS, new BeanPropertyRowMapper<RoleManagement>(RoleManagement.class));
	}

	@Override
	public int getAllRollManagementsCount() {

		return jdbcTemplate.queryForObject(TOTALCOUNT, Integer.class);
	}

}
