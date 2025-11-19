package com.aye.backendservice.service;


import com.aye.commonlib.dto.response.ApiRequestResponse;


public interface UserService {
    ApiRequestResponse findByUserName(String username);
}

