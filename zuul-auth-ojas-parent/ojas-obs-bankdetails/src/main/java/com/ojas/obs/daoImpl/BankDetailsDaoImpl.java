 package com.ojas.obs.daoImpl;

import static com.ojas.obs.constants.UserConstants.ALLRECORDS;
import static com.ojas.obs.constants.UserConstants.DELETEBANKDETAILS;
import static com.ojas.obs.constants.UserConstants.RECORDSCOUNT;
import static com.ojas.obs.constants.UserConstants.SAVEBANKDETAILS;
import static com.ojas.obs.constants.UserConstants.UPDATEBANKDETAILS;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ojas.obs.dao.BankDetailsDAO;
import com.ojas.obs.model.BankDetails;
import com.ojas.obs.request.BankDetailsRequest;

/**
 * 
 * @author akrishna
 *
 */
@Repository
public class BankDetailsDaoImpl implements BankDetailsDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ojas.obs.dao.BankDetailsDAO#saveEmployeeBankDetails(com.ojas.obs.request.
	 * BankDetailsRequest)
	 */
	@Override
	public boolean saveEmployeeBankDetails(BankDetailsRequest bankDetailsRequest) {
		Timestamp timestamp = new Timestamp(new Date().getTime());
		List<BankDetails> bankDetails = bankDetailsRequest.getBankDetails();
		List<Object[]> list = new ArrayList<Object[]>();

		for (BankDetails employeeBankDetails : bankDetails) {
			employeeBankDetails.setFlag(true);
			Object[] object = { employeeBankDetails.getBank_account_no(), employeeBankDetails.getBank_name(),
					employeeBankDetails.getBank_city(), employeeBankDetails.getBank_branch(),
					employeeBankDetails.getBank_ifsc_code(), employeeBankDetails.getBank_account_status(),
					employeeBankDetails.getEmployee_id(), employeeBankDetails.isIs_active(),
					employeeBankDetails.getCreated_by(), timestamp, employeeBankDetails.isFlag() };

			list.add(object);
		}
		int[] batchsave = jdbcTemplate.batchUpdate(SAVEBANKDETAILS, list);
		if (batchsave.length > 0) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.BankDetailsDAO#updateEmployeeBankDetails(com.ojas.obs.
	 * request.BankDetailsRequest)
	 */
	@Override
	public boolean updateEmployeeBankDetails(BankDetailsRequest bankDetailsRequest) {
		Timestamp timestamp = new Timestamp(new Date().getTime());
		System.out.println("timestamp update****** " + timestamp);
		List<BankDetails> bankDetails = bankDetailsRequest.getBankDetails();
		List<Object[]> list = new ArrayList<Object[]>();
		for (BankDetails employeeBankDetails : bankDetails) {
			employeeBankDetails.setFlag(true);
			Object[] object = { employeeBankDetails.getBank_account_no(), employeeBankDetails.getBank_name(),
					employeeBankDetails.getBank_city(), employeeBankDetails.getBank_branch(),
					employeeBankDetails.getBank_ifsc_code(), employeeBankDetails.getBank_account_status(),
					employeeBankDetails.isIs_active(), employeeBankDetails.getUpdated_by(), timestamp,
					employeeBankDetails.isFlag(), employeeBankDetails.getEmployee_id() };
			list.add(object);
		}
		int[] batchUpdate = jdbcTemplate.batchUpdate(UPDATEBANKDETAILS, list);
		if (batchUpdate.length > 0) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.BankDetailsDAO#deleteEmployeeBankDetails(int)
	 */
	@Override
	public boolean deleteEmployeeBankDetails(BankDetailsRequest bankDetailsRequest) {
		int delete = 0;
		List<BankDetails> bankDetails = bankDetailsRequest.getBankDetails();
		boolean flag = false;
		for (BankDetails employeeBankDetails : bankDetails) {
			delete = jdbcTemplate.update(DELETEBANKDETAILS, flag, employeeBankDetails.getId());
		}
		if (delete > 0) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.BankDetailsDAO#getAllBankDetails()
	 */
	@Override
	public List<BankDetails> getAllBankDetails(BankDetailsRequest bankDetailsRequest) throws SQLException {

		List<BankDetails> bankObjects = jdbcTemplate.query(ALLRECORDS,
				new BeanPropertyRowMapper<BankDetails>(BankDetails.class));
		List<BankDetails> listObjects = new ArrayList<BankDetails>();
		if (null != bankObjects || !bankObjects.isEmpty()) {
			for (BankDetails bankDetails2 : bankObjects) {
				List<BankDetails> bankDetails = bankDetailsRequest.getBankDetails();
				for (BankDetails bankDetails3 : bankDetails) {
					if (bankDetails2.getId() == bankDetails3.getId()) {
						listObjects.add(bankDetails2);
						return listObjects;
					}
				}
			}
		}
		return bankObjects;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.BankDetailsDAO#getAllEmployeeBankDetailsCount()
	 */
	@Override
	public int getAllEmployeeBankDetailsCount() {
		Integer queryForObject = jdbcTemplate.queryForObject(RECORDSCOUNT, Integer.class);
		return queryForObject;
	}

}
