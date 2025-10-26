package com.mhdev.backendservice.service.implementations;

import com.mhdev.backendservice.entity.Step;
import com.mhdev.backendservice.entity.StepSetup;
import com.mhdev.backendservice.entity.StepSetupDetails;
import com.mhdev.backendservice.mapper.StepSetupDetailsMapper;
import com.mhdev.backendservice.mapper.StepSetupMapper;
import com.mhdev.backendservice.repository.StepSetupRepository;
import com.mhdev.backendservice.service.StepService;
import com.mhdev.backendservice.service.StepSetupDetailsService;
import com.mhdev.backendservice.service.StepSetupService;
import com.mhdev.commonlib.dto.request.StepSetupDetailsRequest;
import com.mhdev.commonlib.dto.request.StepSetupRequest;
import com.mhdev.commonlib.dto.response.StepSetupDetailsResponse;
import com.mhdev.commonlib.dto.response.StepSetupResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StepSetupServiceImpl implements StepSetupService {
    @Autowired
    StepSetupRepository stepSetupRepository;
    @Autowired
    StepService stepService;
    @Autowired
    StepSetupDetailsService stepSetupDetailsService;
    @Autowired
    StepSetupMapper stepSetupMapper;
    @Autowired
    StepSetupDetailsMapper stepSetupDetailsMapper;


    @Transactional
    @Override
    public StepSetupResponse saveStepSetup(StepSetupRequest request) {

        StepSetup stepSetup = stepSetupMapper.toEntity(request);
        stepSetup.setCreatedAt(new Date());
        stepSetup.setCreatedBy(1L);
        Set<Step> existingSteps = new HashSet<>();
        int serialNo = 1;

        // Process and validate new details
        for (StepSetupDetails detail : stepSetup.getStepSetupDetails()) {
            //Checking Duplicate Step Entry
            if (!existingSteps.add(detail.getStep())) {
                throw new IllegalArgumentException("Step cannot be duplicate");
            }
            //Checking Valid Step
            stepService.getStep(detail.getStep().getStepId());

            detail.setSerialNo(serialNo++);
            detail.setCreatedAt(new Date());
            detail.setCreatedBy(1L);
            detail.setIsActive(1);
            detail.setStepSetup(stepSetup);
        }
        StepSetup saved = stepSetupRepository.save(stepSetup);
        return stepSetupMapper.toResponseDto(saved);
    }


    @Transactional
    @Override
    public StepSetupResponse addOrUpdateDetail(StepSetupDetailsRequest newDetailsRequest) {
        StepSetupDetails requestDetails = stepSetupDetailsMapper.toEntity(newDetailsRequest);
        Set<Step> existingSteps;
        int serialNo;
        var stepSetup = this.stepSetupRepository.findById(newDetailsRequest.getStepSetupId()).orElseThrow(
                () -> new EntityNotFoundException("Step Setup Id: " + newDetailsRequest.getStepSetupId())
        );

        if (requestDetails.getStepSetupDetailsId() == null) {
            existingSteps = stepSetup.getStepSetupDetails().stream()
                    .map(StepSetupDetails::getStep)
                    .collect(Collectors.toSet());
            serialNo = existingSteps.size() + 1;
            if (!existingSteps.add(requestDetails.getStep())) {
                throw new IllegalArgumentException("Step is already exists in this setup");
            }
            requestDetails.setSerialNo(serialNo);
            requestDetails.setCreatedAt(new Date());
            requestDetails.setCreatedBy(1L);
            requestDetails.setIsActive(1);
            stepSetup.getStepSetupDetails().add(requestDetails);
            stepSetupRepository.save(stepSetup);
            return this.stepSetupMapper.toResponseDto(stepSetup);
        }
        var dbDetails = this.stepSetupDetailsService.findById(requestDetails.getStepSetupDetailsId());
        dbDetails.setUpdatedAt(new Date());
        dbDetails.setUpdatedBy(1L);
        dbDetails.setIsActive(requestDetails.getIsActive());
        stepSetup.getStepSetupDetails().stream().filter(
                stepSetupDetails -> stepSetupDetails.getStepSetupDetailsId().equals(dbDetails.getStepSetupDetailsId())
        );
        return this.stepSetupMapper.toResponseDto(stepSetup);
    }


    @Transactional(readOnly = true)
    @Override
    public List<StepSetupDetailsResponse> findByIdRes(Long stepSetupId) {
        StepSetup stepSetup = this.stepSetupRepository.findById(stepSetupId).orElseThrow(() -> new EntityNotFoundException("Step Setup Not Found with id:" + stepSetupId));
        return this.stepSetupDetailsService.getDetailsBySetupId(stepSetup);

    }

    @Transactional(readOnly = true)
    @Override
    public StepSetup findById(Long id) {
        return this.stepSetupRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("StepSetup not found with this id " + id)
        );
    }

    @Transactional(readOnly = true)
    @Override
    public List<StepSetupResponse> findAllStepSetup() {
        return this.stepSetupRepository.findAll((root, query, cb) -> {
            Fetch<StepSetup, StepSetupDetails> detailsFetch = root.fetch("stepSetupDetails", JoinType.INNER);
            Join<StepSetup, StepSetupDetails> detailsJoin = (Join<StepSetup, StepSetupDetails>) detailsFetch;
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("isActive"), 1));
            predicates.add(cb.equal(detailsJoin.get("isActive"), 1));
            return cb.and(predicates.toArray(new Predicate[0]));
        }).stream().map(stepSetup -> stepSetupMapper.toResponseDto(stepSetup)).collect(Collectors.toList());

    }
}
