package com.aye.backendservice.service;

import com.aye.commonlib.dto.request.StepTransLinesRequest;
import com.aye.commonlib.dto.request.StepTransRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface StepTransService {


    ApiRequestResponse saveStepTrans(StepTransRequest stepTransRequest, String userName);

    ApiRequestResponse findById(Long stepTransId);

    ApiRequestResponse findAll(Pageable pageable);

    ApiRequestResponse updateTransLines(StepTransLinesRequest stepTransLinesRequest, String userName);

//    ApiRequestResponse findAllBySetupDtls(List<Long> setupDetailIds, String searchWords, Pageable pageable);

    ApiRequestResponse findAllByTempDtlId(Integer tempDtlId, String searchWords, Pageable pageable);


}
