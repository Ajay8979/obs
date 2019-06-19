package com.ojas.obs.response;

import java.util.List;

import com.ojas.obs.model.Skill;

public class SkillResponse {
	
	private List<Skill> listOfSkill;
   private String statusCode;
	private String message;
	public List<Skill> getListOfSkill() {
		return listOfSkill;
	}
	public void setListOfSkill(List<Skill> listOfSkill) {
		this.listOfSkill = listOfSkill;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listOfSkill == null) ? 0 : listOfSkill.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((statusCode == null) ? 0 : statusCode.hashCode());
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
		SkillResponse other = (SkillResponse) obj;
		if (listOfSkill == null) {
			if (other.listOfSkill != null)
				return false;
		} else if (!listOfSkill.equals(other.listOfSkill))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (statusCode == null) {
			if (other.statusCode != null)
				return false;
		} else if (!statusCode.equals(other.statusCode))
			return false;
		return true;
	}
	
}
