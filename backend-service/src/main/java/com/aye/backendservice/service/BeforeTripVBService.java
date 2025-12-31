package com.aye.backendservice.service;

import com.aye.commonlib.dto.response.ApiRequestResponse;

/**
 * @author: Miraj
 * @date: 30/12/2025
 * @time: 12:39
 * @project: AyeTKVehicleManagement
 */
public interface BeforeTripVBService {
    ApiRequestResponse getDeliveryNumbers(Long orgId, Long invOrgId, Long searchWords);
}
