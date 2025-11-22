package com.aye.backendservice.mapper;

import com.aye.RestfulServer.model.Mrole;
import com.aye.commonlib.dto.response.MRoleResponse;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        builder = @Builder(disableBuilder = true))
public interface MRoleMapper {

    MRoleResponse toResponseDto(Mrole mrole);
}
