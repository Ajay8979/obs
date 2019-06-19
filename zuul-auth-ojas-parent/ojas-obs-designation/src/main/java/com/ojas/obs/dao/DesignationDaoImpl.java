package com.ojas.obs.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ojas.obs.model.Designation;
import com.ojas.obs.request.DesignationRequest;

/**
 * 
 * @author nsrikanth
 * 
 */
@Repository
public class DesignationDaoImpl implements DesignationDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	Logger logger = Logger.getLogger(this.getClass());

	public static final String SAVEDesignation = "Insert into obs_designation(designation) values(?)";
	public static final String UPDATEDesignation = "Update obs_designation set designation =? where id =? ";
	public static final String DELETEDesignation = "Delete from obs_designation where id =?";
	public static final String SELECTDesignation = "Select * from obs_designation ORDER BY id asc";
	public static final String DESIGNATIONCOUNTDesignation = "Select count(*) from obs_designation";
	public static final String getbyid = "Select * from obs_designation where id = ";
	
	@Override
	public boolean saveDesignation(DesignationRequest designationRequest) throws SQLException {
		logger.info("The requests inside save in DesignationDaoImpl " + designationRequest);

		List<Object[]> inputList = new ArrayList<Object[]>();

		for (Designation designation : designationRequest.getDesignation()) {

			logger.debug("Inside saveforecah in DesignationDaoImpl " +designationRequest);
			Object[] obj = new Object[] { designation.getDesignation() };

			inputList.add(obj);

		}
		int[] batchUpdate = jdbcTemplate.batchUpdate(SAVEDesignation, inputList);
		if (batchUpdate.length > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateDesignation(DesignationRequest designationRequest) throws SQLException {

		List<Object[]> inputList = new ArrayList<Object[]>();
		logger.info("The requests inside update in DesignationDaoImpl " + designationRequest);

		List<Designation> designation = designationRequest.getDesignation();

		for (Designation plan : designation) {
			logger.debug("Inside updateforecah in DesignationDaoImpl " +designationRequest);

			Object[] obj = new Object[] { plan.getDesignation(), plan.getId() };

			inputList.add(obj);

		}
		int[] batchUpdate = jdbcTemplate.batchUpdate(UPDATEDesignation, inputList);

		if (batchUpdate.length > 0) {
			return true;
		}
		return false;

	}

	@Override
	public int getAllDesignationCount() throws SQLException {
		logger.info("The requests inside getAllDesignationCount in DesignationDaoImpl ");

		return jdbcTemplate.queryForObject(DESIGNATIONCOUNTDesignation, Integer.class);

	}

	@Override
	public List<Designation> getAll(DesignationRequest designationRequest) throws SQLException {
		logger.info("The requests inside getAll in DesignationDaoImpl " + designationRequest);

		logger.debug("Inside getAllDesignationDetails DAO  " + designationRequest);

		List<Designation> designation = jdbcTemplate.query(SELECTDesignation,
				new BeanPropertyRowMapper<>(Designation.class));

		return designation;
	}

	public List<Designation> getById(DesignationRequest designationRequest) throws SQLException {
		logger.info("The requests inside getAll in DesignationDaoImpl " + designationRequest);

		List<Designation> designationList = new ArrayList<>();
		List<Designation> insuranceList = designationRequest.getDesignation();
		for (Designation designation : insuranceList) {
			List<Designation> query = jdbcTemplate.query(getbyid + designation.getId(),
					new BeanPropertyRowMapper<>(Designation.class));
			designationList.addAll(query);
		}
		return designationList;

	}

}
