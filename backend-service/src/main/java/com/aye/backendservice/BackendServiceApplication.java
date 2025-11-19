package com.aye.backendservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.aye"})
//@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public class BackendServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendServiceApplication.class, args);
    }

}
