package com.mhdev.backendservice.mapper;

import com.mhdev.backendservice.entity.StepTrans;
import com.mhdev.commonlib.dto.request.StepTransRequest;
import com.mhdev.commonlib.dto.response.StepTransResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR,nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,uses = {ReferenceMapper.class,StepSetupMapper.class, StepTransLinesMapper.class},builder = @Builder(disableBuilder = true))
public interface StepTransMapper {
    StepTrans toEntity(Long id);
    //Long toId(StepTrans value);

    @Mapping(source = "stepSetupId",target = "stepSetup")
    StepTrans toEntity(StepTransRequest stepTransRequest);

    @Mapping(source = "stepSetup.stepSetupId",target = "stepSetupId")
    @Mapping(source = "stepTransLinesList",target = "stepTransLinesResponseList")
    StepTransResponse toResponseDto(StepTrans stepTrans);
}
