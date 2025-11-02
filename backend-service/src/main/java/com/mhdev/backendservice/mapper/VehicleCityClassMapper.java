package com.mhdev.backendservice.mapper;

import com.mhdev.backendservice.entity.VehicleCityClass;
import com.mhdev.commonlib.dto.response.VehicleCityClassResponse;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        builder = @Builder(disableBuilder = true))
public interface VehicleCityClassMapper {
    VehicleCityClassResponse toResponseDto(VehicleCityClass vehicleCityClass);

}
