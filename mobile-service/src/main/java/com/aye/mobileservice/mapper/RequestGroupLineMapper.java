package com.aye.mobileservice.mapper;

import com.aye.RestfulServer.model.RequestGroupLine;
import com.aye.commonlib.dto.response.RequestGroupLineResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        builder = @Builder(disableBuilder = true))
public interface RequestGroupLineMapper {
    @Mapping(source = "requestGroupHeader.id", target = "reqGrpHdrId")
    RequestGroupLineResponse toResponseDto(RequestGroupLine requestGroupLine);
}
