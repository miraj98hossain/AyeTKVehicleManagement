package com.mhdev.webservice.service;

import com.mhdev.commonlib.dto.request.StepSetupRequest;
import com.mhdev.commonlib.dto.response.StepSetupDetailsResponse;
import com.mhdev.commonlib.dto.response.StepSetupResponse;
import com.mhdev.webservice.feignclient.StepSetupServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StepSetupService {

    @Autowired
    StepSetupServiceFeignClient stepSetupServiceFeignClient;


    public StepSetupResponse saveStepSetup(StepSetupRequest stepSetupRequest) {
        return stepSetupServiceFeignClient.saveStepSetup(stepSetupRequest);
    }


    public List<StepSetupDetailsResponse> getStepSetup(Long id) {
        return stepSetupServiceFeignClient.getStepSetup(id);
    }


    public Page<StepSetupResponse> getAllStepsSetup(Pageable pageable) {
        return stepSetupServiceFeignClient.getAllStepsSetup(pageable);
    }
}





