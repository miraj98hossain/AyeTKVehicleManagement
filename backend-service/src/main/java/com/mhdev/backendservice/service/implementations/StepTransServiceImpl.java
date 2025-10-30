package com.mhdev.backendservice.service.implementations;

import com.mhdev.backendservice.entity.StepSetupDetails;
import com.mhdev.backendservice.entity.StepTrans;
import com.mhdev.backendservice.entity.StepTransLines;
import com.mhdev.backendservice.mapper.StepTransLinesMapper;
import com.mhdev.backendservice.mapper.StepTransMapper;
import com.mhdev.backendservice.repository.StepTransRepository;
import com.mhdev.backendservice.service.StepTransLinesService;
import com.mhdev.backendservice.service.StepTransService;
import com.mhdev.backendservice.utils.enums.StepStatus;
import com.mhdev.commonlib.dto.request.StepTransLinesRequest;
import com.mhdev.commonlib.dto.request.StepTransRequest;
import com.mhdev.commonlib.dto.response.ApiRequestResponse;
import com.mhdev.commonlib.dto.response.ApiRequestResponseDetail;
import com.mhdev.commonlib.dto.response.StepTransLinesResponse;
import com.mhdev.commonlib.dto.response.StepTransResponse;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Transactional
    @Override
    public ApiRequestResponse saveStepTrans(StepTransRequest stepTransRequest) {
        StepTrans stepTrans = stepTransMapper.toEntity(stepTransRequest);
        stepTrans.setCreatedAt(new Date());
        stepTrans.setCreatedBy(1L);

        if (stepTrans.getStepTransLinesList().isEmpty()) {
            StepTransLines line = new StepTransLines();
            line.setStepTrans(stepTrans);
            line.setStep(stepTrans.getStepSetup().getStepSetupDetails().get(0).getStep());
            line.setStepStatus(StepStatus.N);
            line.setParentLineId(0L);
            line.setStage(0);
            line.setCreatedBy(1L);
            line.setCreatedAt(new Date());
            stepTrans.getStepTransLinesList().add(line);
        }
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

    @Override
    public ApiRequestResponse updateTransLines(StepTransLinesRequest linesReq) {
        //requestedLine
        StepTransLines reqStepTransLines = this.stepTransLinesMapper.toEntity(linesReq);
        //databaseLine
        StepTransLines dbstepTransLines = this.stepTransLinesService.getStepTransLine(linesReq.getStepTransLinesId());
        //for storing response
        StepTransLinesResponse objResponse = new StepTransLinesResponse();

        //-----------------------Business for pick event Start----------------------------------

        //Checking Request is a pick event or not
        if (linesReq.getPick() != null && linesReq.getPick() == 1) {
            //Checking it is child or not
            //if parent do not need to get it's parentTrans just increment the stage
            if (dbstepTransLines.getParentLineId() == 0) {
                if (dbstepTransLines.getStage() == 1) {
                    throw new IllegalArgumentException("This Step Trans is already picked");
                }
                dbstepTransLines.setStage(dbstepTransLines.getStage() + 1); //current value should be 1(0->1). Eligible to be at wip now.
                objResponse = this.stepTransLinesService.saveStepTransLines(dbstepTransLines);//updating

            } else {
                var parentTransLine = this.stepTransLinesService.getStepTransLine(dbstepTransLines.getParentLineId());
                if (parentTransLine.getStage() == 2) {
                    throw new IllegalArgumentException("This Step Trans is already picked");
                }
                parentTransLine.setStage(parentTransLine.getStage() + 1); //current value should be 2(1->2). Eligible to be at com now.
                objResponse = this.stepTransLinesService.saveStepTransLines(parentTransLine);//updating

            }
        }

        //-----------------------Business for pick event End----------------------------------

        //-----------------------Business for status change event Start----------------------------------

        //Checking Request is a Status Change event or not
        if (linesReq.getPick() == null) {

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
                        var step = stepSetup.get(existingTrans.size()).getStep();
                        StepTransLines newStepTransLines = new StepTransLines();
                        newStepTransLines.setStepStatus(StepStatus.N);
                        newStepTransLines.setStepTrans(stepTrans);
                        newStepTransLines.setStep(step);
                        newStepTransLines.setParentLineId(dbstepTransLines.getStepTransLinesId());
                        newStepTransLines.setStage(0);
                        //creating new line
                        this.stepTransLinesService.saveStepTransLines(newStepTransLines);
                        //updating new line
                        objResponse = this.stepTransLinesService.saveStepTransLines(dbstepTransLines);
                    }
                }
            }
            if (reqStepTransLines.getStepStatus().equals(StepStatus.C)) {
                if (!(dbstepTransLines.getStage() == 2)) {
                    throw new IllegalArgumentException("This step trans is not eligible for Complete");
                } else {
                    dbstepTransLines.setStepStatus(StepStatus.C);
                    dbstepTransLines.setRemarks(reqStepTransLines.getRemarks());
                    objResponse = this.stepTransLinesService.saveStepTransLines(dbstepTransLines);//Changing Status.
                    var childLine = this.stepTransLinesService.getChildStepLine(dbstepTransLines.getStepTransLinesId());
                    childLine.setStage(childLine.getStage() + 1);//current value should be 1(0->1). Eligible to be at wip now.
                    //updating child
                    this.stepTransLinesService.saveStepTransLines(childLine);
                }
            }

            if (reqStepTransLines.getStepStatus().equals(StepStatus.R)) {
                dbstepTransLines.setStepStatus(StepStatus.R);
                dbstepTransLines.setRemarks(reqStepTransLines.getRemarks());
                objResponse = this.stepTransLinesService.saveStepTransLines(dbstepTransLines);//Changing Status.
            }
        }


        //-----------------------Business for status change event End----------------------------------


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

}
