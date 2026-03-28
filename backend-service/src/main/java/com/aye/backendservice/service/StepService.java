package com.aye.backendservice.service;


import com.aye.dtoLib.dto.request.StepRequest;
import com.aye.dtoLib.dto.response.ApiRequestResponse;
import org.springframework.data.domain.Pageable;


public interface StepService {
    ApiRequestResponse saveStep(StepRequest stepRequest, String currentUserName);

    ApiRequestResponse getStep(Long stepId);

    ApiRequestResponse getAllSteps(Pageable pageable);

//    List<Step> getAllSteps();

    ApiRequestResponse getSearchedSteps(String searchWords);
}
