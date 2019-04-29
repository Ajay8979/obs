package com.ojas.obs.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ojas.obs.model.States;
import com.ojas.obs.request.StatesRequest;
import static com.ojas.obs.constants.Constants.INSERT_STATES;
import static com.ojas.obs.constants.Constants.UPDATE_STATES;
import static com.ojas.obs.constants.Constants.SELECT_STATES;
import static com.ojas.obs.constants.Constants.STATESCOUNT;
import static com.ojas.obs.constants.Constants. SELECT_STATES_BY_ID;

@Repository
public class StatesDAOImpl implements StatesDao {
    @Autowired
	private JdbcTemplate jdbcTemplate;
    Logger logger = Logger.getLogger(this.getClass());
    
	/*
	 * @Autowired private PropsReaderUtil propsReaderUtil;
	 */
	@Override
	public boolean saveStates(StatesRequest statesRequestObj) throws SQLException {

		logger.debug("Inside saveStates..DAO");
		List<States> states = statesRequestObj.getStates();
		List<Object[]> list = new ArrayList<Object[]>();
		int count=0;
		for (States states1 : states) {
			Object[] params = new Object[] { states1.getStateName() };
			list.add(params);
		}
		//int[] count = jdbcTemplate.batchUpdate(propsReaderUtil.getValue("INSERT_STATES"), list);
		int[] batchUpdate = jdbcTemplate.batchUpdate(INSERT_STATES, list);
		int len=batchUpdate.length;
		for (int i : batchUpdate) {
			if (i > 0) {
				count++;
			}
		}
		if (len == count) {
			logger.debug("saved successfully through DaoImpl");
			return true;
		}
		logger.debug("failed to save through daoImpl Method");
		return false;
	}

	@Override
	public int getAllStatesCount() throws SQLException {
		return jdbcTemplate.queryForObject(STATESCOUNT,Integer.class);
	}

	@Override
	public boolean updateStates(StatesRequest statesRequestObj) throws SQLException {
		logger.debug("Inside updatestates..DAO *****");
		List<States> states1=statesRequestObj.getStates();
		List<Object[]> list=new ArrayList<Object[]>();
		int count=0;
		for(States states:states1) {
			Object[] params=new Object[] {states.getStateName(),states.getId()};
			list.add(params);
		}
		//int[] count=jdbcTemplate.batchUpdate(propsReaderUtil.getValue("UPDATE_STATES"),list );
		int[] batchUpdate=jdbcTemplate.batchUpdate(UPDATE_STATES,list );
		int len=batchUpdate.length;
		for (int i : batchUpdate) {
			if (i > 0) {
				count++;
			}
		}
		if (len == count) {
			logger.debug("updated successfully through DaoImpl");
			return true;
		}
			logger.debug("failed to save through daoImpl Method");
			return false;
	}

	/*
	 * @Override public boolean deleteStates(StatesRequest statesRequestObj) throws
	 * SQLException { logger.debug("Inside deletestates..DAO *****"); List<States>
	 * states1=statesRequestObj.getStates(); List<Object[]> list=new
	 * ArrayList<Object[]>(); for(States states:states1) { Object[] params=new
	 * Object[] {states.getId()}; list.add(params); } //int[]
	 * count=jdbcTemplate.batchUpdate(propsReaderUtil.getValue("DELETE_STATES"),
	 * list); int[] count=jdbcTemplate.batchUpdate(DELETE_STATES, list); for (int i
	 * : count) { if (i > 0) { logger.debug("saved successfully through DaoImpl" +
	 * i); return true; } } logger.debug("failed to save through daoImpl Method");
	 * return false;
	 * 
	 * }
	 */

	@Override
	public List<States> getAll(StatesRequest statesRequestObj) throws SQLException {
		logger.debug("Inside getAllstates..DAO *****");
        //return jdbcTemplate.query(propsReaderUtil.getValue("SELECT_STATES"), new BeanPropertyRowMapper<States>(States.class));
		return jdbcTemplate.query(SELECT_STATES, new BeanPropertyRowMapper<States>(States.class));
	}
      
	@Override
	public List<States> getCountPerPage(List<States> stateList, int pageSize, int pageNo) {
		List<States> getAllFiltered = new ArrayList<>();
		if (stateList != null && !stateList.isEmpty()) {
			pageSize = pageSize > 0 ? pageSize : pageSize * -1;
			pageNo = pageNo > 0 ? pageNo : pageNo == 0 ? 1 : pageNo * -1;
			if (pageSize != 0) {
				int endIndex = pageNo * pageSize;
				int startIndex = endIndex - pageSize;
				endIndex = endIndex < stateList.size() ? endIndex : stateList.size();
				startIndex = startIndex < stateList.size() ? startIndex : 0;
				getAllFiltered = stateList.subList(startIndex, endIndex);
			}
		}
		return getAllFiltered;
	}

	@Override
	public List<States>getStateById(StatesRequest statesRequestObj) throws SQLException {
		List<Object[]> inputList = new ArrayList<Object[]>();
		Object[] stateId=null;
		List<States> statesList=statesRequestObj.getStates();
		for(States state:statesList) {
		   stateId=new Object[] {state.getId()};
			inputList.add(stateId);
		}
		return jdbcTemplate.query(SELECT_STATES_BY_ID,stateId, new
				BeanPropertyRowMapper<>(States.class));
	
	}

}
