package com.aye.backendservice.mapper;

import com.aye.RestfulServer.model.Executables;
import com.aye.commonlib.dto.request.ExecutablesRequest;
import com.aye.commonlib.dto.response.ExecutablesResponse;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        builder = @Builder(disableBuilder = true))
public interface ExecutableMapper {

    ExecutablesResponse toResponseDto(Executables executables);

    Executables dtoToExecutables(ExecutablesRequest executablesRequest);

}
