package com.aye.webservice.service;


import com.aye.commonlib.dto.request.InventoryInformationRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.feignclient.InvInfoFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


@Service
public class InvInfoService {
    @Autowired
    InvInfoFeignClient invInfoFeignClient;

    public ApiRequestResponse findOne(@PathVariable("invId") Long invId) {
        return this.invInfoFeignClient.findOne(invId).getBody();
    }


    public ApiRequestResponse save(InventoryInformationRequest invRequest) {
        return this.invInfoFeignClient.save(invRequest).getBody();
    }


    public ApiRequestResponse findByOu(@PathVariable("orgId") Long orgId) {
        return this.invInfoFeignClient.findByOu(orgId).getBody();
    }
}
