package com.mhdev.webservice.service;

import com.mhdev.commonlib.dto.request.StepTransLinesRequest;
import com.mhdev.commonlib.dto.request.StepTransRequest;
import com.mhdev.commonlib.dto.response.StepTransLinesResponse;
import com.mhdev.commonlib.dto.response.StepTransResponse;
import com.mhdev.webservice.feignclient.StepTransServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class StepTransService {
    @Autowired
    StepTransServiceFeignClient stepTransServiceFeignClient;


    public StepTransResponse create(StepTransRequest stepTransRequest) {
        return stepTransServiceFeignClient.create(stepTransRequest);
    }


    public StepTransLinesResponse updateLines(StepTransLinesRequest stepTransLinesRequest) {
        return stepTransServiceFeignClient.updateLines(stepTransLinesRequest);
    }


    public Page<StepTransResponse> findAll(Pageable pageable) {
        return stepTransServiceFeignClient.findAll(pageable);
    }


    public StepTransResponse findById(Long id) {
        return stepTransServiceFeignClient.findById(id);
    }
}
