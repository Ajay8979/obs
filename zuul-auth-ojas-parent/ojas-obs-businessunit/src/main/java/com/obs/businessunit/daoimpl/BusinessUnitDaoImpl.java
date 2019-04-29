package com.obs.businessunit.daoimpl;

import static com.obs.businessunit.constants.UserConstants.GETBYIDSTMT;
import static com.obs.businessunit.constants.UserConstants.INSERT_BUSINESSUNIT;
import static com.obs.businessunit.constants.UserConstants.SELECT_BUSINESSUNIT;
import static com.obs.businessunit.constants.UserConstants.UPDATE_BUSINESSUNIT;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.obs.businessunit.dao.BusinessUnitDao;
import com.obs.businessunit.model.BusinessUnit;
import com.obs.businessunit.request.BusinessUnitRequest;
@Repository
public class BusinessUnitDaoImpl implements BusinessUnitDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	Logger logger = Logger.getLogger(this.getClass());

	@Override
	public Boolean saveBusinessUnit(BusinessUnitRequest businessUnitRequest) {
		boolean b = false;
		int[] save;
		List<BusinessUnit> businessUnitList = businessUnitRequest.getBusinessUnit();
		List<Object[]> list = new ArrayList<>();
		for (BusinessUnit businessUnit : businessUnitList) {
			 Object[] bus = new Object[] {businessUnit.getBusinessUnitName(),businessUnit.getCostCenterId()};
			list.add(bus);
		}
		save = jdbcTemplate.batchUpdate(INSERT_BUSINESSUNIT, list);
		
		if (save.length > 0) {
			b = true;
			
		}
		return b;
	}
	@Override
	public Boolean updateBusinessUnit(BusinessUnitRequest businessUnitRequest) {
		List<BusinessUnit> businessUnitList = businessUnitRequest.getBusinessUnit();
		boolean b = false;
		int[] update;
		List<Object[]> list = new ArrayList<>();
		for (BusinessUnit businessUnit : businessUnitList) {
			 Object[] bus = new Object[] {businessUnit.getBusinessUnitName(),businessUnit.getCostCenterId(),businessUnit.getId()};
			list.add(bus);
		}
		update = jdbcTemplate.batchUpdate(UPDATE_BUSINESSUNIT, list);
		
		if (update.length > 0) {
			b = true;
		} 
		return b;
	}
	

	
	@Override
	public List<BusinessUnit> getAllBussinessDetails(BusinessUnitRequest request) {
		logger.debug("Inside getAllEducationDetails DAO .***");
		List<BusinessUnit> list = jdbcTemplate.query(SELECT_BUSINESSUNIT, new BeanPropertyRowMapper<BusinessUnit>(BusinessUnit.class));
		return list;
	}
	@Override
	public List<BusinessUnit> getById(BusinessUnitRequest request) {
		Integer id = request.getBusinessUnit().get(0).getId();
		Object[] param = {id};
		return  jdbcTemplate.query(GETBYIDSTMT, param, new BeanPropertyRowMapper<BusinessUnit>(BusinessUnit.class));
}

}
