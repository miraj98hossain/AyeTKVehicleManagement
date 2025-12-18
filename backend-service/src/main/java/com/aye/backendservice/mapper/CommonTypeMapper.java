package com.aye.backendservice.mapper;

import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * @author: Miraj
 * @date: 17/12/2025
 * @time: 15:53
 * @project: AyeTKVehicleManagement
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        builder = @Builder(disableBuilder = true))
public interface CommonTypeMapper {

    default Boolean map(Integer value) {
        return value != null && value == 1;
    }

    default Integer map(Boolean value) {
        return Boolean.TRUE.equals(value) ? 1 : 0;
    }
}
