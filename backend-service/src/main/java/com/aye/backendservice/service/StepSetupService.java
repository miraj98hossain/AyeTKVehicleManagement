package com.aye.backendservice.service;


import com.aye.dto.request.StepSetupDetailsRequest;
import com.aye.dto.request.StepSetupRequest;
import com.aye.dto.response.StepSetupDetailsResponse;
import com.aye.entity.vehicleproject.StepSetup;
import com.aye.entity.vehicleproject.StepSetupDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface StepSetupService {

    StepSetup saveStepSetup(StepSetupRequest stepSetupRequest, String currentUserName);

    StepSetup findByIdRes(Long StepSetup);

    StepSetup findById(Long id);

    StepSetupDetails addOrUpdateDetail(StepSetupDetailsRequest newDetailsRequest, String currentUserName);

    Page<StepSetup> findAllStepSetup(Pageable pageable);

    List<StepSetupDetails> filterStepSetup(Long orgId, Long invOrgId, String searchWords);


    List<StepSetupDetailsResponse> findStepStpDtlByDtlIds(List<Long> setupDetailIds);


    List<StepSetup> findSetupByTempDtlId(Integer tempDtlId, Long invOrgId);

    List<StepSetupDetails> getAllDetailsBySetup(Long setupId);
}
