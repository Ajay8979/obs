package com.ojas.obs.facade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ojas.obs.dao.EmployeeSkillDao;
import com.ojas.obs.model.EmployeeSkillInfo;
import com.ojas.obs.model.EmployeeSkillInfoRequest;
import com.ojas.obs.model.EmployeeSkillInfoResponse;

@Service
public class EmployeeSkillFacadeImpl implements EmployeeSkillFacade {

	@Autowired
	private EmployeeSkillDao employeeSkillDao;

	Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ResponseEntity<Object> setEmployeeSkillInfo(EmployeeSkillInfoRequest employeeSkillInfoRequest)
			throws SQLException {

		EmployeeSkillInfoResponse response = new EmployeeSkillInfoResponse();
		List<EmployeeSkillInfo> listEmployeeSkillInfo = employeeSkillInfoRequest.getSkillInfoModel();
		logger.info("Enter the set method facade ....");

		// save method

		if (employeeSkillInfoRequest.getTransactionType().equalsIgnoreCase("save")) {

			employeeSkillDao.saveEmployeeSkillInfo(employeeSkillInfoRequest);
			int count = employeeSkillDao.getAllCount();
			response.setGetSkillInfoList(new ArrayList<>());
			logger.debug("Successfully record addedd...");
			response.setStatusMessage("Successfully record added");
			response.setTotalCount(count);

			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}

		// update method

		if (employeeSkillInfoRequest.getTransactionType().equalsIgnoreCase("update"))
			for (EmployeeSkillInfo skillDetails : listEmployeeSkillInfo) {
				if (0 != skillDetails.getId()) {
					employeeSkillDao.updateEmployeeSkillInfo(employeeSkillInfoRequest);
					int count = employeeSkillDao.getAllCount();
					response.setGetSkillInfoList(new ArrayList<>());
					logger.debug("Successfully record updated....");
					response.setStatusMessage("Successfully record updated");
					response.setTotalCount(count);
					return new ResponseEntity<Object>(response, HttpStatus.OK);
				}
			}
		return null;
	}

	@Override
	public ResponseEntity<Object> getEmployeeSkillInfo(EmployeeSkillInfoRequest employeeSkillInfoRequest)
			throws SQLException {

		logger.info("Enter the get method () in facade class....");
		
		// -------------getById method call----------------------------//

		EmployeeSkillInfoResponse response = new EmployeeSkillInfoResponse();
		List<EmployeeSkillInfo> listEmployeeSkillInfo = employeeSkillInfoRequest.getSkillInfoModel();
		List<EmployeeSkillInfo> byId = null;
		if (employeeSkillInfoRequest.getTransactionType().equalsIgnoreCase("getById")) {
			for (EmployeeSkillInfo skillDetails : listEmployeeSkillInfo) {
				if (skillDetails.getId() == null)
					byId = employeeSkillDao.getByEmpId(skillDetails.getEmployee_id());
				else
					byId = employeeSkillDao.getById(skillDetails.getId());
				int count = employeeSkillDao.getAllCount();
				response.setTotalCount(count);
				response.setGetSkillInfoList(byId);
				logger.debug(" success");
				response.setStatusMessage("success");
				return new ResponseEntity<Object>(response, HttpStatus.OK);
			}
		}

		List<EmployeeSkillInfo> listOfSkillInfo = employeeSkillDao.showEmployeeSkillInfo(employeeSkillInfoRequest);

		// if (listForPage == null || listOfSkillInfo == null)
		if (listOfSkillInfo == null) {
			response.setGetSkillInfoList(new ArrayList<>());
			response.setStatusMessage("No record Found");
			response.setTotalCount(0);
		} else {
			int count = employeeSkillDao.getAllCount();
			response.setTotalCount(count);
			response.setGetSkillInfoList(listOfSkillInfo);
			response.setStatusMessage("success");
		}
		// System.out.println(response.getPageNo());

		return new ResponseEntity<Object>(response, HttpStatus.OK);

	}

}
