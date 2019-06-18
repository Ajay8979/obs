package com.ojas.obs.dao;

import java.sql.SQLException;
import java.util.List;

import com.ojas.obs.model.BankDetails;
import com.ojas.obs.request.BankDetailsRequest;
/**
 * 
 * @author akrishna
 *
 */
public interface BankDetailsDAO {

	boolean saveEmployeeBankDetails(BankDetailsRequest employeeBankDetailsRequest);

	boolean updateEmployeeBankDetails(BankDetailsRequest employeeBankDetailsRequest);

	boolean deleteEmployeeBankDetails(BankDetailsRequest employeeBankDetailsRequest);

	List<BankDetails> getAllBankDetails(BankDetailsRequest bankDetailsRequest)throws SQLException;

}
