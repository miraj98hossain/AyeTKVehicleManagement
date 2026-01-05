package com.aye.backendservice.service;

import com.aye.commonlib.dto.response.ApiRequestResponse;

/**
 * @author: Miraj
 * @date: 04/01/2026
 * @time: 17:27
 */
public interface OrderedItemVBService {
    ApiRequestResponse filterOrderedItem(Long orgId, Long invOrgId, String searchWords);
}
