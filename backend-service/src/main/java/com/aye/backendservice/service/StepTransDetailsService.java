package com.aye.backendservice.service;

import com.aye.commonlib.dto.request.StepTransDetailsLinesRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.stereotype.Service;


@Service
public interface StepTransDetailsService {


//    ApiRequestResponse save(StepTransDetailsRequest stepTransRequest, String userName);

    ApiRequestResponse findById(Long stepTransDtlId);

    ApiRequestResponse findAllByStepTransId(Long stepTransId);

    ApiRequestResponse deleteById(Long stepTransDtlId);

    void deleteAllByStepTransId(Long stepTransId);

    //***Line Section*********************
    ApiRequestResponse saveStDtlLine(StepTransDetailsLinesRequest stepTransDetailsLinesRequest, String userName);

    ApiRequestResponse findStDtlLineById(Long stepTransDtlLnId);

    ApiRequestResponse findAllByStTrnDtlId(Long stepTransDtlId);

    ApiRequestResponse deleteLineById(Long stepTransDtlLnId);
}
