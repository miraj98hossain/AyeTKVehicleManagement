package com.aye.mobileservice.service;

import com.aye.commonlib.dto.request.StepSetupRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.validationGroup.StepSetupCreateValidation;
import com.aye.mobileservice.feignclient.StepSetupServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class StepSetupService {

    @Autowired
    StepSetupServiceFeignClient stepSetupServiceFeignClient;


    public ApiRequestResponse saveStepSetup(@Validated({StepSetupCreateValidation.class})
                                            @RequestBody StepSetupRequest stepSetupRequest) {
        return stepSetupServiceFeignClient.saveStepSetup(stepSetupRequest).getBody();
    }


    public ApiRequestResponse getStepSetup(@PathVariable("id") Long id) {
        return stepSetupServiceFeignClient.getStepSetup(id).getBody();
    }


    public ApiRequestResponse getAllStepsSetup(Pageable pageable) {
        return stepSetupServiceFeignClient.getAllStepsSetup(pageable).getBody();
    }


    public ApiRequestResponse filterStepSetup(
            @RequestParam Long orgId,
            @RequestParam Long invOrgId,
            @RequestParam(required = false) String searchWords) {
        return this.stepSetupServiceFeignClient.filterStepSetup(orgId, invOrgId, searchWords).getBody();
    }


    public ApiRequestResponse findSetupByDtlId(
            @RequestParam Long detailId) {
        return stepSetupServiceFeignClient.findSetupByDtlId(detailId).getBody();
    }


    public ApiRequestResponse findSetupByTempDtlId(@PathVariable("tempDtlId") Integer tempDtlId) {
        return this.stepSetupServiceFeignClient.findSetupByTempDtlId(tempDtlId).getBody();
    }
}





