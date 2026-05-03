package com.aye.backendservice.service;


import com.aye.RestfulServer.service.MuserService;
import com.aye.backendservice.repository.StepRepository;
import com.aye.dtoLib.dto.request.StepRequest;
import com.aye.entitylib.entity.user.Muser;
import com.aye.entitylib.entity.vehicleproject.Step;
import com.aye.mapper.StepMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StepServiceImpl implements StepService {
    @Autowired
    private MuserService muserService;
    @Autowired
    private StepRepository stepRepository;
    @Autowired
    private StepMapper stepMapper;

    @Override
    public Step saveStep(StepRequest stepRequest, String currentUserName) {
        Muser muser = muserService.findByUserName(currentUserName);
        Step reqStep = stepMapper.toEntity(stepRequest);
        if (reqStep.getStepId() != null) {
            var extStep = this.stepRepository.findById(stepRequest.getStepId()).orElseThrow(
                    () -> new EntityNotFoundException("No Active Step found with this id " + stepRequest.getStepId()));
            reqStep.setCreatedAt(extStep.getCreatedAt());
            reqStep.setCreatedBy(extStep.getCreatedBy());
            reqStep.setUpdatedAt(new Date());
            reqStep.setUpdatedBy(Long.valueOf(muser.getId()));
        } else {
            reqStep.setCreatedAt(new Date());
            reqStep.setCreatedBy(Long.valueOf(muser.getId()));
            reqStep.setIsActive(1);
        }

        reqStep = this.stepRepository.save(reqStep);

        return reqStep;
    }

    @Override
    public Step getStep(Long stepId) {

        Step step = this.stepRepository.findOne((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("stepId"), stepId));
            predicates.add(cb.equal(root.get("isActive"), 1));
            return cb.and(predicates.toArray(new Predicate[0]));
        }).orElseThrow(
                () -> new EntityNotFoundException("No Active Step found with this id " + stepId));

        return step;
    }

    @Override
    public Page<Step> getAllSteps(Pageable pageable) {

        var allActiveSteps = this.stepRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("isActive"), 1));

            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable);

        return allActiveSteps;
    }

    @Override
    public List<Step> getSearchedSteps(String searchWords) {

        var allActiveSteps = this.stepRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (searchWords != null && !searchWords.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("stepName")), "%" + searchWords.toLowerCase() + "%"));
            }
            predicates.add(cb.equal(root.get("isActive"), 1));
            return cb.and(predicates.toArray(new Predicate[0]));
        });

        return allActiveSteps;
    }


}
