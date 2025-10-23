package com.mhdev.backendservice.service;


import com.mhdev.backendservice.entity.StepTransLines;
import com.mhdev.commonlib.dto.request.StepTransLinesRequest;
import com.mhdev.commonlib.dto.response.StepTransLinesResponse;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StepTransLinesService {

    StepTransLinesResponse saveStepTransLines(StepTransLines stepTransLines);
    StepTransLinesResponse getStepTransLine(Long stepTransLineId);
    Page<StepTransLinesResponse> getAllStepTransLine(Pageable pageable);
}

