package com.aye.backendservice.mapper;


import com.aye.commonlib.dto.response.BeforeTripVResponse;
import com.aye.entitylib.entity.projection.BeforeTripVProjection;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * @author: Miraj
 * @date: 30/12/2025
 * @time: 12:43
 * @project: AyeTKVehicleManagement
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        builder = @Builder(disableBuilder = true))
public interface BeforeTripVMapper {

    BeforeTripVResponse toResponseDto(BeforeTripVProjection beforeTripVProjection);
}
