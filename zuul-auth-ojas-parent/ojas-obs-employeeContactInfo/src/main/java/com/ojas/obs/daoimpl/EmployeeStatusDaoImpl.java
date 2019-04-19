package com.ojas.obs.daoimpl;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

	public static final String INSERTEMPLOYEECONTACTINFOSTMT = "INSERT INTO EmployeeContactInfo(email,personal_mobileNo,alternate_mobileNo,current_Address_line1,current_Address_line2, current_City,current_State,current_Pin,permanent_Address_Line_1,emp_Id,created_By,created_date,flag) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String SHOWCONTACTINFOSTMT = "SELECT * FROM EmployeeContactInfo WHERE id=?";
	public static final String SHOWCONTACTINFOSTMTEMPID = "SELECT * FROM EmployeeContactInfo WHERE emp_Id=?";
	public static final String UPDATESTMT = "UPDATE EmployeeContactInfo set email = ? ,personal_mobileNo = ?,alternate_mobileNo = ?,current_Address_line1 = ?,current_Address_line2 = ?, current_City = ?,current_State = ?,current_Pin = ?,permanent_Address_Line_1 = ?,emp_Id = ?,updated_By = ?,updated_date = ? WHERE id = ? ";
	public static final String DELETECONTACT = "UPDATE EmployeeContactInfo set flag = ?,updated_date = ?,updated_By = ? where id = ?";
	public static final String TOTALRECORDS = "select * from EmployeeContactInfo where flag=?";
	
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
	   
		
		
		
		
		
		/*int res=jdbcTemplate.update(INSERTEMPLOYEECONTACTINFOSTMT,empRequest.getEmpInfo().getEmail(),empRequest.getEmpInfo().getPersonal_mobileNo(),
				empRequest.getEmpInfo().getPersonal_mobileNo(),empRequest.getEmpInfo().getCurrent_Address_Line1(),
				empRequest.getEmpInfo().getCurrent_Address_Line2(),empRequest.getEmpInfo().getCurrent_City(),empRequest.getEmpInfo().getCurrent_State(),
				empRequest.getEmpInfo().getCurrent_Pin(),empRequest.getEmpInfo().getPermanent_Address_Line_1(),empRequest.getEmpInfo().getEmp_Id(),
				empRequest.getEmpInfo().getCreated_By(),sqlDate,true);
		
		if(res>0) {
			EmployeeContactInfo empRes=new EmployeeContactInfo();
			
			empRes.setEmail(empRequest.getEmpInfo().getEmail());
			empRes.setPersonal_mobileNo(empRequest.getEmpInfo().getPersonal_mobileNo());
			empRes.setAlternate_mobileNo(empRequest.getEmpInfo().getAlternate_mobileNo());
			empRes.setCurrent_Address_Line1(empRequest.getEmpInfo().getCurrent_Address_Line1());
			empRes.setCurrent_Address_Line2(empRequest.getEmpInfo().getCurrent_Address_Line2());
			empRes.setCurrent_City(empRequest.getEmpInfo().getCurrent_City());
			empRes.setCurrent_State(empRequest.getEmpInfo().getCurrent_State());
			empRes.setCurrent_Pin(empRequest.getEmpInfo().getCurrent_Pin());
			empRes.setPermanent_Address_Line_1(empRequest.getEmpInfo().getPermanent_Address_Line_1());
			empRes.setEmp_Id(empRequest.getEmpInfo().getEmp_Id());
			
			return empRes;
			
		}else {
			return null;
		}*/
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
	        		   emp.getUpdated_By(),
	        		   sqlUpdateDate,
	        		   emp.getId()
	                 };
	           inputList.add(tmp);
	           
	       } 
	       jdbcTemplate.batchUpdate(UPDATESTMT, inputList);
		
		
		
		
		/*
		int res=jdbcTemplate.update(UPDATESTMT,empRequest.getEmpInfo().getEmail(),empRequest.getEmpInfo().getPersonal_mobileNo(),
		empRequest.getEmpInfo().getAlternate_mobileNo(),empRequest.getEmpInfo().getCurrent_Address_Line1(),
		empRequest.getEmpInfo().getCurrent_Address_Line2(),empRequest.getEmpInfo().getCurrent_City(),empRequest.getEmpInfo().getCurrent_State(),
		empRequest.getEmpInfo().getCurrent_Pin(),empRequest.getEmpInfo().getPermanent_Address_Line_1(),empRequest.getEmpInfo().getUpdated_By(),sqlUpdateDate,empRequest.getEmpInfo().getId());
		*/
		
		/*int res=jdbcTemplate.update(UPDATESTMT,empRequest.getEmail(),empRequest.getPersonal_mobileNo(),
				empRequest.getAlternate_mobileNo(),empRequest.getCurrent_Address_Line1(),
				empRequest.getCurrent_Address_Line2(),empRequest.getCurrent_City(),empRequest.getCurrent_State(),
				empRequest.getCurrent_Pin(),empRequest.getPermanent_Address_Line_1(),empRequest.getEmp_Id(),
				empRequest.getCreated_By(),empRequest.getUpdated_By(),empRequest.getCreated_date(),
				empRequest.getUpdated_date()); */
		
		/*
		if(res>0) {
			EmployeeContactInfo empRes=new EmployeeContactInfo();
			
			empRes.setEmail(empRequest.getEmpInfo().getEmail());
			empRes.setPersonal_mobileNo(empRequest.getEmpInfo().getPersonal_mobileNo());
			empRes.setAlternate_mobileNo(empRequest.getEmpInfo().getAlternate_mobileNo());
			empRes.setCurrent_Address_Line1(empRequest.getEmpInfo().getCurrent_Address_Line1());
			empRes.setCurrent_Address_Line2(empRequest.getEmpInfo().getCurrent_Address_Line2());
			empRes.setCurrent_City(empRequest.getEmpInfo().getCurrent_City());
			empRes.setCurrent_State(empRequest.getEmpInfo().getCurrent_State());
			empRes.setCurrent_Pin(empRequest.getEmpInfo().getCurrent_Pin());
			empRes.setPermanent_Address_Line_1(empRequest.getEmpInfo().getPermanent_Address_Line_1());
			empRes.setEmp_Id(empRequest.getEmpInfo().getEmp_Id());
			
			return empRes;
			
		}else {
			return null;
		}
		*/
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
		 
		   
		
		
		/*
		int res=jdbcTemplate.update(DELETECONTACT,false,sqlDeleteDate,empRequest.getEmpInfo().getUpdated_By(),empRequest.getEmpInfo().getId());
		if(res>0) {
			EmployeeContactInfo empRes=new EmployeeContactInfo();
			
			empRes.setEmail(empRequest.getEmpInfo().getEmail());
			empRes.setPersonal_mobileNo(empRequest.getEmpInfo().getPersonal_mobileNo());
			empRes.setAlternate_mobileNo(empRequest.getEmpInfo().getAlternate_mobileNo());
			empRes.setCurrent_Address_Line1(empRequest.getEmpInfo().getCurrent_Address_Line1());
			empRes.setCurrent_Address_Line2(empRequest.getEmpInfo().getCurrent_Address_Line2());
			empRes.setCurrent_City(empRequest.getEmpInfo().getCurrent_City());
			empRes.setCurrent_State(empRequest.getEmpInfo().getCurrent_State());
			empRes.setCurrent_Pin(empRequest.getEmpInfo().getCurrent_Pin());
			empRes.setPermanent_Address_Line_1(empRequest.getEmpInfo().getPermanent_Address_Line_1());
			empRes.setEmp_Id(empRequest.getEmpInfo().getEmp_Id());
			
			return empRes;
			
		}else {
			return null;
		}*/
	       
		//return "success";
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
