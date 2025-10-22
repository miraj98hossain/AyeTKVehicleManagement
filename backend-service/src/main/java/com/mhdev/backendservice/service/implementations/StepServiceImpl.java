package com.mhdev.backendservice.service.implementations;

import com.mhdev.backendservice.entity.Step;
import com.mhdev.backendservice.mapper.StepMapper;
import com.mhdev.backendservice.repository.StepRepository;
import com.mhdev.backendservice.service.StepService;
import com.mhdev.commonlib.dto.request.StepRequest;
import com.mhdev.commonlib.dto.response.StepResponse;
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
    private StepRepository stepRepository;
    @Autowired
    private StepMapper stepMapper;

    public StepResponse saveStep(StepRequest stepRequest){
        Step step = stepMapper.toEntity(stepRequest);
        if(step.getStepId()!=null){
           var extStep = this.stepRepository.findById(stepRequest.getStepId()).orElseThrow(
                    ()-> new EntityNotFoundException("Entity not found with this id"+stepRequest.getStepId()));
            step.setCreatedAt(extStep.getCreatedAt());
            step.setCreatedBy(extStep.getCreatedBy());
            step.setUpdatedAt(new Date());
            step.setUpdatedBy((long)1);
            return this.stepMapper.toResponseDto(this.stepRepository.save(step));
        }
        step.setCreatedAt(new Date());
        step.setCreatedBy((long)1);
        return this.stepMapper.toResponseDto(this.stepRepository.save(step));
    }
    public  StepResponse getStep(Long stepId){
        Step step = this.stepRepository.findById(stepId).orElseThrow(
                ()-> new EntityNotFoundException("Entity not found with this id"+stepId));
        return this.stepMapper.toResponseDto(step);
    }

    public Page<StepResponse> getAllSteps(Pageable pageable) {
        Page<Step> allActiveSteps = this.stepRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("isActive"), 1));
            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable);

        return allActiveSteps.map(stepMapper::toResponseDto);
    }

}
