package com.ojas.obs.dao;

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

import com.ojas.obs.model.EmployeeBUDetails;
import com.ojas.obs.request.EmployeeBUDetailsRequest;

/**
 * 
 * @author uyashwanth
 *
 */
@Repository
public class EmployeeBUDaoImpl implements EmployeeBUDao {
	private static final String INSERTEMPLOYEBU = "Insert into obs_budetails(EmployeeId, sbu, status, Createdby, Createddate,flag) values(?,?,?,?,?,?)";
	private static final String GETEMPLOYEBU = "Select * from obs_budetails where flag='1' ";
	private static final String GETBYID = "Select * from obs_budetails where flag = 1 and id = ? ";
	private static final String UPDATEBUDETAILS = "Update obs_budetails set EmployeeId=?, sbu=?, status=?, Updatedby=?, Updateddate=? WHERE Id=?";
	private static final String DELETEBUDEAILS = "Update obs_budetails set flag='0' where Id = ?";
	private static final String GETBUDetailsCOUNT = "select count(*) from obs_budetails where flag = '1' ";
	@Autowired
	private JdbcTemplate jdbcTemplate;
	Logger logger = Logger.getLogger(this.getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.EmployeeBUDao#saveEmployeebu(com.ojas.obs.request.
	 * EmployeeBUDetailsRequest)
	 */
	@Override
	public boolean saveEmployeebu(EmployeeBUDetailsRequest employeebuRequest) throws SQLException {
		try {
		Timestamp timestamp = new Timestamp(new Date().getTime());
		boolean flag = true;
		int count = 0;
		boolean status = false;
		List<EmployeeBUDetails> budetails = employeebuRequest.getEmployeeBUDeatils();
		for (EmployeeBUDetails employeebudetails : budetails) {
			count = jdbcTemplate.update(INSERTEMPLOYEBU, employeebudetails.getEmployeeId(), employeebudetails.getSbu(),
					employeebudetails.getStatus(), employeebudetails.getCreatedby(), timestamp,
					flag);
		}
		if (count > 0)
			return true;
		else
			return status;
	} finally {
		jdbcTemplate.getDataSource().getConnection().close();
	}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.EmployeeBUDao#getAllEmployeebu(com.ojas.obs.request.
	 * EmployeeBUDetailsRequest)
	 */
	@Override
	public List<EmployeeBUDetails> getAllEmployeebu(EmployeeBUDetailsRequest employeebuRequest) throws SQLException {
		try {
		return jdbcTemplate.query(GETEMPLOYEBU, new BeanPropertyRowMapper<EmployeeBUDetails>(EmployeeBUDetails.class));
	} finally {
		jdbcTemplate.getDataSource().getConnection().close();
	}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.EmployeeBUDao#updateEmployeebu(com.ojas.obs.request.
	 * EmployeeBUDetailsRequest)
	 */
	@Override
	public boolean updateEmployeebu(EmployeeBUDetailsRequest employeebuRequest) throws SQLException {
		try {
		int count = 0;
		Timestamp timestamp = new Timestamp(new Date().getTime());
		List<EmployeeBUDetails> budetails = employeebuRequest.getEmployeeBUDeatils();
		for (EmployeeBUDetails employeebudetails : budetails) {
			count = jdbcTemplate.update(UPDATEBUDETAILS, employeebudetails.getEmployeeId(), employeebudetails.getSbu(),
					employeebudetails.getStatus(), employeebudetails.getUpdatedby(), timestamp,
					employeebudetails.getId());
		}
		if (count > 0)
			return true;
		else
			return false;
	} finally {
		jdbcTemplate.getDataSource().getConnection().close();
	}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.EmployeeBUDao#deleteEmployeeRecord(java.lang.Integer)
	 */
	@Override
	public boolean deleteEmployeeRecord(Integer courseId) throws SQLException {
		try {
		int count = 0;
		boolean status = false;
		count = jdbcTemplate.update(DELETEBUDEAILS, courseId);
		if (count > 0)
			return true;
		else
			return status;
	} finally {
		jdbcTemplate.getDataSource().getConnection().close();
	}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.EmployeeBUDao#getAllRecordsCount()
	 */
	@Override
	public int getAllRecordsCount() throws SQLException {
		try {
		return jdbcTemplate.queryForObject(GETBUDetailsCOUNT, Integer.class);
	} finally {
		jdbcTemplate.getDataSource().getConnection().close();
	}
	}

	@Override
	public List<EmployeeBUDetails> getById(EmployeeBUDetailsRequest employeebuRequest) throws SQLException {
		try {
		List<EmployeeBUDetails> modelList = employeebuRequest.getEmployeeBUDeatils();
		List<Object[]> list = new ArrayList<Object[]>();
		Object[] param = null;
		for (EmployeeBUDetails details : modelList) {
			param = new Object[] { details.getId() };
			list.add(param);

		}
		List<EmployeeBUDetails> query = jdbcTemplate.query(GETBYID, param, new BeanPropertyRowMapper<>(EmployeeBUDetails.class));
		return query;
	}finally {
		jdbcTemplate.getDataSource().getConnection().close();
	}
	}
}
