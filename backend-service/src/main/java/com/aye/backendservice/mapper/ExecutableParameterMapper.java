package com.aye.backendservice.mapper;

import com.aye.RestfulServer.model.ExecutableParameter;
import com.aye.commonlib.dto.request.ExecutableParameterRequest;
import com.aye.commonlib.dto.response.ExecutableParameterResponse;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        builder = @Builder(disableBuilder = true))
public interface ExecutableParameterMapper {
    
    ExecutableParameterResponse toResponseDto(ExecutableParameter executableParameter);
    ExecutableParameter dtoToEntity(ExecutableParameterRequest executableParameterRequest);
}
