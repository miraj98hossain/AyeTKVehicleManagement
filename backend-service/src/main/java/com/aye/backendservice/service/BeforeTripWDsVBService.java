package com.aye.backendservice.service;

import com.aye.commonlib.dto.response.BeforeTripWDsVResponse;

import java.util.List;

/**
 * @author: Miraj
 * @date: 30/12/2025
 * @time: 10:43
 * @project: AyeTKVehicleManagement
 */
public interface BeforeTripWDsVBService {

    List<BeforeTripWDsVResponse> findScheduleId(Long orgId, Long invOrgId);
}
