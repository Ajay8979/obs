package com.ojas.obs.model;

import java.sql.Timestamp;

/**
 * 
 * @author pnaveen
 *
 */

public class GpaPlan {

	private Integer gpaPlanId;
	private String gpaPlanType;
	private Double gpaPremium;
	private Double totalPremium;
	public Integer getGpaPlanId() {
		return gpaPlanId;
	}
	public void setGpaPlanId(Integer gpaPlanId) {
		this.gpaPlanId = gpaPlanId;
	}
	public String getGpaPlanType() {
		return gpaPlanType;
	}
	public void setGpaPlanType(String gpaPlanType) {
		this.gpaPlanType = gpaPlanType;
	}
	public Double getGpaPremium() {
		return gpaPremium;
	}
	public void setGpaPremium(Double gpaPremium) {
		this.gpaPremium = gpaPremium;
	}
	public Double getTotalPremium() {
		return totalPremium;
	}
	public void setTotalPremium(Double totalPremium) {
		this.totalPremium = totalPremium;
	}
	

	// private String flag;
	/**
	 * @return the gpaPlanId
	 */
	
}
