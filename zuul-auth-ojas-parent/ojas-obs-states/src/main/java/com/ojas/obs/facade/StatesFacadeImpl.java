package com.ojas.obs.facade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ojas.obs.dao.StatesDao;
import com.ojas.obs.model.ErrorResponse;
import com.ojas.obs.model.States;
import com.ojas.obs.request.StatesRequest;
import com.ojas.obs.response.StatesResponse;
import static com.ojas.obs.constants.Constants.DELETE;
import static com.ojas.obs.constants.Constants.FAILED;
import static com.ojas.obs.constants.Constants.SAVE;
import static com.ojas.obs.constants.Constants.UPDATE;
import static com.ojas.obs.constants.Constants.GETALL;
import static com.ojas.obs.constants.Constants.GETBYID;

@Service
public class StatesFacadeImpl implements StatesFacade {

	@Autowired
	private StatesDao statesDao;
	Logger logger = Logger.getLogger(this.getClass());

    @Override
	public ResponseEntity<Object> setStates(StatesRequest statesRequestObj) {

		logger.debug("inside set method : " + statesRequestObj);
		StatesResponse statesResponse = null;
		try {
			if (statesRequestObj.getTransactionType().equalsIgnoreCase(SAVE)) {
				statesResponse = new StatesResponse();
				int count = statesDao.getAllStatesCount();
				boolean savestate = statesDao.saveStates(statesRequestObj);
				if (savestate) {
					logger.debug("inside  save condition.****** : " + savestate);
					statesResponse.setStatusMessage("Successfully record added");
					statesResponse.setTotalCount(count);
					return new ResponseEntity<>(statesResponse, HttpStatus.OK);
				} else {
					statesResponse.setStatusMessage(FAILED);
					return new ResponseEntity<>(statesResponse, HttpStatus.CONFLICT);
				}
			}
			if (statesRequestObj.getTransactionType().equalsIgnoreCase(UPDATE)) {
				statesResponse = new StatesResponse();
				int count = statesDao.getAllStatesCount();
				boolean updateState = statesDao.updateStates(statesRequestObj);
				logger.debug("inside  update condition.****** : " + updateState);
				if (updateState) {
					statesResponse.setStatusMessage("Successfully record updated");
					statesResponse.setTotalCount(count);
					return new ResponseEntity<>(statesResponse, HttpStatus.OK);
				} else {
					statesResponse.setStatusMessage(FAILED);
					return new ResponseEntity<>(statesResponse, HttpStatus.CONFLICT);
				}
			}
			/*
			 * if (statesRequestObj.getTransactionType().equalsIgnoreCase(DELETE)) {
			 * statesResponse = new StatesResponse(); List<Integer> list= new
			 * ArrayList<Integer>(); int count = statesDao.getAllStatesCount(); for(States
			 * state:statesRequestObj.getStates()) { Integer id =state.getId();
			 * list.add(id); boolean deleteState = statesDao.deleteStates(statesRequestObj);
			 * logger.debug("inside  delete condition.****** and id is : " + list); if
			 * (deleteState) {
			 * statesResponse.setStatusMessage("Successfully record deleted");
			 * statesResponse.setTotalCount(count); return new
			 * ResponseEntity<>(statesResponse, HttpStatus.OK); } else {
			 * statesResponse.setStatusMessage(FAILED); return new
			 * ResponseEntity<>(statesResponse, HttpStatus.CONFLICT); } } }
			 */
			boolean b = (statesRequestObj.getTransactionType().equalsIgnoreCase(SAVE)
					|| statesRequestObj.getTransactionType().equalsIgnoreCase(UPDATE));
					if (!(statesRequestObj.getTransactionType().equalsIgnoreCase(DELETE) || b)) {
						statesResponse = new StatesResponse();
						statesResponse.setStatusCode("422");
						statesResponse.setStatusMessage("transaction type is not correct");
					}
					return new ResponseEntity<>(statesResponse, HttpStatus.CONFLICT);
		} catch (SQLException exception) {
			logger.debug("inside designationService catch block.****");
			ErrorResponse error = new ErrorResponse();
			logger.debug("data is  invalid");
			exception.printStackTrace();
			error.setStatusMessage(exception.getMessage());
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}
	}
    
	@Override
	public ResponseEntity<Object> getStates(StatesRequest statesRequestObj) throws SQLException {

		StatesResponse statesResponse = new StatesResponse();
		logger.debug("inside get in DesignationServiceImpl.***");
		List<States> StateList=null;
		if(statesRequestObj.getTransactionType().equalsIgnoreCase(GETALL)) {
		 StateList = statesDao.getAll(statesRequestObj);
		}else if(statesRequestObj.getTransactionType().equalsIgnoreCase(GETBYID)){
			 StateList = statesDao.getStateById(statesRequestObj);
		}
		List<States> list = statesDao.getCountPerPage(StateList, statesRequestObj.getPageSize(),
				statesRequestObj.getPageNo());
		if (  list == null || StateList == null) { 
			statesResponse.setStatesList(new ArrayList<>());
			statesResponse.setStatusMessage("No records found");
			statesResponse.setTotalCount(0);
		} else {

			int count = statesDao.getAllStatesCount();
			if (statesRequestObj.getPageNo() == 0 || statesRequestObj.getPageSize() == 0) {
				statesResponse.setStatesList(StateList);
				statesResponse.setStatusMessage("success");
				statesResponse.setTotalCount(count);
			} else {
				statesResponse.setStatesList(list);
				statesResponse.setStatusMessage("success");
				statesResponse.setTotalCount(count);
			}
		}
		return new ResponseEntity<Object>(statesResponse, HttpStatus.OK);
	}

}
