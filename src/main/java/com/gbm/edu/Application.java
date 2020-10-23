package com.gbm.edu;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.gbm.edu.config.DBEncKeyProperties;
import com.gbm.edu.config.MybatisConfiguration;
import com.gbm.edu.util.ProcessIDUtil;
import com.gsitm.base1.multidatasource.MultiDataSourceAutoConfiguration;

@EnableAutoConfiguration
@Import({MultiDataSourceAutoConfiguration.class, MybatisConfiguration.class})
@SpringBootApplication
@EnableScheduling
@Order(Ordered.HIGHEST_PRECEDENCE)
public class Application {

	/*
	@Autowired
	CommonInterceptor commonInterceptor ;
	*/

    @Autowired
	private DBEncKeyProperties dbProps;

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
		return new WebMvcConfigurerAdapter() {

			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				// 공통 Interceptor 등록
				//registry.addInterceptor(commonInterceptor).addPathPatterns("/**").excludePathPatterns("/error");
			}


		};
	}

	// ServerAgent 초기화 진행
	@PostConstruct
	public void postConstruct() {
		ProcessIDUtil.props = dbProps;	// com.gbm.server.util.ProcessIDUtil.props.getEncKey() 를 static으로 사용하기 위해 어플리케이션 실행 시 최초 셋팅
	}


}
