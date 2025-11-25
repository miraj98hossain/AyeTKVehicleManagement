package com.aye.webservice.service;


import com.aye.commonlib.dto.request.MUserRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.feignclient.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserFeignClient controller;

    public ApiRequestResponse findAllUser() {
        return this.controller.findAllUser().getBody();
    }

    public ApiRequestResponse findByUserName(String username) {
        return this.controller.findByUserName(username).getBody();
    }

    public ApiRequestResponse findUser(String name) {
        return this.controller.findByUserName(name).getBody();
    }

    public ApiRequestResponse findById(Integer userId) {
        return this.controller.findById(userId).getBody();
    }

    public ApiRequestResponse findAllRoles() {
        return this.controller.findAllRoles().getBody();
    }

    public ApiRequestResponse saveUser(MUserRequest mUserRequest, String username) {
        return this.controller.saveUser(mUserRequest, username).getBody();
    }

    public ResponseEntity<ApiRequestResponse> findByUserNameLike(String username) {
        return this.controller.findByUserNameLike(username);
    }
}
