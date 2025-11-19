package com.aye.backendservice.service;

import com.aye.commonlib.dto.response.ApiRequestResponse;

public interface UserMenuBService {

    ApiRequestResponse getUserAccessByUserName(String username);
}
