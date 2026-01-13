package com.aye.backendservice.mapper;


import com.aye.commonlib.dto.response.StepTransTimeStampVResponse;
import com.aye.entitylib.entity.StepTransTimeStampV;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * @author: Miraj
 * @date: 01/01/2026
 * @time: 13:31
 * @project: AyeTKVehicleManagement
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class},
        builder = @Builder(disableBuilder = true))
public interface StepTransTimeStampVMapper {
    StepTransTimeStampVResponse toResponseDto(StepTransTimeStampV stepTransTimeStampV);
}
