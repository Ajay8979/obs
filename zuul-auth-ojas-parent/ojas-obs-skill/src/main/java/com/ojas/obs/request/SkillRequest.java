package com.ojas.obs.request;

import java.util.List;

import com.ojas.obs.model.Skill;

public class SkillRequest {

	
	private List<Skill> listOfSkill;
	private String transactionType;
	public List<Skill> getListOfSkill() {
		return listOfSkill;
	}
	public void setListOfSkill(List<Skill> listOfSkill) {
		this.listOfSkill = listOfSkill;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listOfSkill == null) ? 0 : listOfSkill.hashCode());
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
		SkillRequest other = (SkillRequest) obj;
		if (listOfSkill == null) {
			if (other.listOfSkill != null)
				return false;
		} else if (!listOfSkill.equals(other.listOfSkill))
			return false;
		if (transactionType == null) {
			if (other.transactionType != null)
				return false;
		} else if (!transactionType.equals(other.transactionType))
			return false;
		return true;
	}
	
	
}
