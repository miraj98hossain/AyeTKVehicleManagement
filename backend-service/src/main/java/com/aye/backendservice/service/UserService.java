package com.aye.backendservice.service;


import com.aye.dto.request.MUserRequest;
import com.aye.dto.request.UserSearchRequest;
import com.aye.dto.response.ApiRequestResponse;


public interface UserService {
    ApiRequestResponse findByUserName(String username);

    ApiRequestResponse findByUserNameLike(String username);

    ApiRequestResponse findAllUser();

    ApiRequestResponse findById(Integer userId);

    ApiRequestResponse findAllRoles();

    ApiRequestResponse updateUser(MUserRequest mUserRequest, String username);

    ApiRequestResponse filterUsers(UserSearchRequest userSearchRequest);

}

