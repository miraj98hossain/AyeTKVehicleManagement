package com.mhdev.backendservice.mapper;

import com.mhdev.backendservice.entity.StepTransDetails;
import com.mhdev.commonlib.dto.request.StepTransDetailsRequest;
import com.mhdev.commonlib.dto.response.StepTransDetailsResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class,
                StepMapper.class,
                StepTransMapper.class,
                StepStatusTypeMapper.class},
        builder = @Builder(disableBuilder = true))
public interface StepTransDetailsMapper {
    @Mapping(source = "stepTransId", target = "stepTrans")
    StepTransDetails toEntity(StepTransDetailsRequest stepTransDetailsRequest);

    @Mapping(source = "stepTrans.stepTransId", target = "stepTransId")
    StepTransDetailsResponse toResponseDto(StepTransDetails stepTransDetails);
}
