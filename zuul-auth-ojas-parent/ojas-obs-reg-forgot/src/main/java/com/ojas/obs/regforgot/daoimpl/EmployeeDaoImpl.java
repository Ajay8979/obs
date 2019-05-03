package com.ojas.obs.regforgot.daoimpl;

import static com.ojas.obs.regforgot.constants.UserConstants.SAVEEMPINFO;
import static com.ojas.obs.regforgot.constants.UserConstants.SAVEEMPLOGININFO;
import static com.ojas.obs.regforgot.constants.UserConstants.SAVEEMPROLE;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ojas.obs.regforgot.dao.EmployeeInfoDao;
import com.ojas.obs.regforgot.model.EmployeeInfo;
import com.ojas.obs.regforgot.request.EmployeeInfoRequest;

@Repository
public class EmployeeDaoImpl implements EmployeeInfoDao {
	@Autowired
	private PasswordEncoder passwordEncode;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

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
			List<Object[]> loginList = new ArrayList<>();
			List<Object[]> roleList = new ArrayList<>();

			for (EmployeeInfo employeeInfo : employeeInfoRequest.getEmployeeInfo()) {
				logger.debug("Inside redister dao" + employeeInfo);
				java.sql.Date date = java.sql.Date.valueOf(employeeInfo.getDob());
				Object[] empinfo = { employeeInfo.getFirstname(), employeeInfo.getMiddlename(),
						employeeInfo.getLastname(), employeeInfo.getStatus(), date, employeeInfo.getGender(),
						employeeInfo.getTitle(), employeeInfo.getEmployeeId(), true,
						new Timestamp(new Date().getTime()), employeeInfo.getCreatedBy() };
				employeeInfoList.add(empinfo);
				Object[] empLogin = { employeeInfo.getEmployeeId(),
						this.passwordEncode.encode(employeeInfo.getPassword()), new Timestamp(new Date().getTime()),
						employeeInfo.getCreatedBy() };
				loginList.add(empLogin);
				Object[] empList = { employeeInfo.getEmployeeId(), 2 };
				roleList.add(empList);
				jdbcTemplate.batchUpdate(SAVEEMPROLE, roleList);
				jdbcTemplate.batchUpdate(SAVEEMPLOGININFO, loginList);
			}

			jdbcTemplate.batchUpdate(SAVEEMPINFO, employeeInfoList);
			return true;

		} finally

		{
			jdbcTemplate.getDataSource().getConnection().close();
		}
	}

}