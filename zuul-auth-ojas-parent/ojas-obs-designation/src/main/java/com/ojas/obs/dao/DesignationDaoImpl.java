package com.ojas.obs.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ojas.model.Designation;
import com.ojas.obs.request.DesignationRequest;

/**
 * 
 * @author nsrikanth
 * 
 */
@Repository
public class DesignationDaoImpl implements DesignationDao {

	@Autowired
	private JdbcTemplate jdbc;
	Logger logger = Logger.getLogger(this.getClass());

	public static final String SAVEDesignation = "Insert into obs_designation(designation) values(?)";
	public static final String UPDATEDesignation = "Update obs_designation set designation =? where id =? ";
	public static final String DELETEDesignation = "Delete from obs_designation where id =?";
	public static final String SELECTDesignation = "Select * from obs_designation ORDER BY id asc";
	public static final String DESIGNATIONCOUNTDesignation = "Select count(*) from obs_designation";
	public static final String getbyid = "Select * from obs_designation where id = ";
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.DesignationDao#saveDesignation(com.ojas.obs.request.
	 * DesignationRequest)
	 */

	@Override
	public boolean saveDesignation(DesignationRequest designationRequest) throws SQLException {

	//	try {

			List<Object[]> inputList = new ArrayList<Object[]>();

			for (Designation designation : designationRequest.getDesignation()) {

				logger.debug("Inside save Designation..getConnection()");
				Object[] obj = new Object[] { designation.getDesignation() };

				inputList.add(obj);

				/*
				 * if (n > 0) { return true; } return false;
				 */
			}
			int[] batchUpdate = jdbc.batchUpdate(SAVEDesignation, inputList);
			if (batchUpdate.length > 0) {
				return true;
			}
			return false;
/*		}

		catch (Exception e) {
			logger.debug("Inside saveDesignation..DAO catch block" + e.getMessage());
		}*/
		//return false;
	}

	/*
	 * int save = 0; List<Designation> designation =
	 * designationRequest.getDesignation(); for (Designation designationObj :
	 * designation) { save = jdbc.update(SAVEDesignation,
	 * designationObj.getDesignation()); }
	 * 
	 * if (save > 0) return true; else return false;
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.DesignationDao#updateDesignation(com.ojas.obs.request.
	 * DesignationRequest)
	 */

	@Override
	public boolean updateDesignation(DesignationRequest designationRequest) throws SQLException {

		List<Object[]> inputList = new ArrayList<Object[]>();
		logger.debug("Inside updateDesignation..DAO *****");
		try {

			List<Designation> designation = designationRequest.getDesignation();

			for (Designation plan : designation) {

				Object[] obj = new Object[] { plan.getDesignation(), plan.getId() };
				/*
				 * if (n > 0) return true; else return false;
				 */

				inputList.add(obj);

			}
			int[] batchUpdate = jdbc.batchUpdate(UPDATEDesignation, inputList);

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
	 * int up = 0; List<Designation> designation =
	 * designationRequest.getDesignation(); for(Designation designationObj :
	 * designation) {
	 * 
	 * up = jdbc.update(UPDATEDesignation, designationObj.getDesignation(),
	 * designationObj.getId());
	 * 
	 * } if (up > 0) return true; else return false;
	 * 
	 * }
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.DesignationDao#deleteDesignation(int)
	 */

	/*
	 * @Override public boolean deleteDesignation(int id) throws SQLException {
	 * //boolean b2=false; int d = jdbc.update(DELETEDesignation, id); if (d > 0)
	 * return true; else return false; }
	 */

	/*
	 * @Override public boolean deleteDesignation(DesignationRequest
	 * designationRequest) throws SQLException { List<Designation>
	 * des=designationRequest.getDesignation(); List<Object[]> list = new
	 * ArrayList<Object[]>(); for(Designation designation : des) { Object[]
	 * model=new Object[]{designation.getId()};
	 * System.out.println("Id list::"+designation.getId()); list.add(model); } int[]
	 * count=jdbc.batchUpdate(DELETEDesignation,list); for (int i : count) { if (i >
	 * 0) {
	 * 
	 * logger.debug("saved successfully through DaoImpl" + i); return true; } }
	 * logger.debug("failed to save through daoImpl Method"); return false; }
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.DesignationDao#getAllDesignationCount()
	 */

	@Override
	public int getAllDesignationCount() throws SQLException {

		return jdbc.queryForObject(DESIGNATIONCOUNTDesignation, Integer.class);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.DesignationDao#getAll(com.ojas.obs.request.
	 * DesignationRequest)
	 */

	@Override
	public List<Designation> getAll(DesignationRequest designationRequest) throws SQLException {

		logger.debug("Inside getAllDesignationDetails DAO .***");

		List<Designation> designation = jdbc.query(SELECTDesignation, new BeanPropertyRowMapper<>(Designation.class));

		return designation;

	}

	public List<Designation> getById(DesignationRequest designationRequest) {

		List<Designation> designationList = new ArrayList<>();
		List<Designation> insuranceList = designationRequest.getDesignation();
		for (Designation designation : insuranceList) {
			List<Designation> query = jdbc.query(getbyid + designation.getId(),
					new BeanPropertyRowMapper<>(Designation.class));
			designationList.addAll(query);
		}
		return designationList;
	}
	
	
	
	
	
	

	/*
	 * List<Designation> query = jdbc.query(SELECTDesignation, new
	 * BeanPropertyRowMapper<Designation>(Designation.class));
	 * System.out.println("data.****" + query); return query;
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.DesignationDao#getCountPerPage(java.util.List, int,
	 * int)
	 */

	/*
	 * @Override public List<Designation> getCountPerPage(List<Designation> list,
	 * int pageSize, int pageNo) { // TODO Auto-generated method stub return null; }
	 */

}
