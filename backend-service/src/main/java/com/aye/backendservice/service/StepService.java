package com.aye.backendservice.service;


import com.aye.commonlib.dto.request.StepRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.data.domain.Pageable;


public interface StepService {
    ApiRequestResponse saveStep(StepRequest stepRequest, Long currentUserId);

    ApiRequestResponse getStep(Long stepId);

    ApiRequestResponse getAllSteps(Pageable pageable);

//    List<Step> getAllSteps();

    ApiRequestResponse getSearchedSteps(String searchWords);
}
