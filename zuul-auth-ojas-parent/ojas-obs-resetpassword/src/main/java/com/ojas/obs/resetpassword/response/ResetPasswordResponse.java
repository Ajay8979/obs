package com.ojas.obs.resetpassword.response;

import com.ojas.obs.resetpassword.model.ResetPassword;

public class ResetPasswordResponse {
	private String status;
	private ResetPassword pwd;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ResetPassword getPwd() {
		return pwd;
	}
	public void setPwd(ResetPassword pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "ResetPasswordResponse [status=" + status + ", pwd=" + pwd + "]";
	}
	
}
