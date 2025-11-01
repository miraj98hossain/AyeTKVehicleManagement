package com.mhdev.backendservice.mapper;

import com.mhdev.backendservice.entity.StepSetupDetails;
import com.mhdev.commonlib.dto.request.StepSetupDetailsRequest;
import com.mhdev.commonlib.dto.response.StepSetupDetailsResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class, StepMapper.class},
        builder = @Builder(disableBuilder = true))
public interface StepSetupDetailsMapper {
    StepSetupDetails toEntity(Long id);

    //Long toId(StepDetails value);
    @Mapping(source = "stepId", target = "step")
    StepSetupDetails toEntity(StepSetupDetailsRequest stepDetailsRequest);

    @Mapping(source = "step.stepId", target = "stepId")
    @Mapping(source = "stepSetup.stepSetupId", target = "stepSetupId")
    StepSetupDetailsResponse toResponseDto(StepSetupDetails stepDetails);
}
