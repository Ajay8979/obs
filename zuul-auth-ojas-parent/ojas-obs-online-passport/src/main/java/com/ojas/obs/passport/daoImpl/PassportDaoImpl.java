package com.ojas.obs.passport.daoImpl;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ojas.obs.passport.Request.PassportRequest;
import com.ojas.obs.passport.dao.PassportDao;
import com.ojas.obs.passport.model.Passport;
//import com.ojas.obs.passport.utility.PropsReaderUtil;
import static com.ojas.obs.passport.utility.Constants.INSERTPASSPORTSTMT;
import static com.ojas.obs.passport.utility.Constants.GETTOTALSTMT;
//import static com.ojas.obs.passport.utility.Constants.DELETESTMT;
import static com.ojas.obs.passport.utility.Constants.UPDATESTMT;
import static com.ojas.obs.passport.utility.Constants.COUNTSTMT;
import static com.ojas.obs.passport.utility.Constants.GETTOBYID;

@Repository
public class PassportDaoImpl implements PassportDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	// public static final String countQuery="select count(*) from obs_password
	// where centername=?";
	/*
	 * @Autowired private PropsReaderUtil propsReaderUtil;
	 */

	Logger logger = Logger.getLogger(this.getClass());

	/** save method **/

	@Override
	public boolean savePassport(PassportRequest passportRequest) throws SQLException {
		logger.debug("In save Passport method jdbc template" + jdbcTemplate);

		List<Passport> passport = passportRequest.getPassportList();
		List<Object[]> list = new ArrayList<Object[]>();
		try {
			for (Passport passport2 : passport) {
				Object[] params = new Object[] { passport2.getCenterName() };
				list.add(params);
			}

			// int[] update =
			// jdbcTemplate.batchUpdate(propsReaderUtil.getValue("INSERTPASSPORTSTMT"),
			// list);
			int[] update = jdbcTemplate.batchUpdate(INSERTPASSPORTSTMT, list);

			for (int i : update) {
				if (i > 0) {
					logger.debug("saved successfully through DaoImpl" + i);
					return true;

				}
			}
		} finally {
			jdbcTemplate.getDataSource().getConnection().close();

		}

		logger.debug("failed to save through daoImpl Method");
		return false;
	}

	/*
	 * // delete method
	 * 
	 * @Override public boolean deletePassport(PassportRequest passportRequest)
	 * throws SQLException {
	 * logger.debug("Inside the delete passport method DaoImpl"); List<Passport>
	 * passport = passportRequest.getPassportList(); List<Object[]> list = new
	 * ArrayList<Object[]>(); for (Passport passport2 : passport) { Object[] params
	 * = new Object[] { passport2.getId() }; list.add(params); } //int[] update =
	 * jdbcTemplate.batchUpdate(propsReaderUtil.getValue("DELETESTMT"), list); int[]
	 * update = jdbcTemplate.batchUpdate(DELETESTMT, list); for (int i : update) {
	 * if (i > 0) { logger.debug("delete passport in DaoImpl " + i); return true; }
	 * } logger.debug("failed delete passport in DaoImpl "); return false; }
	 */

	//

	/** update method **/

	@Override
	public boolean updatePassport(PassportRequest passportRequest) throws SQLException {
		logger.debug("Inside the update passport method DaoImpl");
		List<Passport> passport = passportRequest.getPassportList();
		List<Object[]> list = new ArrayList<Object[]>();
		try {
			for (Passport passport2 : passport) {
				Object[] params = new Object[] { passport2.getCenterName(), passport2.getId() };
				list.add(params);
			}
			// int[] update =
			// jdbcTemplate.batchUpdate(propsReaderUtil.getValue("UPDATESTMT"), list);
			int[] update = jdbcTemplate.batchUpdate(UPDATESTMT, list);
			for (int i : update) {
				if (i > 0) {
					logger.debug("update passport method in DaoImpl");
					return true;
				}
			}
		} finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}

		logger.debug("failed to update passport method in DaoImpl");
		return false;
	}

	/** GETALL method **/

	@Override
	public List<Passport> getAll(PassportRequest passportRequest) throws SQLException {
		logger.debug("Inside the getAll passport method in DaoImpl");
		// List<Passport> query =
		// jdbcTemplate.query(propsReaderUtil.getValue("GETTOTALSTMT"),new
		// BeanPropertyRowMapper<>(Passport.class));
		List<Passport> query = null;
		try {
			query = jdbcTemplate.query(GETTOTALSTMT, new BeanPropertyRowMapper<>(Passport.class));
		} finally {
			jdbcTemplate.getDataSource().getConnection().close();

		}

		logger.debug("Returned List" + query);
		return query;
	}

	/** Count number of records in database **/

	@Override
	public Integer getcountPassport(PassportRequest passportRequest) throws SQLException {
		logger.debug("Inside the countPassport method in DaoImpl");
		// Integer update =
		// jdbcTemplate.queryForObject(propsReaderUtil.getValue("COUNTSTMT"),
		// Integer.class);
		Integer update = null;
		try {
			update = jdbcTemplate.queryForObject(COUNTSTMT, Integer.class);

		} finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}

		logger.debug("Returned count" + update);
		return update;
	}

	/** Pagination **/

	/*
	 * @Override public List<Passport> getCountPerPage(List<Passport> passportList,
	 * int pageSize, int pageNo) throws SQLException { List<Passport> getAllFiltered
	 * = new ArrayList<>(); if (passportList != null && !passportList.isEmpty()) {
	 * pageSize = pageSize > 0 ? pageSize : pageSize * -1; pageNo = pageNo > 0 ?
	 * pageNo : pageNo == 0 ? 1 : pageNo * -1; if (pageSize != 0) { int endIndex =
	 * pageNo * pageSize; int startIndex = endIndex - pageSize; endIndex = endIndex
	 * < passportList.size() ? endIndex : passportList.size(); startIndex =
	 * startIndex < passportList.size() ? startIndex : 0; getAllFiltered =
	 * passportList.subList(startIndex, endIndex); } } return getAllFiltered; }
	 */
	/** GetById **/

	@Override
	public List<Passport> getById(PassportRequest passportRequest) throws SQLException {
		List<Object[]> inputList = new ArrayList<Object[]>();
		Object[] update = null;
		try {
			List<Passport> listOfPassport = passportRequest.getPassportList();

			for (Passport passport : listOfPassport) {
				update = new Object[] { passport.getId() };
				inputList.add(update);
			}
			return jdbcTemplate.query(GETTOBYID, update, new BeanPropertyRowMapper<>(Passport.class));
		} finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}
	}

}
