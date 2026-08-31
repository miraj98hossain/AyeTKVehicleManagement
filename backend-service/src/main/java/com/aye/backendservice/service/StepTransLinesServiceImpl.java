package com.aye.backendservice.service;


import com.aye.backendservice.aspect.annotation.EnableLogging;
import com.aye.backendservice.repository.StepTransLinesRepository;
import com.aye.backendservice.service.specification.StepTransLinesSpecification;
import com.aye.dto.request.StepTransLineFilter;
import com.aye.dto.response.StepTransLinesResponse;
import com.aye.entity.vehicleproject.*;
import com.aye.mapper.StepTransLinesMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class StepTransLinesServiceImpl implements StepTransLinesService {
    @Autowired
    private StepTransLinesRepository stepTransLinesRepository;

    @Autowired
    private StepTransLinesMapper stepTransLinesMapper;
    @Autowired
    private NoGenService noGenService;
    @Autowired
    private StepTransLinesSpecification stepTransLinesSpecification;

    @Transactional
    public StepTransLines saveStepTransLines(StepTransLines stepTransLines, boolean isStatusChange, Long currentUserId) {
        if (stepTransLines.getStepTransLinesId() == null) {
            //stepTransLines.setCreatedAt(new Date());
            stepTransLines.setCreatedBy(currentUserId);
            stepTransLines.setStepTransLinesNo(noGenService.createTransLNo());
        } else {
            //stepTransLines.setUpdatedAt(new Date());
            stepTransLines.setUpdatedBy(currentUserId);
        }
        if (isStatusChange) {
            StepTransTimeline stepTransTimeline = new StepTransTimeline();
            stepTransTimeline.setStepTransLines(stepTransLines);
            stepTransTimeline.setIgnitionTime(LocalDateTime.now());
            stepTransTimeline.setStepStatus(stepTransLines.getStepStatus());
            stepTransLines.getStepTransTimeline().add(stepTransTimeline);
        }

        return this.stepTransLinesRepository.save(stepTransLines);
    }

    @Transactional(readOnly = true)
    @Override
    public StepTransLines getStepTransLine(String stepTransLinesNo) {
        return this.stepTransLinesRepository.findByStepTransLinesNo(stepTransLinesNo).orElseThrow(
                () -> new EntityNotFoundException("StepTransLine not found with No " + stepTransLinesNo));
    }

    @Transactional(readOnly = true)
    @Override
    public StepTransLines getStepTransLine(Long stepTransLinesId) {
        return this.stepTransLinesRepository.findById(stepTransLinesId).orElseThrow(
                () -> new EntityNotFoundException("StepTransLine not found with id " + stepTransLinesId));
    }

    @Transactional(readOnly = true)
    @Override
    public StepTransLines getChildStepLine(Long stepTransLineId) {
        return this.stepTransLinesRepository.findByParentLineId(stepTransLineId).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<StepTransLinesResponse> getAllStepTransLine(Pageable pageable) {
        return this.stepTransLinesRepository.findAll(pageable).map(this.stepTransLinesMapper::toResponseDto);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<StepTransLinesResponse> getAllStepTransLine(
            List<Long> stepSetupDIds,
            List<Long> invItemsAccess,
            String searchWords,
            String filterStatus,
            Pageable pageable) {
        var list = this.stepTransLinesRepository.findAll((root, query, cb) -> {
            assert query != null;
            query.distinct(true);

            Join<StepTransLines, StepTrans> masterJoin = root.join("stepTrans", JoinType.INNER);
            Join<StepTrans, StepTransDetails> stepTransDetails = masterJoin.join("stepTransDetails", JoinType.LEFT);
            Join<StepTransDetails, StepTransDetailsLines> stepTransDetailsLines = stepTransDetails.join("stepTransDetailsLines", JoinType.LEFT);
            Join<StepTransLines, StepSetupDetails> stepSetupDJoin = root.join("stepSetupDetails", JoinType.INNER);
            List<Order> orders = new ArrayList<>();
            orders.add(cb.asc(masterJoin.get("stepTransId")));
            orders.add(cb.asc(masterJoin.get("createdAt")));
            query.orderBy(orders);

            List<Predicate> predicates = new ArrayList<>();
            // stepStatus not in C,R
            predicates.add(cb.not(root.get("stepStatus").in('C', 'R')));
            // setupSetupId IN (...)
            predicates.add(stepSetupDJoin.get("stepSetupDetailsId").in(stepSetupDIds));
            if (searchWords != null && !searchWords.isEmpty()) {
                String pattern = "%" + searchWords.toLowerCase() + "%";
                predicates.add(cb.or(
                        cb.like(cb.lower(masterJoin.get("vehicleNumber")), pattern),
                        cb.like(cb.lower(masterJoin.get("stepTransNo")), pattern),
                        cb.like(cb.lower(masterJoin.get("driverPhoneNo")), pattern),
                        cb.like(cb.lower(masterJoin.get("driverName")), pattern),
                        cb.like(cb.lower(masterJoin.get("transportName")), pattern),
                        cb.like(cb.lower(stepTransDetails.get("custName")), pattern),
                        cb.like(cb.lower(stepTransDetailsLines.get("orderedItem")), pattern)

                ));
            }
            if (filterStatus != null && !filterStatus.isEmpty()) {
                predicates.add(cb.or(cb.equal(root.get("stepStatus"), filterStatus)));
            }
            if (invItemsAccess != null && !invItemsAccess.isEmpty()) {
                predicates.add(cb.in(stepTransDetailsLines.get("invItemId")).value(invItemsAccess));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable);


        return this.stepTransLinesMapper.toResponseDto(list);
    }

    @Transactional(readOnly = true)
    @Override
    @EnableLogging
    public List<StepTransLines> stepTransLinesSearch(List<Long> setupDetailIds, StepTransLineFilter stepTransFilter) {


        Specification<StepTransLines> spec = stepTransLinesSpecification.filterBy(stepTransFilter, setupDetailIds);

        List<StepTransLines> fetchedStepTrans = this.stepTransLinesRepository.findAll(spec);
        return fetchedStepTrans;
    }
}

