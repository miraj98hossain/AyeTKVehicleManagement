package com.aye.webservice.service;

import com.aye.commonlib.dto.request.UserAccessRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.feignclient.UserAccessFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccessService {
    @Autowired
    UserAccessFeignClient controller;

    public ApiRequestResponse getAllTemplet() {
        return this.controller.getAllTemplet().getBody();
    }

    public ApiRequestResponse findByUserId(Integer userId) {
        return this.controller.findByUserId(userId).getBody();
    }


    public ApiRequestResponse saveDtlLine(UserAccessRequest userAccessRequest) {
        return this.controller.saveDtlLine(userAccessRequest).getBody();
    }

}
