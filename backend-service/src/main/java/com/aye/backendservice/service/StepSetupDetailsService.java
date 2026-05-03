package com.aye.backendservice.service;


import com.aye.dtoLib.dto.request.StepSetupDetailsRequest;
import com.aye.entitylib.entity.vehicleproject.StepSetup;
import com.aye.entitylib.entity.vehicleproject.StepSetupDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface StepSetupDetailsService {


    StepSetupDetails saveStepSetupDetails(StepSetupDetailsRequest stepSetupDetailsRequest);


    StepSetupDetails findById(Long stepSetupDetailsId);

    List<StepSetupDetails> findByIds(List<Long> stepSetupDetailsIds);

    Page<StepSetupDetails> getAllStepSetupDetails(Pageable pageable);

    List<StepSetupDetails> getAllDetailsBySetup(StepSetup stepSetup);

    List<StepSetupDetails> getDetailsBySetupId(StepSetup stepSetup);
    
    void saveAll(List<StepSetupDetails> stepSetupDetails);

    StepSetupDetails save(StepSetupDetails stepSetupDetails);

    List<StepSetupDetails> filterStepSetupDetails(List<StepSetup> stepSetups, Long orgId, Long invOrgId, String searchWords);


}
