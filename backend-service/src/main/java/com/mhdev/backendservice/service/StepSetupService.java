package com.mhdev.backendservice.service;

import com.mhdev.backendservice.entity.StepSetup;
import com.mhdev.commonlib.dto.request.StepSetupDetailsRequest;
import com.mhdev.commonlib.dto.request.StepSetupRequest;
import com.mhdev.commonlib.dto.response.StepSetupDetailsResponse;
import com.mhdev.commonlib.dto.response.StepSetupResponse;

import java.util.List;


public interface StepSetupService {

    StepSetupResponse saveStepSetup(StepSetupRequest stepSetupRequest);

    List<StepSetupDetailsResponse> findByIdRes(Long StepSetup);

    StepSetup findById(Long id);

    StepSetupResponse addOrUpdateDetail(StepSetupDetailsRequest newDetailsRequest);

    List<StepSetupResponse> findAllStepSetup();
}
