package com.aye.backendservice.service;

import com.aye.dtoLib.dto.response.ApiRequestResponse;

/**
 * @author: Miraj
 * @date: 01/01/2026
 * @time: 13:39
 * @project: AyeTKVehicleManagement
 */
public interface StepTransTimeStampVService {

    ApiRequestResponse getTimeStampByDetailsId(Long stepSetupDetailsId);
}
