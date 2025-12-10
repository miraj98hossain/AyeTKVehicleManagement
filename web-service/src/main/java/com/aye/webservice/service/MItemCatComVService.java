package com.aye.webservice.service;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.feignclient.MItemCatComVFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MItemCatComVService {
    @Autowired
    private MItemCatComVFeignClient mItemCatComVFeignClient;


    public ApiRequestResponse getAllItemCatComb() {
        return this.mItemCatComVFeignClient.getAllItemCatComb().getBody();
    }

    public ApiRequestResponse filterItemCatComb(Long orgId, String searchWords) {
        return this.mItemCatComVFeignClient.filterItemCatComb(orgId, searchWords).getBody();
    }
}
