package com.gbm.edu.model;

import java.util.Date;

import lombok.Data;

@Data
public class Domain {

	private String cudMode;
	/**
	 * 최초등록자 아이디
	 */
    private String regId;

	/**
	 * 최초등록자 아이피
	 */
    private String regIp;

	/**
	 * 최초등록일
	 */
    private Date regDt = new Date();

	/**
	 * 최종변경자 아이디
	 */
    private String modId;

	/**
	 * 최종변경자 아이피
	 */
    private String modIp;

	/**
	 * 최종변경일
	 */
    private Date modDt = new Date();

    
    
    
	public String getCudMode() {
		return cudMode;
	}

	public void setCudMode(String cudMode) {
		this.cudMode = cudMode;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegIp() {
		return regIp;
	}

	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}

	public Date getRegDt() {
		return regDt;
	}

	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}

	public String getModId() {
		return modId;
	}

	public void setModId(String modId) {
		this.modId = modId;
	}

	public String getModIp() {
		return modIp;
	}

	public void setModIp(String modIp) {
		this.modIp = modIp;
	}

	public Date getModDt() {
		return modDt;
	}

	public void setModDt(Date modDt) {
		this.modDt = modDt;
	}
    
    
    
    
}