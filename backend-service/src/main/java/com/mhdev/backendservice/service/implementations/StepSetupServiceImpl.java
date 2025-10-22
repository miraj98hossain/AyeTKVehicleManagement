package com.mhdev.backendservice.service.implementations;

import com.mhdev.backendservice.entity.StepSetup;
import com.mhdev.backendservice.mapper.StepSetupMapper;
import com.mhdev.backendservice.repository.StepSetupRepository;
import com.mhdev.backendservice.service.StepSetupDetailsService;
import com.mhdev.backendservice.service.StepSetupService;
import com.mhdev.commonlib.dto.request.StepSetupRequest;
import com.mhdev.commonlib.dto.response.StepSetupResponse;
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
public class StepSetupServiceImpl  implements StepSetupService {
    @Autowired
    StepSetupRepository stepSetupRepository;
    @Autowired
    StepSetupDetailsService stepSetupDetailsService;
    @Autowired
    StepSetupMapper stepSetupMapper;
    @Transactional
    public StepSetupResponse saveStepSetup(StepSetupRequest stepSetupRequest){
        StepSetup stepSetup = this.stepSetupMapper.toEntity(stepSetupRequest);
        if(stepSetup.getStepSetupId()!=null){
           var extStepSetup= this.stepSetupRepository.findById(stepSetupRequest.getStepSetupId()).orElseThrow(
                    ()-> new EntityNotFoundException("Entity not found with this id"+stepSetupRequest.getStepSetupId())
            );
            stepSetup.setCreatedAt(extStepSetup.getCreatedAt());
            stepSetup.setCreatedBy(extStepSetup.getCreatedBy());
            stepSetup.setUpdatedAt(new Date());
            stepSetup.setUpdatedBy((long)1);
            return this.stepSetupMapper.toResponseDto(this.stepSetupRepository.save(stepSetup));
        }
        stepSetup.setCreatedAt(new Date());
        stepSetup.setCreatedBy((long)1);
        for(int i=0;i<stepSetup.getStepSetupDetails().size();i++){
            stepSetup.getStepSetupDetails().get(i).setSerialNo(i);
        }
        this.stepSetupDetailsService.saveAll(stepSetup.getStepSetupDetails());
        return this.stepSetupMapper.toResponseDto(this.stepSetupRepository.save(stepSetup));
    }

    @Transactional(readOnly = true)
    public StepSetupResponse getStepSetupRes(Long id){
        StepSetup stepSetup = this.stepSetupRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Entity not found with this id"+id)
        );
        return this.stepSetupMapper.toResponseDto(stepSetup);
    }
    @Transactional(readOnly = true)
    public StepSetup getStepSetup(Long orgId){
        return this.stepSetupRepository.findOne((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("orgId"), orgId));
            return cb.and(predicates.toArray(new Predicate[0]));
        }).orElseThrow(
                ()-> new EntityNotFoundException("Entity not found with this id"+orgId)
        );


    }
    @Transactional(readOnly = true)
    public Page<StepSetupResponse> getAllStepSetup(Pageable pageable){
        return this.stepSetupRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("isActive"), 1));
            return cb.and(predicates.toArray(new Predicate[0]));
        },pageable).map(this.stepSetupMapper::toResponseDto);
    }
}
