package com.obs.employeeCertificationDetails.error;

public class ErrorResponse {
  private String code;
  private String message;
  private String statusMessage;
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public String getStatusMessage() {
	return statusMessage;
}
public void setStatusMessage(String statusMessage) {
	this.statusMessage = statusMessage;
}
@Override
public String toString() {
	return "ErrorResponse [code=" + code + ", message=" + message + ", statusMessage=" + statusMessage + "]";
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((code == null) ? 0 : code.hashCode());
	result = prime * result + ((message == null) ? 0 : message.hashCode());
	result = prime * result + ((statusMessage == null) ? 0 : statusMessage.hashCode());
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
	if (code == null) {
		if (other.code != null)
			return false;
	} else if (!code.equals(other.code))
		return false;
	if (message == null) {
		if (other.message != null)
			return false;
	} else if (!message.equals(other.message))
		return false;
	if (statusMessage == null) {
		if (other.statusMessage != null)
			return false;
	} else if (!statusMessage.equals(other.statusMessage))
		return false;
	return true;
}

  
   
}
