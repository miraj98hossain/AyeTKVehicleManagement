package com.aye.mobileservice.service;

import com.aye.mobileservice.feignclient.StepTransServiceFeignClient;
import com.mhdev.commonlib.dto.request.StepTransLinesRequest;
import com.mhdev.commonlib.dto.request.StepTransRequest;
import com.mhdev.commonlib.dto.response.ApiRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StepTransService {
    @Autowired
    StepTransServiceFeignClient stepTransServiceFeignClient;


    public ApiRequestResponse create(StepTransRequest stepTransRequest, Long currentUserId) {
        return stepTransServiceFeignClient.create(currentUserId, stepTransRequest).getBody();
    }


    public ApiRequestResponse updateLines(StepTransLinesRequest stepTransLinesRequest, Long currentUserId) {
        return stepTransServiceFeignClient.updateLines(currentUserId, stepTransLinesRequest).getBody();
    }


    public ApiRequestResponse findAll(Pageable pageable) {
        return stepTransServiceFeignClient.findAll(pageable).getBody();
    }


    public ApiRequestResponse findById(Long id) {
        return stepTransServiceFeignClient.findById(id).getBody();
    }


    public ApiRequestResponse findAllBySetupDtls(List<Long> setupDetailIds, Pageable pageable) {
        return stepTransServiceFeignClient.findAllBySetupDtls(setupDetailIds, pageable).getBody();
    }
}
