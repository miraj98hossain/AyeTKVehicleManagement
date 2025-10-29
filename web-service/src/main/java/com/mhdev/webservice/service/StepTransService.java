package com.mhdev.webservice.service;

import com.mhdev.commonlib.dto.request.StepTransLinesRequest;
import com.mhdev.commonlib.dto.request.StepTransRequest;
import com.mhdev.commonlib.dto.response.ApiRequestResponse;
import com.mhdev.webservice.feignclient.StepTransServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class StepTransService {
    @Autowired
    StepTransServiceFeignClient stepTransServiceFeignClient;


    public ApiRequestResponse create(StepTransRequest stepTransRequest) {
        return stepTransServiceFeignClient.create(stepTransRequest);
    }


    public ApiRequestResponse updateLines(StepTransLinesRequest stepTransLinesRequest) {
        return stepTransServiceFeignClient.updateLines(stepTransLinesRequest);
    }


    public ApiRequestResponse findAll(Pageable pageable) {
        return stepTransServiceFeignClient.findAll(pageable);
    }


    public ApiRequestResponse findById(Long id) {
        return stepTransServiceFeignClient.findById(id);
    }
}
