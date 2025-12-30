package com.aye.backendservice.service;

import com.aye.commonlib.dto.response.BeforeTripVResponse;

import java.util.List;

/**
 * @author: Miraj
 * @date: 30/12/2025
 * @time: 12:39
 * @project: AyeTKVehicleManagement
 */
public interface BeforeTripVBService {
    List<BeforeTripVResponse> findOnHandOrders(Long orgId, Long invOrgId);
}
