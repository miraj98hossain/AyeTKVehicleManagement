//package com.aye.backendservice.config;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        basePackages = {"com.aye.backendservice.repository"},
//        entityManagerFactoryRef = "ebsEntityManagerFactory",
//        transactionManagerRef = "ebsTransactionManager"
//)
//public class EbsJpaConfiguration {
//
//    @Bean
//    @Primary
//    public LocalContainerEntityManagerFactoryBean ebsEntityManagerFactory(EntityManagerFactoryBuilder builder,
//                                                                          @Qualifier("ebsDataSource") DataSource dataSource,
//                                                                          EbsJpaProperties jpaProperties) {
//
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
//                .persistenceUnit("ebs")
//                .packages("com.aye.entity")
//                .properties(properties)
//                .build();
//    }
//
//
//    @Bean
//    @Primary
//    public PlatformTransactionManager ebsTransactionManager(
//            @Qualifier("ebsEntityManagerFactory") LocalContainerEntityManagerFactoryBean todosEntityManagerFactory) {
//        return new JpaTransactionManager(Objects.requireNonNull(todosEntityManagerFactory.getObject()));
//    }
//
//
//}

package com.aye.backendservice.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        basePackages = "com.aye.backendservice.repository",   // adjust
        entityManagerFactoryRef = "ebsEntityManagerFactory",
        transactionManagerRef = "ebsTransactionManager")
public class EbsJpaConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.ebs.jpa")
    public JpaProperties ebsJpaProperties() {
        return new JpaProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.ebs.jpa.hibernate")
    public HibernateProperties ebsHibernateProperties() {
        return new HibernateProperties();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean ebsEntityManagerFactory(DataSource ebsDataSource, JpaProperties ebsJpaProperties, HibernateProperties ebsHibernateProperties) {

        Map<String, Object> props = ebsHibernateProperties
                .determineHibernateProperties(ebsJpaProperties.getProperties(), new HibernateSettings());

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(ebsJpaProperties.isShowSql());
        adapter.setGenerateDdl(ebsJpaProperties.isGenerateDdl());

        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(ebsDataSource);
        emf.setPackagesToScan("com.aye.entity");
        emf.setPersistenceUnitName("ebs");
        emf.setJpaVendorAdapter(adapter);
        emf.setJpaPropertyMap(props);
        return emf;
    }


    @Bean
    @Primary
    public PlatformTransactionManager ebsTransactionManager(
            @Qualifier("ebsEntityManagerFactory") LocalContainerEntityManagerFactoryBean ebsEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(ebsEntityManagerFactory.getObject()));
    }
}