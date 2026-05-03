package com.aye.backendservice.service;

import com.aye.RestfulServer.service.UserCodeAccessService;
import com.aye.dtoLib.dto.request.StepTransDetailsLinesRequest;
import com.aye.dtoLib.dto.request.StepTransDetailsRequest;
import com.aye.dtoLib.dto.response.ApiRequestResponse;
import com.aye.dtoLib.dto.response.ApiRequestResponseDetail;
import com.aye.dtoLib.dto.response.StepTransDetailsLinesResponse;
import com.aye.dtoLib.dto.response.StepTransDetailsResponse;
import com.aye.entitylib.entity.UserCodeAccess;
import com.aye.entitylib.entity.order.MItemCatV;
import com.aye.entitylib.entity.vehicleproject.StepTransDetails;
import com.aye.entitylib.entity.vehicleproject.StepTransDetailsLines;
import com.aye.mapper.StepTransDetailsLinesMapper;
import com.aye.mapper.StepTransDetailsMapper;
import jakarta.validation.Valid;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StepTransDetailsViewService {
    private final StepTransDetailsService stepTransDetailsService;
    private final StepTransDetailsMapper stepTransDetailsMapper;
    private final StepTransDetailsLinesMapper stepTrnsDtlLnMapper;
    private final StepTransDetailsCreationService creationService;
    private final MItemCatVService mItemCatVService;
    private final UserCodeAccessService userCodeAccessService;

    public ApiRequestResponse findById(Long stepTransDtlId) {
        StepTransDetails stepTransDetails = stepTransDetailsService.findById(stepTransDtlId);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.O,
                "stepTransDtl",
                StepTransDetailsResponse.class.getName(),
                stepTransDetailsMapper.toResponseDto(stepTransDetails)
        );
    }

    public ApiRequestResponse save(StepTransDetailsRequest stepTransRequest, String userName) {
        List<StepTransDetails> list = creationService.save(stepTransRequest, userName);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.A,
                "data",
                StepTransDetailsResponse.class.getName(),
                this.stepTransDetailsMapper.entityListToResponseList(list)

        );
    }

    public ApiRequestResponse findAllByStepTransId(Long stepTransId) {
        List<StepTransDetails> stepTransDetails = stepTransDetailsService.findAllByStepTransId(stepTransId);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.A,
                "stepTransDtlList",
                StepTransDetailsResponse.class.getName(),
                stepTransDetailsMapper.entityListToResponseList(stepTransDetails)
        );
    }

    public ApiRequestResponse deleteById(Long stepTransDtlId) {
        this.stepTransDetailsService.deleteById(stepTransDtlId);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Successfully Deleted",
                null, null, null, null
        );
    }


    //***Line Section*********************
    public ApiRequestResponse loadingStatusUpdate(Long stepTransLineId, Long detailLineId, String stepStatus, String userName) throws ExecutionControl.NotImplementedException {
        StepTransDetailsLines stepTransDetailsLines = this.stepTransDetailsService.updateLine(stepTransLineId, detailLineId, stepStatus, userName);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.O,
                "data",
                StepTransDetailsLinesResponse.class.getName(),
                this.stepTrnsDtlLnMapper.toResponseDto(stepTransDetailsLines)
        );
    }

    public ApiRequestResponse saveLine(@Valid StepTransDetailsLinesRequest stepTrnsDtlLnsReq, String userName) {
        List<StepTransDetailsLines> stepTransDetailsLines = this.creationService.saveLine(stepTrnsDtlLnsReq, userName);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.A,
                "data",
                StepTransDetailsLinesResponse.class.getName(),
                this.stepTrnsDtlLnMapper.entityListToResponseList(stepTransDetailsLines)
        );
    }

    public ApiRequestResponse findStDtlLineById(Long stepTransDtlLnId) {
        StepTransDetailsLines stepTransDetailsLines = this.stepTransDetailsService.findStDtlLineById(stepTransDtlLnId);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.O,
                "stepTransDtlLn",
                StepTransDetailsLinesResponse.class.getName(),
                stepTrnsDtlLnMapper.toResponseDto(stepTransDetailsLines)
        );
    }


    public ApiRequestResponse findAllByStTrnDtlId(Long stepTransDtlId) {
        List<StepTransDetailsLines> stepTransDetailsLines = this.stepTransDetailsService.findAllByStTrnDtlId(stepTransDtlId);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.A,
                "stepTransDtlLnList",
                StepTransDetailsLinesResponse.class.getName(),
                stepTransDetailsLines.stream().map(stepTrnsDtlLnMapper::toResponseDto).toList()
        );
    }


    public ApiRequestResponse deleteLineById(Long stepTransDtlLnId) {
        this.stepTransDetailsService.deleteById(stepTransDtlLnId);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Successfully Deleted",
                null, null, null, null
        );
    }

    public ApiRequestResponse findAllLinesByStepTransIdAndUserItemAccess(Long stepTransId, String userName) {
        List<StepTransDetailsResponse> detailsList = this.stepTransDetailsMapper.entityListToResponseList(this.stepTransDetailsService.findAllLinesByStepTransIdAndUserItemAccess(stepTransId));

        List<String> itemAccessList = userCodeAccessService.findAllUserItemAccess(userName).stream()
                .map(UserCodeAccess::getItemCatComb).toList();
        List<Long> itemCatVList = mItemCatVService.findAllByItemCombinations(itemAccessList)
                .stream()
                .map(MItemCatV::getInventoryItemId)
                .toList();
        List<StepTransDetailsLinesResponse> stepTransDetailsLines =
                detailsList.stream()
                        .flatMap(item ->
                                item.getStepTransDetailsLines()
                                        .stream()
                                        .filter(stepTransDtlLnRes -> itemCatVList.contains(stepTransDtlLnRes.getInvItemId()))
                                        .peek(e -> e.setCustName(item.getCustName()))
                        )
                        .toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.A,
                "stepTransDtlLnList",
                StepTransDetailsLinesResponse.class.getName(),
                stepTransDetailsLines
        );
    }

}
