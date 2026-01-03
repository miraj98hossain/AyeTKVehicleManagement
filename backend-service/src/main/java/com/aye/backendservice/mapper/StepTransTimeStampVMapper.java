package com.aye.backendservice.mapper;

import com.aye.backendservice.entity.StepTransTimeStampV;
import com.aye.commonlib.dto.response.StepTransTimeStampVResponse;
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
