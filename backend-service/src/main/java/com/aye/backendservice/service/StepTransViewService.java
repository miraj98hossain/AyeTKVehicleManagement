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
import com.aye.mapper.StepTransLinesMapper;
import com.aye.mapper.StepTransMapper;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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

//    protected ApiRequestResponse findAllBySetupDtls(List<Long> setupDetailIds, List<Long> holdAbleStepDetails,
//                                                    String searchWords, String filterStatus, Pageable pageable, String userName, Boolean isUserSpecific) {
//        List<Long> userItemAccess;
//        if (isUserSpecific) {
//            userItemAccess = commonHelperService.getUserAccessedItems(userName);
//        } else {
//            userItemAccess = new ArrayList<>();
//        }
//
//        List<StepSetupDetailsResponse> details = this.stepSetupService.findStepStpDtlByDtlIds(setupDetailIds);
//
//        Set<Long> setupDIds = details.stream()
//                .map(StepSetupDetailsResponse::getStepSetupDetailsId)
//                .collect(Collectors.toSet());
//
//        Page<StepTransLinesResponse> stepTransPage = this.stepTransLinesService.getAllStepTransLine(setupDIds.stream().toList(), userItemAccess, searchWords, filterStatus, pageable);
//        stepTransPage.getContent().stream()
//                .filter(linesResponse -> holdAbleStepDetails
//                        .contains(linesResponse.getStepSetupDetailsId()))
//                .forEach(line -> {
//                    if (!userItemAccess.isEmpty()) {
//                        line.getStepTransDetails().removeIf(stepTransDetails ->
//                                stepTransDetails.getStepTransDetailsLines().stream()
//                                        .anyMatch(detailLine -> !userItemAccess.contains(detailLine.getInvItemId()))
//                        );
//                    }
//                    line.setHoldAble(true);
//                });
//
//        stepTransPage.getContent().stream()
//                .filter(stepTransLine -> stepTransLine.getParentTransId() != null)
//                .forEach(stepTransLine -> {
//                    stepTransLine.getStepTransDetails().forEach(stepTransDetail ->
//                            stepTransDetail.getStepTransDetailsLines().removeIf(detailLine ->
//                                    !Objects.equals(stepTransLine.getStepTransLinesId(), detailLine.getTrnRefId())
//                            )
//                    );
//
//                    stepTransLine.getStepTransDetails().removeIf(stepTransDetail ->
//                            stepTransDetail.getStepTransDetailsLines().isEmpty()
//                    );
//                });
//
//        return ApiRequestResponseMaker.make(
//                HttpStatus.OK.name(),
//                "Successfully found all step trans",
//                ApiRequestResponseDetail.ObjectType.PD,
//                "stepTransResponseList",
//                StepTransLinesResponse.class.getName(),
//                stepTransPage
//        );
//    }

//    protected ApiRequestResponse findAllBySetupDtls(List<Long> setupDetailIds, List<Long> holdAbleStepDetails,
//                                                    String searchWords, String filterStatus, Pageable pageable,
//                                                    String userName, Boolean isUserSpecific) {
//        List<Long> userItemAccess = isUserSpecific
//                ? commonHelperService.getUserAccessedItems(userName)
//                : new ArrayList<>();
//
//        List<StepSetupDetailsResponse> details = this.stepSetupService.findStepStpDtlByDtlIds(setupDetailIds);
//
//        Set<Long> setupDIds = details.stream()
//                .map(StepSetupDetailsResponse::getStepSetupDetailsId)
//                .collect(Collectors.toSet());
//
//        Page<StepTransLinesResponse> stepTransPage = this.stepTransLinesService
//                .getAllStepTransLine(setupDIds.stream().toList(), userItemAccess, searchWords, filterStatus, pageable);
//
//        // 1. Remove hold-able lines whose permitted detail lines are all completed
//        List<StepTransLinesResponse> filteredContent = stepTransPage.getContent().stream()
//                .filter(linesResponse -> {
//                    if (!holdAbleStepDetails.contains(linesResponse.getStepSetupDetailsId())) {
//                        return true;
//                    }
//
//                    List<StepTransDetailsLinesResponse> permittedLines = linesResponse.getStepTransDetails().stream()
//                            .flatMap(detail -> detail.getStepTransDetailsLines().stream())
//                            .filter(detailLine -> userItemAccess.isEmpty()
//                                    || userItemAccess.contains(detailLine.getInvItemId()))
//                            .toList();
//
//                    boolean allCompleted = !permittedLines.isEmpty()
//                            && permittedLines.stream()
//                            .allMatch(detailLine -> StepStatus.C.name().equals(detailLine.getStepStatus()));
//
//                    return !allCompleted;
//                })
//                .collect(Collectors.toCollection(ArrayList::new));
//
//        // 2. Your existing mutations, now on the filtered list
//        filteredContent.stream()
//                .filter(line -> holdAbleStepDetails.contains(line.getStepSetupDetailsId()))
//                .forEach(line -> {
//                    if (!userItemAccess.isEmpty()) {
//                        line.getStepTransDetails().removeIf(stepTransDetails ->
//                                stepTransDetails.getStepTransDetailsLines().stream()
//                                        .anyMatch(detailLine -> detailLine.getStepStatus().equals(StepStatus.C.name())));
//                        line.getStepTransDetails().removeIf(stepTransDetails ->
//                                stepTransDetails.getStepTransDetailsLines().stream()
//                                        .anyMatch(detailLine -> !userItemAccess.contains(detailLine.getInvItemId())));
//                    }
//                    line.setHoldAble(true);
//                });
//        //for sub transaction only show the transaction item
//        filteredContent.stream()
//                .filter(stepTransLine -> stepTransLine.getParentTransId() != null)
//                .forEach(stepTransLine -> {
//                    stepTransLine.getStepTransDetails().forEach(stepTransDetail ->
//                            stepTransDetail.getStepTransDetailsLines().removeIf(detailLine ->
//                                    !Objects.equals(stepTransLine.getSubTrnRefId(), detailLine.getStepTransDtlLnId())));
//
//                    stepTransLine.getStepTransDetails().removeIf(stepTransDetail ->
//                            stepTransDetail.getStepTransDetailsLines().isEmpty());
//                });
//
//        // 3. Rebuild the Page
//        int removed = stepTransPage.getContent().size() - filteredContent.size();
//        Page<StepTransLinesResponse> resultPage = new PageImpl<>(
//                filteredContent,
//                pageable,
//                Math.max(0, stepTransPage.getTotalElements() - removed)
//        );
//
//        return ApiRequestResponseMaker.make(
//                HttpStatus.OK.name(),
//                "Successfully found all step trans",
//                ApiRequestResponseDetail.ObjectType.PD,
//                "stepTransResponseList",
//                StepTransLinesResponse.class.getName(),
//                resultPage
//        );
//    }


    protected ApiRequestResponse findAllBySetupDtls(List<Long> setupDetailIds, List<Long> holdAbleStepDetails,
                                                    String searchWords, String filterStatus, Pageable pageable,
                                                    String userName, Boolean isUserSpecific) {


        List<StepSetupDetailsResponse> details = this.stepSetupService.findStepStpDtlByDtlIds(setupDetailIds);

        Set<Long> setupDIds = details.stream()
                .map(StepSetupDetailsResponse::getStepSetupDetailsId)
                .collect(Collectors.toSet());

        Page<StepTransLinesResponse> stepTransPage = this.stepTransLinesService
                .getAllStepTransLine(setupDIds.stream().toList(), List.of(), searchWords, filterStatus, pageable);


        List<StepTransLinesResponse> filteredContent = stepTransPage.getContent().stream().toList();

        filteredContent.stream()
                .filter(stepTransLine -> stepTransLine.getParentTransId() != null)
                .forEach(stepTransLine -> {
                    stepTransLine.getStepTransDetails().forEach(stepTransDetail ->
                            stepTransDetail.getStepTransDetailsLines().removeIf(detailLine ->
                                    !Objects.equals(stepTransLine.getStepTransLinesId(), detailLine.getTrnRefId())));

                    stepTransLine.getStepTransDetails().removeIf(stepTransDetail ->
                            stepTransDetail.getStepTransDetailsLines().isEmpty());
                });

        // 3. Rebuild the Page
        int removed = stepTransPage.getContent().size() - filteredContent.size();
        Page<StepTransLinesResponse> resultPage = new PageImpl<>(
                filteredContent,
                pageable,
                Math.max(0, stepTransPage.getTotalElements() - removed)
        );

        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Successfully found all step trans",
                ApiRequestResponseDetail.ObjectType.PD,
                "stepTransResponseList",
                StepTransLinesResponse.class.getName(),
                resultPage
        );
    }
}
