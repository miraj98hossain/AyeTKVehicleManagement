package com.aye.backendservice.service;


import com.aye.dtoLib.dto.request.MUserRequest;
import com.aye.dtoLib.dto.request.UserSearchRequest;
import com.aye.dtoLib.dto.response.ApiRequestResponse;


public interface UserService {
    ApiRequestResponse findByUserName(String username);

    ApiRequestResponse findByUserNameLike(String username);

    ApiRequestResponse findAllUser();

    ApiRequestResponse findById(Integer userId);

    ApiRequestResponse findAllRoles();

    ApiRequestResponse updateUser(MUserRequest mUserRequest, String username);

    ApiRequestResponse filterUsers(UserSearchRequest userSearchRequest);

}

