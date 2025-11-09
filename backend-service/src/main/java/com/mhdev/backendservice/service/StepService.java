package com.mhdev.backendservice.service;


import com.mhdev.commonlib.dto.request.StepRequest;
import com.mhdev.commonlib.dto.response.ApiRequestResponse;
import org.springframework.data.domain.Pageable;


public interface StepService {
    ApiRequestResponse saveStep(StepRequest stepRequest, Long currentUserId);

    ApiRequestResponse getStep(Long stepId);

    ApiRequestResponse getAllSteps(Pageable pageable);

    ApiRequestResponse getSearchedSteps(String searchWords);
}
