package com.ojas.obs.passport.Response;

import java.util.List;

import com.ojas.obs.passport.model.Passport;

//Request Pojo Class
public class PassportResponse {
	
	private List<Passport> passportList; //This field is used to display the list of Model Class;
	private String statusCode;
	private String statusMessage;
	private  Integer noOfRecords;
	public List<Passport> getPassportList() {
		return passportList;
	}
	public void setPassportList(List<Passport> passportList) {
		this.passportList = passportList;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public Integer getNoOfRecords() {
		return noOfRecords;
	}
	public void setNoOfRecords(Integer noOfRecords) {
		this.noOfRecords = noOfRecords;
	}
	/*
	 * @Override public int hashCode() { final int prime = 31; int result = 1;
	 * result = prime * result + ((noOfRecords == null) ? 0 :
	 * noOfRecords.hashCode()); result = prime * result + ((passportList == null) ?
	 * 0 : passportList.hashCode()); result = prime * result + ((statusCode == null)
	 * ? 0 : statusCode.hashCode()); result = prime * result + ((statusMessage ==
	 * null) ? 0 : statusMessage.hashCode()); return result; }
	 * 
	 * @Override public boolean equals(Object obj) { if (this == obj) return true;
	 * if (obj == null) return false; if (getClass() != obj.getClass()) return
	 * false; PassportResponse other = (PassportResponse) obj; if (noOfRecords ==
	 * null) { if (other.noOfRecords != null) return false; } else if
	 * (!noOfRecords.equals(other.noOfRecords)) return false; if (passportList ==
	 * null) { if (other.passportList != null) return false; } else if
	 * (!passportList.equals(other.passportList)) return false; if (statusCode ==
	 * null) { if (other.statusCode != null) return false; } else if
	 * (!statusCode.equals(other.statusCode)) return false; if (statusMessage ==
	 * null) { if (other.statusMessage != null) return false; } else if
	 * (!statusMessage.equals(other.statusMessage)) return false; return true; }
	 */
	
}
