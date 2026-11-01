package com.aye.backendservice.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicDatasourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.topics")
    public DataSourceProperties topicsDataSourceProperties() {
        return new DataSourceProperties();
    }

}