package com.aye.webservice.service;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.feignclient.MuserDataFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class MuserDataService {
    @Autowired
    private MuserDataFeignClient muserDataFeignClient;

    public ApiRequestResponse findByOrgId(Long orgId) {
        return this.muserDataFeignClient.findByOrgId(orgId).getBody();
    }

    public ApiRequestResponse getAllCustomerByOrg(@PathVariable("orgId") Long orgId) {
        return this.muserDataFeignClient.getAllCustomerByOrg(orgId).getBody();
    }
}
