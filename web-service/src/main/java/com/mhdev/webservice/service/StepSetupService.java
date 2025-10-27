package com.mhdev.webservice.service;

import com.mhdev.commonlib.dto.request.StepSetupRequest;
import com.mhdev.webservice.feignclient.StepSetupServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StepSetupService {

    @Autowired
    StepSetupServiceFeignClient stepSetupServiceFeignClient;


    public Response saveStepSetup(StepSetupRequest stepSetupRequest) {
        return stepSetupServiceFeignClient.saveStepSetup(stepSetupRequest).getBody();
    }


    public Response getStepSetup(Long id) {
        return stepSetupServiceFeignClient.getStepSetup(id).getBody();
    }


    public Response getAllStepsSetup(Pageable pageable) {
        return stepSetupServiceFeignClient.getAllStepsSetup(pageable).getBody();
    }
}





