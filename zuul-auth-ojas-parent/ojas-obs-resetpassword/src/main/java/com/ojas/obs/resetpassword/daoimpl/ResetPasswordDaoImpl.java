package com.ojas.obs.resetpassword.daoimpl;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ojas.obs.resetpassword.dao.ResetPasswordDao;
import com.ojas.obs.resetpassword.model.ResetPassword;
import com.ojas.obs.resetpassword.request.ResetPasswordRequest;

@Repository
public class ResetPasswordDaoImpl implements ResetPasswordDao {
	private static final String RESETPWDSTMT = "update ojas_obs.obs_employee_login set password = ?, updatedBy=?, updatedOn=? where employeeId=?";
	private static final String GETPWDSTMT = "select password from ojas_obs.obs_employee_login where employeeId=";
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private PasswordEncoder passwordEncode;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	Logger logger = Logger.getLogger(this.getClass());

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean updatePassword(ResetPasswordRequest resetPasswordRequest) {

		ResetPassword resetPassword = resetPasswordRequest.getPwd();
		boolean b = false;
		int update = 0;
		/*
		 * List<Object[]> list = new ArrayList<>();
		 * 
		 * Object[] pwd = new Object[] {
		 * this.passwordEncode.encode(password.getNewPassword()),
		 * password.getUpdatedBy(), new Timestamp(new Date().getTime()),
		 * password.getEmployeeId(),
		 * this.passwordEncode.encode(password.getCurruntPassword()) }; list.add(pwd);
		 */
		String pass = jdbcTemplate.queryForObject(GETPWDSTMT + resetPassword.getEmployeeId(), String.class);
		System.out.println("password "+jdbcTemplate.getDataSource().hashCode());
		if (this.passwordEncode.matches(resetPassword.getCurruntPassword(), pass)) {
			logger.debug("Password matched");
			update = jdbcTemplate.update(RESETPWDSTMT, this.passwordEncode.encode(resetPassword.getNewPassword()),
					resetPassword.getUpdatedBy(), new Timestamp(new Date().getTime()), resetPassword.getEmployeeId());
		}
		if (update > 0) {
			b = true;
		}
		return b;
	}

}
