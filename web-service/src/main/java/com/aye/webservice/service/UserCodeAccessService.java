package com.aye.webservice.service;

import com.aye.commonlib.dto.request.UserCodeAccessRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.feignclient.UserCodeAccessFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Service
public class UserCodeAccessService {
    @Autowired
    private UserCodeAccessFeignClient userCodeAccessFeignClient;

    @PostMapping("/save")
    public ApiRequestResponse save(UserCodeAccessRequest userCodeAccess) {
        return userCodeAccessFeignClient.save(userCodeAccess).getBody();
    }

    @GetMapping("/{id}")
    public ApiRequestResponse findById(Long userCodeAccessId) {
        return userCodeAccessFeignClient.findById(userCodeAccessId).getBody();
    }

    @GetMapping("/findAllByUser/{id}")
    public ApiRequestResponse findAllByUser(Integer userId) {
        return userCodeAccessFeignClient.findAllByUser(userId).getBody();
    }
}
