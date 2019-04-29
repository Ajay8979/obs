package com.ojas.obs.request;

import java.util.List;

import com.ojas.obs.model.EmployeeExperienceDetails;

public class ExperienceRequest {
	private String transactionType;
	List<EmployeeExperienceDetails> employeeExperienceDetails;
	private String sessionId;
	public ExperienceRequest() {
		super();
	}
	
	public ExperienceRequest(String transactionType,
			List<EmployeeExperienceDetails> employeeExperienceDetails, String sessionId) {
		super();
		this.transactionType = transactionType;
		this.employeeExperienceDetails = employeeExperienceDetails;
		this.sessionId = sessionId;
	}

	public List<EmployeeExperienceDetails> getEmployeeExperienceDetails() {
		return employeeExperienceDetails;
	}

	public void setEmployeeExperienceDetails(List<EmployeeExperienceDetails> employeeExperienceDetails) {
		this.employeeExperienceDetails = employeeExperienceDetails;
	}

	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	/*
	 * @Override public int hashCode() { final int prime = 31; int result = 1;
	 * result = prime * result + ((employeeExperienceDetails == null) ? 0 :
	 * employeeExperienceDetails.hashCode()); result = prime * result + ((sessionId
	 * == null) ? 0 : sessionId.hashCode()); result = prime * result +
	 * ((transactionType == null) ? 0 : transactionType.hashCode()); return result;
	 * }
	 * 
	 * @Override public boolean equals(Object obj) { if (this == obj) return true;
	 * if (obj == null) return false; if (getClass() != obj.getClass()) return
	 * false; ExperienceRequest other = (ExperienceRequest) obj; if
	 * (employeeExperienceDetails == null) { if (other.employeeExperienceDetails !=
	 * null) return false; } else if
	 * (!employeeExperienceDetails.equals(other.employeeExperienceDetails)) return
	 * false; if (sessionId == null) { if (other.sessionId != null) return false; }
	 * else if (!sessionId.equals(other.sessionId)) return false; if
	 * (transactionType == null) { if (other.transactionType != null) return false;
	 * } else if (!transactionType.equals(other.transactionType)) return false;
	 * return true; }
	 */
	
}
