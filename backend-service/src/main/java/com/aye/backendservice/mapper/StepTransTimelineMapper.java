package com.aye.backendservice.mapper;


import com.aye.commonlib.dto.request.StepTransTimelineRequest;
import com.aye.entitylib.entity.StepTransTimeline;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class,
                StepSetupDetailsMapper.class,
                StepTransLinesMapper.class,},
        builder = @Builder(disableBuilder = true))
public interface StepTransTimelineMapper {
    //    @Mapping(source = "stepTransId",target = "stepTrans")
    @Mapping(source = "stepTransLinesId", target = "stepTransLines")
    StepTransTimeline toEntity(StepTransTimelineRequest stepTransTimelineRequest);
}
