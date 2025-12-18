package com.aye.backendservice.service.implementations;

import com.aye.RestfulServer.model.Muser;
import com.aye.RestfulServer.model.UserTransactionTypes;
import com.aye.RestfulServer.service.MuserService;
import com.aye.RestfulServer.service.UserAccessTempltService;
import com.aye.backendservice.entity.Step;
import com.aye.backendservice.entity.StepSetup;
import com.aye.backendservice.entity.StepSetupDetails;
import com.aye.backendservice.mapper.StepSetupDetailsMapper;
import com.aye.backendservice.mapper.StepSetupMapper;
import com.aye.backendservice.repository.StepSetupRepository;
import com.aye.backendservice.service.ApiRequestResponseMaker;
import com.aye.backendservice.service.StepService;
import com.aye.backendservice.service.StepSetupDetailsService;
import com.aye.backendservice.service.StepSetupService;
import com.aye.commonlib.dto.request.StepSetupDetailsRequest;
import com.aye.commonlib.dto.request.StepSetupRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
import com.aye.commonlib.dto.response.StepSetupDetailsResponse;
import com.aye.commonlib.dto.response.StepSetupResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
    StepSetupRepository stepSetupRepository;
    @Autowired
    MuserService muserService;
    @Autowired
    StepService stepService;
    @Autowired
    StepSetupDetailsService stepSetupDetailsService;
    @Autowired
    StepSetupMapper stepSetupMapper;
    @Autowired
    StepSetupDetailsMapper stepSetupDetailsMapper;
    @Autowired
    private UserAccessTempltService userAccessTempltService;

    @Transactional
    @Override
    public ApiRequestResponse saveStepSetup(StepSetupRequest request, String currentUserName) {
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
        return ApiRequestResponseMaker.make(HttpStatus.OK.name(), "Successfully created the step setup",
                ApiRequestResponseDetail.ObjectType.O, "stepSetupResponse",
                StepSetupResponse.class.getName(),
                stepSetupMapper.toResponseDto(stepSetup));
    }


    @Transactional
    @Override
    public ApiRequestResponse addOrUpdateDetail(StepSetupDetailsRequest newDetailsRequest, String currentUserName) {
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

        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O,
                "stepSetupDtlResponse",
                StepSetupDetailsResponse.class.getName(),
                stepSetupDetailsMapper.toResponseDto(requestDetails)
        );
    }


    @Transactional(readOnly = true)
    @Override
    public ApiRequestResponse findByIdRes(Long stepSetupId) {
        StepSetup stepSetup = this.stepSetupRepository.findById(stepSetupId).orElseThrow(
                () -> new EntityNotFoundException("Step Setup Not Found with id:" + stepSetupId));
        ApiRequestResponse response = new ApiRequestResponse();
        response.setHttpStatus(HttpStatus.OK.name());
        response.setMessage("Successfully found the step setup");
        List<ApiRequestResponseDetail> details = new ArrayList<>();
        ApiRequestResponseDetail apiRequestResponseDetail = ApiRequestResponseDetail.builder()
                .objectTag("stepSetupResponse")
                .mapperClass(StepSetupResponse.class.getName())
                .objectType(ApiRequestResponseDetail.ObjectType.O)
                .object(stepSetupMapper.toResponseDto(stepSetup))
                .build();
        details.add(apiRequestResponseDetail);
        response.setApiRequestResponseDetails(details);
        return response;

    }

    @Transactional(readOnly = true)
    @Override
    public ApiRequestResponse findById(Long id) {

        StepSetup stepSetup = this.stepSetupRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("StepSetup not found with this id " + id)
        );
        return ApiRequestResponseMaker.make(HttpStatus.OK.name(), "Successfully created the step setup",
                ApiRequestResponseDetail.ObjectType.O, "stepSetupResponse",
                StepSetupResponse.class.getName(),
                stepSetupMapper.toResponseDto(stepSetup));

    }

    @Transactional(readOnly = true)
    @Override
    public ApiRequestResponse findAllStepSetup(Pageable pageable) {
        ApiRequestResponse response = new ApiRequestResponse();
        response.setHttpStatus(HttpStatus.OK.name());
        response.setMessage("Successfully found all the step setups");

        var page = this.stepSetupRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("isActive"), 1));
            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable).map(stepSetupMapper::toResponseDto);

        List<ApiRequestResponseDetail> detailsResList = new ArrayList<>();
        ApiRequestResponseDetail details = ApiRequestResponseDetail.builder()
                .objectTag("stepSetupResList")
                .object(page)
                .mapperClass(StepSetupResponse.class.getName())
                .objectType(ApiRequestResponseDetail.ObjectType.PD)
                .build();
        detailsResList.add(details);
        response.setApiRequestResponseDetails(detailsResList);
        return response;

    }

    @Transactional(readOnly = true)
    @Override
    public ApiRequestResponse filterStepSetup(Long orgId, Long invOrgId, String searchWords) {
        ApiRequestResponse response = new ApiRequestResponse();
        response.setHttpStatus(HttpStatus.OK.name());
        response.setMessage("Successfully found all the step setups");
        StepSetup stepSetup = this.stepSetupRepository.findOne(
                ((root, query, cb) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    predicates.add(cb.equal(root.get("isActive"), 1));
                    predicates.add(cb.equal(root.get("orgId"), orgId));
                    predicates.add(cb.equal(root.get("invOrg"), invOrgId));
                    return cb.and(predicates.toArray(new Predicate[0]));
                })
        ).orElseThrow(
                () -> new EntityNotFoundException("No Setup Found!!")
        );

        var dtls = this.stepSetupDetailsService.filterStepSetupDetails(stepSetup, orgId, invOrgId, searchWords);
        stepSetup.setStepSetupDetails(dtls);
        List<ApiRequestResponseDetail> detailsResList = new ArrayList<>();
        ApiRequestResponseDetail details = ApiRequestResponseDetail.builder()
                .objectTag("stepSetupResponseList")
                .object(stepSetup.getStepSetupDetails().stream().map(stepSetupDetailsMapper::toResponseDto).collect(Collectors.toList()))
                .mapperClass(StepSetupDetailsResponse.class.getName())
                .objectType(ApiRequestResponseDetail.ObjectType.A)
                .build();
        detailsResList.add(details);
        response.setApiRequestResponseDetails(detailsResList);
        return response;

    }

    @Transactional
    @Override
    public ApiRequestResponse findSetupByDtlId(Long setupDetailId) {
        ApiRequestResponse response = new ApiRequestResponse();
        response.setHttpStatus(HttpStatus.OK.name());
        response.setMessage("Successfully found");
        StepSetupDetails details = this.stepSetupDetailsService.findById(setupDetailId);
        List<ApiRequestResponseDetail> detailsResList = new ArrayList<>();
        ApiRequestResponseDetail resDetails = ApiRequestResponseDetail.builder()
                .objectTag("stepSetupDetailsResponse")
                .object(stepSetupDetailsMapper.toResponseDto(details))
                .mapperClass(StepSetupDetailsResponse.class.getName())
                .objectType(ApiRequestResponseDetail.ObjectType.O)
                .build();
        detailsResList.add(resDetails);
        response.setApiRequestResponseDetails(detailsResList);
        return response;
    }

    @Transactional
    @Override
    public ApiRequestResponse findStepStpDtlByDtlId(Long stepDetailId) {
        StepSetupDetails details = this.stepSetupDetailsService.findById(stepDetailId);

        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "stepSetupDtl",
                StepSetupDetailsResponse.class.getName(), this.stepSetupDetailsMapper.toResponseDto(details)
        );
    }

    @Transactional(readOnly = true)
    @Override
    public List<StepSetupDetailsResponse> findStepStpDtlByDtlIds(List<Long> setupDetailIds) {
        List<StepSetupDetails> details = this.stepSetupDetailsService.findByIds(setupDetailIds);
        return details.stream().map(stepSetupDetailsMapper::toResponseDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public ApiRequestResponse findSetupByTempDtlId(Integer tempDtlId) {
        var userAccessTmpDtl = this.userAccessTempltService.findByDtlId(tempDtlId);

        List<Long> setupDetailIds = userAccessTmpDtl.getUserAccessInvOrgs().stream()
                .flatMap(inv -> inv.getUserTransactionTypes().stream())
                .map(UserTransactionTypes::getTrnsTypeId)
                .toList();

        List<StepSetupDetailsResponse> details = findStepStpDtlByDtlIds(setupDetailIds);

        Set<Long> setupIds = details.stream()
                .map(StepSetupDetailsResponse::getStepSetupId)
                .collect(Collectors.toSet());
        List<StepSetupResponse> stepSetupResponseList = this.stepSetupRepository
                .findAllById(setupIds).stream().map(stepSetupMapper::toResponseDto).toList();

        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "stepSetupList",
                StepSetupResponse.class.getName(), stepSetupResponseList
        );
    }

    @Override
    public ApiRequestResponse getAllDetailsBySetup(Long setupId) {
        StepSetup stepSetup = this.stepSetupRepository.findById(setupId).orElseThrow(
                () -> new EntityNotFoundException("No Setup Found!!")
        );
        List<StepSetupDetailsResponse> list = this.stepSetupDetailsService.getAllDetailsBySetup(stepSetup);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "stepSetupDtlList",
                StepSetupDetailsResponse.class.getName(), list
        );
    }

}
