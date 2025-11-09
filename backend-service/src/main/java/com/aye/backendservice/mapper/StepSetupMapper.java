package com.aye.backendservice.mapper;

import com.aye.backendservice.entity.StepSetup;
import com.aye.commonlib.dto.request.StepSetupRequest;
import com.aye.commonlib.dto.response.StepSetupResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class,
                StepMapper.class,
                StepSetupDetailsMapper.class},
        builder = @Builder(disableBuilder = true))
public interface StepSetupMapper {
    StepSetup toEntity(Long id);

    //Long toId(StepDetails value);
    @Mapping(source = "stepSetupDetailsRequests", target = "stepSetupDetails")
    StepSetup toEntity(StepSetupRequest stepSetupRequest);

    StepSetupResponse toResponseDto(StepSetup stepSetup);

}
