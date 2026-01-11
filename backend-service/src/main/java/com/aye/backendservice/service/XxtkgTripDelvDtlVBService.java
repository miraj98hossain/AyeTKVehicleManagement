package com.aye.backendservice.service;

import com.aye.commonlib.dto.response.ApiRequestResponse;

/**
 * @author: Miraj
 * @date: 08/01/2026
 * @time: 11:50
 */
public interface XxtkgTripDelvDtlVBService {

    ApiRequestResponse filterChallanNumber(Long orgId, Long invOrgId, String searchWords);
}
