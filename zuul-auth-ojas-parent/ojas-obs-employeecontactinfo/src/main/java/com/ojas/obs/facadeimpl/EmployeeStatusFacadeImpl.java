package com.ojas.obs.facadeimpl;



import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ojas.obs.dao.EmployeeStatusDao;
import com.ojas.obs.facade.EmployeeStatusFacade;
import com.ojas.obs.model.EmployeeContactInfo;
import com.ojas.obs.requests.EmployeeContactInfoRequest;
import com.ojas.obs.response.EmployeeContactInfoResponse;
/**
 * 
 * @author ksaiKrishna
 *
 */
@Service
public class EmployeeStatusFacadeImpl implements EmployeeStatusFacade {

	Logger logger = Logger.getLogger(this.getClass());
	 
	
	@Autowired
	private EmployeeStatusDao empDao;

	
	 
	/*
	 * (non-Javadoc)
	 * @see com.ojas.obs.facade.EmployeeStatusFacade#setEmployeeContactInfo(com.ojas.obs.requests.EmployeeContactInfoRequest)
	 */
	@Override
	public EmployeeContactInfoResponse setEmployeeContactInfo(EmployeeContactInfoRequest empRequests) {
		EmployeeContactInfoResponse empContResponse = new EmployeeContactInfoResponse();
		logger.debug("@Transaction type is"+empRequests.getTransactionType());  
        boolean b=false;
		if (empRequests.getTransactionType().equalsIgnoreCase("save")) {
			
			
          for(EmployeeContactInfo empRequest : empRequests.getEmpInfo()) {
			if (((empRequest.getEmail() == null) || (empRequest.getEmail().equalsIgnoreCase("")))
					|| ((empRequest.getPersonal_mobileNo() == null) || (empRequest.getPersonal_mobileNo().length()!=10)
							|| (empRequest.getPersonal_mobileNo().equalsIgnoreCase("")))
					|| ((empRequest.getAlternate_mobileNo() == null) || (empRequest.getAlternate_mobileNo().length()!=10)
							|| (empRequest.getAlternate_mobileNo().equalsIgnoreCase(""))) 
					|| ((empRequest.getCurrent_Address_Line1() == null)
							|| (empRequest.getCurrent_Address_Line1().equalsIgnoreCase("")))
					|| ((empRequest.getCurrent_Address_Line2() == null)
							|| (empRequest.getCurrent_Address_Line2().equalsIgnoreCase("")))
					|| ((empRequest.getCurrent_City() == null) || (empRequest.getCurrent_City().equalsIgnoreCase("")))
					|| ((empRequest.getCurrent_State() == 0))
					|| ((empRequest.getCreated_By() == null) || (empRequest.getCreated_By().equalsIgnoreCase("")))||
					((empRequest.getEmp_Id() == null) || (empRequest.getEmp_Id().equalsIgnoreCase("")))
					|| ((empRequest.getPermanent_Address_Line_1() == null)
							|| (empRequest.getPermanent_Address_Line_1().equalsIgnoreCase("")))) {

				empContResponse.setStatusCode("422");
				empContResponse.setStatusMessage("Improper save data");
				b=true;
				break;
			}
          }
          if(b) {
        	  
          }
			else {
               logger.debug("@saving employee contact info");
				String empResponse = empDao.saveEmployeeContactInfo(empRequests);

				if (empResponse.equalsIgnoreCase("success")) { 

					//empContResponse.setEmpContact(empResponse);
					empContResponse.setStatusCode("200");
					empContResponse.setStatusMessage("employee contact info saved successfully");
				} else {
					empContResponse.setStatusCode("422");
					empContResponse.setStatusMessage("failed to save");
				}
 
			}
			return empContResponse;

		} else if (empRequests.getTransactionType().equalsIgnoreCase("update")) {
			
			
			for(EmployeeContactInfo empRequest : empRequests.getEmpInfo()) {
			if(((empRequest.getEmail() == null) || (empRequest.getEmail().equalsIgnoreCase("")))
					|| ((empRequest.getPersonal_mobileNo() == null) || (empRequest.getPersonal_mobileNo().length()!=10)
							|| (empRequest.getPersonal_mobileNo().equalsIgnoreCase("")))
					|| ((empRequest.getAlternate_mobileNo() == null) || (empRequest.getAlternate_mobileNo().length()!=10)
							|| (empRequest.getAlternate_mobileNo().equalsIgnoreCase("")))
					|| ((empRequest.getCurrent_Address_Line1() == null)
							|| (empRequest.getCurrent_Address_Line1().equalsIgnoreCase("")))
					|| ((empRequest.getCurrent_Address_Line2() == null)
							|| (empRequest.getCurrent_Address_Line2().equalsIgnoreCase("")))
					|| ((empRequest.getCurrent_City() == null) || (empRequest.getCurrent_City().equalsIgnoreCase("")))
					|| ((empRequest.getCurrent_State() == 0))
					|| ((empRequest.getUpdated_By() == null) || (empRequest.getUpdated_By().equalsIgnoreCase("")))
					|| (empRequest.getId() == 0)
					|| ((empRequest.getPermanent_Address_Line_1() == null)
							|| (empRequest.getPermanent_Address_Line_1().equalsIgnoreCase("")))) {
				empContResponse.setStatusCode("422");
				empContResponse.setStatusMessage("Improper update data");
				b=true;
				break;
				
			}
			}
			
			if(b) {
				
			}
			else {
			logger.debug("@Updating employee contact info(Facade");
				
			String empResponse = empDao.updateEmployeeContactInfo(empRequests);
			if (empResponse.equalsIgnoreCase("success")) {
				empContResponse.setStatusCode("200");
				empContResponse.setStatusMessage("employee contact info updated successfully");

			} else {
				empContResponse.setStatusCode("422");
				empContResponse.setStatusMessage("failed to update");
			}
			}
			return empContResponse;
			
		} else if (empRequests.getTransactionType().equalsIgnoreCase("delete")) {
			
			
			for(EmployeeContactInfo empRequest : empRequests.getEmpInfo()) {
			if(empRequest.getId() == 0 || ((empRequest.getUpdated_By() == null) || (empRequest.getUpdated_By().equalsIgnoreCase("")))) {
				empContResponse.setStatusCode("422");
				empContResponse.setStatusMessage("Improper delete data");
				b=true;
				break;
			}
			}
			if(b) {
				
			}
			
			else {
			
			logger.debug("@Deleting employee contact info(Facade)");
			String empResponse = empDao.deleteEmployeeContactInfo(empRequests);
			if (empResponse.equalsIgnoreCase("success")) {
				empContResponse.setStatusCode("200");
				empContResponse.setStatusMessage("employee contact info deleted successfully");

			} else {
				empContResponse.setStatusCode("422");
				empContResponse.setStatusMessage("failed to delete");
			}
			}
			return empContResponse;
		}else {
			empContResponse.setStatusCode("422");
			empContResponse.setStatusMessage("Please select proper Transactiontype");
			return empContResponse;
		}
		

	} 

	
	/*
	 * (non-Javadoc)
	 * @see com.ojas.obs.facade.EmployeeStatusFacade#getEmployeeContactInfo(com.ojas.obs.requests.EmployeeContactInfoRequest)
	 */
	@Override
	public List<EmployeeContactInfo> getEmployeeContactInfo(EmployeeContactInfoRequest empRequest) {

		//return empDao.showEmployeeContactInfo(empRequest);
          return empDao.getAll(empRequest);
	}
	
	/*
	 * (non-Javadoc) 
	 * @see com.ojas.obs.facade.EmployeeStatusFacade#showEmployeeContactInfo(com.ojas.obs.requests.EmployeeContactInfoRequest)
	 */

	@Override
	public EmployeeContactInfo showEmployeeContactInfo(EmployeeContactInfoRequest empRequest) {
		
		
		
		return empDao.showEmployeeContactInfo(empRequest);
	}


	@Override
	public EmployeeContactInfo showEmployeeContactInfoByEmpId(EmployeeContactInfoRequest empRequest) {
		// TODO Auto-generated method stub
		return empDao.showEmployeeContactInfoByEmpId(empRequest);
	}


	@Override
	public EmployeeContactInfo showEmployeeContactInfoById(EmployeeContactInfoRequest empRequest) {
		// TODO Auto-generated method stub
		return empDao.showEmployeeContactInfoById(empRequest);
	}
	
	
	

}
