package com.obs.employeeCertificationDetails.response;

import java.util.List;

import com.obs.employeeCertificationDetails.model.CertificationDetails;

public class CertificationDetailsResponse {

private List<CertificationDetails> getCertificationDetailsModelist;
private int pageNo;
private int  pageSize;
private int  totalCount;
private String statusMassage;
private String statusCode;
private List<CertificationDetails> certificationDetailsList;

public List<CertificationDetails> getGetCertificationDetailsModelist() {
	return getCertificationDetailsModelist;
}
public void setGetCertificationDetailsModelist(List<CertificationDetails> getCertificationDetailsModelist) {
	this.getCertificationDetailsModelist = getCertificationDetailsModelist;
}
public int getPageNo() {
	return pageNo;
}
public void setPageNo(int pageNo) {
	this.pageNo = pageNo;
}
public int getPageSize() {
	return pageSize;
}
public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
}
public int getTotalCount() {
	return totalCount;
}
public void setTotalCount(int totalCount) {
	this.totalCount = totalCount;
}
public String getStatusMassage() {
	return statusMassage;
}
public String getStatusCode() {
	return statusCode;
}
public void setStatusCode(String statusCode) {
	this.statusCode = statusCode;
}
public void setStatusMassage(String statusMassage) {
	this.statusMassage = statusMassage;
}
public List<CertificationDetails> getCertificationDetailsList() {
	return certificationDetailsList;
}
public void setCertificationDetailsList(List<CertificationDetails> certificationDetailsList) {
	this.certificationDetailsList = certificationDetailsList;
}


}
