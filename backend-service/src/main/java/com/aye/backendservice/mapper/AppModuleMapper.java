package com.aye.backendservice.mapper;

import com.aye.RestfulServer.model.om.AppModule;
import com.aye.commonlib.dto.response.AppModuleResponse;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        builder = @Builder(disableBuilder = true))
public interface AppModuleMapper {
    AppModuleResponse toResponseDto(AppModule appModule);
}
