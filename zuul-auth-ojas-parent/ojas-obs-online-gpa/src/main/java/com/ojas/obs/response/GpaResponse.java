package com.ojas.obs.response;

import java.util.List;

import com.ojas.obs.model.GpaPlan;
/**
 * 
 * @author pnaveen
 *
 */

public class GpaResponse {

	List<GpaPlan> gpa;


	private Integer totalCount;
	private Integer pageSize;
	private Integer pageNum;
	private String statusMessage;

	/**
	 * 
	 * @return
	 */
	public List<GpaPlan> getGpa() {
		return gpa;
	}

	/**
	 * 
	 * @param gpa
	 */
	public void setGpa(List<GpaPlan> gpa) {
		this.gpa = gpa;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getTotalCount() {
		return totalCount;
	}

	/**
	 * 
	 * @param totalCount
	 */
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * 
	 * @param pageSize
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/***
	 * 
	 * @return
	 */
	public Integer getPageNum() {
		return pageNum;
	}

	/**
	 * 
	 * @param pageNum
	 */
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	/**
	 * 
	 * @return
	 */
	public String getStatusMessage() {
		return statusMessage;
	}

	/**
	 * 
	 * @param statusMessage
	 */
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
}