package com.aye.backendservice.service;


import com.aye.RestfulServer.service.MuserService;
import com.aye.RestfulServer.service.UserAccessTempltService;
import com.aye.backendservice.repository.StepSetupRepository;
import com.aye.dtoLib.dto.request.StepSetupDetailsRequest;
import com.aye.dtoLib.dto.request.StepSetupRequest;
import com.aye.dtoLib.dto.response.StepSetupDetailsResponse;
import com.aye.entitylib.entity.UserTransactionTypes;
import com.aye.entitylib.entity.user.Muser;
import com.aye.entitylib.entity.vehicleproject.Step;
import com.aye.entitylib.entity.vehicleproject.StepSetup;
import com.aye.entitylib.entity.vehicleproject.StepSetupDetails;
import com.aye.mapper.StepSetupDetailsMapper;
import com.aye.mapper.StepSetupMapper;
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
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StepSetupServiceImpl implements StepSetupService {
    @Autowired
    private StepSetupRepository stepSetupRepository;
    @Autowired
    private MuserService muserService;
    @Autowired
    private StepSetupDetailsService stepSetupDetailsService;
    @Autowired
    private StepSetupMapper stepSetupMapper;
    @Autowired
    private StepSetupDetailsMapper stepSetupDetailsMapper;
    @Autowired
    private UserAccessTempltService userAccessTempltService;

    @Transactional
    @Override
    public StepSetup saveStepSetup(StepSetupRequest request, String currentUserName) {
        Muser muser = this.muserService.findByUserName(currentUserName);
        StepSetup stepSetup;
        if (request.getStepSetupId() != null) {
            var dbSetup = this.stepSetupRepository.findById(request.getStepSetupId()).orElseThrow(
                    () -> new EntityNotFoundException("Step Setup Id not found")
            );
            this.stepSetupMapper.dtoToEntity(request, dbSetup);
            dbSetup.setUpdatedAt(new Date());
            dbSetup.setUpdatedBy(Long.valueOf(muser.getId()));
            stepSetup = stepSetupRepository.save(dbSetup);
        } else {
            stepSetup = stepSetupMapper.dtoToEntity(request);
            stepSetup.setCreatedAt(new Date());
            stepSetup.setCreatedBy(Long.valueOf(muser.getId()));
            stepSetup = stepSetupRepository.save(stepSetup);
        }
        return stepSetup;
    }


    @Transactional
    @Override
    public StepSetupDetails addOrUpdateDetail(StepSetupDetailsRequest newDetailsRequest, String currentUserName) {
        Muser muser = this.muserService.findByUserName(currentUserName);
        StepSetupDetails requestDetails = stepSetupDetailsMapper.toEntity(newDetailsRequest);
        Set<Step> existingSteps;
        int serialNo;
        var stepSetup = this.stepSetupRepository.findById(newDetailsRequest.getStepSetupId()).orElseThrow(
                () -> new EntityNotFoundException("Step Setup Id: " + newDetailsRequest.getStepSetupId())
        );
        if (requestDetails.getStepSetupDetailsId() == null) {
            existingSteps = stepSetup.getStepSetupDetails().stream()
                    .map(StepSetupDetails::getStep)
                    .collect(Collectors.toSet());
            serialNo = existingSteps.size() + 1;
            if (!existingSteps.add(requestDetails.getStep())) {
                throw new IllegalArgumentException("Step is already exists in this setup");
            }
            requestDetails.setSerialNo(serialNo);
            requestDetails.setCreatedAt(new Date());
            requestDetails.setCreatedBy(Long.valueOf(muser.getId()));
            requestDetails.setIsActive(1);
            requestDetails = this.stepSetupDetailsService.save(requestDetails);
        } else {
            var dbDetails = this.stepSetupDetailsService.findById(requestDetails.getStepSetupDetailsId());
            dbDetails.setUpdatedAt(new Date());
            dbDetails.setUpdatedBy(Long.valueOf(muser.getId()));
            dbDetails.setIsActive(requestDetails.getIsActive());
            requestDetails = this.stepSetupDetailsService.save(dbDetails);
        }

        return requestDetails;
    }


    @Transactional(readOnly = true)
    @Override
    public StepSetup findByIdRes(Long stepSetupId) {
        StepSetup stepSetup = this.stepSetupRepository.findById(stepSetupId).orElseThrow(
                () -> new EntityNotFoundException("Step Setup Not Found with id:" + stepSetupId));
        return stepSetup;

    }

    @Transactional(readOnly = true)
    @Override
    public StepSetup findById(Long id) {

        StepSetup stepSetup = this.stepSetupRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("StepSetup not found with this id " + id)
        );
        return stepSetup;

    }

    @Transactional(readOnly = true)
    @Override
    public Page<StepSetup> findAllStepSetup(Pageable pageable) {


        var page = this.stepSetupRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("isActive"), 1));
            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable);

        return page;
    }

    @Transactional(readOnly = true)
    @Override
    public List<StepSetupDetails> filterStepSetup(Long orgId, Long invOrgId, String searchWords) {
        List<StepSetup> stepSetups = this.stepSetupRepository.findAll(
                ((root, query, cb) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    predicates.add(cb.equal(root.get("isActive"), 1));
                    predicates.add(cb.equal(root.get("org").get("id"), orgId));
                    predicates.add(cb.equal(root.get("invOrg").get("id"), invOrgId));
                    return cb.and(predicates.toArray(new Predicate[0]));
                })
        );
        var dtls = this.stepSetupDetailsService.filterStepSetupDetails(stepSetups, orgId, invOrgId, searchWords);
        return dtls;
    }


    @Transactional(readOnly = true)
    @Override
    public List<StepSetupDetailsResponse> findStepStpDtlByDtlIds(List<Long> setupDetailIds) {
        List<StepSetupDetails> details = this.stepSetupDetailsService.findByIds(setupDetailIds);
        return details.stream().map(stepSetupDetailsMapper::toResponseDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<StepSetup> findSetupByTempDtlId(Integer tempDtlId, Long invOrgId) {
        var userAccessTmpDtl = this.userAccessTempltService.findByDtlId(tempDtlId);
        List<Long> setupDetailIds = userAccessTmpDtl.getUserAccessInvOrgs()
                .stream()
                .filter(accessInvOrg -> accessInvOrg.getInvOrgs().getId().equals(invOrgId))
                .flatMap(inv -> inv.getUserTransactionTypes().stream())
                .map(UserTransactionTypes::getTrnsTypeId)
                .toList();
        List<StepSetupDetailsResponse> details = findStepStpDtlByDtlIds(setupDetailIds);
        Set<Long> setupIds = details.stream()
                .map(StepSetupDetailsResponse::getStepSetupId)
                .collect(Collectors.toSet());
        List<StepSetup> stepSetupResponseList = this.stepSetupRepository
                .findAllById(setupIds);
        return stepSetupResponseList;
    }

    @Override
    public List<StepSetupDetails> getAllDetailsBySetup(Long setupId) {
        StepSetup stepSetup = this.stepSetupRepository.findById(setupId).orElseThrow(
                () -> new EntityNotFoundException("No Setup Found!!")
        );
        List<StepSetupDetails> list = this.stepSetupDetailsService.getAllDetailsBySetup(stepSetup);
        return list;
    }

}
