package com.ojas.obs.daoimpl;

import java.util.ArrayList;

import java.util.List;

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

	public static String INSERTEMPLOYEEEDUCATIONINFOSTMT = "INSERT INTO education_table (id,degree,pg,degree_stream,pg_stream) VALUES (?,?,?,?,?)";

	public static String SHOWEDUCATIONINFOSTMT = "SELECT * FROM education_table WHERE id=?";

	public static String UPDATESTMT = "UPDATE education_table set degree=?, pg = ?, degree_stream = ?, pg_stream = ? WHERE id = ? ";

	public static final String DELETEEDUCATION = "delete from education_table where id = ?";

	public static final String TOTALRECORDS = "select * from education_table";

	public static final String COUNTRECORDS = "select count(*) from education_table";

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
	public boolean saveEmployeeEducation(EmployeeEducationRequest employeeEducationRequest) {
		List<EmployeeEducation> employeeEducations = employeeEducationRequest.getListEmployeeEducations();
		int[] batchSave = null;
		List<Object[]> input = new ArrayList<Object[]>();
		for (EmployeeEducation employeeEducation : employeeEducations) {
			Object[] saveList = { employeeEducation.getId(), employeeEducation.getDegree(),
					employeeEducation.getDegree_stream(), employeeEducation.getPg(), employeeEducation.getPg_stream() };
			input.add(saveList);
		}
		batchSave = jdbcTemplate.batchUpdate(INSERTEMPLOYEEEDUCATIONINFOSTMT, input);
		if (batchSave.length > 0)
			return true;
		else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ojas.obs.dao.EmployeeEducationDao#updateEmployeeEducation(com.ojas.obs.
	 * modelrequest.EmployeeEducationRequest)
	 */
	@Override
	public boolean updateEmployeeEducation(EmployeeEducationRequest employeeEducationRequest) {
		List<EmployeeEducation> employeeEducations = employeeEducationRequest.getListEmployeeEducations();
		int[] batchSave = null;
		List<Object[]> input = new ArrayList<Object[]>();
		for (EmployeeEducation employeeEducation : employeeEducations) {
			Object[] saveList = { employeeEducation.getDegree(), employeeEducation.getDegree_stream(),
					employeeEducation.getPg(), employeeEducation.getPg_stream(), employeeEducation.getId() };
			input.add(saveList);
		}
		batchSave = jdbcTemplate.batchUpdate(UPDATESTMT, input);
		if (batchSave.length > 0)
			return true;
		else {
			return false;
		}
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.EmployeeEducationDao#deleteEmployeeEducation(int)
	 */

	@Override
	public boolean deleteEmployeeEducation(int id) {
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

	@Override
	public List<EmployeeEducation> getAllEmployeeEducation(EmployeeEducationRequest employeeEducationRequest) {
		List<EmployeeEducation> employeeEducations = jdbcTemplate.query(TOTALRECORDS,
				new BeanPropertyRowMapper<EmployeeEducation>(EmployeeEducation.class));
		return employeeEducations;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.EmployeeEducationDao#getAllRecordsCount()
	 */

	@Override
	public int getAllRecordsCount() {
		int records = jdbcTemplate.queryForObject(COUNTRECORDS, Integer.class);
		return records;
	}

}
