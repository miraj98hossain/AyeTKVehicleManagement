package com.aye.backendservice.mapper;


import com.aye.commonlib.dto.response.ScaleSetupResponse;
import com.aye.entitylib.entity.ScaleSetup;
import org.mapstruct.*;

/**
 * @author: Miraj
 * @date: 05/01/2026
 * @time: 16:20
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class},
        builder = @Builder(disableBuilder = true))
public interface ScaleSetupMapper {
    @Mapping(target = "orgCode", source = "orgHierarchy.code")
    @Mapping(target = "orgId", source = "orgHierarchy.id")
    ScaleSetupResponse toResponseDto(ScaleSetup scaleSetup);

    @Mapping(target = "orgCode", source = "orgHierarchy.code")
    @Mapping(target = "orgId", source = "orgHierarchy.id")
    @Mapping(target = "ipAddress", ignore = true)
    ScaleSetupResponse toResponseDtoWOIP(ScaleSetup scaleSetup);

    ScaleSetup toEntity(Long scaleSetupId);

}
