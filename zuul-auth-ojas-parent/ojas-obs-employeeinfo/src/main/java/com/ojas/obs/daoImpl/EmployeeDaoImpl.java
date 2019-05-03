package com.ojas.obs.daoImpl;

import static com.ojas.obs.constants.UserConstants.DELETEEMPINFO;
import static com.ojas.obs.constants.UserConstants.GETEMPBYID;
import static com.ojas.obs.constants.UserConstants.GETEMPCOUNT;
import static com.ojas.obs.constants.UserConstants.GETEMPDETAILS;
import static com.ojas.obs.constants.UserConstants.GETROLEBYEMPID;
import static com.ojas.obs.constants.UserConstants.SAVEEMPINFO;
import static com.ojas.obs.constants.UserConstants.UPDATEEMPINFO;

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
import org.springframework.transaction.annotation.Transactional;

import com.ojas.obs.dao.EmployeeInfoDao;
import com.ojas.obs.model.EmployeeInfo;
import com.ojas.obs.request.EmployeeInfoRequest;
import com.ojas.obs.response.EmployeeRoleResponse;
import com.ojas.obs.response.RoleResponse;

@Repository
public class EmployeeDaoImpl implements EmployeeInfoDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	Logger logger = Logger.getLogger(this.getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.EmployeeInfoDao#saveEmployeeInfo(com.ojas.obs.request.
	 * EmployeeInfoRequest)
	 */

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean saveEmployeeInfo(EmployeeInfoRequest employeeInfoRequest) throws SQLException {
		try {
			List<Object[]> employeeInfoList = new ArrayList<>();

			for (EmployeeInfo employeeInfo : employeeInfoRequest.getEmployeeInfo()) {
				java.sql.Date date = java.sql.Date.valueOf(employeeInfo.getDob());
				Object[] empinfo = { employeeInfo.getFirstname(), employeeInfo.getMiddlename(),
						employeeInfo.getLastname(), employeeInfo.getStatus(), date, employeeInfo.getGender(),
						employeeInfo.getTitle(), employeeInfo.getEmployeeId(), true,
						new Timestamp(new Date().getTime()), employeeInfo.getCreatedBy() };
				employeeInfoList.add(empinfo);
			}
			jdbcTemplate.batchUpdate(SAVEEMPINFO, employeeInfoList);
			return true;

		} finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ojas.obs.dao.EmployeeInfoDao#updateEmployeeInfo(com.ojas.obs.request.
	 * EmployeeInfoRequest)
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean updateEmployeeInfo(EmployeeInfoRequest employeeInfoRequest) throws SQLException {
		try {
			List<Object[]> inputList = new ArrayList<>();
			for (EmployeeInfo employeeInfo : employeeInfoRequest.getEmployeeInfo()) {
				java.sql.Date date = java.sql.Date.valueOf(employeeInfo.getDob());
				Object[] emp = { employeeInfo.getFirstname(), employeeInfo.getMiddlename(), employeeInfo.getLastname(),
						employeeInfo.getStatus(), date, employeeInfo.getGender(), employeeInfo.getTitle(), employeeInfo.getEmployeeId(),
						new Timestamp(new Date().getTime()), employeeInfo.getUpdatedBy(), employeeInfo.getId() };
				/* ***Update role***
				 * if (null != employeeInfo.getRoleId) { Object[] empRoleDel = {
				 * employeeInfo.getEmployeeId() }; roleListDel.add(empRoleDel);
				 * jdbcTemplate.batchUpdate(DELETEEMPROLE, roleListDel); Object[] empRole = {
				 * employeeInfo.getEmployeeId(), employeeInfo.getRoleId() };
				 * roleList.add(empRole); 
				 * jdbcTemplate.batchUpdate(SAVEEMPROLE, roleList); }
				 */

				inputList.add(emp);
			}
			jdbcTemplate.batchUpdate(UPDATEEMPINFO, inputList);
			return true;

		} finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ojas.obs.dao.EmployeeInfoDao#deleteEmployeeInfo(com.ojas.obs.request.
	 * EmployeeInfoRequest)
	 */
	@Override
	public boolean deleteEmployeeInfo(EmployeeInfoRequest employeeInfoRequest) throws SQLException {
		try {
			List<Object[]> inputList = new ArrayList<Object[]>();
			EmployeeInfo employeeInfo = employeeInfoRequest.getEmployeeInfo().get(0);
			Object[] emp = { false, new Timestamp(new Date().getTime()), employeeInfo.getId() };
			inputList.add(emp);
			/*
			 * Object[] empRole = { employeeInfo.getEmployeeId() }; roleList.add(empRole);
			 * jdbcTemplate.batchUpdate(DELETEEMPROLE, roleList);
			 */
			jdbcTemplate.batchUpdate(DELETEEMPINFO, inputList);
			return true;
		} finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ojas.obs.dao.EmployeeInfoDao#getAllEmployeeDetails(com.ojas.obs.request.
	 * EmployeeInfoRequest)
	 */

//	@Override
//	public List<EmployeeInfo> getAllEmployeeDetails(EmployeeInfoRequest employeeInfoRequest) throws SQLException {
//		List<EmployeeInfo> employeeList = employeeInfoRequest.getEmployeeInfo();
//		logger.debug("**employeeInfoRequest in EmployeeDaoImpl ::" + employeeInfoRequest);
//		StringBuilder builder = new StringBuilder();
//		builder.append(GETEMPDETAILS);
//		
//		
//		
//		for (EmployeeInfo getList : employeeList) {
//			if (getList.getId() != 0) {
//				builder.append("where id = " + getList.getId());
//
//			} else {
//				List<EmployeeInfo> empInfo = jdbcTemplate.query(builder.toString(),
//						new BeanPropertyRowMapper<>(EmployeeInfo.class));
//
//				
//
//				return empInfo;
//			}
//		}
//		return null;
//	}

	@Override
	public List<EmployeeInfo> getById(EmployeeInfoRequest employeeInfoRequest) throws SQLException {
		try {
			List<EmployeeInfo> employeeinfo = null;
			EmployeeInfo employee = employeeInfoRequest.getEmployeeInfo().get(0);

			employeeinfo = jdbcTemplate.query(GETEMPBYID + employee.getId(),
					new BeanPropertyRowMapper<>(EmployeeInfo.class));
			
			/*
			 * List<UserRole> roleList = jdbcTemplate.query(ROLEEMPBYID +
			 * employeeinfo.get(0).getEmployeeId(), new
			 * BeanPropertyRowMapper<>(UserRole.class));
			 * employeeinfo.get(0).setRole(roleList);
			 */
			return employeeinfo;
		} finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}
	}
	
	@Override
	public List<EmployeeInfo> getAllEmployeeDetails(EmployeeInfoRequest employeeInfoRequest) throws SQLException {
		
		try {
			List<EmployeeInfo> employee = jdbcTemplate.query(GETEMPDETAILS, new BeanPropertyRowMapper<>(EmployeeInfo.class));
			
			return employee;
		} finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.EmployeeInfoDao#getAllEmployeeDetailsCount()
	 */
	
	@Override
	public int getAllEmployeeDetailsCount() throws SQLException {
		try {
			int count = jdbcTemplate.queryForObject(GETEMPCOUNT, Integer.class);
			
			return count;
			
		} finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ojas.obs.dao.EmployeeInfoDao#getPageRecords(java.util.List, int,
	 * int)
	 */
	@Override
	public List<EmployeeInfo> getPageRecords(List<EmployeeInfo> allemployeeDetails, int pageSize, int pageNum)
			throws SQLException {
		try {
			List<EmployeeInfo> empInfo = new ArrayList<>();

			if (allemployeeDetails != null && !allemployeeDetails.isEmpty()) {

				pageSize = pageSize > 0 ? pageSize : pageSize * -1;

				pageNum = pageNum > 0 ? pageNum : pageNum == 0 ? 1 : pageNum * -1;

				if (pageSize != 0) {

					int endIndex = pageNum * pageSize;

					int startIndex = endIndex - pageSize;

					endIndex = endIndex < allemployeeDetails.size() ? endIndex : allemployeeDetails.size();

					startIndex = startIndex < allemployeeDetails.size() ? startIndex : 0;

					empInfo = allemployeeDetails.subList(startIndex, endIndex);
				}
			}
			return empInfo;
		} finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}
	}



	@Override
	public EmployeeRoleResponse getRoleById(EmployeeInfoRequest employeeInfoRequest) throws SQLException {
		try {
			EmployeeRoleResponse employeeinfo = new EmployeeRoleResponse();
			RoleResponse roleResponse = null;
			List<EmployeeInfo> employeeList = employeeInfoRequest.getEmployeeInfo();
			for (EmployeeInfo getList : employeeList) {
				roleResponse = jdbcTemplate.queryForObject(GETROLEBYEMPID, new Object[] { getList.getEmployeeId() },
						new RoleRowMapper());
			}
			System.out.println("RoleName" + roleResponse.getRoleName());
			employeeinfo.setRoleName(roleResponse.getRoleName());
			employeeinfo.setRoleId(roleResponse.getRoleId());
			employeeinfo.setEmployeeId(roleResponse.getEmployeeId());
			employeeinfo.setEmpFirstName(roleResponse.getFirstName());
			employeeinfo.setEmpMiddleName(roleResponse.getMiddleName());
			employeeinfo.setEmpLastName(roleResponse.getMiddleName());
			return employeeinfo;

		} finally {
			jdbcTemplate.getDataSource().getConnection().close();
		}
	}

}
