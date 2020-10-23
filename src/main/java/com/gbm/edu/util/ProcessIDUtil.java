package com.gbm.edu.util;

import java.util.UUID;


import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import com.gbm.edu.config.DBEncKeyProperties;
import com.jcraft.jsch.Logger;


@Component
public class ProcessIDUtil {
	//ProcessIDUtil.props.getEncKey() 전역적으로 메서드 사용
	public static DBEncKeyProperties props;


	public static String uuid(){
		return UUID.randomUUID().toString();
	}

	public static String prcsId(){
		String prcs_id = MDC.get("PRCS_ID");

		if(prcs_id.equals("")) {
			System.out.println("PRCS_ID NEW Generation");
			prcs_id = ProcessIDUtil.uuid();
		}

		return prcs_id;
	}
}
