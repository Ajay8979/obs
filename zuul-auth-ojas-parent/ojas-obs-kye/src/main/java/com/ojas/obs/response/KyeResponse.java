package com.ojas.obs.response;

import java.util.List;

import com.ojas.obs.model.KYE;

/**
 * 
 * @author tshiva
 *
 */
public class KyeResponse {
	private List<KYE> kyeList;
	private int pageNo;
	private int pageSize;
	private int totalCount;
	private String statusMessage;
	private String statusCode;

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public List<KYE> getKyeList() {
		return kyeList;
	}

	public void setKyeList(List<KYE> kyeList) {
		this.kyeList = kyeList;
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

}
