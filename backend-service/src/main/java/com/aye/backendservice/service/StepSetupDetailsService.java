package com.aye.backendservice.service;


import com.aye.commonlib.dto.request.StepSetupDetailsRequest;
import com.aye.commonlib.dto.response.StepSetupDetailsResponse;
import com.aye.entitylib.entity.StepSetup;
import com.aye.entitylib.entity.StepSetupDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface StepSetupDetailsService {


    StepSetupDetailsResponse saveStepSetupDetails(StepSetupDetailsRequest stepSetupDetailsRequest);


    StepSetupDetails findById(Long stepSetupDetailsId);

    List<StepSetupDetails> findByIds(List<Long> stepSetupDetailsIds);

    Page<StepSetupDetailsResponse> getAllStepSetupDetails(Pageable pageable);

    List<StepSetupDetailsResponse> getAllDetailsBySetup(StepSetup stepSetup);

    List<StepSetupDetailsResponse> getDetailsBySetupId(StepSetup stepSetup);

    //    List<StepSetupDetailsResponse> getDetailsBySetupIds(List<StepSetup> stepSetups);
    void saveAll(List<StepSetupDetails> stepSetupDetails);

    StepSetupDetails save(StepSetupDetails stepSetupDetails);

    List<StepSetupDetails> filterStepSetupDetails(List<StepSetup> stepSetups, Long orgId, Long invOrgId, String searchWords);


}
