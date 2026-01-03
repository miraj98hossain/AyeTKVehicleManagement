package com.aye.backendservice.service;

import com.aye.commonlib.dto.response.ApiRequestResponse;

/**
 * @author: Miraj
 * @date: 01/01/2026
 * @time: 13:39
 * @project: AyeTKVehicleManagement
 */
public interface StepTransTimeStampVService {

    ApiRequestResponse getTimeStampByDetailsId(Long stepSetupDetailsId);
}
