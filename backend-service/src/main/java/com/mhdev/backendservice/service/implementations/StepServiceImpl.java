package com.mhdev.backendservice.service.implementations;


import com.mhdev.backendservice.entity.Step;
import com.mhdev.backendservice.mapper.StepMapper;
import com.mhdev.backendservice.repository.StepRepository;
import com.mhdev.backendservice.service.StepService;
import com.mhdev.commonlib.dto.request.StepRequest;
import com.mhdev.commonlib.dto.response.ApiRequestResponse;
import com.mhdev.commonlib.dto.response.ApiRequestResponseDetail;
import com.mhdev.commonlib.dto.response.StepResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StepServiceImpl implements StepService {

    @Autowired
    private StepRepository stepRepository;
    @Autowired
    private StepMapper stepMapper;

    public ApiRequestResponse saveStep(StepRequest stepRequest) {
        Step reqStep = stepMapper.toEntity(stepRequest);
        ApiRequestResponse response = new ApiRequestResponse();
        if (reqStep.getStepId() != null) {
            var extStep = this.stepRepository.findById(stepRequest.getStepId()).orElseThrow(
                    () -> new EntityNotFoundException("No Active Step found with this id " + stepRequest.getStepId()));
            reqStep.setCreatedAt(extStep.getCreatedAt());
            reqStep.setCreatedBy(extStep.getCreatedBy());
            reqStep.setUpdatedAt(new Date());
            reqStep.setUpdatedBy(1L);
            response.setMessage("Successfully updated the step");
        } else {
            reqStep.setCreatedAt(new Date());
            reqStep.setCreatedBy(1L);
            reqStep.setIsActive(1);
            response.setMessage("Successfully created the Step");
        }

        reqStep = this.stepRepository.save(reqStep);
        response.setHttpStatus(HttpStatus.OK.name());
        List<ApiRequestResponseDetail> detailsResList = new ArrayList<>();
        ApiRequestResponseDetail details = ApiRequestResponseDetail.builder()
                .objectTag("stepResponse")
                .object(this.stepMapper.toResponseDto(reqStep))
                .mapperClass(StepResponse.class.getName())
                .objectType(ApiRequestResponseDetail.ObjectType.O)
                .build();
        detailsResList.add(details);
        response.setApiRequestResponseDetails(detailsResList);

       return response;

    }

    public ApiRequestResponse getStep(Long stepId) {

        Step step = this.stepRepository.findOne((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("stepId"), stepId));
            predicates.add(cb.equal(root.get("isActive"), 1));
            return cb.and(predicates.toArray(new Predicate[0]));
        }).orElseThrow(
                () -> new EntityNotFoundException("No Active Step found with this id " + stepId));

        ApiRequestResponse response = new ApiRequestResponse();
        response.setHttpStatus(HttpStatus.OK.name());
        List<ApiRequestResponseDetail> detailsResList = new ArrayList<>();
        ApiRequestResponseDetail details = ApiRequestResponseDetail.builder()
                .objectTag("stepResponse")
                .object(this.stepMapper.toResponseDto(step))
                .mapperClass(StepResponse.class.getName())
                .objectType(ApiRequestResponseDetail.ObjectType.O)
                .build();
        detailsResList.add(details);
        response.setApiRequestResponseDetails(detailsResList);

        return response;
    }

    public ApiRequestResponse getAllSteps(Pageable pageable) {

        Page<StepResponse> allActiveSteps = this.stepRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("isActive"), 1));
            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable).map(stepMapper::toResponseDto);

        ApiRequestResponse response = new ApiRequestResponse();
        response.setHttpStatus(HttpStatus.OK.name());
        List<ApiRequestResponseDetail> detailsResList = new ArrayList<>();
        ApiRequestResponseDetail details = ApiRequestResponseDetail.builder()
                .objectTag("allStepsResponse")
                .object(allActiveSteps)
                .mapperClass(Page.class.getName())
                .objectType(ApiRequestResponseDetail.ObjectType.PD)
                .build();
        detailsResList.add(details);
        response.setApiRequestResponseDetails(detailsResList);
        return response;
    }


}
