package com.aye.backendservice.service;

import com.aye.commonlib.dto.response.ApiRequestResponse;

/**
 * @author: Miraj
 * @date: 30/12/2025
 * @time: 10:43
 * @project: AyeTKVehicleManagement
 */
public interface BeforeTripWDsVBService {

    ApiRequestResponse findScheduleId(Long orgId, Long invOrgId, String searchWords);
}
