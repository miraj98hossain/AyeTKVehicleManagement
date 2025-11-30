package com.aye.backendservice.service;

import com.aye.backendservice.entity.StepSetup;
import com.aye.commonlib.dto.request.StepSetupDetailsRequest;
import com.aye.commonlib.dto.request.StepSetupRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.StepSetupDetailsResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface StepSetupService {

    ApiRequestResponse saveStepSetup(StepSetupRequest stepSetupRequest);

    ApiRequestResponse findByIdRes(Long StepSetup);

    StepSetup findById(Long id);

    ApiRequestResponse addOrUpdateDetail(StepSetupDetailsRequest newDetailsRequest);

    ApiRequestResponse findAllStepSetup(Pageable pageable);

    ApiRequestResponse filterStepSetup(Long orgId, Long invOrgId, String searchWords);

    ApiRequestResponse findSetupByDtlId(Long setupDetailId);

    StepSetupDetailsResponse findStepStpDtlByDtlId(Long stepDetailId);

    List<StepSetupDetailsResponse> findStepStpDtlByDtlIds(List<Long> setupDetailIds);


    ApiRequestResponse findSetupByTempDtlId(Integer tempDtlId);

}
