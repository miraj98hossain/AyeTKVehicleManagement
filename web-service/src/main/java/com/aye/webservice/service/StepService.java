package com.aye.webservice.service;

import com.aye.webservice.feignclient.StepServiceFeignClient;
import com.mhdev.commonlib.dto.request.StepRequest;
import com.mhdev.commonlib.dto.response.ApiRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class StepService {
    @Autowired
    StepServiceFeignClient stepServiceFeignClient;

    public ApiRequestResponse saveStep(StepRequest stepRequest, Long currentUserId) {
        return stepServiceFeignClient.saveStep(stepRequest, currentUserId).getBody();
    }


    ApiRequestResponse updateStep(StepRequest stepRequest, Long currentUserId) {
        return stepServiceFeignClient.updateStep(stepRequest, currentUserId).getBody();
    }


    public ApiRequestResponse getStep(Long id) {
        return stepServiceFeignClient.getStep(id).getBody();
    }


    public ApiRequestResponse getSteps(String searchWords, Pageable pageable) {
        return stepServiceFeignClient.getAllSteps(searchWords, pageable).getBody();
    }
}

