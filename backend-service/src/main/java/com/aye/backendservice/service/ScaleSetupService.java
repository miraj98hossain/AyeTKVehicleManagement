package com.aye.backendservice.service;

import com.aye.commonlib.dto.response.ApiRequestResponse;

/**
 * @author: Miraj
 * @date: 05/01/2026
 * @time: 16:04
 */
public interface ScaleSetupService {
    ApiRequestResponse findAllScaleSetup();

    ApiRequestResponse filterScaleSetup(Long orgId);
}
