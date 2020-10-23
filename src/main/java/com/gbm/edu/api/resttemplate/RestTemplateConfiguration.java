package com.gbm.edu.api.resttemplate;

import java.util.concurrent.TimeUnit;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Rest Template Configuration
 */
@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(getRequestFactoryAdvanced());
    }

    private ClientHttpRequestFactory getRequestFactoryAdvanced() {
        RequestConfig config = RequestConfig.custom()
                .setSocketTimeout(30000)
                .setConnectTimeout(30000)
                .setConnectionRequestTimeout(30000)
                .build();

        CloseableHttpClient client = HttpClientBuilder
                .create()
                .setDefaultRequestConfig(config)
                // 최대 오픈되는 커넥션 수
                .setMaxConnTotal(1000)
                // 커넥션풀적용(IP:포트 1쌍에 대해 수행 할 연결 수제한)
                .setMaxConnPerRoute(1000)
                //서버에서 keepalive시간동안 미 사용한 커넥션을 죽이는 등의 케이스 방어로 idle커넥션을 주기적으로 지움
                .evictIdleConnections(2000L, TimeUnit.MILLISECONDS)
                .build();

        return new HttpComponentsClientHttpRequestFactory(client);
    }
}