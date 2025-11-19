package com.aye.mobileservice.service;

import com.aye.commonlib.dto.request.StepTransLinesRequest;
import com.aye.commonlib.dto.request.StepTransRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.mobileservice.feignclient.StepTransServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


@Service
public class StepTransService {
    @Autowired
    StepTransServiceFeignClient stepTransServiceFeignClient;


    public ApiRequestResponse create(StepTransRequest stepTransRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return stepTransServiceFeignClient.create(auth.getName(), stepTransRequest).getBody();
    }


    public ApiRequestResponse updateLines(StepTransLinesRequest stepTransLinesRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return stepTransServiceFeignClient.updateLines(auth.getName(), stepTransLinesRequest).getBody();
    }


    public ApiRequestResponse findAll(Pageable pageable) {
        return stepTransServiceFeignClient.findAll(pageable).getBody();
    }


    public ApiRequestResponse findById(Long id) {
        return stepTransServiceFeignClient.findById(id).getBody();
    }

    public ApiRequestResponse findAllByTempDtlId(@RequestParam Integer tempDtlId,
                                                 @RequestParam(required = false) String searchWords,
                                                 @PageableDefault(size = 10, page = 0) Pageable pageable) {
        return stepTransServiceFeignClient.findAllByTempDtlId(tempDtlId, searchWords, pageable).getBody();
    }
}
