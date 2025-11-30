package com.aye.vhmwebclient.service;

import com.aye.commonlib.dto.request.StepTransLinesRequest;
import com.aye.commonlib.dto.request.StepTransRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.vhmwebclient.feignclient.StepTransServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class StepTransService {
    @Autowired
    StepTransServiceFeignClient stepTransServiceFeignClient;


    public ApiRequestResponse create(StepTransRequest stepTransRequest, String userName) {
        return stepTransServiceFeignClient.create(userName, stepTransRequest).getBody();
    }


    public ApiRequestResponse updateLines(StepTransLinesRequest stepTransLinesRequest, String userName) {
        return stepTransServiceFeignClient.updateLines(userName, stepTransLinesRequest).getBody();
    }


    public ApiRequestResponse findAll(Pageable pageable) {
        return stepTransServiceFeignClient.findAll(pageable).getBody();
    }


    public ApiRequestResponse findById(Long id) {
        return stepTransServiceFeignClient.findById(id).getBody();
    }


    public ApiRequestResponse findAllByTempDtlId(Integer tempDtlId,
                                                 String searchWords,
                                                 Pageable pageable) {
        return this.stepTransServiceFeignClient.findAllByTempDtlId(tempDtlId, searchWords, pageable).getBody();

    }
}
