package com.obs.employeeCertificationDetails.request;

import java.util.List;

import com.obs.employeeCertificationDetails.model.CertificationDetails;

public class CertificationDetailsRequest {

private List<CertificationDetails> certificationDetailsModel;
private String transactionType;
public List<CertificationDetails> getCertificationDetailsModel() {
	return certificationDetailsModel;
}
public void setCertificationDetailsModel(List<CertificationDetails> certificationDetailsModel) {
	this.certificationDetailsModel = certificationDetailsModel;
}
public String getTransactionType() {
	return transactionType;
}
public void setTransactionType(String transactionType) {
	this.transactionType = transactionType;
}
@Override
public String toString() {
	return "CertificationDetailsRequest [certificationDetailsModel=" + certificationDetailsModel + ", transactionType="
			+ transactionType + "]";
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((certificationDetailsModel == null) ? 0 : certificationDetailsModel.hashCode());
	result = prime * result + ((transactionType == null) ? 0 : transactionType.hashCode());
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
	CertificationDetailsRequest other = (CertificationDetailsRequest) obj;
	if (certificationDetailsModel == null) {
		if (other.certificationDetailsModel != null)
			return false;
	} else if (!certificationDetailsModel.equals(other.certificationDetailsModel))
		return false;
	if (transactionType == null) {
		if (other.transactionType != null)
			return false;
	} else if (!transactionType.equals(other.transactionType))
		return false;
	return true;
}






}
