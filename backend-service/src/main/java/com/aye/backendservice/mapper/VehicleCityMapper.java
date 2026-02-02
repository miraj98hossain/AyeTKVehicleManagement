package com.aye.backendservice.mapper;


import com.aye.commonlib.dto.response.VehicleCityResponse;
import com.aye.entitylib.entity.vehicleproject.VehicleCity;
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
