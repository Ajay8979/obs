package com.ojas.obs.passport.Request;

import java.util.List;

import com.ojas.obs.passport.model.Passport;

//Request Pojo Class
public class PassportRequest {
	private List<Passport> passportList; //This fied is used to send model class request
	private String transaactionType;
	private int pageNo;
	private int pageSize;
	private String status;
	private String sessionId;
	
	public List<Passport> getPassportList() {
		return passportList;
	}
	public void setPassportList(List<Passport> passportList) {
		this.passportList = passportList;
	}
	public String getTransaactionType() {
		return transaactionType;
	}
	public void setTransaactionType(String transaactionType) {
		this.transaactionType = transaactionType;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	/*
	 * @Override public int hashCode() { final int prime = 31; int result = 1;
	 * result = prime * result + pageNo; result = prime * result + pageSize; result
	 * = prime * result + ((passportList == null) ? 0 : passportList.hashCode());
	 * result = prime * result + ((sessionId == null) ? 0 : sessionId.hashCode());
	 * result = prime * result + ((status == null) ? 0 : status.hashCode()); result
	 * = prime * result + ((transaactionType == null) ? 0 :
	 * transaactionType.hashCode()); return result; }
	 */
	/*
	 * @Override public boolean equals(Object obj) { if (this == obj) return true;
	 * if (obj == null) return false; if (getClass() != obj.getClass()) return
	 * false; PassportRequest other = (PassportRequest) obj; if (pageNo !=
	 * other.pageNo) return false; if (pageSize != other.pageSize) return false; if
	 * (passportList == null) { if (other.passportList != null) return false; } else
	 * if (!passportList.equals(other.passportList)) return false; if (sessionId ==
	 * null) { if (other.sessionId != null) return false; } else if
	 * (!sessionId.equals(other.sessionId)) return false; if (status == null) { if
	 * (other.status != null) return false; } else if (!status.equals(other.status))
	 * return false; if (transaactionType == null) { if (other.transaactionType !=
	 * null) return false; } else if
	 * (!transaactionType.equals(other.transaactionType)) return false; return true;
	 * }
	 */
	
}
