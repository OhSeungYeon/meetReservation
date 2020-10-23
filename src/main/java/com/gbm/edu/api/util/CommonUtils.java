package com.gbm.edu.api.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * CommonUtils.java
 * @author 정희권(C1U3856)
 * @since 2018. 8. 21.
 */
public class CommonUtils {
	/**
	 * 클라이언트 아이피 추출
	 * @param request
	 * @return
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
        String ip = null;
        ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-RealIP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("REMOTE_ADDR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

	/**
	 * 모델 클래스 맵 변환
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<String, Object> objToMap(Object obj) {
		if(ObjectUtils.isEmpty(obj)) {
			return null;
		}

		ObjectMapper objectMapper = new ObjectMapper();
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			map = objectMapper.convertValue(obj, HashMap.class);
		} catch(Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	/**
	 * JSONString 맵 변환
	 * @param request
	 * @return
	 */
	public static HashMap<String, Object> jsonToMap(String obj) {
		if(ObjectUtils.isEmpty(obj)) {
			return null;
		}

		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();

		TypeReference<HashMap<String,Object>> typeRef = new TypeReference<HashMap<String,Object>>() {};

		try {
			map = objectMapper.readValue(obj, typeRef);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return (HashMap<String, Object>) map;
	}

	/**
	 * JSONString List 변환
	 * @param request
	 * @return
	 */
	public static List<?> jsonToList(String obj) {
		if(ObjectUtils.isEmpty(obj)) {
			return null;
		}

		ObjectMapper objectMapper = new ObjectMapper();
		List<?> list = new ArrayList<>();

		//TypeReference<HashMap<String,Object>> typeRef = new TypeReference<HashMap<String,Object>>() {};

		try {
			list = objectMapper.readValue(obj, List.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * Object JSONString 변환
	 * @param obj
	 * @return
	 */
	public static String objToJsonString(Object obj) {
		if(ObjectUtils.isEmpty(obj)) {
			return "";
		}

		ObjectMapper mapper = new ObjectMapper();
		String result = "";
		try {
			result = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 랜덤 키 생성
	 * @param Integer length 길이
	 * @param Boolean useLetters 문자열포함
	 * @param Boolean useNumbers 숫자포함
	 * @return
	 */
	public static String generateRandomKey(Integer length, Boolean useLetters, Boolean useNumbers) {
	    String generated = RandomStringUtils.random(length, useLetters, useNumbers);

	    return generated;
	}

	/**
	 * XSS 관련 시큐어 코딩
	 * @param String data 문자열
	 * @return
	 */
	public static String XSSFilter(String data){
		if(data != null) {
			data = data
			.replaceAll("&", "&amp;")
			.replaceAll("#", "&#35;")
			.replaceAll("<", "&lt;")
			.replaceAll(">", "&gt;")
			.replaceAll("[(]", "&#40;")
			.replaceAll("[)]", "&#41;")
			.replaceAll("\'", "&#39;")
			.replaceAll("\"", "&quot;");
		}

		return data;
	}

	/**
	 * array 중복제거 메소드(LinkedHashSet 사용)
	 *
	 * @param String[] array
	 * @return
	 */
	public static Object[] removeDuplicateArray(String[] array){
		{
			Object[] removeArray=null;

			LinkedHashSet<String> lhs = new LinkedHashSet<>();

			for(int i=0; i<array.length; i++){
				lhs.add(array[i]);
			}
			removeArray= lhs.toArray();

			return removeArray;
		}
	}

	/**
	 * 숫자유효성검사
	 * 숫자만 입력되었을경우 true로 리턴 그렇지 않을경우 false 리턴
	 * @param String
	 * @return Boolean(true, false)
	 */
	public static Boolean numberMatches(String stParam){
		String regExp = "^[0-9]+$"; //숫자유효성검사
		Boolean flag = false;

		if(stParam.matches(regExp) ) {
			flag = true;
		}

		return flag;
	}

	/**
	 * 전화번호 유효성 검증
	 *
	 * @param callNo
	 * @return
	 */
    public static boolean validCallNo(String callNo) {

    	String regExp = "^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$"; //숫자유효성검사
		Boolean flag = false;

		if(callNo.matches(regExp) ) {
			flag = true;
		}

		return flag;
    }
}
