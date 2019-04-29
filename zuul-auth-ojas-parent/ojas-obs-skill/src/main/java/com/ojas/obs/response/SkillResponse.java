package com.ojas.obs.response;

import java.util.List;

import com.ojas.obs.model.Skill;

public class SkillResponse {
	
	private List<Skill> listOfSkill;
	private int totalCount;
	private String statusMessage;
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public List<Skill> getListOfSkill() {
		return listOfSkill;
	}
	public void setListOfSkill(List<Skill> listOfSkill) {
		this.listOfSkill = listOfSkill;
	}

}
