package com.aye.mobileservice.service;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    UserMenuService userMenuService;


    public ApiRequestResponse login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        return userMenuService.getUserAccessByUserName(userName);
    }
}
