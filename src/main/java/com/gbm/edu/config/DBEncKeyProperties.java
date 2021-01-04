package com.gbm.edu.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.gbm.edu.util.AES256Util;

import lombok.Data;

/*
 * http://blog.saltfactory.net/java/load-yaml-file-in-spring.html
 */
@Data
@Component
@ConfigurationProperties(prefix="db.key", ignoreUnknownFields=false)
public class DBEncKeyProperties {

	private static final Logger logger = LoggerFactory.getLogger(DBEncKeyProperties.class);
    /**
     * 파일이름
     */
    private String fileName;
    /**
     * 경로
     */
    private String location;
    /**
     * encrypt 공용키
     */
    private String publicKey;


    /**
     * 암호화키
     */
    @SuppressWarnings("unused")
	private String encKey;


    public void setEncKey(){
    	String key = "";
		try {
			key = AES256Util.ResourcePathFileToMemDec(location, fileName, publicKey);
			logger.info("AES256UtilResourcePathFileToMemDec:"+key);
		} catch (Exception e) {
			logger.debug("setEncKey"+e.getMessage());
		}

    	this.encKey = key;
    }

    public String getEncKey(){
    	if(this.encKey == null || this.encKey.equals("")) {
    		this.setEncKey();
    	}
    	return this.encKey;
    }

}
