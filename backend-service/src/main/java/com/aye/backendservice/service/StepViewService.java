package com.aye.backendservice.service;


import com.aye.dtoLib.dto.request.StepRequest;
import com.aye.dtoLib.dto.response.ApiRequestResponse;
import com.aye.dtoLib.dto.response.ApiRequestResponseDetail;
import com.aye.dtoLib.dto.response.StepResponse;
import com.aye.entitylib.entity.vehicleproject.Step;
import com.aye.mapper.StepMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StepViewService {
    private final StepService stepService;
    private final StepMapper mapper;

    public ApiRequestResponse saveStep(StepRequest stepRequest, String currentUserName) {

        Step step = this.stepService.saveStep(stepRequest, currentUserName);

        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "success",
                ApiRequestResponseDetail.ObjectType.O,
                "stepResponse",
                StepResponse.class.getName(),
                this.mapper.toResponseDto(step)
        );
    }

    public ApiRequestResponse getStep(Long stepId) {
        Step step = this.stepService.getStep(stepId);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "success",
                ApiRequestResponseDetail.ObjectType.O,
                "stepResponse",
                StepResponse.class.getName(),
                this.mapper.toResponseDto(step)
        );
    }

    public ApiRequestResponse getAllSteps(Pageable pageable) {
        Page<Step> allActiveSteps = this.stepService.getAllSteps(pageable);

        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Successfully",
                ApiRequestResponseDetail.ObjectType.PD,
                "allStepsResponse",
                StepResponse.class.getName(),
                this.mapper.toResponseDto(allActiveSteps)
        );
    }


    public ApiRequestResponse getSearchedSteps(String searchWords) {
        List<Step> steps = stepService.getSearchedSteps(searchWords);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Successfully",
                ApiRequestResponseDetail.ObjectType.A,
                "allStepsResponse",
                StepResponse.class.getName(),
                this.mapper.toResponseDto(steps)
        );
    }
}
