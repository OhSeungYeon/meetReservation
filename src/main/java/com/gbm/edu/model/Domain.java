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
}