package com.aye.backendservice.service;


import com.aye.commonlib.dto.response.StepTransLinesResponse;
import com.aye.backendservice.entity.StepTransLines;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StepTransLinesService {

    StepTransLinesResponse saveStepTransLines(StepTransLines stepTransLines, boolean isStatusChange, Long currentUserId);

    StepTransLines getStepTransLine(String stepTransLinesNo);

    StepTransLines getStepTransLine(Long stepTransLinesId);

    StepTransLines getChildStepLine(Long stepTransLineId);

    Page<StepTransLinesResponse> getAllStepTransLine(Pageable pageable);
}

