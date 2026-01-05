package com.aye.backendservice.service;

import com.aye.commonlib.dto.response.ApiRequestResponse;

/**
 * @author: Miraj
 * @date: 05/01/2026
 * @time: 10:02
 */
public interface OrderedCustomerVBService {

    ApiRequestResponse filterCustomer(Long orgId, String searchWords);
}
