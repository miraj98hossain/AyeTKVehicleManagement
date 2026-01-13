package com.aye.backendservice.mapper;


import com.aye.commonlib.dto.response.VehicleCityClassResponse;
import com.aye.entitylib.entity.VehicleCityClass;
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
