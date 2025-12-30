package com.aye.backendservice.service;

import com.aye.RestfulServer.model.Muser;
import com.aye.RestfulServer.service.MuserService;
import com.aye.backendservice.entity.StepTransDetails;
import com.aye.backendservice.entity.StepTransDetailsLines;
import com.aye.backendservice.mapper.StepTransDetailsLinesMapper;
import com.aye.backendservice.mapper.StepTransDetailsMapper;
import com.aye.backendservice.repository.StepTransDetailsLinesRepository;
import com.aye.backendservice.repository.StepTransDetailsRepository;
import com.aye.commonlib.dto.request.StepTransDetailsLinesRequest;
import com.aye.commonlib.dto.request.StepTransDetailsRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
import com.aye.commonlib.dto.response.StepTransDetailsLinesResponse;
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
    private StepTransDetailsRepository stepTrnsDtlRepo;
    @Autowired
    private StepTransDetailsLinesRepository stepTrnsDtlLnRepo;
    @Autowired
    private StepTransDetailsMapper stepTransDetailsMapper;
    @Autowired
    private StepTransDetailsLinesMapper stepTrnsDtlLnMapper;
    @Autowired
    private MuserService muserService;

    @Override
    public ApiRequestResponse save(StepTransDetailsRequest stepTransDRequest, String userName) {
        Muser muser = muserService.findByUserName(userName);
        if (stepTransDRequest.getStepTransDtlId() != null) {
            var dbTransDtl = stepTrnsDtlRepo.findById(stepTransDRequest.getStepTransDtlId()).orElseThrow(
                    () -> new EntityNotFoundException("StepTransDtl Not Found with id: " + stepTransDRequest.getStepTransDtlId())
            );
            stepTransDetailsMapper.dtoToEntity(stepTransDRequest, dbTransDtl);
            dbTransDtl.setUpdatedBy(Long.valueOf(muser.getId()));
            dbTransDtl = stepTrnsDtlRepo.save(dbTransDtl);
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
        stepTransDetails = stepTrnsDtlRepo.save(stepTransDetails);
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
        StepTransDetails stepTransDetails = stepTrnsDtlRepo.findById(stepTransDtlId).orElseThrow(
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
        List<StepTransDetails> stepTransDetails = stepTrnsDtlRepo.findAllByStepTrans_StepTransId(stepTransId);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.A,
                "stepTransDtlList",
                StepTransDetailsResponse.class.getName(),
                stepTransDetails.stream().map(stepTransDetailsMapper::toResponseDto).toList()
        );
    }

    //***Line Section*********************
    @Override
    public ApiRequestResponse saveStDtlLine(StepTransDetailsLinesRequest stepTrnsDtlLnsReq, String userName) {
        Muser muser = muserService.findByUserName(userName);
        if (stepTrnsDtlLnsReq.getStepTransDtlLnId() != null) {
            var dbTransDtl = stepTrnsDtlLnRepo.findById(stepTrnsDtlLnsReq.getStepTransDtlLnId()).orElseThrow(
                    () -> new EntityNotFoundException("Step Trans Details Line Not Found with id: " + stepTrnsDtlLnsReq.getStepTransDtlLnId())
            );
            stepTrnsDtlLnMapper.dtoToEntity(stepTrnsDtlLnsReq, dbTransDtl);
            dbTransDtl.setUpdatedBy(Long.valueOf(muser.getId()));
            dbTransDtl = stepTrnsDtlLnRepo.save(dbTransDtl);
            return ApiRequestResponseMaker.make(
                    HttpStatus.OK.name(),
                    "Success",
                    ApiRequestResponseDetail.ObjectType.O,
                    "stepTransDtlLn",
                    StepTransDetailsLinesResponse.class.getName(),
                    stepTrnsDtlLnMapper.toResponseDto(dbTransDtl)
            );
        }
        StepTransDetailsLines stepTransDetails = stepTrnsDtlLnMapper.dtoToEntity(stepTrnsDtlLnsReq);
        stepTransDetails.setCreatedBy(Long.valueOf(muser.getId()));
        stepTransDetails.setStepTransDtlLnNo(noGenService.createTransDtlLNo());
        stepTransDetails = stepTrnsDtlLnRepo.save(stepTransDetails);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.O,
                "stepTransDtlLn",
                StepTransDetailsLinesResponse.class.getName(),
                stepTrnsDtlLnMapper.toResponseDto(stepTransDetails)
        );

    }

    @Override
    public ApiRequestResponse findStDtlLineById(Long stepTransDtlLnId) {
        StepTransDetailsLines stepTransDetailsLines = stepTrnsDtlLnRepo.findById(stepTransDtlLnId).orElseThrow(
                () -> new EntityNotFoundException("Step Trans Details Line Not Found with id: " + stepTransDtlLnId)
        );
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.O,
                "stepTransDtlLn",
                StepTransDetailsLinesResponse.class.getName(),
                stepTrnsDtlLnMapper.toResponseDto(stepTransDetailsLines)
        );
    }

    @Override
    public ApiRequestResponse findAllByStTrnDtlId(Long stepTransDtlId) {
        List<StepTransDetailsLines> stepTransDetailsLines = stepTrnsDtlLnRepo
                .findAllByStepTransDetails_StepTransDtlId(stepTransDtlId);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.A,
                "stepTransDtlLnList",
                StepTransDetailsLinesResponse.class.getName(),
                stepTransDetailsLines.stream().map(stepTrnsDtlLnMapper::toResponseDto).toList()
        );
    }
}
