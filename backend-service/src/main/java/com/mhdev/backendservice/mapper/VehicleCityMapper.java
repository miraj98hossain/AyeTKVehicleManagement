package com.mhdev.backendservice.mapper;

import com.mhdev.backendservice.entity.VehicleCity;
import com.mhdev.commonlib.dto.response.VehicleCityResponse;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,

        builder = @Builder(disableBuilder = true))
public interface VehicleCityMapper {
    VehicleCityResponse toResponseDto(VehicleCity vehicleCity);

}
