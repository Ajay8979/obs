package com.ojas.obs.controller;

import java.util.ArrayList;

import java.util.List;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.ojas.obs.constants.UserConstants.SET;
import static com.ojas.obs.constants.UserConstants.GET;

import com.ojas.obs.facade.EmployeeStatusFacade;
import com.ojas.obs.model.EmployeeContactInfo;
import com.ojas.obs.requests.EmployeeContactInfoRequest;
import com.ojas.obs.response.EmployeeContactInfoResponse;
import com.ojas.obs.response.ErrorResponse;


/**
 * 
 * @author ksaiKrishna
 *  
 */

@RestController
//@RequestMapping("/employeecontactinfo")
public class EmployeeContactInfoController {

	@Autowired
	private EmployeeStatusFacade empFacade;

	Logger logger = Logger.getLogger(this.getClass());
	 
	//service to save,update or delete employeeContactInfo
	@PostMapping(SET)
	public ResponseEntity<Object> setEmployeeContactInfo(@RequestBody EmployeeContactInfoRequest employeeContacts,
			HttpServletRequest request, HttpServletResponse response) {

	    if(employeeContacts == null) {
	    	logger.debug("@Request Object is null");
			ErrorResponse err = new ErrorResponse();
			err.setStatusMessage("Request object is null");
			err.setStatusCode("422");
			return new ResponseEntity<Object>(err, HttpStatus.UNAUTHORIZED);
	    }
	    else if (employeeContacts.getTransactionType() == null || employeeContacts.getTransactionType().equalsIgnoreCase("")) {
			logger.debug("@Transaction Type is null while save, update or delete");
			ErrorResponse err = new ErrorResponse();
			err.setStatusMessage("Transaction Type is null");
			err.setStatusCode("422");
			return new ResponseEntity<Object>(err, HttpStatus.UNAUTHORIZED);
		} else {
			
               
			EmployeeContactInfoResponse empInfo = empFacade.setEmployeeContactInfo(employeeContacts);

			return new ResponseEntity<Object>(empInfo, HttpStatus.OK);
		}

	}

	
	//Service to get EmployeeContactInfo
	@PostMapping(GET)
	public ResponseEntity<Object> getEmployeeContactInfo(@RequestBody EmployeeContactInfoRequest empRequest,
			HttpServletRequest request, HttpServletResponse response) {
         
		if(empRequest == null) {
			logger.debug("@Request is null");
			ErrorResponse err = new ErrorResponse();
			err.setStatusMessage("Request is null"); 
			err.setStatusCode("422");
			return new ResponseEntity<Object>(err, HttpStatus.UNAUTHORIZED);
		}else {
	
		 if(empRequest.getTransactionType().equalsIgnoreCase("getAll") && empRequest.getTransactionType()!= null) {
					logger.debug("@Transaction Type is :"+empRequest.getTransactionType());
				 logger.debug("@block to get all employees contact info details");
 
				 if(empRequest.getEmpInfo() == null) {
				List<EmployeeContactInfo> empInfo = empFacade.getEmployeeContactInfo(empRequest);
                
				EmployeeContactInfoResponse resp=new EmployeeContactInfoResponse();
				resp.setEmpContacts(empInfo); 
				resp.setStatusCode("200");
				resp.setStatusMessage("success"); 
				 
				 
				return new ResponseEntity<Object>(resp,HttpStatus.OK);
				 }
				 else if(empRequest.getEmpInfo().get(0).getId() >0) {
					    
						logger.debug("@block to get particular employee contact info based on his id");
						EmployeeContactInfo emp=empFacade.showEmployeeContactInfo(empRequest);
						if(emp!=null) { 
						   EmployeeContactInfoResponse resp=new EmployeeContactInfoResponse();
						   List empList=new ArrayList();
						   empList.add(emp);
						   resp.setEmpContacts(empList);
						   
						   resp.setStatusCode("200");
						   resp.setStatusMessage("success");
						   return new ResponseEntity<Object>(resp,HttpStatus.OK);
						}else {
							EmployeeContactInfoResponse resp=new EmployeeContactInfoResponse(); 
							resp.setStatusCode("422");
							resp.setStatusMessage("No data");
							return new ResponseEntity<Object>(resp,HttpStatus.OK);
						}
					  
				 }
				 else if(empRequest.getEmpInfo().get(0).getEmp_Id() !=null) {
					    
						logger.debug("@block to get particular employee contact info based on his empid");
						EmployeeContactInfo emp=empFacade.showEmployeeContactInfoByEmpId(empRequest);
						if(emp!=null) { 
						   EmployeeContactInfoResponse resp=new EmployeeContactInfoResponse();
						   List empList=new ArrayList();
						   empList.add(emp);
						   resp.setEmpContacts(empList);
						   
						   resp.setStatusCode("200");
						   resp.setStatusMessage("success");
						   return new ResponseEntity<Object>(resp,HttpStatus.OK);
						}else {
							EmployeeContactInfoResponse resp=new EmployeeContactInfoResponse(); 
							resp.setStatusCode("422");
							resp.setStatusMessage("No data");
							return new ResponseEntity<Object>(resp,HttpStatus.OK);
						}
					 
				 }
				 else if(empRequest.getEmpInfo().get(0).getId() !=0) {
					    
						logger.debug("@block to get particular employee contact info based on his empid");
						EmployeeContactInfo emp=empFacade.showEmployeeContactInfoById(empRequest);
						if(emp!=null) { 
						   EmployeeContactInfoResponse resp=new EmployeeContactInfoResponse();
						   List empList=new ArrayList();
						   empList.add(emp);
						   resp.setEmpContacts(empList);
						   
						   resp.setStatusCode("200");
						   resp.setStatusMessage("success");
						   return new ResponseEntity<Object>(resp,HttpStatus.OK);
						}else {
							EmployeeContactInfoResponse resp=new EmployeeContactInfoResponse(); 
							resp.setStatusCode("422");
							resp.setStatusMessage("No data");
							return new ResponseEntity<Object>(resp,HttpStatus.OK);
						}
					 
				 }
				 
				 else {
					 EmployeeContactInfoResponse resp=new EmployeeContactInfoResponse();
						resp.setStatusCode("422");
						resp.setStatusMessage("No data");
						return new ResponseEntity<Object>(resp,HttpStatus.NO_CONTENT);
				 }
				 
		 }
				 else {
						logger.debug("@Transaction Type is null while getting data");
						ErrorResponse err = new ErrorResponse();
						err.setStatusMessage("Transaction Type is null");
						err.setStatusCode("422");
						return new ResponseEntity<Object>(err, HttpStatus.UNAUTHORIZED); 
					}
		
		 
		 
	}}
}
	

