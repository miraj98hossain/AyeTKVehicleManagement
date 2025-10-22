package com.mhdev.backendservice.service;

import com.mhdev.backendservice.entity.StepSetup;


import com.mhdev.commonlib.dto.request.StepSetupRequest;
import com.mhdev.commonlib.dto.response.StepSetupResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface StepSetupService {

    StepSetupResponse saveStepSetup(StepSetupRequest stepSetupRequest);
    StepSetupResponse getStepSetupRes(Long id);
    StepSetup getStepSetup(Long orgId);
    public Page<StepSetupResponse> getAllStepSetup(Pageable pageable);
}
