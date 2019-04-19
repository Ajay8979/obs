package com.ojas.obs.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import org.apache.log4j.Logger;


import com.ojas.obs.model.SeparationType;
import com.ojas.obs.request.SeparationTypeRequest;
//import com.ojas.obs.request.SeparationTypeRequest;

/**
 * 
 * @author nsrikanth 
 *
 */ 

@Repository
public class SeparationTypeDaoImpl implements SeparationTypeDao  {  
	
	@Autowired 
	private JdbcTemplate jdbc;
	Logger logger = Logger.getLogger(this.getClass());
	
	public static final String SAVESeparationTtype = "Insert into obs_separationtype(separationType) values(?)";
	public static final String UPDATESeparationTtype = "Update obs_separationtype set separationType =? where separationTypeId =? ";
	public static final String DELETESeparationTtype = "Delete from obs_separationtype where separationTypeId =?";
	public static final String SELECTSeparationTtype = "Select * from obs_separationtype";
	//String DESIGNATIONCOUNTDesignation = "Select count(*) from obs_designation";

/*
 * (non-Javadoc)
 * @see com.ojas.obs.dao.SeparationTypeDao#saveSeparationType(com.ojas.obs.model.SeparationType)
 */
	@Override
	public boolean saveSeparationType(SeparationTypeRequest separationTypeReq) throws SQLException { 
		try {

			List<Object[]> inputList = new ArrayList<Object[]>();

			for (SeparationType separationType : separationTypeReq.getSeparationType()) {

				logger.debug("Inside save SeparationType..getConnection()");
				Object[] obj = new Object[] { separationType.getSeparationType() };

				inputList.add(obj);

				/*
				 * if (n > 0) { return true; } return false;
				 */
			}
			int[] batchUpdate = jdbc.batchUpdate(SAVESeparationTtype, inputList);
			if (batchUpdate.length > 0) { 
				return true;
			}
			return false;
		}

		catch (Exception e) {
			logger.debug("Inside saveSeparationType..DAO catch block" + e.getMessage());
		}
		return false;
		
	}
	
	
	
	
	
	
		/*int save= 0;
		
		List<SeparationType> separationType = separationTypeReq.getSeparationType();
		for(SeparationType separationTypeObj :separationType) {
		 save = jdbc.update(SAVESeparationTtype, separationTypeObj.getSeparationType());
		 
		}
		if (save > 0)
			return true;
		else
			return false;
	}*/
	
	
	
	
	
	
	
	/*
	 * (non-Javadoc)
	 * @see com.ojas.obs.dao.SeparationTypeDao#updateSeparationType(com.ojas.obs.model.SeparationType)
	 */

	@Override
	public boolean updateSeparationType(SeparationTypeRequest separationTypeRequest) throws SQLException {
		
		List<Object[]> inputList = new ArrayList<Object[]>();
		logger.debug("Inside updateSeparationType..DAO *****");
		try {

			List<SeparationType> separationType = separationTypeRequest.getSeparationType();

			for (SeparationType plan : separationType) {

				Object[] obj = new Object[] { plan.getSeparationType(), plan.getSeparationTypeId() };
				/*
				 * if (n > 0) return true; else return false;
				 */

				inputList.add(obj);

			}
			int[] batchUpdate = jdbc.batchUpdate(UPDATESeparationTtype, inputList);

			if (batchUpdate.length > 0) {
				return true;
			}
			return false;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
		
		
		
		
	}
		
		/*
		 * int up = 0; List<SeparationType> separationType2 =
		 * separationType.getSeparationType(); for (SeparationType
		 * separationTypeObj:separationType2 ) { up =
		 * jdbc.update(UPDATESeparationTtype,separationTypeObj.getSeparationType(),
		 * separationTypeObj.getSeparationTypeId()); } if (up > 0) return true; else
		 * return false;
		 */
	
	
	 
	
	/*
	 * (non-Javadoc)
	 * @see com.ojas.obs.dao.SeparationTypeDao#deleteSeparationType(int)
	 */
	/*
	 * @Override public boolean deleteSeparationType(int separationTypeId) throws
	 * SQLException {
	 * 
	 * int d = jdbc.update(DELETESeparationTtype,separationTypeId); if (d > 0)
	 * return true; else return false; }
	 */
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * (non-Javadoc)
	 * @see com.ojas.obs.dao.SeparationTypeDao#getAllSeparationType()
	 */

	@Override
	public List<SeparationType> getAllSeparationType() throws SQLException {
		
		logger.debug("Inside getAllSeparationTypeDetails DAO .***");

		List<SeparationType> separationType = jdbc.query(SELECTSeparationTtype, new BeanPropertyRowMapper<>(SeparationType.class));

		return separationType;

		/*
		 * List<SeparationType> query = jdbc.query(SELECTSeparationTtype, new
		 * BeanPropertyRowMapper<SeparationType>(SeparationType.class));
		 * System.out.println("data.****" + query); return query;
		 */
	}
	
	

}
