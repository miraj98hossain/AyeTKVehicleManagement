package com.aye.webservice.service;

import com.aye.commonlib.dto.request.UserAccessRequest;
import com.aye.commonlib.dto.request.UserAccessTemltDtlRequest;
import com.aye.commonlib.dto.request.UserAccessTempltRequest;
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


    public ApiRequestResponse saveUserAccessTemp(
            UserAccessTempltRequest userAccessTempRequest) {
        return this.controller.saveUserAccessTemp(userAccessTempRequest).getBody();
    }

    public ApiRequestResponse saveUserAccessTempDtl(
            UserAccessTemltDtlRequest userAccessTemltDtlRequest) {
        return this.controller.saveUserAccessTempDtl(userAccessTemltDtlRequest).getBody();
    }


    public ApiRequestResponse findTempById(Integer id) {
        return this.controller.findTempById(id).getBody();
    }


    public ApiRequestResponse findTempDtlByDtlId(Integer id) {
        return this.controller.findTempDtlByDtlId(id).getBody();
    }

    public ApiRequestResponse findByTempHdrId(Integer id) {
        return this.controller.findByTempHdrId(id).getBody();
    }
    
    public ApiRequestResponse findUserAccessById(Integer id) {
        return this.controller.findUserAccessById(id).getBody();
    }

}
