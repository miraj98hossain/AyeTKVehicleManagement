package com.mhdev.backendservice.service.implementations;

import com.mhdev.backendservice.entity.StepSetupDetails;
import com.mhdev.backendservice.entity.StepTrans;
import com.mhdev.backendservice.entity.StepTransLines;
import com.mhdev.backendservice.mapper.StepTransLinesMapper;
import com.mhdev.backendservice.mapper.StepTransMapper;
import com.mhdev.backendservice.repository.StepTransRepository;
import com.mhdev.backendservice.service.StepTransLinesService;
import com.mhdev.backendservice.service.StepTransService;
import com.mhdev.backendservice.utils.enums.StepStatus;
import com.mhdev.commonlib.dto.request.StepTransLinesRequest;
import com.mhdev.commonlib.dto.request.StepTransRequest;
import com.mhdev.commonlib.dto.response.StepTransLinesResponse;
import com.mhdev.commonlib.dto.response.StepTransResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class StepTransServiceImpl implements StepTransService {
    @Autowired
    StepTransRepository stepTransRepository;
    @Autowired
    StepTransMapper stepTransMapper;
    @Autowired
    StepTransLinesMapper stepTransLinesMapper;
    @Autowired
    StepTransLinesService stepTransLinesService;

    @Transactional
    @Override
    public StepTransResponse saveStepTrans(StepTransRequest stepTransRequest) {
        StepTrans stepTrans = stepTransMapper.toEntity(stepTransRequest);
        stepTrans.setCreatedAt(new Date());
        stepTrans.setCreatedBy(1L);

        if (stepTrans.getStepTransLinesList().isEmpty()) {
            StepTransLines line = new StepTransLines();
            line.setStepTrans(stepTrans);
            line.setStep(stepTrans.getStepSetup().getStepSetupDetails().get(0).getStep());
            line.setStepStatus(StepStatus.PENDING);
            line.setCreatedBy(1L);
            line.setCreatedAt(new Date());
            stepTrans.getStepTransLinesList().add(line);
        }
        stepTrans = stepTransRepository.save(stepTrans);
        return stepTransMapper.toResponseDto(stepTrans);
    }


    @Transactional()
    @Override
    public StepTransResponse findById(Long stepTransId) {
        StepTrans stepTrans = this.stepTransRepository.findById(stepTransId).orElseThrow(
                () -> new EntityNotFoundException("StepTrans not found with this id " + stepTransId)
        );
        return this.stepTransMapper.toResponseDto(stepTrans);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<StepTransResponse> findAll(Pageable pageable) {
        return this.stepTransRepository.findAll((root, query, cb) -> {
            Fetch<StepTrans, StepTransLines> detailsFetch = root.fetch("stepTransLinesList", JoinType.INNER);
            Join<StepTrans, StepTransLines> detailsJoin = (Join<StepTrans, StepTransLines>) detailsFetch;
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.notEqual(detailsJoin.get("stepStatus"), "Completed"));
            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable).map(stepTransMapper::toResponseDto);
    }

    @Override
    public StepTransLinesResponse updateTransLines(StepTransLinesRequest stepTransLinesRequest) {
        StepTransLines reqStepTransLines = this.stepTransLinesMapper.toEntity(stepTransLinesRequest);
        StepTransLines dbstepTransLines = this.stepTransLinesService.getStepTransLine(stepTransLinesRequest.getStepTransLinesId());

        dbstepTransLines.setStepStatus(reqStepTransLines.getStepStatus());
        dbstepTransLines.setRemarks(reqStepTransLines.getRemarks());

        StepTrans stepTrans = this.stepTransRepository.findById(dbstepTransLines.getStepTrans().getStepTransId()).orElseThrow(
                () -> new EntityNotFoundException("StepTrans not found with this id " + dbstepTransLines.getStepTrans().getStepTransId()));

        List<StepSetupDetails> stepSetup = stepTrans.getStepSetup().getStepSetupDetails();
        var existingTrans = stepTrans.getStepTransLinesList();
        if (reqStepTransLines.getStepStatus().equals(StepStatus.COMPLETED)) {
            if (!(stepSetup.size() == existingTrans.size())) {
                var step = stepSetup.get(existingTrans.size()).getStep();
                StepTransLines newStepTransLines = new StepTransLines();
                newStepTransLines.setStepStatus(StepStatus.PENDING);
                newStepTransLines.setStepTrans(stepTrans);
                newStepTransLines.setStep(step);
                this.stepTransLinesService.saveStepTransLines(newStepTransLines);
            }
        }
        return this.stepTransLinesService.saveStepTransLines(dbstepTransLines);
    }

}
