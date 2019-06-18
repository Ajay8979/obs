package com.ojas.obs.daoimpl;

import static com.ojas.obs.constants.UserConstants.COUNTRECORDS;
import static com.ojas.obs.constants.UserConstants.DELETEEDUCATION;
import static com.ojas.obs.constants.UserConstants.INSERTEMPLOYEEEDUCATIONINFOSTMT;
import static com.ojas.obs.constants.UserConstants.TOTALRECORDS;
import static com.ojas.obs.constants.UserConstants.UPDATESTMT;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ojas.obs.dao.EmployeeEducationDao;
import com.ojas.obs.model.EmployeeEducation;
import com.ojas.obs.modelrequest.EmployeeEducationRequest;

/**
 * 
 * @author mpraneethguptha
 *
 */
@Repository
public class EmployeeEducationDaoImpl implements EmployeeEducationDao {

	private static final Logger log = Logger.getLogger(EmployeeEducationDaoImpl.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ojas.obs.dao.EmployeeEducationDao#saveEmployeeEducation(com.ojas.obs.
	 * modelrequest.EmployeeEducationRequest)
	 */

	@Override
	public boolean saveEmployeeEducation(EmployeeEducationRequest employeeEducationRequest) throws SQLException {
		log.info("The request inside save dao" + employeeEducationRequest);
		List<EmployeeEducation> employeeEducations = employeeEducationRequest.getListEmployeeEducations();
		int[] batchSave = null;
		List<Object[]> input = new ArrayList<Object[]>();
		for (EmployeeEducation employeeEducation : employeeEducations) {
			Object[] saveList = { employeeEducation.getEducationType() };
			input.add(saveList);
		}
	
			batchSave = jdbcTemplate.batchUpdate(INSERTEMPLOYEEEDUCATIONINFOSTMT, input);

			if (batchSave.length > 0)
				return true;
			else
				return false;
		

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ojas.obs.dao.EmployeeEducationDao#updateEmployeeEducation(com.ojas.obs.
	 * modelrequest.EmployeeEducationRequest)
	 */
	@Override
	public boolean updateEmployeeEducation(EmployeeEducationRequest employeeEducationRequest) throws SQLException {
		log.info("&&&&&&& the request inside update dao" + employeeEducationRequest);
		List<EmployeeEducation> employeeEducations = employeeEducationRequest.getListEmployeeEducations();
		int[] batchSave = null;
		List<Object[]> input = new ArrayList<Object[]>();
		for (EmployeeEducation employeeEducation : employeeEducations) {
			Object[] saveList = { employeeEducation.getEducationType(), employeeEducation.getId() };
			input.add(saveList);
		}
		batchSave = jdbcTemplate.batchUpdate(UPDATESTMT, input);
	
			if (batchSave.length > 0)
				return true;
			else
				return false;
		

	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.EmployeeEducationDao#deleteEmployeeEducation(int)
	 */

	@Override
	public boolean deleteEmployeeEducation(int id) throws SQLException {

			int delete = jdbcTemplate.update(DELETEEDUCATION, id);

			if (delete > 0)
				return true;
			else
				return false;
		

	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ojas.obs.dao.EmployeeEducationDao#getAllEmployeeEducation(com.ojas.obs.
	 * modelrequest.EmployeeEducationRequest)
	 */

	/*
	 * @Override public List<EmployeeEducation>
	 * getAllEmployeeEducation(EmployeeEducationRequest employeeEducationRequest)
	 * throws SQLException { log.info("The request inside getall dao" +
	 * employeeEducationRequest); try { return jdbcTemplate.query(TOTALRECORDS, new
	 * BeanPropertyRowMapper<EmployeeEducation>(EmployeeEducation.class)); } finally
	 * { if (jdbcTemplate != null) { try {
	 * jdbcTemplate.getDataSource().getConnection().close(); } catch (Exception e) {
	 * e.printStackTrace(); log.error("The exception raised in getAll catch"); } }
	 * 
	 * }
	 * 
	 * }
	 */
	@Override
	public List<EmployeeEducation> getAllEmployeeEducation(EmployeeEducationRequest employeeEducationRequest) {
		log.info("the request  inside the daoimpl " + employeeEducationRequest);
		List<EmployeeEducation> query = null;
		StringBuilder builder = new StringBuilder();
		builder.append(TOTALRECORDS);
			List<EmployeeEducation> listEmployeeEducations = employeeEducationRequest.getListEmployeeEducations();
if(null != employeeEducationRequest.getListEmployeeEducations().get(0) && null != employeeEducationRequest.getListEmployeeEducations().get(0).getId()) {
	for (EmployeeEducation empedu : listEmployeeEducations) {
		if (empedu.getId() != 0) {
			builder.append(" where id = " + empedu.getId());
			 query = jdbcTemplate.query(builder.toString(),
					new BeanPropertyRowMapper<EmployeeEducation>(EmployeeEducation.class));
			log.debug("response1 from dao dao " + query);
		}
		}
	}else {
		 query = jdbcTemplate.query(builder.toString(),
			new BeanPropertyRowMapper<EmployeeEducation>(EmployeeEducation.class));
		 }
	 	return query;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.EmployeeEducationDao#getAllRecordsCount()
	 */

	@Override
	public int getAllRecordsCount() throws SQLException {

		
			return jdbcTemplate.queryForObject(COUNTRECORDS, Integer.class);
		
	}

	/*
	 * @Override public List<EmployeeEducation> getById(EmployeeEducationRequest
	 * employeeEducationRequest) throws SQLException { try { return
	 * jdbcTemplate.query(GETBYID +
	 * employeeEducationRequest.getListEmployeeEducations().get(0).getId(), new
	 * BeanPropertyRowMapper<EmployeeEducation>(EmployeeEducation.class)); } finally
	 * { if (jdbcTemplate != null) { try {
	 * jdbcTemplate.getDataSource().getConnection().close(); } catch (Exception e) {
	 * e.printStackTrace(); log.error("The exception raised in getAll catch"); } }
	 * 
	 * } }
	 */
}