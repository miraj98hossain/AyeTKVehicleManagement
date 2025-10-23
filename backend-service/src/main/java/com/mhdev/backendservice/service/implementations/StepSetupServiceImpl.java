package com.mhdev.backendservice.service.implementations;

import com.mhdev.backendservice.entity.StepSetup;
import com.mhdev.backendservice.entity.StepSetupDetails;
import com.mhdev.backendservice.mapper.StepSetupMapper;
import com.mhdev.backendservice.repository.StepSetupRepository;
import com.mhdev.backendservice.service.StepSetupDetailsService;
import com.mhdev.backendservice.service.StepSetupService;
import com.mhdev.commonlib.dto.request.StepSetupRequest;
import com.mhdev.commonlib.dto.response.StepSetupDetailsResponse;
import com.mhdev.commonlib.dto.response.StepSetupResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
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
//        if(stepSetup.getStepSetupId()!=null){
//           var extStepSetup= this.stepSetupRepository.findById(stepSetupRequest.getStepSetupId()).orElseThrow(
//                    ()-> new EntityNotFoundException("Entity not found with this id"+stepSetupRequest.getStepSetupId())
//            );
//            stepSetup.setCreatedAt(extStepSetup.getCreatedAt());
//            stepSetup.setCreatedBy(extStepSetup.getCreatedBy());
//            stepSetup.setUpdatedAt(new Date());
//            stepSetup.setUpdatedBy((long)1);
//            return this.stepSetupMapper.toResponseDto(this.stepSetupRepository.save(stepSetup));
//        }
        stepSetup.setCreatedAt(new Date());
        stepSetup.setCreatedBy((long)1);
        stepSetup=this.stepSetupRepository.save(stepSetup);

        for(int i=0;i<stepSetup.getStepSetupDetails().size();i++){
            stepSetup.getStepSetupDetails().get(i).setSerialNo(i);
            stepSetup.getStepSetupDetails().get(i).setCreatedAt(new Date());
            stepSetup.getStepSetupDetails().get(i).setCreatedBy((long)1);
            stepSetup.getStepSetupDetails().get(i).setIsActive(1);
            stepSetup.getStepSetupDetails().get(i).setStepSetup(stepSetup);
        }
        this.stepSetupDetailsService.saveAll(stepSetup.getStepSetupDetails());
        return this.stepSetupMapper.toResponseDto(stepSetup);
    }

    @Transactional(readOnly = true)
    public List<StepSetupDetailsResponse> getStepSetupRes(Long stepSetupId){
        StepSetup stepSetup =this.stepSetupRepository.findById(stepSetupId).orElseThrow(()->new EntityNotFoundException("Step Setup Not Found with id:"+stepSetupId));
        return this.stepSetupDetailsService.getDetailsBySetupId(stepSetup);

    }
    @Transactional(readOnly = true)
    public StepSetup getStepSetup(Long id){
        return this.stepSetupRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Entity not found with this id"+id)
        );
    }
    @Transactional(readOnly = true)
    public Page<StepSetupResponse> getAllStepSetup(Pageable pageable){
        return this.stepSetupRepository.findAll((root, query, cb) -> {
            Fetch<StepSetup, StepSetupDetails> detailsFetch = root.fetch("stepSetupDetails", JoinType.INNER);
            Join<StepSetup, StepSetupDetails> detailsJoin = (Join<StepSetup, StepSetupDetails>) detailsFetch;
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("isActive"), 1));
            predicates.add(cb.equal(detailsJoin.get("isActive"), 1));
            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable).map(stepSetupMapper::toResponseDto);


//        return this.stepSetupRepository.findAll((root, query, cb) -> {
//            Join<StepSetup, StepSetupDetails> detailsJoin = root.join("stepSetupDetails", JoinType.INNER);
//            List<Predicate> predicates = new ArrayList<>();
//            predicates.add(cb.equal(root.get("isActive"), 1));
//            predicates.add(cb.equal(detailsJoin.get("isActive"), 1));
//            return cb.and(predicates.toArray(new Predicate[0]));
//        }, pageable).map(stepSetupMapper::toResponseDto);
    }
}
