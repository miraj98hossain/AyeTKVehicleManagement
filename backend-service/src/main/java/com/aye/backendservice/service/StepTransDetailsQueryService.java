package com.aye.backendservice.service;

/**
 * @author: Miraj
 * @date: 12/01/2026
 * @time: 09:52
 */


import com.aye.backendservice.mapper.StepTransDetailsLinesMapper;
import com.aye.backendservice.mapper.StepTransDetailsMapper;
import com.aye.backendservice.repository.StepTransDetailsLinesRepository;
import com.aye.backendservice.repository.StepTransDetailsRepository;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
import com.aye.commonlib.dto.response.StepTransDetailsLinesResponse;
import com.aye.commonlib.dto.response.StepTransDetailsResponse;
import com.aye.entitylib.entity.StepTransDetails;
import com.aye.entitylib.entity.StepTransDetailsLines;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class StepTransDetailsQueryService {
    @Autowired
    private StepTransDetailsRepository detailsRepo;
    @Autowired
    private StepTransDetailsLinesRepository linesRepo;
    @Autowired
    private StepTransDetailsMapper detailsMapper;
    @Autowired
    private StepTransDetailsLinesMapper linesMapper;

    public ApiRequestResponse findAllByStepTransId(Long stepTransId) {
        List<StepTransDetails> stepTransDetails = detailsRepo.findAllByStepTrans_StepTransId(stepTransId);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.A,
                "stepTransDtlList",
                StepTransDetailsResponse.class.getName(),
                stepTransDetails.stream().map(detailsMapper::toResponseDto).toList()
        );
    }

    public ApiRequestResponse findById(Long stepTransDtlId) {
        StepTransDetails stepTransDetails = detailsRepo.findById(stepTransDtlId).orElseThrow(
                () -> new EntityNotFoundException("StepTransDtl Not Found with id: " + stepTransDtlId)
        );
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.O,
                "stepTransDtl",
                StepTransDetailsResponse.class.getName(),
                detailsMapper.toResponseDto(stepTransDetails)
        );
    }

    public ApiRequestResponse findStDtlLineById(Long stepTransDtlLnId) {
        StepTransDetailsLines stepTransDetailsLines = linesRepo.findById(stepTransDtlLnId).orElseThrow(
                () -> new EntityNotFoundException("Step Trans Details Line Not Found with id: " + stepTransDtlLnId)
        );
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.O,
                "stepTransDtlLn",
                StepTransDetailsLinesResponse.class.getName(),
                linesMapper.toResponseDto(stepTransDetailsLines)
        );
    }

    public ApiRequestResponse findAllByStTrnDtlId(Long stepTransDtlId) {
        List<StepTransDetailsLines> stepTransDetailsLines = linesRepo
                .findAllByStepTransDetails_StepTransDtlId(stepTransDtlId);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.A,
                "stepTransDtlLnList",
                StepTransDetailsLinesResponse.class.getName(),
                stepTransDetailsLines.stream().map(linesMapper::toResponseDto).toList()
        );
    }

}

