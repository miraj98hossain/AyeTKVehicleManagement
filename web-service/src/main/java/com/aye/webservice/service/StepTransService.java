package com.aye.webservice.service;

import com.aye.dtoLib.dto.request.StepTransFilter;
import com.aye.dtoLib.dto.request.StepTransLinesRequest;
import com.aye.dtoLib.dto.request.StepTransRequest;
import com.aye.dtoLib.dto.response.ApiRequestResponse;
import com.aye.webservice.feignclient.StepTransServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


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
                                                 Long invOrgId,
                                                 String searchWords,
                                                 Pageable pageable) {
        return this.stepTransServiceFeignClient.findAllByTempDtlId(tempDtlId, invOrgId, searchWords, pageable).getBody();

    }


    public ApiRequestResponse searchTransactions(@RequestParam Integer tempDtlId,

                                                 @RequestBody StepTransFilter stepTransFilter) {
        return this.stepTransServiceFeignClient.searchTransactions(tempDtlId, stepTransFilter).getBody();
    }

}
