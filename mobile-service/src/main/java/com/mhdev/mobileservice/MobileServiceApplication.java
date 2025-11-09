package com.mhdev.mobileservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MobileServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MobileServiceApplication.class, args);
    }

}
