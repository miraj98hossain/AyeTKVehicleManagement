package com.aye.vhmwebclient.service;

import com.aye.vhmwebclient.feignclient.StepServiceFeignClient;
import com.mhdev.commonlib.dto.request.StepRequest;
import com.mhdev.commonlib.dto.response.ApiRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service

public class StepService {
    @Autowired
    StepServiceFeignClient stepServiceFeignClient;

    public ApiRequestResponse saveStep(StepRequest stepRequest) {
        return stepServiceFeignClient.saveStep(stepRequest).getBody();
    }


    public ApiRequestResponse updateStep(StepRequest stepRequest) {
        return stepServiceFeignClient.updateStep(stepRequest).getBody();
    }


    public ApiRequestResponse getStep(Long id) {
        return stepServiceFeignClient.getStep(id).getBody();
    }


    public ApiRequestResponse getSteps(Pageable pageable) {
        return stepServiceFeignClient.getAllSteps(pageable).getBody();
    }
}

