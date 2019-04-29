package com.ojas.obs.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ojas.obs.model.GpaPlan;
import com.ojas.obs.request.GpaRequest;
import static com.ojas.obs.constants.GpaServiceConstants.*;

@Repository
public class GpaPlanDaoImpl implements GpaPlanDao {

	@Autowired
	private JdbcTemplate jdbcTemplaeObject;

	Logger logger = Logger.getLogger(this.getClass());

	@Override
	public boolean saveGpaPlan(GpaRequest gpaPlan) throws SQLException {

		try {

			List<Object[]> inputList = new ArrayList<Object[]>();

			for (GpaPlan plan : gpaPlan.getGpaPlan()) {
				logger.debug("Inside save Gpa..getConnection()");
				Object[] obj = new Object[] { plan.getGpaPlanType(), plan.getGpaPremium(), plan.getTotalPremium() };

				inputList.add(obj);

				/*
				 * if (n > 0) { return true; } return false;
				 */
			}
			int[] batchUpdate = jdbcTemplaeObject.batchUpdate(INSERTGPA, inputList);
			if (batchUpdate.length > 0) {
				return true;
			}
			return false;
		}

		catch (Exception e) {
			logger.debug("Inside saveGpa..DAO catch block" + e.getMessage());
		}
		return false;
	}

	@Override
	public boolean updateGpa(GpaRequest gpaRequest) throws SQLException {

		List<Object[]> inputList = new ArrayList<Object[]>();
		logger.debug("Inside updateGpa..DAO *****");
		try {

			List<GpaPlan> gpa = gpaRequest.getGpaPlan();

			for (GpaPlan plan : gpa) {

				Object[] obj = new Object[] { plan.getGpaPlanType(), plan.getGpaPremium(), plan.getTotalPremium(),
						plan.getGpaPlanId() };
				/*
				 * if (n > 0) return true; else return false;
				 */

				inputList.add(obj);

			}
			int[] batchUpdate = jdbcTemplaeObject.batchUpdate(UPDATEGPAPLAN, inputList);

			if (batchUpdate.length > 0) {
				return true;
			}
			return false;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<GpaPlan> getAllGpaDetails(GpaRequest gpaRequest) throws SQLException {

		logger.debug("Inside getAllGpaDetails DAO .***");

		List<GpaPlan> gpa = jdbcTemplaeObject.query(GETGPAPLAN, new BeanPropertyRowMapper<>(GpaPlan.class));

		return gpa;
	}

	// @Override
//	public boolean deleteGpaRecord(int courseId) throws SQLException {
//		logger.debug("Inside deleteGpa...DAO");
//
//		int i = jdbcTemplaeObject.update(DELETEGPA, new Timestamp(new Date().getTime()), courseId);
//		if (i > 0) {
//			return true;
//		} else {
//			return false;
//
//		}
//	}

	@Override
	public List<GpaPlan> getPageRecords(List<GpaPlan> allGpaDetails, int pageSize, int pageNum) {

		List<GpaPlan> gpaDetails = new ArrayList<>();

		if (allGpaDetails != null && !allGpaDetails.isEmpty()) {

			pageSize = pageSize > 0 ? pageSize : pageSize * -1;

			pageNum = pageNum > 0 ? pageNum : pageNum == 0 ? 1 : pageNum * -1;

			if (pageSize != 0) {

				int endIndex = pageNum * pageSize;

				int startIndex = endIndex - pageSize;

				endIndex = endIndex < allGpaDetails.size() ? endIndex : allGpaDetails.size();

				startIndex = startIndex < allGpaDetails.size() ? startIndex : 0;

				gpaDetails = allGpaDetails.subList(startIndex, endIndex);
			}
		}
		return gpaDetails;
	}

	@Override
	public int getAllGpaDetailsCount() throws SQLException {

		return jdbcTemplaeObject.queryForObject(GETGPACOUNT, Integer.class);

	}

}
