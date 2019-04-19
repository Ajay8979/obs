package com.ojas.obs.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ojas.obs.dao.SubBusinessUnitDao;
import com.ojas.obs.model.SubBusinessUnit;
import com.ojas.obs.request.SubBusinessUnitRequest;

/**
 * 
 * @author asuneel
 *
 */
@Repository
public class SubBusinessUnitDaoImpl implements SubBusinessUnitDao {

	public static final String SAVEBUSINESSUNIT = "Insert into obs_subbusinessunitt(name,BusinessUnitId,CostCenterId) VALUES (?,?,?)";
	public static final String UPDATEBUSINESSUNITID = "update obs_subbusinessunitt set name = ?, BusinessUnitId = ?, CostCenterId = ? where id = ? ";
	//public static final String DELETEBUSINESSUNIT = "delete from obs_subbusinessunitt where id = ?";
	public static final String GETALLBUSINESSUNITS = "select * from obs_subbusinessunitt";
	public static final String GETBUSINESSUNITCOUNT = "select count(*) from obs_subbusinessunitt";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ojas.obs.dao.SubBusinessUnitDao#saveSubBusinessUnit(com.ojas.obs.request.
	 * SubBusinessUnitRequest)
	 */
	@Override
	public boolean saveSubBusinessUnit(SubBusinessUnitRequest subBusinessUnitRequest) {

		boolean flag = false;
		int[] save;
		List<SubBusinessUnit> subBusinessUnitList = subBusinessUnitRequest.getSubBusinessUnitModel();
		List<Object[]> list = new ArrayList<>();
		for (SubBusinessUnit subBusinessUnit : subBusinessUnitList) {

			Object[] subBusiness = new Object[] { subBusinessUnit.getName(), subBusinessUnit.getBusinessUnitId(),
					subBusinessUnit.getCostCenterId() };
			list.add(subBusiness);
		}
		save = jdbcTemplate.batchUpdate(SAVEBUSINESSUNIT, list);
		if (save.length > 0) {
			flag = true;
		}
		return flag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.SubBusinessUnitDao#updateSubBusinessUnit(com.ojas.obs.
	 * request.SubBusinessUnitRequest)
	 */
	@Override
	public boolean updateSubBusinessUnit(SubBusinessUnitRequest subBusinessUnitRequest) {

		boolean flag = false;
		int[] update;
		List<SubBusinessUnit> subBusinessUnitList = subBusinessUnitRequest.getSubBusinessUnitModel();
		List<Object[]> list = new ArrayList<>();
		for (SubBusinessUnit subBusinessUnit : subBusinessUnitList) {

			Object[] subBusiness = new Object[] { subBusinessUnit.getName(), subBusinessUnit.getBusinessUnitId(),
					subBusinessUnit.getCostCenterId(), subBusinessUnit.getId() };
			list.add(subBusiness);
		}
		update = jdbcTemplate.batchUpdate(UPDATEBUSINESSUNITID, list);
		if (update.length > 0) {
			flag = true;
		}
		return flag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.SubBusinessUnitDao#deleteSubBusinessUnit(int)
	 */
	/*
	 * @Override public boolean deleteSubBusinessUnit(SubBusinessUnitRequest
	 * subBusinessUnitRequest) {
	 * 
	 * boolean flag = false; int[] delete; List<SubBusinessUnit> subBusinessUnitList
	 * = subBusinessUnitRequest.getSubBusinessUnitModel(); List<Object[]> list = new
	 * ArrayList<>(); for (SubBusinessUnit subBusinessUnit : subBusinessUnitList) {
	 * 
	 * Object[] subBusiness = new Object[] { subBusinessUnit.getId() };
	 * list.add(subBusiness); } delete =
	 * jdbcTemplate.batchUpdate(DELETEBUSINESSUNIT, list); if (delete.length > 0) {
	 * flag = true; } return flag; }
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ojas.obs.dao.SubBusinessUnitDao#getAllSubBusinessUnitDetails(com.ojas.obs
	 * .request.SubBusinessUnitRequest)
	 */
	@Override
	public List<SubBusinessUnit> getAllSubBusinessUnitDetails() {

		return jdbcTemplate.query(GETALLBUSINESSUNITS,
				new BeanPropertyRowMapper<SubBusinessUnit>(SubBusinessUnit.class));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.SubBusinessUnitDao#getAllSubBusinessUnitDetailsCount()
	 */
	@Override
	public int getAllSubBusinessUnitDetailsCount() {

		return jdbcTemplate.queryForObject(GETBUSINESSUNITCOUNT, Integer.class);
	}

}
