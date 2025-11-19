package com.aye.mobileservice.service;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.mobileservice.feignclient.UserMenuFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class UserMenuService {
    @Autowired
    private UserMenuFeignClient userMenuFeignClient;

    @GetMapping
    ApiRequestResponse getUserAccessByUserName(String username) {
        return userMenuFeignClient.getUserAccessByUserName(username).getBody();
    }

}
