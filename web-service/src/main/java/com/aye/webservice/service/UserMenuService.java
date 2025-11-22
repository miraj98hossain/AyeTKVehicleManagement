package com.aye.webservice.service;


import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.feignclient.UserMenuFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMenuService {
    @Autowired
    UserMenuFeignClient controller;

    public ApiRequestResponse getUserAccessByUserName(String username, String roleType) {
        return this.controller.getUserAccessByUserName(username, roleType).getBody();
    }
}
