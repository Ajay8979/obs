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

	public static final String SAVEBUSINESSUNIT = "Insert into obs_subbusinessunit(name,BusinessUnitId,CostCenterId) VALUES (?,?,?)";
	public static final String UPDATEBUSINESSUNITID = "update obs_subbusinessunit set name = ?, BusinessUnitId = ?, CostCenterId = ? where id = ? ";
	public static final String GETALLBUSINESSUNITS = "select * from obs_subbusinessunit";
	public static final String GETBYIDBUSINESSUNITS = "select * from obs_subbusinessunit where id = ?";

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
	 * @see com.ojas.obs.dao.SubBusinessUnitDao#getAllSubBusinessUnitDetails()
	 */
	@Override
	public List<SubBusinessUnit> getAllSubBusinessUnitDetails() {

		return jdbcTemplate.query(GETALLBUSINESSUNITS,
				new BeanPropertyRowMapper<SubBusinessUnit>(SubBusinessUnit.class));

	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ojas.obs.dao.SubBusinessUnitDao#getByIdSubBusinessUnitDetails(java.lang.Integer)
	 */
	@Override
	public List<SubBusinessUnit> getByIdSubBusinessUnitDetails(Integer id) {

		Object[] params = new Object[] { id };
		return jdbcTemplate.query(GETBYIDBUSINESSUNITS, params,
				new BeanPropertyRowMapper<SubBusinessUnit>(SubBusinessUnit.class));

	}
	
}
