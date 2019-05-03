package com.ojas.obs.dao;

import static com.ojas.obs.utility.Constants.DELETE_KYE;
import static com.ojas.obs.utility.Constants.GETALL_KYE;
import static com.ojas.obs.utility.Constants.GETALL_KYE_COUNT;
import static com.ojas.obs.utility.Constants.SAVE_KYE;
import static com.ojas.obs.utility.Constants.UPDATE_KYE;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ojas.obs.model.KYE;
import com.ojas.obs.request.KYERequest;

/**
 * 
 * @author tshiva
 *
 */

@Repository
public class KyeDaoImpl implements KyeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	Logger logger = Logger.getLogger(this.getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.KyeDao#saveKYE(com.ojas.obs.request.KYERequest)
	 */
	@Override
	public boolean saveKYE(KYERequest kyeRequest) throws SQLException {
		logger.debug("@kyeRequest in KyeDaoImpl ::" + kyeRequest);
		List<KYE> kyeList = kyeRequest.getKye();
		boolean status = false;
		List<Object[]> inputList = new ArrayList<Object[]>();
		try {
			for (KYE kye : kyeList) {
				java.sql.Date issueDate = java.sql.Date.valueOf(kye.getPassport_date_of_Issue());
				java.sql.Date expireDate = java.sql.Date.valueOf(kye.getPassport_date_of_expiry());
				Object[] save = { kye.getkYE_Type(), kye.getUan(), kye.getkYE_address(), kye.getPassport_no(), issueDate,
						expireDate, kye.getPlace_of_issue(), kye.getPassport_address(), kye.getEmployee_Id(), false,
						kye.getCreated_by(), new Timestamp(new Date().getTime()) };
				inputList.add(save);

			}
			int[] batchUpdate = jdbcTemplate.batchUpdate(SAVE_KYE, inputList);
			if (batchUpdate.length > 0) {
				return true;
			}
			return status;
		}finally {
			if(jdbcTemplate!=null)
				jdbcTemplate.getDataSource().getConnection().close();
		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.KyeDao#updateKYE(com.ojas.obs.request.KYERequest)
	 */
	@Override
	public boolean updateKYE(KYERequest kyeRequest) throws SQLException {
		logger.debug("@kyeRequest in KyeDaoImpl ::" + kyeRequest);
		List<KYE> kyeList = kyeRequest.getKye();
		boolean status = false;
		List<Object[]> inputList = new ArrayList<Object[]>();
		try {
			for (KYE kye : kyeList) {
				java.sql.Date issueDate = java.sql.Date.valueOf(kye.getPassport_date_of_Issue());
				java.sql.Date expireDate = java.sql.Date.valueOf(kye.getPassport_date_of_expiry());

				Object[] update = { kye.getkYE_Type(), kye.getUan(), kye.getkYE_address(), kye.getPassport_no(), issueDate,
						expireDate, kye.getPlace_of_issue(), kye.getPassport_address(), false, kye.getUpdated_by(),
						new Timestamp(new Date().getTime()), kye.getId() };
				inputList.add(update);

			}
			int[] batchUpdate = jdbcTemplate.batchUpdate(UPDATE_KYE, inputList);
			if (batchUpdate.length > 0) {
				return true;
			}
			return status;
		}finally {
			if(jdbcTemplate!=null)
				jdbcTemplate.getDataSource().getConnection().close();
		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.KyeDao#deleteKYE(com.ojas.obs.request.KYERequest)
	 */

	@Override
	public boolean deleteKYE(KYERequest kyeRequest) throws SQLException {
		List<Object[]> inputList = new ArrayList<Object[]>();
		logger.debug("@kyeRequest in KyeDaoImpl ::" + kyeRequest);
		List<KYE> kyeList = kyeRequest.getKye();
		boolean status = false;
		try {
			for (KYE kye : kyeList) {
				/*
				 * kyeRequest.getTransactionType().equalsIgnoreCase(GETALL); List<KYE> allKYE =
				 * getAllKYE(kyeRequest); for (KYE kye1 : allKYE) { boolean flag =
				 * kye1.isFlag(); if ((kye.getId() == kye1.getId()) && (flag)) { return false; }
				 * }
				 */

				Object[] delete = { true, kye.getUpdated_by(), new Timestamp(new Date().getTime()), kye.getId() };
				inputList.add(delete);
			}

			int[] batchUpdate = jdbcTemplate.batchUpdate(DELETE_KYE, inputList);

			if (batchUpdate.length > 0) {
				return true;
			}
			return status;
		}finally {
			if(jdbcTemplate!=null)
				jdbcTemplate.getDataSource().getConnection().close();
		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.KyeDao#getAllKYE(com.ojas.obs.request.KYERequest)
	 */
	@Override
	public List<KYE> getAllKYE(KYERequest kyeRequest) throws SQLException {
		logger.debug("@kyeRequest in KyeDaoImpl ::" + kyeRequest);
		List<KYE> kyeList = kyeRequest.getKye();
		try {
			for (KYE kye : kyeList) {
				StringBuilder buffer = new StringBuilder();
				buffer.append(GETALL_KYE);
				if (kye.getId() != 0) {
					buffer.append(" and id = " + kye.getId());
				} else if (kye.getEmployee_Id()!=null) {
					buffer.append(" and employee_Id = " + kye.getEmployee_Id());
				}
				return jdbcTemplate.query(buffer.toString(), new BeanPropertyRowMapper<>(KYE.class));
			}
			return null;
		}finally {
			if(jdbcTemplate!=null)
				jdbcTemplate.getDataSource().getConnection().close();
		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.KyeDao#getAllKYECount()
	 */
	@Override
	public int getAllKYECount() throws SQLException {
		try {
			return jdbcTemplate.queryForObject(GETALL_KYE_COUNT, Integer.class);
		}finally {
				jdbcTemplate.getDataSource().getConnection().close();
		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.KyeDao#getCountPerPage(java.util.List, int, int)
	 */
	@Override
	public List<KYE> getCountPerPage(List<KYE> list, int pageSize, int pageNo) {
		List<KYE> getAllFiltered = new ArrayList<>();
		if (list != null && !list.isEmpty()) {
			pageSize = pageSize > 0 ? pageSize : pageSize * -1;
			pageNo = pageNo > 0 ? pageNo : pageNo == 0 ? 1 : pageNo * -1;
			if (pageSize != 0) {
				int endIndex = pageNo * pageSize;
				int startIndex = endIndex - pageSize;
				endIndex = endIndex < list.size() ? endIndex : list.size();
				startIndex = startIndex < list.size() ? startIndex : 0;
				getAllFiltered = list.subList(startIndex, endIndex);
			}
		}
		return getAllFiltered;
	}
}
