package com.aye.backendservice.mapper;

import com.aye.commonlib.dto.response.ScaleSetupResponse;
import com.aye.entitylib.entity.ScaleSetup;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

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
    ScaleSetupResponse toIPInfoResponse(ScaleSetup scaleSetup);

    ScaleSetup toEntity(Long scaleSetupId);

}
