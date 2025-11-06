package com.aye.vhmwebclient.service;

import com.aye.vhmwebclient.feignclient.StepTransServiceFeignClient;
import com.mhdev.commonlib.dto.request.StepTransLinesRequest;
import com.mhdev.commonlib.dto.request.StepTransRequest;
import com.mhdev.commonlib.dto.response.ApiRequestResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class StepTransService {

    StepTransServiceFeignClient stepTransServiceFeignClient;


    public ApiRequestResponse create(StepTransRequest stepTransRequest) {
        return stepTransServiceFeignClient.create(stepTransRequest).getBody();
    }


    public ApiRequestResponse updateLines(StepTransLinesRequest stepTransLinesRequest) {
        return stepTransServiceFeignClient.updateLines(stepTransLinesRequest).getBody();
    }


    public ApiRequestResponse findAll(Pageable pageable) {
        return stepTransServiceFeignClient.findAll(pageable).getBody();
    }


    public ApiRequestResponse findById(Long id) {
        return stepTransServiceFeignClient.findById(id).getBody();
    }
}
