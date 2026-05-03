package com.aye.backendservice.service;


import com.aye.backendservice.repository.StepSetupDetailsRepository;
import com.aye.dtoLib.dto.request.StepSetupDetailsRequest;
import com.aye.entitylib.entity.vehicleproject.Step;
import com.aye.entitylib.entity.vehicleproject.StepSetup;
import com.aye.entitylib.entity.vehicleproject.StepSetupDetails;
import com.aye.mapper.StepSetupDetailsMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StepSetupDetailsServiceImpl implements StepSetupDetailsService {
    @Autowired
    private StepSetupDetailsRepository stepSetupDetailsRepository;
    @Autowired
    private StepSetupDetailsMapper stepSetupDetailsMapper;

    @Transactional
    @Override
    public StepSetupDetails saveStepSetupDetails(StepSetupDetailsRequest stepSetupDetailsRequest) {
        StepSetupDetails stepSetupDetails = stepSetupDetailsMapper.toEntity(stepSetupDetailsRequest);

        if (stepSetupDetails.getStepSetupDetailsId() != null) {
            var extstepSetupDetails = this.stepSetupDetailsRepository.findById(stepSetupDetails.getStepSetupDetailsId()).orElseThrow(
                    () -> new EntityNotFoundException("StepSetupDetails Not Found With this id" + stepSetupDetails.getStepSetupDetailsId())
            );
            stepSetupDetails.setCreatedAt(extstepSetupDetails.getCreatedAt());
            stepSetupDetails.setCreatedBy(extstepSetupDetails.getCreatedBy());
            stepSetupDetails.setUpdatedAt(new Date());
            stepSetupDetails.setUpdatedBy((long) 1);
            return this.stepSetupDetailsRepository.save(stepSetupDetails);
        }
        stepSetupDetails.setCreatedAt(new Date());
        stepSetupDetails.setCreatedBy((long) 1);
        return this.stepSetupDetailsRepository.save(stepSetupDetails);

    }

    @Transactional(readOnly = true)
    @Override
    public StepSetupDetails findById(Long stepSetupDetailsId) {
        return this.stepSetupDetailsRepository.findById(stepSetupDetailsId).orElseThrow(
                () -> new EntityNotFoundException("StepSetupDetails Not Found With this id " + stepSetupDetailsId)
        );
    }

    @Transactional(readOnly = true)
    @Override
    public List<StepSetupDetails> findByIds(List<Long> stepSetupDetailsIds) {

        var list = this.stepSetupDetailsRepository.findAllByStepSetupDetailsIdInAndIsActive(stepSetupDetailsIds, 1);
        if (list.isEmpty()) {
            throw new EntityNotFoundException("No StepSetupDetails Found With this ids ");
        }
        return list;
    }

    @Transactional(readOnly = true)
    @Override
    public List<StepSetupDetails> getDetailsBySetupId(StepSetup stepSetup) {
        return this.stepSetupDetailsRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("stepSetup"), stepSetup));
            predicates.add(cb.equal(root.get("isActive"), 1));
            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }

    @Transactional(readOnly = true)
    @Override
    public List<StepSetupDetails> getAllDetailsBySetup(StepSetup stepSetup) {
        return this.stepSetupDetailsRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("stepSetup"), stepSetup));
            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }

    @Transactional(readOnly = true)
    @Override
    public Page<StepSetupDetails> getAllStepSetupDetails(Pageable pageable) {
        return this.stepSetupDetailsRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("isActive"), 1));
            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable);
    }

    @Transactional
    @Override
    public void saveAll(List<StepSetupDetails> stepSetupDetails) {
        this.stepSetupDetailsRepository.saveAll(stepSetupDetails);
    }

    @Transactional
    @Override
    public StepSetupDetails save(StepSetupDetails stepSetupDetails) {
        return this.stepSetupDetailsRepository.save(stepSetupDetails);
    }

    @Transactional
    @Override
    public List<StepSetupDetails> filterStepSetupDetails(List<StepSetup> stepSetups, Long orgId, Long invOrgId, String searchWords) {

        return this.stepSetupDetailsRepository.findAll((root, query, cb) -> {
            Join<StepSetupDetails, Step> stepJoin = root.join("step", JoinType.INNER);

            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.in(root.get("stepSetup")).value(stepSetups));
            predicates.add(cb.equal(root.get("isActive"), 1));
            if (searchWords != null && !searchWords.isEmpty()) {
                String searchTerm = "%" + searchWords.toLowerCase() + "%";
                predicates.add(cb.like(cb.lower(stepJoin.get("stepName")), searchTerm));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
}
