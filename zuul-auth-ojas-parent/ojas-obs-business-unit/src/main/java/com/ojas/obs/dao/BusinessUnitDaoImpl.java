package com.ojas.obs.dao;

import static com.ojas.obs.constants.UserConstants.BUSINESSUNITCOUNT;
import static com.ojas.obs.constants.UserConstants.DELETE_BUSINESSUNIT;
import static com.ojas.obs.constants.UserConstants.INSERT_BUSINESSUNIT;
import static com.ojas.obs.constants.UserConstants.SELECT_BUSINESSUNIT;
import static com.ojas.obs.constants.UserConstants.UPDATE_BUSINESSUNIT;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ojas.obs.model.BusinessUnit;
import com.ojas.obs.model.BusinessUnitRequest;



@Repository
public class BusinessUnitDaoImpl implements BusinessUnitDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	Logger logger = Logger.getLogger(this.getClass());

	public Connection getConnection() throws SQLException {
		DataSource datasource = null;

		datasource = jdbcTemplate.getDataSource();
		logger.debug("MefkeyDao:calling the connection from datasource");
		Connection connection = datasource.getConnection();
		logger.debug("mefKeyDao:returning the conection object ");
		return connection;
	}

	@Override
	public Boolean saveBusinessUnit(BusinessUnitRequest businessUnitRequest) throws SQLException {

		logger.debug("Inside saveEducation..DAO");
		boolean status = false;
		try (Connection connection = getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(INSERT_BUSINESSUNIT)) {
			connection.setAutoCommit(false);
			logger.debug("Inside saveEducation..getConnection()");
			prepareStatement.setInt(1, businessUnitRequest.getBusinessUnit().getId());
			prepareStatement.setString(2, businessUnitRequest.getBusinessUnit().getBusinessUnitName());
			prepareStatement.setInt(3, businessUnitRequest.getBusinessUnit().getCostCenterId());
			int i = prepareStatement.executeUpdate();
			connection.commit();
			if (i > 0)
				return true;
		}
		return status;
	}

	@Override
	public Boolean updateBusinessUnit(BusinessUnitRequest businessUnitRequestobject) throws SQLException {

		logger.debug("Inside updateEducation..DAO *****");
		boolean status = false;
		try (Connection connection2 = getConnection();
				PreparedStatement prepareStatement = connection2.prepareStatement(UPDATE_BUSINESSUNIT)) {

			logger.debug("Inside update .prepareStatement.*****");
			prepareStatement.setString(1, businessUnitRequestobject.getBusinessUnit().getBusinessUnitName());
			prepareStatement.setInt(2, businessUnitRequestobject.getBusinessUnit().getCostCenterId());
			prepareStatement.setInt(3, businessUnitRequestobject.getBusinessUnit().getId());
			int i = prepareStatement.executeUpdate();
			if (i > 0)
				return true;
		}
		return status;
	}

	@Override
	public boolean deleteBusinessUnit(Integer courseId) throws SQLException {
		logger.debug("Inside saveEducation..DAO");
		boolean status = false;
		try (Connection connection = getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(DELETE_BUSINESSUNIT)) {
			prepareStatement.setInt(1, courseId);
			int i = prepareStatement.executeUpdate();
			if (i > 0)
				return true;
		}
		return status;
	}

	@Override
	public List<BusinessUnit> getAllBussinessDetails(BusinessUnitRequest request) throws SQLException {
		logger.debug("Inside getAllEducationDetails DAO .***");
		StringBuilder buffer = new StringBuilder();
		List<BusinessUnit> decodeResultset = null;
		try (Connection connection = getConnection();) {
			if (request.getBusinessUnit() != null) {
				BusinessUnit businessUnit = request.getBusinessUnit();
				buffer.append(SELECT_BUSINESSUNIT);
				if (businessUnit.getId() != 0) {
					buffer.append(" Where id= " + businessUnit.getId());
				}
				if (businessUnit.getBusinessUnitName() != null && !businessUnit.getBusinessUnitName().isEmpty()) {
					if (buffer.toString().contains("Where id")) {
						buffer.append(" and businessUnitName= '" + businessUnit.getBusinessUnitName() + "'");
					} else {
						buffer.append(" where businessUnitName = '" + businessUnit.getBusinessUnitName() + "'");
					}
				}
				if (businessUnit.getCostCenterId() != 0) {
					if (buffer.toString().contains("Where id and businessUnitName")) {
						buffer.append(" and costCenterId= " + businessUnit.getCostCenterId());
					} else {
						buffer.append(" where costCenterId= " + businessUnit.getCostCenterId());
					}
				}
				try (PreparedStatement prepareStatement = connection.prepareStatement(buffer.toString())) {
					try (ResultSet resultSet = prepareStatement.executeQuery()) {

						logger.debug("Inside getAll .prepareStatement.*****");
						decodeResultset = this.decodeResultset(resultSet);
					}
				}
			}
			return decodeResultset;
		}
	}

	@Override
	public int getAllBussinessCount() throws SQLException {
		int count = 0;
		try (Connection connection2 = getConnection();
				PreparedStatement prepareStatement = connection2.prepareStatement(BUSINESSUNITCOUNT);
				ResultSet resultSet = prepareStatement.executeQuery()) {
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
			return count;
		}
	}

	public List<BusinessUnit> getCountPerPage(List<BusinessUnit> list, int pageSize, int pageNo) {
		List<BusinessUnit> getAllFiltered = new ArrayList<>();
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

	public List<BusinessUnit> decodeResultset(ResultSet rs) throws SQLException {
		BusinessUnit b = null;
		List<BusinessUnit> businessUnitList = new ArrayList<>();
		while (rs.next()) {
			b = new BusinessUnit();
			b.setId(rs.getInt("id"));
			b.setBusinessUnitName(rs.getString("businessUnitName"));
			b.setCostCenterId(rs.getInt("costCenterId"));
			businessUnitList.add(b);
		}
		return businessUnitList;
	}
}