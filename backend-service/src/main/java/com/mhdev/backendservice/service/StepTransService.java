package com.mhdev.backendservice.service;

import com.mhdev.commonlib.dto.request.StepTransLinesRequest;
import com.mhdev.commonlib.dto.request.StepTransRequest;
import com.mhdev.commonlib.dto.response.StepTransLinesResponse;
import com.mhdev.commonlib.dto.response.StepTransResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface StepTransService {


    StepTransResponse saveStepTrans(StepTransRequest stepTransRequest);

    StepTransResponse findById(Long stepTransId);

    Page<StepTransResponse> findAll(Pageable pageable);

    StepTransLinesResponse updateTransLines(StepTransLinesRequest stepTransLinesRequest);
}
