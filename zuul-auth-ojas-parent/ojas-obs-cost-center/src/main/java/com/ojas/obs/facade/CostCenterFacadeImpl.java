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
		ResponseEntity<Object> responseEntity = null;

		CostCenterResponse response = null;
		boolean saveCode = false;
		logger.debug("The Request INSIDE the service set method....");

		

		// ---------------------for saving the data----------------------------//
		if (null != costReq.getTransactionType()) {
			if (costReq.getTransactionType().equalsIgnoreCase("SAVE")) {
				response = new CostCenterResponse();
				logger.debug("checking the TransactionType SAVE");
				//List<CostCenter> costCenter = costReq.getCostCenter();
				saveCode = costcenterDao.save(costReq);
				if (saveCode) {
					response.setMessage("Successfully record added");
					response.setStatusCode("200");
				} else {
					response.setMessage("sorry  record has not added");
					response.setStatusCode("409");
				}

				response.setListOfCostCenter(new ArrayList<>());
				return new ResponseEntity<Object>(response, HttpStatus.OK);
			}

			// --------------------- for updating the data ----------------------------//

			if (costReq.getTransactionType().equalsIgnoreCase("UPDATE")) {
				response = new CostCenterResponse();
				logger.debug("checking the TransactionType update");
				List<CostCenter> costCenter = costReq.getCostCenter();
				for (CostCenter costCenterCode : costCenter) {
					if (0 != costCenterCode.getId()) {
						boolean updateCode = costcenterDao.updateCenter(costCenter);
						if (updateCode) {
							response.setMessage("Successfully record updated");
							response.setStatusCode("200");
							response.setListOfCostCenter(new ArrayList<>());
							return new ResponseEntity<Object>(response, HttpStatus.OK);
						} else {
							logger.error("Record is not Updated...");
							response.setMessage("Not updated");
							response.setStatusCode("409");
							return new ResponseEntity<Object>(response, HttpStatus.OK);
						}

					}
				}

			}

		} else {
			response = new CostCenterResponse();
			logger.error("Transaction Type is not Valid...");
			response.setMessage("Transaction Type is not valid");
			responseEntity = new ResponseEntity<Object>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return responseEntity;
	}

	@Override
	public ResponseEntity<Object> get(CostCenterRequest costReq) throws Exception, SQLException {
		CostCenterResponse response = new CostCenterResponse();
		logger.info("The Request Enter the service get method...");

		List<CostCenter> allCostCenterlist = costcenterDao.getAllCostCenter(costReq);
		
			
		if ((allCostCenterlist != null) && (!allCostCenterlist.isEmpty())) {
			response.setListOfCostCenter(allCostCenterlist);
			response.setMessage("list has  found");

		} else {
			response.setListOfCostCenter(new ArrayList<>());
			response.setMessage("no records found");
			response.setStatusCode("409");
		}
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}
