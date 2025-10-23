package com.mhdev.backendservice.service.implementations;

import com.mhdev.backendservice.entity.StepTransLines;
import com.mhdev.backendservice.mapper.StepTransLinesMapper;
import com.mhdev.backendservice.repository.StepTransLinesRepository;
import com.mhdev.backendservice.service.StepTransLinesService;
import com.mhdev.commonlib.dto.response.StepTransLinesResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class StepTransLinesServiceImpl implements StepTransLinesService {
    @Autowired
    StepTransLinesRepository stepTransLinesRepository;
    @Autowired
    StepTransLinesMapper stepTransLinesMapper;

    @Transactional
    public StepTransLinesResponse saveStepTransLines(StepTransLines stepTransLines) {
        if (stepTransLines.getStepTransLinesId() == null) {
            stepTransLines.setCreatedAt(new Date());
            stepTransLines.setCreatedBy((long) 1);
        } else {
            stepTransLines.setUpdatedAt(new Date());
            stepTransLines.setUpdatedBy((long) 1);
        }
        return this.stepTransLinesMapper.toResponseDto(this.stepTransLinesRepository.save(stepTransLines));


    }


    public StepTransLines getStepTransLine(Long stepTransLineId) {
        return this.stepTransLinesRepository.findById(stepTransLineId).orElseThrow(
                () -> new EntityNotFoundException("Entity not found with id" + stepTransLineId));
    }

    public Page<StepTransLinesResponse> getAllStepTransLine(Pageable pageable) {
        return this.stepTransLinesRepository.findAll(pageable).map(this.stepTransLinesMapper::toResponseDto);
    }
}

