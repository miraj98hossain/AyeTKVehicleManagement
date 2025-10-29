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
            line.setStepStatus(StepStatus.PENDING);
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
            //Fetch<StepTrans, StepTransLines> detailsFetch = root.fetch("stepTransLinesList", JoinType.INNER);
            Join<StepTrans, StepTransLines> detailsJoin = root.join("stepTransLinesList", JoinType.INNER);
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(detailsJoin.get("stepStatus"), "PENDING"));
            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable).map(stepTransMapper::toResponseDto);
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
    public ApiRequestResponse updateTransLines(StepTransLinesRequest stepTransLinesRequest) {
        StepTransLines reqStepTransLines = this.stepTransLinesMapper.toEntity(stepTransLinesRequest);
        StepTransLines dbstepTransLines = this.stepTransLinesService.getStepTransLine(stepTransLinesRequest.getStepTransLinesId());

        dbstepTransLines.setStepStatus(reqStepTransLines.getStepStatus());
        dbstepTransLines.setRemarks(reqStepTransLines.getRemarks());

        StepTrans stepTrans = this.stepTransRepository.findById(dbstepTransLines.getStepTrans().getStepTransId()).orElseThrow(
                () -> new EntityNotFoundException("StepTrans not found with this id " + dbstepTransLines.getStepTrans().getStepTransId()));

        List<StepSetupDetails> stepSetup = stepTrans.getStepSetup().getStepSetupDetails();
        var existingTrans = stepTrans.getStepTransLinesList();
        if (reqStepTransLines.getStepStatus().equals(StepStatus.COMPLETED)) {
            if (!(stepSetup.size() == existingTrans.size())) {
                var step = stepSetup.get(existingTrans.size()).getStep();
                StepTransLines newStepTransLines = new StepTransLines();
                newStepTransLines.setStepStatus(StepStatus.PENDING);
                newStepTransLines.setStepTrans(stepTrans);
                newStepTransLines.setStep(step);
                this.stepTransLinesService.saveStepTransLines(newStepTransLines);
            }
        }
        var linesRes = this.stepTransLinesService.saveStepTransLines(dbstepTransLines);
        ApiRequestResponse response = new ApiRequestResponse();
        response.setHttpStatus(HttpStatus.OK.name());
        response.setMessage("Successfully updated step trans");
        ApiRequestResponseDetail details = ApiRequestResponseDetail.builder()
                .objectTag("stepTransLinesResponse")
                .object(linesRes)
                .mapperClass(StepTransLinesResponse.class.getName())
                .objectType(ApiRequestResponseDetail.ObjectType.O)
                .build();
        response.getApiRequestResponseDetails().add(details);
        return response;

    }

}
