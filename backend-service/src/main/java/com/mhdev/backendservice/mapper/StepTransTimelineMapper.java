package com.mhdev.backendservice.mapper;

import com.mhdev.backendservice.entity.StepTransTimeline;
import com.mhdev.commonlib.dto.request.StepTransTimelineRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class,
                StepMapper.class,
//                StepTransMapper.class,
                StepTransLinesMapper.class,
                StepStatusTypeMapper.class},
        builder = @Builder(disableBuilder = true))
public interface StepTransTimelineMapper {
    //    @Mapping(source = "stepTransId",target = "stepTrans")
    @Mapping(source = "stepTransLinesId", target = "stepTransLines")
    @Mapping(source = "stepId", target = "step")
    @Mapping(source = "stepStatus", target = "stepStatus")
    StepTransTimeline toEntity(StepTransTimelineRequest stepTransTimelineRequest);
}
