package com.aye.backendservice.service.implementations;

import com.aye.RestfulServer.model.Muser;
import com.aye.RestfulServer.service.MuserService;
import com.aye.backendservice.entity.StepTransDetails;
import com.aye.backendservice.mapper.StepTransDetailsMapper;
import com.aye.backendservice.repository.StepTransDetailsRepository;
import com.aye.backendservice.service.ApiRequestResponseMaker;
import com.aye.backendservice.service.StepTransDetailsService;
import com.aye.commonlib.dto.request.StepTransDetailsRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
import com.aye.commonlib.dto.response.StepTransDetailsResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StepTransDetailsServiceImpl implements StepTransDetailsService {
    @Autowired
    NoGenService noGenService;
    @Autowired
    private StepTransDetailsRepository stepTransDetailsRepository;
    @Autowired
    private StepTransDetailsMapper stepTransDetailsMapper;
    @Autowired
    private MuserService muserService;

    @Override
    public ApiRequestResponse save(StepTransDetailsRequest stepTransDRequest, String userName) {
        Muser muser = muserService.findByUserName(userName);
        if (stepTransDRequest.getStepTransDtlId() != null) {
            var dbTransDtl = stepTransDetailsRepository.findById(stepTransDRequest.getStepTransDtlId()).orElseThrow(
                    () -> new EntityNotFoundException("StepTransDtl Not Found with id: " + stepTransDRequest.getStepTransDtlId())
            );
            stepTransDetailsMapper.dtoToEntity(stepTransDRequest, dbTransDtl);
            dbTransDtl.setUpdatedBy(Long.valueOf(muser.getId()));
            dbTransDtl = stepTransDetailsRepository.save(dbTransDtl);
            return ApiRequestResponseMaker.make(
                    HttpStatus.OK.name(),
                    "Success",
                    ApiRequestResponseDetail.ObjectType.O,
                    "stepTransDtl",
                    StepTransDetailsResponse.class.getName(),
                    stepTransDetailsMapper.toResponseDto(dbTransDtl)
            );
        }
        StepTransDetails stepTransDetails = stepTransDetailsMapper.dtoToEntity(stepTransDRequest);
        stepTransDetails.setCreatedBy(Long.valueOf(muser.getId()));
        stepTransDetails.setStepTransDtlNo(noGenService.createTransDNo());
        stepTransDetails = stepTransDetailsRepository.save(stepTransDetails);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.O,
                "stepTransDtl",
                StepTransDetailsResponse.class.getName(),
                stepTransDetailsMapper.toResponseDto(stepTransDetails)
        );
    }

    @Override
    public ApiRequestResponse findById(Long stepTransDtlId) {
        StepTransDetails stepTransDetails = stepTransDetailsRepository.findById(stepTransDtlId).orElseThrow(
                () -> new EntityNotFoundException("StepTransDtl Not Found with id: " + stepTransDtlId)
        );
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.O,
                "stepTransDtl",
                StepTransDetailsResponse.class.getName(),
                stepTransDetailsMapper.toResponseDto(stepTransDetails)
        );
    }

    @Override
    public ApiRequestResponse findAllByStepTransId(Long stepTransId) {
        List<StepTransDetails> stepTransDetails = stepTransDetailsRepository.findAllByStepTrans_StepTransId(stepTransId);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.A,
                "stepTransDtlList",
                StepTransDetailsResponse.class.getName(),
                stepTransDetails.stream().map(stepTransDetailsMapper::toResponseDto).toList()
        );
    }
}
