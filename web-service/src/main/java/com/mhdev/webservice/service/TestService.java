package com.mhdev.webservice.service;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestService {
    @Autowired
    RestTemplate restTemplate ;


    public String test() {

        String url
                = "https://localhost:8443/api/test";
        ResponseEntity<String> response
                = restTemplate.getForEntity(url, String.class);
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        return response.getBody();
    }
}
