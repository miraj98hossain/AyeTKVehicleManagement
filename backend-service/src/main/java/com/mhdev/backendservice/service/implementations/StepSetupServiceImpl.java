package com.mhdev.backendservice.service.implementations;

import com.mhdev.backendservice.entity.Step;
import com.mhdev.backendservice.entity.StepSetup;
import com.mhdev.backendservice.entity.StepSetupDetails;
import com.mhdev.backendservice.mapper.StepSetupMapper;
import com.mhdev.backendservice.repository.StepSetupRepository;
import com.mhdev.backendservice.service.StepService;
import com.mhdev.backendservice.service.StepSetupDetailsService;
import com.mhdev.backendservice.service.StepSetupService;
import com.mhdev.commonlib.dto.request.StepSetupRequest;
import com.mhdev.commonlib.dto.response.StepSetupDetailsResponse;
import com.mhdev.commonlib.dto.response.StepSetupResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


    @Transactional
    public StepSetupResponse saveStepSetup(StepSetupRequest request) {

        StepSetup stepSetup = stepSetupMapper.toEntity(request);
        boolean isUpdate = stepSetup.getStepSetupId() != null;

        StepSetup targetSetup;
        Set<Step> existingSteps = new HashSet<>();
        int serialNo = 1;

        if (isUpdate) {
            targetSetup = stepSetupRepository.findById(stepSetup.getStepSetupId())
                    .orElseThrow(() -> new EntityNotFoundException("StepSetup not found with id: " + stepSetup.getStepSetupId()));
            targetSetup.setIsActive(stepSetup.getIsActive());
            targetSetup.setOrgId(stepSetup.getOrgId());
            targetSetup.setInvOrg(stepSetup.getInvOrg());
            targetSetup.setUpdatedAt(new Date());
            targetSetup.setUpdatedBy(1L);

            existingSteps = targetSetup.getStepSetupDetails().stream()
                    .map(StepSetupDetails::getStep)
                    .collect(Collectors.toSet());

            serialNo = targetSetup.getStepSetupDetails().size() + 1;
        } else {
            targetSetup = stepSetup;
            targetSetup.setCreatedAt(new Date());
            targetSetup.setCreatedBy(1L);
            targetSetup = stepSetupRepository.save(targetSetup);
        }

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
            detail.setStepSetup(targetSetup);
        }

        stepSetupDetailsService.saveAll(stepSetup.getStepSetupDetails());

        // Save main entity only once
        StepSetup saved = stepSetupRepository.save(targetSetup);
        return stepSetupMapper.toResponseDto(saved);
    }


    @Transactional(readOnly = true)
    public List<StepSetupDetailsResponse> getStepSetupRes(Long stepSetupId) {
        StepSetup stepSetup = this.stepSetupRepository.findById(stepSetupId).orElseThrow(() -> new EntityNotFoundException("Step Setup Not Found with id:" + stepSetupId));
        return this.stepSetupDetailsService.getDetailsBySetupId(stepSetup);

    }

    @Transactional(readOnly = true)
    public StepSetup getStepSetup(Long id) {
        return this.stepSetupRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("StepSetup not found with this id " + id)
        );
    }

    @Transactional(readOnly = true)
    public Page<StepSetupResponse> getAllStepSetup(Pageable pageable) {
        return this.stepSetupRepository.findAll((root, query, cb) -> {
            Fetch<StepSetup, StepSetupDetails> detailsFetch = root.fetch("stepSetupDetails", JoinType.INNER);
            Join<StepSetup, StepSetupDetails> detailsJoin = (Join<StepSetup, StepSetupDetails>) detailsFetch;
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("isActive"), 1));
            predicates.add(cb.equal(detailsJoin.get("isActive"), 1));
            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable).map(stepSetupMapper::toResponseDto);

    }
}
