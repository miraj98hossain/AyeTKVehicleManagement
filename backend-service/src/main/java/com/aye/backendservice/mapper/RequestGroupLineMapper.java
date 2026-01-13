package com.aye.backendservice.mapper;


import com.aye.commonlib.dto.request.RequestGroupLineRequest;
import com.aye.commonlib.dto.response.RequestGroupLineResponse;
import com.aye.entitylib.entity.RequestGroupLine;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        builder = @Builder(disableBuilder = true))
public interface RequestGroupLineMapper {
    @Mapping(source = "requestGroupHeader.id", target = "reqGrpHdrId")
    RequestGroupLineResponse toResponseDto(RequestGroupLine requestGroupLine);

    RequestGroupLine dtoToEntity(RequestGroupLineRequest requestGroupHeader);
}
