package com.aye.vhmwebclient.service;

import com.aye.commonlib.dto.request.StepRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.vhmwebclient.feignclient.StepServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service

public class StepService {
    @Autowired
    StepServiceFeignClient stepServiceFeignClient;

    public ApiRequestResponse saveStep(StepRequest stepRequest, Long currentUserId) {
        return stepServiceFeignClient.saveStep(currentUserId, stepRequest).getBody();
    }


    public ApiRequestResponse updateStep(StepRequest stepRequest, Long currentUserId) {
        return stepServiceFeignClient.updateStep(currentUserId, stepRequest).getBody();
    }


    public ApiRequestResponse getStep(Long id) {
        return stepServiceFeignClient.getStep(id).getBody();
    }


    public ApiRequestResponse getSteps(Pageable pageable) {
        return stepServiceFeignClient.getAllSteps(pageable).getBody();
    }
}

