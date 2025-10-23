package com.mhdev.backendservice.service;

import com.mhdev.backendservice.entity.StepSetup;
import com.mhdev.backendservice.entity.StepSetupDetails;
import com.mhdev.commonlib.dto.request.StepSetupDetailsRequest;
import com.mhdev.commonlib.dto.response.StepSetupDetailsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface StepSetupDetailsService {


    StepSetupDetailsResponse saveStepStatusDetails(StepSetupDetailsRequest stepSetupDetailsRequest);


    StepSetupDetailsResponse getStepSetupDetails(Long stepSetupDetailsId);

    Page<StepSetupDetailsResponse> getAllStepSetupDetails(Pageable pageable);
    List<StepSetupDetailsResponse> getDetailsBySetupId(StepSetup stepSetup);
//    List<StepSetupDetailsResponse> getDetailsBySetupIds(List<StepSetup> stepSetups);
    void saveAll(List<StepSetupDetails> stepSetupDetails);
}
