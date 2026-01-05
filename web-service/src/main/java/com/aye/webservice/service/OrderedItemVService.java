package com.aye.webservice.service;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.feignclient.OrderedItemVFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Miraj
 * @date: 04/01/2026
 * @time: 17:27
 */
@Service
public class OrderedItemVService {
    @Autowired
    private OrderedItemVFeignClient orderedItemVFeignClient;

    public ApiRequestResponse filterOrderedItem(Long orgId, Long invOrgId, String searchWords) {
        return orderedItemVFeignClient.filterOrderedItem(orgId, invOrgId, searchWords).getBody();
    }
}
