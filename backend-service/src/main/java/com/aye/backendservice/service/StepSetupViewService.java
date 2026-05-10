package com.aye.backendservice.service;


import com.aye.dtoLib.dto.request.StepSetupDetailsRequest;
import com.aye.dtoLib.dto.request.StepSetupRequest;
import com.aye.dtoLib.dto.response.ApiRequestResponse;
import com.aye.dtoLib.dto.response.ApiRequestResponseDetail;
import com.aye.dtoLib.dto.response.StepSetupDetailsResponse;
import com.aye.dtoLib.dto.response.StepSetupResponse;
import com.aye.entitylib.entity.vehicleproject.StepSetup;
import com.aye.entitylib.entity.vehicleproject.StepSetupDetails;
import com.aye.mapper.StepSetupDetailsMapper;
import com.aye.mapper.StepSetupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StepSetupViewService {
    private final StepSetupDetailsService stepSetupDetailsService;
    private final StepSetupMapper stepSetupMapper;
    private final StepSetupDetailsMapper stepSetupDetailsMapper;
    private final StepSetupService stepSetupService;

    public ApiRequestResponse saveStepSetup(StepSetupRequest request, String currentUserName) {
        StepSetup stepSetup = this.stepSetupService.saveStepSetup(request, currentUserName);
        StepSetupResponse stepSetupResponse = this.stepSetupMapper.toResponseDto(stepSetup);
        return ApiRequestResponseMaker.make(HttpStatus.OK.name(), "Successfully created the step setup",
                ApiRequestResponseDetail.ObjectType.O, "stepSetupResponse",
                StepSetupResponse.class.getName(),
                stepSetupResponse
        );
    }


    public ApiRequestResponse addOrUpdateDetail(StepSetupDetailsRequest newDetailsRequest, String currentUserName) {
        StepSetupDetails stepSetupDetails = this.stepSetupService.addOrUpdateDetail(newDetailsRequest, currentUserName);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O,
                "stepSetupDtlResponse",
                StepSetupDetailsResponse.class.getName(),
                stepSetupDetailsMapper.toResponseDto(stepSetupDetails)
        );
    }


    public ApiRequestResponse findByIdRes(Long stepSetupId) {
        StepSetup stepSetup = this.stepSetupService.findById(stepSetupId);
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


    public ApiRequestResponse findById(Long id) {
        StepSetup stepSetup = this.stepSetupService.findById(id);
        return ApiRequestResponseMaker.make(HttpStatus.OK.name(), "Successfully created the step setup",
                ApiRequestResponseDetail.ObjectType.O, "stepSetupResponse",
                StepSetupResponse.class.getName(),
                stepSetupMapper.toResponseDto(stepSetup));

    }


    public ApiRequestResponse findAllStepSetup(Pageable pageable) {
        ApiRequestResponse response = new ApiRequestResponse();
        response.setHttpStatus(HttpStatus.OK.name());
        response.setMessage("Successfully found all the step setups");
        var page = this.stepSetupService.findAllStepSetup(pageable);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.PD, "stepSetupResList",
                StepSetupResponse.class.getName(),
                this.stepSetupMapper.toResponseDto(page)
        );
    }


    public ApiRequestResponse filterStepSetup(Long orgId, Long invOrgId, String searchWords) {
        List<StepSetupDetails> stepSetupsDetails = this.stepSetupService.filterStepSetup(orgId, invOrgId, searchWords);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Successfully found all the step setups",
                ApiRequestResponseDetail.ObjectType.A,
                "stepSetupResponseList",
                StepSetupDetailsResponse.class.getName(),
                this.stepSetupDetailsMapper.toResponseDto(stepSetupsDetails)
        );
    }


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


    public ApiRequestResponse findStepStpDtlByDtlId(Long stepDetailId) {
        StepSetupDetails details = this.stepSetupDetailsService.findById(stepDetailId);

        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "stepSetupDtl",
                StepSetupDetailsResponse.class.getName(), this.stepSetupDetailsMapper.toResponseDto(details)
        );
    }


    public List<StepSetupDetailsResponse> findStepStpDtlByDtlIds(List<Long> setupDetailIds) {
        List<StepSetupDetails> details = this.stepSetupDetailsService.findByIds(setupDetailIds);
        return details.stream().map(stepSetupDetailsMapper::toResponseDto).collect(Collectors.toList());
    }


    public ApiRequestResponse findSetupByTempDtlId(Integer tempDtlId, Long invOrgId) {
        List<StepSetup> stepSetupList = this.stepSetupService.findSetupByTempDtlId(tempDtlId, invOrgId);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "stepSetupList",
                StepSetupResponse.class.getName(), this.stepSetupMapper.toResponseDto(stepSetupList)
        );
    }


    public ApiRequestResponse getAllDetailsBySetup(Long setupId) {
        List<StepSetupDetails> list = this.stepSetupService.getAllDetailsBySetup(setupId);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "stepSetupDtlList",
                StepSetupDetailsResponse.class.getName(), this.stepSetupDetailsMapper.toResponseDto(list)
        );
    }

}
