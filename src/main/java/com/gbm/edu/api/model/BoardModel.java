package com.gbm.edu.api.model;

import com.gbm.edu.model.Domain;

import lombok.Data;

@Data
public class BoardModel extends Domain {
	//회의실(meeting) 테이블
	
	//회의실ID
	private String meetingId;
	
	//사용가능인원수
	private Integer avaPerson;
	
	//회의실명
	private String meetingNm;
	
	//회의실설명
	private String meetingEtc;
	
}
