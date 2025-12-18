package com.aye.webservice.service;

import com.aye.commonlib.dto.request.StepSetupDetailsRequest;
import com.aye.commonlib.dto.request.StepSetupRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.feignclient.StepSetupServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StepSetupService {

    @Autowired
    StepSetupServiceFeignClient stepSetupServiceFeignClient;


    public ApiRequestResponse saveStepSetup(StepSetupRequest stepSetupRequest, String currentUserName) {
        return stepSetupServiceFeignClient.saveStepSetup(stepSetupRequest, currentUserName).getBody();
    }


    public ApiRequestResponse getStepSetup(Long id) {
        return stepSetupServiceFeignClient.getStepSetup(id).getBody();
    }


    public ApiRequestResponse getAllStepsSetup(Pageable pageable) {
        return stepSetupServiceFeignClient.getAllStepsSetup(pageable).getBody();
    }

    public ApiRequestResponse filterStepSetup(Long orgId, Long invOrgId, String searchWords) {
        return stepSetupServiceFeignClient.filterStepSetup(orgId, invOrgId, searchWords).getBody();
    }

    public ApiRequestResponse findSetupByDtlId(Long detailId) {
        return stepSetupServiceFeignClient.findSetupByDtlId(detailId).getBody();
    }


    public ApiRequestResponse findSetupByTempDtlId(Integer tempDtlId) {
        return stepSetupServiceFeignClient.findSetupByTempDtlId(tempDtlId).getBody();
    }

    public ApiRequestResponse findAllSetupDetails(Long setupId) {
        return stepSetupServiceFeignClient.findAllSetupDetails(setupId).getBody();
    }


    public ApiRequestResponse addOrUpdateDetail(
            StepSetupDetailsRequest stepSetupDetailsRequest,
            String currentUserName) {

        return stepSetupServiceFeignClient
                .addOrUpdateDetail(stepSetupDetailsRequest, currentUserName)
                .getBody();
    }

    public ApiRequestResponse findStepStpDtlByDtlId(Long detailId) {
        return stepSetupServiceFeignClient.findStepStpDtlByDtlId(detailId).getBody();
    }
}





