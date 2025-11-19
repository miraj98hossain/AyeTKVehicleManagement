package com.aye.backendservice.controller;

import com.aye.backendservice.service.implementations.UserServiceImpl;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aye-tk-vhcle-mng/api/user")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @GetMapping("/findByUserName")
    public ResponseEntity<ApiRequestResponse> findByUserName(@RequestParam String username) {
        return ResponseEntity.ok(userServiceImpl.findByUserName(username));
    }
}
