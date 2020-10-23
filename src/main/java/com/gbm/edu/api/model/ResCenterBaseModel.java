package com.gbm.edu.api.model;

import lombok.Data;

@Data
public class ResCenterBaseModel<T> {
	
	/*
	 * 처리 결과 코드
	 * (0000은 정상 / 나머지는 오류)
	 */
	private String code;
	
	/*
	 * 오류 메시지
	 * (오류시 존재하는 값)
	 */
	private String message;
	
	/*
	 * 카테고리 이름.
	 */
	private String name;
	
	/*
	 * 결과 데이터.
	 */
	private T data;
}
