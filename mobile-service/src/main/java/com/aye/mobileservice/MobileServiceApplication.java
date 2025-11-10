package com.aye.mobileservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableFeignClients
@EntityScan({"com.aye.RestfulServer.model"})
@EnableJpaRepositories(basePackages = {"com.aye.RestfulServer.repo"})
@EnableTransactionManagement
@ComponentScan({"com.aye", "com.aye.RestfulServer"})
public class MobileServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MobileServiceApplication.class, args);
    }

}
