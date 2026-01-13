package com.aye.backendservice.mapper;


import com.aye.commonlib.dto.request.ExecutablesRequest;
import com.aye.commonlib.dto.response.ExecutablesResponse;
import com.aye.entitylib.entity.Executables;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class},
        builder = @Builder(disableBuilder = true))
public interface ExecutableMapper {

    ExecutablesResponse toResponseDto(Executables executables);

    Executables toEntity(Long id);

    Executables dtoToExecutables(ExecutablesRequest executablesRequest);

}
