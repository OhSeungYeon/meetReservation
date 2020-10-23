package com.gbm.edu.util;

import java.util.HashMap;


/**
 * 프로젝트 전체적인 Naming Rule을 맞추기 위해 MyBatis에서 일반적인 
 * Map을 사용할 경우 컬럼명이 대문자와 Underscore가 붙은 형태로 
 * Map의 key에 set이 되게되어 Java Camel 표기법을 표준으로 하는 
 * 본 프로젝트의 사상과 맞지 않으므로 대문자 + Underscore로 구성된 
 * 컬럼명을 Java Camel 표기법으로 자동으로 변환 시켜주는 유틸리티 클래스이다.
 *
 * @author 조용상
 * @version 1.0
 *
 * <pre>
 * 수정일                수정자         수정내용
 * ---------------------------------------------------------------------
 * </pre>
 */
@SuppressWarnings("serial")
public class CamelCaseMap extends HashMap<String, Object> {

    /**
     * Map의 put을 오버라이드해 key값을 camel 표기법으로 변환하여 put 한다.
     * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
     */
    @Override
    public Object put(String key, Object value) {
        return super.put(convertUnderscoreNameToPropertyName(key), value);
    }

    public String getString(String key) {
        return get(key) != null ? get(key).toString() : null;
    }

    public int getInt(String key) {
        return get(key) != null ? Integer.parseInt(get(key).toString()) : 0;
    }

    public long getLong(String key) {
        return get(key) != null ? Long.parseLong(get(key).toString()) : 0;
    }

    public float getFloat(String key) {
        return get(key) != null ? Float.parseFloat(get(key).toString()) : 0f;
    }

    public double getDouble(String key) {
        return get(key) != null ? Double.parseDouble(get(key).toString()) : 0d;
    }
    
	public static String convertUnderscoreNameToPropertyName(String name) {
		StringBuilder result = new StringBuilder();
		boolean nextIsUpper = false;
		if (name != null && name.length() > 0) {
			if (name.length() > 1 && name.substring(1,2).equals("_")) {
				result.append(name.substring(0, 1).toLowerCase());
			}
			else {
				result.append(name.substring(0, 1).toLowerCase());
			}
			for (int i = 1; i < name.length(); i++) {
				String s = name.substring(i, i + 1);
				if (s.equals("_")) {
					nextIsUpper = true;
				}
				else {
					if (nextIsUpper) {
						result.append(s.toUpperCase());
						nextIsUpper = false;
					}
					else {
						result.append(s.toLowerCase());
					}
				}
			}
		}
		return result.toString();
	}
}