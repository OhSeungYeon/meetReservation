package com.gbm.edu.api.model;

import lombok.Data;

@Data
public class ResBzmBaseModel {

	/*
	 * 처리 결과 코드
	 * (0000은 정상 / 나머지는 오류)
	 * "code ":"0000"
	 */
	private String code;

	/*
	 * 결과 응답아이디
	 * (결과 존재시 존재하는 값)
	 * "response_id":1
	 */
	private String response_id;

	/*
	 * 메시지를 수신한 시간
	 * (* realtime 발송 성공시에만 존재. 단, 읽은 시간은 아님)
	 * "responsed_at":"2015-08-06 10:51:00"
	 */
	private String received_at;

	/**
	 * 결과를 응답한 시간
	 * (결과 존재시 존재하는 값)
	 */
	private String responsed_at;

	/*
	 * 오류 메시지
	 * (오류시 존재하는 값)
	 * "message ":"ResponseHistoryNotFoundException(2)"
	 */
	private String message;

	/**
	 * 업로드된 이미지 url
	 */
	private String image;

	/*
	 * 메시지일련번호 (메시지에 대한 고유값)
	 * 8자리 발송요청일자-일련번호
	 * 예) yyyyMMdd-일련번호
	 */
	private String serial_number;

	/*
	 * 메시지 전송 결과 수신 채널
	 */
	String channelKey;
}
