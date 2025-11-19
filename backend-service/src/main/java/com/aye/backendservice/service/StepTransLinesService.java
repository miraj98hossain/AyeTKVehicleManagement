package com.aye.backendservice.service;


import com.aye.backendservice.entity.StepTransLines;
import com.aye.commonlib.dto.response.StepTransLinesResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface StepTransLinesService {

    StepTransLinesResponse saveStepTransLines(StepTransLines stepTransLines, boolean isStatusChange, Long currentUserId);

    StepTransLines getStepTransLine(String stepTransLinesNo);

    StepTransLines getStepTransLine(Long stepTransLinesId);

    StepTransLines getChildStepLine(Long stepTransLineId);

    Page<StepTransLinesResponse> getAllStepTransLine(Pageable pageable);

    Page<StepTransLinesResponse> getAllStepTransLine(
            Set<Long> stepIds, List<Long> stepSetupIds, String searchWords, Pageable pageable);
}

