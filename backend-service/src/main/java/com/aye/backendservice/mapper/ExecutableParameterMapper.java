package com.aye.backendservice.mapper;


import com.aye.commonlib.dto.request.ExecutableParameterRequest;
import com.aye.commonlib.dto.response.ExecutableParameterResponse;
import com.aye.entitylib.entity.ExecutableParameter;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class},
        builder = @Builder(disableBuilder = true))
public interface ExecutableParameterMapper {
    @Mapping(source = "executables.id", target = "executablesId")
    @Mapping(source = "executables.name", target = "executablesName")
    ExecutableParameterResponse toResponseDto(ExecutableParameter executableParameter);

    ExecutableParameter toEntity(Long id);

    ExecutableParameter dtoToEntity(ExecutableParameterRequest executableParameterRequest);
}
