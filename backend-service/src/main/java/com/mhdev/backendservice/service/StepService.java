package com.mhdev.backendservice.service;


import com.mhdev.commonlib.dto.request.StepRequest;
import com.mhdev.commonlib.dto.response.StepResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface StepService {
    StepResponse saveStep(StepRequest stepRequest);
    StepResponse getStep(Long stepId);
    Page<StepResponse> getAllSteps(Pageable pageable);

}
