package com.mhdev.backendservice.service;

import com.mhdev.commonlib.dto.request.StepTransLinesRequest;
import com.mhdev.commonlib.dto.request.StepTransRequest;
import com.mhdev.commonlib.dto.response.ApiRequestResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface StepTransService {


    ApiRequestResponse saveStepTrans(StepTransRequest stepTransRequest);

    ApiRequestResponse findById(Long stepTransId);

    ApiRequestResponse findAll(Pageable pageable);

    ApiRequestResponse updateTransLines(StepTransLinesRequest stepTransLinesRequest);

    ApiRequestResponse findAllBySetupDtls(List<Long> setupDetailIds, Pageable pageable);
}
