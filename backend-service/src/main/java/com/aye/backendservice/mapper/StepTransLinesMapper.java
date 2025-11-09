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

    @Mapping(source = "stepTrans.stepTransNo", target = "stepTransNo")
    @Mapping(source = "step.stepId", target = "stepId")
    @Mapping(source = "step.stepName", target = "stepName")
    @Mapping(source = "stepStatus", target = "stepStatus", qualifiedByName = "toDisplayName")
    StepTransLinesResponse toResponseDto(StepTransLines stepTransLines);
}
