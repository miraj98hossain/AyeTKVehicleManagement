package com.aye.backendservice.mapper;

import com.aye.backendservice.entity.StepSetupDetails;
import com.aye.commonlib.dto.request.StepSetupDetailsRequest;
import com.aye.commonlib.dto.response.StepSetupDetailsResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class,
                StepSetupMapper.class,
                StepMapper.class,
                CommonTypeMapper.class},
        builder = @Builder(disableBuilder = true))
public interface StepSetupDetailsMapper {
    StepSetupDetails toEntity(Long id);

    //Long toId(StepDetails value);
    @Mapping(source = "stepId", target = "step")
    @Mapping(source = "stepSetupId", target = "stepSetup")
    StepSetupDetails toEntity(StepSetupDetailsRequest stepDetailsRequest);

    @Mapping(source = "step.stepId", target = "stepId")
    @Mapping(source = "step.stepName", target = "stepName")
    @Mapping(source = "stepSetup.stepSetupId", target = "stepSetupId")
    @Mapping(source = "stepSetup.description", target = "setupDescription")
    StepSetupDetailsResponse toResponseDto(StepSetupDetails stepDetails);
}
