package com.ojas.obs.daoimpl;


import java.sql.Timestamp;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import static com.ojas.obs.constants.UserConstants.INSERTEMPLOYEECONTACTINFOSTMT;
import static com.ojas.obs.constants.UserConstants.DELETECONTACT;
import static com.ojas.obs.constants.UserConstants.SHOWCONTACTINFOSTMT;
import static com.ojas.obs.constants.UserConstants.SHOWCONTACTINFOSTMTEMPID;
import static com.ojas.obs.constants.UserConstants.TOTALRECORDS;
import static com.ojas.obs.constants.UserConstants.UPDATESTMT;

import com.ojas.obs.dao.EmployeeContactRowMapper;
import com.ojas.obs.dao.EmployeeStatusDao;
import com.ojas.obs.model.EmployeeContactInfo;
import com.ojas.obs.requests.EmployeeContactInfoRequest;
import com.ojas.obs.response.EmployeeContactInfoResponse;

/**
 *  
 * @author ksaiKrishna
 *
 */

@Repository
public class EmployeeStatusDaoImpl implements EmployeeStatusDao{
	
	Logger logger = Logger.getLogger(this.getClass()); 


	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	/* 
	 * (non-Javadoc)
	 * @see com.ojas.obs.dao.EmployeeStatusDao#saveEmployeeContactInfo(com.ojas.obs.requests.EmployeeContactInfoRequest)
	 */
	
	@Override
	public String saveEmployeeContactInfo(EmployeeContactInfoRequest empRequest) {
		
		
		logger.debug("@Saving employee contact info dao method");
		Timestamp sqlDate = new Timestamp(new java.util.Date().getTime());
		try {
	       List<Object[]> inputList = new ArrayList<Object[]>();
	       for(EmployeeContactInfo emp:empRequest.getEmpInfo()){
	           Object[] tmp = {
	        		   emp.getEmail(),
	        		   emp.getPersonal_mobileNo(),
	        		   emp.getAlternate_mobileNo(),
	        		   emp.getCurrent_Address_Line1(),
	        		   emp.getCurrent_Address_Line2(),
	        		   emp.getCurrent_City(),
	        		   emp.getCurrent_State(),
	        		   emp.getCurrent_Pin(),
	        		   emp.getPermanent_Address_Line_1(),
	        		   emp.getEmp_Id(),
	        		   emp.getCreated_By(),
	        		   sqlDate,
	        		   true 
	                 };
	           inputList.add(tmp);
	           
	       }
	       jdbcTemplate.batchUpdate(INSERTEMPLOYEECONTACTINFOSTMT, inputList); 
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	return "success";
	}

	/*
	 * (non-Javadoc)
	 * @see com.ojas.obs.dao.EmployeeStatusDao#showEmployeeContactInfo(com.ojas.obs.requests.EmployeeContactInfoRequest)
	 */
	
	@Override
	public EmployeeContactInfo showEmployeeContactInfo(EmployeeContactInfoRequest empRequest) {
		EmployeeContactInfo emp=null;
		logger.debug("@getting employee contact info based on his id");
		try {
		emp=jdbcTemplate.queryForObject(SHOWCONTACTINFOSTMT, new Object[] { empRequest.getEmpInfo().get(0).getId() }, new EmployeeContactRowMapper());
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return emp;
		
		 
	}
	
	
	

	/*
	 * (non-Javadoc)
	 * @see com.ojas.obs.dao.EmployeeStatusDao#updateEmployeeContactInfo(com.ojas.obs.requests.EmployeeContactInfoRequest)
	 */
	@Override
	public String updateEmployeeContactInfo(EmployeeContactInfoRequest empRequest) {
		
		logger.debug("@updating employee contact info");
		Timestamp sqlUpdateDate = new Timestamp(new java.util.Date().getTime());
		try {
		  List<Object[]> inputList = new ArrayList<Object[]>();
	       for(EmployeeContactInfo emp:empRequest.getEmpInfo()){
	           Object[] tmp = {
	        		   emp.getEmail(),
	        		   emp.getPersonal_mobileNo(),
	        		   emp.getAlternate_mobileNo(),
	        		   emp.getCurrent_Address_Line1(), 
	        		   emp.getCurrent_Address_Line2(),
	        		   emp.getCurrent_City(),
	        		   emp.getCurrent_State(),
	        		   emp.getCurrent_Pin(),
	        		   emp.getPermanent_Address_Line_1(),
	        		   emp.getUpdated_By(),
	        		   sqlUpdateDate,
	        		   emp.getId()
	                 };
	           inputList.add(tmp);
	           
	       } 
	       jdbcTemplate.batchUpdate(UPDATESTMT, inputList);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		

		return "success";
		
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.ojas.obs.dao.EmployeeStatusDao#deleteEmployeeContactInfo(com.ojas.obs.requests.EmployeeContactInfoRequest)
	 */
	@Override
	public String deleteEmployeeContactInfo(EmployeeContactInfoRequest empRequest) {
	
		logger.debug("@Deleting employee contact info");
		Timestamp sqlDeleteDate = new Timestamp(new java.util.Date().getTime());
		
		 List<Object[]> inputList = new ArrayList<Object[]>();
	       for(EmployeeContactInfo emp:empRequest.getEmpInfo()){
	           Object[] tmp = {
	        		 
	        		   false,
	        		   sqlDeleteDate,
	        		   emp.getUpdated_By(),
	        		   emp.getId()
	        		   
	        		   
	                 };
	           inputList.add(tmp);
	           
	       }
	       int[] res=jdbcTemplate.batchUpdate(DELETECONTACT, inputList);
		
	       Boolean b=true;
	       for(int i=0;i<res.length;i++) {
	    	   if(res[i]==1) {
	    		   
	    	   }else {
	    		   b=false;
	    		   break;
	    	   }
	    	   
	       }
	       if(b) {
	    	   return "success";
	       }else {
	    	   return "failed";
	       }
		 
		   
		
	
	}
 
	/*
	 * (non-Javadoc)
	 * @see com.ojas.obs.dao.EmployeeStatusDao#getAll(com.ojas.obs.requests.EmployeeContactInfoRequest)
	 */
	@Override
	public List<EmployeeContactInfo> getAll(EmployeeContactInfoRequest employeeContactInfo) {
		
		logger.debug("@Getting all employees contact info details");
		return  jdbcTemplate.query(TOTALRECORDS,new Object[] {true},new BeanPropertyRowMapper<EmployeeContactInfo>(EmployeeContactInfo.class));
	      
		
	}

	@Override
	public EmployeeContactInfo showEmployeeContactInfoByEmpId(EmployeeContactInfoRequest empRequest) {
		EmployeeContactInfo emp=null;
		logger.debug("@getting employee contact info based on his empid");
		try {
		emp=jdbcTemplate.queryForObject(SHOWCONTACTINFOSTMTEMPID, new Object[] { empRequest.getEmpInfo().get(0).getEmp_Id() }, new EmployeeContactRowMapper());
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return emp;
		
	}
	
	
}
