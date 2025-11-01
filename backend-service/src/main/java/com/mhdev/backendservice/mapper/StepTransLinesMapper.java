package com.mhdev.backendservice.mapper;


import com.mhdev.backendservice.entity.StepTransLines;
import com.mhdev.commonlib.dto.request.StepTransLinesRequest;
import com.mhdev.commonlib.dto.response.StepTransLinesResponse;
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
    @Mapping(source = "step.stepId", target = "stepId")
    @Mapping(source = "step.stepName", target = "stepName")
    @Mapping(source = "stepStatus", target = "stepStatus", qualifiedByName = "toDisplayName")
    StepTransLinesResponse toResponseDto(StepTransLines stepTransLines);
}
