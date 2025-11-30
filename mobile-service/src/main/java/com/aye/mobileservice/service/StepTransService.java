package com.aye.mobileservice.service;

import com.aye.commonlib.dto.request.StepTransLinesRequest;
import com.aye.commonlib.dto.request.StepTransRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.mobileservice.feignclient.StepTransServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Service
public class StepTransService {
    @Autowired
    StepTransServiceFeignClient stepTransServiceFeignClient;


    @PostMapping("/create")
    public ApiRequestResponse create(
            String userName,
            StepTransRequest stepTransRequest
    ) {
        return this.stepTransServiceFeignClient.create(userName, stepTransRequest).getBody();
    }

    @PostMapping("/update-lines")
    public ApiRequestResponse updateLines(
            String userName,
            StepTransLinesRequest stepTransLinesRequest) {
        return this.stepTransServiceFeignClient.updateLines(userName, stepTransLinesRequest).getBody();
    }


    public ApiRequestResponse findAll(Pageable pageable) {
        return stepTransServiceFeignClient.findAll(pageable).getBody();
    }


    public ApiRequestResponse findAllByTempDtlId(@RequestParam Integer tempDtlId,
                                                 @RequestParam(required = false) String searchWords,
                                                 @PageableDefault(size = 10, page = 0) Pageable pageable) {
        return this.stepTransServiceFeignClient.findAllByTempDtlId(tempDtlId, searchWords, pageable).getBody();
    }


    public ApiRequestResponse findById(Long id) {
        return this.stepTransServiceFeignClient.findById(id).getBody();
    }
}
