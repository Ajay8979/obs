package com.ojas.obs.passport.facadeImpl;
//import static com.ojas.obs.passport.utility.Constants.DELETE;
import static com.ojas.obs.passport.utility.Constants.GETALL;
import static com.ojas.obs.passport.utility.Constants.SAVE;
import static com.ojas.obs.passport.utility.Constants.UPDATE;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ojas.obs.passport.Request.PassportRequest;
import com.ojas.obs.passport.Response.PassportResponse;
import com.ojas.obs.passport.daoImpl.PassportDaoImpl;
import com.ojas.obs.passport.facade.PassportFacade;
import com.ojas.obs.passport.model.Passport;

@Service
public class PassportFacadeImpl implements PassportFacade {

	@Autowired
	PassportDaoImpl passportDaoImpl;

	PassportResponse passportResponse = null;

	Logger logger = Logger.getLogger(this.getClass());

	// SAVE 
	@Override
	public ResponseEntity<Object>  setPassport(PassportRequest passportRequestObject) throws SQLException {
		passportResponse = new PassportResponse();
		if (passportRequestObject.getTransaactionType().equalsIgnoreCase(SAVE)) {
			boolean savePassport = passportDaoImpl.savePassport(passportRequestObject);
			
			System.out.println("PassportFacadeImpl.setPassport()");
			System.out.println("save::"+savePassport);
			int count=passportDaoImpl.getcountPassport(passportRequestObject);
			logger.debug("@@@@@@@@@@@received at service by calling the savePassport is" + savePassport);
			if (savePassport) {
				System.out.println("PassportFacadeImpl.setPassport()$$$$SSSSS");
				logger.debug("inside  save condition.****** : ");
				passportResponse.setStatusCode("200");
				passportResponse.setStatusMessage("PassportCenter has saved successfully");
				passportResponse.setNoOfRecords(count);
		return new ResponseEntity<Object>(passportResponse, HttpStatus.OK);
	}else {
		logger.debug("inside  save condition.****** : ");
		passportResponse.setStatusCode("422");
		passportResponse.setStatusMessage("failed to save");
		passportResponse.setNoOfRecords(count);
		return new ResponseEntity<Object>(passportResponse, HttpStatus.UNPROCESSABLE_ENTITY);
	}
		}
		  //UPDATE 
		if (passportRequestObject.getTransaactionType().equalsIgnoreCase(UPDATE)) {
			for(Passport passport : passportRequestObject.getPassportList()) {
			if (null != passport.getId()) {
				boolean updatePassport = passportDaoImpl.updatePassport(passportRequestObject);
				int count1 = passportDaoImpl.getcountPassport(passportRequestObject);
				logger.debug("@@@@@@@@@@@@@@received at service by calling the updatePassport is" + updatePassport);
				if (updatePassport) {
					logger.debug("inside  update condition.****** : ");
					passportResponse.setStatusCode("200");
					passportResponse.setStatusMessage("PassportCenter has updated successfully");
					passportResponse.setNoOfRecords(count1);
					return new ResponseEntity<Object>(passportResponse, HttpStatus.OK);
				}else {
					logger.debug("inside  update condition.****** : ");
					passportResponse.setStatusCode("422");
					passportResponse.setStatusMessage("failed to update");
					passportResponse.setNoOfRecords(count1);
					return new ResponseEntity<Object>(passportResponse, HttpStatus.UNPROCESSABLE_ENTITY);
				}
			} else {
				passportResponse.setStatusMessage("please provide the id");
				return new ResponseEntity<Object>(passportResponse, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			
		}
			}
		
		  //DELETE
		/*if (passportRequestObject.getTransaactionType().equalsIgnoreCase(DELETE)) {
			for(Passport passport : passportRequestObject.getPassportList() ) {
			if (null != passport.getId()) {
				boolean deletePassport = passportDaoImpl.deletePassport(passportRequestObject);
				int count1 = passportDaoImpl.getcountPassport(passportRequestObject);
				logger.debug("@@@@@@@@@@@@@received at service by calling the deletePassport is" + deletePassport);
				if (deletePassport) {
					logger.debug("inside  get_count condition.****** : ");
					passportResponse.setStatusCode("200");
					passportResponse.setStatusMessage("PassportCenter has  deleted successfully");
					passportResponse.setNoOfRecords(count1);
					return new ResponseEntity<Object>(passportResponse, HttpStatus.OK);
				}else {
					logger.debug("inside  get_count condition.****** : ");
					passportResponse.setStatusCode("200");
					passportResponse.setStatusMessage("PassportCenter has not  deleted ");
					passportResponse.setNoOfRecords(count1);
					return new ResponseEntity<Object>(passportResponse, HttpStatus.UNPROCESSABLE_ENTITY);
				}
				
				} else {
				passportResponse.setStatusCode("422");
				passportResponse.setStatusMessage("please provide the id");
				return new ResponseEntity<Object>(passportResponse, HttpStatus.UNPROCESSABLE_ENTITY);
				}
			}*/
		boolean b = (passportRequestObject.getTransaactionType().equalsIgnoreCase(SAVE)
				|| passportRequestObject.getTransaactionType().equalsIgnoreCase(UPDATE));
		if (!b) {
			passportResponse.setStatusCode("422");
			passportResponse.setStatusMessage("transaction type is not correct");
			return new ResponseEntity<Object>(passportResponse, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<Object>(passportResponse, HttpStatus.OK);
		}
	
	  //GETALL
	  
	@Override
	public ResponseEntity<Object> getPassport(PassportRequest passportRequestObject) throws SQLException {
		passportResponse = new PassportResponse();
		if (passportRequestObject.getTransaactionType().equalsIgnoreCase(GETALL)) {
			List<Passport> allPassportDetails = passportDaoImpl.getAll(passportRequestObject);
			int count = passportDaoImpl.getcountPassport(passportRequestObject);
			System.out.println("all method " + allPassportDetails);
			List<Passport> perPage = passportDaoImpl.getCountPerPage(allPassportDetails, passportRequestObject.getPageSize(),
					passportRequestObject.getPageNo());
			logger.debug("@@@@@@@@@@@@@received at service by calling the getAllmethod is" + allPassportDetails);
			
			
			if (allPassportDetails.isEmpty() || allPassportDetails == null) {
				passportResponse.setPassportList(allPassportDetails);
				passportResponse.setStatusCode("409");
				passportResponse.setStatusMessage("no records found");
				return new ResponseEntity<Object>(passportResponse, HttpStatus.CONFLICT);

			} else {
				if (passportRequestObject.getPageSize() == 0 || passportRequestObject.getPageNo() == 0) {
					passportResponse.setStatusCode("200");
					passportResponse.setStatusMessage("you got the list of PassportCenters successfully");
					passportResponse.setPassportList(allPassportDetails);
					passportResponse.setNoOfRecords(count);
					return new ResponseEntity<Object>(passportResponse, HttpStatus.OK);

				} else {
					passportResponse.setStatusCode("200");
					passportResponse.setStatusMessage("you got the list of PassportCenters successfully");
					passportResponse.setPassportList(perPage);
					passportResponse.setNoOfRecords(count);
					
				}
			}
				return new ResponseEntity<Object>(passportResponse, HttpStatus.OK);
			}
		 return new ResponseEntity<Object>(passportResponse, HttpStatus.OK);
	}}
		

