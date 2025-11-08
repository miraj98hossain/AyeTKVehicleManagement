package com.mhdev.backendservice.service;

import com.mhdev.backendservice.entity.StepSetup;
import com.mhdev.commonlib.dto.request.StepSetupDetailsRequest;
import com.mhdev.commonlib.dto.request.StepSetupRequest;
import com.mhdev.commonlib.dto.response.ApiRequestResponse;
import org.springframework.data.domain.Pageable;


public interface StepSetupService {

    ApiRequestResponse saveStepSetup(StepSetupRequest stepSetupRequest);

    ApiRequestResponse findByIdRes(Long StepSetup);

    StepSetup findById(Long id);

    ApiRequestResponse addOrUpdateDetail(StepSetupDetailsRequest newDetailsRequest);

    ApiRequestResponse findAllStepSetup(Pageable pageable);

    ApiRequestResponse filterStepSetup(Long orgId, Long invOrgId);
}
