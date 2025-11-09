package com.aye.backendservice.service;

import com.aye.commonlib.dto.request.StepSetupDetailsRequest;
import com.aye.commonlib.dto.response.StepSetupDetailsResponse;
import com.aye.backendservice.entity.StepSetup;
import com.aye.backendservice.entity.StepSetupDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface StepSetupDetailsService {


    StepSetupDetailsResponse saveStepSetupDetails(StepSetupDetailsRequest stepSetupDetailsRequest);


    StepSetupDetails findById(Long stepSetupDetailsId);

    List<StepSetupDetails> findByIds(List<Long> stepSetupDetailsIds);

    Page<StepSetupDetailsResponse> getAllStepSetupDetails(Pageable pageable);

    List<StepSetupDetailsResponse> getDetailsBySetupId(StepSetup stepSetup);

    //    List<StepSetupDetailsResponse> getDetailsBySetupIds(List<StepSetup> stepSetups);
    void saveAll(List<StepSetupDetails> stepSetupDetails);

    List<StepSetupDetails> filterStepSetupDetails(StepSetup stepSetup, Long orgId, Long invOrgId, String searchWords);


}
