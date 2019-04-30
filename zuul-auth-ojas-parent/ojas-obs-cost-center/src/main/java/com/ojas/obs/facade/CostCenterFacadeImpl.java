package com.ojas.obs.facade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ojas.obs.dao.CostCenterDao;
import com.ojas.obs.model.CostCenter;
import com.ojas.obs.model.CostCenterRequest;
import com.ojas.obs.model.CostCenterResponse;

@Service
public class CostCenterFacadeImpl implements CostCenterFacade {

	@Autowired
	CostCenterDao costcenterDao;

	Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ResponseEntity<Object> set(CostCenterRequest costReq) throws SQLException {
		// ResponseEntity<Object>responseEntity =null;
		CostCenterResponse response = null;
		boolean saveCode = false;
		logger.debug("INSIDE the service set method....");
		//int pageNo = costReq.getPageNo();
		//response.setPageNo(pageNo);
		//int pageSize = costReq.getPageSize();
		//response.setPageSize(pageSize);
			if (costReq.getTransactionType().equalsIgnoreCase("SAVE")) {
				response = new CostCenterResponse();
				logger.debug("checking the TransactionType");

				List<CostCenter> costCenter = costReq.getCostCenter();
				
				saveCode = costcenterDao.save(costCenter);
				response.setStatusMessage("Successfully record added");
				int count=costcenterDao.getAllCostCenterCount();
				response.setTotalCount(count);
				response.setListOfCostCenter(new ArrayList<>());
				return new ResponseEntity<Object>(response, HttpStatus.OK);
			}

			if (costReq.getTransactionType().equalsIgnoreCase("UPDATE")) {
				response = new CostCenterResponse();
				int count = costcenterDao.getAllCostCenterCount();
				logger.debug("checking the TransactionType update");
				List<CostCenter> costCenter = costReq.getCostCenter();
				
				boolean updateCode = costcenterDao.updateCenter(costCenter);
				response.setStatusMessage("Successfully record updated");
				response.setTotalCount(count);
				response.setListOfCostCenter(new ArrayList<>());
				return new ResponseEntity<Object>(response, HttpStatus.OK);
			}

			if (costReq.getTransactionType().equalsIgnoreCase("DELETE")) {
				response = new CostCenterResponse();
				logger.debug("checking the TransactionType delete");
				
				List<CostCenter> list = costReq.getCostCenter();
				for (CostCenter costCenter : list) {
					
				Integer id = costCenter.getId();
				boolean deleteCode = costcenterDao.deleteCenterCode(id);
				}
				
				response.setStatusMessage("Successfully record deleted");
				
				int count=costcenterDao.getAllCostCenterCount();
				
				response.setListOfCostCenter(new ArrayList<>());
				return new ResponseEntity<Object>(response, HttpStatus.OK);

			}
		
		return new ResponseEntity<Object>(response, HttpStatus.FAILED_DEPENDENCY);
	}

	@Override
	public ResponseEntity<Object> get(CostCenterRequest costReq) throws Exception,SQLException {
		CostCenterResponse response = new CostCenterResponse();
		logger.debug("Enter the service get method...");

		List<CostCenter> allCostCenterlist = costcenterDao.getAllCostCenter(costReq);

		List<CostCenter> arrayList = new ArrayList<>();

		int pageNo = costReq.getPageNo();
		int pageSize = costReq.getPageSize();
		response.setPageNo(pageNo);
		response.setPageSize(pageSize);

		List<CostCenter> list = costcenterDao.getCountPerPage(allCostCenterlist, costReq.getPageSize(),
				costReq.getPageNo());

		if (allCostCenterlist == null || list == null) {
			response.setListOfCostCenter(arrayList);
			response.setStatusMessage("No records found");
			response.setTotalCount(0);
		} else {
			int count = costcenterDao.getAllCostCenterCount();
			if (costReq.getPageNo() == 0 || costReq.getPageSize() == 0) {
				response.setListOfCostCenter(allCostCenterlist);
				response.setStatusMessage("Success");
				response.setTotalCount(count);
			} else {
				response.setListOfCostCenter(list);
				response.setStatusMessage("Success");
				response.setTotalCount(count);

			}

		}

		return new ResponseEntity<Object>(response, HttpStatus.OK);

	}

}
