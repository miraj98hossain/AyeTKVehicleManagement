package com.mhdev.backendservice.service.implementations;

import com.mhdev.backendservice.entity.StepTransLines;
import com.mhdev.backendservice.entity.StepTransTimeline;
import com.mhdev.backendservice.mapper.StepTransLinesMapper;
import com.mhdev.backendservice.repository.StepTransLinesRepository;
import com.mhdev.backendservice.service.StepTransLinesService;
import com.mhdev.backendservice.service.StepTransTimeLineService;
import com.mhdev.commonlib.dto.response.StepTransLinesResponse;
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
    StepTransTimeLineService stepTransTimeLineService;

    @Transactional
    public StepTransLinesResponse saveStepTransLines(StepTransLines stepTransLines, boolean isStatusChange) {
        if (isStatusChange) {
            //-----------------------Saving StepTransTimeLine----------------------------------
            StepTransTimeline stepTransTimeline = new StepTransTimeline();
            stepTransTimeline.setStep(stepTransLines.getStep());
            stepTransTimeline.setStepTransLines(stepTransLines);
            stepTransTimeline.setIgnitionTime(LocalDateTime.now());
            stepTransTimeline.setStepStatus(stepTransLines.getStepStatus());
            stepTransLines.getStepTransTimelineList().add(stepTransTimeline);
            //-----------------------Ends StepTransTimeLine----------------------------------
        }
        if (stepTransLines.getStepTransLinesId() == null) {
            stepTransLines.setCreatedAt(new Date());
            stepTransLines.setCreatedBy((long) 1);
        } else {
            stepTransLines.setUpdatedAt(new Date());
            stepTransLines.setUpdatedBy((long) 1);
        }
        return this.stepTransLinesMapper.toResponseDto(this.stepTransLinesRepository.save(stepTransLines));
    }

    @Override
    public StepTransLines getStepTransLine(Long stepTransLineId) {
        return this.stepTransLinesRepository.findById(stepTransLineId).orElseThrow(
                () -> new EntityNotFoundException("StepTransLine not found with id " + stepTransLineId));
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

