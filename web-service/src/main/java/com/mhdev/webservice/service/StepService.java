package com.mhdev.webservice.service;

import com.mhdev.commonlib.dto.request.StepRequest;
import com.mhdev.webservice.feignclient.StepServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class StepService {
    @Autowired
    StepServiceFeignClient stepServiceFeignClient;

    public Response saveStep(StepRequest stepRequest) {
        return stepServiceFeignClient.saveStep(stepRequest).getBody();
    }


    Response updateStep(StepRequest stepRequest) {
        return stepServiceFeignClient.updateStep(stepRequest).getBody();
    }


    public Response getStep(Long id) {
        return stepServiceFeignClient.getStep(id).getBody();
    }


    public Response getSteps(Pageable pageable) {
        return stepServiceFeignClient.getAllSteps(pageable).getBody();
    }
}

