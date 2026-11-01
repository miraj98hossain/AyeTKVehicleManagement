package com.aye.backendservice.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.aye.backendservice.repository"},
        entityManagerFactoryRef = "ebsEntityManagerFactory",
        transactionManagerRef = "ebsTransactionManager"
)
public class EbsJpaConfiguration {

    @Bean
    public LocalContainerEntityManagerFactoryBean ebsEntityManagerFactory(
            @Qualifier("ebsDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages("com.aye.backendservice.repository")
                .build();
    }

    @Bean
    public PlatformTransactionManager ebsTransactionManager(
            @Qualifier("ebsEntityManagerFactory") LocalContainerEntityManagerFactoryBean todosEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(todosEntityManagerFactory.getObject()));
    }

}