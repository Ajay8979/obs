package com.ojas.obs.error;

public class ErrorResponse {
	private String Message;
	private String StatusCode;

	public ErrorResponse() {
		super();
	}

	@Override
	public String toString() {
		return "ErrorResponse [Message=" + Message + ", StatusCode=" + StatusCode + "]";
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getStatusCode() {
		return StatusCode;
	}

	public void setStatusCode(String statusCode) {
		StatusCode = statusCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Message == null) ? 0 : Message.hashCode());
		result = prime * result + ((StatusCode == null) ? 0 : StatusCode.hashCode());
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
		if (Message == null) {
			if (other.Message != null)
				return false;
		} else if (!Message.equals(other.Message))
			return false;
		if (StatusCode == null) {
			if (other.StatusCode != null)
				return false;
		} else if (!StatusCode.equals(other.StatusCode))
			return false;
		return true;
	}

	
	

}
