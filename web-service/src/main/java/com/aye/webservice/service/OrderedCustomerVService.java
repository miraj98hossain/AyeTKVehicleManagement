package com.aye.webservice.service;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.feignclient.OrderedCustomerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Miraj
 * @date: 05/01/2026
 * @time: 10:02
 */
@Service
public class OrderedCustomerVService {
    @Autowired
    private OrderedCustomerFeignClient orderedCustomerFeignClient;

    public ApiRequestResponse filterCustomer(Long orgId, String searchWords) {
        return orderedCustomerFeignClient.filterCustomer(orgId, searchWords).getBody();
    }
}
