package com.mhdev.backendservice.service.implementations;

import com.mhdev.backendservice.entity.StepTrans;
import com.mhdev.backendservice.mapper.StepTransMapper;
import com.mhdev.backendservice.repository.StepTransRepository;
import com.mhdev.backendservice.service.StepSetupService;
import com.mhdev.commonlib.dto.request.StepTransRequest;
import com.mhdev.commonlib.dto.response.StepTransResponse;
import jakarta.persistence.EntityNotFoundException;
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
public class StepTransServiceImpl {
    @Autowired
    StepTransRepository stepTransRepository;
    @Autowired
    StepTransMapper stepTransMapper;
    @Autowired
    StepSetupService stepSetupService;
    @Transactional
    public StepTransResponse saveStepTrans(StepTransRequest stepTransRequest){
        StepTrans stepTrans = this.stepTransMapper.toEntity(stepTransRequest);
        if(stepTransRequest.getStepTransId()!=null){
            this.stepTransRepository.findById(stepTransRequest.getStepTransId()).orElseThrow(
                    ()-> new EntityNotFoundException("Entity not found with this id"+stepTransRequest.getStepTransId())
            );
            stepTrans.setStepSetup(this.stepSetupService.getStepSetup(stepTransRequest.getStepSetupId()));
            stepTrans.setUpdatedAt(new Date());
            stepTrans.setUpdatedBy((long)1);
            return this.stepTransMapper.toResponseDto(this.stepTransRepository.save(stepTrans));
        }

        stepTrans.setCreatedAt(new Date());
        stepTrans.setCreatedBy((long)1);
        stepTrans.setStepSetup(this.stepSetupService.getStepSetup(stepTransRequest.getStepSetupId()));

        return this.stepTransMapper.toResponseDto(this.stepTransRepository.save(stepTrans));

    }

    @Transactional(readOnly = true)
    public StepTransResponse getStepTrans(Long stepTransId){
       StepTrans stepTrans= this.stepTransRepository.findById(stepTransId).orElseThrow(
                ()-> new EntityNotFoundException("Entity not found with this id"+stepTransId)
        );
        return this.stepTransMapper.toResponseDto(stepTrans);
    }
    @Transactional(readOnly = true)
    public Page<StepTransResponse> getAllStepTrans(Pageable pageable){
        return this.stepTransRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("isActive"), 1));
            return cb.and(predicates.toArray(new Predicate[0]));
        },pageable).map(this.stepTransMapper::toResponseDto);
    }
}
