package com.ojas.obs.facade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ojas.obs.dao.SkillDao;
import com.ojas.obs.model.Skill;
import com.ojas.obs.request.SkillRequest;
import com.ojas.obs.response.SkillResponse;

@Service
public class SkillFacadeImpl implements  SkillFacade {

	@Autowired
	private SkillDao skillDao;
	@Override
	public ResponseEntity<Object> setSkillInfo(SkillRequest skillRequest) {
		// TODO Auto-generated method stub
		
		SkillResponse response = new SkillResponse();

		// save method

		if (skillRequest.getTransactionType().equalsIgnoreCase("save")) {
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
		return null;
	}
	@Override
	public ResponseEntity<Object> getSkillInfo(SkillRequest skillRequest) {

		
		SkillResponse response = new SkillResponse();
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
		return null;

	}
	
	

}
