package com.obs.employeeCertificationDetails.daoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.obs.employeeCertificationDetails.constants.Constants.INSERT;
import static com.obs.employeeCertificationDetails.constants.Constants.UPDATEQ;
import static com.obs.employeeCertificationDetails.constants.Constants.DELETECertificationDetails;
import static com.obs.employeeCertificationDetails.constants.Constants.GETCertificationDetailsCOUNT;
import static com.obs.employeeCertificationDetails.constants.Constants.GETCertificationDetails;
import static com.obs.employeeCertificationDetails.constants.Constants.GETCertificationDetailById;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.obs.employeeCertificationDetails.constants.PropsReaderUtil;
import com.obs.employeeCertificationDetails.dao.EmployeeCertificationDAO;
import com.obs.employeeCertificationDetails.model.CertificationDetails;
import com.obs.employeeCertificationDetails.request.CertificationDetailsRequest;

@Repository
public class EmployeeCertificationDAOImpl implements EmployeeCertificationDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	PropsReaderUtil propsReaderUtil;
	Logger logger = Logger.getLogger(this.getClass());
	@Override
	public Boolean saveCertificationDetails(CertificationDetailsRequest certificationDetailsRequest) throws SQLException{
		List<CertificationDetails> modelList = certificationDetailsRequest.getCertificationDetailsModel();
		List<Object[]> list= new ArrayList<Object[]>();
		int[] count = null;
		for(CertificationDetails details :modelList) {
			Object[] model= new Object[] { details.getCertificationName(), details.getIssuedBy(),details.getDateOfIssue(), details.getEmployeeId(), details.getCreatedBy(),details.isFlag()};
		    list.add(model);
		}
       // count = jdbcTemplate.batchUpdate(propsReaderUtil.getValue("INSERTCertificationDetails"),list);
		count = jdbcTemplate.batchUpdate(INSERT,list);
        for (int i : count) {
			if (i > 0) {
				logger.debug("saved successfully through DaoImpl" + i);
				return true;
            }
		}
		logger.debug("failed to save through daoImpl Method");
		return false;
	}

	@Override
	public Boolean updateCertificationDetails(CertificationDetailsRequest certificationDetailsRequest) throws SQLException{
		List<CertificationDetails> modelList = certificationDetailsRequest.getCertificationDetailsModel();
		List<Object[]> list= new ArrayList<Object[]>();
		int[] count = null;
		for(CertificationDetails details :modelList) {
			Object[] model= new Object[] { details.getCertificationName(), details.getIssuedBy(),details.getDateOfIssue(), details.getCreatedBy(), details.getUpdatedBy(),details.getId()};
		    list.add(model);
		}
        //count = jdbcTemplate.batchUpdate(propsReaderUtil.getValue("UPDATECertificationDetailsBYID"),list);
		 count = jdbcTemplate.batchUpdate(UPDATEQ,list);
        for (int i : count) {
			if (i > 0) {
				logger.debug("updated successfully through DaoImpl" + i);
				return true;
            }
		}
		logger.debug("failed to update through daoImpl Method");
		return false;
	}

	@Override
	public Boolean deleteCertificationDetails(CertificationDetailsRequest certificationDetailsRequest) throws SQLException{
		List<CertificationDetails> modelList = certificationDetailsRequest.getCertificationDetailsModel();
		List<Object[]> list= new ArrayList<Object[]>();
		int[] count = null;
		for(CertificationDetails details :modelList) {
			Object[] model= new Object[] {details.getUpdatedBy(),details.getId()};
		    list.add(model);
		}
		//count = jdbcTemplate.batchUpdate(propsReaderUtil.getValue("DELETECertificationDetails"), list);
		count = jdbcTemplate.batchUpdate(DELETECertificationDetails, list);

		 for (int i : count) {
				if (i > 0) {
					logger.debug("updated successfully through DaoImpl" + i);
					return true;
	            }
		 }
			logger.debug("failed to update through daoImpl Method");
			return false;
	}

	@Override
	public List<CertificationDetails> getAllCertificationDetails() throws SQLException{
	    //return jdbcTemplate.query(propsReaderUtil.getValue("GETCertificationDetails"),new BeanPropertyRowMapper<CertificationDetails>(CertificationDetails.class));
		return jdbcTemplate.query(GETCertificationDetails,new BeanPropertyRowMapper<CertificationDetails>(CertificationDetails.class));
	}

	@Override
	public int getAllCertificationDetailsCount() {
		//return jdbcTemplate.queryForObject(propsReaderUtil.getValue("GETCertificationDetailsCOUNT"), Integer.class);
		return jdbcTemplate.queryForObject(GETCertificationDetailsCOUNT, Integer.class);
	}

	@Override
	public List<CertificationDetails> getCountPerPage(List<CertificationDetails> certificationList, int pageSize,
			int pageNo) {
		List<CertificationDetails> getAllFiltered = new ArrayList<>();
		if (certificationList != null && !certificationList.isEmpty()) {
			pageSize = pageSize > 0 ? pageSize : pageSize * -1;
			pageNo = pageNo > 0 ? pageNo : pageNo == 0 ? 1 : pageNo * -1;
			if (pageSize != 0) {
				int endIndex = pageNo * pageSize;
				int startIndex = endIndex - pageSize;
				endIndex = endIndex < certificationList.size() ? endIndex : certificationList.size();
				startIndex = startIndex < certificationList.size() ? startIndex : 0;
				getAllFiltered = certificationList.subList(startIndex, endIndex);
			}
		}
		return getAllFiltered;
	}

	@Override
	public List<CertificationDetails> getDetailById(CertificationDetailsRequest certificationDetailsRequest)
			throws SQLException {
		List<CertificationDetails> modelList = certificationDetailsRequest.getCertificationDetailsModel();
		List<Object[]> list= new ArrayList<Object[]>();
		Object[] param=null;
		for(CertificationDetails details :modelList) {
			param	 = new Object[] { details.getId() };
			list.add(param);

		}
		List<CertificationDetails> query = jdbcTemplate.query(GETCertificationDetailById,param,
				new BeanPropertyRowMapper<>(CertificationDetails.class));
		return query;
	}

}
