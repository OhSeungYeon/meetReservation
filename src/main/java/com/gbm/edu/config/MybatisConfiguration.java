package com.gbm.edu.config;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import com.gbm.edu.model.Domain;

@MapperScan(basePackages = {"com.gbm.edu.service"}, annotationClass = Repository.class)
public class MybatisConfiguration extends MybatisAutoConfiguration {


	@Autowired
    private MybatisProperties properties;

    public MybatisConfiguration(MybatisProperties properties, ObjectProvider<Interceptor[]> interceptorsProvider,
			ResourceLoader resourceLoader, ObjectProvider<DatabaseIdProvider> databaseIdProvider,
			ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider) {
		super(properties, interceptorsProvider, resourceLoader, databaseIdProvider, configurationCustomizersProvider);
		// TODO Auto-generated constructor stub
        this.properties = properties;
	}

    @Bean
    @Override
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactory factory = super.sqlSessionFactory(dataSource);
        //factory.openSession(ExecutorType.BATCH);
        Configuration configuration = factory.getConfiguration();
        configuration.getTypeAliasRegistry().registerAliases(this.properties.getTypeAliasesPackage(), Domain.class);
        return factory;
    }
}