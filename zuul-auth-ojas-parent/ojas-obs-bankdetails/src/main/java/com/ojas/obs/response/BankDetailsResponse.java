package com.ojas.obs.response;

import java.util.List;

import com.ojas.obs.model.BankDetails;

/**
 * 
 * @author akrishna
 *
 */
public class BankDetailsResponse {

	private List<BankDetails> listBankDetails;
	private String statusMessage;
	private Integer pageNo;
	private Integer pageSize;
	private Integer totalCount;

	public List<BankDetails> getListBankDetails() {
		return listBankDetails;
	}

	public void setListBankDetails(List<BankDetails> listBankDetails) {
		this.listBankDetails = listBankDetails;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public String toString() {
		return "EmployeeBankDetailsResponse [listBankDetails=" + listBankDetails + ", statusMessage=" + statusMessage
				+ ", pageNo=" + pageNo + ", pageSize=" + pageSize + ", totalCount=" + totalCount + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listBankDetails == null) ? 0 : listBankDetails.hashCode());
		result = prime * result + ((pageNo == null) ? 0 : pageNo.hashCode());
		result = prime * result + ((pageSize == null) ? 0 : pageSize.hashCode());
		result = prime * result + ((statusMessage == null) ? 0 : statusMessage.hashCode());
		result = prime * result + ((totalCount == null) ? 0 : totalCount.hashCode());
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
		BankDetailsResponse other = (BankDetailsResponse) obj;
		if (listBankDetails == null) {
			if (other.listBankDetails != null)
				return false;
		} else if (!listBankDetails.equals(other.listBankDetails))
			return false;
		if (pageNo == null) {
			if (other.pageNo != null)
				return false;
		} else if (!pageNo.equals(other.pageNo))
			return false;
		if (pageSize == null) {
			if (other.pageSize != null)
				return false;
		} else if (!pageSize.equals(other.pageSize))
			return false;
		if (statusMessage == null) {
			if (other.statusMessage != null)
				return false;
		} else if (!statusMessage.equals(other.statusMessage))
			return false;
		if (totalCount == null) {
			if (other.totalCount != null)
				return false;
		} else if (!totalCount.equals(other.totalCount))
			return false;
		return true;
	}

}
