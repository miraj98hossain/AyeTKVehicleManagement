package com.aye.backendservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
//@EntityScan({"com.aye.RestfulServer.model", "com.aye.backendservice.entity"})
//@EnableJpaRepositories(basePackages = {"com.aye.RestfulServer.repo", "com.aye.backendservice.repository"})
@EnableTransactionManagement
@ComponentScan({"com.aye"})
public class BackendServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendServiceApplication.class, args);
    }

}
