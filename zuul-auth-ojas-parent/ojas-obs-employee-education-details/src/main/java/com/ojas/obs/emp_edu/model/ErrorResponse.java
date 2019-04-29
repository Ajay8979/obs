package com.ojas.obs.emp_edu.model;

public class ErrorResponse {
	private String statuscode;
	private String StatusMessage;
	private String message;
	public String getStatuscode() {
		return statuscode;
	}
	public void setStatuscode(String statuscode) {
		this.statuscode = statuscode;
	}
	public String getStatusMessage() {
		return StatusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		StatusMessage = statusMessage;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((StatusMessage == null) ? 0 : StatusMessage.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((statuscode == null) ? 0 : statuscode.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErrorResponse other = (ErrorResponse) obj;
		if (StatusMessage == null) {
			if (other.StatusMessage != null)
				return false;
		} else if (!StatusMessage.equals(other.StatusMessage))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (statuscode == null) {
			if (other.statuscode != null)
				return false;
		} else if (!statuscode.equals(other.statuscode))
			return false;
		return true;
	}
	

}
