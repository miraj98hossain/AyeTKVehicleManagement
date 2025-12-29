package com.aye.backendservice.service;

import com.aye.commonlib.dto.request.StepTransDetailsRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.stereotype.Service;


@Service
public interface StepTransDetailsService {


    ApiRequestResponse save(StepTransDetailsRequest stepTransRequest, String userName);

    ApiRequestResponse findById(Long stepTransDtlId);

    ApiRequestResponse findAllByStepTransId(Long stepTransId);


}
