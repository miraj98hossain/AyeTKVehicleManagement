package com.aye.webservice.service;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.feignclient.MTrnsCountFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MTrnsCountService {
    @Autowired
    MTrnsCountFeignClient controller;

    public ApiRequestResponse findAll() {
        return this.controller.findAll().getBody();
    }

}
