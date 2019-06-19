package com.obs.employeeCertificationDetails.response;

import java.util.List;

import com.obs.employeeCertificationDetails.model.CertificationDetails;

public class CertificationDetailsResponse {

private List<CertificationDetails> getCertificationDetailsModelist;
private String message;
private String statusCode;
private List<CertificationDetails> certificationDetailsList;
public List<CertificationDetails> getGetCertificationDetailsModelist() {
	return getCertificationDetailsModelist;
}
public void setGetCertificationDetailsModelist(List<CertificationDetails> getCertificationDetailsModelist) {
	this.getCertificationDetailsModelist = getCertificationDetailsModelist;
}
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
public List<CertificationDetails> getCertificationDetailsList() {
	return certificationDetailsList;
}
public void setCertificationDetailsList(List<CertificationDetails> certificationDetailsList) {
	this.certificationDetailsList = certificationDetailsList;
}
@Override
public String toString() {
	return "CertificationDetailsResponse [getCertificationDetailsModelist=" + getCertificationDetailsModelist
			+ ", message=" + message + ", statusCode=" + statusCode + ", certificationDetailsList="
			+ certificationDetailsList + "]";
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((certificationDetailsList == null) ? 0 : certificationDetailsList.hashCode());
	result = prime * result
			+ ((getCertificationDetailsModelist == null) ? 0 : getCertificationDetailsModelist.hashCode());
	result = prime * result + ((message == null) ? 0 : message.hashCode());
	result = prime * result + ((statusCode == null) ? 0 : statusCode.hashCode());
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
	CertificationDetailsResponse other = (CertificationDetailsResponse) obj;
	if (certificationDetailsList == null) {
		if (other.certificationDetailsList != null)
			return false;
	} else if (!certificationDetailsList.equals(other.certificationDetailsList))
		return false;
	if (getCertificationDetailsModelist == null) {
		if (other.getCertificationDetailsModelist != null)
			return false;
	} else if (!getCertificationDetailsModelist.equals(other.getCertificationDetailsModelist))
		return false;
	if (message == null) {
		if (other.message != null)
			return false;
	} else if (!message.equals(other.message))
		return false;
	if (statusCode == null) {
		if (other.statusCode != null)
			return false;
	} else if (!statusCode.equals(other.statusCode))
		return false;
	return true;
}




}
