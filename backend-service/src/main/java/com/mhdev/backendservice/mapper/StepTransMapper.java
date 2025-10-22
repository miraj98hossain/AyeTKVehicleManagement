package com.mhdev.backendservice.mapper;

import com.mhdev.backendservice.entity.StepTrans;
import com.mhdev.commonlib.dto.request.StepTransRequest;
import com.mhdev.commonlib.dto.response.StepTransResponse;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR,nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,uses = {ReferenceMapper.class},builder = @Builder(disableBuilder = true))
public interface StepTransMapper {
    StepTrans toEntity(Long id);
    //Long toId(StepTrans value);
    StepTrans toEntity(StepTransRequest stepTransRequest);
    StepTransResponse toResponseDto(StepTrans stepTrans);
}
