package com.ojas.obs.response;

import java.util.List;

import com.ojas.obs.model.EmployeeExperienceDetails;

public class ExperienceResponse {
	private List<EmployeeExperienceDetails> employeeExperienceDetails;
	private String statusCode;
	private String statusMessage;
	public ExperienceResponse() {
		super();
	}
	public ExperienceResponse(List<EmployeeExperienceDetails> employeeExperienceDetails,
			String statusCode, String statusMessage) {
		super();
		this.employeeExperienceDetails = employeeExperienceDetails;
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}
	public List<EmployeeExperienceDetails> getEmployeeExperienceDetails() {
		return employeeExperienceDetails;
	}
	public void setEmployeeExperienceDetails(List<EmployeeExperienceDetails> employeeExperienceDetails) {
		this.employeeExperienceDetails = employeeExperienceDetails;
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
	/*
	 * @Override public int hashCode() { final int prime = 31; int result = 1;
	 * result = prime * result + ((employeeExperienceDetails == null) ? 0 :
	 * employeeExperienceDetails.hashCode()); result = prime * result + ((statusCode
	 * == null) ? 0 : statusCode.hashCode()); result = prime * result +
	 * ((statusMessage == null) ? 0 : statusMessage.hashCode()); return result; }
	 * 
	 * @Override public boolean equals(Object obj) { if (this == obj) return true;
	 * if (obj == null) return false; if (getClass() != obj.getClass()) return
	 * false; ExperienceResponse other = (ExperienceResponse) obj; if
	 * (employeeExperienceDetails == null) { if (other.employeeExperienceDetails !=
	 * null) return false; } else if
	 * (!employeeExperienceDetails.equals(other.employeeExperienceDetails)) return
	 * false; if (statusCode == null) { if (other.statusCode != null) return false;
	 * } else if (!statusCode.equals(other.statusCode)) return false; if
	 * (statusMessage == null) { if (other.statusMessage != null) return false; }
	 * else if (!statusMessage.equals(other.statusMessage)) return false; return
	 * true; }
	 */}
