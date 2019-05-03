package com.ojas.obs.resetpassword.dao;

import com.ojas.obs.resetpassword.request.ResetPasswordRequest;


public interface ResetPasswordDao {
	public boolean updatePassword(ResetPasswordRequest resetPasswordRequest);
}
