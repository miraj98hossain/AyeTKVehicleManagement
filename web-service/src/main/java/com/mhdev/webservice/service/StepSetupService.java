package com.mhdev.webservice.service;

import com.mhdev.commonlib.dto.request.StepSetupRequest;
import com.mhdev.commonlib.dto.response.ApiRequestResponse;
import com.mhdev.webservice.feignclient.StepSetupServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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


    public ApiRequestResponse getAllStepsSetup(Pageable pageable) {
        return stepSetupServiceFeignClient.getAllStepsSetup(pageable).getBody();
    }

    public ApiRequestResponse filterStepSetup(@RequestParam Long orgId, @RequestParam Long invOrgId, @RequestParam(required = false) String searchWords) {
        return stepSetupServiceFeignClient.filterStepSetup(orgId, invOrgId, searchWords).getBody();
    }
}





