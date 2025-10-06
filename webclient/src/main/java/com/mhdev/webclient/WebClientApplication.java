package com.mhdev.webclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WebClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(com.mhdev.webclient.WebClientApplication.class, args);
	}

}
