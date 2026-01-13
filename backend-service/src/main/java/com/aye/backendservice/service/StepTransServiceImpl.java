package com.aye.backendservice.service;


import com.aye.RestfulServer.service.MuserService;
import com.aye.RestfulServer.service.UserAccessTempltService;
import com.aye.backendservice.mapper.StepTransLinesMapper;
import com.aye.backendservice.mapper.StepTransMapper;
import com.aye.backendservice.repository.StepTransRepository;
import com.aye.commonlib.dto.request.StepTransDetailsRequest;
import com.aye.commonlib.dto.request.StepTransLinesRequest;
import com.aye.commonlib.dto.request.StepTransRequest;
import com.aye.commonlib.dto.response.*;
import com.aye.entitylib.entity.*;
import com.aye.entitylib.entity.enums.StepStatus;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StepTransServiceImpl implements StepTransService {
    @Autowired
    StepTransRepository stepTransRepository;
    @Autowired
    StepTransMapper stepTransMapper;
    @Autowired
    StepTransLinesMapper stepTransLinesMapper;
    @Autowired
    StepTransLinesService stepTransLinesService;
    @Autowired
    NoGenService noGenService;
    @Autowired
    StepSetupService stepSetupService;
    @Autowired
    MuserService userService;
    @Autowired
    StepWiseTransCountVService transCountVService;
    @Autowired
    private UserAccessTempltService userAccessTempltService;
    @Autowired
    private IPInfoService ipInfoService;
    @Autowired
    private StepTransDetailsCreationService stepTransDetailsCreationService;

    @Transactional
    @Override
    public ApiRequestResponse saveStepTrans(StepTransRequest stepTransRequest, String userName) {
        Muser muser = userService.findByUserName(userName);
        StepTrans stepTrans = stepTransMapper.toEntity(stepTransRequest);
        stepTrans.setCreatedAt(new Date());
        stepTrans.setCreatedBy(Long.valueOf(muser.getId()));

        if (stepTrans.getStepTransLinesList().isEmpty()) {
            StepTransLines line = new StepTransLines();
            line.setStepTrans(stepTrans);
            line.setStepSetupDetails(stepTrans.getStepSetup().getStepSetupDetails().get(0));
            line.setStepStatus(StepStatus.N);
            line.setStepTransLinesNo(noGenService.createTransLNo());
            line.setParentLineId(0L);
            line.setStage(0);
            line.setCreatedBy(Long.valueOf(muser.getId()));
            line.setCreatedAt(new Date());
            stepTrans.getStepTransLinesList().add(line);

//            //-----------------------Saving StepTransTimeLine----------------------------------
            StepTransTimeline stepTransTimeline = new StepTransTimeline();
            stepTransTimeline.setStepTransLines(line);
            stepTransTimeline.setStepStatus(StepStatus.N);
            stepTransTimeline.setIgnitionTime(LocalDateTime.now());
            //-----------------------Ends StepTransTimeLine----------------------------------
            line.getStepTransTimeline().add(stepTransTimeline);
        }
        stepTrans.setStepTransNo(noGenService.createTransNo());
        stepTrans = stepTransRepository.save(stepTrans);
        ApiRequestResponse response = new ApiRequestResponse();
        response.setHttpStatus(HttpStatus.OK.name());
        response.setMessage("Successfully Created");
        ApiRequestResponseDetail details = ApiRequestResponseDetail.builder()
                .objectTag("stepTransResponse")
                .object(this.stepTransMapper.toResponseDto(stepTrans))
                .mapperClass(StepTransResponse.class.getName())
                .objectType(ApiRequestResponseDetail.ObjectType.O)
                .build();
        response.getApiRequestResponseDetails().add(details);
        return response;
    }

    @Transactional
    @Override
    public ApiRequestResponse updateStepTrans(StepTransRequest stepTransRequest, String userName) {
        Muser muser = userService.findByUserName(userName);

        StepTrans stepTrans = stepTransRepository.findById(stepTransRequest.getStepTransId()).orElseThrow(
                () -> new EntityNotFoundException("Step Trans with id " + stepTransRequest.getStepTransId() + " not found")
        );
        stepTransMapper.toEntity(stepTransRequest, stepTrans);
        stepTrans.setUpdatedAt(new Date());
        stepTrans.setUpdatedBy(Long.valueOf(muser.getId()));
        StepTransDetailsRequest dtlRequest = new StepTransDetailsRequest();
        dtlRequest.setStepTransId(stepTransRequest.getStepTransId());
        dtlRequest.setChallanNumber(stepTransRequest.getChallanNumber());
        stepTransDetailsCreationService.save(dtlRequest, userName);
        stepTrans = stepTransRepository.save(stepTrans);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Successfully Updated",
                ApiRequestResponseDetail.ObjectType.O,
                "stepTransResponse",
                StepTransResponse.class.getName(),
                stepTransMapper.toResponseDto(stepTrans)
        );
    }

    @Transactional
    @Override
    public ApiRequestResponse findById(Long stepTransId) {
        StepTrans stepTrans = this.stepTransRepository.findById(stepTransId).orElseThrow(
                () -> new EntityNotFoundException("StepTrans not found with this id " + stepTransId)
        );

        ApiRequestResponse response = new ApiRequestResponse();
        response.setHttpStatus(HttpStatus.OK.name());
        response.setMessage("Successfully found step trans");
        ApiRequestResponseDetail details = ApiRequestResponseDetail.builder()
                .objectTag("stepTransResponse")
                .object(this.stepTransMapper.toResponseDto(stepTrans))
                .mapperClass(StepTransResponse.class.getName())
                .objectType(ApiRequestResponseDetail.ObjectType.O)
                .build();
        response.getApiRequestResponseDetails().add(details);
        return response;

    }

    @Transactional(readOnly = true)
    @Override
    public ApiRequestResponse findAll(Pageable pageable) {
        var list = this.stepTransRepository.findAll((root, query, cb) -> {
            assert query != null;
            query.distinct(true);

            Join<StepTrans, StepTransLines> linesJoin = root.join("stepTransLinesList", JoinType.INNER);

            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.not(linesJoin.get("stepStatus").in('C', 'R')));

            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable).map(stepTransMapper::toResponseDto);
        list.forEach(stepTransResponse -> stepTransResponse.setStepTransLinesResponseList(
                stepTransResponse.getStepTransLinesResponseList().stream()
                        .filter(line -> !"Complete".equals(line.getStepStatus()) && !"Reject".equals(line.getStepStatus()))
                        .toList()
        ));
        ApiRequestResponse response = new ApiRequestResponse();
        response.setHttpStatus(HttpStatus.OK.name());
        response.setMessage("Successfully found all step trans");
        ApiRequestResponseDetail details = ApiRequestResponseDetail.builder()
                .objectTag("stepTransResponseList")
                .object(list)
                .mapperClass(StepTransResponse.class.getName())
                .objectType(ApiRequestResponseDetail.ObjectType.PD)
                .build();
        response.getApiRequestResponseDetails().add(details);
        return response;
    }

    @Transactional(readOnly = true)
    protected ApiRequestResponse findAllBySetupDtls(List<Long> setupDetailIds, String searchWords, Pageable pageable) {
        List<StepSetupDetailsResponse> details = this.stepSetupService.findStepStpDtlByDtlIds(setupDetailIds);

        Set<Long> setupDIds = details.stream()
                .map(StepSetupDetailsResponse::getStepSetupDetailsId)
                .collect(Collectors.toSet());   // or getStepId() depending on the field;

        Set<Long> stepIds = details.stream()
                .map(StepSetupDetailsResponse::getStepId)
                .collect(Collectors.toSet());

        var list = this.stepTransLinesService.getAllStepTransLine(setupDIds.stream().toList(), searchWords, pageable);

        ApiRequestResponse response = new ApiRequestResponse();
        response.setHttpStatus(HttpStatus.OK.name());
        response.setMessage("Successfully found all step trans");
        ApiRequestResponseDetail resdetails = ApiRequestResponseDetail.builder()
                .objectTag("stepTransResponseList")
                .object(list)
                .mapperClass(StepTransLinesResponse.class.getName())
                .objectType(ApiRequestResponseDetail.ObjectType.PD)
                .build();
        response.getApiRequestResponseDetails().add(resdetails);
        return response;
    }


    @Override
    @Transactional
    public ApiRequestResponse updateTransLines(StepTransLinesRequest linesReq, String userName) {
        Muser muser = userService.findByUserName(userName);

        //requestedLine
        StepTransLines reqStepTransLines = this.stepTransLinesMapper.toEntity(linesReq);
        //databaseLine
        StepTransLines dbstepTransLines = this.stepTransLinesService.getStepTransLine(linesReq.getStepTransLinesId());
        //for storing response
        StepTransLinesResponse objResponse = new StepTransLinesResponse();

        //-----------------------Business for pick event Starts----------------------------------

        //Checking Request is a pick event or not
        if (reqStepTransLines.getStepStatus().equals(StepStatus.P)) {
            //Checking it is child or not
            //if parent ? Do not need to find it's parentTrans just increment the stage
            if (dbstepTransLines.getParentLineId() == 0) {
                if (dbstepTransLines.getStage() == 1) {
                    throw new IllegalArgumentException("This Step Trans is already picked");
                }
                dbstepTransLines.setStepStatus(StepStatus.P);
                dbstepTransLines.setStage(dbstepTransLines.getStage() + 1); //current value should be 1(0->1). Eligible to be at WIP now.
                objResponse = this.stepTransLinesService.saveStepTransLines(dbstepTransLines, true, Long.valueOf(muser.getId()));//updating

            } else {
                dbstepTransLines.setStepStatus(StepStatus.P);
                var parentTransLine = this.stepTransLinesService.getStepTransLine(dbstepTransLines.getParentLineId());
                if (parentTransLine.getStage() == 2) {
                    throw new IllegalArgumentException("This Step Trans is already picked");
                }
                parentTransLine.setStage(parentTransLine.getStage() + 1); //current value should be 2(1->2). Eligible to be at com now.
                this.stepTransLinesService.saveStepTransLines(parentTransLine, false, Long.valueOf(muser.getId()));//updating parent
                objResponse = this.stepTransLinesService.saveStepTransLines(dbstepTransLines, true, Long.valueOf(muser.getId()));//updating

            }
        }

        //-----------------------Business for pick event Ends----------------------------------

        //-----------------------Business for status change event Starts----------------------------------

        //Checking Request is a Status Change event or not
        if (!reqStepTransLines.getStepStatus().equals(StepStatus.P)) {

            if (reqStepTransLines.getStepStatus().equals(StepStatus.W)) {
                if (!(dbstepTransLines.getStage() == 1)) {
                    throw new IllegalArgumentException("This step trans is not eligible for WIP");
                } else {
                    //Creating a new Step Trans and changing status
                    dbstepTransLines.setStepStatus(StepStatus.W);
                    dbstepTransLines.setRemarks(reqStepTransLines.getRemarks());
                    //Fetching the setup
                    StepTrans stepTrans = this.stepTransRepository.findById(dbstepTransLines.getStepTrans().getStepTransId()).orElseThrow(
                            () -> new EntityNotFoundException("StepTrans not found with this id " + dbstepTransLines.getStepTrans().getStepTransId()));
                    List<StepSetupDetails> stepSetup = stepTrans.getStepSetup().getStepSetupDetails();
                    var existingTrans = stepTrans.getStepTransLinesList();

                    //if (reqStepTransLines.getStepStatus().equals(StepStatus.C)) {}

                    if (!(stepSetup.size() == existingTrans.size())) {
                        var setupDetails = stepSetup.get(existingTrans.size());
                        StepTransLines newStepTransLines = new StepTransLines();
                        newStepTransLines.setStepStatus(StepStatus.N);
                        newStepTransLines.setStepTrans(stepTrans);
                        newStepTransLines.setStepSetupDetails(setupDetails);
                        newStepTransLines.setParentLineId(dbstepTransLines.getStepTransLinesId());
                        newStepTransLines.setStage(0);
                        //creating new line
                        this.stepTransLinesService.saveStepTransLines(newStepTransLines, true, Long.valueOf(muser.getId()));
                        //updating new line
                        objResponse = this.stepTransLinesService.saveStepTransLines(dbstepTransLines, true, Long.valueOf(muser.getId()));
                    } else {
                        //There is no step left to create also no child left to increase its stage.
                        dbstepTransLines.setStepStatus(reqStepTransLines.getStepStatus());
                        dbstepTransLines.setStage(dbstepTransLines.getStage() + 1);
                        objResponse = this.stepTransLinesService.saveStepTransLines(dbstepTransLines, true, Long.valueOf(muser.getId()));
                    }
                }
            }
            if (reqStepTransLines.getStepStatus().equals(StepStatus.C)) {
                if (!(dbstepTransLines.getStage() == 2)) {
                    throw new IllegalArgumentException("This step trans is not eligible for Complete");
                } else {
                    dbstepTransLines.setStepStatus(StepStatus.C);
                    dbstepTransLines.setRemarks(reqStepTransLines.getRemarks());
                    objResponse = this.stepTransLinesService.saveStepTransLines(dbstepTransLines, true, Long.valueOf(muser.getId()));//Changing Status.
                    var childLine = this.stepTransLinesService.getChildStepLine(dbstepTransLines.getStepTransLinesId());
                    if (childLine != null) {
                        childLine.setStage(childLine.getStage() + 1);//current value should be 1(0->1). Eligible to be at wip now.
                        //updating child
                        this.stepTransLinesService.saveStepTransLines(childLine, false, Long.valueOf(muser.getId()));
                    }
                }
            }

            if (reqStepTransLines.getStepStatus().equals(StepStatus.R)) {
                dbstepTransLines.setStepStatus(StepStatus.R);
                dbstepTransLines.setRemarks(reqStepTransLines.getRemarks());
                rejectTransLine(dbstepTransLines, Long.valueOf(muser.getId()), null);
                //objResponse = this.stepTransLinesService.saveStepTransLines(dbstepTransLines, true, Long.valueOf(muser.getId()));//Changing Status.
            }
        }


        //-----------------------Business for status change event Ends----------------------------------


        ApiRequestResponse response = new ApiRequestResponse();
        response.setHttpStatus(HttpStatus.OK.name());
        response.setMessage("Successfully updated step trans");
        ApiRequestResponseDetail details = ApiRequestResponseDetail.builder()
                .objectTag("stepTransLinesResponse")
                .object(objResponse)
                .mapperClass(StepTransLinesResponse.class.getName())
                .objectType(ApiRequestResponseDetail.ObjectType.O)
                .build();
        response.getApiRequestResponseDetails().add(details);
        return response;

    }

    @Transactional(readOnly = true)
    @Override
    public ApiRequestResponse findAllByTempDtlId(Integer tempDtlId, String searchWords, Pageable pageable) {
        var userAccessTmpDtl = this.userAccessTempltService.findByDtlId(tempDtlId);

        List<Long> setupDetailIds = userAccessTmpDtl.getUserAccessInvOrgs().stream()
                .flatMap(inv -> inv.getUserTransactionTypes().stream())
                .map(UserTransactionTypes::getTrnsTypeId)
                .toList();
        var ipRes = this.ipInfoService.findAllIPInfo();
        var res = this.transCountVService.getCountByDetailId(setupDetailIds);
        var response = findAllBySetupDtls(setupDetailIds, searchWords, pageable);
        response.getApiRequestResponseDetails().addAll(res.getApiRequestResponseDetails());
        response.getApiRequestResponseDetails().addAll(ipRes.getApiRequestResponseDetails());
        return response;
    }


    @Transactional
    protected void rejectTransLine(StepTransLines stepTransLines, Long currentUser, Set<Long> visited) {

        if (visited == null) {
            visited = new HashSet<>();
        }

        Long stepId = stepTransLines.getStepTransLinesId();
        if (visited.contains(stepId)) {
            return; // Already processed this step
        }
        visited.add(stepId);


        var childLine = this.stepTransLinesService.getChildStepLine(stepId);
        if (childLine != null) {
            rejectTransLine(childLine, currentUser, visited);
        }

        // Reject current step
        stepTransLines.setStepStatus(StepStatus.R);
        this.stepTransLinesService.saveStepTransLines(stepTransLines, true, currentUser);
    }

}
