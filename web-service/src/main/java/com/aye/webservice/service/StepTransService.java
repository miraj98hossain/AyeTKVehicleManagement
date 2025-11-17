package com.aye.webservice.service;

import com.aye.commonlib.dto.request.StepTransLinesRequest;
import com.aye.commonlib.dto.request.StepTransRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.feignclient.StepTransServiceFeignClient;
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


    public ApiRequestResponse findAllBySetupDtls(List<Long> setupDetailIds, String searchWords, Pageable pageable) {
        return stepTransServiceFeignClient.findAllBySetupDtls(setupDetailIds, searchWords, pageable).getBody();
    }
}
