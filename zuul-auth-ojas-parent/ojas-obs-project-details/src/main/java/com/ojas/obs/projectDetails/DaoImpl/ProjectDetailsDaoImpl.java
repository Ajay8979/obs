package com.ojas.obs.projectDetails.DaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ojas.obs.projectDetails.Dao.ProjectDetailsDao;
import com.ojas.obs.projectDetails.Exception.TransactionException;
import com.ojas.obs.projectDetails.constants.PropsReaderUtil;
import com.ojas.obs.projectDetails.model.ProjectDetails;
import com.ojas.obs.projectDetails.request.ProjectDetailsRequest;
import com.ojas.obs.projectDetails.rowmapper.ProjectDetailsRowMappers;

@Component
public class ProjectDetailsDaoImpl implements ProjectDetailsDao {
	Logger logger = Logger.getLogger(this.getClass());
	/**
	 * 
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private PropsReaderUtil propsReaderUtil;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Integer saveProjectDetails(ProjectDetailsRequest projectDetailsRequestObject) throws SQLException {
		List<Object[]> list = new ArrayList<>();

		logger.debug(" Received input to ProjectDetailsDaoImpl / saveProjectDetails :" + projectDetailsRequestObject);
		try {
			for (ProjectDetails projectDetails : projectDetailsRequestObject.getProjectDetailsList()) {
				Object[] params = new Object[] { projectDetails.getProjectName(), projectDetails.getContractId(),
						projectDetails.getRateId(), projectDetails.getStartDate(), projectDetails.getEndDate(),
						projectDetails.getBillingId(), projectDetails.getEmployeeId(),
						projectDetails.getProjectTechStack(), projectDetails.getCustomer(),
						projectDetails.getLocation(), projectDetails.isGstApplicable(), projectDetails.getProjectType(),
						projectDetails.getProjectStatus(), projectDetails.getBdmContact(),
						projectDetails.isIsInternal(), true, projectDetails.getCreatedBy() };
				list.add(params);
			}

			int[] response = jdbcTemplate.batchUpdate(propsReaderUtil.getValue("insert_ProjectDetails"), list);
			logger.debug(" output in ProjectDetailsDaoImpl / saveProjectDetails :" + response);

			return response.length;
		} finally {
			if (null != jdbcTemplate) {
				try {
					jdbcTemplate.getDataSource().getConnection().close();
					logger.debug("Closing the connection :" + jdbcTemplate.getDataSource().getConnection().isClosed());
				} catch (Exception e) {
					e.getMessage();
				}
			}
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Integer updateProjectDetails(ProjectDetailsRequest projectDetailsRequestObject) throws SQLException {
		logger.debug(" Received input to ProjectDetailsDaoImpl / updateProjectDetails :" + projectDetailsRequestObject);
		try {
			List<Object[]> list = new ArrayList<>();
			for (ProjectDetails projectDetails : projectDetailsRequestObject.getProjectDetailsList()) {
				Object[] params = new Object[] { projectDetails.getProjectName(), projectDetails.getContractId(),
						projectDetails.getRateId(), projectDetails.getStartDate(), projectDetails.getEndDate(),
						projectDetails.getBillingId(), projectDetails.getEmployeeId(),
						projectDetails.getProjectTechStack(), projectDetails.getCustomer(),
						projectDetails.getLocation(), projectDetails.isGstApplicable(), projectDetails.getProjectType(),
						projectDetails.getProjectStatus(), projectDetails.getBdmContact(),
						projectDetails.isIsInternal(), projectDetails.getUpdatedBy(), projectDetails.getId() };

				list.add(params);
			}
			int[] response = jdbcTemplate.batchUpdate(propsReaderUtil.getValue("update_ProjectDetails"), list);
			logger.debug(" output in ProjectDetailsDaoImpl / updateProjectDetails :" + response);
			return response.length;
		} finally {
			if (null != jdbcTemplate) {
				try {
					jdbcTemplate.getDataSource().getConnection().close();
				} catch (Exception e) {
					e.getMessage();
				}
			}
		}
	}

	@Override
	public List<ProjectDetails> getAll() throws SQLException {
		logger.debug(" entered into ProjectDetailsDaoImpl / getAll ");
		try {
			List<ProjectDetails> projectDetailsList = jdbcTemplate
					.query(propsReaderUtil.getValue("getAll_projectDetails"), new ProjectDetailsRowMappers());
			logger.debug(" output in ProjectDetailsDaoImpl / getAll :" + projectDetailsList);
			return projectDetailsList;
		} finally {
			if (null != jdbcTemplate) {
				try {
					jdbcTemplate.getDataSource().getConnection().close();
				} catch (Exception e) {
					e.getMessage();
				}
			}
		}
	}

	@Override
	public List<ProjectDetails> getById(Integer id) throws SQLException {
		logger.debug(" Received input to ProjectDetailsDaoImpl / getById :" + id);
		try {
			Object[] params = new Object[] { id };
			List<ProjectDetails> projectDetailsList = jdbcTemplate
					.query(propsReaderUtil.getValue("getById_projectDetails"), params, new ProjectDetailsRowMappers());
			logger.debug(" output in ProjectDetailsDaoImpl / getById :" + projectDetailsList);
			return projectDetailsList;
		} finally {
			if (null != jdbcTemplate) {
				try {
					jdbcTemplate.getDataSource().getConnection().close();
				} catch (Exception e) {
					e.getMessage();
				}
			}
		}
	}

	@Transactional(rollbackFor = TransactionException.class)
	@Override
	public Integer deleteProjectDetails(ProjectDetailsRequest projectDetailsRequestObject) throws SQLException {
		logger.debug(" Received input to ProjectDetailsDaoImpl / deleteProjectDetails :" + projectDetailsRequestObject);
		try {
			List<Object[]> list = new ArrayList<>();
			for (ProjectDetails projectDetails : projectDetailsRequestObject.getProjectDetailsList()) {
				Object[] params = new Object[] { false, projectDetails.getUpdatedBy(), projectDetails.getId() };

				list.add(params);
			}
			int[] response = jdbcTemplate.batchUpdate(propsReaderUtil.getValue("delete_ProjectDetails"), list);
			logger.debug(" output in ProjectDetailsDaoImpl / getById :" + response);
			return response.length;
		} finally {
			if (null != jdbcTemplate) {
				try {
					jdbcTemplate.getDataSource().getConnection().close();
				} catch (Exception e) {
					e.getMessage();
				}
			}
		}
	}

}
