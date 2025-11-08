package com.aye.vhmwebclient.service;

import com.aye.vhmwebclient.feignclient.StepSetupServiceFeignClient;
import com.mhdev.commonlib.dto.request.StepSetupRequest;
import com.mhdev.commonlib.dto.response.ApiRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StepSetupService {

    @Autowired
    StepSetupServiceFeignClient stepSetupServiceFeignClient;


    public ApiRequestResponse saveStepSetup(StepSetupRequest stepSetupRequest) {
        return stepSetupServiceFeignClient.saveStepSetup(stepSetupRequest).getBody();
    }


    public ApiRequestResponse getStepSetup(Long id) {
        return stepSetupServiceFeignClient.getStepSetup(id).getBody();
    }


    public ApiRequestResponse getAllStepsSetup() {
        return stepSetupServiceFeignClient.getAllStepsSetup().getBody();
    }

    public ApiRequestResponse filterStepSetup(Long orgId, Long invOrgId, String searchWords) {
        return stepSetupServiceFeignClient.filterStepSetup(orgId, invOrgId, searchWords).getBody();
    }
}





