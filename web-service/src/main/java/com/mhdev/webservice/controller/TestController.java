package com.mhdev.webservice.controller;

import com.mhdev.webservice.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    TestService testService;
    @GetMapping()
    public String test(){
        try {
            return testService.test();
        }catch (Exception e){
           throw  new RuntimeException(e.getMessage());
        }
    }
}
