package com.ojas.obs.facade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ojas.obs.dao.SkillDao;
import com.ojas.obs.model.ErrorResponse;
import com.ojas.obs.model.Skill;
import com.ojas.obs.request.SkillRequest;
import com.ojas.obs.response.SkillResponse;

@Service
public class SkillFacadeImpl implements SkillFacade {

	@Autowired
	private SkillDao skillDao;
	Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ResponseEntity<Object> setSkillInfo(SkillRequest skillRequest) throws SQLException {
		// TODO Auto-generated method stub
		logger.debug("Enter the set method facade...");
		SkillResponse response = new SkillResponse();
		ResponseEntity<Object> responseEntity = null;
		// save method
		try {
			if (skillRequest.getTransactionType().equalsIgnoreCase("save")) {
				logger.debug("checking transaction type save...");
				int saveSkillInfo = skillDao.saveSkillInfo(skillRequest);
				int count = skillDao.getAllCount();
				response.setListOfSkill(new ArrayList<>());
				response.setStatusMessage("Successfully record added");
				response.setTotalCount(count);

				return new ResponseEntity<Object>(response, HttpStatus.OK);
			}

			// update method

			if (skillRequest.getTransactionType().equalsIgnoreCase("update")) {
				List<Skill> listOfSkill = skillRequest.getListOfSkill();
				logger.debug("checking transaction type update...");
				for (Skill skillDetails : listOfSkill) {
					if (0 != skillDetails.getId()) {
						skillDao.updateSkillInfo(skillRequest);
						int count = skillDao.getAllCount();
						response.setListOfSkill(new ArrayList<>());
						response.setStatusMessage("Successfully record updated");
						response.setTotalCount(count);

						return new ResponseEntity<Object>(response, HttpStatus.OK);
					}
				}

			}
		} catch (Exception e) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage(e.getMessage());
			responseEntity = new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}
		return responseEntity;
	}

	@Override
	public ResponseEntity<Object> getSkillInfo(SkillRequest skillRequest) throws SQLException {

		logger.debug("enter the get method facade...");
		SkillResponse response = new SkillResponse();
		ResponseEntity<Object> responseEntity = null;
		try {
			if (skillRequest.getTransactionType().equalsIgnoreCase("getAll")) {
				List<Skill> listOfSkillInfo = skillDao.showSkillInfo(skillRequest);
				int count = skillDao.getAllCount();
				response.setTotalCount(count);
				response.setListOfSkill(listOfSkillInfo);
				response.setStatusMessage("success");
				return new ResponseEntity<Object>(response, HttpStatus.OK);
			}
			if (skillRequest.getTransactionType().equalsIgnoreCase("getById")) {
				List<Skill> listOfSkillInfo = skillDao.getById(skillRequest);
				int count = skillDao.getAllCount();
				response.setTotalCount(count);
				response.setListOfSkill(listOfSkillInfo);
				response.setStatusMessage("success");
				return new ResponseEntity<Object>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage(e.getMessage());
			responseEntity = new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}

		return responseEntity;

	}

}
