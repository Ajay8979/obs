package com.ojas.obs.dao;

import java.sql.SQLException;
import java.util.List;
import com.ojas.obs.model.States;
import com.ojas.obs.request.StatesRequest;

public interface StatesDao {
	boolean saveStates(StatesRequest statesRequestObj) throws SQLException;

	int getAllStatesCount() throws SQLException;

	boolean updateStates(StatesRequest statesRequestObj) throws SQLException;

	//boolean deleteStates(StatesRequest statesRequestObj) throws SQLException;

	List<States> getAll(StatesRequest statesRequestObj) throws SQLException;
	List<States> getCountPerPage(List<States> stateList, int pageSize, int pageNo);
}
