package com.aye.backendservice.mapper;

import com.aye.RestfulServer.model.common.AppModuleCode;
import com.aye.RestfulServer.model.om.AppModule;
import com.aye.commonlib.dto.request.AppModuleRequest;
import com.aye.commonlib.dto.response.AppModuleResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class},
        builder = @Builder(disableBuilder = true))
public interface AppModuleMapper {
    AppModuleResponse toResponseDto(AppModule appModule);

    AppModule toEntity(Long id);


    @Mapping(target = "moduleCode", expression = "java(stringToEnum(request.getModuleCode()))")
    AppModule dtoToEntity(AppModuleRequest request);

    
    default AppModuleCode stringToEnum(String code) {
        if (code == null) return null;
        try {
            return AppModuleCode.valueOf(code.toUpperCase()); // supports lowercase input
        } catch (IllegalArgumentException e) {
            return null; // avoid breaking execution if user sends invalid code
        }
    }
}
