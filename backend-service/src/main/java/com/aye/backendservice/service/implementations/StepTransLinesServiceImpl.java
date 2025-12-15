package com.aye.backendservice.service.implementations;

import com.aye.backendservice.entity.StepSetupDetails;
import com.aye.backendservice.entity.StepTrans;
import com.aye.backendservice.entity.StepTransLines;
import com.aye.backendservice.entity.StepTransTimeline;
import com.aye.backendservice.mapper.StepTransLinesMapper;
import com.aye.backendservice.repository.StepTransLinesRepository;
import com.aye.backendservice.service.StepTransLinesService;
import com.aye.commonlib.dto.response.StepTransLinesResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StepTransLinesServiceImpl implements StepTransLinesService {
    @Autowired
    StepTransLinesRepository stepTransLinesRepository;

    @Autowired
    StepTransLinesMapper stepTransLinesMapper;
    @Autowired
    NoGenService noGenService;

    @Transactional
    public StepTransLinesResponse saveStepTransLines(StepTransLines stepTransLines, boolean isStatusChange, Long currentUserId) {
        if (isStatusChange) {
            switch (stepTransLines.getStepStatus()) {
                case N -> {
                    StepTransTimeline stepTransTimeline = new StepTransTimeline();

                    stepTransTimeline.setStepTransLines(stepTransLines);
                    stepTransTimeline.setIgnTimeN(LocalDateTime.now());
                    stepTransLines.setStepTransTimeline(stepTransTimeline);
                }
                case P -> {
                    stepTransLines.getStepTransTimeline().setIgnTimeP(LocalDateTime.now());
                }
                case W -> {
                    stepTransLines.getStepTransTimeline().setIgnTimeW(LocalDateTime.now());
                }
                case C -> {
                    stepTransLines.getStepTransTimeline().setIgnTimeC(LocalDateTime.now());
                }
                case R -> {
                    stepTransLines.getStepTransTimeline().setIgnTimeR(LocalDateTime.now());
                }
            }
        }
        if (stepTransLines.getStepTransLinesId() == null) {
            stepTransLines.setCreatedAt(new Date());
            stepTransLines.setCreatedBy(currentUserId);
            stepTransLines.setStepTransLinesNo(noGenService.createTransLNo());
        } else {
            stepTransLines.setUpdatedAt(new Date());
            stepTransLines.setUpdatedBy(currentUserId);
        }

        return this.stepTransLinesMapper.toResponseDto(this.stepTransLinesRepository.save(stepTransLines));
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
            String searchWords,
            Pageable pageable) {
        return this.stepTransLinesRepository.findAll((root, query, cb) -> {
            assert query != null;
            query.distinct(true);

            Join<StepTransLines, StepTrans> masterJoin = root.join("stepTrans", JoinType.INNER);
            Join<StepTransLines, StepSetupDetails> stepSetupDJoin = root.join("stepSetupDetails", JoinType.INNER);
            List<Predicate> predicates = new ArrayList<>();
            // stepStatus not in C,R
            predicates.add(cb.not(root.get("stepStatus").in('C', 'R')));
            // setupSetupId IN (...)
            predicates.add(stepSetupDJoin.get("stepSetupDetailsId").in(stepSetupDIds));

            if (searchWords != null && !searchWords.isEmpty()) {
                predicates.add(cb.and(cb.like(masterJoin.get("vehicleNumber"), "%" + searchWords + "%")));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable).map(stepTransLinesMapper::toResponseDto);
    }
}

