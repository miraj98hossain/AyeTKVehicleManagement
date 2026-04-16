package com.aye.webservice.service;

import com.aye.dtoLib.dto.request.UserCodeAccessRequest;
import com.aye.dtoLib.dto.response.ApiRequestResponse;
import com.aye.webservice.feignclient.UserCodeAccessFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserCodeAccessService {
    @Autowired
    private UserCodeAccessFeignClient userCodeAccessFeignClient;


    public ApiRequestResponse save(UserCodeAccessRequest userCodeAccess, String currentUser) {
        return userCodeAccessFeignClient.save(userCodeAccess, currentUser).getBody();
    }


    public ApiRequestResponse findById(Long userCodeAccessId) {
        return userCodeAccessFeignClient.findById(userCodeAccessId).getBody();
    }


    public ApiRequestResponse findAllByUser(Integer userId) {
        return userCodeAccessFeignClient.findAllByUser(userId).getBody();
    }

    public ApiRequestResponse findAllByUserName(String userName) {
        return userCodeAccessFeignClient.findAllByUserName(userName).getBody();
    }
}
