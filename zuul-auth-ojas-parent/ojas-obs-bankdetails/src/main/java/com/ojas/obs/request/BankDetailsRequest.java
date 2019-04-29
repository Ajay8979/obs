package com.ojas.obs.request;

import java.util.List;

import com.ojas.obs.model.BankDetails;

/**
 * 
 * @author akrishna
 *
 */
public class BankDetailsRequest {

	private List<BankDetails> bankDetails;
	private int pageNo;
	private int pageSize;
	private int totalCount;
	private String transactionType;

	public List<BankDetails> getBankDetails() {
		return bankDetails;
	}

	public void setBankDetails(List<BankDetails> bankDetails) {
		this.bankDetails = bankDetails;
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

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	@Override
	public String toString() {
		return "BankDetailsRequest [bankDetails=" + bankDetails + ", pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", totalCount=" + totalCount + ", transactionType=" + transactionType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bankDetails == null) ? 0 : bankDetails.hashCode());
		result = prime * result + pageNo;
		result = prime * result + pageSize;
		result = prime * result + totalCount;
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
		BankDetailsRequest other = (BankDetailsRequest) obj;
		if (bankDetails == null) {
			if (other.bankDetails != null)
				return false;
		} else if (!bankDetails.equals(other.bankDetails))
			return false;
		if (pageNo != other.pageNo)
			return false;
		if (pageSize != other.pageSize)
			return false;
		if (totalCount != other.totalCount)
			return false;
		if (transactionType == null) {
			if (other.transactionType != null)
				return false;
		} else if (!transactionType.equals(other.transactionType))
			return false;
		return true;
	}

	
}
