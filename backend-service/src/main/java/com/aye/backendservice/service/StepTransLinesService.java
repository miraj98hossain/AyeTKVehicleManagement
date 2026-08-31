package com.aye.backendservice.service;


import com.aye.dto.request.StepTransLineFilter;
import com.aye.dto.response.StepTransLinesResponse;
import com.aye.entity.vehicleproject.StepTransLines;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StepTransLinesService {

    StepTransLines saveStepTransLines(StepTransLines stepTransLines, boolean isStatusChange, Long currentUserId);

    StepTransLines getStepTransLine(String stepTransLinesNo);

    StepTransLines getStepTransLine(Long stepTransLinesId);

    StepTransLines getChildStepLine(Long stepTransLineId);

    Page<StepTransLinesResponse> getAllStepTransLine(Pageable pageable);

    Page<StepTransLinesResponse> getAllStepTransLine(
            List<Long> stepSetupIds, List<Long> invItemsAccess, String searchWords, String filterStatus, Pageable pageable);


    List<StepTransLines> stepTransLinesSearch(List<Long> setupDetailIds, StepTransLineFilter stepTransFilter);
}

