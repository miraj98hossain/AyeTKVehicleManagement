package com.aye.vhmwebclient.service;

import com.aye.commonlib.dto.request.StepSetupDetailsRequest;
import com.aye.commonlib.dto.request.StepSetupRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.vhmwebclient.feignclient.StepSetupServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class StepSetupService {

    @Autowired
    StepSetupServiceFeignClient stepSetupServiceFeignClient;


    public ApiRequestResponse saveStepSetup(StepSetupRequest stepSetupRequest,
                                            String currentUserName) {
        return this.stepSetupServiceFeignClient.saveStepSetup(stepSetupRequest, currentUserName).getBody();
    }


    public ApiRequestResponse getStepSetup(Long id) {
        return stepSetupServiceFeignClient.getStepSetup(id).getBody();
    }


    public ApiRequestResponse getAllStepsSetup(Pageable pageable) {
        return this.stepSetupServiceFeignClient.getAllStepsSetup(pageable).getBody();
    }

    public ApiRequestResponse filterStepSetup(Long orgId, Long invOrgId, String searchWords) {
        return this.stepSetupServiceFeignClient.filterStepSetup(orgId, invOrgId, searchWords).getBody();
    }

    public ApiRequestResponse findSetupByDtlId(Long detailId) {
        return this.stepSetupServiceFeignClient.findSetupByDtlId(detailId).getBody();
    }


    public ApiRequestResponse findSetupByTempDtlId(Integer tempDtlId) {
        return this.stepSetupServiceFeignClient.findSetupByTempDtlId(tempDtlId).getBody();
    }

    public ApiRequestResponse getAllDetailsBySetup(@PathVariable("setupId") Long setupId) {
        return this.stepSetupServiceFeignClient.getAllDetailsBySetup(setupId).getBody();
    }


    public ApiRequestResponse addOrUpdateDetail(
            StepSetupDetailsRequest stepSetupDetailsRequest,
            String currentUserName) {

        return this.stepSetupServiceFeignClient.addOrUpdateDetail(stepSetupDetailsRequest, currentUserName).getBody();
    }


    public ApiRequestResponse findStepStpDtlByDtlId(@PathVariable("detailId") Long detailId) {
        return this.stepSetupServiceFeignClient.findStepStpDtlByDtlId(detailId).getBody();
    }
}





