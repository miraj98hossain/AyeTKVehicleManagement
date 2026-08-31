package com.aye.backendservice.service;


import com.aye.backendservice.service.orgManagement.UserAccessTempltService;
import com.aye.dto.request.StepTransFilter;
import com.aye.dto.request.StepTransLineFilter;
import com.aye.dto.request.StepTransLinesRequest;
import com.aye.dto.request.StepTransRequest;
import com.aye.dto.response.*;
import com.aye.entity.UserAccessTemltDtl;
import com.aye.entity.UserTransactionTypes;
import com.aye.entity.vehicleproject.StepTrans;
import com.aye.entity.vehicleproject.StepTransLines;
import com.aye.enums.StepStatus;
import com.aye.mapper.StepTransLinesMapper;
import com.aye.mapper.StepTransMapper;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: Miraj
 * @date: 30/04/2026
 * @time: 3:39 pm
 */
@Service
@RequiredArgsConstructor
public class StepTransViewService {
    private final StepTransService stepTransService;
    private final StepTransLinesService stepTransLinesService;
    private final StepTransMapper stepTransMapper;
    private final StepTransLinesMapper stepTransLinesMapper;
    private final ScaleSetupViewService scaleSetupService;
    private final StepWiseTransCountVViewService stepWiseTransCountVService;
    private final StepSetupService stepSetupService;
    private final UserAccessTempltService userAccessTempltService;
    private final CommonHelperService commonHelperService;

    public ApiRequestResponse saveStepTrans(StepTransRequest stepTransRequest, String userName) {
        StepTrans stepTrans = this.stepTransService.saveStepTrans(stepTransRequest, userName);

        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Successfully Created",
                ApiRequestResponseDetail.ObjectType.O,
                "stepTransResponse",
                StepTransResponse.class.getName(),
                stepTransMapper.toResponseDto(stepTrans)
        );
    }

    public ApiRequestResponse updateStepTrans(StepTransRequest stepTransRequest, String userName) {
        StepTrans stepTrans = this.stepTransService.updateStepTrans(stepTransRequest, userName);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Successfully updated",
                ApiRequestResponseDetail.ObjectType.O,
                "stepTransResponse",
                StepTransResponse.class.getName(),
                stepTransMapper.toResponseDto(stepTrans)
        );
    }

    public ApiRequestResponse findById(Long stepTransId) {
        StepTrans stepTrans = this.stepTransService.findById(stepTransId);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Successfully ound step trans",
                ApiRequestResponseDetail.ObjectType.O,
                "stepTransResponse",
                StepTransResponse.class.getName(),
                stepTransMapper.toResponseDto(stepTrans)
        );
    }

    public ApiRequestResponse findAll(Pageable pageable) {
        Page<StepTrans> stepTransPage = this.stepTransService.findAll(pageable);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Successfully found all step trans",
                ApiRequestResponseDetail.ObjectType.PD,
                "stepTransResponseList",
                StepTransResponse.class.getName(),
                stepTransMapper.toResponseDto(stepTransPage)
        );
    }

    public ApiRequestResponse updateTransLines(StepTransLinesRequest linesReq, String userName) throws ExecutionControl.NotImplementedException {
        StepTransLines stepTransLines = this.stepTransService.updateTransLines(linesReq, userName);

        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Successfully updated step trans",
                ApiRequestResponseDetail.ObjectType.O,
                "stepTransLinesResponse",
                StepTransLinesResponse.class.getName(),
                stepTransLinesMapper.toResponseDto(stepTransLines)
        );
    }

    public ApiRequestResponse stepTransLinesSearch(Integer tempDtlId, StepTransLineFilter stepTransFilter) {
        List<StepTransLines> stepTransLines = this.stepTransService.stepTransLinesSearch(tempDtlId, stepTransFilter);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "stepTransLinesList",
                StepTransLinesResponse.class.getName(), this.stepTransLinesMapper.entityListToDtoList(stepTransLines)
        );
    }

    public ApiRequestResponse stepTransSearch(Integer tempDtlId, StepTransFilter stepTransFilter) {
        List<StepTrans> stepTransLines = this.stepTransService.stepTransSearch(tempDtlId, stepTransFilter);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "stepTransList",
                StepTransResponse.class.getName(), this.stepTransMapper.toResponseDto(stepTransLines)
        );
    }


    public ApiRequestResponse findAllByTempDtlId(Integer tempDtlId, String userName, Long invOrgId, String searchWords, String filterStatus, Pageable pageable) {

        UserAccessTemltDtl tempDtl = this.userAccessTempltService.findByDtlId(tempDtlId);

        List<Long> holdAbleStepDetails = tempDtl.getUserAccessInvOrgs()
                .stream()
                .filter(uAInvOrg -> uAInvOrg.getInvOrgs().getId().equals(invOrgId))
                .flatMap(invOrg -> invOrg.getUserTransactionTypes()
                        .stream()
                        .filter(t -> Boolean.TRUE.equals(t.getIsMandatory())))
                .map(UserTransactionTypes::getTrnsTypeId).toList();


        List<Long> setupDetailIds = this.stepTransService.findAllByTempDtlId(tempDtlId, invOrgId, pageable);
        var scaleSetupList = this.scaleSetupService.findAllScaleSetup();
        var stepWiseTransCountVList = this.stepWiseTransCountVService.getCountByDetailId(setupDetailIds);
        var response = this.findAllBySetupDtls(setupDetailIds, holdAbleStepDetails, searchWords, filterStatus, pageable, userName, tempDtl.getUserSpecefic());
        response.getApiRequestResponseDetails().addAll(stepWiseTransCountVList.getApiRequestResponseDetails());
        response.getApiRequestResponseDetails().addAll(scaleSetupList.getApiRequestResponseDetails());
        return response;
    }

    protected ApiRequestResponse findAllBySetupDtls(List<Long> setupDetailIds, List<Long> holdAbleStepDetails,
                                                    String searchWords, String filterStatus, Pageable pageable, String userName, Boolean isUserSpecific) {
        List<Long> userItemAccess;
        if (isUserSpecific) {
            userItemAccess = commonHelperService.getUserAccessedItems(userName);
        } else {
            userItemAccess = new ArrayList<>();
        }

        List<StepSetupDetailsResponse> details = this.stepSetupService.findStepStpDtlByDtlIds(setupDetailIds);

        Set<Long> setupDIds = details.stream()
                .map(StepSetupDetailsResponse::getStepSetupDetailsId)
                .collect(Collectors.toSet());

        Page<StepTransLinesResponse> stepTransPage = this.stepTransLinesService.getAllStepTransLine(setupDIds.stream().toList(), userItemAccess, searchWords, filterStatus, pageable);

        stepTransPage.getContent().stream()
                .filter(linesResponse -> holdAbleStepDetails
                        .contains(linesResponse.getStepSetupDetailsId()))
                .forEach(line -> {
//                    boolean isHoldAble = line.
//                            getStepTransDetails().stream()
//                            .flatMap(detail -> detail.getStepTransDetailsLines().stream())
//                            .limit(2)
//                            .count() > 1;
                    boolean isHoldAble = line.getStepTransDetails().stream()
                            .flatMap(detail -> detail.getStepTransDetailsLines().stream())
                            .map(StepTransDetailsLinesResponse::getInvItemId)
                            .distinct()
                            .limit(2)
                            .count() > 1;
                    line.setHoldAble(isHoldAble);
                    String items = line.getStepTransDetails().stream()
                            .flatMap(stepTDtlRes -> stepTDtlRes.getStepTransDetailsLines().stream())
                            .filter(stepTransDtlLRes -> stepTransDtlLRes.getStepStatus().equals(StepStatus.W.name()))
                            .map(StepTransDetailsLinesResponse::getOrderedItem)
                            .collect(Collectors.joining(", "));
                    line.setCurrentlyLoadingItem(items);
                });


        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Successfully found all step trans",
                ApiRequestResponseDetail.ObjectType.PD,
                "stepTransResponseList",
                StepTransLinesResponse.class.getName(),
                stepTransPage
        );
    }
}
