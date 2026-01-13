package com.aye.backendservice.mapper;


import com.aye.commonlib.dto.request.StepSetupDetailsRequest;
import com.aye.commonlib.dto.response.StepSetupDetailsResponse;
import com.aye.entitylib.entity.StepSetupDetails;
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
    @Mapping(target = "stepNameWithSetupDesc", expression = "java(concatStepNameWithSetupDesc(stepDetails.getStep().getStepName(), stepDetails.getStepSetup().getDescription()))")
    StepSetupDetailsResponse toResponseDto(StepSetupDetails stepDetails);


    default String concatStepNameWithSetupDesc(String stepName, String setupDescription) {
        return (stepName != null ? stepName : "") + " " + "(" + (setupDescription != null ? setupDescription : "") + ")";
    }
}
