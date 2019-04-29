package com.ojas.obs.constants;
/**
 * 
 * @author akrishna
 *
 */
public class UserConstants {

	public static final String SAVE = "save";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";
	public static final String FAILED = "Failed";
	public static final String NORECORDS ="No Records Found";
	public static final String SUCCESS = "Success";
	public static final String SET = "/set";
	public static final String GET = "/getAll";
	public static final String GETALL = "/getAll";
	/* public static final String BANKDETAILS ="/obs/BankDetails"; */
	
	//--------------------Bank Details------------------//
	public static final String SAVEBANKDETAILS = "INSERT INTO obs_bankdetails(bank_account_no,bank_name,bank_city,bank_branch,bank_ifsc_code,bank_account_status,employee_id,Is_active,created_by,created_date,flag) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	public static final String DELETEBANKDETAILS = "update obs_BankDetails set flag = ? where id = ?";
	public static final String UPDATEBANKDETAILS = "Update obs_BankDetails set bank_account_no=?,bank_name=?, bank_city=?,bank_branch=?, bank_ifsc_code=?, bank_account_status=?, Is_active=?,updated_by=?,updated_date=?,flag=? where employee_id = ? ";
	public static final String RECORDSCOUNT = "select count(*) from obs_BankDetails";
	public static final String ALLRECORDS = "select * from obs_BankDetails where flag !=false ";
	public static final String GETBYID = "select * from obs_bankdetails where id  = ?";
}
