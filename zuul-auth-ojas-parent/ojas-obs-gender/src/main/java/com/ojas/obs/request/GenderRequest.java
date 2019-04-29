package com.ojas.obs.request;

import java.util.List;

import com.ojas.obs.model.Genders;

public class GenderRequest {
  private List<Genders> gender;
  private String sessionId;
  private int pageNo;
  private int pageSize;
  private String transactionType;

public List<Genders> getGender() {
	return gender;
}
public void setGender(List<Genders> gender) {
	this.gender = gender;
}
public String getSessionId() {
	return sessionId;
}
public void setSessionId(String sessionId) {
	this.sessionId = sessionId;
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
public String getTransactionType() {
	return transactionType;
}
public void setTransactionType(String transactionType) {
	this.transactionType = transactionType;
}
  

}
