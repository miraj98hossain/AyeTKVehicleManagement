package com.aye.backendservice.service;


import com.aye.RestfulServer.service.MuserService;
import com.aye.RestfulServer.service.UserAccessTempltService;
import com.aye.backendservice.repository.StepTransRepository;
import com.aye.dtoLib.dto.request.StepTransDetailsRequest;
import com.aye.dtoLib.dto.request.StepTransFilter;
import com.aye.dtoLib.dto.request.StepTransLinesRequest;
import com.aye.dtoLib.dto.request.StepTransRequest;
import com.aye.entitylib.entity.UserTransactionTypes;
import com.aye.entitylib.entity.user.Muser;
import com.aye.entitylib.entity.vehicleproject.StepSetupDetails;
import com.aye.entitylib.entity.vehicleproject.StepTrans;
import com.aye.entitylib.entity.vehicleproject.StepTransLines;
import com.aye.entitylib.entity.vehicleproject.StepTransTimeline;
import com.aye.enums.StepStatus;
import com.aye.enums.StepTransStatus;
import com.aye.mapper.StepTransLinesMapper;
import com.aye.mapper.StepTransMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class StepTransServiceImpl implements StepTransService {
    @Autowired
    private StepTransRepository stepTransRepository;
    @Autowired
    private StepTransMapper stepTransMapper;
    @Autowired
    private StepTransLinesMapper stepTransLinesMapper;
    @Autowired
    private StepTransLinesService stepTransLinesService;
    @Autowired
    private NoGenService noGenService;
    @Autowired
    private MuserService userService;
    @Autowired
    private UserAccessTempltService userAccessTempltService;
    @Autowired
    @Lazy
    private StepTransDetailsCreationService stepTransDetailsCreationService;


    @Transactional
    @Override
    public StepTrans saveStepTrans(StepTransRequest stepTransRequest, String userName) {
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
        stepTrans.setStepTransStatus(StepTransStatus.P);
        stepTrans = stepTransRepository.save(stepTrans);
        return stepTrans;
    }

    @Transactional
    @Override
    public StepTrans updateStepTrans(StepTransRequest stepTransRequest, String userName) {
        Muser muser = userService.findByUserName(userName);

        StepTrans stepTrans = stepTransRepository.findById(stepTransRequest.getStepTransId()).orElseThrow(
                () -> new EntityNotFoundException("Step Trans with id " + stepTransRequest.getStepTransId() + " not found")
        );
        stepTransMapper.toEntity(stepTransRequest, stepTrans);
        stepTrans.setUpdatedAt(new Date());
        stepTrans.setUpdatedBy(Long.valueOf(muser.getId()));
        if (stepTrans.getChallanNumber() != null && !stepTrans.getChallanNumber().isEmpty()) {
            StepTransDetailsRequest dtlRequest = new StepTransDetailsRequest();
            dtlRequest.setStepTransId(stepTransRequest.getStepTransId());
            dtlRequest.setChallanNumber(stepTransRequest.getChallanNumber());
            stepTransDetailsCreationService.save(dtlRequest, userName);
        }
        stepTrans = stepTransRepository.save(stepTrans);
        return stepTrans;
    }

    @Transactional
    @Override
    public StepTrans findById(Long stepTransId) {
        StepTrans stepTrans = this.stepTransRepository.findById(stepTransId).orElseThrow(
                () -> new EntityNotFoundException("StepTrans not found with this id " + stepTransId)
        );

        return stepTrans;

    }

    @Transactional(readOnly = true)
    @Override
    public Page<StepTrans> findAll(Pageable pageable) {
        var list = this.stepTransRepository.findAll((root, query, cb) -> {
            assert query != null;
            query.distinct(true);

            Join<StepTrans, StepTransLines> linesJoin = root.join("stepTransLinesList", JoinType.INNER);

            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.not(linesJoin.get("stepStatus").in('C', 'R')));

            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable);
        list.forEach(stepTrans -> stepTrans.setStepTransLinesList(
                stepTrans.getStepTransLinesList().stream()
                        .filter(line -> !line.getStepStatus().equals(StepStatus.C) && !line.getStepStatus().equals(StepStatus.R))
//                        .filter(line -> !"Complete".equals(line.getStepStatus()) && !"Reject".equals(line.getStepStatus()))
                        .toList()
        ));
        return list;
    }


    @Override
    @Transactional
    public StepTransLines updateTransLines(StepTransLinesRequest linesReq, String userName) {
        Muser muser = userService.findByUserName(userName);

        //requestedLine
        StepTransLines reqStepTransLines = this.stepTransLinesMapper.toEntity(linesReq);
        //databaseLine
        StepTransLines dbstepTransLines = this.stepTransLinesService.getStepTransLine(linesReq.getStepTransLinesId());
        //for storing response
        StepTransLines objResponse = new StepTransLines();
        StepTrans stepTrans = this.stepTransRepository.findById(dbstepTransLines.getStepTrans().getStepTransId()).orElseThrow(
                () -> new EntityNotFoundException("StepTrans not found with this id " + dbstepTransLines.getStepTrans().getStepTransId()));
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
                    if (!dbstepTransLines.getStepStatus().equals(StepStatus.H)) {
                        dbstepTransLines.setStepStatus(StepStatus.W);
                    }
                    dbstepTransLines.setRemarks(reqStepTransLines.getRemarks());
                    //Fetching the setup

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
                    } else {
                        stepTrans.setStepTransStatus(StepTransStatus.C);
                        this.stepTransRepository.save(stepTrans);
                    }
                }
            }
            if (reqStepTransLines.getStepStatus().equals(StepStatus.H)) {
                if (dbstepTransLines.getStepStatus().equals(StepStatus.H)) {
                    throw new IllegalArgumentException("This Step Trans is already hold by " + dbstepTransLines.getHoldBy());
                }
                //Checking it is child or not
                //if parent ? Do not need to find it's parentTrans just increment the stage
                if (dbstepTransLines.getParentLineId() == 0) {
                    dbstepTransLines.setStepStatus(StepStatus.H);
                    dbstepTransLines.setHoldBy(muser.getUserName());
                    if (dbstepTransLines.getStage() != 1) {
                        dbstepTransLines.setStage(dbstepTransLines.getStage() + 1); //current value should be 1(0->1). Eligible to be at WIP now.
                    }

                    objResponse = this.stepTransLinesService.saveStepTransLines(dbstepTransLines, true, Long.valueOf(muser.getId()));//updating

                } else {
                    dbstepTransLines.setStepStatus(StepStatus.H);
                    dbstepTransLines.setHoldBy(muser.getUserName());
                    var parentTransLine = this.stepTransLinesService.getStepTransLine(dbstepTransLines.getParentLineId());
                    if (parentTransLine.getStage() != 2) {
                        parentTransLine.setStage(parentTransLine.getStage() + 1); //current value should be 2(1->2). Eligible to be at com now.
                    }
                    this.stepTransLinesService.saveStepTransLines(parentTransLine, false, Long.valueOf(muser.getId()));//updating parent
                    objResponse = this.stepTransLinesService.saveStepTransLines(dbstepTransLines, true, Long.valueOf(muser.getId()));//updating

                }
            }
            if (reqStepTransLines.getStepStatus().equals(StepStatus.L)) {
                dbstepTransLines.setStepStatus(StepStatus.L);
                dbstepTransLines.setHoldBy(null);
                this.stepTransLinesService.saveStepTransLines(dbstepTransLines, true, Long.valueOf(muser.getId()));
            }
            if (reqStepTransLines.getStepStatus().equals(StepStatus.R)) {
                dbstepTransLines.setStepStatus(StepStatus.R);
                dbstepTransLines.setRemarks(reqStepTransLines.getRemarks());
                rejectTransLine(dbstepTransLines, Long.valueOf(muser.getId()), null);
                stepTrans.setStepTransStatus(StepTransStatus.R);
                this.stepTransRepository.save(stepTrans);
                //objResponse = this.stepTransLinesService.saveStepTransLines(dbstepTransLines, true, Long.valueOf(muser.getId()));//Changing Status.
            }
        }


        //-----------------------Business for status change event Ends----------------------------------


        return objResponse;

    }

    @Transactional(readOnly = true)
    @Override
    public List<Long> findAllByTempDtlId(Integer tempDtlId, Long invOrgId, String searchWords, Pageable pageable) {
        var userAccessTmpDtl = this.userAccessTempltService.findByDtlId(tempDtlId);

        List<Long> setupDetailIds = userAccessTmpDtl.getUserAccessInvOrgs().stream()
                .filter(uAInvOrg -> uAInvOrg.getInvOrgs().getId().equals(invOrgId))
                .flatMap(inv -> inv.getUserTransactionTypes().stream())
                .map(UserTransactionTypes::getTrnsTypeId)
                .toList();
        return setupDetailIds;
    }

    @Override
    public List<StepTransLines> stepTransSearch(Integer tempDtlId, StepTransFilter stepTransFilter) {
        var userAccessTmpDtl = this.userAccessTempltService.findByDtlId(tempDtlId);

        List<Long> setupDetailIds = userAccessTmpDtl.getUserAccessInvOrgs().stream()
                .flatMap(inv -> inv.getUserTransactionTypes().stream())
                .map(UserTransactionTypes::getTrnsTypeId)
                .toList();
        List<StepTransLines> fetchedStepTransLines = this.stepTransLinesService.stepTransSearch(setupDetailIds, stepTransFilter);
        return fetchedStepTransLines;
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
