package com.aye.backendservice.service.implementations;


import com.aye.backendservice.entity.Step;
import com.aye.backendservice.mapper.StepMapper;
import com.aye.backendservice.repository.StepRepository;
import com.aye.backendservice.service.StepService;
import com.aye.commonlib.dto.request.StepRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
import com.aye.commonlib.dto.response.StepResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public ApiRequestResponse saveStep(StepRequest stepRequest, Long currentUserId) {
        Step reqStep = stepMapper.toEntity(stepRequest);
        ApiRequestResponse response = new ApiRequestResponse();
        if (reqStep.getStepId() != null) {
            var extStep = this.stepRepository.findById(stepRequest.getStepId()).orElseThrow(
                    () -> new EntityNotFoundException("No Active Step found with this id " + stepRequest.getStepId()));
            reqStep.setCreatedAt(extStep.getCreatedAt());
            reqStep.setCreatedBy(extStep.getCreatedBy());
            reqStep.setUpdatedAt(new Date());
            reqStep.setUpdatedBy(currentUserId);
            response.setMessage("Successfully updated the step");
        } else {
            reqStep.setCreatedAt(new Date());
            reqStep.setCreatedBy(currentUserId);
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

    @Override
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

    @Override
    public ApiRequestResponse getAllSteps(Pageable pageable) {

        var allActiveSteps = this.stepRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("isActive"), 1));

            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable).map(stepMapper::toResponseDto);

        ApiRequestResponse response = new ApiRequestResponse();
        response.setHttpStatus(HttpStatus.OK.name());
        response.setMessage("Successfully");
        List<ApiRequestResponseDetail> detailsResList = new ArrayList<>();
        ApiRequestResponseDetail details = ApiRequestResponseDetail.builder()
                .objectTag("allStepsResponse")
                .object(allActiveSteps)
                .mapperClass(StepResponse.class.getName())
                .objectType(ApiRequestResponseDetail.ObjectType.PD)
                .build();
        detailsResList.add(details);
        response.setApiRequestResponseDetails(detailsResList);
        return response;
    }

    @Override
    public ApiRequestResponse getSearchedSteps(String searchWords) {

        var allActiveSteps = this.stepRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (searchWords != null && !searchWords.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("stepName")), "%" + searchWords.toLowerCase() + "%"));
            }
            predicates.add(cb.equal(root.get("isActive"), 1));
            return cb.and(predicates.toArray(new Predicate[0]));
        }).stream().map(stepMapper::toResponseDto);

        ApiRequestResponse response = new ApiRequestResponse();
        response.setHttpStatus(HttpStatus.OK.name());
        response.setMessage("Successfully");
        List<ApiRequestResponseDetail> detailsResList = new ArrayList<>();
        ApiRequestResponseDetail details = ApiRequestResponseDetail.builder()
                .objectTag("allStepsResponse")
                .object(allActiveSteps)
                .mapperClass(StepResponse.class.getName())
                .objectType(ApiRequestResponseDetail.ObjectType.A)
                .build();
        detailsResList.add(details);
        response.setApiRequestResponseDetails(detailsResList);
        return response;
    }

//    public List<Step> getAllSteps() {
//        var list = this.stepRepository.findAllStep();
//        return list;
//    }

}
