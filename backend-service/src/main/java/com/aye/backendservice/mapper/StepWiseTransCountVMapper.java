package com.aye.backendservice.mapper;


import com.aye.commonlib.dto.response.StepWiseTransCountVResponse;
import com.aye.entitylib.entity.StepWiseTransCountV;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        builder = @Builder(disableBuilder = true))
public interface StepWiseTransCountVMapper {
    StepWiseTransCountVResponse toResponseDto(StepWiseTransCountV stepWiseTransCountV);
}
