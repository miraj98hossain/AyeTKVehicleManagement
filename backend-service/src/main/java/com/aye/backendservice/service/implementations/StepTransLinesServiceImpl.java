package com.aye.backendservice.service.implementations;

import com.aye.commonlib.dto.response.StepTransLinesResponse;
import com.aye.backendservice.entity.StepTransLines;
import com.aye.backendservice.entity.StepTransTimeline;
import com.aye.backendservice.mapper.StepTransLinesMapper;
import com.aye.backendservice.repository.StepTransLinesRepository;
import com.aye.backendservice.service.StepTransLinesService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

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
                    stepTransTimeline.setStep(stepTransLines.getStep());
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

    @Override
    public StepTransLines getStepTransLine(String stepTransLinesNo) {
        return this.stepTransLinesRepository.findByStepTransLinesNo(stepTransLinesNo).orElseThrow(
                () -> new EntityNotFoundException("StepTransLine not found with No " + stepTransLinesNo));
    }

    @Override
    public StepTransLines getStepTransLine(Long stepTransLinesId) {
        return this.stepTransLinesRepository.findById(stepTransLinesId).orElseThrow(
                () -> new EntityNotFoundException("StepTransLine not found with id " + stepTransLinesId));
    }

    @Override
    public StepTransLines getChildStepLine(Long stepTransLineId) {
        return this.stepTransLinesRepository.findByParentLineId(stepTransLineId).orElse(null);
    }

    @Override
    public Page<StepTransLinesResponse> getAllStepTransLine(Pageable pageable) {
        return this.stepTransLinesRepository.findAll(pageable).map(this.stepTransLinesMapper::toResponseDto);
    }
}

