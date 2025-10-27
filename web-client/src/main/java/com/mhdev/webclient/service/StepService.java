package com.mhdev.webclient.service;

import com.mhdev.commonlib.dto.request.StepRequest;
import com.mhdev.commonlib.dto.response.StepResponse;
import com.mhdev.webclient.feignclient.StepServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class StepService {
    @Autowired
    StepServiceFeignClient stepServiceFeignClient;

    public StepResponse saveStep(StepRequest stepRequest) {
        return stepServiceFeignClient.saveStep(stepRequest).getBody();
    }


    public StepResponse updateStep(StepRequest stepRequest) {
        return stepServiceFeignClient.updateStep(stepRequest).getBody();
    }


    public StepResponse getStep(Long id) {
        return stepServiceFeignClient.getStep(id).getBody();
    }


    public Page<StepResponse> getSteps(Pageable pageable) {
        return stepServiceFeignClient.getAllSteps(pageable).getBody();
    }
}

