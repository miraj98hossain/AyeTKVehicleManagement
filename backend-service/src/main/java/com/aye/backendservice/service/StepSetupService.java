package com.aye.backendservice.service;

import com.aye.dtoLib.dto.request.StepSetupDetailsRequest;
import com.aye.dtoLib.dto.request.StepSetupRequest;
import com.aye.dtoLib.dto.response.StepSetupDetailsResponse;
import com.aye.entitylib.entity.vehicleproject.StepSetup;
import com.aye.entitylib.entity.vehicleproject.StepSetupDetails;
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
