package com.aye.backendservice.service;


import com.aye.commonlib.dto.request.MUserRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;


public interface UserService {
    ApiRequestResponse findByUserName(String username);

    ApiRequestResponse findByUserNameLike(String username);

    ApiRequestResponse findAllUser();

    ApiRequestResponse findById(Integer userId);

    ApiRequestResponse findAllRoles();

    ApiRequestResponse updateUser(MUserRequest mUserRequest, String username);


}

