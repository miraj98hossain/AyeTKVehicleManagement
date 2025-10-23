package com.mhdev.backendservice.service.implementations;


import com.mhdev.backendservice.entity.StepSetup;
import com.mhdev.backendservice.entity.StepSetupDetails;
import com.mhdev.backendservice.mapper.StepSetupDetailsMapper;
import com.mhdev.backendservice.repository.StepSetupDetailsRepository;
import com.mhdev.backendservice.service.StepSetupDetailsService;
import com.mhdev.commonlib.dto.request.StepSetupDetailsRequest;
import com.mhdev.commonlib.dto.response.StepSetupDetailsResponse;
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
public class StepSetupDetailsServiceImpl implements StepSetupDetailsService {
    @Autowired
    private StepSetupDetailsRepository stepSetupDetailsRepository;
    @Autowired
    private StepSetupDetailsMapper stepSetupDetailsMapper;

    @Transactional
    public StepSetupDetailsResponse saveStepStatusDetails(StepSetupDetailsRequest stepSetupDetailsRequest){
        StepSetupDetails stepSetupDetails = stepSetupDetailsMapper.toEntity(stepSetupDetailsRequest);

        if(stepSetupDetails.getStepSetupDetailsId()!=null){
           var extstepSetupDetails= this.stepSetupDetailsRepository.findById(stepSetupDetails.getStepSetupDetailsId()).orElseThrow(
                    ()-> new EntityNotFoundException("Entity Not Found With this id"+stepSetupDetails.getStepSetupDetailsId())
            );
            stepSetupDetails.setCreatedAt(extstepSetupDetails.getCreatedAt());
            stepSetupDetails.setCreatedBy(extstepSetupDetails.getCreatedBy());
            stepSetupDetails.setUpdatedAt(new Date());
            stepSetupDetails.setUpdatedBy((long)1);
           return this.stepSetupDetailsMapper.toResponseDto(stepSetupDetailsRepository.save(stepSetupDetails)) ;
        }
        stepSetupDetails.setCreatedAt(new Date());
        stepSetupDetails.setCreatedBy((long)1);
        return this.stepSetupDetailsMapper.toResponseDto(stepSetupDetailsRepository.save(stepSetupDetails)) ;

    }

    @Transactional(readOnly = true)
    public StepSetupDetailsResponse getStepSetupDetails(Long stepSetupDetailsId){
      StepSetupDetails stepSetupDetails=  this.stepSetupDetailsRepository.findById(stepSetupDetailsId).orElseThrow(
                ()-> new EntityNotFoundException("Entity Not Found With this id"+stepSetupDetailsId)
        );
      return this.stepSetupDetailsMapper.toResponseDto(stepSetupDetails);
    }

    @Transactional(readOnly = true)
    public List<StepSetupDetailsResponse> getDetailsBySetupId(StepSetup stepSetup){
        return this.stepSetupDetailsRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("stepSetup"), stepSetup));
            predicates.add(cb.equal(root.get("isActive"), 1));
            return cb.and(predicates.toArray(new Predicate[0]));
        }).stream().map(this.stepSetupDetailsMapper::toResponseDto).toList();
    }

    @Transactional(readOnly = true)
    public Page<StepSetupDetailsResponse> getAllStepSetupDetails(Pageable pageable){
        return this.stepSetupDetailsRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("isActive"), 1));
            return cb.and(predicates.toArray(new Predicate[0]));
        },pageable).map(this.stepSetupDetailsMapper::toResponseDto);
    }
    @Transactional
    public void saveAll(List<StepSetupDetails> stepSetupDetails){
        this.stepSetupDetailsRepository.saveAll(stepSetupDetails);
    }
}
