package com.aye.backendservice.mapper;

import com.aye.backendservice.entity.StepTransDetailsLines;
import com.aye.commonlib.dto.request.StepTransDetailsLinesRequest;
import com.aye.commonlib.dto.response.StepTransDetailsLinesResponse;
import org.mapstruct.*;

/**
 * @author: Miraj
 * @date: 29/12/2025
 * @time: 17:52
 * @project: AyeTKVehicleManagement
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class, StepTransDetailsMapper.class},
        builder = @Builder(disableBuilder = true))
public interface StepTransDetailsLinesMapper {

    StepTransDetailsLines toEntity(Long id);

    @Mapping(source = "stepTransDetails.stepTransDtlId", target = "stepTransDtlId")
    StepTransDetailsLinesResponse toResponseDto(StepTransDetailsLines stepTransDetailsLines);

    @Mapping(source = "stepTransDtlId", target = "stepTransDetails")
    StepTransDetailsLines dtoToEntity(StepTransDetailsLinesRequest stepTransDetailsLinesRequest);

    @Mapping(source = "stepTransDtlId", target = "stepTransDetails")
    void dtoToEntity(StepTransDetailsLinesRequest stepTransDetailsLinesRequest, @MappingTarget StepTransDetailsLines stepTransDetailsLines);

}
