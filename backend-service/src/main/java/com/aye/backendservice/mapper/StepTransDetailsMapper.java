package com.aye.backendservice.mapper;

import com.aye.backendservice.entity.StepTransDetails;
import com.aye.commonlib.dto.request.StepTransDetailsRequest;
import com.aye.commonlib.dto.response.StepTransDetailsResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class,
                StepTransMapper.class},
        builder = @Builder(disableBuilder = true))
public interface StepTransDetailsMapper {

    StepTransDetails toEntity(Long id);

    @Mapping(source = "stepTransId", target = "stepTrans")
    StepTransDetails dtoToEntity(StepTransDetailsRequest stepTransDetailsRequest);

    @Mapping(source = "stepTransId", target = "stepTrans")
    void dtoToEntity(StepTransDetailsRequest stepTransDetailsRequest, @MappingTarget StepTransDetails stepTransDetails);

    @Mapping(source = "stepTrans.stepTransId", target = "stepTransId")
    @Mapping(source = "stepTrans.stepTransNo", target = "stepTransNo")
    StepTransDetailsResponse toResponseDto(StepTransDetails stepTransDetails);
}
