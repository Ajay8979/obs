package com.ojas.obs.dao;

import static com.ojas.obs.constants.UserConstants.DELETECOSTCENTER;
import static com.ojas.obs.constants.UserConstants.GETCOSTCENTER;
import static com.ojas.obs.constants.UserConstants.GETCOSTCENTERCOUNT;
import static com.ojas.obs.constants.UserConstants.INSERTCOSTCENTER;
import static com.ojas.obs.constants.UserConstants.UPDATECOSTCENTER;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ojas.obs.model.CostCenter;
import com.ojas.obs.model.CostCenterRequest;

@Repository
public class CostCenterImpl implements CostCenterDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	Logger logger = Logger.getLogger(this.getClass());

	// saving the data into database
	@Override
	public boolean save(List<CostCenter> lists) throws SQLException {
		logger.debug("INSIDE the save....");
		ArrayList<Object[]> list = new ArrayList<>();

		for (CostCenter costCenter2 : lists) {
			Object[] param = new Object[] { costCenter2.getCostCenterCode() };
			list.add(param);
		}

		int[] i = jdbcTemplate.batchUpdate(INSERTCOSTCENTER, list);
		try {
			jdbcTemplate.getDataSource().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int j : i) {
			if (j > 0) {

				return true;
			}else {
				
			}
		}
		return false;
	}

	// update the data into database

	@Override
	public boolean updateCenter(List<CostCenter> lists) throws SQLException {
		logger.debug("INSIDE the updateCenter....");
		int costCenterCode;
		ArrayList<Object[]> list = new ArrayList<>();

		for (CostCenter costCenter2 : lists) {
			Object[] param = new Object[] { costCenter2.getCostCenterCode(), costCenter2.getId()  };

			list.add(param);
		}
		int[] batchUpdate = jdbcTemplate.batchUpdate(UPDATECOSTCENTER, list);

		for (int i : batchUpdate) {

			if (i > 0) {
				return true;
			}
		}
		try {
			jdbcTemplate.getDataSource().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// Delete the data into database

	@Override
	public boolean deleteCenterCode(int id) throws SQLException {
		logger.debug("INSIDE the deleteCenterCode....");
		int batchUpdate = jdbcTemplate.update(DELETECOSTCENTER, id);
		if (batchUpdate > 0) {
			return true;
		}
		
		try {
			jdbcTemplate.getDataSource().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// retrieve the data in database

	@Override
	public List<CostCenter> getAllCostCenter(CostCenterRequest costCenterReq) throws SQLException {

		logger.debug("INSIDE the getAllCostCenter....");
		List<CostCenter> cost= jdbcTemplate.query(GETCOSTCENTER, new BeanPropertyRowMapper<>(CostCenter.class));
		try {
			jdbcTemplate.getDataSource().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cost;
	}

	// get costcenter count
	
	@Override
	public int getAllCostCenterCount() throws SQLException {
		logger.debug("INSIDE the getAllCostCenterCount....");
		int i= jdbcTemplate.queryForObject(GETCOSTCENTERCOUNT, Integer.class);
		
		try {
			jdbcTemplate.getDataSource().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
	}

	@Override
	public List<CostCenter> getCountPerPage(List<CostCenter> list, int pageSize, int pageNo) {
		logger.debug("INSIDE the getCountPerPage....");

		List<CostCenter> getAllFilteredList = new ArrayList<>();
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

	}

}
