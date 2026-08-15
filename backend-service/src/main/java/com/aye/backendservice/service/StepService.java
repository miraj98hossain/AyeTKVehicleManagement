package com.aye.backendservice.service;


import com.aye.dto.request.StepRequest;
import com.aye.entity.vehicleproject.Step;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface StepService {
    Step saveStep(StepRequest stepRequest, String currentUserName);

    Step getStep(Long stepId);

    Page<Step> getAllSteps(Pageable pageable);

//    List<Step> getAllSteps();

    List<Step> getSearchedSteps(String searchWords);
}
