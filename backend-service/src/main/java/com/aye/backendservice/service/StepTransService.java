package com.aye.backendservice.service;

import com.aye.commonlib.dto.request.StepTransLinesRequest;
import com.aye.commonlib.dto.request.StepTransRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface StepTransService {


    ApiRequestResponse saveStepTrans(StepTransRequest stepTransRequest, Long currentUserId);

    ApiRequestResponse findById(Long stepTransId);

    ApiRequestResponse findAll(Pageable pageable);

    ApiRequestResponse updateTransLines(StepTransLinesRequest stepTransLinesRequest, Long currentUserId);

    ApiRequestResponse findAllBySetupDtls(List<Long> setupDetailIds, Pageable pageable);
}
