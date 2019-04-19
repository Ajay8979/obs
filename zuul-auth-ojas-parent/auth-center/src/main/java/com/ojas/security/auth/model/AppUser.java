package com.ojas.security.auth.model;

public class AppUser {

	private String UserId;
	private String UserName;
	private String encrytedPassword;

	public AppUser() {

	}

	public AppUser(String userId, String userName, String encrytedPassword) {
		super();
		UserId = userId;
		UserName = userName;
		this.encrytedPassword = encrytedPassword;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getEncrytedPassword() {
		return encrytedPassword;
	}

	public void setEncrytedPassword(String encrytedPassword) {
		this.encrytedPassword = encrytedPassword;
	}

}
