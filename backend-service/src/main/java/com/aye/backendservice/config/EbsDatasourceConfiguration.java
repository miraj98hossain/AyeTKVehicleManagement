package com.aye.backendservice.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class EbsDatasourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.ebs")
    @Primary
    public DataSourceProperties ebsDataSourceProperties() {
        return new DataSourceProperties();
    }

    @ConfigurationProperties("spring.datasource.ebs.hikari")
    @Bean
    @Primary
    public DataSource ebsDataSource() {
        return ebsDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    @Primary
    public JdbcTemplate ebsJdbcTemplate(@Qualifier("ebsDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
