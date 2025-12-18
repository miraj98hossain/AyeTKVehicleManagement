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

    public ApiRequestResponse saveStep(StepRequest stepRequest, String currentUserName) {
        return stepServiceFeignClient.saveStep(currentUserName, stepRequest).getBody();
    }


    public ApiRequestResponse updateStep(StepRequest stepRequest, String currentUserName) {
        return stepServiceFeignClient.updateStep(currentUserName, stepRequest).getBody();
    }


    public ApiRequestResponse getStep(Long id) {
        return stepServiceFeignClient.getStep(id).getBody();
    }


    public ApiRequestResponse getSteps(Pageable pageable, String searchWords) {
        return stepServiceFeignClient.getSteps(searchWords, pageable).getBody();
    }
}

