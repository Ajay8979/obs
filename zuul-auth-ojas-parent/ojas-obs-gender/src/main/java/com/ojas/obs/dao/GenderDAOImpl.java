package com.ojas.obs.dao;

import java.util.ArrayList;
import java.util.List;
import static com.ojas.obs.constants.Constants.INSERT_GENDER;
import static com.ojas.obs.constants.Constants.UPDATE_GENDER;
import static com.ojas.obs.constants.Constants.SELECT_GENDER;
import static com.ojas.obs.constants.Constants.GENDERCOUNT;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ojas.obs.model.Genders;
import com.ojas.obs.request.GenderRequest;
@Repository
public class GenderDAOImpl implements GenderDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/*
	 * @Autowired private PropsReaderUtil propsReaderUtil;
	 */
	Logger logger = Logger.getLogger(this.getClass());
     
	/**
	 * method to save the record from database
	 */
	@Override
	public boolean saveGender(GenderRequest genderRequest) {
		List<Genders> genders=genderRequest.getGender();
		List<Object[]> list= new ArrayList<Object[]>();
	    for(Genders gender:genders) {
		Object[] model=new Object[]{gender.getGender()};
		list.add(model);
	    }
		//int[] count=jdbcTemplate.batchUpdate(propsReaderUtil.getValue("INSERT_GENDER"), list);
	    int[] count=jdbcTemplate.batchUpdate(INSERT_GENDER, list);
		for (int i : count) {
			if (i > 0) {
				logger.debug("saved successfully through DaoImpl" + i);
				return true;

			}
		}
		logger.debug("failed to save through daoImpl Method");
		return false;
	}
    
	/**
	 * method to update the record from database
	 */
	@Override
	public boolean updateGender(GenderRequest genderRequest) {
		List<Genders> genders = genderRequest.getGender();
		List<Object[]> list = new ArrayList<Object[]>();
		for (Genders gender : genders) {
			Object[] model = new Object[] { gender.getGender() ,gender.getId() };
			list.add(model);
		}
		//int[] count = jdbcTemplate.batchUpdate(propsReaderUtil.getValue("UPDATE_GENDER"), list);
		int[] count = jdbcTemplate.batchUpdate(UPDATE_GENDER, list);
		for (int i : count) {
			if (i > 0) {
				logger.debug("saved successfully through DaoImpl" + i);
				return true;
			}
		}
		logger.debug("failed to save through daoImpl Method");
		return false;
	}
	/**
	 * method to delete the record from database
	 */

	/*
	 * @Override public boolean deleteGender(GenderRequest genderRequest) {
	 * List<Genders> genders=genderRequest.getGender(); List<Object[]> list = new
	 * ArrayList<Object[]>(); for(Genders gender:genders) { Object[] model=new
	 * Object[]{gender.getId()}; list.add(model); } //int[]
	 * count=jdbcTemplate.batchUpdate(propsReaderUtil.getValue("DELETE_GENDER"),list
	 * ); int[] count=jdbcTemplate.batchUpdate(DELETE_GENDER,list); for (int i :
	 * count) { if (i > 0) { logger.debug("saved successfully through DaoImpl" + i);
	 * return true; } } logger.debug("failed to save through daoImpl Method");
	 * return false; }
	 */
    /**
     * method will get count of all record from database
     */
	@Override
	public int getAllCount(GenderRequest genderRequest) {
	   return jdbcTemplate.queryForObject(GENDERCOUNT, Integer.class);
	}
	/**
	 * method to get all the record from database
	 */

	@Override
	public List<Genders> getAll(GenderRequest genderRequest) {
		//return jdbcTemplate.query(propsReaderUtil.getValue("SELECT_GENDER"), new BeanPropertyRowMapper<Genders>(Genders.class));
		return jdbcTemplate.query(SELECT_GENDER, new BeanPropertyRowMapper<Genders>(Genders.class));
	}

	@Override
	public List<Genders> getCountPerPage(List<Genders> gendersList, int pageSize, int pageNo) {
		List<Genders> getAllFiltered = new ArrayList<>();
		if (gendersList != null && !gendersList.isEmpty()) {
			pageSize = pageSize > 0 ? pageSize : pageSize * -1;
			pageNo = pageNo > 0 ? pageNo : pageNo == 0 ? 1 : pageNo * -1;
			if (pageSize != 0) {
				int endIndex = pageNo * pageSize;
				int startIndex = endIndex - pageSize;
				endIndex = endIndex < gendersList.size() ? endIndex : gendersList.size();
				startIndex = startIndex < gendersList.size() ? startIndex : 0;
				getAllFiltered = gendersList.subList(startIndex, endIndex);
			}
		}
		return getAllFiltered;
	}

}
