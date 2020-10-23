package com.gbm.edu.config;

import java.nio.file.Paths;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/*
 * http://blog.saltfactory.net/java/load-yaml-file-in-spring.html
 */
@Data
@Component
@ConfigurationProperties(prefix="base1.fileupload", ignoreUnknownFields=false)
public class StorageProperties {
    /**
     * 첨부파일 저장 루트 경로
     */
    private String location = Paths.get(System.getProperty("user.home"), "base1").toString();

    /**
     * 압축파일 생성 임시 저장 경로
     */
    private String tempLocation = Paths.get(location, "temp").toString();

    /**
     * 업로드 허용 확장자 리스트
     */
    private List<String> allowedExtensions;
    
    /**
     * 업로드 허용 max파일 사이즈
     */
    private long maxFileSize;
    
    /**
     * 파일 Storage 사용시 Alias URI 
     */
    private String storageAliasUri;

    private Batch batch = new Batch();

    @Data
    public static class Batch {
        public static final String DEFAULT_CRON = "0 0 3 * * *";

        /**
         * DB에서 삭제 플래그된 데이터 중 며칠이 지난 데이터를 삭제할지 일자를 입력
         */
        private Integer daysAfterDeleted = 2;

        /**
         * 파일삭제 배치 실행 여부
         */
    	private boolean batchAct = true;
    	
        /**
         * 데이터 삭제를 한 번에 몇 개씩 처리할지에 대한 수
         */
        private Integer chunkSize = 100;

        /**
         * 데이터 삭제 작업을 언제 실행할지 대한 cron express
         */
        private String cron = DEFAULT_CRON;
    }
}