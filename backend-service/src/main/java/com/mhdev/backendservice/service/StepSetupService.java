package com.mhdev.backendservice.service;

import com.mhdev.backendservice.entity.StepSetup;


import com.mhdev.commonlib.dto.request.StepSetupRequest;
import com.mhdev.commonlib.dto.response.StepSetupDetailsResponse;
import com.mhdev.commonlib.dto.response.StepSetupResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface StepSetupService {

    StepSetupResponse saveStepSetup(StepSetupRequest stepSetupRequest);
    List<StepSetupDetailsResponse> getStepSetupRes(Long StepSetup);
    StepSetup getStepSetup(Long id);
    Page<StepSetupResponse> getAllStepSetup(Pageable pageable);
}
