package com.aye.webservice.service;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.feignclient.XxtkgTripDelvDtlVFeignClinet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Miraj
 * @date: 08/01/2026
 * @time: 12:42
 */
@Service
public class XxtkgTripDelvDtlVService {
    @Autowired
    private XxtkgTripDelvDtlVFeignClinet feignClient;


    public ApiRequestResponse filterChallanNumber(Long orgId,
                                                  Long invOrgId,
                                                  String searchWords) {
        return feignClient.filterChallanNumber(orgId, invOrgId, searchWords).getBody();
    }


}
