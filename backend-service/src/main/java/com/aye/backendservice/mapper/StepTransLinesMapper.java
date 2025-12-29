package com.aye.backendservice.mapper;


import com.aye.backendservice.entity.StepTransLines;
import com.aye.commonlib.dto.request.StepTransLinesRequest;
import com.aye.commonlib.dto.response.StepTransLinesResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class,
                StepStatusTypeMapper.class,
                StepMapper.class},
        builder = @Builder(disableBuilder = true))
public interface StepTransLinesMapper {

    StepTransLines toEntity(Long id);


    @Mapping(source = "stepStatus.", target = "stepStatus", qualifiedByName = "toStepStatusType")
    StepTransLines toEntity(StepTransLinesRequest stepTransLinesRequest);

    @Mapping(source = "stepTrans.stepTransId", target = "stepTransId")
    @Mapping(source = "stepTrans.stepTransNo", target = "stepTransNo")
    @Mapping(source = "stepTrans.vehicleNumber", target = "vehicleNumber")
    @Mapping(source = "stepTrans.transportName", target = "transportName")
    @Mapping(source = "stepTrans.driverPhoneNo", target = "driverPhoneNo")
    @Mapping(source = "stepTrans.driverName", target = "driverName")
    @Mapping(source = "stepSetupDetails.step.stepId", target = "stepId")
    @Mapping(source = "stepSetupDetails.step.stepName", target = "stepName")
    @Mapping(source = "stepStatus", target = "stepStatus", qualifiedByName = "toDisplayName")
    StepTransLinesResponse toResponseDto(StepTransLines stepTransLines);
}
