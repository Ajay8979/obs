package com.ojas.obs.error;
/**
 * 
 * @author asuneel
 *
 */
public class ErrorResponse {
	private String message;
	private String statusCode;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

}
