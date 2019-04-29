package com.ojas.obs.daoImpl;

import static com.ojas.obs.constants.DependentDetailsContants.DELETEDEPENDENTDETAILS;
import static com.ojas.obs.constants.DependentDetailsContants.GETALLDEPENDENTDETAILS;
import static com.ojas.obs.constants.DependentDetailsContants.GETDEPENDENTDETAILSBYID;
import static com.ojas.obs.constants.DependentDetailsContants.INSERTDEPENDENTDETAILS;
import static com.ojas.obs.constants.DependentDetailsContants.UPDATEDEPENDENTDETAILS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ojas.obs.dao.DependentDetailsDao;
import com.ojas.obs.model.DependentDetails;
import com.ojas.obs.request.DependentDetailsRequest;
import com.ojas.obs.service.RowMap;

/**
 * 
 * @author srinukummari
 *
 */
@Component
public class DependentDetailsDaoImpl implements DependentDetailsDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/*
	 * (non-Javadoc)
	 * @see com.ojas.obs.dao.DependentDetailsDao#saveDependentDetails(com.ojas.obs.request.DependentDetailsRequest)
	 */
	@Override
	public int saveDependentDetails(DependentDetailsRequest dependentDetailsRequest) throws SQLException {
		 Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		boolean flag = true;
		int savedrecords = 0;
		List<DependentDetails> dependentDetails = dependentDetailsRequest.getDependentDetails();
		for (DependentDetails details : dependentDetails) {
			details.setCreated_Date(timeStamp); details.setUpdated_By(null); details.setUpdated_Date(null); details.setFlag(flag);
			savedrecords = jdbcTemplate.update(INSERTDEPENDENTDETAILS, details.getDependent_Name(), details.getRelation(), details.getDate_Of_Birth(), details.getEmployee_Id(), details.getCreated_By(), details.getCreated_Date(), details.getUpdated_By(), details.getUpdated_Date(), details.isFlag());
		}
		return savedrecords;
			
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ojas.obs.dao.DependentDetailsDao#updateDependentDetails(com.ojas.obs.request.DependentDetailsRequest)
	 */
	@Override
	public int updateDependentDetails(DependentDetailsRequest dependentDetailsRequest) throws SQLException {
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		int updatedrecords = 0;
		List<DependentDetails> dependentDetails = dependentDetailsRequest.getDependentDetails();
		for (DependentDetails details : dependentDetails) {
			details.setUpdated_Date(timeStamp);
			updatedrecords = jdbcTemplate.update(UPDATEDEPENDENTDETAILS, details.getDependent_Name(),details.getRelation(),details.getDate_Of_Birth(),details.getEmployee_Id(), details.getUpdated_By(), details.getUpdated_Date(), details.getId());
		}
		return updatedrecords;
	}
	/*
	 * (non-Javadoc)
	 * @see com.ojas.obs.dao.DependentDetailsDao#deleteDependentDetails(com.ojas.obs.request.DependentDetailsRequest)
	 */
	@Override
	public int deleteDependentDetails(DependentDetailsRequest dependentDetailsRequest) throws SQLException {
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		int deletedrecords = 0;
		boolean flag = false;
		List<DependentDetails> dependentDetails = dependentDetailsRequest.getDependentDetails();
		for (DependentDetails details : dependentDetails) {
			details.setUpdated_Date(timeStamp);
			details.setFlag(flag);
			deletedrecords = jdbcTemplate.update(DELETEDEPENDENTDETAILS,  details.getUpdated_By(), details.getUpdated_Date(), details.isFlag(), details.getId());
		}
		return deletedrecords;
	}
	/*
	 * (non-Javadoc)
	 * @see com.ojas.obs.DependentDetails.dao.DependentDetailsDao#getAll(com.ojas.obs.DependentDetails.request.DependentDetailsRequest)
	 */
	@Override
	public List<DependentDetails> getAll(DependentDetailsRequest DependentDetailsRequest) throws SQLException {
		List<DependentDetails> listDependentDetails = jdbcTemplate.query(GETALLDEPENDENTDETAILS,new RowMap());
		return listDependentDetails;

	}

	@Override
	public List<DependentDetails> getById(Integer id) throws SQLException {
		
		Object[] obj = new Object[] { id }; 
		
		List<DependentDetails> projectDetailsList = jdbcTemplate.query(GETDEPENDENTDETAILSBYID, obj,new RowMap());

		return projectDetailsList;
	}

}
