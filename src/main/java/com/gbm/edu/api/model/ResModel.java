package com.gbm.edu.api.model;

import java.util.Date;

import com.gbm.edu.model.Domain;

import lombok.Data;

@Data
public class ResModel extends Domain {
	// 회의실예약(reservation) 테이블

	// 회의실아이디
	private String meetingId;

	// 사용자아이디
	private String username;

	// 예약자
	private String userNm;

	// 상태
	// private String state;

	// 용건
	private String reason;

	// 사용시작시간
	private String startTm;

	// 사용종료시간
	private String finishTm;

	// 승인시간
	private String approvalTm;

	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	/*
	 * public String getState() { return state; }
	 * 
	 * public void setState(String state) { this.state = state; }
	 */

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStartTm() {
		return startTm;
	}

	public void setStartTm(String startTm) {
		this.startTm = startTm;
	}

	public String getFinishTm() {
		return finishTm;
	}

	public void setFinishTm(String finishTm) {
		this.finishTm = finishTm;
	}

	public String getApprovalTm() {
		return approvalTm;
	}

	public void setApprovalTm(String approvalTm) {
		this.approvalTm = approvalTm;
	}

	

}
