package com.aye.backendservice.service;

import com.aye.dtoLib.dto.response.*;
import com.aye.mapper.StepTransTotalTImeVMapper;
import com.aye.mapper.StepTransactionVolumeVMapper;
import com.aye.mapper.StepWiseVehicleSummaryVMapper;
import com.aye.mapper.StrStepWiseTotalTimeVMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author: Miraj
 * @date: 06/06/2026
 * @time: 2:10 pm
 */
@Service
public class StepTransDashBoardViewService {
    @Autowired
    private StepTransDashBoardService service;
    @Autowired
    private StepWiseVehicleSummaryVMapper stepWiseVehicleSummaryVMapper;
    @Autowired
    private StepTransactionVolumeVMapper stepTransactionVolumeVMapper;
    @Autowired
    private StepTransTotalTImeVMapper stepTransTotalTImeVMapper;
    @Autowired
    private StrStepWiseTotalTimeVMapper strStepWiseTotalTimeVMapper;

    public ApiRequestResponse getStepWiseVehicleSummary() {
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "vehicleSummary",
                StepWiseVehicleSummaryVDto.class.getName(), this.stepWiseVehicleSummaryVMapper.toResponseDto(this.service.getStepWiseVehicleSummary())
        );
    }

    public ApiRequestResponse getStepTransactionVolume() {
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "stepTransactionVolume",
                StepTransactionVolumeVDto.class.getName(), this.stepTransactionVolumeVMapper.toResponseDto(this.service.getStepTransactionVolume())
        );
    }

    public ApiRequestResponse getStepTransTotalTime() {
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "stepTransTotalTime",
                StepTransTotalTimeVDto.class.getName(), this.stepTransTotalTImeVMapper.toResponseDto(this.service.getStepTransTotalTime())
        );
    }

    public ApiRequestResponse getStrStepWiseTotalTimeByTransId(Long stepTransId) {
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "strStepWiseTotalTime",
                StrStepWiseTotalTimeVDto.class.getName(), this.strStepWiseTotalTimeVMapper.toResponseDto(this.service.getStrStepWiseTotalTimeByTransId(stepTransId))
        );
    }
}
