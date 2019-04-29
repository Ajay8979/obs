package com.ojas.obs.facade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	@Override
	public ResponseEntity<Object> setEmployeeSkillInfo(EmployeeSkillInfoRequest employeeSkillInfoRequest)
			throws SQLException {

		EmployeeSkillInfoResponse response = new EmployeeSkillInfoResponse();
		List<EmployeeSkillInfo> listEmployeeSkillInfo = employeeSkillInfoRequest.getSkillInfoModel();

		// save method

		if (employeeSkillInfoRequest.getTransactionType().equalsIgnoreCase("save")) {

			employeeSkillDao.saveEmployeeSkillInfo(employeeSkillInfoRequest);
			int count = employeeSkillDao.getAllCount();
			response.setGetSkillInfoList(new ArrayList<>());
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

		/*
		 * EmployeeSkillInfoResponse response = new EmployeeSkillInfoResponse(); if
		 * (employeeSkillInfoRequest.getTransactionType().equalsIgnoreCase("getAll")) {
		 * List<EmployeeSkillInfo> listOfSkillInfo =
		 * employeeSkillDao.showEmployeeSkillInfo(employeeSkillInfoRequest); int count =
		 * employeeSkillDao.getAllCount(); response.setTotalCount(count);
		 * response.setGetSkillInfoList(listOfSkillInfo);
		 * response.setStatusMessage("success"); return new
		 * ResponseEntity<Object>(response, HttpStatus.OK); } return null;
		 */

		
		
		//-------------getById method call----------------------------//
		
		EmployeeSkillInfoResponse response = new EmployeeSkillInfoResponse();
		List<EmployeeSkillInfo> listEmployeeSkillInfo = employeeSkillInfoRequest.getSkillInfoModel();
		if (employeeSkillInfoRequest.getTransactionType().equalsIgnoreCase("getById")) {
			for (EmployeeSkillInfo skillDetails : listEmployeeSkillInfo) {
				List<EmployeeSkillInfo> byId = employeeSkillDao.getById(skillDetails.getId());
				int count = employeeSkillDao.getAllCount();
				response.setTotalCount(count);
				response.setGetSkillInfoList(byId);
				response.setStatusMessage("success");
				return new ResponseEntity<Object>(response, HttpStatus.OK);
			}
		}
		/*int pageNo = employeeSkillInfoRequest.getPageNo();
		int pageSize = employeeSkillInfoRequest.getPageSize();
		response.setPageNo(pageNo);
		response.setPageSize(pageSize);*/
		List<EmployeeSkillInfo> listOfSkillInfo = employeeSkillDao.showEmployeeSkillInfo(employeeSkillInfoRequest);
		/*List<EmployeeSkillInfo> listForPage = employeeSkillDao.getCountPerPage(listOfSkillInfo,
				employeeSkillInfoRequest.getPageSize(), employeeSkillInfoRequest.getPageNo());*/

		//if (listForPage == null || listOfSkillInfo == null) 
		if (listOfSkillInfo == null){
			response.setGetSkillInfoList(new ArrayList<>());
			response.setStatusMessage("No record Found");
			response.setTotalCount(0);
		} else {
			/*
			if (employeeSkillInfoRequest.getPageSize() == 0 || employeeSkillInfoRequest.getPageNo() == 0) {
				response.setTotalCount(count);
				response.setGetSkillInfoList(listOfSkillInfo);
				response.setStatusMessage("success");
			} else {*/
				int count = employeeSkillDao.getAllCount();
				response.setTotalCount(count);
				response.setGetSkillInfoList(listOfSkillInfo);
				response.setStatusMessage("success");
			}
			//System.out.println(response.getPageNo());
		
		return new ResponseEntity<Object>(response, HttpStatus.OK);

	}

}
