package com.aye.backendservice.config;//package com.aye.backendservice.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Objects;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.aye.backendservice.postgresRepository"},
        entityManagerFactoryRef = "postgresEntityManagerFactory",
        transactionManagerRef = "postgresTransactionManager"
)
public class PostgresJpaConfiguration {
//    @Bean
//    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(@Qualifier("postgresDataSource") DataSource dataSource,
//                                                                               EntityManagerFactoryBuilder builder, PostgresJpaProperties jpaProperties) {
//        Map<String, Object> properties = new HashMap<>();
//
//        // Set JPA properties
//        properties.put("hibernate.show_sql", jpaProperties.isShowSql());
//        properties.put("hibernate.generate_ddl", jpaProperties.isGenerateDdl());
//
//        // Set Hibernate properties
//        properties.put("hibernate.ddl-auto", jpaProperties.getHibernate().getDdlAuto());
//        properties.put("hibernate.naming.physical-strategy",
//                jpaProperties.getHibernate().getNaming().getPhysicalStrategy());
//
//        // Set additional Hibernate properties
//        properties.putAll(jpaProperties.getProperties());
//
//        return builder
//                .dataSource(dataSource)
//                .properties(properties)
//                .persistenceUnit("postgres")
//                .packages("com.aye.backendservice.postgresEntity")
//                .build();
//    }
//
//    @Bean
//    public PlatformTransactionManager postgresTransactionManager(
//            @Qualifier("postgresEntityManagerFactory") LocalContainerEntityManagerFactoryBean todosEntityManagerFactory) {
//        return new JpaTransactionManager(Objects.requireNonNull(todosEntityManagerFactory.getObject()));
//    }


    @Bean
    @ConfigurationProperties("spring.datasource.postgres.jpa")
    public JpaProperties postgresJpaProperties() {
        return new JpaProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.postgres.jpa.hibernate")
    public HibernateProperties postgresHibernateProperties() {
        return new HibernateProperties();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(@Qualifier("postgresDataSource") DataSource dataSource,
                                                                               @Qualifier("postgresJpaProperties") JpaProperties postgresJpaProperties,
                                                                               @Qualifier("postgresHibernateProperties") HibernateProperties postgresHibernateProperties) {

        Map<String, Object> props = postgresHibernateProperties.determineHibernateProperties(postgresJpaProperties.getProperties(), new HibernateSettings());
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(postgresJpaProperties.isShowSql());
        adapter.setGenerateDdl(postgresJpaProperties.isGenerateDdl());
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.aye.backendservice.postgresEntity");
        emf.setPersistenceUnitName("postgres");
        emf.setJpaVendorAdapter(adapter);
        emf.setJpaPropertyMap(props);
        return emf;
    }

    @Bean
    public PlatformTransactionManager postgresTransactionManager(
            @Qualifier("postgresEntityManagerFactory") LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(postgresEntityManagerFactory.getObject()));
    }

}